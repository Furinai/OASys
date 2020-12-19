package cn.linter.oasys.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxiaoyang
 * @since 2020/12/16
 */
@Configuration
@Primary
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    @Autowired
    private GatewayProperties gatewayProperties;

    private final String LOCATION_KEY = NameUtils.GENERATED_NAME_PREFIX + "0";

    @Value("#{'${swagger.resources.exclude}'.split(',')}")
    private List<String> excludeList;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        gatewayProperties.getRoutes().stream().filter(route -> !excludeList.contains(route.getId()))
                .forEach(route -> route.getPredicates().stream().filter(predicate -> ("Path").equals(predicate.getName()))
                        .forEach(predicate -> resources.add(newSwaggerResource(route.getId(), predicate.getArgs().get(LOCATION_KEY)))));
        return resources;
    }

    private SwaggerResource newSwaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setSwaggerVersion("1.0");
        swaggerResource.setLocation(location.replace("**", "v2/api-docs"));
        return swaggerResource;
    }

}