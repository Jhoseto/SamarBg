package org.samarBg.service.Mappers;

import org.samarBg.model.entities.AccessoryOfferEntity;
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
public final class MapperForAccessory {
    private final UserRepository userRepository;
    private final OfferImageRepository offerImageRepository;

    public MapperForAccessory(UserRepository userRepository,
                           OfferImageRepository offerImageRepository) {
        this.userRepository = userRepository;
        this.offerImageRepository = offerImageRepository;
    }

    public OfferViewModel mapAccessoryToOffer(AccessoryOfferEntity accessory) {

        OfferViewModel offer = new OfferViewModel();
        String userName = accessory.getAuthorName();

        if (userName != null){
            Optional<UserEntity> optionalUser = userRepository.findByUsername(userName);

            if (optionalUser.isPresent()){
                UserEntity user = optionalUser.get();

                // Извличане на информация от обекта за аксесоар
                offer.setAuthorRegistrationData(user.getCreated());
                offer.setAuthorOffersNumbers(user.getUserOffersCount());
                offer.setAuthorProfileImage(user.getImageUrl());
                offer.setAuthorLastOnlineDate(user.getLastOnline());
                offer.setId(accessory.getId());
                offer.setOfferCategory(accessory.getCategory());
                offer.setAccessoriesCategory(accessory.getAccessoriesCategory());
                offer.setPrice(accessory.getPrice());
                offer.setOfferName(accessory.getOfferName());
                offer.setPhone(accessory.getPhone());
                offer.setCity(accessory.getCity());
                offer.setDescription(accessory.getDescription());
                offer.setAuthorName(accessory.getAuthorName());
                offer.setCreateDate(accessory.getCreated());
                offer.setModifiedDate(accessory.getModified());
                offer.setOfferViewCount(accessory.getOfferViewCounter());
                offer.setVideoLink(accessory.getVideoLink());

                // Извличане на URL адресите на снимките от базата данни
                List<String> imageUrls = new ArrayList<>();

                List<OfferImageEntity> images = offerImageRepository.findByAccessoryOfferId(accessory.getId());

                for (OfferImageEntity image : images) {
                    imageUrls.add(image.getImagePath()); // Получаване на URL адреса на снимката
                }
                offer.setImagesUrls(imageUrls);
                offer.setBasicImageUrl(imageUrls.isEmpty() ? null : imageUrls.get(0));
            } else {
                System.out.println("User not found for username: " + userName);
            }
        } else {
            System.out.println("No username found for offer with ID: " + accessory.getId());
        }
        return offer;
    }
}
