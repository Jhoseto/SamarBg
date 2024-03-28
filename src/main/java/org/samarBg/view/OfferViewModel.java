package org.samarBg.view;

import org.samarBg.model.entities.enums.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OfferViewModel {

    private Long id;
    private String basicImageUrl;
    private List<String> imagesUrls;
    private OfferCategoryEnum offerCategory;
    private HorseCategoryEnum horseCategory;
    private AccessoriesEnum accessoriesCategory;
    private SexEnum sex;
    private BigDecimal price;
    private String phone;
    private CityEnum city;
    private String offerName;
    private String description;
    private int offerViewCount;
    private String videoLink;
    private Instant createDate;
    private Instant modifiedDate;
    private String authorName;
    private int authorOffersNumbers;
    private Instant authorRegistrationData;
    private Instant authorLastOnlineDate;
    private String authorProfileImage;






    public Long getId() {
        return id;
    }

    public OfferViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBasicImageUrl() {
        return basicImageUrl;
    }

    public OfferViewModel setBasicImageUrl(String basicImageUrl) {
        this.basicImageUrl = basicImageUrl;
        return this;
    }

    public List<String> getImagesUrls() {
        return imagesUrls;
    }

    public OfferViewModel setImagesUrls(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
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

    public int getOfferViewCount() {
        return offerViewCount;
    }

    public OfferViewModel setOfferViewCount(int offerViewCount) {
        this.offerViewCount = offerViewCount;
        return this;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public OfferViewModel setVideoLink(String videoLink) {
        this.videoLink = videoLink;
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

    public String getAuthorName() {
        return authorName;
    }

    public OfferViewModel setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public int getAuthorOffersNumbers() {
        return authorOffersNumbers;
    }

    public OfferViewModel setAuthorOffersNumbers(int authorOffersNumbers) {
        this.authorOffersNumbers = authorOffersNumbers;
        return this;
    }

    public Instant getAuthorRegistrationData() {
        return authorRegistrationData;
    }

    public OfferViewModel setAuthorRegistrationData(Instant authorRegistrationData) {
        this.authorRegistrationData = authorRegistrationData;
        return this;
    }

    public Instant getAuthorLastOnlineDate() {
        return authorLastOnlineDate;
    }

    public OfferViewModel setAuthorLastOnlineDate(Instant authorLastOnlineDate) {
        this.authorLastOnlineDate = authorLastOnlineDate;
        return this;
    }

    public String getAuthorProfileImage() {
        return authorProfileImage;
    }

    public OfferViewModel setAuthorProfileImage(String authorProfileImage) {
        this.authorProfileImage = authorProfileImage;
        return this;
    }
}