package org.samarBg.model.entities.enums;

public enum SexEnum {
    MALE("Мъжки"),
    FEMALE("Женски");

    private final String BG;

    SexEnum(String bg) {
        this.BG = bg;
    }

    public String toBG() {
        return BG;
    }
}
