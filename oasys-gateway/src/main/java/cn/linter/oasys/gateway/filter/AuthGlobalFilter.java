package cn.linter.oasys.gateway.filter;

import com.nimbusds.jose.JWSObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * 认证信息过滤器
 *
 * @author wangxiaoyang
 * @since 2020/12/17
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    public static final String TOKEN_PREFIX = "bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token == null || token.isEmpty()) {
            return chain.filter(exchange);
        }
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(token.replace(TOKEN_PREFIX, ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String username = jwsObject != null ? jwsObject.getPayload().toJSONObject().getAsString("user_name") : null;
        ServerHttpRequest request = exchange.getRequest().mutate().header("username", username).build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}