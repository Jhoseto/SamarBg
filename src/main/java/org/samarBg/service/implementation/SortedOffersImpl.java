package org.samarBg.service.implementation;

import org.samarBg.service.SortedOfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
public class SortedOffersImpl implements SortedOfferService {

    private final OfferServiceImpl offerServiceImpl;

    public SortedOffersImpl(OfferServiceImpl offerServiceImpl) {
        this.offerServiceImpl = offerServiceImpl;
    }

    @Override
    public List<OfferViewModel> sortedOffersByPrice(String filter) {
        List<OfferViewModel> allOffersByPrice = offerServiceImpl.getAllOffers();
        // компаратор за сортиране по цена
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);

        // Ако filter е "Expensive", сортираме в обратен ред
        if ("Expensive".equals(filter)) {
            allOffersByPrice.sort(priceComparator.reversed());
        } else if ("Cheap".equals(filter)){
            //  сортираме във възходящ ред (най-евтините първи)
            allOffersByPrice.sort(priceComparator);
        }
        return allOffersByPrice;
    }

    @Override
    public List<OfferViewModel> sortedOffersByDate(String filter) {
        List<OfferViewModel> allOffersByDate = offerServiceImpl.getAllOffers();
        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        if ("new".equalsIgnoreCase(filter)) {
            allOffersByDate.sort(dateComparator.reversed());
        } else if ("old".equalsIgnoreCase(filter)) {
            allOffersByDate.sort(dateComparator);
        }
        return allOffersByDate;
    }

    @Override
    public List<OfferViewModel> sortedOffersByView(String filter) {
        List<OfferViewModel> allOffersByView = offerServiceImpl.getAllOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        allOffersByView.sort(viewComparator);
        return allOffersByView;
    }

    @Override
    public List<OfferViewModel> sortedHorseOffersByPrice(String filter) {
        List<OfferViewModel> allHorseOffersByPrice = offerServiceImpl.getAllHorsesOffers();
        // компаратор за сортиране по цена
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);

        // Ако filter е "Expensive", сортираме в обратен ред
        if ("Expensive".equals(filter)) {
            allHorseOffersByPrice.sort(priceComparator.reversed());
        } else if ("Cheap".equals(filter)){
            //  сортираме във възходящ ред (най-евтините първи)
            allHorseOffersByPrice.sort(priceComparator);
        }
        return allHorseOffersByPrice;
    }

    @Override
    public List<OfferViewModel> sortedHorseOffersByDate(String filter) {
        List<OfferViewModel> allHorseOffersByDate = offerServiceImpl.getAllHorsesOffers();
        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        if ("new".equalsIgnoreCase(filter)) {
            allHorseOffersByDate.sort(dateComparator.reversed());
        } else if ("old".equalsIgnoreCase(filter)) {
            allHorseOffersByDate.sort(dateComparator);
        }
        return allHorseOffersByDate;
    }

    @Override
    public List<OfferViewModel> sortedHorseOffersByView(String filter) {
        List<OfferViewModel> allHorseOffersByView = offerServiceImpl.getAllHorsesOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        allHorseOffersByView.sort(viewComparator);
        return allHorseOffersByView;
    }

    @Override
    public List<OfferViewModel> sortedAccessoriesOffersByPrice(String filter) {
        List<OfferViewModel> allAccessoriesOffersByPrice = offerServiceImpl.getAllAccessoryOffers();
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);

        if ("Expensive".equals(filter)) {
            allAccessoriesOffersByPrice.sort(priceComparator.reversed());
        } else if ("Cheap".equals(filter)){
            allAccessoriesOffersByPrice.sort(priceComparator);
        }
        return allAccessoriesOffersByPrice;
    }

    @Override
    public List<OfferViewModel> sortedAccessoriesOffersByDate(String filter) {
        List<OfferViewModel> allAccessoriesHorseOffersByDate = offerServiceImpl.getAllAccessoryOffers();
        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        if ("new".equalsIgnoreCase(filter)) {
            allAccessoriesHorseOffersByDate.sort(dateComparator.reversed());
        } else if ("old".equalsIgnoreCase(filter)) {
            allAccessoriesHorseOffersByDate.sort(dateComparator);
        }
        return allAccessoriesHorseOffersByDate;
    }

    @Override
    public List<OfferViewModel> sortedAccessoriesOffersByView(String filter) {
        List<OfferViewModel> allAccessoriesHorseOffersByView = offerServiceImpl.getAllAccessoryOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        allAccessoriesHorseOffersByView.sort(viewComparator);
        return allAccessoriesHorseOffersByView;
    }
}
