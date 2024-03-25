package org.samarBg.controllers;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.UserService;
import org.samarBg.service.UserSettingsService;
import org.samarBg.view.ChangePasswordViewModel;
import org.samarBg.view.ProfileImageViewModel;
import org.samarBg.view.SettingsFormOneViewModel;
import org.samarBg.view.UserLoginViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import java.text.DateFormat;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UserProfileController {


    private final UserSettingsService userSettingsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserProfileController(UserSettingsService userSettingsService,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userSettingsService = userSettingsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public SettingsFormOneViewModel saveFormOne() {
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

            // Проверка и задаване на realName
            if (settingsFormOneViewModel.getRealName().isEmpty()) {
                settingsFormOneViewModel.setRealName(currentUser.getRealName());
            }

            // Проверка и задаване на phone
            if (settingsFormOneViewModel.getPhone().isEmpty()){
                settingsFormOneViewModel.setPhone(currentUser.getPhone());
            }
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

        // Валидация на формата на файла
        if (file == null || !file.getContentType().startsWith("image")) {
            redirectAttributes.addFlashAttribute("errorImg", "Файлът трябва да бъде изображение (jpg, png, bmp, gif).");
            return "redirect:/user-settings";
        }

        // Валидация на размера на файла
        if (file.getSize() > 5 * 1024 * 1024) { // 5 MB
            redirectAttributes.addFlashAttribute("errorImg", "Файлът трябва да бъде по-малък от 5 MB.");
            return "redirect:/user-settings";
        }
        // Валидация за фалшиво разширение на файла
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && !originalFilename.matches("^[^.]*\\.[^.]*$")) {
            redirectAttributes.addFlashAttribute("errorImg", "Невалиден формат на файла");
            return "redirect:/user-settings";
        }


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
    public ChangePasswordViewModel changePasswordViewModel() {
        return new ChangePasswordViewModel();
    }
    @PostMapping("/user-settings/changePassword")
    public String changePassword(@Valid @ModelAttribute("changePasswordViewModel") ChangePasswordViewModel changePasswordViewModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Проверка за съвпадение на старата парола

            if (passwordEncoder.matches(changePasswordViewModel.getOldPassword(), user.getPassword())) {

                // Валидация на новата парола
                if (bindingResult.hasFieldErrors("newPassword")) {
                    String errorMessage = Objects.requireNonNull(bindingResult.getFieldError("newPassword")).getDefaultMessage();
                    redirectAttributes.addFlashAttribute("errorChangePassword", errorMessage);
                    return "redirect:/user-settings";
                }
                //Ако паролите не съвпадат
                if (!changePasswordViewModel.isPasswordsMatch()) {
                    redirectAttributes.addFlashAttribute("errorChangePassword","Грешка в поворението на новата парола !" );
                    return "redirect:/user-settings";
                }
                // криптиране на новата парола
                String newPassword = passwordEncoder.encode(changePasswordViewModel.getNewPassword());

                // Запазване на новата парола
                user.setPassword(newPassword);
                userRepository.save(user);
                // Актуализиране на датата и часа на модификация
                user.setModified(Instant.now());

                redirectAttributes.addFlashAttribute("successMessageChangePassword", "Паролата е успешно сменена.");
            } else {
                // Старата парола не съвпада с тази в базата данни
                redirectAttributes.addFlashAttribute("errorChangePassword", "Грешна стара парола. Моля, опитайте отново.");
            }
        } else {
            // Потребителят не е намерен в базата данни
            redirectAttributes.addFlashAttribute("error", "Грешка в сървъра ! Моля влезте в профила си отново.");
            return "redirect:/login";
        }

        return "redirect:/user-settings";
    }
}

