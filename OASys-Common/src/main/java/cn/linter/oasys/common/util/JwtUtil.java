package cn.linter.oasys.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.List;

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
     * Token首部字段名
     */
    public static final String HEADER_STRING = "Authorization";

    /**
     * 生成Token
     *
     * @param secret    签名密钥
     * @param userid    用户ID
     * @param validTime 有效时间
     * @param username  用户名
     * @param roles     角色列表
     * @return Token
     */
    public static String generateToken(String secret, Long validTime, Long userid, String username, List<String> roles) {
        String token = null;
        try {
            token = JWT.create()
                    .withClaim("userid", userid)
                    .withClaim("username", username)
                    .withClaim("roles", roles)
                    .withExpiresAt(new Date(System.currentTimeMillis() + validTime))
                    .sign(Algorithm.HMAC512(secret.getBytes()));
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return TOKEN_PREFIX + token;
    }

    /**
     * 验证Token
     *
     * @param token  Token
     * @param secret 签名密钥
     * @return DecodedJWT实体
     */
    public static DecodedJWT verifyToken(String token, String secret) {
        DecodedJWT jwt = null;
        try {
            jwt = JWT.require(Algorithm.HMAC512(secret.getBytes()))
                    .build().verify(token.replace(JwtUtil.TOKEN_PREFIX, ""));
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return jwt;
    }

    /**
     * 从Token获取用户ID
     *
     * @param token Token
     * @return 用户ID
     */
    public static Long getUserId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userid").asLong();
    }

    /**
     * 从Token获取用户名
     *
     * @param token Token
     * @return 用户名
     */
    public static String getUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username").asString();
    }

    /**
     * 从Token获取用户角色列表
     *
     * @param token Token
     * @return 角色列表
     */
    public static List<String> getRoles(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("roles").asList(String.class);
    }

}
