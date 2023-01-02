package com.synchrony.imageapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ImageapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageapiApplication.class, args);
    }

}
