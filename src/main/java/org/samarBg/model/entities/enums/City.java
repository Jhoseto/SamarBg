package org.samarBg.model.entities.enums;

public enum City {
    NONE("-"),
    BLAGOEVGRAD("Благоевград"),
    BURGAS("Бургас"),
    VARNA("Варна"),
    VELIKO_TARNOVO("Велико Търново"),
    VIDIN("Видин"),
    VRATSA("Враца"),
    GABROVO("Габрово"),
    DOBRICH("Добрич"),
    KARDZHALI("Кърджали"),
    KYUSTENDIL("Кюстендил"),
    LOVECH("Ловеч"),
    MONTANA("Монтана"),
    PAZARDZHIK("Пазарджик"),
    PERNIK("Перник"),
    PLEVEN("Плевен"),
    PLOVDIV("Пловдив"),
    RAZGRAD("Разград"),
    RUSE("Русе"),
    SILISTRA("Силистра"),
    SLIVEN("Сливен"),
    SMOLYAN("Смолян"),
    SOFIA("София"),
    STARA_ZAGORA("Стара Загора"),
    TARGOVISHTE("Търговище"),
    HASKOVO("Хасково"),
    SHUMEN("Шумен"),
    YAMBOL("Ямбол");

    private final String BG;

    City(String bg) {
        this.BG = bg;
    }

    public String toBG() {
        return BG;
    }

}
