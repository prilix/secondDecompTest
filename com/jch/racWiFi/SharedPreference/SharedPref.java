package com.jch.racWiFi.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.jch.racWiFi.iduManagement.model.HolidayModeModel;

public class SharedPref {
    private static final SharedPref ourInstance = new SharedPref();
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;

    public SharedPreferences getSharedPreferencesReader() {
        return this.mSharedPreferences;
    }

    public SharedPreferences.Editor getSharedPreferenceEditor() {
        return this.mEditor;
    }

    public static SharedPref getInstance() {
        return ourInstance;
    }

    private SharedPref() {
    }

    public void init(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.mSharedPreferences = defaultSharedPreferences;
        this.mEditor = defaultSharedPreferences.edit();
    }

    public void setHolidayModeDemoData(HolidayModeModel.HolidayModeRequestDataV2 holidayModeRequestDataV2) {
        getInstance().getSharedPreferenceEditor().putString("HOLIDAY_MODE", new Gson().toJson((Object) holidayModeRequestDataV2));
    }

    public HolidayModeModel.HolidayModeDataV2 getHolidayModeDemoDataV2() {
        return (HolidayModeModel.HolidayModeDataV2) new Gson().fromJson(getInstance().getSharedPreferencesReader().getString("Key", (String) null), HolidayModeModel.HolidayModeDataV2.class);
    }

    public static class SharePrefsV2 {
        public static final String PREFS_NAME = "PREFS";
        private static SharedPreferences sharedPreferences;

        public static void setValue(Context context, HolidayModeModel.HolidayModeRequestDataV2 holidayModeRequestDataV2) {
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(PREFS_NAME, 0);
            sharedPreferences = sharedPreferences2;
            SharedPreferences.Editor edit = sharedPreferences2.edit();
            edit.putString("HOLIDAY_MODE", new Gson().toJson((Object) holidayModeRequestDataV2));
            edit.commit();
        }

        public static String getValue(Context context) {
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(PREFS_NAME, 0);
            sharedPreferences = sharedPreferences2;
            return sharedPreferences2.getString("HOLIDAY_MODE", (String) null);
        }
    }
}
