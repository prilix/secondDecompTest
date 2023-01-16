package com.jch.racWiFi;

public interface StatusCode {
    public static final String BUDGET_SETTINGS_NOT_FOUND = "NFE029";
    public static final String BUILTIN_WIRELESS = "0";
    public static final int DEVICE_OFFLINE = 412;
    public static final String EXPIRED_TOKEN = "EXPIRED_TOKEN";
    public static final String EXTERNAL = "1";
    public static final String FAMILY_NOT_FOUND = "NFE004";
    public static final int FORBIDDEN_ACTION = 403;
    public static final String INDIAN_MODEL_TYPE = "2";
    public static final int INVALID_TOKEN = 400;
    public static final int KII_EXCEPTION = 406;
    public static final int NOT_FOUND = 404;
    public static final String NO_RAC_FOUND = "NFE011";
    public static final String RAC_DOES_NOT_BELONG_TO_THIS_FAMILY = "NFE012";
    public static final String RAC_OFFLINE = "PCF009";
    public static final int SUCCESS_STATUS_CODE = 200;
    public static final int UNAUTHORIZED = 401;
    public static final int UNKNOWN_STATUS_CODE = 10009;
    public static final String USER_DETAILS_NOT_FOUND = "NFE002";
    public static final String USER_NOT_FAMILY_MEMBER = "NFE009";
    public static final String USER_NOT_FAMILY_OWNER = "NFE003";
    public static final String USER_NOT_HAVE_PERMISSION = "FAE007";

    public interface Method {

        /* renamed from: AP */
        public static final String f423AP = "ap";
        public static final String WPS = "wps";
    }
}
