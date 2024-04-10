package org.samarBg.service;

import org.samarBg.view.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service interface for sorted searching offers by word and category.
 */
@Service
public interface SortedSearchingOffers {

    /**
     * Retrieves a page of sorted offers by searching word and category.
     *
     * @param word              The word to search for
     * @param filter            Additional filter to apply (if any)
     * @param pageable          Pageable object for pagination and sorting
     * @param mainCategory      Main category to filter (e.g., "Horses", "Accessories")
     * @param cityCategory      City category to filter (e.g., city name)
     * @param horseCategory     Horse category to filter (if applicable)
     * @param genderCategory    Gender category to filter (if applicable)
     * @param accessoriesCategory Accessories category to filter (if applicable)
     * @return Page of sorted offers by searching word and category
     */
    Page<OfferViewModel> sortedSearchingByWordAndCategory(String word, String filter, Pageable pageable,
                                                          String mainCategory, String cityCategory,
                                                          String horseCategory, String genderCategory,
                                                          String accessoriesCategory);

}
