package org.samarBg.service.Mappers;

import org.samarBg.model.entities.HorseOfferEntity;
import org.samarBg.model.entities.OfferImageEntity;
import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public final class MapperForHorses {
    private final UserRepository userRepository;
    private final OfferImageRepository offerImageRepository;

    public MapperForHorses(UserRepository userRepository,
                           OfferImageRepository offerImageRepository) {
        this.userRepository = userRepository;
        this.offerImageRepository = offerImageRepository;
    }

    public OfferViewModel mapHorseToOffer(HorseOfferEntity horse) {
        // Преобразуване на информацията за коня в обява
        OfferViewModel offer = new OfferViewModel();

        // Намиране на потребителя по ID на обявата
        String userName = horse.getAuthorName();

        if (userName != null) {
            // Намиране на потребителя по потребителското име
            Optional<UserEntity> optionalUser = userRepository.findByUsername(userName);

            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();

                // Извличане на необходимите данни от потребителя и присвояване на полетата на обекта OfferViewModel
                offer.setAuthorRegistrationData(user.getCreated());
                offer.setAuthorOffersNumbers(user.getUserOffersCount());
                offer.setAuthorProfileImage(user.getImageUrl());
                offer.setAuthorLastOnlineDate(user.getLastOnline());
                offer.setId(horse.getId());
                offer.setOfferCategory(horse.getCategory());
                offer.setHorseCategory(horse.getHorseCategory());
                offer.setSex(horse.getSex());
                offer.setPrice(horse.getPrice());
                offer.setOfferName(horse.getOfferName());
                offer.setCity(horse.getCity());
                offer.setDescription(horse.getDescription());
                offer.setAuthorName(horse.getAuthorName());
                offer.setCreateDate(horse.getCreated());
                offer.setModifiedDate(horse.getModified());
                offer.setOfferViewCount(horse.getOfferViewCounter());
                offer.setVideoLink(horse.getVideoLink());

                // Извличане на URL адресите на снимките от базата данни
                List<String> imageUrls = new ArrayList<>();

                List<OfferImageEntity> images = offerImageRepository.findByHorseOfferId(horse.getId());

                for (OfferImageEntity image : images) {
                    imageUrls.add(image.getImagePath()); // Получаване на URL адреса на снимката
                }

                offer.setImagesUrls(imageUrls);
                offer.setBasicImageUrl(imageUrls.isEmpty() ? null : imageUrls.get(0));
            } else {
                System.out.println("User not found for username: " + userName);
            }
        } else {
            System.out.println("No username found for offer with ID: " + horse.getId());
        }

        return offer;
    }
}
