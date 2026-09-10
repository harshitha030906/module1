package com.harshitha.module1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1Application implements CommandLineRunner {

	@Autowired
	PaymentService paymentServiceobj1;

	@Autowired
	PaymentService paymentServiceobj2;

	public static void main(String[] args) {
		SpringApplication.run(Module1Application.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println(paymentServiceobj1.hashCode());
		System.out.println(paymentServiceobj2.hashCode());
		paymentServiceobj1.pay();
		paymentServiceobj2.pay();
	}

}
