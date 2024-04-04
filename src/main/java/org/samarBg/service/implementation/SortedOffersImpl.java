package org.samarBg.service.implementation;

import org.samarBg.service.OfferService;
import org.samarBg.service.SortedOfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
public class SortedOffersImpl implements SortedOfferService {

    private final OfferService offerService;

    public SortedOffersImpl(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public List<OfferViewModel> sortedOffersByPrice(String filter) {
        List<OfferViewModel> allOffersByPrice = offerService.getAllOffers();
        // компаратор за сортиране по цена
        Comparator<OfferViewModel> priceComparator = Comparator.comparing(OfferViewModel::getPrice);

        // Ако filter е "Expensive", сортираме в обратен ред
        if ("Expensive".equals(filter)) {
            allOffersByPrice.sort(priceComparator.reversed());
        } else {
            //  сортираме във възходящ ред (най-евтините първи)
            allOffersByPrice.sort(priceComparator);
        }

        return allOffersByPrice;
    }

    @Override
    public List<OfferViewModel> sortedOffersByDate(String filter) {
        List<OfferViewModel> allOffersByDate = offerService.getAllOffers();

        Comparator<OfferViewModel> dateComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        if ("new".equals(filter)) {
            allOffersByDate.sort(dateComparator.reversed());
        } else {
            allOffersByDate.sort(dateComparator);
        }
        return allOffersByDate;
    }

    @Override
    public List<OfferViewModel> sortedOffersByView(String filter) {
        List<OfferViewModel> allOffersByView = offerService.getAllOffers();
        Comparator<OfferViewModel> viewComparator = Comparator.comparing(OfferViewModel::getCreateDate);

        allOffersByView.sort(viewComparator);
        return allOffersByView;
    }
}
