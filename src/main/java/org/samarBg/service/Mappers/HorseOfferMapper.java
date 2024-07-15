package org.samarBg.service.Mappers;

import org.samarBg.models.HorseOfferEntity;
import org.samarBg.models.OfferImageEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.views.OfferViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * MapperForHorses is a service class responsible for mapping HorseOfferEntity to OfferViewModel.
 * This mapper converts a HorseOfferEntity object into its corresponding OfferViewModel representation.
 */
@Service
public final class HorseOfferMapper {

    private final UserRepository userRepository;
    private final OfferImageRepository offerImageRepository;

    /**
     * Constructs a new MapperForHorses instance.
     *
     * @param userRepository     UserRepository for accessing user data.
     * @param offerImageRepository OfferImageRepository for accessing offer image data.
     */
    public HorseOfferMapper(UserRepository userRepository, OfferImageRepository offerImageRepository) {
        this.userRepository = userRepository;
        this.offerImageRepository = offerImageRepository;
    }

    /**
     * Maps a HorseOfferEntity to an OfferViewModel.
     *
     * @param horse The HorseOfferEntity to map.
     * @return The mapped OfferViewModel.
     */
    public OfferViewModel mapHorseToOffer(HorseOfferEntity horse) {
        OfferViewModel offer = new OfferViewModel();
        String userName = horse.getAuthorName();

        if (userName != null) {
            Optional<UserEntity> optionalUser = userRepository.findByUsername(userName);

            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();

                // Extract necessary data from the user and assign to OfferViewModel fields
                offer.setAuthorRegistrationData(user.getCreated());
                offer.setAuthorOffersNumbers(user.getUserOffersCount());
                offer.setAuthorProfileImage(user.getImageUrl());
                offer.setAuthorLastOnlineDate(user.getLastOnline());
                offer.setId(horse.getId());
                offer.setOfferCategory(horse.getCategory());
                offer.setHorseCategory(horse.getHorseCategory());
                offer.setSex(horse.getSex());
                offer.setPrice(horse.getPrice());
                offer.setPhone(horse.getPhone());
                offer.setHiddenPhone(horse.isHiddenPhone());
                offer.setOfferName(horse.getOfferName());
                offer.setCity(horse.getCity());
                offer.setDescription(horse.getDescription());
                offer.setAuthorName(horse.getAuthorName());
                offer.setCreateDate(horse.getCreated());
                offer.setModifiedDate(horse.getModified());
                offer.setOfferViewCount(horse.getOfferViewCounter());
                offer.setVideoLink(horse.getVideoLink());
                offer.setIsActive(horse.getIsActive());

                // Retrieve image URLs from the database
                List<String> imageUrls = new ArrayList<>();

                List<OfferImageEntity> images = offerImageRepository.findByHorseOfferId(horse.getId());

                for (OfferImageEntity image : images) {
                    imageUrls.add(image.getImagePath()); // Get the image URL
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
