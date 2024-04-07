package com.popcorn;

import com.popcorn.listener.CustomApplicationReadyEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodoApplication {

	public static void main(String[] args) {
		 SpringApplication.run(TodoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void customEventListener() {
		System.out.println("MY-CUSTOM-EVENT-LISTENER");
	}

}
