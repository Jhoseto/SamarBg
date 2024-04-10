package org.samarBg.models;


import org.samarBg.models.enums.AccessoriesCategory;
import org.samarBg.models.enums.City;
import org.samarBg.models.enums.OfferCategory;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
/**
 * Represents an entity for Accessories offers in the system.
 */
@Entity
@Table(name = "accessory_offers")
public class AccessoryOfferEntity extends BaseEntity {

    /**
     * The name of the accessory offer.
     */
    @Column(nullable = false)
    private String offerName;

    /**
     * The category of the accessory offer.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferCategory category;

    /**
     * The author's name of the accessory offer.
     */
    @Column(nullable = false)
    private String authorName;

    /**
     * The URL of the basic image for the accessory offer.
     */
    @Column
    private String basicImageUrl;

    /**
     * The phone number associated with the accessory offer.
     */
    @Column(nullable = false)
    private String phone;

    /**
     * Flag indicating whether the phone number should be hidden.
     */
    @Column
    private Boolean hiddenPhone;

    /**
     * The city associated with the accessory offer.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private City city;

    /**
     * The price of the accessory offer.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * The description of the accessory offer.
     */
    @Column(nullable = false)
    @Size(max = 1000)
    private String description;

    /**
     * The category of accessories associated with the offer.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessoriesCategory accessoriesCategory;

    /**
     * The list of images associated with the accessory offer.
     */
    @OneToMany(mappedBy = "accessoryOffer", cascade = CascadeType.ALL)
    private List<OfferImageEntity> images;

    /**
     * Counter for the number of views of the accessory offer.
     */
    @Column
    private int offerViewCounter;

    /**
     * Flag indicating whether the accessory offer is active.
     */
    @Column
    private int isActive;

    /**
     * The video link associated with the accessory offer.
     */
    @Column
    private String videoLink;







    public String getOfferName() {
        return offerName;
    }

    public AccessoryOfferEntity setOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public OfferCategory getCategory() {
        return category;
    }

    public AccessoryOfferEntity setCategory(OfferCategory category) {
        this.category = category;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public AccessoryOfferEntity setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getBasicImageUrl() {
        return basicImageUrl;
    }

    public AccessoryOfferEntity setBasicImageUrl(String basicImageUrl) {
        this.basicImageUrl = basicImageUrl;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AccessoryOfferEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Boolean getHiddenPhone() {
        return hiddenPhone;
    }

    public AccessoryOfferEntity setHiddenPhone(Boolean hiddenPhone) {
        this.hiddenPhone = hiddenPhone;
        return this;
    }

    public City getCity() {
        return city;
    }

    public AccessoryOfferEntity setCity(City city) {
        this.city = city;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AccessoryOfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccessoryOfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public AccessoriesCategory getAccessoriesCategory() {
        return accessoriesCategory;
    }

    public AccessoryOfferEntity setAccessoriesCategory(AccessoriesCategory accessoriesCategory) {
        this.accessoriesCategory = accessoriesCategory;
        return this;
    }

    public List<OfferImageEntity> getImages() {
        return images;
    }

    public AccessoryOfferEntity setImages(List<OfferImageEntity> images) {
        this.images = images;
        return this;
    }

    public int getOfferViewCounter() {
        return offerViewCounter;
    }

    public AccessoryOfferEntity setOfferViewCounter(int offerViewCounter) {
        this.offerViewCounter = offerViewCounter;
        return this;
    }

    public int getIsActive() {
        return isActive;
    }

    public AccessoryOfferEntity setIsActive(int isActive) {
        this.isActive = isActive;
        return this;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public AccessoryOfferEntity setVideoLink(String videoLink) {
        this.videoLink = videoLink;
        return this;
    }
}
