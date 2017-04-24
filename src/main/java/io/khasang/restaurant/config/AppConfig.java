package io.khasang.restaurant.config;

import io.khasang.restaurant.model.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Message message(){
        return new Message("hello bean!");
    }
}
