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

@Service
public class SortedOffersImpl implements SortedOfferService {

    private final OfferService offerService;

    public SortedOffersImpl(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public Page<OfferViewModel> sortedOffersByPrice(String filter, Pageable pageable) {
        // Извличане на всички обяви с пагинация
        List<OfferViewModel> allOffers = offerService.getAllOffers();

        // Сортиране на обявите спрямо цената
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);
        List<OfferViewModel> sortedOffers = allOffers.stream()
                .sorted("Expensive".equals(filter) ? priceComparator.reversed() : priceComparator)
                .collect(Collectors.toList());

        // Подготвяне на нова страница със сортираните обяви
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }



    @Override
    public Page<OfferViewModel> sortedOffersByDate(String filter,Pageable pageable) {
        List<OfferViewModel> allOffersByDate = offerService.getAllOffers();
        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        List<OfferViewModel> sortedOffers = allOffersByDate.stream()
                .sorted("New".equals(filter) ? dateComparator.reversed() : dateComparator)
                .collect(Collectors.toList());
        // Създаваме нов Page със сортираните обяви
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }

    @Override
    public Page<OfferViewModel> sortedOffersByView(String filter, Pageable pageable) {
        List<OfferViewModel> allOffersByView = offerService.getAllOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getOfferViewCount);

        List<OfferViewModel> sortedOffers = allOffersByView.stream()
                .sorted(viewComparator.reversed())
                .collect(Collectors.toList());
        // Създаваме нов Page със сортираните обяви
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }

    @Override
    public Page<OfferViewModel> sortedHorseOffersByPrice(String filter, Pageable pageable) {
        List<OfferViewModel> allHorseOffersByPrice = offerService.getAllHorsesOffers();
        // компаратор за сортиране по цена
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);

        List<OfferViewModel> sortedOffers = allHorseOffersByPrice.stream()
                .sorted("Expensive".equals(filter) ? priceComparator.reversed() : priceComparator)
                .collect(Collectors.toList());
        // Създаваме нов Page със сортираните обяви
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }

    @Override
    public Page<OfferViewModel> sortedHorseOffersByDate(String filter, Pageable pageable) {
        List<OfferViewModel> allHorseOffersByDate = offerService.getAllHorsesOffers();
        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        List<OfferViewModel> sortedOffers = allHorseOffersByDate.stream()
                .sorted("Expensive".equals(filter) ? dateComparator.reversed() : dateComparator)
                .collect(Collectors.toList());
        // Създаваме нов Page със сортираните обяви
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }

    @Override
    public Page<OfferViewModel> sortedHorseOffersByView(String filter, Pageable pageable) {
        List<OfferViewModel> allHorseOffersByView = offerService.getAllHorsesOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getOfferViewCount);

        List<OfferViewModel> sortedOffers = allHorseOffersByView.stream()
                .sorted(viewComparator.reversed())
                .collect(Collectors.toList());
        // Създаваме нов Page със сортираните обяви
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, allHorseOffersByView.size());
    }

    @Override
    public Page<OfferViewModel> sortedAccessoriesOffersByPrice(String filter, Pageable pageable) {
        List<OfferViewModel> allAccessoriesOffersByPrice = offerService.getAllAccessoryOffers();
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);

        List<OfferViewModel> sortedOffers = allAccessoriesOffersByPrice.stream()
                .sorted("Expensive".equals(filter) ? priceComparator.reversed() : priceComparator)
                .collect(Collectors.toList());
        // Създаваме нов Page със сортираните обяви
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }

    @Override
    public Page<OfferViewModel> sortedAccessoriesOffersByDate(String filter, Pageable pageable) {
        List<OfferViewModel> allAccessoriesHorseOffersByDate = offerService.getAllAccessoryOffers();
        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        List<OfferViewModel> sortedOffers = allAccessoriesHorseOffersByDate.stream()
                .sorted("Expensive".equals(filter) ? dateComparator.reversed() : dateComparator)
                .collect(Collectors.toList());
        // Създаваме нов Page със сортираните обяви
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }

    @Override
    public Page<OfferViewModel> sortedAccessoriesOffersByView(String filter, Pageable pageable) {
        List<OfferViewModel> allAccessoriesHorseOffersByView = offerService.getAllAccessoryOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getOfferViewCount);

        List<OfferViewModel> sortedOffers = allAccessoriesHorseOffersByView.stream()
                .sorted(viewComparator.reversed())
                .collect(Collectors.toList());
        // Създаваме нов Page със сортираните обяви
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedOffers.size());
        List<OfferViewModel> pagedOffers = sortedOffers.subList(start, end);

        return new PageImpl<>(pagedOffers, pageable, sortedOffers.size());
    }
}
