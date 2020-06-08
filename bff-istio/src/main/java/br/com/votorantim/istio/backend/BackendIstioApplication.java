package br.com.votorantim.istio.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class BackendIstioApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendIstioApplication.class, args);
	}

}
