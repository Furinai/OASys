package cn.linter.oasys.gateway.config;

import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.gateway.manager.AuthorizationManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Security配置
 *
 * @author wangxiaoyang
 * @since 2020/11/03
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    private final ObjectMapper objectMapper;
    private final AuthorizationManager authorizationManager;

    public WebFluxSecurityConfig(ObjectMapper objectMapper, AuthorizationManager authorizationManager) {
        this.objectMapper = objectMapper;
        this.authorizationManager = authorizationManager;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .pathMatchers("/api/chat").permitAll()
                .pathMatchers("/api/oauth/token").permitAll()
                .pathMatchers("/api/oauth/user/**").authenticated()
                .anyExchange().access(authorizationManager)
                .and().exceptionHandling()
                .authenticationEntryPoint((exchange, exception) -> sendRestResponse(exchange,
                        HttpStatus.UNAUTHORIZED, ResultStatus.UNAUTHORIZED)
                )
                .accessDeniedHandler((exchange, exception) -> sendRestResponse(exchange,
                        HttpStatus.FORBIDDEN, ResultStatus.FORBIDDEN)
                )
                .and().csrf().disable()
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter()))
                        .authenticationEntryPoint((exchange, exception) -> sendRestResponse(exchange,
                                HttpStatus.UNAUTHORIZED, ResultStatus.TOKEN_IS_INVALID)
                        ));
        return http.build();
    }

    public Converter<Jwt, Mono<AbstractAuthenticationToken>> getJwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    private Mono<Void> sendRestResponse(ServerWebExchange exchange, HttpStatus httpStatus, ResultStatus resultStatus) {
        ServerHttpResponse httpResponse = exchange.getResponse();
        httpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        httpResponse.setStatusCode(httpStatus);
        String body;
        try {
            body = objectMapper.writeValueAsString(resultStatus);
        } catch (JsonProcessingException e) {
            body = e.getMessage();
            log.error("Json process error", e);
        }
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = httpResponse.bufferFactory().wrap(bytes);
        return httpResponse.writeWith(Mono.just(buffer));
    }

}