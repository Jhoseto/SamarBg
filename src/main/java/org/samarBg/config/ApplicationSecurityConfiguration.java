package org.samarBg.config;

import org.samarBg.security.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService,
                                            PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                // Разрешаване на статичните ресурси
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**",
                        "/impl/**",
                        "/video/**").permitAll()
                .antMatchers(
                        "/",
                        "/index",
                        "/allAccessories",
                        "/allHorses",
                        "/allads",
                        "/forgotten_password",
                        "/user/registration",
                        "/registration",
                        "/user/login",
                        "/login",
                        "/user/logout",
                        "/confirm").permitAll()
                // Забраняване на всичко останало за неаутентифицирани потребители
                .anyRequest().authenticated()

//                .and()
//                .formLogin()
//                .loginPage("/user/login") // URL адрес на страницата за вход
//                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
//                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
//                .defaultSuccessUrl("/index")
//                .failureForwardUrl("/login")

                .and()
                .rememberMe() // Запомняне на потребителя
                .key(rememberMeKey()) // Уникален ключ за запомняне

                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me") // Изтриване на бисквитките за запомняне и сесията
                .permitAll()


                .and()
                .sessionManagement() // Настройка на управлението на сесиите
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS); // Сесията не се създава автоматичн
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }



    @Bean
    public String rememberMeKey() {
        return KeyGenerator.generateKey();
    }

    @Bean
    public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
        return new TokenBasedRememberMeServices(rememberMeKey(), userDetailsService);
    }

}
