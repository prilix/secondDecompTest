package com.jch.racWiFi.settings.model;

import android.content.Context;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch_hitachi.aircloudglobal.R;
import org.json.JSONException;
import org.json.JSONObject;

public enum TemperatureUnit {
    CELSIUS,
    FAHRENHEIT;
    
    public static TemperatureUnit CURRENT = null;
    public static final String SERVER_DATA_CELSIUS = "degC";
    public static final String SERVER_DATA_FAHRENHEIT = "degF";
    public static final String TEMPERATURE_UNIT = "temperatureUnit";

    public static double convertTemperaureUnitFromCelsiusAccordingToSettings(double d, int i) {
        return d * 2.0d;
    }

    public static double degCelsiusFahrenheit(double d) {
        return (d * 1.7999999523162842d) + 32.0d;
    }

    static {
        TemperatureUnit temperatureUnit;
        CURRENT = temperatureUnit;
    }

    public static void saveToPreference(Context context) {
        SharedPref.getInstance().getSharedPreferenceEditor().putInt("temperatureUnit", CURRENT.ordinal()).commit();
    }

    public static TemperatureUnit init(Context context) {
        return values()[SharedPref.getInstance().getSharedPreferencesReader().getInt("temperatureUnit", 0)];
    }

    public int toStringRes() {
        return equals(CELSIUS) ? R.string.temp_lbl_celciusC : R.string.temp_lbl_fahrenheitF;
    }

    public int toStringUnit() {
        return equals(CELSIUS) ? R.string.c_degree : R.string.f_degree;
    }

    public int toStringUnitAndRes() {
        return equals(CELSIUS) ? R.string.temp_lbl_cCelsius : R.string.temp_lbl_fFahrenheit;
    }

    public static double convertTemperaureUnitFromCelsiusAccordingToSettings(double d) {
        if (CURRENT.equals(CELSIUS)) {
            return d;
        }
        Integer fValueForC = ConversionUtil.getFValueForC(d);
        if (fValueForC == null) {
            return -1.0d;
        }
        return (double) fValueForC.intValue();
    }

    public static double convertTemperaureUnitFromCelsiusAccordingToSettingsSmartFence(double d) {
        return CURRENT.equals(CELSIUS) ? d : d * 2.0d;
    }

    public static double convertTemperaureUnitFromCelsiusAccordingToSettings(double d, boolean z) {
        if (CURRENT.equals(CELSIUS)) {
            return d;
        }
        return degCelsiusFahrenheit(d);
    }

    public static double convertTempAccordingToSettings(double d) {
        if (CURRENT.equals(CELSIUS)) {
            return d;
        }
        return degCelsiusFahrenheit(d);
    }

    public static int getTemperatureUnitFromSettings() {
        return CURRENT.toStringUnit();
    }

    public static TemperatureUnit getEnumFromServerConstant(String str) {
        return str.equals(SERVER_DATA_CELSIUS) ? CELSIUS : FAHRENHEIT;
    }

    public static class ConversionUtil {
        public static final String C_TO_F_CONVERSION_TABLE = "{\n\"-3.0\" : \"26\",\n\"-2.5\" : \"27\",\n\"-2.0\" : \"28\",\n\"-1.5\" : \"29\",\n\"-1.0\" : \"30\",\n\"-0.5\" : \"31\",\n\"0.0\"  : \"32\",\n\"0.5\"  : \"33\",\n\"1.0\"  : \"34\",\n\"1.5\"  : \"35\",\n\"2.0\"  : \"36\",\n\"2.5\"  : \"37\",\n\"3.0\"  : \"38\",  \"10.0\":\"50\",\n  \"10.5\":\"51\",\n  \"11.0\":\"52\",\n  \"11.5\":\"53\",\n  \"12.0\":\"54\",\n  \"12.5\":\"55\",\n  \"13.0\":\"56\",\n  \"13.5\":\"57\",\n  \"14.0\":\"58\",\n  \"14.5\":\"58\",\n  \"15.0\":\"59\",\n  \"15.5\":\"60\",\n  \"16.0\":\"60\",\n  \"16.5\":\"61\",\n  \"17.0\":\"62\",\n  \"17.5\":\"63\",\n  \"18.0\":\"64\",\n  \"18.5\":\"65\",\n  \"19.0\":\"66\",\n  \"19.5\":\"67\",\n  \"20.0\":\"68\",\n  \"20.5\":\"69\",\n  \"21.0\":\"70\",\n  \"21.5\":\"71\",\n  \"22.0\":\"72\",\n  \"22.5\":\"73\",\n  \"23.0\":\"74\",\n  \"23.5\":\"75\",\n  \"24.0\":\"76\",\n  \"24.5\":\"76\",\n  \"25.0\":\"77\",\n  \"25.5\":\"78\",\n  \"26.0\":\"78\",\n  \"26.5\":\"79\",\n  \"27.0\":\"80\",\n  \"27.5\":\"81\",\n  \"28.0\":\"82\",\n  \"28.5\":\"83\",\n  \"29.0\":\"84\",\n  \"29.5\":\"85\",\n  \"30.0\":\"86\",\n  \"30.5\":\"87\",\n  \"31.0\":\"88\",\n  \"31.5\":\"89\",\n  \"32.0\":\"90\",\n  \"32.5\":\"91\",\n  \"33.0\":\"92\",\n  \"33.5\":\"93\",\n  \"34.0\":\"94\",\n  \"34.5\":\"94\",\n  \"35.0\":\"95\",\n  \"35.5\":\"96\",\n  \"36.0\":\"96\",\n  \"36.5\":\"97\",\n  \"37.0\":\"98\",\n  \"37.5\":\"99\",\n  \"38.0\":\"100\",\n  \"38.5\":\"101\",\n  \"39.0\":\"102\",\n  \"39.5\":\"103\"\n}";
        private static JSONObject cToFTemperatureConversionTable;

        static {
            cToFTemperatureConversionTable = new JSONObject();
        }

        public static JSONObject getCToFTemperatureConversionTable() {
            return cToFTemperatureConversionTable;
        }

        public static void init() {
            try {
                cToFTemperatureConversionTable = new JSONObject(C_TO_F_CONVERSION_TABLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public static boolean isTemperatureAtSpecialScenarioWhileIncrement(float f) {
            return Float.compare(f, 14.0f) == 0 || Float.compare(f, 14.5f) == 0 || Float.compare(f, 15.5f) == 0 || Float.compare(f, 24.0f) == 0 || Float.compare(f, 24.5f) == 0 || Float.compare(f, 25.5f) == 0 || Float.compare(f, 34.0f) == 0 || Float.compare(f, 34.5f) == 0 || Float.compare(f, 35.5f) == 0;
        }

        public static boolean isTemperatureAtSpecialScenarioWhileDecrement(float f) {
            return Float.compare(f, 14.5f) == 0 || Float.compare(f, 15.5f) == 0 || Float.compare(f, 16.0f) == 0 || Float.compare(f, 24.5f) == 0 || Float.compare(f, 25.5f) == 0 || Float.compare(f, 26.0f) == 0 || Float.compare(f, 34.5f) == 0 || Float.compare(f, 35.5f) == 0 || Float.compare(f, 36.0f) == 0;
        }

        public static Integer getFValueForC(DetailedIduModel detailedIduModel) {
            return getFValueForC((double) detailedIduModel.iduTemperature);
        }

        public static Integer getFValueForC(double d) {
            try {
                return Integer.valueOf(cToFTemperatureConversionTable.getInt(String.valueOf(d)));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
