package org.samarBg.service;

import org.samarBg.model.entities.UserEntity;
import org.springframework.stereotype.Service;
/**
 * Service for generating confirmation links for user accounts.
 */
@Service
public class ConfirmationLinkService {
    public String generateConfirmationLink(UserEntity user) {

        /**
         * Generates a confirmation link for a given user.
         *
         * @param user The user object for which the link is generated
         * @return Confirmation link to the user account
         */String confirmationLink = "http://213.91.128.33:2662/confirm?userId=+"+user+ "&code=";

        return confirmationLink;
    }
}
//http://213.91.128.33:2662