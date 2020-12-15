package cn.linter.oasys.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSObject;

import java.text.ParseException;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
public class JWTUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

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
        assert jwsObject != null;
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(jwsObject.getPayload().toString(), new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert map != null;
        return ((Integer) map.get("user_id")).longValue();
        //return (Long) jwsObject.getPayload().toJSONObject().get("user_id");

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
        assert jwsObject != null;
        return (String) jwsObject.getPayload().toJSONObject().get("user_name");
    }

}
