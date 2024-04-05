package org.samarBg.view;

import org.samarBg.model.entities.enums.*;

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
