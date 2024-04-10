package org.samarBg.controllers;

import org.samarBg.models.UserEntity;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.UserSettingsService;
import org.samarBg.views.ChangePasswordViewModel;
import org.samarBg.views.ProfileImageViewModel;
import org.samarBg.views.SettingsFormOneViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UserSettingsController {

    private final UserSettingsService userSettingsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserSettingsController(UserSettingsService userSettingsService,
                                  UserRepository userRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userSettingsService = userSettingsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Show user settings page
    @GetMapping("/user-settings")
    public String showUserSettings() {
        return "user-settings";
    }

    // Model attribute for settings form one
    @ModelAttribute("saveFormOne")
    public SettingsFormOneViewModel saveFormOne() {
        return new SettingsFormOneViewModel();
    }

    // Handle saving settings form one
    @PostMapping("/user-settings/saveFormOne")
    public String saveFormOne(@ModelAttribute("saveFormOne") SettingsFormOneViewModel settingsFormOneViewModel,
                              RedirectAttributes redirectAttributes) {

        // Get the email of the current authenticated user
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the user in the database by email
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserEntity currentUser = userOptional.get();

            // Set realName if empty
            if (settingsFormOneViewModel.getRealName().isEmpty()) {
                settingsFormOneViewModel.setRealName(currentUser.getRealName());
            }

            // Set phone if empty
            if (settingsFormOneViewModel.getPhone().isEmpty()) {
                settingsFormOneViewModel.setPhone(currentUser.getPhone());
            }

            // Save settings form one
            userSettingsService.saveSettingsFormOne(
                    currentUser,
                    settingsFormOneViewModel.getRealName(),
                    settingsFormOneViewModel.getPhone(),
                    settingsFormOneViewModel.getCity()
            );

            redirectAttributes.addFlashAttribute("messageFormOne", "New settings saved successfully!");
            return "redirect:/user-settings";
        } else {
            redirectAttributes.addFlashAttribute("errorFormOne", "Error saving settings!");
        }

        redirectAttributes.addFlashAttribute("errorFormOne", "Error in user session! Please log in to your profile.");
        return "redirect:/login";
    }

    // Model attribute for profile image upload
    @ModelAttribute("profileImageViewModel")
    public ProfileImageViewModel profileImageViewModel() {
        return new ProfileImageViewModel();
    }

    // Handle profile image upload
    @PostMapping("/user-settings/uploadProfileImage")
    public String uploadImage(@Valid @ModelAttribute("profileImageViewModel") ProfileImageViewModel profileImageViewModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "redirect:/user-settings";
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        MultipartFile file = profileImageViewModel.getProfileImgFile();

        // Validate file format
        if (file == null || !file.getContentType().startsWith("image")) {
            redirectAttributes.addFlashAttribute("errorImg", "File must be an image (jpg, png, bmp, gif).");
            return "redirect:/user-settings";
        }

        // Validate file size
        if (file.getSize() > 5 * 1024 * 1024) { // 5 MB
            redirectAttributes.addFlashAttribute("errorImg", "File size must be less than 5 MB.");
            return "redirect:/user-settings";
        }

        // Validate file extension
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && !originalFilename.matches("^[^.]*\\.[^.]*$")) {
            redirectAttributes.addFlashAttribute("errorImg", "Invalid file format.");
            return "redirect:/user-settings";
        }

        try {
            userSettingsService.uploadProfileImage(email, file);
            redirectAttributes.addFlashAttribute("messageImg", "File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorImg", "Error uploading file.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorImg", e.getMessage());
        }

        return "redirect:/user-settings";
    }

    // Model attribute for change password
    @ModelAttribute("changePasswordViewModel")
    public ChangePasswordViewModel changePasswordViewModel() {
        return new ChangePasswordViewModel();
    }

    // Handle changing user password
    @PostMapping("/user-settings/changePassword")
    public String changePassword(@Valid @ModelAttribute("changePasswordViewModel") ChangePasswordViewModel changePasswordViewModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Validate old password
            if (passwordEncoder.matches(changePasswordViewModel.getOldPassword(), user.getPassword())) {

                // Validate new password
                if (bindingResult.hasFieldErrors("newPassword")) {
                    String errorMessage = Objects.requireNonNull(bindingResult.getFieldError("newPassword")).getDefaultMessage();
                    redirectAttributes.addFlashAttribute("errorChangePassword", errorMessage);
                    return "redirect:/user-settings";
                }

                // Check if passwords match
                if (!changePasswordViewModel.isPasswordsMatch()) {
                    redirectAttributes.addFlashAttribute("errorChangePassword", "Passwords do not match.");
                    return "redirect:/user-settings";
                }

                // Encode new password
                String newPassword = passwordEncoder.encode(changePasswordViewModel.getNewPassword());

                // Save new password
                user.setPassword(newPassword);
                userRepository.save(user);
                user.setModified(Instant.now());

                redirectAttributes.addFlashAttribute("successMessageChangePassword", "Password changed successfully.");
            } else {
                redirectAttributes.addFlashAttribute("errorChangePassword", "Incorrect old password. Please try again.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Server error! Please log in to your profile again.");
            return "redirect:/login";
        }

        return "redirect:/user-settings";
    }
}
