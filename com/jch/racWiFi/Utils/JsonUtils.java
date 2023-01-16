package com.jch.racWiFi.Utils;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {
    public static String getJsonFromAssets(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
