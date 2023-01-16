package com.jch.racWiFi;

import com.jch.racWiFi.C1662R2;
import com.jch.racWiFi.p010di.util.Constants;

public class Values {
    public static final int CHANGE_NETWORK_SETTING_CODE = 516;
    public static String CURRENCY_KEY = "CURRENCY_KEY";
    public static final String FAMILY_MEMBER_COUNT = "FAMILY_MEMBER_COUNT";
    public static final String FRESH_START_DETAILS_KEY = "freshstart";
    public static String FROM_DEVICE_SETTING = "fromDeviceSetting";
    public static final String FROM_MANAGE_USERS = "FROM_MANAGE_USERS";
    public static final String IDU_DETAILS_KEY = "idu_details";
    public static final String IDU_ID_KEY = "ikey";
    public static final String IDU_NAME_KEY = "idu_key";
    public static String MODE = Constants.FCM.MODE;
    public static String NAVIGATED_FROM = "nav_from";
    public static Integer NAVIGATED_FROM_AP_TUTORIAL = 256;
    public static final Integer NAVIGATED_FROM_HOME_NETWORK_SELECTION_PAGE = 512;
    public static final Integer NAVIGATED_FROM_RAC_WIFI_SELECTION_PAGE = 511;
    public static final Integer NAVIGATED_FROM_SOFT_AP_TUTORIAL = Integer.valueOf(C1662R2.attr.extendedFloatingActionButtonTertiaryStyle);
    public static final Integer NAVIGATED_FROM_WPS_TUTORIAL = 501;
    public static final String NAVIGATING_FROM = "NAVIGATING_FROM";
    public static String NOT_CONNECTED = "Not connected";
    public static final String PASSWORD_KEY = "password_key";
    public static final String PERMISSION_MODE_KEY = "ptype";
    public static String QR_DETAILS_KEY = "qrdetails";
    public static final String QR_SCAN_SUCCESS = "QR_SCAN_SUCCESS";
    public static String RAC_ID = "racId";
    public static String RAC_NAME = Constants.FCM.RAC_NAME;
    public static String SCAN_RESULT_HOME_ROUTER_KEY = "SCAN_RESULT_HOME_ROUTER_KEY";
    public static String SCAN_RESULT_RAC_KEY = "SCAN_RESULT_RAC_KEY";
    public static String SELECTED_HOME_NETWORK_KEY = null;
    public static String SELECTED_HOME_NETWORK_PASSWORD = "selected_home_network_pass";
    public static final String START_OVER_COUNT_KEY = "start_over";
    public static final String TO_ON_BOARDED_DIRECTLY = "TO_ON_BOARDED_DIRECTLY";

    public static class Operation {
        public static final String AC_MODE_COOL = "COOL";
    }

    /* renamed from: com.jch.racWiFi.Values$Permissions */
    public static class C1682Permissions {
        public static final int CHILD = 3;
        public static final int MEMBER = 2;
        public static final int MODE_ALL_IDU = -1;
        public static final int MODE_SPECIFIC_IDU = 2;
        public static final int OWNER = 1;
    }
}
