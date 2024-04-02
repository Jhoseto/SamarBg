package org.samarBg.model.entities.enums;

public enum AccessoriesCategory {

    HORSESHOES("Подкови"),
    SAMARI("Самари"),
    SHOES("Обувки"),
    HATS("Шапки"),
    OTHERS("Други");

    private final String BG;

    AccessoriesCategory(String bg) {
        this.BG = bg;
    }

    public String toBG() {
        return BG;
    }
}