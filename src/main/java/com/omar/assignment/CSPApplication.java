package com.omar.assignment;

import com.omar.assignment.common.SpringInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.omar.assignment.entities")
@PropertySource("classpath:db-config.properties")
public class CSPApplication implements ApplicationListener<ApplicationReadyEvent> {

	public static void main(String[] args) {
		SpringApplication.run(CSPApplication.class, args);
	}


	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		SpringInjector springInjector = applicationReadyEvent.getApplicationContext().getBean(SpringInjector.class);
		springInjector.setApplicationContext(applicationReadyEvent.getApplicationContext());
	}
}
