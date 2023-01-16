package com.jch.racWiFi.weather.model;

import com.google.gson.annotations.SerializedName;

public class WeatherDataModel {
    public static final String COUNTRY_CODE_OR_ZIP_CODE_NOT_FOUND = "NFE017";
    public static WeatherDataModelResponseSuccess CURRENT_HOME_PAGE_WEATHER = new WeatherDataModelResponseSuccess();
    public static final String USER_DETAILS_NOT_FOUND = "NFE002";

    public static class Coordinate {
        @SerializedName("latitude")
        public String latitude;
        @SerializedName("longitude")
        public String longitude;
    }

    public static class MainParameters {
        @SerializedName("feelsLike")
        public double feelsLike;
        @SerializedName("groundLevelPressure")
        public double groundLevelPressure;
        @SerializedName("humidity")
        public double humidity;
        @SerializedName("maximumTemperature")
        public double maximumTemperature;
        @SerializedName("minimumTemperature")
        public double minimumTemperature;
        @SerializedName("pressure")
        public double pressure;
        @SerializedName("seaLevelPressure")
        public double seaLevelPressure;
        @SerializedName("temperature")
        public double temperature;
    }

    public static class Weather {
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;
        @SerializedName("id")

        /* renamed from: id */
        public String f489id;
        @SerializedName("main")
        public String main;
    }

    public static class WeatherDataModelFailureResponse {
        @SerializedName("code")
        public String code;
        @SerializedName("desc")
        public String desc;
        @SerializedName("stackTrace")
        public String stackTrace;
        public int statusCode;
        @SerializedName("type")
        public String type;
    }

    public static class WeatherDataModelResponseSuccess {
        @SerializedName("message")
        public String message;
        @SerializedName("result")
        public WeatherResult weatherResult;
    }

    public static class WeatherResult {
        @SerializedName("cityId")
        public int cityId;
        @SerializedName("cityName")
        public String cityName;
        @SerializedName("cod")
        public int cod;
        @SerializedName("coordinate")
        public Coordinate coordinate;
        @SerializedName("date")
        public String date;
        @SerializedName("mainParameters")
        public MainParameters mainParameters;
        @SerializedName("weather")
        public Weather[] weathers;
    }
}
