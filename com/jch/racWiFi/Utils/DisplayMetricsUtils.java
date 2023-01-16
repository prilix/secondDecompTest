package com.jch.racWiFi.Utils;

import android.content.Context;

public class DisplayMetricsUtils {
    public static float convertDpToPixel(float f, Context context) {
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }
}
