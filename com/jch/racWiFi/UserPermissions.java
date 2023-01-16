package com.jch.racWiFi;

import android.content.Context;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.CertificatePinner;

public class UserPermissions {
    private static final UserPermissions ourInstance = new UserPermissions();
    public HashMap<String, String> permissionForIdu = new HashMap<>();
    private HashMap<String, String> permissionNames = new HashMap<>();
    private HashMap<String, Boolean> permissions = new HashMap<>();
    private boolean permissionsMapObtained = false;

    public static class UserFeatures {
        public static String ACCOUNT_RECOVERY_INIT = "ACCOUNT_RECOVERY_INIT";
        public static String ADD_DEVICES = "ADD_DEVICES";
        public static String ADD_MEMBERS = "ADD_MEMBERS";
        public static String CHANGE_PASSWORD = "CHANGE_PASSWORD";
        public static String CUSTOMER_CARE = "CUSTOMER_CARE";
        public static String ENERGY_CONSUMPTION = "ENERGY_CONSUMPTION";
        public static String FEEDBACK = "FEEDBACK";
        public static String FROST_WASH = "FROST_WASH";
        public static String GET_RAC_WISE_PERMISSION_SETTINGS = "GET_RAC_WISE_PERMISSION_SETTINGS";
        public static String HELP = "HELP";
        public static String HOLIDAY_MODE = "HOLIDAY_MODE";
        public static String HOME = "HOME";
        public static String HOME_PAGE = "HOME_PAGE";
        public static String IDU_LIST = "IDU_LIST";
        public static String IDU_ONBOARD = "IDU_ONBOARD";
        public static String INDIVIDUAL_IDU_PAGE = "INDIVIDUAL_IDU_PAGE";
        public static String LOGOUT = "LOGOUT";
        public static String MANAGE_DEVICES = "MANAGE_DEVICES";
        public static String MANAGE_HOMES = "MANAGE_HOMES";
        public static String MANAGE_USERS = "MANAGE_USERS";
        public static String MY_ACCOUNT = "MY_ACCOUNT";
        public static String NOTIFICATIONS = "NOTIFICATIONS";
        public static String SERVICE_REQUEST = "SERVICE_REQUEST";
        public static String SETTINGS = "SETTINGS";
        public static String SETTINGS_APP_LOCK = "SETTINGS_APP_LOCK";
        public static String SETTINGS_DARK_UI = "SETTINGS_DARK_UI";
        public static String SETTINGS_LANGUAGE = "SETTINGS_LANGUAGE";
        public static String SETTINGS_LOCATION_CONTROLS = "SMART_FENCE";
        public static String SETTINGS_OUT_OF_HOME_REMINDER = "SETTINGS_OUT_OF_HOME_REMINDER";
        public static String SETTINGS_USER_PREFRENCE = "SETTINGS_USER_PREFERENCE";
        public static String SMART_FENCE = "SMART_FENCE";
        public static String WEEKLY_TIMER = "WEEKLY_TIMER_IDU";
    }

    public boolean isPermissionsMapObtained() {
        return this.permissionsMapObtained;
    }

    public void setPermissionsMapObtained(boolean z) {
        this.permissionsMapObtained = z;
    }

    public void updatePermissionString(Context context) {
        init(context);
    }

    public static class IduFeatures {
        public static String AUTO_CHANGE = "AUTO_CHANGE";
        public static String CLEANING_MODE = "CLEANING_MODE";
        public static String ECO = "ECO";
        public static String FAN = "FAN";
        public static String HUMIDITY_SMALL = "HUMIDITY";
        public static String INDIVIDUAL_IDU_PAGE = "INDIVIDUAL_IDU_PAGE";
        public static String MODE = "MODE";
        public static String ON_OFF = "ON_OFF";
        public static String SWING = "SWING";
        public static String TEMPRATURE_SMALL = "TEMPERATURE";
        public static String TIMER = "TIMER";
        public static String WEEKLY_TIMER = "WEEKLY_TIMER_IDU";
        public static ArrayList<String> iduPermissionNames;

        static {
            ArrayList<String> arrayList = new ArrayList<>();
            iduPermissionNames = arrayList;
            arrayList.add(INDIVIDUAL_IDU_PAGE);
            iduPermissionNames.add(ON_OFF);
            iduPermissionNames.add(TEMPRATURE_SMALL);
            iduPermissionNames.add(FAN);
            iduPermissionNames.add(HUMIDITY_SMALL);
            iduPermissionNames.add(MODE);
            iduPermissionNames.add(SWING);
            iduPermissionNames.add(TIMER);
            iduPermissionNames.add(WEEKLY_TIMER);
            iduPermissionNames.add(UserFeatures.FROST_WASH);
        }
    }

    public static UserPermissions getInstance() {
        return ourInstance;
    }

    private UserPermissions() {
    }

    private String getStringFromRes(Context context, int i) {
        return context.getString(i);
    }

    public void init(Context context) {
        this.permissionNames.put(UserFeatures.HOME, getStringFromRes(context, R.string.common_lbl_home));
        this.permissionNames.put(UserFeatures.FROST_WASH, getStringFromRes(context, R.string.notification_lbl_frostWashIndoor));
        this.permissionNames.put(UserFeatures.WEEKLY_TIMER, getStringFromRes(context, R.string.weeklyTimer_lbl_weeklyTimer));
        this.permissionNames.put(UserFeatures.HOLIDAY_MODE, getStringFromRes(context, R.string.menu_item_holidaymode));
        this.permissionNames.put(UserFeatures.MANAGE_USERS, getStringFromRes(context, R.string.manageUser_lbl_manageUser));
        this.permissionNames.put(UserFeatures.MANAGE_DEVICES, getStringFromRes(context, R.string.manageAc_lbl_manageAcs));
        this.permissionNames.put(UserFeatures.ENERGY_CONSUMPTION, getStringFromRes(context, R.string.energyConsumption_lbl_energyCostEstimator));
        this.permissionNames.put(UserFeatures.CUSTOMER_CARE, getStringFromRes(context, R.string.customerCare_lbl_customerCare));
        this.permissionNames.put(UserFeatures.HELP, getStringFromRes(context, R.string.help_lbl_help));
        this.permissionNames.put(UserFeatures.MY_ACCOUNT, getStringFromRes(context, R.string.myAcc_lbl_myAcc));
        this.permissionNames.put(UserFeatures.HOME_PAGE, getStringFromRes(context, R.string.userPermission_lbl_homePage));
        this.permissionNames.put(UserFeatures.NOTIFICATIONS, getStringFromRes(context, R.string.home_lbl_notifications));
        this.permissionNames.put(UserFeatures.ADD_MEMBERS, getStringFromRes(context, R.string.userPermission_lbl_addMembers));
        this.permissionNames.put(UserFeatures.ADD_DEVICES, getStringFromRes(context, R.string.userPermission_lbl_addACs));
        this.permissionNames.put(UserFeatures.INDIVIDUAL_IDU_PAGE, getStringFromRes(context, R.string.userPermission_lbl_iduPage));
        this.permissionNames.put(UserFeatures.MANAGE_HOMES, getStringFromRes(context, R.string.myAcc_lbl_manageHomes));
        this.permissionNames.put(UserFeatures.SMART_FENCE, getStringFromRes(context, R.string.smartFence_lbl_smartFence));
        this.permissionNames.put(IduFeatures.ON_OFF, getStringFromRes(context, R.string.userPermission_lbl_onOff));
        this.permissionNames.put(IduFeatures.TEMPRATURE_SMALL, getStringFromRes(context, R.string.userPermission_lbl_temperature));
        this.permissionNames.put(IduFeatures.FAN, getStringFromRes(context, R.string.common_lbl_fan));
        this.permissionNames.put(IduFeatures.MODE, getStringFromRes(context, R.string.common_lbl_mode));
        this.permissionNames.put(IduFeatures.SWING, getStringFromRes(context, R.string.idu_lbl_swing));
        this.permissionNames.put(IduFeatures.HUMIDITY_SMALL, getStringFromRes(context, R.string.common_lbl_humidity));
        this.permissionNames.put(IduFeatures.TIMER, getStringFromRes(context, R.string.common_lbl_timer));
        this.permissionNames.put(IduFeatures.CLEANING_MODE, getStringFromRes(context, R.string.userPermission_lbl_cleaningMode));
        this.permissionNames.put(IduFeatures.WEEKLY_TIMER, getStringFromRes(context, R.string.weeklyTimer_lbl_weeklyTimer));
        this.permissionForIdu.put(UserFeatures.WEEKLY_TIMER, getStringFromRes(context, R.string.weeklyTimer_lbl_weeklyTimer));
        this.permissionForIdu.put(UserFeatures.INDIVIDUAL_IDU_PAGE, getStringFromRes(context, R.string.userPermission_lbl_iduPage));
        this.permissionForIdu.put(IduFeatures.ON_OFF, getStringFromRes(context, R.string.userPermission_lbl_onOff));
        this.permissionForIdu.put(IduFeatures.TEMPRATURE_SMALL, getStringFromRes(context, R.string.userPermission_lbl_temperature));
        this.permissionForIdu.put(IduFeatures.FAN, getStringFromRes(context, R.string.common_lbl_fan));
        this.permissionForIdu.put(IduFeatures.MODE, getStringFromRes(context, R.string.common_lbl_mode));
        this.permissionForIdu.put(IduFeatures.SWING, getStringFromRes(context, R.string.idu_lbl_swing));
        this.permissionForIdu.put(IduFeatures.HUMIDITY_SMALL, getStringFromRes(context, R.string.common_lbl_humidity));
    }

    public HashMap<String, Boolean> getPermissionContainer() {
        return this.permissions;
    }

    public void setPermissions(HashMap<String, Boolean> hashMap) {
        this.permissions = hashMap;
    }

    public boolean getPermission(String str) {
        String str2 = CertificatePinner.WILDCARD + str;
        if (!this.permissions.containsKey(str2)) {
            return false;
        }
        boolean booleanValue = this.permissions.get(str2).booleanValue();
        Logger.m50v("UP", "Permission " + str + " set to " + booleanValue);
        return booleanValue;
    }

    private boolean fallBackPermission(String str) {
        Logger.m47e("UP", "Permission " + str + " not found. Falling back to default");
        List<DetailedIduModel> onboardedIdus = ConfigurationDataProvider.getInstance().getOnboardedIdus();
        if (onboardedIdus == null || onboardedIdus.isEmpty()) {
            HashMap<String, Boolean> defaultIduAccessModelMap = MockProvider.getInstance().getDefaultIduAccessModelMap();
            if (!defaultIduAccessModelMap.containsKey(str + ".*")) {
                return false;
            }
            if (defaultIduAccessModelMap.get(str + ".*") != null) {
                if (defaultIduAccessModelMap.get(str + ".*").booleanValue()) {
                    return true;
                }
            }
            return false;
        }
        for (DetailedIduModel id : onboardedIdus) {
            Boolean bool = this.permissions.get(String.format("%s.%d", new Object[]{str, id.getId()}));
            if (bool != null) {
                return bool.booleanValue();
            }
        }
        return false;
    }

    public boolean getIduPermission(String str, Integer num) {
        HashMap<String, Boolean> hashMap = this.permissions;
        if (hashMap.containsKey(num + "." + str)) {
            HashMap<String, Boolean> hashMap2 = this.permissions;
            return hashMap2.get(num + "." + str).booleanValue();
        }
        HashMap<String, Boolean> hashMap3 = this.permissions;
        Boolean bool = hashMap3.get(CertificatePinner.WILDCARD + str);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public String getPermissionName(String str) {
        return this.permissionNames.get(str);
    }
}
