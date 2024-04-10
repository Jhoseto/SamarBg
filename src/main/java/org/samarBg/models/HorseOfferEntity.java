package org.samarBg.models;

import org.samarBg.models.enums.City;
import org.samarBg.models.enums.HorseCategory;
import org.samarBg.models.enums.OfferCategory;
import org.samarBg.models.enums.Sex;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
/**
 * Represents an entity for horse offers in the system.
 */
@Entity
@Table(name = "horse_offers")
public class HorseOfferEntity extends BaseEntity {

    /**
     * The name of the offer.
     */
    @Column(nullable = false)
    private String offerName;

    /**
     * The category of the offer (e.g., For Sale, For Lease).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferCategory category;

    /**
     * The name of the author of the offer.
     */
    @Column(nullable = false)
    private String authorName;

    /**
     * The URL of the basic image representing the offer.
     */
    @Column
    private String basicImageUrl;

    /**
     * The phone number associated with the offer.
     */
    @Column(nullable = false)
    private String phone;

    /**
     * Flag indicating whether the phone number should be hidden in the listing.
     */
    @Column
    private Boolean hiddenPhone;

    /**
     * The city associated with the offer location.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private City city;

    /**
     * The price of the offer.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * The description of the offer.
     */
    @Column(nullable = false)
    @Size(max = 500)
    private String description;

    /**
     * The category of the horse associated with the offer.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HorseCategory horseCategory;

    /**
     * The sex of the horse associated with the offer.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;

    /**
     * The list of images associated with the offer.
     */
    @OneToMany(mappedBy = "horseOffer", cascade = CascadeType.ALL)
    private List<OfferImageEntity> images;

    /**
     * The counter for tracking the number of views for the offer.
     */
    @Column
    private int offerViewCounter;

    /**
     * Flag indicating whether the offer is active.
     */
    @Column
    private int isActive;

    /**
     * The link to a video associated with the offer.
     */
    @Column
    private String videoLink;


    public String getOfferName() {
        return offerName;
    }

    public HorseOfferEntity setOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public OfferCategory getCategory() {
        return category;
    }

    public HorseOfferEntity setCategory(OfferCategory category) {
        this.category = category;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public HorseOfferEntity setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getBasicImageUrl() {
        return basicImageUrl;
    }

    public HorseOfferEntity setBasicImageUrl(String basicImageUrl) {
        this.basicImageUrl = basicImageUrl;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public HorseOfferEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Boolean getHiddenPhone() {
        return hiddenPhone;
    }

    public HorseOfferEntity setHiddenPhone(Boolean hiddenPhone) {
        this.hiddenPhone = hiddenPhone;
        return this;
    }

    public City getCity() {
        return city;
    }

    public HorseOfferEntity setCity(City city) {
        this.city = city;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public HorseOfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HorseOfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public HorseCategory getHorseCategory() {
        return horseCategory;
    }

    public HorseOfferEntity setHorseCategory(HorseCategory horseCategory) {
        this.horseCategory = horseCategory;
        return this;
    }

    public Sex getSex() {
        return sex;
    }

    public HorseOfferEntity setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public List<OfferImageEntity> getImages() {
        return images;
    }

    public HorseOfferEntity setImages(List<OfferImageEntity> images) {
        this.images = images;
        return this;
    }

    public int getOfferViewCounter() {
        return offerViewCounter;
    }

    public HorseOfferEntity setOfferViewCounter(int offerViewCounter) {
        this.offerViewCounter = offerViewCounter;
        return this;
    }

    public int getIsActive() {
        return isActive;
    }

    public HorseOfferEntity setIsActive(int isActive) {
        this.isActive = isActive;
        return this;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public HorseOfferEntity setVideoLink(String videoLink) {
        this.videoLink = videoLink;
        return this;
    }
}