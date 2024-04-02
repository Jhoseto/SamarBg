package org.samarBg.service;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.model.entities.enums.City;
import org.samarBg.repository.UserRepository;
import org.samarBg.securityAndComponent.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Optional;

@Service
public class UserSettingsService {
    private final UserRepository userRepository;

    @Autowired
    public UserSettingsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveSettingsFormOne(UserEntity currentUser, String realName, String phone, City city) {
        if (currentUser != null) {
            currentUser.setRealName(realName);
            currentUser.setPhone(phone);
            currentUser.setCity(city);
            currentUser.setModified(Instant.now());

            // Запазване на промените в базата данни
            userRepository.save(currentUser);
        } else {
            throw new IllegalArgumentException("Текущият потребител не е валиден!");
        }
    }

    public void uploadProfileImage(String email, MultipartFile file) throws IOException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            String fileName = user.getUsername() + "_profile_image_" + ".jpg";
            String uploadDir = "F:\\MyProjects\\SamarBG\\SamarBg\\src\\main\\resources\\static\\images\\usersImg\\";
            Path path = Paths.get(uploadDir + fileName);
            user.setImageUrl("/images/usersImg/"+fileName);
            Files.write(path, file.getBytes());

            // Актуализиране на датата и часа на модификация
            user.setModified(Instant.now());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Потребителят с такъв имейл не е намерен.");
        }
    }
}


