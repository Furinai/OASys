package cn.linter.oasys.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("oauth")
public class KeyController {

    private final KeyPair keyPair;

    public KeyController(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @GetMapping("key")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

}