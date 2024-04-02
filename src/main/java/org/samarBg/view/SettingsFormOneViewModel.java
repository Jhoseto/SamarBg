package org.samarBg.view;

import org.samarBg.model.entities.enums.City;

public class SettingsFormOneViewModel {


    private String realName;
    private String phone;
    private City city;

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

    public City getCity() {
        return city;
    }

    public SettingsFormOneViewModel setCity(City city) {
        this.city = city;
        return this;
    }
}
