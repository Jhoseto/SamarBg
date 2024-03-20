package org.samarBg;

import org.samarBg.model.entities.*;
import org.samarBg.model.entities.enums.*;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Component
public class DBInit implements CommandLineRunner {
    private HorseOfferRepository horseOfferRepository;
    private AccessoriesOfferRepository accessoriesOfferRepository;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;



    public DBInit(HorseOfferRepository horseOfferRepository,
                  AccessoriesOfferRepository accessoriesOfferRepository,
                  UserRepository userRepository,
                  UserRoleRepository userRoleRepository,
                  PasswordEncoder passwordEncoder) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public void run(String... args) throws Exception {

        //intHorseOffer();
       // intUsers();
    }

    public static void setCurrentTimeStamps(BaseEntity baseEntity){
        baseEntity.setCreated(Instant.now());
        baseEntity.setModified(Instant.now());
    }


    private void intUsers(){
        UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
        userRoleRepository.saveAll(List.of(adminRole,userRole));

        UserEntity admin = new UserEntity();
        admin.setUsername("Konstantin")
                .setPassword(passwordEncoder.encode("Pass12"))
                .setEmail("konstantinse33@gmail.com")
                .setImageUrl("images/usersImg/konstantinImg.jpg")
                .setCity(CityEnum.valueOf(String.valueOf(CityEnum.SMOLYAN)))
                .setPhone("0886913296")
                .setUserRoles(Collections.singleton(UserRoleEnum.USER));
        setCurrentTimeStamps(admin);
        userRepository.save(admin);
    }


    private void intHorseOffer(){
        HorseOfferEntity horseOffer = new HorseOfferEntity();
        List<OfferImageEntity> images = new ArrayList<>();
        OfferImageEntity image = new OfferImageEntity();

        image.setImagePath("images/offerImg/horse1.jpg");
        image.setHorseOffer(horseOffer);
        images.add(image);

        horseOffer.setImageUrl("images/offerImg/horse1.jpg");

        horseOffer.setOfferName("Продавам мъжки кон. Изгодно !!!")
                .setCategory(OfferCategoryEnum.HORSES)
                .setHorseCategory(HorseCategoryEnum.HORSE)
                .setSex(SexEnum.MALE)
                .setAuthorName("Bai Marin")
                .setCity(CityEnum.RAZGRAD)
                .setPhone("0886913296")
                .setPrice(BigDecimal.valueOf(2100))
                .setDescription("Много здрав и красив кон. Гледал съм го като писано яйце. " +
                        "Хранен е само с италиански салати и сме го поили на Балиевата вОда. " +
                        "Към него подарявам бонус 5 човала фураж !")
                .setImages(images);
        setCurrentTimeStamps(horseOffer);
        horseOfferRepository.save(horseOffer);

    }


}
