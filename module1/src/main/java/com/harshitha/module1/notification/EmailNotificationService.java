package com.harshitha.module1.notification;

import com.harshitha.module1.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationService implements NotificationService {
    public void send(String email){
        System.out.println("sending email" + email);
    }
}
