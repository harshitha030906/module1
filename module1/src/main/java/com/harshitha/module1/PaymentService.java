package com.harshitha.module1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    public void pay(){
        System.out.println("Paying...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("after payment is done");
    }

    @PostConstruct
    public void init(){
        System.out.println("after postConstruct is done");
    }
}
