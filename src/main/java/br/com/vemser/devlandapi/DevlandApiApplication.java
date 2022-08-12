package br.com.vemser.devlandapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableScheduling
public class DevlandApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevlandApiApplication.class, args);
	}

}
