package org.samarBg.views;

import org.samarBg.models.enums.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
/**
 * View model representing the data needed to display an offer.
 * <p>
 * This view model contains the following fields:
 * <ul>
 *     <li><b>id:</b> The unique identifier of the offer.</li>
 *     <li><b>basicImageUrl:</b> The URL of the basic image for the offer.</li>
 *     <li><b>imagesUrls:</b> The list of URLs for additional images associated with the offer.</li>
 *     <li><b>offerCategory:</b> The category of the offer (e.g., horse, accessories, etc.).</li>
 *     <li><b>horseCategory:</b> The category of the horse offer (if applicable).</li>
 *     <li><b>accessoriesCategory:</b> The category of the accessories offer (if applicable).</li>
 *     <li><b>sex:</b> The sex of the horse (if applicable).</li>
 *     <li><b>price:</b> The price of the offer.</li>
 *     <li><b>phone:</b> The phone number for contacting the offer owner.</li>
 *     <li><b>city:</b> The city where the offer is located.</li>
 *     <li><b>offerName:</b> The name/title of the offer.</li>
 *     <li><b>description:</b> The description of the offer.</li>
 *     <li><b>offerViewCount:</b> The number of times the offer has been viewed.</li>
 *     <li><b>videoLink:</b> The link to a video showcasing the offer.</li>
 *     <li><b>createDate:</b> The date and time when the offer was created.</li>
 *     <li><b>modifiedDate:</b> The date and time when the offer was last modified.</li>
 *     <li><b>authorName:</b> The name of the author posting the offer.</li>
 *     <li><b>authorOffersNumbers:</b> The total number of offers posted by the author.</li>
 *     <li><b>authorRegistrationData:</b> The date and time when the author registered.</li>
 *     <li><b>authorLastOnlineDate:</b> The date and time when the author was last online.</li>
 *     <li><b>authorProfileImage:</b> The URL of the author's profile image.</li>
 *     <li><b>isActive:</b> Flag indicating whether the offer is currently active.</li>
 * </ul>
 */
public class OfferViewModel {

    private Long id;
    private String basicImageUrl;
    private List<String> imagesUrls;
    private OfferCategory offerCategory;
    private HorseCategory horseCategory;
    private AccessoriesCategory accessoriesCategory;
    private Sex sex;
    private BigDecimal price;
    private String phone;
    private boolean hiddenPhone;
    private City city;
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
    private int isActive;






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

    public OfferCategory getOfferCategory() {
        return offerCategory;
    }

    public OfferViewModel setOfferCategory(OfferCategory offerCategory) {
        this.offerCategory = offerCategory;
        return this;
    }

    public HorseCategory getHorseCategory() {
        return horseCategory;
    }

    public OfferViewModel setHorseCategory(HorseCategory horseCategory) {
        this.horseCategory = horseCategory;
        return this;
    }

    public AccessoriesCategory getAccessoriesCategory() {
        return accessoriesCategory;
    }

    public OfferViewModel setAccessoriesCategory(AccessoriesCategory accessoriesCategory) {
        this.accessoriesCategory = accessoriesCategory;
        return this;
    }

    public Sex getSex() {
        return sex;
    }

    public OfferViewModel setSex(Sex sex) {
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

    public boolean isHiddenPhone() {
        return hiddenPhone;
    }

    public OfferViewModel setHiddenPhone(boolean hiddenPhone) {
        this.hiddenPhone = hiddenPhone;
        return this;
    }

    public City getCity() {
        return city;
    }

    public OfferViewModel setCity(City city) {
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

    public int getIsActive() {
        return isActive;
    }

    public OfferViewModel setIsActive(int isActive) {
        this.isActive = isActive;
        return this;
    }
}