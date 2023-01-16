package com.jch.racWiFi.userManagement.model;

import android.content.Context;
import com.jch.racWiFi.Utils.LocaleUtils;
import com.jch.racWiFi.userManagement.countryCodeManager.Country;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Marker;

public class CountryCodeUIConfiguration {
    public static CountryCodeUIConfiguration CURRENT;
    private int countryCode = -1;
    private String countryCodeShortForm;
    private String countryCodeStr;
    private int countryFlag = -1;
    private int countryName = -1;
    private int countryNameShortForm = -1;
    private int maxNumberRestricted = -1;
    private int minNumberRestricted = -1;

    public boolean isValid() {
        return (this.countryFlag == -1 || this.countryCode == -1 || this.countryName == -1 || this.countryNameShortForm == -1) ? false : true;
    }

    public void setCountryCodeString(Context context) {
        this.countryCodeStr = context.getString(this.countryCode);
    }

    public String getCountryCodeString() {
        return Marker.ANY_NON_NULL_MARKER + this.countryCodeStr;
    }

    public void setCountryCodeStr(String str) {
        this.countryCodeStr = str;
    }

    public void setCountryFlag(int i) {
        this.countryFlag = i;
    }

    public int getCountryFlag() {
        return this.countryFlag;
    }

    public void setMaxNumberRestricted(int i) {
        this.maxNumberRestricted = i;
    }

    public int getMaxNumberRestricted() {
        return this.maxNumberRestricted;
    }

    public void setMinNumberRestricted(int i) {
        this.minNumberRestricted = i;
    }

    public int getMinNumberRestricted() {
        return this.minNumberRestricted;
    }

    public void setCountryCode(int i) {
        this.countryCode = i;
    }

    public int getCountryCode() {
        return this.countryCode;
    }

    public void setCountryName(int i) {
        this.countryName = i;
    }

    public int getCountryName() {
        return this.countryName;
    }

    public void setCountryNameShortForm(int i) {
        this.countryNameShortForm = i;
    }

    public int getCountryNameShortForm() {
        return this.countryNameShortForm;
    }

    public static void changeToDeviceDefault(Context context) {
        CountryCodeUIConfiguration countryCodeUIConfigurationFromCountryObject = getCountryCodeUIConfigurationFromCountryObject(context, CountryUtils.getByNameCodeFromCustomCountries(context, (List<Country>) null, LocaleUtils.getDeviceCountryCode(context).toLowerCase()));
        CURRENT = countryCodeUIConfigurationFromCountryObject;
        countryCodeUIConfigurationFromCountryObject.setCountryCodeString(context);
    }

    public static List<CountryCodeUIConfiguration> getCountryCodeUIConfigurationList(Context context) {
        ArrayList arrayList = new ArrayList();
        for (Country next : CountryUtils.getAllCountries(context)) {
            CountryCodeUIConfiguration countryCodeUIConfiguration = new CountryCodeUIConfiguration();
            countryCodeUIConfiguration.setCountryFlag(CountryUtils.getFlagDrawableResId(context, next));
            countryCodeUIConfiguration.setCountryName(next.getName());
            countryCodeUIConfiguration.setCountryNameShortForm(next.getIso());
            countryCodeUIConfiguration.setCountryCode(next.getPhoneCode());
            arrayList.add(countryCodeUIConfiguration);
        }
        CountryCodeUIConfiguration countryCodeUIConfiguration2 = new CountryCodeUIConfiguration();
        arrayList.add(countryCodeUIConfiguration2);
        arrayList.add(countryCodeUIConfiguration2);
        return arrayList;
    }

    public static CountryCodeUIConfiguration getCountryCodeUIConfigurationFromCountryObject(Context context, Country country) {
        CountryCodeUIConfiguration countryCodeUIConfiguration = new CountryCodeUIConfiguration();
        countryCodeUIConfiguration.setCountryFlag(CountryUtils.getFlagDrawableResId(context, country));
        countryCodeUIConfiguration.setCountryName(country.getName());
        countryCodeUIConfiguration.setCountryNameShortForm(country.getIso());
        countryCodeUIConfiguration.setCountryCode(country.getPhoneCode());
        return countryCodeUIConfiguration;
    }

    public static void changeCurrentConfig(Context context, String str) {
        CountryCodeUIConfiguration countryCodeUIConfigurationFromCountryObject = getCountryCodeUIConfigurationFromCountryObject(context, CountryUtils.getByNameCodeFromCustomCountries(context, (List<Country>) null, str.toLowerCase()));
        CURRENT = countryCodeUIConfigurationFromCountryObject;
        countryCodeUIConfigurationFromCountryObject.setCountryCodeString(context);
    }
}
