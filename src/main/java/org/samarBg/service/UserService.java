package org.samarBg.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface UserService {
    //return TRUE if user is authenticated successfully
    boolean authenticate (String email, String password);

    void loginUser (String email);

    void changeProfileImage(String username, String fileName, HttpSession session);

    void logoutCurrentUser ();
    boolean confirmEmail(Long userId, String code);

    void changeProfileImage(String username, String fileName);
}
