package org.samarBg.view;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ChangePasswordViewModel {
    private String oldPassword;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Новата паролата трябва да съдържа поне 6 символа на латиница, една голяма буква и поне две цифри")
    private String newPassword;
    private String confirmNewPassword;

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
