package com.jch.racWiFi.fcm.util;

import com.google.gson.Gson;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.p010di.util.Constants;

public class Persistence<T> {
    public void persist(String str, T t) {
        SharedPref.getInstance().getSharedPreferenceEditor().putString(str, new Gson().toJson((Object) t)).commit();
    }

    public T obtain(String str, Class<T> cls) {
        return new Gson().fromJson(SharedPref.getInstance().getSharedPreferencesReader().getString(str, (String) null), cls);
    }

    public void clear(String str) {
        SharedPref.getInstance().getSharedPreferenceEditor().putString(str, (String) null);
    }

    public static void clearAll() {
        for (String putString : Constants.Keys.KEYS) {
            SharedPref.getInstance().getSharedPreferenceEditor().putString(putString, (String) null);
        }
    }
}
