package org.samarBg.service;

import org.samarBg.view.AddAccessoriesViewModel;
import org.samarBg.view.AddOfferHorseViewModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service for adding offers.
 */
@Service
public interface AddOffersService {

    /**
     * Adds a new horse offer.
     *
     * @param addOfferHorseViewModel model for adding a horse offer
     * @param imageUrls              list of URLs of the images of the offer
     * @return the ID of the added offer
     */
    Long addHorseOffer(AddOfferHorseViewModel addOfferHorseViewModel, List<String> imageUrls);

    /**
     * Adds a new accessories offer.
     *
     * @param addAccessoriesViewModel model for adding an accessories offer
     * @param imageUrls               list of URLs of the images of the offer
     * @return the ID of the added offer
     */
    Long addAccessoriesOffer(AddAccessoriesViewModel addAccessoriesViewModel, List<String> imageUrls);

    /**
     * Uploads images to the server.
     *
     * @param files list of files to upload
     * @return list of URLs of the uploaded images
     * @throws IOException if an error occurs while uploading the files
     */
    List<String> uploadImages(List<MultipartFile> files) throws IOException;
}
