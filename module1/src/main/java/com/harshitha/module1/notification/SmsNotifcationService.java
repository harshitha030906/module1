package com.harshitha.module1.notification;

import com.harshitha.module1.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class SmsNotifcationService implements NotificationService {
    public void send(String msg) {
        System.out.println("sending email" + msg);
    }
}
