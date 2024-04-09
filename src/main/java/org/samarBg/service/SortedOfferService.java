package org.samarBg.service;

import org.samarBg.view.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SortedOfferService {

    Page<OfferViewModel> sortedOffersByPrice(String filter, Pageable pageable);


    Page<OfferViewModel> sortedOffersByDate(String filter, Pageable pageable);


    Page<OfferViewModel> sortedOffersByView(String filter, Pageable pageable);


    Page<OfferViewModel> sortedHorseOffersByPrice(String filter, Pageable pageable);


    Page<OfferViewModel> sortedHorseOffersByDate(String filter, Pageable pageable);


    Page<OfferViewModel> sortedHorseOffersByView(String filter, Pageable pageable);


    Page<OfferViewModel> sortedAccessoriesOffersByPrice(String filter, Pageable pageable);


    Page<OfferViewModel> sortedAccessoriesOffersByDate(String filter, Pageable pageable);


    Page<OfferViewModel> sortedAccessoriesOffersByView(String filter, Pageable pageable);


}
