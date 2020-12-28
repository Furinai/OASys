package cn.linter.oasys.common.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import java.text.ParseException;

/**
 * JWT工具类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
public class JwtUtil {

    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    public String generate(String payloadStr, String secret) throws JOSEException {
        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).
                type(JOSEObjectType.JWT)
                .build();
        //将负载信息封装到Payload中
        Payload payload = new Payload(payloadStr);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建HMAC签名器
        JWSSigner jwsSigner = new MACSigner(secret);
        //签名
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    /**
     * 从Token获取用户ID
     *
     * @param token Token
     * @return 用户ID
     */
    public static Long getUserId(String token) {
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(token.replace(TOKEN_PREFIX, ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jwsObject != null ? Long.valueOf((String) jwsObject.getPayload().toJSONObject().get("user_id")) : null;
    }

    public boolean verify(String token, String secret) throws ParseException, JOSEException {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        //创建HMAC验证器
        JWSVerifier jwsVerifier = new MACVerifier(secret);

        return jwsObject.verify(jwsVerifier);
    }

    /**
     * 从Token获取用户名
     *
     * @param token Token
     * @return 用户名
     */
    public static String getUsername(String token) {
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(token.replace(TOKEN_PREFIX, ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jwsObject != null ? (String) jwsObject.getPayload().toJSONObject().get("user_name") : null;
    }

}
