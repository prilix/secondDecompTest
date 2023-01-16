package com.jch.racWiFi.settings.model;

import java.util.Locale;

public class LanguageModel {
    private int countryFlagRes;
    private int englishStringRes;
    private boolean isSelected = false;
    private int languageStringRes;
    private Locale locale;

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public int getCountryFlagRes() {
        return this.countryFlagRes;
    }

    public int getLanguageStringRes() {
        return this.languageStringRes;
    }

    public int getEnglishStringRes() {
        return this.englishStringRes;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public LanguageModel(Locale locale2, int i, int i2) {
        this.locale = locale2;
        this.languageStringRes = i;
        this.englishStringRes = i2;
    }

    public LanguageModel(Locale locale2) {
        this.locale = locale2;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof LanguageModel)) {
            return false;
        }
        LanguageModel languageModel = (LanguageModel) obj;
        if (this.locale.equals(languageModel.locale) || (this.locale.getLanguage().equalsIgnoreCase(languageModel.locale.getLanguage()) && this.locale.getCountry().equalsIgnoreCase(languageModel.locale.getCountry()))) {
            return true;
        }
        return false;
    }

    public LanguageModel(LanguageModel languageModel) {
        this.locale = languageModel.locale;
        this.languageStringRes = languageModel.languageStringRes;
        this.englishStringRes = languageModel.englishStringRes;
        this.countryFlagRes = languageModel.countryFlagRes;
        this.isSelected = languageModel.isSelected;
    }
}
