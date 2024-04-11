package org.samarBg;

import org.samarBg.models.*;
import org.samarBg.models.enums.*;
import org.samarBg.repository.AccessoriesOfferRepository;
import org.samarBg.repository.HorseOfferRepository;
import org.samarBg.repository.UserRepository;
import org.samarBg.repository.UserRoleRepository;
import org.samarBg.service.UserService;
import org.springframework.boot.CommandLineRunner;
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
    private UserService userService;



    public DBInit(HorseOfferRepository horseOfferRepository,
                  AccessoriesOfferRepository accessoriesOfferRepository,
                  UserRepository userRepository,
                  UserRoleRepository userRoleRepository,
                  PasswordEncoder passwordEncoder,
                  UserService userService) {
        this.horseOfferRepository = horseOfferRepository;
        this.accessoriesOfferRepository = accessoriesOfferRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }




    @Override
    public void run(String... args) throws Exception {

        //intHorseOffer();
       // intUsers();
        //userService.promoteUserToAdmin("Bai Marin");
    }

    public static void setCurrentTimeStamps(BaseEntity baseEntity){
        baseEntity.setCreated(Instant.now());
        baseEntity.setModified(Instant.now());
    }


    private void intUsers(){
        UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);
        userRoleRepository.saveAll(List.of(adminRole,userRole));

        UserEntity admin = new UserEntity();
        admin.setUsername("Konstantin")
                .setPassword(passwordEncoder.encode("Pass12"))
                .setEmail("konstantinse33@gmail.com")
                .setImageUrl("images/usersImg/konstantinImg.jpg")
                .setCity(City.valueOf(String.valueOf(City.SMOLYAN)))
                .setPhone("0886913296")
                .setUserRoles(Collections.singleton(UserRole.USER));
        setCurrentTimeStamps(admin);
        userRepository.save(admin);
    }


    private void intHorseOffer(){
        HorseOfferEntity horseOffer = new HorseOfferEntity();
        List<OfferImageEntity> images = new ArrayList<>();
        OfferImageEntity image = new OfferImageEntity();

        image.setImagePath("/images/offerImg/horse1.jpg");
        image.setHorseOffer(horseOffer);
        images.add(image);

        horseOffer.setBasicImageUrl("/images/offerImg/horse1.jpg");

        horseOffer.setOfferName("Продавам мъжки кон. Изгодно !!!")
                .setCategory(OfferCategory.HORSES)
                .setHorseCategory(HorseCategory.HORSE)
                .setSex(Sex.MALE)
                .setAuthorName("Jhose")
                .setCity(City.RAZGRAD)
                .setPhone("0886913296")
                .setHiddenPhone(false)
                .setOfferViewCounter(0)
                .setPrice(BigDecimal.valueOf(2100))
                .setDescription("Много здрав и красив кон. Гледал съм го като писано яйце. " +
                        "Хранен е само с италиански салати и сме го поили на Балиевата вОда. " +
                        "Към него подарявам бонус 5 човала фураж !")
                .setImages(images)
                .setVideoLink("https://www.youtube.com/watch?v=a0ub4S0eGPE")
                .setIsActive(1)
                .setOfferViewCounter(0);

        setCurrentTimeStamps(horseOffer);
        horseOfferRepository.save(horseOffer);

    }


}
