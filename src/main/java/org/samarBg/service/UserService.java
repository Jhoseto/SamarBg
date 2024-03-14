package org.samarBg.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //return TRUE if user is authenticated successfully
    boolean authenticate (String email, String password);

    void loginUser (String email);
    void logoutCurrentUser ();
    boolean confirmEmail(Long userId, String code);
}
