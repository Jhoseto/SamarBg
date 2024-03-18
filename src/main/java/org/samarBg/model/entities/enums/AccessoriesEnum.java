package org.samarBg.model.entities.enums;

public enum AccessoriesEnum {
    HORSES("Коне"),
    ACCESSORIES("Аксесоари"),
    HORSESHOES("Подкови"),
    SAMARI("Самари"),
    SHOES("Обувки"),
    HATS("Шапки");

    private final String BG;

    AccessoriesEnum(String bg) {
        this.BG = bg;
    }

    public String toBG() {
        return BG;
    }
}
