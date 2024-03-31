package org.samarBg.service;

import org.samarBg.model.entities.AccessoryOfferEntity;
import org.samarBg.model.entities.UserEntity;
import org.samarBg.view.OfferViewModel;

public final class MapperForAccessory {
    private MapperForAccessory() {
    }

    public static OfferViewModel mapAccessoryToOffer(AccessoryOfferEntity accessory) {

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
}
