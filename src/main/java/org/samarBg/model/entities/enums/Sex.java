package org.samarBg.model.entities.enums;
/**
 * Represents an enumeration of gender.
 */
public enum Sex {
    MALE("Мъжки"),
    FEMALE("Женски");

    private final String BG;

    Sex(String bg) {
        this.BG = bg;
    }

    public String toBG() {
        return BG;
    }
}
