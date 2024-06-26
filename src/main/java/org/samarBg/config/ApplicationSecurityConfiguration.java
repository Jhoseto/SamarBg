package org.samarBg.config;

import org.samarBg.models.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.securityAndComponent.KeyGenerator;
import org.samarBg.service.UserService;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Configuration class for application security settings.
 */
@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * Constructor injecting custom user details service and password encoder.
     */
    @Autowired
    public ApplicationSecurityConfiguration(UserDetailsService customUserDetailsService,
                                            PasswordEncoder passwordEncoder,
                                            UserService userService,
                                            UserRepository userRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * Configures HTTP security settings for the application.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Permit access to static resources
                .antMatchers(
                        "/images/usersImg/**",
                        "/webjars/**",
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
                        "/searching",
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
                // Deny access to any other URLs for unauthenticated users
                .anyRequest().authenticated()

                .and()
                .rememberMe() // Remember me functionality
                .key(rememberMeKey()) // Unique key for remember me

                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/index")
                .addLogoutHandler((request, response, authentication) -> {
                    UserEntity currentUser = userService.getCurrentUser();
                    currentUser.setOnlineStatus(0);
                    userRepository.save(currentUser);
                })
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me") // Delete cookies for session and remember me
                .permitAll()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Session creation policy

                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    String errorMessage = "Съдържанието е достъпно само за Администратори !";
                    String encodedMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
                    response.sendRedirect("/login?error=" + encodedMessage);
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    String errorMessage = "Моля влезте в профила си или се регистрирайте за да продължите !";
                    String encodedMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
                    response.sendRedirect( "/login?error=" + encodedMessage);
                })

                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and();
    }

    /**
     * Configures authentication manager builder to use custom user details service and password encoder.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * Generates a unique key for remember me functionality.
     */
    @Bean
    public String rememberMeKey() {
        return KeyGenerator.generateKey();
    }

    /**
     * Creates a token-based remember me services using the custom user details service and remember me key.
     */
    @Bean
    public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
        return new TokenBasedRememberMeServices(rememberMeKey(), customUserDetailsService);
    }
}
