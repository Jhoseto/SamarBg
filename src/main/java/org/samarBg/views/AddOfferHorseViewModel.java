package org.samarBg.views;

import org.samarBg.models.enums.City;
import org.samarBg.models.enums.HorseCategory;
import org.samarBg.models.enums.Sex;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * View model representing the data needed to add a horse offer.
 * <p>
 * This view model contains the following fields:
 * <ul>
 *     <li><b>offerName:</b> The name of the horse offer.</li>
 *     <li><b>basicImageUrl:</b> The URL of the basic image for the horse offer.</li>
 *     <li><b>images:</b> The list of images for the horse offer.</li>
 *     <li><b>horseCategory:</b> The category of the horse (e.g., race horse, pony, etc.).</li>
 *     <li><b>sex:</b> The sex of the horse (male or female).</li>
 *     <li><b>price:</b> The price of the horse offer.</li>
 *     <li><b>phone:</b> The phone number for contacting the horse owner.</li>
 *     <li><b>hiddenPhone:</b> Flag indicating whether the phone number should be hidden in the offer.</li>
 *     <li><b>city:</b> The city where the horse is located.</li>
 *     <li><b>description:</b> The description of the horse offer.</li>
 *     <li><b>authorName:</b> The name of the author posting the offer.</li>
 *     <li><b>offerViewCount:</b> The number of times the offer has been viewed.</li>
 *     <li><b>createDate:</b> The date and time when the offer was created.</li>
 *     <li><b>modifiedDate:</b> The date and time when the offer was last modified.</li>
 *     <li><b>videoLink:</b> The link to a video showcasing the horse.</li>
 * </ul>
 */
public class AddOfferHorseViewModel {

    @NotNull
    @Size(min = 5, max = 50, message = "Невалидно име на обявата! Въведете минимум 5 и максимум 50 символа.")
    private String offerName;
    private String basicImageUrl;
    private List<MultipartFile> images;
    @NotNull(message = "Моля, изберете категория на вашето животно ")
    private HorseCategory horseCategory;
    @NotNull(message = "Моля, изберете пол на животното ")
    private Sex sex;
    @NotNull
    @DecimalMax(value = "99999.99", message = "Моля, въведете реалистична цифра за цена")
    private BigDecimal price;
    @NotNull
    @Size(min = 9, max = 15, message = "Моля въведете реален телефонен номер")
    private String phone;
    private boolean hiddenPhone;
    @NotNull(message = "Моля изберете населено място")
    private City city;
    @NotNull(message = "Моля напишете описание на обявата ")
    @Size(min = 20, max = 1000, message = "Описанието на обявата трябва да бъде минимум 20 и максимум 1000 символа !")
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

    public HorseCategory getHorseCategory() {
        return horseCategory;
    }

    public AddOfferHorseViewModel setHorseCategory(HorseCategory horseCategory) {
        this.horseCategory = horseCategory;
        return this;
    }

    public Sex getSex() {
        return sex;
    }

    public AddOfferHorseViewModel setSex(Sex sex) {
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


    public City getCity() {
        return city;
    }

    public AddOfferHorseViewModel setCity(City city) {
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
