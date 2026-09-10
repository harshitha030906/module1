package com.harshitha.module1;

import com.harshitha.module1.notification.EmailNotificationService;
import com.harshitha.module1.notification.SmsNotifcationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1Application implements CommandLineRunner {

	//@Autowired
	NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(Module1Application.class, args);
	}

	//constructor DI
	public Module1Application(
			@Qualifier("sms") NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public void run(String... args) throws Exception {
		notificationService.send("hiii");
	}

}
