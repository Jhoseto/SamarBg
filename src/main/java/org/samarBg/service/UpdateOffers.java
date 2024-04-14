package org.samarBg.service;

import org.samarBg.views.OfferViewModel;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * Service for updating offers.
 */
public interface UpdateOffers {

    /**
     * Updates the offer with the specified identifier.
     *
     * @param offerId        The identifier of the offer
     * @param offerViewModel The model containing updated offer data
     * @param files          Array of uploaded files (images) for the update
     * @throws IOException if an error occurs while working with files
     */
    void updateOffers(Long offerId, OfferViewModel offerViewModel, MultipartFile[] files) throws IOException;

}
