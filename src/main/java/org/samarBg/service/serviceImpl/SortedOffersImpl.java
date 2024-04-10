package org.samarBg.service.serviceImpl;

import org.samarBg.service.OfferService;
import org.samarBg.service.SortedOfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link SortedOfferService} for sorting and paginating offer listings.
 */
@Service
public class SortedOffersImpl implements SortedOfferService {

    private final OfferService offerService;

    /**
     * Constructs a new SortedOffersImpl instance with the specified OfferService.
     *
     * @param offerService the OfferService to be used
     */
    public SortedOffersImpl(OfferService offerService) {
        this.offerService = offerService;
    }


    /**
     * Retrieves and sorts all offers by price, applying pagination.
     *
     * @param filter   the filter criteria ("Expensive" or "Cheap")
     * @param pageable the pagination information
     * @return a {@link Page} of sorted {@link OfferViewModel} objects based on price
     */
    @Override
    public Page<OfferViewModel> sortedOffersByPrice(String filter, Pageable pageable) {
        List<OfferViewModel> allOffers = offerService.getAllOffers();
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);
        List<OfferViewModel> sortedOffers = allOffers.stream()
                .sorted("Expensive".equals(filter) ? priceComparator.reversed() : priceComparator)
                .collect(Collectors.toList());
        return getPage(sortedOffers, pageable);
    }


    /**
     * Retrieves and sorts all offers by date, applying pagination.
     *
     * @param filter   the filter criteria ("New" or "Old")
     * @param pageable the pagination information
     * @return a {@link Page} of sorted {@link OfferViewModel} objects based on date
     */
    @Override
    public Page<OfferViewModel> sortedOffersByDate(String filter, Pageable pageable) {
        List<OfferViewModel> allOffersByDate = offerService.getAllOffers();
        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);
        List<OfferViewModel> sortedOffers = allOffersByDate.stream()
                .sorted("New".equals(filter) ? dateComparator.reversed() : dateComparator)
                .collect(Collectors.toList());
        return getPage(sortedOffers, pageable);
    }


    /**
     * Retrieves and sorts all offers by view count, applying pagination.
     *
     * @param filter   the filter criteria (not used for this method)
     * @param pageable the pagination information
     * @return a {@link Page} of sorted {@link OfferViewModel} objects based on view count
     */
    @Override
    public Page<OfferViewModel> sortedOffersByView(String filter, Pageable pageable) {
        List<OfferViewModel> allOffersByView = offerService.getAllOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getOfferViewCount);
        List<OfferViewModel> sortedOffers = allOffersByView.stream()
                .sorted(viewComparator.reversed())
                .collect(Collectors.toList());
        return getPage(sortedOffers, pageable);
    }


    /**
     * Retrieves and sorts all horse offers by price, applying pagination.
     *
     * @param filter   the filter criteria ("Expensive" or "Cheap")
     * @param pageable the pagination information
     * @return a {@link Page} of sorted {@link OfferViewModel} objects based on price
     */
    @Override
    public Page<OfferViewModel> sortedHorseOffersByPrice(String filter, Pageable pageable) {
        List<OfferViewModel> allHorseOffersByPrice = offerService.getAllHorsesOffers();
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);
        List<OfferViewModel> sortedOffers = allHorseOffersByPrice.stream()
                .sorted("Expensive".equals(filter) ? priceComparator.reversed() : priceComparator)
                .collect(Collectors.toList());
        return getPage(sortedOffers, pageable);
    }


    /**
     * Retrieves and sorts all horse offers by date, applying pagination.
     *
     * @param filter   the filter criteria ("New" or "Old")
     * @param pageable the pagination information
     * @return a {@link Page} of sorted {@link OfferViewModel} objects based on date
     */
    @Override
    public Page<OfferViewModel> sortedHorseOffersByDate(String filter, Pageable pageable) {
        List<OfferViewModel> allHorseOffersByDate = offerService.getAllHorsesOffers();
        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);
        List<OfferViewModel> sortedOffers = allHorseOffersByDate.stream()
                .sorted("New".equals(filter) ? dateComparator.reversed() : dateComparator)
                .collect(Collectors.toList());
        return getPage(sortedOffers, pageable);
    }


    /**
     * Retrieves and sorts all horse offers by view count, applying pagination.
     *
     * @param filter   the filter criteria (not used for this method)
     * @param pageable the pagination information
     * @return a {@link Page} of sorted {@link OfferViewModel} objects based on view count
     */
    @Override
    public Page<OfferViewModel> sortedHorseOffersByView(String filter, Pageable pageable) {
        List<OfferViewModel> allHorseOffersByView = offerService.getAllHorsesOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getOfferViewCount);
        List<OfferViewModel> sortedOffers = allHorseOffersByView.stream()
                .sorted(viewComparator.reversed())
                .collect(Collectors.toList());
        return getPage(sortedOffers, pageable);
    }


    /**
     * Retrieves and sorts all accessories offers by price, applying pagination.
     *
     * @param filter   the filter criteria ("Expensive" or "Cheap")
     * @param pageable the pagination information
     * @return a {@link Page} of sorted {@link OfferViewModel} objects based on price
     */
    @Override
    public Page<OfferViewModel> sortedAccessoriesOffersByPrice(String filter, Pageable pageable) {
        List<OfferViewModel> allAccessoriesOffersByPrice = offerService.getAllAccessoryOffers();
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);
        List<OfferViewModel> sortedOffers = allAccessoriesOffersByPrice.stream()
                .sorted("Expensive".equals(filter) ? priceComparator.reversed() : priceComparator)
                .collect(Collectors.toList());
        return getPage(sortedOffers, pageable);
    }


    /**
     * Retrieves and sorts all accessories offers by date, applying pagination.
     *
     * @param filter   the filter criteria ("New" or "Old")
     * @param pageable the pagination information
     * @return a {@link Page} of sorted {@link OfferViewModel} objects based on date
     */
    @Override
    public Page<OfferViewModel> sortedAccessoriesOffersByDate(String filter, Pageable pageable) {
        List<OfferViewModel> allAccessoriesHorseOffersByDate = offerService.getAllAccessoryOffers();
        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);
        List<OfferViewModel> sortedOffers = allAccessoriesHorseOffersByDate.stream()
                .sorted("New".equals(filter) ? dateComparator.reversed() : dateComparator)
                .collect(Collectors.toList());
        return getPage(sortedOffers, pageable);
    }


    /**
     * Retrieves and sorts all accessories offers by view count, applying pagination.
     *
     * @param filter   the filter criteria (not used for this method)
     * @param pageable the pagination information
     * @return a {@link Page} of sorted {@link OfferViewModel} objects based on view count
     */
    @Override
    public Page<OfferViewModel> sortedAccessoriesOffersByView(String filter, Pageable pageable) {
        List<OfferViewModel> allAccessoriesHorseOffersByView = offerService.getAllAccessoryOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getOfferViewCount);
        List<OfferViewModel> sortedOffers = allAccessoriesHorseOffersByView.stream()
                .sorted(viewComparator.reversed())
                .collect(Collectors.toList());
        return getPage(sortedOffers, pageable);
    }


    /**
     * Helper method to paginate a list of OfferViewModel objects.
     *
     * @param offers   the list of offers to paginate
     * @param pageable the pagination information
     * @return a {@link Page} of {@link OfferViewModel} objects
     */
    private Page<OfferViewModel> getPage(List<OfferViewModel> offers, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), offers.size());
        List<OfferViewModel> pagedOffers = offers.subList(start, end);
        return new PageImpl<>(pagedOffers, pageable, offers.size());
    }
}
