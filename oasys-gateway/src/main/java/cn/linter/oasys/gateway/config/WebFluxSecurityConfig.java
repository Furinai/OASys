package cn.linter.oasys.gateway.config;

import cn.linter.oasys.common.entity.ResultStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
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
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    private final ObjectMapper objectMapper;

    public WebFluxSecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/api/oauth/**", "/api/chat/**")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint((exchange, exception) -> sendRestResponse(exchange,
                        HttpStatus.UNAUTHORIZED, ResultStatus.UNAUTHORIZED)
                )
                .accessDeniedHandler((exchange, exception) -> sendRestResponse(exchange,
                        HttpStatus.FORBIDDEN, ResultStatus.FORBIDDEN)
                )
                .and().csrf().disable()
                .oauth2ResourceServer()
                .authenticationEntryPoint((exchange, exception) -> sendRestResponse(exchange,
                        HttpStatus.UNAUTHORIZED, ResultStatus.TOKEN_IS_INVALID)
                )
                .jwt();
        return http.build();
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
        }
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = httpResponse.bufferFactory().wrap(bytes);
        return httpResponse.writeWith(Mono.just(buffer));
    }

}