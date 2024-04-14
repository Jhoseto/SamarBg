package org.samarBg.service.serviceImpl;

import org.samarBg.models.AccessoryOfferEntity;
import org.samarBg.models.BaseEntity;
import org.samarBg.models.HorseOfferEntity;
import org.samarBg.models.OfferImageEntity;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.service.AddOffersService;
import org.samarBg.service.UpdateOffers;
import org.samarBg.views.OfferViewModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the UpdateOffers service interface.
 */
@Service
public class UpdateOffersImpl implements UpdateOffers {

    private final HorseOfferRepository horseOfferRepository;
    private final AccessoriesOfferRepository accessoriesOfferRepository;
    private final OfferImageRepository offerImageRepository;
    private final AddOffersService addOffersService;

    /**
     * Constructs a new UpdateOffersImpl instance.
     *
     * @param horseOfferRepository       Repository for horse offers.
     * @param accessoriesOfferRepository Repository for accessory offers.
     * @param offerImageRepository       Repository for offer images.
     * @param addOffersService           Service for handling offer operations.
     */
    public UpdateOffersImpl(HorseOfferRepository horseOfferRepository,
                            AccessoriesOfferRepository accessoriesOfferRepository,
                            OfferImageRepository offerImageRepository,
                            AddOffersService addOffersService) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
        this.offerImageRepository = offerImageRepository;
        this.addOffersService = addOffersService;
    }

    /**
     * Updates an offer based on the provided offer ID, view model, and files.
     *
     * @param offerId        The ID of the offer to update.
     * @param offerViewModel The updated view model for the offer.
     * @param files          The array of multipart files (images) to be updated.
     * @throws IOException If an I/O error occurs during file handling.
     */
    @Override
    public void updateOffers(Long offerId, OfferViewModel offerViewModel, MultipartFile[] files) throws IOException {
        Optional<HorseOfferEntity> horseOfferOptional = horseOfferRepository.findById(offerId);
        Optional<AccessoryOfferEntity> accessoryOfferOptional = accessoriesOfferRepository.findById(offerId);

        if (horseOfferOptional.isPresent()) {
            updateHorseOffer(horseOfferOptional.get(), offerViewModel, files);
        } else if (accessoryOfferOptional.isPresent()) {
            updateAccessoryOffer(accessoryOfferOptional.get(), offerViewModel, files);
        }
    }

    private void updateHorseOffer(HorseOfferEntity horseOffer,
                                  OfferViewModel offerViewModel,
                                  MultipartFile[] files) throws IOException {
        List<OfferImageEntity> oldImages = offerImageRepository.findByHorseOfferId(horseOffer.getId());
        List<String> newImages = addOffersService.uploadImages(Arrays.asList(files));
        updateOfferImages(horseOffer.getImages(), oldImages, newImages);

        horseOffer.setOfferName(offerViewModel.getOfferName());
        horseOffer.setCity(offerViewModel.getCity());
        horseOffer.setPhone(offerViewModel.getPhone());
        horseOffer.setPrice(offerViewModel.getPrice());
        horseOffer.setDescription(offerViewModel.getDescription());
        setCurrentTimeStamps(horseOffer);

        horseOfferRepository.save(horseOffer);
    }

    private void updateAccessoryOffer(AccessoryOfferEntity accessoryOffer,
                                      OfferViewModel offerViewModel,
                                      MultipartFile[] files) throws IOException {
        List<OfferImageEntity> oldImages = offerImageRepository.findByHorseOfferId(accessoryOffer.getId());
        List<String> newImages = addOffersService.uploadImages(Arrays.asList(files));
        updateOfferImages(accessoryOffer.getImages(), oldImages, newImages);

        accessoryOffer.setOfferName(offerViewModel.getOfferName());
        accessoryOffer.setCity(offerViewModel.getCity());
        accessoryOffer.setPhone(offerViewModel.getPhone());
        accessoryOffer.setPrice(offerViewModel.getPrice());
        accessoryOffer.setDescription(offerViewModel.getDescription());
        setCurrentTimeStamps(accessoryOffer);

        accessoriesOfferRepository.save(accessoryOffer);
    }

    private void updateOfferImages(List<OfferImageEntity> images,
                                   List<OfferImageEntity> oldImages,
                                   List<String> newImages) {
        int maxIterations = Math.min(oldImages.size(), newImages.size());

        for (int i = 0; i < maxIterations; i++) {
            String newImagePath = newImages.get(i);
            OfferImageEntity image = images.get(i);
            OfferImageEntity oldImage = oldImages.get(i);
            String oldImagePath = oldImage.getImagePath();

            if (!newImagePath.equals(image.getImagePath())) {
                String fullOldImagePath = "F:/MyProjects/SamarBG/SamarBg/src/main/resources/static" + oldImagePath;
                File oldImageFile = new File(fullOldImagePath);
                oldImageFile.delete();

                image.setImagePath(newImagePath);
            }
        }
    }

    private void setCurrentTimeStamps(BaseEntity baseEntity) {
        baseEntity.setModified(Instant.now());
    }
}
