package org.samarBg.views;

import org.springframework.web.multipart.MultipartFile;

/**
 * View model representing the data needed to upload a profile image.
 * <p>
 * This view model contains the following field:
 * <ul>
 *     <li><b>profileImgFile:</b> The profile image file to be uploaded.</li>
 * </ul>
 */
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
