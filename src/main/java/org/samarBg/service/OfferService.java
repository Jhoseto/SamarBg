package org.samarBg.service;

import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OfferService {

    List<OfferViewModel> getAllOffers();

    List<OfferViewModel> getAllAccessoryOffers();

    List<OfferViewModel> getAllHorsesOffers();

    OfferViewModel findOfferById(Long offerId);

    void saveInExistOffers(OfferViewModel offerViewModel);

    void activateOffer(Long offerId);

    void deleteOffer(Long offerId);

    List<OfferViewModel> getAllOffersForUser(String username);
    List<OfferViewModel> getOffersBySearchingWord(String searchingWord);

}
