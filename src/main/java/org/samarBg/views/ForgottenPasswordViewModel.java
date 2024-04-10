package org.samarBg.views;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
