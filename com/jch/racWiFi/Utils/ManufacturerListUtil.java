package com.jch.racWiFi.Utils;

import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;

public class ManufacturerListUtil {
    public static final ArrayList<String> manufacturerList;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        manufacturerList = arrayList;
        arrayList.add("xiaomi");
        arrayList.add("oppo");
        arrayList.add("vivo");
        arrayList.add("oneplus");
        arrayList.add("Letv");
        arrayList.add("huawei");
        arrayList.add("asus");
        arrayList.add("Lenovo");
    }

    public static boolean isChineseManufacturer() {
        String str = Build.MANUFACTURER;
        if (str != null && !str.isEmpty()) {
            Iterator<String> it = manufacturerList.iterator();
            while (it.hasNext()) {
                if (it.next().equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
