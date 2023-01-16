package com.jch.racWiFi.amplitude.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;

@Deprecated
public class AppInfo {
    private static final String TAG = "AppInfo";
    private static AppInfo instance;

    public int getVersionCode() {
        return 32;
    }

    public static AppInfo getInstance() {
        if (instance == null) {
            instance = new AppInfo();
        }
        return instance;
    }

    public String getVersionName() {
        return Constants.CC.getVersion("3.0.9");
    }

    public String getPackageName(Context context) {
        return context.getApplicationContext().getPackageName();
    }

    public String getAppName(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                applicationInfo = null;
            }
            if (applicationInfo != null) {
                return (String) packageManager.getApplicationLabel(applicationInfo);
            }
        }
        return context.getString(R.string.app_name);
    }

    public void deviceInfo(Context context) {
        Log.e(TAG, "deviceInfo: BOARD - " + Build.BOARD);
        Log.e(TAG, "deviceInfo: BOOTLOADER - " + Build.BOOTLOADER);
        Log.e(TAG, "deviceInfo: BRAND - " + Build.BRAND);
        Log.e(TAG, "deviceInfo: DEVICE - " + Build.DEVICE);
        Log.e(TAG, "deviceInfo: DISPLAY - " + Build.DISPLAY);
        Log.e(TAG, "deviceInfo: HARDWARE - " + Build.HARDWARE);
        Log.e(TAG, "deviceInfo: HOST - " + Build.HOST);
        Log.e(TAG, "deviceInfo: ID - " + Build.ID);
        Log.e(TAG, "deviceInfo: MANUFACTURER - " + Build.MANUFACTURER);
        Log.e(TAG, "deviceInfo: MODEL - " + Build.MODEL);
        Log.e(TAG, "deviceInfo: PRODUCT - " + Build.PRODUCT);
        Log.e(TAG, "deviceInfo: VERSION - " + Build.VERSION.RELEASE);
        Log.e(TAG, "deviceInfo: API LEVEL - " + Build.VERSION.SDK_INT);
    }

    public String getDeviceUniqueID(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), "android_id");
        Log.e(TAG, "getUniqueIMEIId: " + string);
        return "";
    }
}
