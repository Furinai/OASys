package cn.linter.oasys.common.utils;

import com.nimbusds.jose.JWSObject;

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
    public static final String TOKEN_PREFIX = "bearer ";

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
        return jwsObject != null ? jwsObject.getPayload().toJSONObject().getAsNumber("user_id").longValue() : null;
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
        return jwsObject != null ? jwsObject.getPayload().toJSONObject().getAsString("user_name") : null;
    }

}
