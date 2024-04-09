package org.samarBg.service;

import org.samarBg.view.OfferViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SortedSearchingOffers {

    Page<OfferViewModel> sortedSearchingByWordAndCategory(String word,
                                                          String filter,
                                                          Pageable pageable,
                                                          String mainCategory,
                                                          String cityCategory,
                                                          String horseCategory,
                                                          String genderCategory,
                                                          String accessoriesCategory);


}
