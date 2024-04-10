package org.samarBg.model.entities.enums;
/**
 * Represents an enumeration of animal types categories.
 */
public enum HorseCategory {
    HORSE("Кон"),
    PONY("Пони"),
    HEAVYHAULER("Тежкотоварен кон"),
    MULE("Муле"),
    DONKEY("Магаре");

    private final String BG;

    HorseCategory(String bg) {
        this.BG = bg;
    }

    public String toBG() {
        return BG;
    }
}
