package org.samarBg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;

/**
 * SamarBgApplication is the main entry point for the SamarBG application.
 */
@SpringBootApplication
public class SamarBgApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(SamarBgApplication.class, args);
    }

    // Additional configuration or bean definitions can be added here if needed

}
