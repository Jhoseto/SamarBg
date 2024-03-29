package org.samarBg.view;

import org.samarBg.model.entities.enums.AccessoriesEnum;
import org.samarBg.model.entities.enums.CityEnum;
import org.samarBg.model.entities.enums.HorseCategoryEnum;
import org.samarBg.model.entities.enums.SexEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class AddOfferHorseViewModel {

    @NotNull
    @Size(min = 5, max = 50, message = "Невалидно име на обявата! Въведете минимум 5 и максимум 30 символа.")
    private String offerName;
    private String basicImageUrl;

    private List<MultipartFile> images;
    @NotNull(message = "Моля, изберете категория на вашето животно ")
    private HorseCategoryEnum horseCategory;
    //TODO za aksesoarite
//    @NotNull(message = "Моля, изберете категория на вашия аксесоар ")
//    private AccessoriesEnum accessoriesCategory;
    @NotNull(message = "Моля, изберете пол на животното ")
    private SexEnum sex;
    @NotNull
   // @Size(min = 1 ,max = 7, message = "Моля въведете реална цифра за цена")
    private BigDecimal price;
    @NotNull
    @Size(min = 9, max = 15, message = "Моля въведете реален телефонен номер")
    private String phone;
    boolean hiddenPhone;
    @NotNull(message = "Моля изберете населено място")
    private CityEnum city;
    @NotNull(message = "Моля напишете описание на обявата ")
    private String description;
    private String authorName;
    private int offerViewCount;
    private Instant createDate;
    private Instant modifiedDate;
    private String videoLink;



    public String getOfferName() {
        return offerName;
    }

    public AddOfferHorseViewModel setOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public String getBasicImageUrl() {
        return basicImageUrl;
    }

    public AddOfferHorseViewModel setBasicImageUrl(String basicImageUrl) {
        this.basicImageUrl = basicImageUrl;
        return this;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public AddOfferHorseViewModel setImages(List<MultipartFile> images) {
        this.images = images;
        return this;
    }

    public HorseCategoryEnum getHorseCategory() {
        return horseCategory;
    }

    public AddOfferHorseViewModel setHorseCategory(HorseCategoryEnum horseCategory) {
        this.horseCategory = horseCategory;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public AddOfferHorseViewModel setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferHorseViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AddOfferHorseViewModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean isHiddenPhone() {
        return hiddenPhone;
    }

    public AddOfferHorseViewModel setHiddenPhone(boolean hiddenPhone) {
        this.hiddenPhone = hiddenPhone;
        return this;
    }

    public CityEnum getCity() {
        return city;
    }

    public AddOfferHorseViewModel setCity(CityEnum city) {
        this.city = city;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOfferHorseViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public AddOfferHorseViewModel setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public int getOfferViewCount() {
        return offerViewCount;
    }

    public AddOfferHorseViewModel setOfferViewCount(int offerViewCount) {
        this.offerViewCount = offerViewCount;
        return this;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public AddOfferHorseViewModel setCreateDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public AddOfferHorseViewModel setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public AddOfferHorseViewModel setVideoLink(String videoLink) {
        this.videoLink = videoLink;
        return this;
    }
}
