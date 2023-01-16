package com.jch.racWiFi.Utils;

import android.content.Intent;

public class IntentUtils {
    public static Intent getIntentForWiFiSettings() {
        return new Intent("android.settings.WIFI_SETTINGS");
    }
}
