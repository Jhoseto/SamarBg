package org.samarBg.model.entities;

import org.samarBg.model.entities.enums.City;
import org.samarBg.model.entities.enums.UserRole;
import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String realName;
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private City city;
    private boolean isActive;
    private String imageUrl;
    private String userConfirmationCode;
    private int UserOffersCount;
    @ElementCollection
    @Column
    private Set<Long> favoriteOfferIds = new HashSet<>();
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column
    private Set<UserRole> userRoles = new HashSet<>();
    @Column(columnDefinition = "TIMESTAMP")
    protected Instant lastOnline;
    @Column
    private Long currentUserOfferId;







    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public UserEntity setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public City getCity() {
        return city;
    }

    public UserEntity setCity(City city) {
        this.city = city;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserEntity setActive(boolean active) {
        isActive = active;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getUserConfirmationCode() {
        return userConfirmationCode;
    }

    public UserEntity setUserConfirmationCode(String userConfirmationCode) {
        this.userConfirmationCode = userConfirmationCode;
        return this;
    }

    public Set<Long> getFavoriteOfferIds() {
        return favoriteOfferIds;
    }

    public UserEntity setFavoriteOfferIds(Set<Long> favoriteOfferIds) {
        this.favoriteOfferIds = favoriteOfferIds;
        return this;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public UserEntity setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public int getUserOffersCount() {
        return UserOffersCount;
    }

    public UserEntity setUserOffersCount(int userOffersCount) {
        UserOffersCount = userOffersCount;
        return this;
    }

    public Instant getLastOnline() {
        return lastOnline;
    }

    public UserEntity setLastOnline(Instant lastOnline) {
        this.lastOnline = lastOnline;
        return this;
    }

    public Long getCurrentUserOfferId() {
        return currentUserOfferId;
    }

    public UserEntity setCurrentUserOfferId(Long currentUserOfferId) {
        this.currentUserOfferId = currentUserOfferId;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city=" + city +
                ", isActive=" + isActive +
                ", imageUrl='" + imageUrl + '\'' +
                ", userConfirmationCode='" + userConfirmationCode + '\'' +
                ", UserOffersCount=" + UserOffersCount +
                ", favoriteOfferIds=" + favoriteOfferIds +
                ", userRoles=" + userRoles +
                ", lastOnline=" + lastOnline +
                ", currentUserOfferId=" + currentUserOfferId +
                ", id=" + id +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
