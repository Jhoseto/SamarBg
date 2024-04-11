package org.samarBg.service.serviceImpl;

import org.samarBg.models.UserEntity;
import org.samarBg.models.UserRoleEntity;
import org.samarBg.models.enums.UserRole;
import org.samarBg.repository.UserRepository;
import org.samarBg.repository.UserRoleRepository;
import org.samarBg.service.Mappers.MapperForUsers;
import org.samarBg.service.UserService;
import org.samarBg.views.UserProfileViewModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the UserService interface.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final MapperForUsers mapperForUsers;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserDetailsService userDetailsService,
                           MapperForUsers mapperForUsers,
                           UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.mapperForUsers = mapperForUsers;
        this.userRoleRepository = userRoleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Authenticates a user using email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return an Authentication object if authentication is successful, otherwise null
     */
    public Authentication authenticateUser(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserProfileViewModel> getAllUsers() {
        List<UserProfileViewModel> allUsers = new ArrayList<>();
        List<UserEntity> users = userRepository.findAll();

        // Mapping users to UserProfileViewModel using MapperForUsers
        for (UserEntity user : users) {
            UserProfileViewModel userProfileViewModel = mapperForUsers.mapUserToProfileViewModel(user);
            allUsers.add(userProfileViewModel);
        }
        return allUsers;
    }

    /**
     * Retrieves information about the currently logged-in user.
     *
     * @return the UserEntity object representing the currently logged-in user, or null if no user is logged in
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


    @Transactional
    public void promoteUserToAdmin(String username) {
        // Намери потребителя по потребителско име
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Намери ролята "ADMIN" в базата данни
            Optional<UserRoleEntity> adminRoleOptional = userRoleRepository.findByRole(UserRole.ADMIN);

            if (adminRoleOptional.isPresent()) {
                // Промени ролята на потребителя към "ADMIN"
                user.getUserRoles().clear(); // Изчистваме старите роли
                user.getUserRoles().add(adminRoleOptional.get().getRole()); // Добавяме новата роля

                // Запиши промените в базата данни
                userRepository.save(user);
            } else {
                throw new RuntimeException("Role 'ADMIN' not found in the database.");
            }
        } else {
            throw new RuntimeException("User is already an ADMIN.");
        }
    }


    @Override
    public void promoteAdminToUser(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // find role ADMIN in dataBase
            Optional<UserRoleEntity> userRoleOptional = userRoleRepository.findById(user.getId());

            if (userRoleOptional.isPresent()) {
                UserRole userRole = userRoleOptional.get().getRole();

                // Change user role from USER to ADMIN
                user.getUserRoles().clear(); // Delete old roles
                user.getUserRoles().add(userRole); // Add new role

                // Save
                userRepository.save(user);
            } else {
                throw new RuntimeException("Role 'USER' not found in the database.");
            }
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }
}
