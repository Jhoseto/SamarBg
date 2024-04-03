package org.samarBg.service.implementation;

import org.samarBg.model.entities.AccessoryOfferEntity;
import org.samarBg.model.entities.BaseEntity;
import org.samarBg.model.entities.HorseOfferEntity;
import org.samarBg.model.entities.OfferImageEntity;
import org.samarBg.model.entities.enums.OfferCategory;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.service.AddOffersService;
import org.samarBg.service.CurrentUserService;
import org.samarBg.view.AddAccessoriesViewModel;
import org.samarBg.view.AddOfferHorseViewModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AddOffersImpl {


    private final HorseOfferRepository horseOfferRepository;
    private final AccessoriesOfferRepository accessoriesOfferRepository;
    private final CurrentUserService currentUserService;


    public AddOffersImpl(HorseOfferRepository horseOfferRepository,
                         AccessoriesOfferRepository accessoriesOfferRepository,
                         CurrentUserService currentUserService) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;

        this.currentUserService = currentUserService;
    }

    @Bean
    public AddOffersService addOffersService (){
        return new AddOffersService() {
            @Override
            public Long addHorseOffer(AddOfferHorseViewModel addOfferHorseViewModel, List<String> imageUrls) {
                HorseOfferEntity horseOffer = new HorseOfferEntity();

                // Получаваме Имейл-а на текущия потребител от CurrentUserService
                String username = currentUserService.getCurrentUser().getUsername();

                List<OfferImageEntity> images = new ArrayList<>();
                for (String imageUrl : imageUrls) {
                    OfferImageEntity offerImage = new OfferImageEntity();
                    offerImage.setImagePath(imageUrl); // мапиране на пътя към снимката
                    offerImage.setHorseOffer(horseOffer); // Свързване на снимката с обявата за кон
                    images.add(offerImage);
                }

                horseOffer.setOfferName(addOfferHorseViewModel.getOfferName());
                horseOffer.setCategory(OfferCategory.HORSES);
                horseOffer.setBasicImageUrl(imageUrls.get(0)); // Променено получаването на основната снимка
                horseOffer.setImages(images);
                horseOffer.setHorseCategory(addOfferHorseViewModel.getHorseCategory());
                horseOffer.setSex(addOfferHorseViewModel.getSex());
                horseOffer.setPrice(addOfferHorseViewModel.getPrice());
                horseOffer.setPhone(addOfferHorseViewModel.getPhone());
                horseOffer.setHiddenPhone(addOfferHorseViewModel.isHiddenPhone());
                horseOffer.setCity(addOfferHorseViewModel.getCity());
                horseOffer.setDescription(addOfferHorseViewModel.getDescription());
                horseOffer.setIsActive(0);
                horseOffer.setVideoLink(addOfferHorseViewModel.getVideoLink());


                setCurrentTimeStamps(horseOffer);
                horseOffer.setAuthorName(username);

                horseOfferRepository.save(horseOffer);
                return horseOffer.getId();
            }

            @Override
            public Long addAccessoriesOffer(AddAccessoriesViewModel addAccessoriesViewModel, List<String> imageUrls) {
                AccessoryOfferEntity accessoriesOffer = new AccessoryOfferEntity();

                // Getting userName for current user from CurrentUserService
                String username = currentUserService.getCurrentUser().getUsername();

                List<OfferImageEntity> images = new ArrayList<>();
                for (String imageUrl : imageUrls) {
                    OfferImageEntity offerImage = new OfferImageEntity();
                    offerImage.setImagePath(imageUrl); // мапиране на пътя към снимката
                    offerImage.setAccessoryOffer(accessoriesOffer); // Свързване на снимката с обявата за кон
                    images.add(offerImage);
                }

                accessoriesOffer.setOfferName(addAccessoriesViewModel.getOfferName());
                accessoriesOffer.setCategory(OfferCategory.ACCESSORIES);
                accessoriesOffer.setBasicImageUrl(imageUrls.get(0)); // Променено получаването на основната снимка
                accessoriesOffer.setImages(images);
                accessoriesOffer.setAccessoriesCategory(addAccessoriesViewModel.getAccessoriesCategory());
                accessoriesOffer.setPrice(addAccessoriesViewModel.getPrice());
                accessoriesOffer.setPhone(addAccessoriesViewModel.getPhone());
                accessoriesOffer.setHiddenPhone(addAccessoriesViewModel.isHiddenPhone());
                accessoriesOffer.setCity(addAccessoriesViewModel.getCity());
                accessoriesOffer.setDescription(addAccessoriesViewModel.getDescription());
                accessoriesOffer.setIsActive(0);
                accessoriesOffer.setVideoLink(addAccessoriesViewModel.getVideoLink());

                setCurrentTimeStamps(accessoriesOffer);
                accessoriesOffer.setAuthorName(username);

                accessoriesOfferRepository.save(accessoriesOffer);
                return accessoriesOffer.getId();
            }

            @Override
            public List<String> uploadImages(List<MultipartFile> files) throws IOException {
                List<String> imageUrls = new ArrayList<>();
                int userOfferNumber = currentUserService.getCurrentUser().getUserOffersCount();

                for (MultipartFile file : files) {
                    if (file.getSize()!=0){
                        String fileName = userOfferNumber+"_"+UUID.randomUUID() + ".jpg";
                        String uploadDir = "F:\\MyProjects\\SamarBG\\SamarBg\\src/main\\resources\\static\\images\\offerImg\\";
                        Path path = Paths.get(uploadDir + fileName);
                        Files.write(path, file.getBytes());

                        imageUrls.add("/images/offerImg/" + fileName); // Добавяне на URL адреса на снимката в списъка
                    }
                }

                return imageUrls;
            }
        };
    }

    //Create and Update TimeStamp
    public static void setCurrentTimeStamps(BaseEntity baseEntity){
        baseEntity.setCreated(Instant.now());
        baseEntity.setModified(Instant.now());
    }
}
