package org.samarBg.view;

import jakarta.validation.constraints.*;

public class ForgottenPasswordViewModel {
    @NotNull
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public ForgottenPasswordViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

}
