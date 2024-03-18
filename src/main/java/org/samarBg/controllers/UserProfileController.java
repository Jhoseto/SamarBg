package org.samarBg.controllers;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/user-settings")
public class UserProfileController {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserProfileController(UserRepository userRepository,
                                 CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @GetMapping("/userdetail")
    public String showUserDetail(){
        return "userdetail";
    }

    @GetMapping("/user-settings")
    public String showUserSettings(){
        return "user-settings";

    }
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              Model model) {

        // Проверка дали текущият потребител е логнат
        if (currentUser == null || !currentUser.isLoggedIn()) {
            // Ако потребителят не е логнат, пренасочваме го към страницата за вход

            model.addAttribute("error", "Моля, влезте в системата, за да качите снимка.");
            return "redirect:/login";
        }

        String username = currentUser.getName();

        // Обработка на качената снимка
        if (!file.isEmpty()) {
            try {
                // Преименуване на снимката
                String originalFileName = file.getOriginalFilename();
                String fileName = username + "_uploaded_image.jpg";

                // Променете пътя, където ще се запише снимката, ако е необходимо
                String uploadDir = "/images/usersImg/";
                Path path = Paths.get(uploadDir + fileName);

                Optional<UserEntity> userOptional = userRepository.findByEmail(username);
                if (userOptional.isPresent()) {
                    UserEntity user = userOptional.get();
                    user.setImageUrl(fileName);
                    Files.write(path, file.getBytes());
                    currentUser.setUserImage(fileName);
                    userRepository.save(user);
                    model.addAttribute("message", "Файлът е качен успешно!");
                }

            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Грешка при качване на файла.");
            }
        } else {
            model.addAttribute("error", "Файлът е празен.");
        }
        return "user-settings";
    }
}
