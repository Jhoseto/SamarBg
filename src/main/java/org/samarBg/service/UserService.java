package org.samarBg.service;


import org.samarBg.models.UserEntity;
import org.samarBg.views.UserProfileViewModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing users.
 */

public interface UserService {

    /**
     * Authenticates a user using the provided email and password.
     *
     * @param email    The email of the user to authenticate
     * @param password The password of the user to authenticate
     * @return An object representing the successful authentication of the user
     * @throws AuthenticationException If there is an error during authentication, typically due to invalid username or password
     */
    Authentication authenticateUser(String email, String password);
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

    /**
     * Promotes a user to admin role.
     *
     * @param username the username of the user to promote
     * @throws RuntimeException if the user or the admin role is not found
     */
    void promoteUserToAdmin(String username);

    /**
     * Promotes a admin to user role.
     *
     * @param username the username of the user to promote
     * @throws RuntimeException if the user or the user role is not found
     */
    void promoteAdminToUser(String username);

    /**
     * Changes the role of the user identified by the given userId.
     * If the user's current role is USER, it promotes the user to ADMIN.
     * If the user's current role is ADMIN, it demotes the user to USER.
     * Saves the updated user entity after the role change.
     *
     * @param userId the unique identifier of the user whose role will be changed
     */
    void changeUserRole(Long userId);

    /**
     * Deletes a user by their user ID.
     * If the user with the specified ID exists, it is deleted from the repository.
     * If the user does not exist, no action is taken.
     *
     * @param userId The ID of the user to be deleted.
     */
    void deleteUser(Long userId);

    /**
     * Retrieves the user profile information for the user with the specified username.
     *
     * @param userName The username of the user whose profile information is to be retrieved.
     * @return UserProfileViewModel containing the profile information of the user.
     */
    UserProfileViewModel getUserByUsername(String userName);
}
