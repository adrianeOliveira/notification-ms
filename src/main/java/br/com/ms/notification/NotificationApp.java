package br.com.ms.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = { "br.com.ms.notification.entity" })
@ComponentScan(basePackages = { "br.com.ms.notification" })
public class NotificationApp {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApp.class, args);
	}
}
