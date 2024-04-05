package org.samarBg.service.Mappers;

import org.samarBg.model.entities.UserEntity;
import org.samarBg.view.UserProfileViewModel;
import org.springframework.stereotype.Service;

@Service
public class MapperForUsers {

    public UserProfileViewModel mapUserToProfileViewModel(UserEntity user) {
        UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
        // Мапиране на данни от UserEntity към UserProfileViewModel
        userProfileViewModel.setId(user.getId());
        userProfileViewModel.setUserName(user.getUsername());
        userProfileViewModel.setEmail(user.getEmail());
        userProfileViewModel.setCity(user.getCity());
        userProfileViewModel.setRealName(user.getRealName());
        userProfileViewModel.setLastOnline(user.getLastOnline());
        userProfileViewModel.setPhone(user.getPhone());
        userProfileViewModel.setProfileImageUrl(user.getImageUrl());

        return userProfileViewModel;
    }
}
