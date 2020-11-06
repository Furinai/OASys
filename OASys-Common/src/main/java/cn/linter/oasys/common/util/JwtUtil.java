package cn.linter.oasys.common.util;

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
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 从Token获取用户ID
     *
     * @param token Token
     * @return 用户ID
     */
    public static Long getUserId(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token.replace(TOKEN_PREFIX, ""));
        return Long.parseLong((String) jwsObject.getPayload().toJSONObject().get("user_id"));
    }

    /**
     * 从Token获取用户名
     *
     * @param token Token
     * @return 用户名
     */
    public static String getUsername(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token.replace(TOKEN_PREFIX, ""));
        return (String) jwsObject.getPayload().toJSONObject().get("user_name");
    }

}
