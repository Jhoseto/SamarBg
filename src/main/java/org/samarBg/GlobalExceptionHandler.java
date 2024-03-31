package org.samarBg;

import org.samarBg.view.AddOfferHorseViewModel;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public String handleException(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "GlobalERROR: " + ex.getMessage());
        return "redirect:/login"; // или към друга страница
    }

    @ExceptionHandler(value = {BindException.class})
    public String handleValidationException(BindException ex, RedirectAttributes redirectAttributes) {
        if (ex.getBindingResult().hasErrors()) {
            // Проверка за грешки във всички полета и събиране на съобщенията
            StringBuilder errorMessages = new StringBuilder();

            for (FieldError error : ex.getBindingResult().getFieldErrors()) {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            }

            // Добавяне на въведените данни за обявата към атрибута на пренасочване
            redirectAttributes.addFlashAttribute("addHorseOffer", ex.getTarget());

            // Добавяне на общото съобщение за грешките към атрибута на пренасочване
            redirectAttributes.addFlashAttribute("error", errorMessages.toString());
        }
        return "redirect:/addOffers";
    }
}
