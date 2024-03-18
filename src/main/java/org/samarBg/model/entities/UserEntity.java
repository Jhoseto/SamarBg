package org.samarBg.model.entities;

import org.samarBg.model.entities.enums.CityEnum;
import org.samarBg.model.entities.enums.UserRoleEnum;
import javax.persistence.*;
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
    private CityEnum city;
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
    private Set<UserRoleEnum> userRoles = new HashSet<>();


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

    public CityEnum getCity() {
        return city;
    }

    public UserEntity setCity(CityEnum city) {
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

    public Set<UserRoleEnum> getUserRoles() {
        return userRoles;
    }

    public UserEntity setUserRoles(Set<UserRoleEnum> userRoles) {
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
}
