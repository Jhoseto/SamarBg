package org.samarBg.config;

import org.samarBg.securityAndComponent.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfiguration(UserDetailsService customUserDetailsService,
                                            PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Разрешаване на статичните ресурси
                .antMatchers(
                        "/images/usersImg/**",
                        "/images/offerImg/**",
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
                        "/allOffers",
                        "/forgotten_password",
                        "/user/registration",
                        "/registration",
                        "/user/login",
                        "/login",
                        "/user/logout",
                        "/confirm").permitAll()
                // Забраняване на всичко останало за неаутентифицирани потребители
                .anyRequest().authenticated()

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

                // Настройка на управлението на сесиите
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Сесията не се създава автоматично

                // Обработка на изключенията
            .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/login?error=AccessDenied >>>it is only available to administrators<<<");
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/login?error=AccessDenied >>>Need to LOGIN IN first !<<<");
                })

                // други настройки за сигурност...
            .and()
                //CSFR защита
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and();

//                // Ограничава изпълнението на скриптове само от текущия домейн
//                .headers()
//                .contentSecurityPolicy("script-src 'self' 'unsafe-inline' 'unsafe-eval' static/js/");
////
//                // Установява Strict Transport Security (HSTS) за повишаване на сигурността при HTTPS
//            .and()
//                .httpStrictTransportSecurity()
//                .includeSubDomains(true)
//                .maxAgeInSeconds(31536000);
    }






    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    @Bean
    public String rememberMeKey() {
        return KeyGenerator.generateKey();
    }

    @Bean
    public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
        return new TokenBasedRememberMeServices(rememberMeKey(), customUserDetailsService);
    }

}
