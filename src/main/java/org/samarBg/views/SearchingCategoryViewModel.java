package org.samarBg.views;

import org.samarBg.models.enums.*;
/**
 * View model representing the search criteria for filtering offers by category.
 * <p>
 * This view model contains the following fields:
 * <ul>
 *     <li><b>mainCategory:</b> The main category of the offer (e.g., horses, accessories).</li>
 *     <li><b>city:</b> The city where the offers are located.</li>
 *     <li><b>horseCategory:</b> The category of the horse (e.g., race horse, pony, etc.).</li>
 *     <li><b>sex:</b> The sex of the horse (male or female).</li>
 *     <li><b>accessoriesCategory:</b> The category of the accessories (e.g., saddle, bridle, etc.).</li>
 * </ul>
 */
public class SearchingCategoryViewModel {
    private OfferCategory mainCategory;
    private City city;
    private HorseCategory horseCategory;
    private Sex sex;
    private AccessoriesCategory accessoriesCategory;


    public OfferCategory getMainCategory() {
        return mainCategory;
    }

    public SearchingCategoryViewModel setMainCategory(OfferCategory mainCategory) {
        this.mainCategory = mainCategory;
        return this;
    }

    public City getCity() {
        return city;
    }

    public SearchingCategoryViewModel setCity(City city) {
        this.city = city;
        return this;
    }

    public HorseCategory getHorseCategory() {
        return horseCategory;
    }

    public SearchingCategoryViewModel setHorseCategory(HorseCategory horseCategory) {
        this.horseCategory = horseCategory;
        return this;
    }

    public Sex getSex() {
        return sex;
    }

    public SearchingCategoryViewModel setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public AccessoriesCategory getAccessoriesCategory() {
        return accessoriesCategory;
    }

    public SearchingCategoryViewModel setAccessoriesCategory(AccessoriesCategory accessoriesCategory) {
        this.accessoriesCategory = accessoriesCategory;
        return this;
    }
}
