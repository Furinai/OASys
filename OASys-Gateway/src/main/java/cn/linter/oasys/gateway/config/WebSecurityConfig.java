package cn.linter.oasys.gateway.config;

import cn.linter.oasys.common.entity.Response;
import cn.linter.oasys.common.util.JwtUtil;
import cn.linter.oasys.gateway.entiry.Principal;
import cn.linter.oasys.gateway.filter.JwtAuthorizationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * SpringSecurity配置
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Token签名密钥
     */
    @Value("${token.sign.secret}")
    private String secret;

    /**
     * Token有效时间，单位：毫秒
     */
    @Value("${token.valid.time}")
    private Long validTime;

    private final ObjectMapper objectMapper;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    public WebSecurityConfig(ObjectMapper objectMapper, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.objectMapper = objectMapper;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers(("/api/*/v2/api-docs"))
                .permitAll()
                .antMatchers("/api/**")
                .authenticated();

        security.formLogin()
                .loginProcessingUrl("/api/user/login")
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Principal principal = ((Principal) authentication.getPrincipal());
                    String token = JwtUtil.generateToken(secret, validTime,
                            principal.getId(), principal.getUsername(), principal.getRoles());
                    out.write(objectMapper.writeValueAsString(Response.sendSuccess("登录成功！", token)));
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(Response.sendError("账号或密码错误！")));
                    out.flush();
                    out.close();
                });

        security.exceptionHandling()
                .authenticationEntryPoint((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(Response.sendError("未登录或已过期，请登录！")));
                    out.flush();
                    out.close();
                })
                .accessDeniedHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(Response.sendError("没有权限进行此操作！")));
                    out.flush();
                    out.close();
                });

        security.logout()
                .logoutUrl("/api/user/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(Response.sendSuccess("注销成功！")));
                    out.flush();
                    out.close();
                });


        security.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        security.csrf().disable();
    }

}