package org.samarBg.models.enums;
/**
 * Represents an enumeration of Main categories in project.
 */
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


