package org.samarBg.service.implementation;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.Mappers.MapperForUsers;
import org.samarBg.service.UserService;
import org.samarBg.view.UserProfileViewModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final MapperForUsers mapperForUsers;


    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserDetailsService userDetailsService,
                           MapperForUsers mapperForUsers) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.mapperForUsers = mapperForUsers;
    }


    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
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

    public List<UserProfileViewModel> getAllUsers() {
        List<UserProfileViewModel> allUsers = new ArrayList<>();
        List<UserEntity> users = userRepository.findAll();

        // Мапиране на потребителите към UserProfileViewModel с MapperForUsers
        for (UserEntity user : users) {
            UserProfileViewModel userProfileViewModel = mapperForUsers.mapUserToProfileViewModel(user);
            allUsers.add(userProfileViewModel);
        }
        return allUsers;
    }
}