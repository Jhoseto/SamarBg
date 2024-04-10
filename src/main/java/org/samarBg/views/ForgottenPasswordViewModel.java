package org.samarBg.views;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
/**
 * View model representing the data needed to process a forgotten password request.
 * <p>
 * This view model contains the following fields:
 * <ul>
 *     <li><b>email:</b> The email address associated with the user's account.</li>
 * </ul>
 */
public class ForgottenPasswordViewModel {

    /**
     * The email address associated with the user's account.
     */
    @NotNull(message = "Email адресът не може да бъде празен")
    @Email(message = "Невалиден email адрес")
    private String email;

    public String getEmail() {
        return email;
    }

    public ForgottenPasswordViewModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
