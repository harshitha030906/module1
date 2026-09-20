package com.coursecodingshuttle.module1introduction;

import com.coursecodingshuttle.module1introduction.impl.SmsNotificationService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {

	MessageNotification notification;

	public Module1introductionApplication(@Qualifier("sausage") MessageNotification notification){
		this.notification = notification;
	}

	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		notification.send("hello");
	}
}
