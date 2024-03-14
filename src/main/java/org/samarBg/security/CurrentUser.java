package org.samarBg.security;

import org.samarBg.model.entities.enums.UserRoleEnum;
import org.samarBg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
@Component
@SessionScope
public class CurrentUser {
    private final String GUEST = "Гост";

    private String name = GUEST;
    private String userImage;
    private boolean isGuest = true;
    private List<UserRoleEnum> userRoles = new ArrayList<>();
    @Autowired
    UserRepository userRepository;

    public String getGUEST() {
        return GUEST;
    }
    public boolean isLoggedIn() {
        return !isGuest();
    }
    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        if (userImage == null || userImage.isEmpty()) {
            this.userImage = "images/userNameIcon1.png";
        } else {
            this.userImage = userImage;
        }
    }

    public boolean isGuest() {
        return isGuest;
    }

    public CurrentUser setGuest(boolean guest) {
        if (guest) {
            this.name = GUEST;
            this.userRoles.clear();
            // Задаване на пътя до стандартното изображение за госта
            this.userImage = "images/userNameIcon1.png";
        }
        isGuest = guest;
        return this;
    }

    public List<UserRoleEnum> getUserRoles() {
        return userRoles;
    }

    public CurrentUser setUserRoles(List<UserRoleEnum> newUserRoles) {
        userRoles.clear();
        userRoles.addAll(newUserRoles);
        return this;
    }
}
