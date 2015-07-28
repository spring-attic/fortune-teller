package io.spring.cloud.samples.fortuneteller.ui;

import io.pivotal.springcloud.ssl.CloudFoundryCertificateTruster;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Application {

    public static void main(String[] args) {
        CloudFoundryCertificateTruster.trustApiCertificate();
        SpringApplication.run(Application.class, args);
    }
}
