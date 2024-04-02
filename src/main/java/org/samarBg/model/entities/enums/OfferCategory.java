package org.samarBg.model.entities.enums;

public enum OfferCategory {
    HORSES("Коне"),
    ACCESSORIES("Аксесоари");

    private final String BG;

    OfferCategory(String BG) {
        this.BG = BG;
    }

    public String toBG() {
        return String.valueOf(BG);
    }

}


