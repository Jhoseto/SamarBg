package org.samarBg.service.serviceImpl;

import org.samarBg.models.*;
import org.samarBg.models.enums.OfferCategory;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.repository.OfferImageRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.service.Mappers.MapperForAccessory;
import org.samarBg.service.Mappers.MapperForHorses;
import org.samarBg.service.OfferService;
import org.samarBg.service.UserService;
import org.samarBg.views.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of {@link OfferService} for managing and processing offer-related operations.
 */
@Service
public class OfferServiceImpl implements OfferService {

    private final HorseOfferRepository horseOfferRepository;
    private final AccessoriesOfferRepository accessoriesOfferRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final OfferImageRepository offerImageRepository;

    /**
     * Constructs a new OfferServiceImpl instance with the required repositories and services.
     *
     * @param horseOfferRepository      the repository for HorseOfferEntity
     * @param accessoriesOfferRepository the repository for AccessoryOfferEntity
     * @param userRepository             the repository for UserEntity
     * @param userService         the service for handling  user operations
     * @param offerImageRepository       the repository for OfferImageEntity
     */
    @Autowired
    public OfferServiceImpl(HorseOfferRepository horseOfferRepository,
                            AccessoriesOfferRepository accessoriesOfferRepository,
                            UserRepository userRepository,
                            UserService userService,
                            OfferImageRepository offerImageRepository) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.offerImageRepository = offerImageRepository;
    }

    /**
     * Retrieves all offers (both horse and accessory) and maps them to OfferViewModel objects.
     *
     * @return a list of all active offers as {@link OfferViewModel} objects
     */
    public List<OfferViewModel> getAllOffers() {
        List<OfferViewModel> allOffers = new ArrayList<>();
        MapperForHorses mapHorseToOffer = new MapperForHorses(userRepository, offerImageRepository);
        MapperForAccessory mapAccessoriesToOffer = new MapperForAccessory(userRepository, offerImageRepository);

        // Retrieve all horse offers from the repository and map them to OfferViewModel objects
        List<HorseOfferEntity> horseOffers = horseOfferRepository.findAll();
        for (HorseOfferEntity horseOffer : horseOffers) {
            if (horseOffer.getIsActive() == 1) { // Check if the offer is active
                allOffers.add(mapHorseToOffer.mapHorseToOffer(horseOffer));
            }
        }

        // Retrieve all accessory offers from the repository and map them to OfferViewModel objects
        List<AccessoryOfferEntity> accessoryOffers = accessoriesOfferRepository.findAll();
        for (AccessoryOfferEntity accessoryOffer : accessoryOffers) {
            if (accessoryOffer.getIsActive() == 1) { // Check if the offer is active
                allOffers.add(mapAccessoriesToOffer.mapAccessoryToOffer(accessoryOffer));
            }
        }
        return allOffers;
    }

    /**
     * Retrieves all accessory offers and maps them to OfferViewModel objects.
     *
     * @return a list of all active accessory offers as {@link OfferViewModel} objects
     */
    public List<OfferViewModel> getAllAccessoryOffers() {
        List<OfferViewModel> allAccessoryOffers = new ArrayList<>();
        MapperForAccessory mapAccessoriesToOffer = new MapperForAccessory(userRepository, offerImageRepository);

        List<AccessoryOfferEntity> accessoryOffers = accessoriesOfferRepository.findAll();
        for (AccessoryOfferEntity accessoryOffer : accessoryOffers) {
            if (accessoryOffer.getIsActive() == 1) { // Check if the offer is active
                allAccessoryOffers.add(mapAccessoriesToOffer.mapAccessoryToOffer(accessoryOffer));
            }
        }
        return allAccessoryOffers;
    }

    /**
     * Retrieves all horse offers and maps them to OfferViewModel objects.
     *
     * @return a list of all active horse offers as {@link OfferViewModel} objects
     */
    public List<OfferViewModel> getAllHorsesOffers() {
        List<OfferViewModel> allActiveHorsesOffers = new ArrayList<>();
        MapperForHorses mapHorseToOffer = new MapperForHorses(userRepository, offerImageRepository);

        List<HorseOfferEntity> horseOffers = horseOfferRepository.findAll();
        for (HorseOfferEntity horseOffer : horseOffers) {
            if (horseOffer.getIsActive() == 1) { // Check if the offer is active
                allActiveHorsesOffers.add(mapHorseToOffer.mapHorseToOffer(horseOffer));
            }
        }
        return allActiveHorsesOffers;
    }

    /**
     * Finds and retrieves an offer by its ID, either from horse offers or accessory offers.
     *
     * @param offerId the ID of the offer to retrieve
     * @return the offer as an {@link OfferViewModel} object
     * @throws IllegalArgumentException if the offer with the specified ID is not found
     */
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

    /**
     * Updates the view count of an existing offer.
     *
     * @param offerViewModel the updated offer information including the new view count
     */
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

    /**
     * Activates an offer by setting its status to active (1).
     *
     * @param offerId the ID of the offer to activate
     */
    public void activateOffer(Long offerId) {
        Optional<HorseOfferEntity> horseOffer = horseOfferRepository.findById(offerId);
        Optional<AccessoryOfferEntity> accessoryOffer = accessoriesOfferRepository.findById(offerId);

        if (horseOffer.isPresent()) {
            HorseOfferEntity offer = horseOffer.get();
            offer.setIsActive(1);
            userService.getCurrentUser().setUserOffersCount(
                    userService.getCurrentUser().getUserOffersCount() + 1);
            horseOfferRepository.save(offer);
        } else if (accessoryOffer.isPresent()) {
            AccessoryOfferEntity offer = accessoryOffer.get();
            offer.setIsActive(1);
            userService.getCurrentUser().setUserOffersCount(
                    userService.getCurrentUser().getUserOffersCount() + 1);
            accessoriesOfferRepository.save(offer);
        } else {
            throw new IllegalArgumentException("The offer not found in the database");
        }
    }

    /**
     * Deletes an offer by its ID.
     *
     * @param offerId the ID of the offer to delete
     */
    public void deleteOffer(Long offerId) {
        OfferViewModel currentOffer = findOfferById(offerId);

        if (currentOffer.getOfferCategory().equals(OfferCategory.HORSES)) {
            List<OfferImageEntity> images = offerImageRepository.findByHorseOfferId(offerId);
            Optional<UserEntity> user = userRepository.findByUsername(currentOffer.getAuthorName());

            if (user.isPresent() && currentOffer.getIsActive() == 1) {
                UserEntity currentUser = user.get();
                currentUser.setUserOffersCount(currentUser.getUserOffersCount() - 1);
                userRepository.save(currentUser);
            }

            // Delete images from directory and database
            deleteOfferImages(images);
            horseOfferRepository.deleteById(offerId);

        } else if (currentOffer.getOfferCategory().equals(OfferCategory.ACCESSORIES)) {
            List<OfferImageEntity> images = offerImageRepository.findByAccessoryOfferId(offerId);
            Optional<UserEntity> user = userRepository.findByUsername(currentOffer.getAuthorName());

            if (user.isPresent() && currentOffer.getIsActive() == 1) {
                UserEntity currentUser = user.get();
                currentUser.setUserOffersCount(currentUser.getUserOffersCount() - 1);
                userRepository.save(currentUser);
            }

            // Delete images from directory and database
            deleteOfferImages(images);
            accessoriesOfferRepository.deleteById(offerId);
        } else {
            throw new IllegalArgumentException("Offer with ID " + offerId + " does not exist.");
        }
    }

    /**
     * Deletes offer images from the file system and database.
     *
     * @param images the list of images associated with the offer
     */
    private void deleteOfferImages(List<OfferImageEntity> images) {
        String directoryPath = "F:/MyProjects/SamarBG/SamarBg/src/main/resources/static";
        Path directory = Paths.get(directoryPath);

        if (!Files.exists(directory)) {
            return;
        }

        for (OfferImageEntity image : images) {
            try {
                Path filePath = Paths.get(directoryPath, image.getImagePath());
                Files.deleteIfExists(filePath); // Delete the file

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Delete images from the database
        offerImageRepository.deleteAll(images);
    }


    /**
     * Retrieves all offers created by a specific user.
     *
     * @param username the username of the user
     * @return a list of {@link OfferViewModel} objects representing offers created by the user
     * @throws IllegalArgumentException if the user is not found
     */
    public List<OfferViewModel> getAllOffersForUser(String username) {
        List<OfferViewModel> userOffers = new ArrayList<>();
        List<OfferViewModel> allOffers = getAllOffers();

        // Filter offers to keep only those created by the user
        for (OfferViewModel offer : allOffers) {
            if (offer.getAuthorName().equals(username)) {
                OfferViewModel offerViewModel = findOfferById(offer.getId());
                userOffers.add(offerViewModel);
            }
        }
        return userOffers;
    }

    /**
     * Retrieves offers by searching for a specific keyword in offer names or authors.
     *
     * @param searchingWord the keyword to search for
     * @return a list of {@link OfferViewModel} objects matching the search criteria
     */
    @Override
    public List<OfferViewModel> getOffersBySearchingWord(String searchingWord) {
        List<OfferViewModel> searchingOffers = new ArrayList<>();
        List<OfferViewModel> allOffers = getAllOffers();

        if (!searchingWord.isEmpty()) {
            String searchLowerCase = searchingWord.toLowerCase();

            for (OfferViewModel offer : allOffers) {
                String userName = offer.getAuthorName().toLowerCase();
                String offerName = offer.getOfferName().toLowerCase();

                // Check for matches in lowercase
                if (offerName.contains(searchLowerCase) || userName.contains(searchLowerCase)) {
                    searchingOffers.add(offer);
                }
            }
            return searchingOffers;
        } else {
            return allOffers;
        }
    }


    /**
     * Retrieves a list of the newest offers based on their creation date.
     * <p>
     * This method fetches all available offers, sorts them based on their creation date in descending order,
     * and then retrieves the top 12 newest offers.
     *
     * @return A list of OfferViewModel objects representing the 12 newest offers, sorted by creation date.
     */
    public List<OfferViewModel> getNewestOffers() {
        List<OfferViewModel> allOffers = getAllOffers();

        // Comparator to sort offers by creation date in descending order
        Comparator<OfferViewModel> comparator = Comparator.comparing(OfferViewModel::getCreateDate).reversed();
        allOffers.sort(comparator);

        // Retrieve the top 12 newest offers
        List<OfferViewModel> newestOffers = allOffers.stream()
                .limit(12)
                .collect(Collectors.toList());

        return newestOffers;
    }
}
