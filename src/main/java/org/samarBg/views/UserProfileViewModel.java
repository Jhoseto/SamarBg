package org.samarBg.views;

import org.samarBg.models.enums.City;

import java.time.Instant;
import java.util.List;


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
