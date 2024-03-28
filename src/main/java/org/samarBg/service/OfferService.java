package org.samarBg.service;


import org.samarBg.model.entities.AccessoryOfferEntity;
import org.samarBg.model.entities.HorseOfferEntity;
import org.samarBg.model.entities.OfferImageEntity;
import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.view.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final HorseOfferRepository horseOfferRepository;
    private final AccessoriesOfferRepository accessoriesOfferRepository;
    private final OfferImageRepository offerImageRepository;
    private final UserRepository userRepository;
    @Autowired
    public OfferService(HorseOfferRepository horseOfferRepository,
                        AccessoriesOfferRepository accessoriesOfferRepository,
                        OfferImageRepository offerImageRepository, UserRepository userRepository) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
        this.offerImageRepository = offerImageRepository;
        this.userRepository = userRepository;
    }


    public List<OfferViewModel> getAllOffers() {
        List<OfferViewModel> allOffers = new ArrayList<>();

        // Извличане на всички обяви за коне от репозиторията и мапиране към обекти от тип HorseOfferEntity
        List<HorseOfferEntity> horseOffers = horseOfferRepository.findAll();
        for (HorseOfferEntity horseOffer : horseOffers) {
            allOffers.add(mapHorseToOffer(horseOffer));
        }

        // Извличане на всички обяви за аксесоари от репозиторията и мапиране към обекти от тип AccessoryOfferEntity
        List<AccessoryOfferEntity> accessoryOffers = accessoriesOfferRepository.findAll();
        for (AccessoryOfferEntity accessoryOffer : accessoryOffers) {
            allOffers.add(mapAccessoryToOffer(accessoryOffer));
        }

        return allOffers;
    }

    public List<OfferViewModel> getAllAccessoryOffers() {
        List<OfferViewModel> allAccessoryOffers = new ArrayList<>();

        // Извличане на всички обяви за аксесоари от репозиторията и мапиране към обекти от тип AccessoryOfferEntity
        List<AccessoryOfferEntity> accessoryOffers = accessoriesOfferRepository.findAll();
        for (AccessoryOfferEntity accessoryOffer : accessoryOffers) {
            allAccessoryOffers.add(mapAccessoryToOffer(accessoryOffer));
        }

        return allAccessoryOffers;
    }

    public List<OfferViewModel> getAllHorsesOffers() {
        List<OfferViewModel> allHorsesOffers = new ArrayList<>();

        // Извличане на всички обяви за коне от репозиторията и мапиране към обекти от тип HorseOfferEntity
        List<HorseOfferEntity> horseOffers = horseOfferRepository.findAll();
        for (HorseOfferEntity horseOffer : horseOffers) {
            allHorsesOffers.add(mapHorseToOffer(horseOffer));
        }
        return allHorsesOffers;
    }

    private OfferViewModel mapHorseToOffer(HorseOfferEntity horse) {
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

                // Останалите данни за обявата могат да бъдат присвоени направо от обекта на коня
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


    private OfferViewModel mapAccessoryToOffer(AccessoryOfferEntity accessory) {

        OfferViewModel offer = new OfferViewModel();
        UserEntity user = new UserEntity();
        String defaultImageUrl = "/impl/defOfferImg.png";

        // Извличане на информация от обекта за аксесоар
        offer.setId(accessory.getId());
        offer.setBasicImageUrl(accessory.getImageUrl() != null ? accessory.getImageUrl() : defaultImageUrl);
        offer.setOfferCategory(accessory.getCategory());
        offer.setAccessoriesCategory(accessory.getAccessoriesCategory());
        offer.setPrice(accessory.getPrice());
        offer.setPhone(accessory.getPhone());
        offer.setCity(accessory.getCity());
        offer.setOfferName(accessory.getOfferName());
        offer.setDescription(accessory.getDescription());
        offer.setCreateDate(accessory.getCreated());
        offer.setOfferViewCount(accessory.getOfferViewCounter());
        offer.setVideoLink(accessory.getVideoLink());

        offer.setAuthorName(accessory.getAuthorName());
        offer.setAuthorRegistrationData(user.getCreated());
        offer.setAuthorOffersNumbers(user.getUserOffersCount());
        offer.setAuthorLastOnlineDate(user.getLastOnline());
        offer.setAuthorProfileImage(user.getImageUrl());




        return offer;
    }


    public OfferViewModel findById(Long offerId) {
        Optional<HorseOfferEntity> horseOfferOptional = horseOfferRepository.findById(offerId);
        if (horseOfferOptional.isPresent()) {
            return mapHorseToOffer(horseOfferOptional.get());
        } else {
            Optional<AccessoryOfferEntity> accessoryOfferOptional = accessoriesOfferRepository.findById(offerId);
            if (accessoryOfferOptional.isPresent()) {
                return mapAccessoryToOffer(accessoryOfferOptional.get());
            } else {
                throw new IllegalArgumentException("Offer with ID " + offerId + " not found.");
            }
        }
    }


    public void saveInExistOffers(OfferViewModel offerViewModel) {
        Long offerId = offerViewModel.getId();
        Optional<HorseOfferEntity> horseOfferOptional = horseOfferRepository.findById(offerId);
        if (horseOfferOptional.isPresent()) {
            HorseOfferEntity horseOfferEntity = horseOfferOptional.get();
            horseOfferEntity.setOfferViewCounter(offerViewModel.getOfferViewCount());

            horseOfferRepository.save(horseOfferEntity);
        } else {
            Optional<AccessoryOfferEntity> accessoryOfferOptional = accessoriesOfferRepository.findById(offerId);
            if (accessoryOfferOptional.isPresent()) {
                AccessoryOfferEntity accessoryOfferEntity = accessoryOfferOptional.get();
                accessoryOfferEntity.setOfferViewCounter(offerViewModel.getOfferViewCount());

                accessoriesOfferRepository.save(accessoryOfferEntity);
            } else {
                throw new IllegalArgumentException("Offer with ID " + offerId + " not found.");
            }
        }
    }


    public String findUsernameByOfferId(Long offerId) {
        Optional<HorseOfferEntity> optionalHorseOffer = horseOfferRepository.findById(offerId);
        if (optionalHorseOffer.isPresent()) {
            HorseOfferEntity horseOffer = optionalHorseOffer.get();
            String authorName = horseOffer.getAuthorName(); // Името на автора
            Optional<UserEntity> optionalUser = userRepository.findByUsername(authorName);
            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();
                return user.getUsername();
            } else {
                // Потребителят не е намерен
                return null;
            }
        } else {
            // Обявата не е намерена
            return null;
        }
    }

}

