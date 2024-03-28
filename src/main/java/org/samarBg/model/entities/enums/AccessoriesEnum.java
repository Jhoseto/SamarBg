package org.samarBg.model.entities.enums;

public enum AccessoriesEnum {

    HORSESHOES("Подкови"),
    SAMARI("Самари"),
    SHOES("Обувки"),
    HATS("Шапки"),
    OTHERS("Други");

    private final String BG;

    AccessoriesEnum(String bg) {
        this.BG = bg;
    }

    public String toBG() {
        return BG;
    }
}
