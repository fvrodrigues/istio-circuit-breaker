package br.com.votorantim.istio.backend.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Slf4j
@Configuration
public class AppConfiguration {

    @Value("${app.client.host:localhost}")
    private String host;

    @Value("${app.client.port:8080}")
    private Integer port;

    @Bean
    @Primary
    public RestTemplate restClient(RestTemplateBuilder builder) {
        log.info("Configure RestTemplate to connect in: {}", String.format("http://%s:%d", host, port));
        return builder
                .rootUri(String.format("http://%s:%d", host, port))
                .build();
    }

}
