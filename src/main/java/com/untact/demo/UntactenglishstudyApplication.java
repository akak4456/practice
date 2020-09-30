package com.untact.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.untact"})
@EntityScan("com.untact.domain")
@EnableJpaRepositories("com.untact.persistent")
public class UntactenglishstudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UntactenglishstudyApplication.class, args);
	}

}
