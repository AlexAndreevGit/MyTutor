package com.myTutor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient restClient(){     //Spring -> https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
        return RestClient.create();  //we use the satic ".create()" method
    }

}
