package com.jch.racWiFi.fcm.repository;

import android.app.Application;
import com.jch.racWiFi.App;
import com.jch.racWiFi.fcm.model.AcActivity;
import com.jch.racWiFi.fcm.model.Alert;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.model.Maintenance;
import com.jch.racWiFi.fcm.model.Reminder;
import com.jch.racWiFi.fcm.model.Silent;
import com.jch.racWiFi.fcm.model.SmartFence;
import com.jch.racWiFi.fcm.util.AcActivitiesSubCategory;
import com.jch.racWiFi.fcm.util.AlertSubCategory;
import com.jch.racWiFi.fcm.util.EnumUtil;
import com.jch.racWiFi.fcm.util.ErrorSubCategory;
import com.jch.racWiFi.fcm.util.MaintenanceSubCategory;
import com.jch.racWiFi.fcm.util.ReminderSubCategory;
import com.jch.racWiFi.fcm.util.SilentSubCategory;
import com.jch.racWiFi.fcm.util.SmartFenceSubCategory;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.p010di.util.Constants;
import java.io.File;
import java.util.Map;

public class ModelRepository {
    public ModelRepository(Application application) {
    }

    public Reminder getReminder(String str, long j, String str2, Map<String, String> map) {
        Reminder reminder = new Reminder();
        reminder.setInfoUrl(map.get(Constants.FCM.INFO_URL));
        reminder.setUserName(map.get(Constants.FCM.USER_NAME));
        reminder.setSubCategory((ReminderSubCategory) EnumUtil.getInstance().getString(ReminderSubCategory.class, str));
        reminder.setTimestamp(j);
        reminder.setId(str2);
        reminder.setFamilyId(map.get(Constants.FCM.FAMILY_ID));
        reminder.setFamilyName(map.get(Constants.FCM.FAMILY_NAME));
        return reminder;
    }

    public Maintenance getMaintenance(String str, String str2, Map<String, String> map) {
        Maintenance maintenance = new Maintenance();
        maintenance.setSubCategory((MaintenanceSubCategory) EnumUtil.getInstance().getString(MaintenanceSubCategory.class, str.replace(Constants.FCM.DASH, Constants.FCM.UNDER_SCORE)));
        String str3 = map.get(Constants.FCM.MAINTENANCE_DATE);
        if (str3 != null) {
            maintenance.setMaintenanceDate(Long.parseLong(str3));
        }
        maintenance.setId(str2);
        String str4 = map.get(Constants.FCM.MAINTENANCE_DURATION);
        if (str4 != null) {
            maintenance.setMaintenanceDuration(Long.parseLong(str4));
        }
        return maintenance;
    }

    public Alert getAlert(String str, long j, String str2, Map<String, String> map) {
        Alert alert = new Alert();
        alert.setSubCategory((AlertSubCategory) EnumUtil.getInstance().getString(AlertSubCategory.class, str));
        alert.setTimestamp(j);
        alert.setId(str2);
        alert.setEcPercentage(map.get(Constants.FCM.EC_PERCENTAGE));
        alert.setRacName(map.get(Constants.FCM.RAC_NAME));
        if (map.get("racId") != null) {
            try {
                alert.setRacId(Long.valueOf(Long.parseLong(map.get("racId"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        alert.setFamilyId(map.get(Constants.FCM.FAMILY_ID));
        alert.setFamilyName(map.get(Constants.FCM.FAMILY_NAME));
        alert.setHomeName(map.get(Constants.FCM.HOME_NAME));
        alert.setRoleName(map.get(Constants.FCM.ROLE_NAME));
        alert.setMemberName(map.get(Constants.FCM.MEMBER_NAME));
        alert.setUserName(map.get(Constants.FCM.USER_NAME));
        return alert;
    }

    public Error getError(String str, long j, String str2, Map<String, String> map) {
        Error error = new Error();
        error.setErrorSubCategory((ErrorSubCategory) EnumUtil.getInstance().getString(ErrorSubCategory.class, str));
        error.setName(map.get(Constants.FCM.RAC_NAME));
        if (map.get("racId") != null) {
            error.setRacId(Long.valueOf(map.get("racId")));
        }
        error.setTimestamp(j);
        error.setId(str2);
        error.setErrorCode(map.get(Constants.FCM.ERROR_CODE));
        error.setFamilyId(map.get(Constants.FCM.FAMILY_ID));
        error.setVendorThingId(map.get("vendorThingId"));
        error.setSubCategory1(str);
        return error;
    }

    public SmartFence getSmartFence(String str, long j, String str2, Map<String, String> map) {
        SmartFence smartFence = new SmartFence();
        smartFence.setSubCategory((SmartFenceSubCategory) EnumUtil.getInstance().getString(SmartFenceSubCategory.class, str));
        smartFence.setRacName(map.get(Constants.FCM.RAC_NAME));
        smartFence.setUserName(map.get(Constants.FCM.USER_NAME));
        if (map.get("racId") != null) {
            smartFence.setRacId(Long.valueOf(map.get("racId")));
        }
        smartFence.setRacIncluded(map.get(Constants.FCM.RAC_INCLUDED));
        smartFence.setUserId(map.get(Constants.FCM.USER_ID));
        smartFence.setFamilyId(map.get(Constants.FCM.FAMILY_ID));
        smartFence.setFamilyName(map.get(Constants.FCM.FAMILY_NAME));
        smartFence.setMode(map.get(Constants.FCM.MODE));
        smartFence.setTemperature(map.get(Constants.FCM.TEMPERATURE));
        smartFence.setTemperatureUnit("temperatureUnit");
        smartFence.setTimestamp(j);
        smartFence.setId(str2);
        return smartFence;
    }

    public Silent getSilent(String str) {
        Silent silent = new Silent();
        silent.setType(Type.SILENT);
        silent.setSubCategory((SilentSubCategory) EnumUtil.getInstance().getString(SilentSubCategory.class, str));
        return silent;
    }

    public AcActivity getAcActivity(DetailedIduModel detailedIduModel, AcActivitiesSubCategory acActivitiesSubCategory) {
        if (!detailedIduModel.isInSpecialMode()) {
            return null;
        }
        AcActivity acActivity = new AcActivity();
        acActivity.setSubCategory(acActivitiesSubCategory);
        acActivity.setRacName(detailedIduModel.name);
        acActivity.setRacId(Long.valueOf(detailedIduModel.f454id.longValue()));
        acActivity.setType(Type.AC_ACTIVITIES);
        acActivity.setTimestamp(System.currentTimeMillis());
        return acActivity;
    }

    public void clearDirectory() {
        String[] list;
        File file = new File(String.valueOf(App.getApplicatonContext().getFilesDir()));
        if (file.isDirectory() && (list = file.list()) != null && list.length > 0) {
            for (String file2 : list) {
                new File(file, file2).delete();
            }
        }
    }
}
