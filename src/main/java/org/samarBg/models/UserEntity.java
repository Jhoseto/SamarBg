package org.samarBg.models;

import org.samarBg.models.enums.City;
import org.samarBg.models.enums.UserRole;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
/**
 * Represents an entity for users in the application.
 */
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The real name of the user.
     */
    private String realName;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The email address of the user (unique and non-nullable).
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * The phone number of the user.
     */
    private String phone;

    /**
     * The city of the user (enum type).
     */
    @Enumerated(EnumType.STRING)
    private City city;

    /**
     * Flag indicating if the user account is active.
     */
    private boolean isActive;

    /**
     * The URL of the user's profile image.
     */
    private String imageUrl;

    /**
     * The confirmation code used for user registration.
     */
    private String userConfirmationCode;

    /**
     * The count of offers created by the user.
     */
    private int userOffersCount;

    /**
     * Set of favorite offer IDs associated with the user.
     */
    @ElementCollection
    @Column
    private Set<Long> favoriteOfferIds = new HashSet<>();

    /**
     * Set of roles assigned to the user (enum type).
     */
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * The timestamp indicating the user's last online activity.
     */
    @Column(columnDefinition = "TIMESTAMP")
    protected Instant lastOnline;

    /**
     * Indicator for Online/Offline status on user.
     */
    private int onlineStatus;



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

    public int getUserOffersCount() {
        return userOffersCount;
    }

    public UserEntity setUserOffersCount(int userOffersCount) {
        this.userOffersCount = userOffersCount;
        return this;
    }

    public Set<Long> getFavoriteOfferIds() {
        return favoriteOfferIds;
    }

    public UserEntity setFavoriteOfferIds(Set<Long> favoriteOfferIds) {
        this.favoriteOfferIds = favoriteOfferIds;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public UserEntity setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public Instant getLastOnline() {
        return lastOnline;
    }

    public UserEntity setLastOnline(Instant lastOnline) {
        this.lastOnline = lastOnline;
        return this;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public UserEntity setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
        return this;
    }
}
