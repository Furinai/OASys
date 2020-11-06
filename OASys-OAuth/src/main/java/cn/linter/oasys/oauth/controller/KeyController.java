package cn.linter.oasys.oauth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * RSA密钥控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/04
 */
@RestController
public class KeyController {

    /**
     * RSA密钥对
     */
    @Autowired
    private KeyPair keyPair;

    /**
     * 获取RSA公钥
     *
     * @return RSA公钥
     */
    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

}