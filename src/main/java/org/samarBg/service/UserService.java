package org.samarBg.service;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.view.UserProfileViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The UserService interface provides methods for user-related operations.
 * These methods allow for retrieving user information from the database.
 */
@Service
public interface UserService {

    /**
     * Retrieve a user entity by email.
     *
     * @param email the email address of the user
     * @return an Optional containing the user entity, or empty if not found
     */
    Optional<UserEntity> findUserByEmail(String email);

    /**
     * Retrieve a user entity by username.
     *
     * @param username the username of the user
     * @return an Optional containing the user entity, or empty if not found
     */
    Optional<UserEntity> findUserByUsername(String username);

    /**
     * Retrieve a list of user profiles.
     *
     * @return a list of UserProfileViewModel objects representing user profiles
     */
    List<UserProfileViewModel> getAllUsers();
}
