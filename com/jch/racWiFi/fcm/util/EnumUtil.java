package com.jch.racWiFi.fcm.util;

import java.util.EnumSet;
import java.util.Iterator;

public class EnumUtil {
    private static EnumUtil instance;

    private EnumUtil() {
    }

    public static EnumUtil getInstance() {
        if (instance == null) {
            instance = new EnumUtil();
        }
        return instance;
    }

    public <E extends Enum<E>> E getString(Class<E> cls, String str) {
        Iterator it = EnumSet.allOf(cls).iterator();
        while (it.hasNext()) {
            E e = (Enum) it.next();
            if (e.name().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }
}
