package org.samarBg.service.serviceImpl;

import org.samarBg.models.UserEntity;
import org.samarBg.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link UserDetailsService} responsible for loading user details by email.
 */
@Service
public class CustomUserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a new CustomUserDetailsImpl instance with the UserRepository dependency.
     *
     * @param userRepository the repository for accessing user data
     */
    public CustomUserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user details by email.
     *
     * @param email the email address of the user to load
     * @return UserDetails representing the user with the given email
     * @throws UsernameNotFoundException if the user with the specified email is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with this email address not found: " + email));
        return mapToUserDetails(userEntity);
    }

    /**
     * Maps a UserEntity to a UserDetails object containing user details and authorities.
     *
     * @param userEntity the user entity to map to UserDetails
     * @return UserDetails representing the mapped user details
     */
    private static UserDetails mapToUserDetails(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getUserRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
