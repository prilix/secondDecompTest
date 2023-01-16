package com.jch.racWiFi.p010di.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.C1662R2;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.dataClasses.Country;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* renamed from: com.jch.racWiFi.di.util.Constants */
public interface Constants {
    public static final long CONNECT_TIMEOUT = 15;
    public static final String DISPLAY_FORMAT = "display_format";
    public static final long READ_TIMEOUT = 60;
    public static final String SWITCH = "switch";
    public static final long WRITE_TIMEOUT = 60;
    public static final int[] arrArrivingMeter = {500, 1000, C1662R2.C1663color.ripple_material_light, 3000, C1662R2.C1665id.cb_member_permission, 5000, C1662R2.C1665id.text_view_enter_email_or_mobile_number, C1662R2.layout.material_timepicker_dialog, C1662R2.string.country_nicaragua_code, C1662R2.string.serviceRequest_lbl_pleaseSelectMethodForRaisingServiceRequest, 10000};
    public static final int[] arrLivingMeter = {500, 600, 700, C1662R2.attr.materialDividerStyle, 900, 1000};

    /* renamed from: com.jch.racWiFi.di.util.Constants$Alexa */
    public interface Alexa {
        public static final String AUTH_CODE = "auth_code";
        public static final String AUTH_SCOPE = "auth_scope";
        public static final String AUTH_STATE = "amazon_authorization_state";
        public static final String CALLBACK = "Callback";
        public static final String CODE = "code";
        public static final String ERROR = "error";
        public static final String ERROR_DESCRIPTION = "error_description";
        public static final String OAUTH_CODE = "oauth_code";
        public static final String OAUTH_STATE = "oauth_state";
        public static final String REDIRECT_URL = "redirect_url";
        public static final String SCOPE = "scope";
        public static final String STATE = "state";
        public static final String STATE_CODE = "12345";
        public static final String URI = "uri";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$ContentType */
    public interface ContentType {
        public static final String APPLICATION_JSON = "application/json";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$DateFormat */
    public interface DateFormat {
        public static final String FORMAT_1 = "dd/MM/yyyy";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$FCM */
    public interface FCM {
        public static final String CATEGORY = "category";
        public static final char DASH = '-';
        public static final String DATE_PATTERN = "dd MMM yyyy, hh:mm a";
        public static final String DEVICE_ID = "device-id";
        public static final String EC_PERCENTAGE = "ecPercentage";
        public static final String ERROR_CODE = "errCode";
        public static final String FAMILY_ID = "familyId";
        public static final String FAMILY_NAME = "familyName";
        public static final String HOME_NAME = "homeName";
        public static final String INFO_URL = "infoUrl";
        public static final String MAINTENANCE_DATE = "maintenanceDate";
        public static final String MAINTENANCE_DURATION = "maintenanceDuration";
        public static final String MEMBER_NAME = "memberName";
        public static final String MODE = "mode";
        public static final String NOTIFICATION_CATEGORY = "notification-category";
        public static final String NOTIFICATION_ID = "notification-id";
        public static final String RAC_ID = "racId";
        public static final String RAC_INCLUDED = "racIncluded";
        public static final String RAC_NAME = "racName";
        public static final String REMOTE_MESSAGE = "RemoteMessage";
        public static final String REMOTE_MESSAGE_BROADCAST_RECEIVER = "RemoteMessageBroadcastReceiver";
        public static final String ROLE_NAME = "roleName";
        public static final String SUB_CATEGORY = "subCategory";
        public static final String TEMPERATURE = "temperature";
        public static final String TEMPERATURE_UNIT = "temperatureUnit";
        public static final char UNDER_SCORE = '_';
        public static final String USER_ID = "userId";
        public static final String USER_NAME = "userName";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$GrantedPermissions */
    public interface GrantedPermissions {
        public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
        public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$HttpMethods */
    public interface HttpMethods {
        public static final String DELETE = "DELETE";
        public static final String GET = "GET";
        public static final String POST = "POST";
        public static final String PUT = "PUT";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$Intents */
    public interface Intents {
        public static final String RAC_HOME_DETAIL = "racHomeDetail";
        public static final String VENDOR_THING_ID = "vendorThingId";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$Json */
    public interface Json {
        public static final String APP_VERSION = "app_version.json";
        public static final String CONFIG_INIT = "config_init.json";
        public static final String COUNTRY = "country.json";
        public static final String CUSTOMER_SUPPORT = "customer_support.json";
        public static final String EC_ALL_RAC = "ec_all_rac.json";
        public static final String EC_SUMMARY_V3 = "ec_summary_v3.json";
        public static final String ENERGY_CONSUMPTION_BUDGET_DATA = "energyConsumptionBudgetSetup.json";
        public static final String ENERGY_CONSUMPTION_MONTHLY_GRAPH = "energyConsumptionMonthlyData.json";
        public static final String ENERGY_CONSUMPTION_PREV_MONTH_GRAPH = "energyConsumptionPrevMonthData.json";
        public static final String ENERGY_CONSUMPTION_PREV_WEEK_GRAPH = "energyConsumptionPrevWeek.json";
        public static final String ENERGY_CONSUMPTION_PREV_YEAR_GRAPH = "energyConsumptionPrevYearly.json";
        public static final String ENERGY_CONSUMPTION_SUMMARY = "energyConsumptionSummary.json";
        public static final String ENERGY_CONSUMPTION_WEEKLY_GRAPH = "energyConsumptionWeeklyData.json";
        public static final String ENERGY_CONSUMPTION_YEARLY_GRAPH = "energyConsumptionYearlyData.json";
        public static final String EXTENSION = ".json";
        public static final String FAMILY_GROUP = "family_group.json";
        public static final String FAMILY_INVITATIONS = "family_invitations.json";
        public static final String FAMILY_LIST_BY_FAMILY_ID = "family_list_by_family_id.json";
        public static final String FUNCTIONAL_ACCESS = "functional_access.json";
        public static final String HOLIDAY_MODE = "holiday_mode.json";
        public static final String IDU_MODEL_LIST = "idu_model_list.json";
        public static final String JSON = "json";
        public static final String PATH = (JSON + File.separator);
        public static final String PRIVACY_POLICY = "private_policy.json";
        public static final String RAC_CONFIGURATION_MODEL_WISE = "rac_configuration_model_wise.json";
        public static final String RAC_USER_MANUAL = "rac_user_manual.json";
        public static final String SMART_FENCE_MEMBERS = "smart_fence_members.json";
        public static final String SMART_FENCE_SETTINGS = "smart_fence_settings.json";
        public static final String SMART_FENCE_STATUS = "smart_fence_status.json";
        public static final String TIMER = "timer.json";
        public static final String USER_INFO = "user_info.json";
        public static final String USER_LIST = "user_list.json";
        public static final String USER_NOTIFICATIONS = "user_notifications.json";
        public static final String USER_PERMISSIONS = "user_permissions.json";
        public static final String WEATHER_INFO_EN = "weather_info_en.json";
        public static final String WEATHER_INFO_JP = "weather_info_jp.json";
        public static final String WEB_SOCKET_NOTIFICATION = "web_socket_notification.json";
        public static final String WEEKLY_TIMER = "weekly_timer.json";
        public static final String WEEKLY_TIMER_SCHEDULE_COUNT = "weekly_timer.json.json";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$JsonKeys */
    public interface JsonKeys {
        public static final String API_LEVEL = "apiLevel";
        public static final String APP_PLATFORM = "appPlatform";
        public static final String APP_VERSION = "appVersion";
        public static final String COUNTRY_CODE = "countryCode";
        public static final String COUNTRY_NAME = "countryName";
        public static final String DEVICE_VERSION = "deviceVersion";
        public static final String IS_ENABLED = "isEnabled";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$Keys */
    public interface Keys {
        public static final String ADAPTER_TYPE = "adapter_type";
        public static final String COUNTRY_CODE = "country-code";
        public static final String CURRENT_DATE = "currentDate";
        public static final String DETAILED_IDU_MODEL = "DetailedIduModel";
        public static final String ENTRY_TIME = "entry_time";
        public static final String ERROR_STATE = "errorState";
        public static final String ERROR_STATUS = "errorStatus";
        public static final String EXPIRED_TOKEN = "EXPIRED_TOKEN";
        public static final String FROM = "from";
        public static final String INITIALIZATION_VECTOR = "initializationVector";
        public static final String IS_ACCOUNT_DELETED = "IsAccountDeleted";
        public static final String IS_AMPLITUDE_ENABLED = "is_amplitude_enabled";
        public static final String IS_COUNTRY_SUPPORT_MILES = "isCountrySupportMiles";
        public static final String IS_LOGIN = "isLogin";
        public static final String IS_REFRESH_TOKEN = "isRefreshToken";
        public static final String[] KEYS = {IS_AMPLITUDE_ENABLED};
        public static final String KILO_METER = "km";
        public static final String LATEST_VERSION = "latestVersion";
        public static final String METER = "m";
        public static final String METHOD = "Method";
        public static final String MILES = "mi";
        public static final String MINIMUM_VERSION = "minimumVersion";

        /* renamed from: P */
        public static final String f429P = "JohnsonControls";

        /* renamed from: S */
        public static final String f430S = "Hitachi";
        public static final String SPACE = " ";
        public static final String STORE_LINK = "storeLink";
        public static final String TOKEN_INFO = "TokenInfo";
        public static final String TOKEN_RESPONSE = "TokenResponse";
        public static final String UNIT = "unit";
        public static final String VENDOR_THING_ID = "vendorThingId";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$Link */
    public interface Link {
        public static final String AMPLITUDE = "/app-settings/amplitude/status";
        public static final String APP_VERSION = "/management/app-version/v1/ANDROID";
        public static final String CONFIG_INIT = "/config/init";
        public static final String COUNTRY_UNIT = "/app-settings/country-distance-measurement-unit";
        public static final String CUSTOMER_SUPPORT = "/help/customer-support";
        public static final String DELETE_PICTURE = "/user/v2/profile-picture/delete";
        public static final String EC_ALL_RAC = "energy-consumptions/all/racs";
        public static final String EC_SETTINGS = "/energy-consumptions/settings";
        public static final String EC_SUMMARY = "/energy-consumptions/summary/";
        public static final String ENERGY_CONSUMPTION_GRAPH_BUDGET = "/budget-settings/data?familyId=180";
        public static final String ENERGY_CONSUMPTION_GRAPH_BUDGET_SAVE = "/budget-settings/save";
        public static final String ENERGY_CONSUMPTION_GRAPH_DATA = "/energy-consumptions?familyId=180";
        public static final String ENERGY_CONSUMPTION_SUMMARY = "/energy-consumptions/summary/v3?familyId=180";
        public static final String FAMILY_ACCOUNT = "/family-account/";
        public static final String FAMILY_GROUP = "/family-account/v2/groups";
        public static final String FAMILY_INVITATIONS = "/family-account/family-invitations";
        public static final String FAMILY_LIST_1 = "/family-account/v2/groups/";
        public static final String FCM_DEREGISTER = "/user/device/de-register";
        public static final String FCM_REGISTER = "/user/device";
        public static final String FUNCTIONAL_ACCESS_2 = "permissions/has-functional-access";
        public static final String GET_HOLIDAY_MODE = "/scheduled-operations/holidayModeSchedule/schedules";
        public static final String HELP = "/help/";
        public static final String IDU_FROST_WASH = "/clean/idu-frost-wash/";
        public static final String IDU_LIST = "idu-list/";
        public static final String IDU_MODEL_LIST = "/ownership/groups/cloudIds/";
        public static final String IDU_RENAME = "/manage-idu/update/";
        public static final String IDU_START = "idu/start";
        public static final String IDU_STATUS = "/status/command";
        public static final String IDU_STOP = "idu/stop";
        public static final String INTERRUPT_HOLIDAY_MODE = "/scheduled-operations/holidayModeSchedule/disableHolidayMode";
        public static final String INVITE = "invite";
        public static final String INVITE_USER = "/family-account/groups/";
        public static final String MANAGE_IDU_GROUPS = "/manage-idu/groups/";
        public static final String MEMBERS = "members";
        public static final String ON_BOARD_IDU = "/on-board/groups/180/idu";
        public static final String ON_BOARD_IDU_INDIA = "/on-board/groups/180/idu/india";
        public static final String ON_BOARD_IDU_INFO = "/on-board/idu/info";
        public static final String OWNERSHIP_GROUPS = "/ownership/groups/";
        public static final String PERMISSIONS = "permissions";
        public static final String PRIVACY_POLICY = "/management/privacy-policy";
        public static final String RAC_CONFIGURATION_MODEL_WISE = "/model-wise/rac-configuration";
        public static final String RAC_USER_MANUAL = "/help/rac-user-manual/v2";
        public static final String REFRESH_TOKEN = "/auth/refresh-token";
        public static final String SCHEDULED_OPERATIONS = "/scheduled-operations/";
        public static final String SCHEDULE_ENABLE_DISABLE = "/scheduled-operations/racs/schedules/enableDisable";
        public static final String SMART_FENCE_MEMBERS = "?x=";
        public static final String SMART_FENCE_SETTINGS = "/location-controls/settings";
        public static final String SMART_FENCE_STATUS = "/location-controls/status";
        public static final String STATUS = "/status/";
        public static final String TIMER_1 = "/scheduled-operations/timer/racs/";
        public static final String UPDATE_ADDRESS = "/user/update-address";
        public static final String UPDATE_HOLIDAY_MODE = "/scheduled-operations/holidayModeSchedule/schedules";
        public static final String UPDATE_IDU_STATE_AS_WHOLE = "/basic-idu-control/general-control-command/";
        public static final String UPDATE_NAME = "/user/update-name";
        public static final String UPLOAD_PICTURE = "/user/v2/profile-picture/upload";
        public static final String USER_INFO = "/user/v2/who-am-i";
        public static final String USER_NOTIFICATIONS = "/user/notifications";
        public static final String V4_UPDATE_ADDRESS = "/user/v4/update-address";
        public static final String WEATHER_INFO = "/weather/info";
        public static final String WEEKLY_TIMER_1 = "/scheduled-operations/weekly-timer/racs/";
        public static final String WEEKLY_TIMER_2 = "schedules?familyId=";
        public static final String WEEKLY_TIMER_COPY_SCHEDULE_DAY_WISE = "/scheduled-operations/weekly-timer/racs/schedules/copy-day-wise";
        public static final String WEEKLY_TIMER_COPY_SCHEDULE_RAC_WISE = "/scheduled-operations/weekly-timer/racs/schedules/copy-rac-wise";
        public static final String WEEKLY_TIMER_OPERATIONS = "/scheduled-operations/weekly-timer/racs/schedules?familyId=";
        public static final String WEEKLY_TIMER_SCHEDULE_COUNT = "/scheduled-operations/weekly-timer/racs/schedules/count";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$Meta */
    public interface Meta {
        public static final String BODY = "body";
        public static final String CODE = "code";
        public static final String MESSAGE = "message";
        public static final String META = "meta";
        public static final String RESULT = "result";
        public static final String TOKEN = "token";
        public static final String TYPE = "type";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$Multiple */
    public interface Multiple {
        public static final double DECIMAL_THOUSAND = 1000.0d;
        public static final int FIVE_HUNDRED = 500;
        public static final int HUNDRED = 100;
        public static final int THOUSAND = 1000;
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$NetworkParams */
    public interface NetworkParams {
        public static final String ACCEPT = "Accept";
        public static final String APPLICATION_JSON = "application/json";
        public static final String AUTHORIZATION = "Authorization";
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String DEREGISTER_TOKEN = "Deregister-Token";
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$StatusCode */
    public interface StatusCode {
        public static final int SUCCESS = 200;
    }

    /* renamed from: com.jch.racWiFi.di.util.Constants$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            long j = Constants.CONNECT_TIMEOUT;
        }

        public static int getLeavingValue(double d) {
            int i = (int) (d / 100.0d);
            int i2 = (int) (d % 100.0d);
            return (i2 < 50 || i2 > 100) ? i : i + 1;
        }

        public static Resources getResource(Context context) {
            Configuration configuration = context.getResources().getConfiguration();
            configuration.locale = LocaleConfiguration.getCurrentAppLocale();
            return new Resources(context.getAssets(), new DisplayMetrics(), configuration);
        }

        public static double metersToMilesArriving(double d) {
            return ((double) Math.round((d / 1609.3440057765d) * 100.0d)) / 100.0d;
        }

        public static double metersToMilesLeaving(double d) {
            return Double.parseDouble(String.valueOf(d / 1609.3440057765d));
        }

        public static double milesToMeters(double d) {
            return ((double) Math.round((d / 6.213711E-4d) * 10.0d)) / 10.0d;
        }

        public static double getLivingMiles(double d) {
            return Math.max(Double.parseDouble(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(d)})), 0.5d);
        }

        public static String getCountryCode(Context context) {
            return ((TelephonyManager) context.getSystemService("phone")).getSimCountryIso().toUpperCase();
        }

        public static String getProgress(String str) {
            String[] split = str.split(" ");
            String str2 = split[1];
            str2.hashCode();
            char c = 65535;
            switch (str2.hashCode()) {
                case 109:
                    if (str2.equals(Keys.METER)) {
                        c = 0;
                        break;
                    }
                    break;
                case C1662R2.C1664drawable.ic_red_arrow:
                    if (str2.equals(Keys.KILO_METER)) {
                        c = 1;
                        break;
                    }
                    break;
                case C1662R2.C1664drawable.ic_weeklytimer_fan:
                    if (str2.equals(Keys.MILES)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return String.format(Locale.US, "%s", new Object[]{split[0]});
                case 1:
                case 2:
                    double parseDouble = Double.parseDouble(split[0]) * 1000.0d;
                    if (parseDouble % 1.0d == C1030Utils.DOUBLE_EPSILON) {
                        return String.format(Locale.US, TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf((int) parseDouble)});
                    }
                    return String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(parseDouble)});
                default:
                    return null;
            }
        }

        public static double roundToHalf(double d) {
            return Math.ceil(d * 2.0d) / 2.0d;
        }

        public static String getArrivingRange(double d, String str, boolean z) {
            str.hashCode();
            if (!str.equals(Keys.KILO_METER)) {
                if (!str.equals(Keys.MILES)) {
                    return null;
                }
                if (!z) {
                    return getValue(roundToHalf(metersToMilesArriving(d)), str);
                }
                return getValue(Double.parseDouble(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(d / 1000.0d)})), str);
            } else if (!z) {
                double roundToHalf = roundToHalf(d / 1000.0d);
                if (roundToHalf <= 0.5d) {
                    return getValue(roundToHalf * 1000.0d, Keys.METER);
                }
                if (roundToHalf > 10.0d) {
                    return getValue(10.0d, str);
                }
                return getValue(roundToHalf, str);
            } else if (d <= 500.0d) {
                return String.format(Locale.US, "%d %s", new Object[]{500, Keys.METER});
            } else {
                return getValue(Double.parseDouble(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(d / 1000.0d)})), str);
            }
        }

        public static String getValue(double d, String str) {
            if (d % 1.0d == C1030Utils.DOUBLE_EPSILON) {
                return String.format(Locale.US, "%d %s", new Object[]{Integer.valueOf((int) d), str});
            }
            return String.format(Locale.US, "%.1f %s", new Object[]{Double.valueOf(d), str});
        }

        public static String getLeavingRange(double d, String str, boolean z) {
            str.hashCode();
            if (!str.equals(Keys.KILO_METER)) {
                if (!str.equals(Keys.MILES)) {
                    return null;
                }
                if (z) {
                    return getValue(d / 1000.0d, str);
                }
                return getValue(getLivingMiles(metersToMilesLeaving(d)), str);
            } else if (z) {
                return d < 1000.0d ? getValue(d, Keys.METER) : getValue(d / 1000.0d, str);
            } else {
                int leavingValue = getLeavingValue(d);
                return leavingValue < 10 ? getValue((double) (leavingValue * 100), Keys.METER) : getValue(1.0d, str);
            }
        }

        public static String getCountryCode(Context context, Location location) throws IOException {
            if (location == null) {
                return "IN";
            }
            List<Address> fromLocation = new Geocoder(context).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (fromLocation.isEmpty()) {
                return null;
            }
            return fromLocation.get(0).getCountryCode();
        }

        public static String getCountryCode() {
            if (Build.VERSION.SDK_INT >= 24) {
                return Resources.getSystem().getConfiguration().getLocales().get(0).getCountry();
            }
            return Resources.getSystem().getConfiguration().locale.getCountry();
        }

        public static Country getCountry() {
            if (Build.VERSION.SDK_INT >= 24) {
                return new Country(Resources.getSystem().getConfiguration().getLocales().get(0).getCountry(), Resources.getSystem().getConfiguration().getLocales().get(0).getDisplayCountry());
            }
            return new Country(Resources.getSystem().getConfiguration().locale.getCountry(), Resources.getSystem().getConfiguration().locale.getDisplayCountry());
        }

        public static String getVersion(String str) {
            return (!str.contains("(") || !str.contains(")")) ? str : str.substring(0, str.length() - 3);
        }

        public static boolean isDateChanged(String str, String str2, String str3) {
            try {
                Date parse = new SimpleDateFormat(str3, Locale.getDefault()).parse(str);
                Date parse2 = new SimpleDateFormat(str3, Locale.getDefault()).parse(str2);
                if (parse == null || parse2 == null || parse.compareTo(parse2) >= 0) {
                    return false;
                }
                return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }

        public static String getCurrentDate(String str) {
            return new SimpleDateFormat(str, Locale.getDefault()).format(Calendar.getInstance().getTime());
        }

        public static long getSeconds(long j) {
            return (j / 1000) % 60;
        }

        public static float getHeight(DisplayMetrics displayMetrics) {
            if (displayMetrics.density < 2.0f || displayMetrics.density >= 3.0f || displayMetrics.heightPixels > 2000) {
                return 0.6f;
            }
            return 0.72f;
        }

        public static String getRawResponse(Response response) {
            ResponseBody body;
            if (!(response == null || (body = response.body()) == null)) {
                try {
                    return body.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        public static String getRawResponse(ResponseBody responseBody) {
            if (responseBody != null) {
                try {
                    return responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
