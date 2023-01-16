package com.jch.racWiFi;

import android.content.Context;
import android.content.pm.PackageManager;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppVersionModels {

    public enum Platform {
        ANDROID,
        IOS;

        public String getCurrentAppVersion(Context context) {
            try {
                return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "Unknown-01";
            }
        }

        public String getAppInstallTime(Context context) {
            try {
                return new SimpleDateFormat("yyyy/MMM/dd, HH:mm", LocaleConfiguration.getCurrentAppLocale()).format(new Date(new File(context.getApplicationContext().getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir).lastModified()));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return "Unknow";
            }
        }
    }
}
