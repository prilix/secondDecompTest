package com.jch.racWiFi.userOnboarding.model;

import android.os.Build;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public class QRDetailsModel implements Serializable {
    public static QRDetailsModel CURRENT_RAC_DETAILS = new QRDetailsModel();
    private String SSID;
    private String password;
    private RacTypeEnum racTypeEnum;
    private String type = "";

    public RacTypeEnum getRacTypeEnum() {
        return this.racTypeEnum;
    }

    public void setRacTypeEnum(RacTypeEnum racTypeEnum2) {
        this.racTypeEnum = racTypeEnum2;
    }

    public String getSSID() {
        return this.SSID;
    }

    public void setSSID(String str) {
        this.SSID = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean parseQrString(String str) {
        String[] split = str.split("/");
        if (split.length == 3) {
            String trim = split[0].trim();
            String trim2 = split[1].trim();
            String trim3 = split[2].trim();
            String[] split2 = trim.split("=");
            String[] split3 = trim2.split("=");
            String[] split4 = trim3.split("=");
            if (!(split2.length != 2 || split3.length != 2 || split4.length != 2 || split2[0] == null || split2[1] == null || split3[0] == null || split3[1] == null || split4[0] == null || split4[1] == null)) {
                String lowerCase = split2[0].trim().toLowerCase();
                String lowerCase2 = split3[0].trim().toLowerCase();
                String lowerCase3 = split4[0].trim().toLowerCase();
                if (lowerCase.equals("ssid") && ((lowerCase2.equals("pass") || lowerCase2.equals("key")) && lowerCase3.equals("type"))) {
                    String trim4 = split2[1].trim();
                    String trim5 = split3[1].trim();
                    String trim6 = split4[1].trim();
                    setSSID(trim4);
                    setPassword(trim5);
                    setType(trim6);
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        return "ssid= " + getSSID() + " , password= " + getPassword() + " , type= " + getType();
    }

    private static boolean stringContainsItemFromList(String str, String[] strArr) {
        if (Build.VERSION.SDK_INT < 24) {
            return StringUtils.containsAny((CharSequence) str, (CharSequence[]) strArr);
        }
        Objects.requireNonNull(str);
        return ((Stream) Arrays.stream(strArr).parallel()).anyMatch(new QRDetailsModel$$ExternalSyntheticLambda0(str));
    }
}
