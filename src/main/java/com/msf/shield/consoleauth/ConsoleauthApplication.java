package com.msf.shield.consoleauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@RestController
public class ConsoleauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsoleauthApplication.class, args);
		System.out.println("startring app");
	}

	
}
