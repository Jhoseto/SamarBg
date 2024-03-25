package org.samarBg.controllers;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.UserService;
import org.samarBg.service.UserSettingsService;
import org.samarBg.view.ProfileImageViewModel;
import org.samarBg.view.SettingsFormOneViewModel;
import org.samarBg.view.UserLoginViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class UserProfileController {


    private final UserSettingsService userSettingsService;
    private final UserRepository userRepository;


    @Autowired
    public UserProfileController(UserSettingsService userSettingsService,
                                 UserRepository userRepository) {
        this.userSettingsService = userSettingsService;
        this.userRepository = userRepository;

    }



    @GetMapping("/user-detail")
    public String showUserDetail() {
        return "user-detail";
    }

    @GetMapping("/user-settings")
    public String showUserSettings(Model model) {
        return "user-settings";
    }


    @ModelAttribute("saveFormOne")
    public SettingsFormOneViewModel userModel() {
        return new SettingsFormOneViewModel();
    }


    @PostMapping("/user-settings/saveFormOne")
    public String saveFormOne(@ModelAttribute("saveFormOne")SettingsFormOneViewModel settingsFormOneViewModel,
                              RedirectAttributes redirectAttributes) {

        // Получаваме Имейл-а на текущия потребител от CurrentUserService
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Намираме потребителя в базата данни по email
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserEntity currentUser = userOptional.get();

            // Подаваме нужната информация за текущия потребител на метода за запазване
            userSettingsService.saveSettingsFormOne(
                    currentUser,
                    settingsFormOneViewModel.getRealName(),
                    settingsFormOneViewModel.getPhone(),
                    settingsFormOneViewModel.getCity()
            );
            redirectAttributes.addFlashAttribute("massageFormOne", "Новите настройки са запазени !");
            return "redirect:/user-settings";
        } else {
            redirectAttributes.addFlashAttribute("errorFormOne", "Грешка при запазване на настройките !");
        }
        redirectAttributes.addFlashAttribute("errorFormOne",
                "Грешка в потребителската сесия ! Моля влезте в вашия профил.");
        return "redirect:/login";
    }


//TODO prodaljavame s snimkata



    @ModelAttribute("profileImageViewModel")
    public ProfileImageViewModel profileImageViewModel() {
        return new ProfileImageViewModel();
    }


    @PostMapping("/user-settings/uploadProfileImage")
    public String uploadImage(@Valid @ModelAttribute("profileImageViewModel") ProfileImageViewModel profileImageViewModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // Обработка на грешките при валидация
            return "redirect:/user-settings";
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        MultipartFile file = profileImageViewModel.getProfileImgFile();

        try {
            userSettingsService.uploadProfileImage(email, file);

            redirectAttributes.addFlashAttribute("messageImg", "Файлът е качен успешно!");
            return "redirect:/user-settings";
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorImg", "Грешка при качване на файла.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorImg", e.getMessage());
        }

        return "redirect:/user-settings";
    }



    @ModelAttribute("changePasswordViewModel")
    public ProfileImageViewModel profileImageViewModel() {
        return new ProfileImageViewModel();
    }
    @PostMapping("/user-settings/changePassword")
    public String changePassword(@Valid @ModelAttribute("changePasswordViewModel") ProfileImageViewModel profileImageViewModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){



    }
}

