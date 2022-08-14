package com.automation.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChatEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatEngineApplication.class, args);
	}

}
