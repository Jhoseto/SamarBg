package org.samarBg.service;

import org.samarBg.views.OfferViewModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service interface for managing offers.
 */

public interface OfferService {

    /**
     * Retrieves all active offers.
     *
     * @return List of all active offers
     */
    List<OfferViewModel> getAllOffers();

    /**
     * Retrieves all active accessory offers.
     *
     * @return List of all active accessory offers
     */
    List<OfferViewModel> getAllAccessoryOffers();

    /**
     * Retrieves all active horse offers.
     *
     * @return List of all active horse offers
     */
    List<OfferViewModel> getAllHorsesOffers();

    /**
     * Finds an offer by its ID from all Offers.
     *
     * @param offerId ID of the offer to find
     * @return The offer view model corresponding to the ID
     * @throws IllegalArgumentException if no offer is found with the given ID
     */
    OfferViewModel findOfferById(Long offerId);

    /**
     * Updates an existing offer's view count.
     *
     * @param offerViewModel The offer view model containing updated view count
     */
    void saveInExistOffers(OfferViewModel offerViewModel);

    /**
     * Activates an offer by setting its status to active.
     *
     * @param offerId ID of the offer to activate
     */
    void activateOffer(Long offerId);

    /**
     * Deletes an offer by its ID.
     *
     * @param offerId ID of the offer to delete
     * @throws IllegalArgumentException if the offer does not exist
     */
    void deleteOffer(Long offerId);

    /**
     * Retrieves all offers created by a specific user.
     *
     * @param username Username of the user whose offers are to be retrieved
     * @return List of offers created by the specified user
     * @throws IllegalArgumentException if the user does not exist
     */
    List<OfferViewModel> getAllOffersForUser(String username);

    /**
     * Retrieves offers based on a provided searching word.
     *
     * @param searchingWord The word to search within offer names and usernames
     * @return List of offers matching the searching word
     */
    List<OfferViewModel> getOffersBySearchingWord(String searchingWord);

    /**
     * Retrieves a list of the newest offers.
     *
     * This method retrieves a list of OfferViewModel objects representing the newest offers.
     * The criteria for determining "newest" offers can vary and should be specified in the
     * implementation of the OfferService.
     *
     * @return A list of OfferViewModel objects representing the newest offers.
     */
    List<OfferViewModel> getNewestOffers();

    /**
     * Groups the list of offer view models into sublists, each containing four offer view models.
     * If the number of offers is not evenly divisible by four, the last sublist may contain fewer than four offers.
     *
     * @param offers The list of offer view models to be grouped.
     * @return List of sublists, each containing four offer view models.
     */
    List<List<OfferViewModel>> groupOffers(List<OfferViewModel> offers);
}
