package org.samarBg.service;

import org.samarBg.view.OfferViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SortedOfferService {
    List<OfferViewModel> sortedOffersByPrice(String filter);
    List<OfferViewModel> sortedOffersByDate(String filter);
    List<OfferViewModel> sortedOffersByView(String filter);
}
