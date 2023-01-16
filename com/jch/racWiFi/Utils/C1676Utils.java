package com.jch.racWiFi.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.jch.racWiFi.Utils.Utils */
public class C1676Utils {
    public static int getDeviceDefaultOrientation(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        if (((rotation == 0 || rotation == 2) && configuration.orientation == 2) || ((rotation == 1 || rotation == 3) && configuration.orientation == 1)) {
            return 2;
        }
        return 1;
    }

    public static String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (!TextUtils.isEmpty(fileExtensionFromUrl)) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        String fileExtensionFromUrl2 = MimeTypeMap.getFileExtensionFromUrl(str.replaceAll("\\s+", ""));
        if (!TextUtils.isEmpty(fileExtensionFromUrl2)) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl2);
        }
        return "";
    }

    public static int convertDipToPixels(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static long[] convert(ArrayList<Long> arrayList) {
        long[] jArr = new long[arrayList.size()];
        Iterator<Long> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            jArr[i] = it.next().longValue();
            i++;
        }
        return jArr;
    }
}
