package org.samarBg.service.impl;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.model.entities.UserRoleEntity;
import org.samarBg.model.entities.enums.UserRoleEnum;
import org.samarBg.repository.UserRepository;
import org.samarBg.security.CurrentUser;
import org.samarBg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public boolean authenticate(String email, String password) {
        Optional<UserEntity> userEntityOpt = userRepository.findByEmail(email);

        if (userEntityOpt.isPresent()) {
            UserEntity user = userEntityOpt.get();
            return passwordEncoder.matches(password, user.getPassword());
        }

        return false;
    }

    @Override
    public void loginUser(String email) {
        Optional<UserEntity> userEntityOpt = userRepository.findByEmail(email);

        if (userEntityOpt.isPresent()) {
            UserEntity user = userEntityOpt.get();
            List<UserRoleEnum> userRoles = new ArrayList<>(user.getUserRoles());

            currentUser
                    .setGuest(false)
                    .setName(user.getUsername())
                    .setUserRoles(userRoles)
                    .setUserImage(user.getImageUrl());
        }
    }

    @Override
    public void logoutCurrentUser() {
        currentUser.setGuest(true);
    }

    @Override
    public boolean confirmEmail(Long userId, String code) {
        return false;
    }
}
