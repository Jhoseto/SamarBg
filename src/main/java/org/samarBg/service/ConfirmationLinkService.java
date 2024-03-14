package org.samarBg.service;

import org.samarBg.model.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationLinkService {
    public String generateConfirmationLink(UserEntity user) {


        // Генериране на URL адрес с уникален идентификатор на потребителя и код за потвърждение
        String confirmationLink = "http://localhost:8080/confirm?userId="+ "&code=";

        return confirmationLink;
    }
}
