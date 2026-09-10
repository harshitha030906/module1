package com.harshitha.module1.notification;

import com.harshitha.module1.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Qualifier("sms")
@ConditionalOnProperty(name = "notification.type", havingValue = "sms")
public class SmsNotifcationService implements NotificationService {
    public void send(String msg) {
        System.out.println("sending sms " + msg);
    }
}
