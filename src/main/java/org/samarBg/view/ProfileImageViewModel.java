package org.samarBg.view;

import org.springframework.web.multipart.MultipartFile;


public class ProfileImageViewModel {

    private MultipartFile profileImgFile;


    public MultipartFile getProfileImgFile() {
        return profileImgFile;
    }

    public ProfileImageViewModel setProfileImgFile(MultipartFile profileImgFile) {
        this.profileImgFile = profileImgFile;
        return this;
    }
}
