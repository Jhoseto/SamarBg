package org.samarBg.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.samarBg.models.AccessoryOfferEntity;
import org.samarBg.models.HorseOfferEntity;
import org.samarBg.models.UserEntity;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.service.UserService;
import org.samarBg.service.serviceImpl.AddOffersImpl;
import org.samarBg.views.AddAccessoriesViewModel;
import org.samarBg.views.AddOfferHorseViewModel;

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
    private UserService userService;

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
        when(userService.getCurrentUser()).thenReturn(mockUser);

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
        when(userService.getCurrentUser()).thenReturn(mockUser);

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