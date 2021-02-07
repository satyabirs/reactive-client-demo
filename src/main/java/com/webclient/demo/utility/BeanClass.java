package com.webclient.demo.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BeanClass {

    @Bean
    public WebClient getwebClientBean()
    {
        return WebClient.create("http://localhost:8080/");
    }
}
