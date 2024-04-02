package org.samarBg.model.entities;

import org.samarBg.model.entities.enums.City;
import org.samarBg.model.entities.enums.HorseCategory;
import org.samarBg.model.entities.enums.OfferCategory;
import org.samarBg.model.entities.enums.Sex;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "horse_offers")
public class HorseOfferEntity extends BaseEntity {

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
    private HorseCategory horseCategory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;

    @OneToMany(mappedBy = "horseOffer", cascade = CascadeType.ALL)
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