package com.jch.racWiFi.Utils;

import org.slf4j.Marker;

public class SecurityUtils {
    public static String staringNumber(String str) {
        if (str == null) {
            return "";
        }
        String substring = str.substring(0, 5);
        String substring2 = str.substring(str.length() - 2);
        int length = str.substring(5, str.length() - 2).length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append('*');
        }
        return substring + sb.toString() + substring2;
    }

    public static String staringEmail(String str) {
        String str2 = "";
        if (str == null) {
            return str2;
        }
        if (str.length() == 0) {
            return str;
        }
        String[] split = str.split("@");
        String str3 = split[0];
        String str4 = split[1];
        int length = str3.length() - 1;
        for (int i = 0; i < str3.length(); i++) {
            if (i == 0) {
                str2 = str2 + str3.charAt(i);
            } else if (i == 3) {
                str2 = str2 + str3.charAt(i);
            } else if (i == 6) {
                str2 = str2 + str3.charAt(i);
            } else if (length == i) {
                str2 = str2 + str3.charAt(i);
            } else if (str3.length() - 4 == i) {
                str2 = str2 + str3.charAt(i);
            } else if (str3.length() - 7 == i) {
                str2 = str2 + str3.charAt(i);
            } else {
                str2 = str2 + Marker.ANY_MARKER;
            }
        }
        return str2 + "@" + str4;
    }
}
