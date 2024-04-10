package org.samarBg.views;

import org.samarBg.models.enums.City;

import java.time.Instant;
import java.util.List;


/**
 * View model representing user profile information.
 * <p>
 * This view model contains the following fields:
 * <ul>
 *     <li><b>id:</b> The unique identifier of the user.</li>
 *     <li><b>userName:</b> The username of the user.</li>
 *     <li><b>realName:</b> The real name of the user.</li>
 *     <li><b>profileImageUrl:</b> The URL of the user's profile image.</li>
 *     <li><b>email:</b> The email address of the user.</li>
 *     <li><b>phone:</b> The phone number of the user.</li>
 *     <li><b>city:</b> The city where the user is located.</li>
 *     <li><b>userOffers:</b> A list of offers created by the user.</li>
 *     <li><b>lastOnline:</b> The date and time when the user was last online.</li>
 * </ul>
 */
public class UserProfileViewModel {
    private Long id;
    private String userName;
    private String realName;
    private String profileImageUrl;
    private String email;
    private String phone;
    private City city;
    private List<OfferViewModel> userOffers;
    private Instant lastOnline;


    public Long getId() {
        return id;
    }

    public UserProfileViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Instant getLastOnline() {
        return lastOnline;
    }

    public UserProfileViewModel setLastOnline(Instant lastOnline) {
        this.lastOnline = lastOnline;
        return this;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public UserProfileViewModel setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserProfileViewModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public UserProfileViewModel setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserProfileViewModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public City getCity() {
        return city;
    }

    public UserProfileViewModel setCity(City city) {
        this.city = city;
        return this;
    }

    public List<OfferViewModel> getUserOffers() {
        return userOffers;
    }

    public UserProfileViewModel setUserOffers(List<OfferViewModel> userOffers) {
        this.userOffers = userOffers;
        return this;
    }
}
