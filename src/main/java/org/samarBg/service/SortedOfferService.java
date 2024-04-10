package org.samarBg.service;

import org.samarBg.views.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service interface for sorting and retrieving offers.
 */
@Service
public interface SortedOfferService {

    /**
     * Retrieves a page of sorted offers by price.
     *
     * @param filter   Filter to apply (if any)
     * @param pageable Pageable object for pagination and sorting
     * @return Page of sorted offers by price
     */
    Page<OfferViewModel> sortedOffersByPrice(String filter, Pageable pageable);

    /**
     * Retrieves a page of sorted offers by date.
     *
     * @param filter   Filter to apply (if any)
     * @param pageable Pageable object for pagination and sorting
     * @return Page of sorted offers by date
     */
    Page<OfferViewModel> sortedOffersByDate(String filter, Pageable pageable);

    /**
     * Retrieves a page of sorted offers by view count.
     *
     * @param filter   Filter to apply (if any)
     * @param pageable Pageable object for pagination and sorting
     * @return Page of sorted offers by view count
     */
    Page<OfferViewModel> sortedOffersByView(String filter, Pageable pageable);

    /**
     * Retrieves a page of sorted horse offers by price.
     *
     * @param filter   Filter to apply (if any)
     * @param pageable Pageable object for pagination and sorting
     * @return Page of sorted horse offers by price
     */
    Page<OfferViewModel> sortedHorseOffersByPrice(String filter, Pageable pageable);

    /**
     * Retrieves a page of sorted horse offers by date.
     *
     * @param filter   Filter to apply (if any)
     * @param pageable Pageable object for pagination and sorting
     * @return Page of sorted horse offers by date
     */
    Page<OfferViewModel> sortedHorseOffersByDate(String filter, Pageable pageable);

    /**
     * Retrieves a page of sorted horse offers by view count.
     *
     * @param filter   Filter to apply (if any)
     * @param pageable Pageable object for pagination and sorting
     * @return Page of sorted horse offers by view count
     */
    Page<OfferViewModel> sortedHorseOffersByView(String filter, Pageable pageable);

    /**
     * Retrieves a page of sorted accessories offers by price.
     *
     * @param filter   Filter to apply (if any)
     * @param pageable Pageable object for pagination and sorting
     * @return Page of sorted accessories offers by price
     */
    Page<OfferViewModel> sortedAccessoriesOffersByPrice(String filter, Pageable pageable);

    /**
     * Retrieves a page of sorted accessories offers by date.
     *
     * @param filter   Filter to apply (if any)
     * @param pageable Pageable object for pagination and sorting
     * @return Page of sorted accessories offers by date
     */
    Page<OfferViewModel> sortedAccessoriesOffersByDate(String filter, Pageable pageable);

    /**
     * Retrieves a page of sorted accessories offers by view count.
     *
     * @param filter   Filter to apply (if any)
     * @param pageable Pageable object for pagination and sorting
     * @return Page of sorted accessories offers by view count
     */
    Page<OfferViewModel> sortedAccessoriesOffersByView(String filter, Pageable pageable);

}
