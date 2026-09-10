package com.harshitha.module1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Bean
    @Scope("prototype")
    public PaymentService paymentService(){
        return new PaymentService();
    }
}
