package io.wisoft.actuator.custom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyLibraryInfoEndpointConfig {

    @Bean
    public MyLibraryInfoEndpoint myLibraryInfoEndPoint() {
        return new MyLibraryInfoEndpoint();
    }
}
