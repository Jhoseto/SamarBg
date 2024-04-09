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
    public Page<OfferViewModel> sortedSearchingByWordAndCategory(String word, String filter, Pageable pageable,
                                                                 String mainCategory, String cityCategory,
                                                                 String horseCategory, String genderCategory,
                                                                 String accessoriesCategory) {
        List<OfferViewModel> searchingOffers = offerService.getOffersBySearchingWord(word);

        // Filtering by MainCategory (if selected)
        if (!mainCategory.equals("none")  && !mainCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> offer.getOfferCategory().toString().equals(mainCategory))
                    .collect(Collectors.toList());
        }

        // Filtering by City (if selected)
        if (!cityCategory.equals("none") && !cityCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> offer.getCity().toString().equalsIgnoreCase(cityCategory))
                    .collect(Collectors.toList());
        }

        // Filtering by HorseCategory (if selected)
        if (!horseCategory.equals("none") && !horseCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> {
                        HorseCategory offerHorseCategory = offer.getHorseCategory();
                        return offerHorseCategory != null && offerHorseCategory.toString().equals(horseCategory);
                    })
                    .collect(Collectors.toList());
        }

        // Filtering by GenderCategory (if selected)
        if (!genderCategory.equals("none") && !genderCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> {
                        Sex offerSex = offer.getSex();
                        return offerSex != null && offerSex.toString().equalsIgnoreCase(genderCategory);
                    })
                    .collect(Collectors.toList());
        }

        // Filtering by accessoriesCategory (if selected)
        if (!accessoriesCategory.equals("none") && !accessoriesCategory.isEmpty()) {
            searchingOffers = searchingOffers.stream()
                    .filter(offer -> {
                        AccessoriesCategory offerAccessoriesCategory = offer.getAccessoriesCategory();
                        return offerAccessoriesCategory != null && offerAccessoriesCategory.toString().equalsIgnoreCase(accessoriesCategory);
                    })
                    .collect(Collectors.toList());
        }

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
