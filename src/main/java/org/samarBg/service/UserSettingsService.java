package org.samarBg.service;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.model.entities.enums.CityEnum;
import org.samarBg.repository.UserRepository;
import org.samarBg.securityAndComponent.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class UserSettingsService {
    private final UserRepository userRepository;
    private final CacheService cacheService;
    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public UserSettingsService(UserRepository userRepository,
                               CacheService cacheService, UserService userService,
                               HttpSession session) {
        this.userRepository = userRepository;
        this.cacheService = cacheService;
        this.userService = userService;
        this.session = session;
    }

    public void saveSettingsFormOne(UserEntity currentUser, String realName, String phone, CityEnum city) {

        if (currentUser != null) {
            // Присвояване на стойностите, независимо дали са null
            currentUser.setRealName(realName);
            currentUser.setPhone(phone);
            currentUser.setCity(city);

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


            // Генериране на уникално име за качения файл, включващо времеви маркер
            String timestamp = String.valueOf(System.currentTimeMillis());
            String fileName = user.getUsername() + "_profile_image_" + timestamp + ".jpg";

            String uploadDir = "F:\\MyProjects\\SamarBG\\SamarBg\\src\\main\\resources\\static\\images\\usersImg\\";
            Path path = Paths.get(uploadDir + fileName);
            user.setImageUrl("/images/usersImg/"+fileName);
            Files.write(path, file.getBytes());
            userRepository.save(user);

        } else {
            throw new IllegalArgumentException("Потребителят с такъв имейл не е намерен.");
        }
    }
}


