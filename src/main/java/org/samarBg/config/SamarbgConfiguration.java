package org.samarBg.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
@Configuration
public class SamarbgConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new Pbkdf2PasswordEncoder();
    }
    @Bean
    public ModelMapper modelMapper (){
        return new ModelMapper();
    }


}
