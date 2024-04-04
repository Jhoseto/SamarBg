package org.samarBg.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.samarBg.model.entities.*;
import org.samarBg.model.entities.enums.OfferCategory;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.service.CurrentUserService;
import org.samarBg.service.implementation.AddOffersImpl;
import org.samarBg.view.AddAccessoriesViewModel;
import org.samarBg.view.AddOfferHorseViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddOffersImplTest {

    @Mock
    private HorseOfferRepository horseOfferRepository;

    @Mock
    private AccessoriesOfferRepository accessoriesOfferRepository;

    @Mock
    private CurrentUserService currentUserService;

    @InjectMocks
    private AddOffersImpl addOffersService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddHorseOffer() {
        // Mocking current user service
        UserEntity mockUser = new UserEntity(/* Mock user details */);
        when(currentUserService.getCurrentUser()).thenReturn(mockUser);

        // Mocking view model and file list
        AddOfferHorseViewModel viewModel = new AddOfferHorseViewModel();
        List<String> imageUrls = new ArrayList<>();

        // Mocking repository method
        when(horseOfferRepository.save(any(HorseOfferEntity.class))).thenReturn(new HorseOfferEntity());

        // Calling the method under test
        Long offerId = addOffersService.addHorseOffer(viewModel, imageUrls);

        // Verifying that the repository method was called
        verify(horseOfferRepository, times(1)).save(any(HorseOfferEntity.class));

        // Asserting the returned offer ID
        assertEquals(offerId, Long.valueOf(0)); // Change the expected ID according to your test case
    }

    @Test
    public void testAddAccessoriesOffer() {
        // Mocking current user service
        UserEntity mockUser = new UserEntity(/* Mock user details */);
        when(currentUserService.getCurrentUser()).thenReturn(mockUser);

        // Mocking view model and file list
        AddAccessoriesViewModel viewModel = new AddAccessoriesViewModel();
        List<String> imageUrls = new ArrayList<>();

        // Mocking repository method
        when(accessoriesOfferRepository.save(any(AccessoryOfferEntity.class))).thenReturn(new AccessoryOfferEntity());

        // Calling the method under test
        Long offerId = addOffersService.addAccessoriesOffer(viewModel, imageUrls);

        // Verifying that the repository method was called
        verify(accessoriesOfferRepository, times(1)).save(any(AccessoryOfferEntity.class));

        // Asserting the returned offer ID
        assertEquals(offerId, Long.valueOf(0)); // Change the expected ID according to your test case
    }
}