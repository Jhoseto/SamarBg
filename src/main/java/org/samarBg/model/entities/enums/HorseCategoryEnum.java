package org.samarBg.model.entities.enums;

public enum HorseCategoryEnum {
    HORSE("Кон"),
    PONY("Пони"),
    HEAVYHAULER("Тежкотоварен кон"),
    MULE("Муле"),
    DONKEY("Магаре");

    private final String BG;

    HorseCategoryEnum(String bg) {
        this.BG = bg;
    }

    public String toBG() {
        return BG;
    }
}
