package com.teste.vianuvem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

import static java.util.Arrays.asList;


@Configuration
public class ClientConfiguration {
    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            request.getHeaders().add("user-agent", "spring");
            return execution.execute(request, body);
        };

        restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
        restTemplate.setInterceptors(asList(interceptor));
        return restTemplate;
    }


}
