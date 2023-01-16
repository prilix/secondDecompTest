package com.jch.racWiFi.Utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class SimUtils {
    public static boolean getSimState(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimState() != 1;
    }
}
