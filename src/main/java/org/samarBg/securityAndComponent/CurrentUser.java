package org.samarBg.securityAndComponent;

import org.samarBg.model.entities.enums.UserRoleEnum;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class CurrentUser {
    private static final String GUEST = "Гост";
    private static final String GUEST_IMAGE_PATH = "images/userNameIcon1.png";

    private String name = GUEST;
    private String userImage = GUEST_IMAGE_PATH;
    private boolean loggedIn = false;
    private List<UserRoleEnum> userRoles = new ArrayList<>();

    public boolean isLoggedIn() {
        return loggedIn;
    }

//    public void authenticate(String userName, List<UserRoleEnum> roles, String userImage) {
//        this.name = userName;
//        this.userRoles.clear();
//        this.userRoles.addAll(roles);
//        this.userImage = (userImage != null && !userImage.isEmpty()) ? userImage : GUEST_IMAGE_PATH;
//        this.loggedIn = true;
//    }
//
//    public void logout() {
//        this.name = GUEST;
//        this.userRoles.clear();
//        this.userImage = GUEST_IMAGE_PATH;
//        this.loggedIn = false;
//    }

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

    public CurrentUser setUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
        return this;
    }

    public List<UserRoleEnum> getUserRoles() {
        return userRoles;
    }

    public CurrentUser setUserRoles(List<UserRoleEnum> userRoles) {
        this.userRoles = userRoles;
        return this;
    }
}
