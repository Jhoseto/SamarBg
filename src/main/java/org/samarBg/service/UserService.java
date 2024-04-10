package org.samarBg.service;

import org.springframework.stereotype.Service;


import org.samarBg.model.entities.UserEntity;
import org.samarBg.view.UserProfileViewModel;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing users.
 */

public interface UserService {

    /**
     * Finds a user by email.
     *
     * @param email the email of the user
     * @return an Optional containing the user, if exists
     */
    Optional<UserEntity> findUserByEmail(String email);

    /**
     * Finds a user by username.
     *
     * @param username the username of the user
     * @return an Optional containing the user, if exists
     */
    Optional<UserEntity> findUserByUsername(String username);

    /**
     * Retrieves information for all users in the system.
     *
     * @return a list of user profile models (UserProfileViewModel)
     */
    List<UserProfileViewModel> getAllUsers();

    /**
     * Retrieves the current authenticated user.
     *
     * @return the current user entity
     */
    UserEntity getCurrentUser();
}
