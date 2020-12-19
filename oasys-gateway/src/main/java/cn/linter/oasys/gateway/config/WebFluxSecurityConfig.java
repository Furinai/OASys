package cn.linter.oasys.gateway.config;

import cn.linter.oasys.common.entity.ResultStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 网关Security配置
 *
 * @author wangxiaoyang
 * @since 2020/11/03
 */
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/api/auth/oauth/token")
                .permitAll()
                .pathMatchers("/api/*/v2/api-docs")
                .permitAll()
                .pathMatchers("/api/**")
                .authenticated()
                .anyExchange()
                .permitAll()
                .and().exceptionHandling()
                .authenticationEntryPoint((exchange, exception) -> sendRestResponse(exchange,
                        HttpStatus.UNAUTHORIZED, ResultStatus.UNAUTHORIZED)
                )
                .accessDeniedHandler((exchange, exception) -> sendRestResponse(exchange,
                        HttpStatus.FORBIDDEN, ResultStatus.FORBIDDEN)
                )
                .and().csrf().disable();
        http.oauth2ResourceServer()
                .authenticationEntryPoint((exchange, exception) -> sendRestResponse(exchange,
                        HttpStatus.BAD_REQUEST, ResultStatus.TOKEN_IS_INVALID)
                )
                .jwt();
        return http.build();
    }

    private Mono<Void> sendRestResponse(ServerWebExchange exchange, HttpStatus httpStatus, ResultStatus resultStatus) {
        ServerHttpResponse httpResponse = exchange.getResponse();
        httpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        httpResponse.setStatusCode(httpStatus);
        byte[] body;
        try {
            body = objectMapper.writeValueAsString(resultStatus).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            body = e.getMessage().getBytes();
        }
        DataBuffer buffer = httpResponse.bufferFactory().wrap(body);
        return httpResponse.writeWith(Mono.just(buffer));
    }

}