package com.jch.racWiFi.settings.model;

import com.google.gson.annotations.SerializedName;

public class SettingsDataModels {

    public static class SettingsData {
        @SerializedName("homeOnWeekdays")
        public boolean homeOnWeekdays;
        @SerializedName("homeOnWeekends")
        public boolean homeOnWeekends;
        @SerializedName("language")
        public String language = "in";
        @SerializedName("outOfHomeAddress")
        public String outOfHomeAddress;
        @SerializedName("outOfHomeLatitude")
        public double outOfHomeLatitude;
        @SerializedName("outOfHomeLongitude")
        public double outOfHomeLongitude;
        @SerializedName("outOfHomeRadius")
        public float outOfHomeRadius;
        @SerializedName("outOfHomeRemainderEnabled")
        public boolean outOfHomeRemainderEnabled;
        @SerializedName("sensitiveToCold")
        public boolean sensitiveToCold;
        @SerializedName("temperatureUnit")
        public String temperatureUnit = TemperatureUnit.SERVER_DATA_CELSIUS;

        public void copy(SettingsData settingsData) {
            this.language = settingsData.language;
            this.temperatureUnit = settingsData.temperatureUnit;
            this.outOfHomeRemainderEnabled = settingsData.outOfHomeRemainderEnabled;
            this.outOfHomeLatitude = settingsData.outOfHomeLatitude;
            this.outOfHomeLongitude = settingsData.outOfHomeLongitude;
            this.outOfHomeRadius = settingsData.outOfHomeRadius;
            this.outOfHomeAddress = settingsData.outOfHomeAddress;
            this.homeOnWeekends = settingsData.homeOnWeekends;
            this.homeOnWeekdays = settingsData.homeOnWeekdays;
            this.sensitiveToCold = settingsData.sensitiveToCold;
        }

        public void copy(UserPreferenceSettingsData userPreferenceSettingsData) {
            this.homeOnWeekends = userPreferenceSettingsData.homeOnWeekends;
            this.homeOnWeekdays = userPreferenceSettingsData.homeOnWeekdays;
            this.sensitiveToCold = userPreferenceSettingsData.sensitiveToCold;
        }
    }

    public static class UserPreferenceSettingsData {
        @SerializedName("homeDuringWeekDays")
        public boolean homeOnWeekdays;
        @SerializedName("homeDuringWeekends")
        public boolean homeOnWeekends;
        @SerializedName("coldSensitive")
        public boolean sensitiveToCold;

        public void copy(UserPreferenceSettingsData userPreferenceSettingsData) {
            this.homeOnWeekdays = userPreferenceSettingsData.homeOnWeekdays;
            this.homeOnWeekends = userPreferenceSettingsData.homeOnWeekends;
            this.sensitiveToCold = userPreferenceSettingsData.sensitiveToCold;
        }
    }
}
