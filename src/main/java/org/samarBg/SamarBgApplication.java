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

@SpringBootApplication
public class SamarBgApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SamarBgApplication.class, args);
    }

}
