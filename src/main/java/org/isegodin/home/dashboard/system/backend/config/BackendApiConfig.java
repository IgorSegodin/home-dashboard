package org.isegodin.home.dashboard.system.backend.config;

import org.isegodin.home.dashboard.backend.api.i2c.sensor.SensorRestApi;
import org.isegodin.home.dashboard.backend.api.i2c.sensor.data.SensorInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author isegodin
 */
@Configuration
public class BackendApiConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder, ReactiveLoadBalancer.Factory loadBalancerFactory) {
        return builder
                .filter(new ReactorLoadBalancerExchangeFilterFunction(loadBalancerFactory))
                .build();
    }

    @Bean
    public SensorRestApi sensorRestApi(@Value("${home.service.i2c.protocol}") String protocol,
                                       @Value("${home.service.i2c.name}") String serviceUrl,
                                       WebClient webClient) {
        return new SensorRestApi() {
            @Override
            public Flux<SensorInfo> listSensors() {
                return webClient.get()
                        .uri(protocol + "://" + serviceUrl + "/sensor/list")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(SensorInfo.class);
            }

            @Override
            public Mono<Map<String, Object>> getSensorValues(String name) {
                return webClient.get()
                        .uri(protocol + "://" + serviceUrl + "/sensor/value/{name}", name)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<>() {
                        });
            }
        };

    }
}
