package org.samarBg.securityAndComponent;

import org.samarBg.models.UserEntity;
import org.samarBg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Глобален контролер, който осигурява достъп до информация за текущия потребител
 * за всички контролери в приложението.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    private final UserService userService;

    /**
     * Конструктор за GlobalControllerAdvice класа.
     *
     * @param currentUserService Сервиз за текущия потребител
     */
    @Autowired
    public GlobalControllerAdvice(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод, който връща информация за текущия потребител като атрибут на модела.
     *
     * @return Информация за текущия потребител
     */
    @ModelAttribute("loggedInUser")
    public UserEntity getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return userService.getCurrentUser();
        }
        return null;
    }
}
