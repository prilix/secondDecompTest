package com.jch.racWiFi.weather;

import com.jch_hitachi.aircloudglobal.R;

public class WeatherIconsFactory {
    private static int ID_CLEAR = 800;
    private static int ID_CLOUDS_MAX = 804;
    private static int ID_CLOUDS_MIN = 801;
    private static int ID_DRIZZLE_MAX = 321;
    private static int ID_DRIZZLE_MIN = 300;
    private static int ID_MIST_MAX = 781;
    private static int ID_MIST_MIN = 701;
    private static int ID_RAIN_MAX = 531;
    private static int ID_RAIN_MIN = 500;

    public static int getWeatherIcon(int i) {
        if (i == ID_CLEAR) {
            return R.drawable.ic_sunny_white_svg;
        }
        if (i >= ID_RAIN_MIN && i <= ID_RAIN_MAX) {
            return R.drawable.ic_rainy;
        }
        if (i >= ID_DRIZZLE_MIN && i <= ID_DRIZZLE_MAX) {
            return R.drawable.ic_drizzle;
        }
        if (i < ID_MIST_MIN || i > ID_MIST_MAX) {
            return (i < ID_CLOUDS_MIN || i > ID_CLOUDS_MAX) ? R.drawable.ic_error_outline_white_24dp : R.drawable.ic_cloudy;
        }
        return R.drawable.ic_windy;
    }

    public static int getDisabledWeatherIcon(int i) {
        if (i == ID_CLEAR) {
            return R.drawable.ic_heat_disabled_svg;
        }
        if (i >= ID_RAIN_MIN && i <= ID_RAIN_MAX) {
            return R.drawable.ic_rainy_disabled;
        }
        if (i >= ID_DRIZZLE_MIN && i <= ID_DRIZZLE_MAX) {
            return R.drawable.ic_drizzy_disabled;
        }
        if (i < ID_MIST_MIN || i > ID_MIST_MAX) {
            return (i < ID_CLOUDS_MIN || i > ID_CLOUDS_MAX) ? R.drawable.ic_error_outline_disabled_24dp : R.drawable.ic_cloudy_disabled;
        }
        return R.drawable.ic_windy_disabled;
    }
}
