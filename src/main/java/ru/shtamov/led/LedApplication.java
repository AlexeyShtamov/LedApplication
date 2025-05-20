package ru.shtamov.led;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Slf4j
@SpringBootApplication
public class LedApplication {

	public static void main(String[] args) {
		SpringApplication.run(LedApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(List.of((request, body, execution) -> {
			log.info("Request Body: {}", new String(body, StandardCharsets.UTF_8));
			return execution.execute(request, body);
		}));
		return restTemplate;
	}

}
