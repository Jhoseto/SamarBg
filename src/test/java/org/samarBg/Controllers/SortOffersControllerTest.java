package org.samarBg.Controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.samarBg.controllers.SortOffersController;
import org.samarBg.service.OfferService;
import org.samarBg.service.SortedOfferService;
import org.samarBg.view.OfferViewModel;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SortOffersControllerTest {

    @Mock
    private SortedOfferService sortedOfferService;

    @Mock
    private OfferService offerService;

    @Mock
    private Model model;

    @InjectMocks
    private SortOffersController sortOffersController;

    @Test
    void sortAllOffersTest() {
        MockitoAnnotations.initMocks(this);

        List<OfferViewModel> mockOffers = new ArrayList<>();
        // Populate mockOffers with dummy data

        when(offerService.getAllOffers()).thenReturn(mockOffers);

        // Test sorting by New
        when(sortedOfferService.sortedOffersByDate("New")).thenReturn(mockOffers);
        String result = sortOffersController.sortAllOffers("New", model);
        assertEquals("allOffers", result);

        // Test sorting by Old
        when(sortedOfferService.sortedOffersByDate("Old")).thenReturn(mockOffers);
        result = sortOffersController.sortAllOffers("Old", model);
        assertEquals("allOffers", result);

        // Test sorting by Favorites
        when(sortedOfferService.sortedOffersByView("Favorites")).thenReturn(mockOffers);
        result = sortOffersController.sortAllOffers("Favorites", model);
        assertEquals("allOffers", result);

        // Test sorting by Expensive
        when(sortedOfferService.sortedOffersByPrice("Expensive")).thenReturn(mockOffers);
        result = sortOffersController.sortAllOffers("Expensive", model);
        assertEquals("allOffers", result);

        // Test sorting by Cheap
        when(sortedOfferService.sortedOffersByPrice("Cheap")).thenReturn(mockOffers);
        result = sortOffersController.sortAllOffers("Cheap", model);
        assertEquals("allOffers", result);

        // Test default sorting
        result = sortOffersController.sortAllOffers("NonExistingFilter", model);
        assertEquals("allOffers", result);

        // Verify that the appropriate service method is called
        verify(sortedOfferService, times(2)).sortedOffersByDate(anyString());
        verify(sortedOfferService, times(2)).sortedOffersByPrice(anyString());
        verify(sortedOfferService, times(1)).sortedOffersByView(anyString());
        verify(offerService, times(1)).getAllOffers();
    }
}
