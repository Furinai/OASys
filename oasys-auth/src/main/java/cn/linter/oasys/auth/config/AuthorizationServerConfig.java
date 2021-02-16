package cn.linter.oasys.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * 授权服务器配置类
 *
 * @author wangxiaoyang
 * @since 2021/2/4
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig {

    @Value("#{'${security.oauth2.authorization.jwt.key-store}'.substring(10)}")
    private String keyLocation;
    @Value("${security.oauth2.authorization.jwt.key-store-password}")
    private String keyPassword;
    @Value("${security.oauth2.authorization.jwt.key-alias}")
    private String keyAlias;

    @Bean
    public KeyPair keyPairFactory() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(keyLocation), keyPassword.toCharArray());
        return keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword.toCharArray());
    }

}
