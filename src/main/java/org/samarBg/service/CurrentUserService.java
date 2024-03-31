package org.samarBg.service;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**SamarBG *
     * Method getting information for current logged User for all session before he logout
     */
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Optional<UserEntity> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                return userOptional.get();
            } else {
                // Потребителят не е намерен по потребителско име, и се търси по имейл
                Optional<UserEntity> userByEmailOptional = userRepository.findByEmail(username);
                return userByEmailOptional.orElse(null);
            }
        }
        return null;
    }




}
