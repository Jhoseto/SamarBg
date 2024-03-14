package org.samarBg.view;

import org.samarBg.model.entities.enums.AccessoriesEnum;
import org.samarBg.model.entities.enums.CityEnum;
import org.samarBg.model.entities.enums.HorseCategoryEnum;
import org.samarBg.model.entities.enums.OfferCategoryEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferDetailsViewModel extends OfferViewModel {
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
    private Instant modified;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public OfferDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public OfferDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Override
    public OfferCategoryEnum getOfferCategory() {
        return offerCategory;
    }

    @Override
    public OfferDetailsViewModel setOfferCategory(OfferCategoryEnum offerCategory) {
        this.offerCategory = offerCategory;
        return this;
    }

    @Override
    public HorseCategoryEnum getHorseCategory() {
        return horseCategory;
    }

    @Override
    public OfferDetailsViewModel setHorseCategory(HorseCategoryEnum horseCategory) {
        this.horseCategory = horseCategory;
        return this;
    }

    @Override
    public AccessoriesEnum getAccessoriesCategory() {
        return accessoriesCategory;
    }

    @Override
    public OfferDetailsViewModel setAccessoriesCategory(AccessoriesEnum accessoriesCategory) {
        this.accessoriesCategory = accessoriesCategory;
        return this;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public OfferDetailsViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Override
    public CityEnum getCity() {
        return city;
    }

    @Override
    public OfferDetailsViewModel setCity(CityEnum city) {
        this.city = city;
        return this;
    }

    @Override
    public String getOfferName() {
        return offerName;
    }

    @Override
    public OfferDetailsViewModel setOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public OfferDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String getAuthorName() {
        return authorName;
    }

    @Override
    public OfferDetailsViewModel setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    @Override
    public Instant getCreateDate() {
        return createDate;
    }

    @Override
    public OfferDetailsViewModel setCreateDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OfferDetailsViewModel setModified(Instant modified) {
        this.modified = modified;
        return this;
    }
}
