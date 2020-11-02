package cn.linter.oasys.gateway.filter;

import cn.linter.oasys.common.util.JwtUtil;
import cn.linter.oasys.gateway.entiry.Principal;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT过滤器
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    /**
     * Token签名密钥
     */
    @Value("${token.sign.secret}")
    private String secret;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JwtUtil.HEADER_STRING);
        if (token != null && token.startsWith(JwtUtil.TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authentication = null;
            DecodedJWT jwt = JwtUtil.verifyToken(token, secret);
            if (jwt != null) {
                Principal principal = new Principal(jwt.getClaim("username").asString(), jwt.getClaim("roles").asList(String.class));
                authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

}
