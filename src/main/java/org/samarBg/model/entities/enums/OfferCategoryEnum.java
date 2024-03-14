package org.samarBg.model.entities.enums;

public enum OfferCategoryEnum {
    HORSES("Коне"),
    ACCESSORIES("Аксесоари");

    private final String BG;

    OfferCategoryEnum(String BG) {
        this.BG = BG;
    }

    public String toBG() {
        return String.valueOf(BG);
    }

}


