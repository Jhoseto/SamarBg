package org.samarBg.view;

import org.samarBg.model.entities.enums.*;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferViewModel {

    private Long id;
    private String imageUrl;
    private OfferCategoryEnum offerCategory;
    private HorseCategoryEnum horseCategory;
    private AccessoriesEnum accessoriesCategory;
    private SexEnum sex;
    private BigDecimal price;
    private String phone;
    private CityEnum city;
    private String offerName;
    private String description;
    private String authorName;
    private int offerViewCount;
    private Instant userRegistrationDate;
    private int userOffersCount;
    private Instant createDate;
    private Instant modifiedDate;


    public Long getId() {
        return id;
    }

    public OfferViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public OfferCategoryEnum getOfferCategory() {
        return offerCategory;
    }

    public OfferViewModel setOfferCategory(OfferCategoryEnum offerCategory) {
        this.offerCategory = offerCategory;
        return this;
    }

    public HorseCategoryEnum getHorseCategory() {
        return horseCategory;
    }

    public OfferViewModel setHorseCategory(HorseCategoryEnum horseCategory) {
        this.horseCategory = horseCategory;
        return this;
    }

    public AccessoriesEnum getAccessoriesCategory() {
        return accessoriesCategory;
    }

    public OfferViewModel setAccessoriesCategory(AccessoriesEnum accessoriesCategory) {
        this.accessoriesCategory = accessoriesCategory;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public OfferViewModel setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public OfferViewModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CityEnum getCity() {
        return city;
    }

    public OfferViewModel setCity(CityEnum city) {
        this.city = city;
        return this;
    }

    public String getOfferName() {
        return offerName;
    }

    public OfferViewModel setOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public OfferViewModel setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public int getOfferViewCount() {
        return offerViewCount;
    }

    public OfferViewModel setOfferViewCount(int offerViewCount) {
        this.offerViewCount = offerViewCount;
        return this;
    }

    public Instant getUserRegistrationDate() {
        return userRegistrationDate;
    }

    public OfferViewModel setUserRegistrationDate(Instant userRegistrationDate) {
        this.userRegistrationDate = userRegistrationDate;
        return this;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public OfferViewModel setCreateDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public OfferViewModel setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }


    public int getUserOffersCount() {
        return userOffersCount;
    }

    public OfferViewModel setUserOffersCount(int userOffersCount) {
        this.userOffersCount = userOffersCount;
        return this;
    }

    @Override
    public String toString() {
        return "OfferViewModel{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", offerCategory=" + offerCategory +
                ", horseCategory=" + horseCategory +
                ", accessoriesCategory=" + accessoriesCategory +
                ", sex=" + sex +
                ", price=" + price +
                ", phone='" + phone + '\'' +
                ", city=" + city +
                ", offerName='" + offerName + '\'' +
                ", description='" + description + '\'' +
                ", authorName='" + authorName + '\'' +
                ", userOfferCount=" + offerViewCount +
                ", userRegistrationDate=" + userRegistrationDate +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}