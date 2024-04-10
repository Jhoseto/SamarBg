package org.samarBg.service.serviceImpl;

import org.samarBg.model.entities.enums.AccessoriesCategory;
import org.samarBg.model.entities.enums.HorseCategory;
import org.samarBg.model.entities.enums.Sex;
import org.samarBg.service.OfferService;
import org.samarBg.service.SortedSearchingOffers;
import org.samarBg.view.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of SortedSearchingOffers service interface.
 * This service provides methods to perform sorted searching of offers based on various filters.
 */
@Service
public class SortedSearchingOffersImpl implements SortedSearchingOffers {

    private final OfferService offerService;

    public SortedSearchingOffersImpl(OfferService offerService) {
        this.offerService = offerService;
    }

    /**
     * Perform sorted searching of offers based on specified word and category filters.
     *
     * @param word              The search keyword.
     * @param filter            The sorting filter (e.g., New, Old, Expensive, Cheap, Favorites).
     * @param pageable          The pagination information.
     * @param mainCategory      The main category filter.
     * @param cityCategory      The city category filter.
     * @param horseCategory     The horse category filter.
     * @param genderCategory    The gender category filter.
     * @param accessoriesCategory The accessories category filter.
     * @return A paginated list of sorted OfferViewModel objects.
     */
    @Override
    public Page<OfferViewModel> sortedSearchingByWordAndCategory(String word, String filter, Pageable pageable,
                                                                 String mainCategory, String cityCategory,
                                                                 String horseCategory, String genderCategory,
                                                                 String accessoriesCategory) {
        // Get all offers matching the search word
        List<OfferViewModel> searchingOffers = offerService.getOffersBySearchingWord(word);

        // Apply filters based on selected categories
        if (!mainCategory.equals("none") && !mainCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> offer.getOfferCategory().toString().equals(mainCategory))
                    .collect(Collectors.toList());
        }

        if (!cityCategory.equals("none") && !cityCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> offer.getCity().toString().equalsIgnoreCase(cityCategory))
                    .collect(Collectors.toList());
        }

        if (!horseCategory.equals("none") && !horseCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> {
                        HorseCategory offerHorseCategory = offer.getHorseCategory();
                        return offerHorseCategory != null && offerHorseCategory.toString().equals(horseCategory);
                    })
                    .collect(Collectors.toList());
        }

        if (!genderCategory.equals("none") && !genderCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> {
                        Sex offerSex = offer.getSex();
                        return offerSex != null && offerSex.toString().equalsIgnoreCase(genderCategory);
                    })
                    .collect(Collectors.toList());
        }

        if (!accessoriesCategory.equals("none") && !accessoriesCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> {
                        AccessoriesCategory offerAccessoriesCategory = offer.getAccessoriesCategory();
                        return offerAccessoriesCategory != null && offerAccessoriesCategory.toString().equalsIgnoreCase(accessoriesCategory);
                    })
                    .collect(Collectors.toList());
        }

        // Apply sorting based on selected filter
        Comparator<OfferViewModel> comparator;
        switch (filter) {
            case "New":
                comparator = Comparator.comparing(OfferViewModel::getCreateDate).reversed();
                break;
            case "Old":
                comparator = Comparator.comparing(OfferViewModel::getCreateDate);
                break;
            case "Expensive":
                comparator = Comparator.comparing(OfferViewModel::getPrice).reversed();
                break;
            case "Cheap":
                comparator = Comparator.comparing(OfferViewModel::getPrice);
                break;
            case "Favorites":
                comparator = Comparator.comparing(OfferViewModel::getOfferViewCount).reversed();
                break;
            default:
                comparator = Comparator.comparing(OfferViewModel::getCreateDate).reversed(); // Default sorting by Date (newest first)
                break;
        }

        // Sort the offers based on the selected comparator
        List<OfferViewModel> sortedOffers = searchingOffers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        // Perform pagination
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        // Create a Page object containing the paginated and sorted offers
        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }
}
