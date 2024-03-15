package org.samarBg.service;


import org.samarBg.model.entities.AccessoryOfferEntity;
import org.samarBg.model.entities.HorseOfferEntity;
import org.samarBg.model.entities.UserEntity;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
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
    @Autowired
    public OfferService(HorseOfferRepository horseOfferRepository, AccessoriesOfferRepository accessoriesOfferRepository) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
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

    private OfferViewModel mapHorseToOffer(HorseOfferEntity horse) {
        // Преобразуване на информацията за коня в обява
        UserEntity user = new UserEntity();
        OfferViewModel offer = new OfferViewModel();
        String defaultImageUrl = "/impl/defOfferImg.png";

        // Извличане на необходимата информация от обекта за кон
        offer.setId(horse.getId());
        offer.setImageUrl(horse.getImageUrl() != null ? horse.getImageUrl() : defaultImageUrl);
        offer.setOfferCategory(horse.getCategory());
        offer.setHorseCategory(horse.getHorseCategory());
        offer.setSex(horse.getSex());
        offer.setPrice(horse.getPrice());
        offer.setPhone(horse.getPhone());
        offer.setOfferName(horse.getOfferName());
        offer.setCity(horse.getCity());
        offer.setDescription(horse.getDescription());
        offer.setAuthorName(horse.getAuthorName());
        offer.setCreateDate(horse.getCreated());
        offer.setModifiedDate(horse.getModified());
        offer.setOfferViewCount(horse.getOfferViewCounter());
        offer.setUserRegistrationDate(user.getCreated());
        offer.setUserOffersCount(user.getUserOffersCount());
        return offer;
    }

    private OfferViewModel mapAccessoryToOffer(AccessoryOfferEntity accessory) {

        OfferViewModel offer = new OfferViewModel();
        UserEntity user = new UserEntity();
        String defaultImageUrl = "/impl/defOfferImg.png";

        // Извличане на информация от обекта за аксесоар
        offer.setId(accessory.getId());
        offer.setImageUrl(accessory.getImageUrl() != null ? accessory.getImageUrl() : defaultImageUrl);
        offer.setOfferCategory(accessory.getCategory());
        offer.setAccessoriesCategory(accessory.getAccessoriesCategory());
        offer.setPrice(accessory.getPrice());
        offer.setPhone(accessory.getPhone());
        offer.setCity(accessory.getCity());
        offer.setOfferName(accessory.getOfferName());
        offer.setDescription(accessory.getDescription());
        offer.setCreateDate(accessory.getCreated());
        offer.setOfferViewCount(accessory.getOfferViewCounter());
        offer.setUserRegistrationDate(user.getCreated());
        offer.setUserOffersCount(user.getUserOffersCount());
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


    public void saveInOffers(OfferViewModel offerViewModel) {
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

}

