package com.jch.racWiFi.Utils;

import android.app.Activity;
import android.content.Context;

public class ExceptionUtil {
    public static void checkContextBelongsToActivity(Context context) {
        if (!(context instanceof Activity)) {
            new Exception("context doesn't belong to Activity");
        }
    }
}
