package org.samarBg.service.serviceImpl;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.model.entities.enums.City;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Optional;

/**
 * UserSettingsServiceImpl is the implementation of UserSettingsService interface.
 * It provides methods to save user settings and upload profile images.
 */
@Service
public class UserSettingsServiceImpl implements UserSettingsService {

    private final UserRepository userRepository;

    @Autowired
    public UserSettingsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Save user settings from the first settings form.
     *
     * @param currentUser The current user entity to update settings for.
     * @param realName    The new real name to set.
     * @param phone       The new phone number to set.
     * @param city        The new city to set.
     */
    @Override
    public void saveSettingsFormOne(UserEntity currentUser, String realName, String phone, City city) {
        if (currentUser != null) {
            currentUser.setRealName(realName);
            currentUser.setPhone(phone);
            currentUser.setCity(city);
            currentUser.setModified(Instant.now());

            // Save the changes to the database
            userRepository.save(currentUser);
        } else {
            throw new IllegalArgumentException("Invalid current user!");
        }
    }

    /**
     * Upload and save a profile image for a user.
     *
     * @param email The email of the user to upload the image for.
     * @param file  The image file to upload.
     * @throws IOException If an I/O error occurs while processing the file.
     */
    @Override
    public void uploadProfileImage(String email, MultipartFile file) throws IOException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            String fileName = user.getUsername() + "_profile_image_" + ".jpg";
            String uploadDir = "F:\\MyProjects\\SamarBG\\SamarBg\\src\\main\\resources\\static\\images\\usersImg\\";
            Path path = Paths.get(uploadDir + fileName);
            user.setImageUrl("/images/usersImg/" + fileName);
            Files.write(path, file.getBytes());

            // Update modification date
            user.setModified(Instant.now());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with this email not found.");
        }
    }
}
