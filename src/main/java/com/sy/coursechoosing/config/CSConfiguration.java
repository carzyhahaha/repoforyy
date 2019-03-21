package com.sy.coursechoosing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.support.WebBindingInitializer;

@Configuration
public class CSConfiguration {

    @Bean
    public WebBindingInitializer myWebBindingInitializer() {
        return new MyWebBindingInitializer();
    }
}
