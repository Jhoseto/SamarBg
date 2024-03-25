package org.samarBg.view;

import org.samarBg.model.entities.enums.CityEnum;

public class SettingsFormOneViewModel {


    private String realName;
    private String phone;
    private CityEnum city;

    public String getRealName() {
        return realName;
    }

    public SettingsFormOneViewModel setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public SettingsFormOneViewModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CityEnum getCity() {
        return city;
    }

    public SettingsFormOneViewModel setCity(CityEnum city) {
        this.city = city;
        return this;
    }
}
