package com.swcns.reflcrypt;

import com.swcns.reflcrypt.aspect.ReflcryptAspect;
import com.swcns.reflcrypt.core.EncryptionManager;
import com.swcns.reflcrypt.util.ObjectDecryptor;
import com.swcns.reflcrypt.util.ObjectEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@EnableConfigurationProperties(ReflcryptProperties.class)
@Configuration
public class ReflcryptAutoConfiguration {

    private final ReflcryptProperties properties;

    @Bean
    public ReflcryptAspect reflcryptAspect() {
        return new ReflcryptAspect(encryptor(), decryptor());
    }

    @Bean
    public EncryptionManager manager() {
        return new EncryptionManager(properties);
    }

    @Bean
    public ObjectEncryptor encryptor() {
        return new ObjectEncryptor(manager());
    }

    @Bean
    public ObjectDecryptor decryptor() {
        return new ObjectDecryptor(manager());
    }
}
