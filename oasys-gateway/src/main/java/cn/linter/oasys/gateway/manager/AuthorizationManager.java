package cn.linter.oasys.gateway.manager;

import cn.linter.oasys.gateway.dto.PermissionDTO;
import cn.linter.oasys.gateway.dto.PermissionRoleDTO;
import cn.linter.oasys.gateway.dto.RoleDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 认证管理器
 *
 * @author wangxiaoyang
 * @since 2021/01/20
 */
@Slf4j
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final WebClient.Builder webClientBuilder;
    private final PathMatcher pathMatcher = new AntPathMatcher();
    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    public AuthorizationManager(WebClient.Builder webClientBuilder, StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        this.webClientBuilder = webClientBuilder;
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String requestMethod = request.getMethodValue();
        String requestPath = request.getPath().value().replace("/api", "");
        List<String> authorities = new ArrayList<>();
        List<PermissionRoleDTO> permissionRoleDTOList = new ArrayList<>();
        String cacheValue = stringRedisTemplate.opsForValue().get("permission-role::resource");
        if (cacheValue == null) {
            try {
                permissionRoleDTOList = CompletableFuture.supplyAsync(() ->
                        webClientBuilder.build().get().uri("http://user-service/permissions/roles").retrieve()
                                .bodyToMono(new ParameterizedTypeReference<List<PermissionRoleDTO>>() {
                                }).blockOptional().orElse(Collections.emptyList())).get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("获取权限角色信息失败", e);
            }
        } else {
            try {
                permissionRoleDTOList = objectMapper.readValue(cacheValue, new TypeReference<List<PermissionRoleDTO>>() {
                });
            } catch (JsonProcessingException e) {
                log.error("Json process error", e);
            }
        }
        for (PermissionRoleDTO permissionRoleDTO : permissionRoleDTOList) {
            PermissionDTO permission = permissionRoleDTO.getPermission();
            boolean isPathMatch = pathMatcher.match(permission.getResourcePath(), requestPath);
            boolean isMethodMatch = permission.getRequestMethod().matches(requestMethod);
            if (isPathMatch && isMethodMatch) {
                List<RoleDTO> roles = permissionRoleDTO.getRoles();
                for (RoleDTO role : roles) {
                    authorities.add(role.getName());
                }
                break;
            }
        }
        return mono.flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}
