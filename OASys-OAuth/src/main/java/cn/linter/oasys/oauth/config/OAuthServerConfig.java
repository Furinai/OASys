package cn.linter.oasys.oauth.config;

import cn.linter.oasys.oauth.entity.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.Collections;

/**
 * 授权服务OAuth配置
 *
 * @author wangxiaoyang
 * @since 2020/11/03
 */
@Configuration
@EnableAuthorizationServer
public class OAuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private KeyPair keyPair;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * ClientId
     */
    @Value("${client.id}")
    private String clientId;
    /**
     * Client密码
     */
    @Value("${client.secret}")
    private String clientSecret;
    /**
     * 授权类型
     */
    @Value("${grant.type}")
    private String grantType;
    /**
     * 授权范围
     */
    @Value("${grant.scope}")
    private String grantScope;
    /**
     * Token有效时间
     */
    @Value("${token.validity.second}")
    private int validitySecond;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String password = passwordEncoder.encode(clientSecret);
        clients.inMemory()
                .withClient(clientId)
                .secret(password)
                .scopes(grantScope)
                .authorizedGrantTypes(grantType)
                .accessTokenValiditySeconds(validitySecond);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setKeyPair(keyPair);
        enhancerChain.setTokenEnhancers(Arrays.asList(getTokenEnhancer(), tokenConverter));
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(tokenConverter)
                .tokenEnhancer(enhancerChain);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }

    public TokenEnhancer getTokenEnhancer() {
        return (accessToken, authentication) -> {
            Principal principal = (Principal) authentication.getPrincipal();
            DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
            oAuth2AccessToken.setAdditionalInformation(Collections.singletonMap("user_id", principal.getId()));
            return oAuth2AccessToken;
        };
    }

}