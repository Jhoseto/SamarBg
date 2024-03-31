package org.samarBg.service;


import org.samarBg.model.entities.AccessoryOfferEntity;
import org.samarBg.model.entities.HorseOfferEntity;
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
    private final UserRepository userRepository;
    private final OfferImageRepository offerImageRepository;

    @Autowired
    public OfferService(HorseOfferRepository horseOfferRepository,
                        AccessoriesOfferRepository accessoriesOfferRepository,
                        UserRepository userRepository,
                        OfferImageRepository offerImageRepository) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
        this.userRepository = userRepository;
        this.offerImageRepository = offerImageRepository;
    }


    public List<OfferViewModel> getAllOffers() {
        List<OfferViewModel> allOffers = new ArrayList<>();
        MapperForHorses mapHorseToOffer = new MapperForHorses(userRepository, offerImageRepository);//TODO sashtoto i za acccessori za da vzima ot Mapper klasa

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
                allOffers.add(MapperForAccessory.mapAccessoryToOffer(accessoryOffer));
            }

        }
        return allOffers;
    }

    public List<OfferViewModel> getAllAccessoryOffers() {
        List<OfferViewModel> allAccessoryOffers = new ArrayList<>();

        // Извличане на всички обяви за аксесоари от репозиторията и мапиране към обекти от тип AccessoryOfferEntity
        List<AccessoryOfferEntity> accessoryOffers = accessoriesOfferRepository.findAll();
        for (AccessoryOfferEntity accessoryOffer : accessoryOffers) {
            if (accessoryOffer.getIsActive()==1) { // Проверка дали обявата е активна
                allAccessoryOffers.add(MapperForAccessory.mapAccessoryToOffer(accessoryOffer));
            }
        }
        return allAccessoryOffers;
    }

    public List<OfferViewModel> getAllHorsesOffers() {
        List<OfferViewModel> allActiveHorsesOffers = new ArrayList<>();
        MapperForHorses mapHorseToOffer = new MapperForHorses(userRepository, offerImageRepository);

        // Извличане на всички обяви за коне от репозиторията и мапиране към обекти от тип HorseOfferEntity
        List<HorseOfferEntity> horseOffers = horseOfferRepository.findAll();
        for (HorseOfferEntity horseOffer : horseOffers) {
            if (horseOffer.getIsActive()==1) { // Проверка дали обявата е активна
                allActiveHorsesOffers.add(mapHorseToOffer.mapHorseToOffer(horseOffer));
            }
        }
        return allActiveHorsesOffers;
    }



    public OfferViewModel findById(Long offerId) {
        Optional<HorseOfferEntity> horseOfferOptional = horseOfferRepository.findById(offerId);
        MapperForHorses mapHorseToOffer = new MapperForHorses(userRepository, offerImageRepository);
        if (horseOfferOptional.isPresent()) {
            return mapHorseToOffer.mapHorseToOffer(horseOfferOptional.get());
        } else {
            Optional<AccessoryOfferEntity> accessoryOfferOptional = accessoriesOfferRepository.findById(offerId);
            if (accessoryOfferOptional.isPresent()) {
                return MapperForAccessory.mapAccessoryToOffer(accessoryOfferOptional.get());
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
            horseOfferRepository.save(offer);

        } else if (accessoryOffer.isPresent()) {
            AccessoryOfferEntity offer = accessoryOffer.get();
            offer.setIsActive(1);
            accessoriesOfferRepository.save(offer);
        } else {
            throw new IllegalArgumentException("The offer not found in DataBase");
        }
    }
}

