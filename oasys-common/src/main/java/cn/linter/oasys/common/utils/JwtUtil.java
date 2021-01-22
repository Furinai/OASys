package cn.linter.oasys.common.utils;

import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

/**
 * JWT工具类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Slf4j
public class JwtUtil {

    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "bearer ";

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
            log.error("Token parse error", e);
        }
        return jwsObject != null ? (String) jwsObject.getPayload().toJSONObject().get("user_name") : null;
    }

}
