package org.samarBg.service.serviceImpl;

import org.samarBg.service.OfferService;
import org.samarBg.service.SortedSearchingOffers;
import org.samarBg.view.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SortedSearchingOffersImpl implements SortedSearchingOffers {

    private final OfferService offerService;

    public SortedSearchingOffersImpl(OfferService offerService) {
        this.offerService = offerService;
    }


    @Override
    public Page<OfferViewModel> sortedSearchingByWord(String word, String filter, Pageable pageable) {
        List<OfferViewModel> searchingOffers = offerService.getOffersBySearchingWord(word);
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
            default: // Default sorting By Date
                comparator = Comparator.comparing(OfferViewModel::getCreateDate).reversed();
                break;
        }

        List<OfferViewModel> sortedOffers = searchingOffers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        // Pagination
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }
}
