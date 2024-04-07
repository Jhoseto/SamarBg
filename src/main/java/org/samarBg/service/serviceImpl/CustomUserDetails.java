package org.samarBg.service.serviceImpl;

import org.samarBg.model.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private UserEntity userEntity;

    public CustomUserDetails(String username, String password, UserEntity userEntity) {
        this.username = username;
        this.password = password;
        this.userEntity = userEntity;
        this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public CustomUserDetails setUsername(String username) {
        this.username = username;
        return this;
    }

    public CustomUserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomUserDetails setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CustomUserDetails setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
