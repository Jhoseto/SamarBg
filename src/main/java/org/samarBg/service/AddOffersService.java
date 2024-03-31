package org.samarBg.service;

import org.samarBg.model.entities.BaseEntity;
import org.samarBg.model.entities.HorseOfferEntity;
import org.samarBg.model.entities.OfferImageEntity;
import org.samarBg.model.entities.enums.OfferCategoryEnum;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.view.AddOfferHorseViewModel;
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
public class AddOffersService {


    private final HorseOfferRepository horseOfferRepository;
    private final AccessoriesOfferRepository accessoriesOfferRepository;
    private final CurrentUserService currentUserService;


    public AddOffersService(HorseOfferRepository horseOfferRepository,
                            AccessoriesOfferRepository accessoriesOfferRepository,
                            CurrentUserService currentUserService) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;

        this.currentUserService = currentUserService;
    }

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
        horseOffer.setCategory(OfferCategoryEnum.HORSES);
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

        // запазване на обявата в репозиторито
//       entity e = mapper.map(dto/model, user)
//        e.setuser
        horseOfferRepository.save(horseOffer);

        return horseOffer.getId();
    }


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


    //Времето на създаване и ъпдейтване
    public static void setCurrentTimeStamps(BaseEntity baseEntity){
        baseEntity.setCreated(Instant.now());
        baseEntity.setModified(Instant.now());
    }
}
