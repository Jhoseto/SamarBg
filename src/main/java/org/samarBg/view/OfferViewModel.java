package org.samarBg.view;

import org.samarBg.model.entities.enums.AccessoriesEnum;
import org.samarBg.model.entities.enums.CityEnum;
import org.samarBg.model.entities.enums.HorseCategoryEnum;
import org.samarBg.model.entities.enums.OfferCategoryEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferViewModel {

    private Long id;
    private String imageUrl;
    private OfferCategoryEnum offerCategory;
    private HorseCategoryEnum horseCategory;
    private AccessoriesEnum accessoriesCategory;
    private BigDecimal price;
    private CityEnum city;
    private String offerName;
    private String description;
    private String authorName;
    private Instant createDate;


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

    public HorseCategoryEnum getHorseCategory() {
        return horseCategory;
    }

    public OfferCategoryEnum getOfferCategory() {
        return offerCategory;
    }

    public OfferViewModel setOfferCategory(OfferCategoryEnum offerCategory) {
        this.offerCategory = offerCategory;
        return this;
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

    public BigDecimal getPrice() {
        return price;
    }

    public OfferViewModel setPrice(BigDecimal price) {
        this.price = price;
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

    public Instant getCreateDate() {
        return createDate;
    }

    public OfferViewModel setCreateDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }


    @Override
    public String toString() {
        return "OfferThumbnailViewModel{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", offerCategory=" + offerCategory +
                ", horseCategory=" + horseCategory +
                ", accessoriesCategory=" + accessoriesCategory +
                ", price=" + price +
                ", city=" + city +
                ", offerName='" + offerName + '\'' +
                ", description='" + description + '\'' +
                ", authorName='" + authorName + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}