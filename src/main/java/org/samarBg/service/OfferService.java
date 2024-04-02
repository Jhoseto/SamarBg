package org.samarBg.service;


import org.samarBg.model.entities.AccessoryOfferEntity;
import org.samarBg.model.entities.HorseOfferEntity;
import org.samarBg.model.entities.OfferImageEntity;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.view.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class OfferService {
    private final HorseOfferRepository horseOfferRepository;
    private final AccessoriesOfferRepository accessoriesOfferRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final OfferImageRepository offerImageRepository;

    @Autowired
    public OfferService(HorseOfferRepository horseOfferRepository,
                        AccessoriesOfferRepository accessoriesOfferRepository,
                        UserRepository userRepository,
                        CurrentUserService currentUserService,
                        OfferImageRepository offerImageRepository) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.offerImageRepository = offerImageRepository;
    }


    public List<OfferViewModel> getAllOffers() {
        List<OfferViewModel> allOffers = new ArrayList<>();
        MapperForHorses mapHorseToOffer = new MapperForHorses(userRepository, offerImageRepository);
        MapperForAccessory mapAccessoriesToOffer = new MapperForAccessory(userRepository, offerImageRepository);

        // Извличане на всички обяви за коне от репозиторията и мапиране към обекти от тип HorseOfferEntity
        List<HorseOfferEntity> horseOffers = horseOfferRepository.findAll();
        for (HorseOfferEntity horseOffer : horseOffers) {
            if (horseOffer.getIsActive()==1) { // Проверка дали обявата е активна
                allOffers.add(mapHorseToOffer.mapHorseToOffer(horseOffer));
            }
        }
        // Извличане на всички обяви за аксесоари от репозиторията и мапиране към обекти от тип AccessoryOfferEntity
        List<AccessoryOfferEntity> accessoryOffers = accessoriesOfferRepository.findAll();
        for (AccessoryOfferEntity accessoryOffer : accessoryOffers) {
            if (accessoryOffer.getIsActive()==1) { // Проверка дали обявата е активна
                allOffers.add(mapAccessoriesToOffer.mapAccessoryToOffer(accessoryOffer));
            }
        }
        return allOffers;
    }

    // Извличане на всички обяви за аксесоари от репозиторията и мапиране към обекти от тип AccessoryOfferEntity
    public List<OfferViewModel> getAllAccessoryOffers() {
        List<OfferViewModel> allAccessoryOffers = new ArrayList<>();
        MapperForAccessory mapAccessoriesToOffer = new MapperForAccessory(userRepository, offerImageRepository);

        List<AccessoryOfferEntity> accessoryOffers = accessoriesOfferRepository.findAll();
        for (AccessoryOfferEntity accessoryOffer : accessoryOffers) {
            if (accessoryOffer.getIsActive()==1) { // Проверка дали обявата е активна
                allAccessoryOffers.add(mapAccessoriesToOffer.mapAccessoryToOffer(accessoryOffer));
            }
        }
        return allAccessoryOffers;
    }

    // Извличане на всички обяви за коне от репозиторията и мапиране към обекти от тип HorseOfferEntity
    public List<OfferViewModel> getAllHorsesOffers() {
        List<OfferViewModel> allActiveHorsesOffers = new ArrayList<>();
        MapperForHorses mapHorseToOffer = new MapperForHorses(userRepository, offerImageRepository);

        List<HorseOfferEntity> horseOffers = horseOfferRepository.findAll();
        for (HorseOfferEntity horseOffer : horseOffers) {
            if (horseOffer.getIsActive()==1) { // Проверка дали обявата е активна
                allActiveHorsesOffers.add(mapHorseToOffer.mapHorseToOffer(horseOffer));
            }
        }
        return allActiveHorsesOffers;
    }

    //Method for searching Offer ID from booth categories H/A
    public OfferViewModel findById(Long offerId) {
        MapperForHorses mapHorseToOffer = new MapperForHorses(userRepository, offerImageRepository);
        MapperForAccessory mapAccessoriesToOffer = new MapperForAccessory(userRepository, offerImageRepository);

        Optional<HorseOfferEntity> horseOfferOptional = horseOfferRepository.findById(offerId);
        if (horseOfferOptional.isPresent()) {
            return mapHorseToOffer.mapHorseToOffer(horseOfferOptional.get());
        } else {
            Optional<AccessoryOfferEntity> accessoryOfferOptional = accessoriesOfferRepository.findById(offerId);
            if (accessoryOfferOptional.isPresent()) {
                return mapAccessoriesToOffer.mapAccessoryToOffer(accessoryOfferOptional.get());
            } else {
                throw new IllegalArgumentException("Offer with ID " + offerId + " not found.");
            }
        }
    }


    //Method using for offers counter update
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

    public void activateOffer(Long offerId) {
        Optional<HorseOfferEntity> horseOffer = horseOfferRepository.findById(offerId);
        Optional<AccessoryOfferEntity> accessoryOffer = accessoriesOfferRepository.findById(offerId);

        if (horseOffer.isPresent()){
            HorseOfferEntity offer = horseOffer.get();
            offer.setIsActive(1);
            currentUserService.getCurrentUser()
                    .setUserOffersCount(currentUserService.getCurrentUser().getUserOffersCount()+1);
            horseOfferRepository.save(offer);

        } else if (accessoryOffer.isPresent()) {
            AccessoryOfferEntity offer = accessoryOffer.get();
            offer.setIsActive(1);
            currentUserService.getCurrentUser()
                    .setUserOffersCount(currentUserService.getCurrentUser().getUserOffersCount()+1);
            accessoriesOfferRepository.save(offer);
        } else {
            throw new IllegalArgumentException("The offer not found in DataBase");
        }
    }

    // Method for delete offer by ID
    public void deleteOffer(Long offerId) {

        if (horseOfferRepository.existsById(offerId)) {
            List<OfferImageEntity> images = offerImageRepository.findByHorseOfferId(offerId);

            // Изтрийте снимките от директорията и от базата данни
            deleteOfferImages(images);
            horseOfferRepository.deleteById(offerId);

        } else if (accessoriesOfferRepository.existsById(offerId)){
            List<OfferImageEntity> images = offerImageRepository.findByAccessoryOfferId(offerId);

            // Изтрийте снимките от директорията и от базата данни
            deleteOfferImages(images);
            accessoriesOfferRepository.deleteById(offerId);

        }else {
            throw new IllegalArgumentException("Обявата с идентификатор " + offerId + " не съществува.");
        }
    }

    //Method for deleting image files
    private void deleteOfferImages(List<OfferImageEntity> images) {
        String directoryPath = "F:/MyProjects/SamarBG/SamarBg/src/main/resources/static";
        Path directory = Paths.get(directoryPath);

        if (!Files.exists(directory)) {
            return;
        }

        for (OfferImageEntity image : images) {
            Path imagePath = directory.resolve(image.getImagePath());
            try {
                Path filePath = Paths.get(directoryPath, image.getImagePath());
                Files.deleteIfExists(filePath); // Изтриваме файла

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // Изтрийте снимките от базата данни
        offerImageRepository.deleteAll(images);
    }
}

