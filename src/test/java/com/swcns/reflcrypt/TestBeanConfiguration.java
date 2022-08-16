package com.swcns.reflcrypt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfiguration {
    @Bean
    public AnnotationTestMethodClass testMethodClass() {
        return new AnnotationTestMethodClass();
    }
}
