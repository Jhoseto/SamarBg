package org.samarBg.service;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service

public class UserService  {
    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;
    //private CurrentUser currentUser;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }




//
//    public void changeProfileImage(String username, String fileName) {
//        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
//
//        userOptional.ifPresent(user -> {
//            user.setImageUrl(fileName);
//            userRepository.save(user);
//
//        });
//    }


    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public Authentication authenticateUser(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;
        }
        return null;
    }

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Optional<UserEntity> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                return userOptional.get();
            } else {
                // Потребителят не е намерен по потребителско име, опитвайки се да го намерите по имейл
                Optional<UserEntity> userByEmailOptional = userRepository.findByEmail(username);
                return userByEmailOptional.orElse(null);
            }
        }
        return null;
    }
}