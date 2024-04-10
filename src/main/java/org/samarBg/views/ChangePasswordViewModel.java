package org.samarBg.views;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
/**
 * View model representing the data needed to change a user's password.
 * <p>
 * This view model contains the following fields:
 * <ul>
 *     <li><b>oldPassword:</b> The user's current password.</li>
 *     <li><b>newPassword:</b> The new password to be set. Must contain at least 6 Latin characters,
 *         one uppercase letter, and at least two digits.</li>
 *     <li><b>confirmNewPassword:</b> The confirmation of the new password.</li>
 * </ul>
 */
public class ChangePasswordViewModel {

    private String oldPassword;

    /**
     * The new password to be set.
     * Must contain at least 6 characters, including one uppercase letter and at least two digits.
     */
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{6,}$",
            message = "Новата паролата трябва да съдържа поне 6 символа на латиница, една голяма буква и поне две цифри")
    private String newPassword;

    private String confirmNewPassword;

    /**
     * Checks if the confirmNewPassword matches the newPassword.
     * @return true if the passwords match, false otherwise.
     */
    @AssertTrue(message = "Грешка в повторението на паролата")
    public boolean isPasswordsMatch() {
        if (!newPassword.equals(confirmNewPassword)) {
            confirmNewPassword = null;
            return false;
        }
        return true;
    }




    public String getOldPassword() {
        return oldPassword;
    }

    public ChangePasswordViewModel setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public ChangePasswordViewModel setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public ChangePasswordViewModel setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
        return this;
    }
}
