package org.samarBg.views;

import org.samarBg.models.enums.City;
/**
 * View model representing the settings form part one.
 * <p>
 * This view model contains the following fields:
 * <ul>
 *     <li><b>realName:</b> The real name of the user.</li>
 *     <li><b>phone:</b> The phone number of the user.</li>
 *     <li><b>city:</b> The city where the user resides.</li>
 * </ul>
 */
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
