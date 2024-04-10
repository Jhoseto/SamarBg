package org.samarBg.service.Mappers;

import org.samarBg.models.UserEntity;
import org.samarBg.views.UserProfileViewModel;
import org.springframework.stereotype.Service;

/**
 * MapperForUsers is a service class responsible for mapping UserEntity to UserProfileViewModel.
 * This mapper converts a UserEntity object into its corresponding UserProfileViewModel representation.
 */
@Service
public class MapperForUsers {

    /**
     * Maps a UserEntity to a UserProfileViewModel.
     *
     * @param user The UserEntity to map.
     * @return The mapped UserProfileViewModel.
     */
    public UserProfileViewModel mapUserToProfileViewModel(UserEntity user) {
        UserProfileViewModel userProfileViewModel = new UserProfileViewModel();

        // Mapping data from UserEntity to UserProfileViewModel
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
