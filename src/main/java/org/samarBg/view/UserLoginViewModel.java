package org.samarBg.view;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginViewModel {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 6)
    private String password;

    private boolean isActive;


    public String getEmail() {
        return email;
    }

    public UserLoginViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }


    public UserLoginViewModel setPassword(String password) {
        this.password = password;
        return this;

    }
    public boolean isActive() {
        return isActive;
    }

    public UserLoginViewModel setActive(boolean active) {
        isActive = active;
        return this;
    }
}
