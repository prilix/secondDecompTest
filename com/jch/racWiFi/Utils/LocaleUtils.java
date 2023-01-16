package com.jch.racWiFi.Utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class LocaleUtils {
    public static String getDeviceCountryCode(Context context) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (simCountryIso != null && simCountryIso.length() == 2) {
                return simCountryIso.toLowerCase();
            }
            if (telephonyManager.getPhoneType() == 2) {
                str = "in";
            } else {
                str = telephonyManager.getNetworkCountryIso();
            }
            if (str != null && str.length() == 2) {
                return str.toLowerCase();
            }
        }
        return "in";
    }
}
