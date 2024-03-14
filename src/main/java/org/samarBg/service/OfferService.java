package org.samarBg.service;


import org.samarBg.model.entities.AccessoryOfferEntity;
import org.samarBg.model.entities.HorseOfferEntity;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.view.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        OfferViewModel offer = new OfferViewModel();
        String defaultImageUrl = "/impl/defOfferImg.png";

        // Извличане на необходимата информация от обекта за кон
        offer.setId(horse.getId());
        offer.setImageUrl(horse.getImageUrl() != null ? horse.getImageUrl() : defaultImageUrl);
        offer.setOfferCategory(horse.getCategory());
        offer.setHorseCategory(horse.getHorseCategory());
        offer.setPrice(horse.getPrice());
        offer.setOfferName(horse.getOfferName());
        offer.setCity(horse.getCity());
        offer.setDescription(horse.getDescription());
        offer.setAuthorName(horse.getAuthorName());
        offer.setCreateDate(horse.getCreated());
        return offer;
    }

    private OfferViewModel mapAccessoryToOffer(AccessoryOfferEntity accessory) {
        // Преобразуване на информацията за аксесоара в обява
        OfferViewModel offer = new OfferViewModel();
        String defaultImageUrl = "/impl/defOfferImg.png";

        // Извличане на необходимата информация от обекта за аксесоар
        offer.setId(accessory.getId());
        offer.setImageUrl(accessory.getImageUrl() != null ? accessory.getImageUrl() : defaultImageUrl);
        offer.setOfferCategory(accessory.getCategory());
        offer.setAccessoriesCategory(accessory.getAccessoriesCategory());
        offer.setPrice(accessory.getPrice());
        offer.setCity(accessory.getCity());
        offer.setOfferName(accessory.getOfferName());
        offer.setDescription(accessory.getDescription());
        offer.setCreateDate(accessory.getCreated());
        return offer;
    }
}

