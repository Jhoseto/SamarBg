package org.samarBg.service;

import org.samarBg.models.UserEntity;
import org.samarBg.models.enums.City;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service interface for user settings operations.
 */
@Service
public interface UserSettingsService {

    /**
     * Saves user settings from form one.
     *
     * @param currentUser The current user entity
     * @param realName    The real name to update
     * @param phone       The phone number to update
     * @param city        The city to update
     * @throws IllegalArgumentException if the current user is null
     */
    void saveSettingsFormOne(UserEntity currentUser, String realName, String phone, City city);

    /**
     * Uploads a profile image for the user.
     *
     * @param email The email of the user
     * @param file  The profile image file to upload
     * @throws IOException               if an I/O error occurs
     * @throws IllegalArgumentException if the user with the specified email is not found
     */
    void uploadProfileImage(String email, MultipartFile file) throws IOException;
}
