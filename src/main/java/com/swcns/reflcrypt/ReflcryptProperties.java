package com.swcns.reflcrypt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@AllArgsConstructor @Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "reflcrypt")
public class ReflcryptProperties {
    private String algorithm;
    private String key;
    private String hash;
    private String iv;
}
