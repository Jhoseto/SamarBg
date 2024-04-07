package org.samarBg.service.serviceImpl;

import org.samarBg.model.entities.AccessoryOfferEntity;
import org.samarBg.model.entities.HorseOfferEntity;
import org.samarBg.model.entities.OfferImageEntity;
import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.CurrentUserService;
import org.samarBg.service.Mappers.MapperForAccessory;
import org.samarBg.service.Mappers.MapperForHorses;
import org.samarBg.service.OfferService;
import org.samarBg.service.UserService;
import org.samarBg.view.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class OfferServiceImpl implements OfferService {
    private final HorseOfferRepository horseOfferRepository;
    private final AccessoriesOfferRepository accessoriesOfferRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final OfferImageRepository offerImageRepository;
    private final UserService userService;

    @Autowired
    public OfferServiceImpl(HorseOfferRepository horseOfferRepository,
                            AccessoriesOfferRepository accessoriesOfferRepository,
                            UserRepository userRepository,
                            CurrentUserService currentUserService,
                            OfferImageRepository offerImageRepository,
                            UserService userService) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.offerImageRepository = offerImageRepository;
        this.userService = userService;
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
    public OfferViewModel findOfferById(Long offerId) {
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
            userRepository.save(currentUserService.getCurrentUser());

        } else if (accessoriesOfferRepository.existsById(offerId)){
            List<OfferImageEntity> images = offerImageRepository.findByAccessoryOfferId(offerId);

            // Изтрийте снимките от директорията и от базата данни
            deleteOfferImages(images);
            accessoriesOfferRepository.deleteById(offerId);
            userRepository.save(currentUserService.getCurrentUser());

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

    // Метод за извличане на всички обяви на потребителя
    public List<OfferViewModel> getAllOffersForUser(String username) {
        // Намерете потребителя по потребителското име
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Потребителят не е намерен."));

        List<OfferViewModel> userOffers = new ArrayList<>();
        List<OfferViewModel> allOffers = getAllOffers();

        // Филтриране на обявите, за да се запазят само тези на потребителя
        for (OfferViewModel offer : allOffers) {
            if (offer.getAuthorName().equals(username)) {
                OfferViewModel offerViewModel = findOfferById(offer.getId());
                userOffers.add(offerViewModel);
            }
        }
        return userOffers;
    }

    @Override
    public List<OfferViewModel> getOffersBySearchingWord(String searchingWord) {
        List<OfferViewModel> searchingOffers = new ArrayList<>();
        List<OfferViewModel> allOffers = getAllOffers();

        for (OfferViewModel offer : allOffers) {
            String userName = offer.getAuthorName();
            String offerName = offer.getOfferName();
            if (offerName.contains(searchingWord) || userName.contains(searchingWord)){
                searchingOffers.add(offer);
            }
        }
        return searchingOffers;
    }
}

