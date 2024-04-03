package org.samarBg.config;

import org.modelmapper.ModelMapper;
import org.samarBg.service.AddOffersService;
import org.samarBg.service.implementation.AddOffersImpl;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import java.nio.charset.StandardCharsets;


@Configuration
@EnableWebSecurity
@EnableCaching
public class BeansConfig {
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Bean
    public ModelMapper modelMapper (){
        return new ModelMapper();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }


    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }


}
