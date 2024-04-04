package org.samarBg.service;

import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SortedOfferService {
    /**
     * Sorts the offers by price.
     *
     * @param filter the filter for the offers (e.g., "ascending", "descending")
     * @return a list of sorted offers by price
     */
    List<OfferViewModel> sortedOffersByPrice(String filter);

    /**
     * Sorts the offers by date.
     *
     * @param filter the filter for the offers (e.g., "new", "old")
     * @return a list of sorted offers by date
     */
    List<OfferViewModel> sortedOffersByDate(String filter);

    /**
     * Sorts the offers by number of views.
     *
     * @param filter the filter for the offers (e.g., "most-viewed", "least-viewed")
     * @return a list of sorted offers by number of views
     */
    List<OfferViewModel> sortedOffersByView(String filter);

    /**
     * Sorts the horse offers by price.
     *
     * @param filter the filter for the offers (e.g., "ascending", "descending")
     * @return a list of sorted horse offers by price
     */
    List<OfferViewModel> sortedHorseOffersByPrice(String filter);

    /**
     * Sorts the horse offers by date.
     *
     * @param filter the filter for the offers (e.g., "new", "old")
     * @return a list of sorted horse offers by date
     */
    List<OfferViewModel> sortedHorseOffersByDate(String filter);

    /**
     * Sorts the horse offers by number of views.
     *
     * @param filter the filter for the offers (e.g., "most-viewed", "least-viewed")
     * @return a list of sorted horse offers by number of views
     */
    List<OfferViewModel> sortedHorseOffersByView(String filter);

    /**
     * Sorts the accessories offers by price.
     *
     * @param filter the filter for the offers (e.g., "ascending", "descending")
     * @return a list of sorted accessories offers by price
     */
    List<OfferViewModel> sortedAccessoriesOffersByPrice(String filter);

    /**
     * Sorts the accessories offers by date.
     *
     * @param filter the filter for the offers (e.g., "new", "old")
     * @return a list of sorted accessories offers by date
     */
    List<OfferViewModel> sortedAccessoriesOffersByDate(String filter);

    /**
     * Sorts the accessories offers by number of views.
     *
     * @param filter the filter for the offers (e.g., "most-viewed", "least-viewed")
     * @return a list of sorted accessories offers by number of views
     */
    List<OfferViewModel> sortedAccessoriesOffersByView(String filter);
}
