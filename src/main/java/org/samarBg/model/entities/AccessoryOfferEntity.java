package org.samarBg.model.entities;


import org.samarBg.model.entities.enums.AccessoriesCategory;
import org.samarBg.model.entities.enums.City;
import org.samarBg.model.entities.enums.OfferCategory;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accessory_offers")
public class AccessoryOfferEntity extends BaseEntity {

    @Column(nullable = false)
    private String offerName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferCategory category;

    @Column(nullable = false)
    private String authorName;

    @Column
    private String basicImageUrl;
    @Column(nullable = false)
    private String phone;

    @Column
    private Boolean hiddenPhone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private City city;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    @Size(max = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessoriesCategory accessoriesCategory;

    @OneToMany(mappedBy = "accessoryOffer", cascade = CascadeType.ALL)
    private List<OfferImageEntity> images;

    @Column
    private int offerViewCounter;

    @Column
    private int isActive;

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
