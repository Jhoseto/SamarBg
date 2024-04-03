package org.samarBg.service.implementation;

import org.samarBg.model.entities.UserEntity;
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

@Service
public class CustomUserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Потребител с този имейл адрес не е намерен: " + email));
        return mapToUserDetails (userEntity); // Връщаме реален обект UserDetails, който трябва да представя потребителя.
    }

    private static UserDetails mapToUserDetails (UserEntity userEntity){
        List<GrantedAuthority> authorities =
                userEntity.getUserRoles().stream()
                        .map( role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList());

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);

    }
}
