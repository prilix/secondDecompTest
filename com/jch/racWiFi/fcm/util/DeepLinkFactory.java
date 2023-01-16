package com.jch.racWiFi.fcm.util;

import android.app.Application;
import com.jch.racWiFi.fcm.model.Alert;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.model.Maintenance;
import com.jch.racWiFi.fcm.model.Reminder;
import com.jch.racWiFi.fcm.model.SmartFence;
import com.jch_hitachi.aircloudglobal.R;

public class DeepLinkFactory {
    public DeepLinkFactory(Application application) {
    }

    public DeepLink getDeepLink(SmartFence smartFence) {
        DeepLink deepLink = new DeepLink();
        deepLink.setRacId(smartFence.getRacId());
        deepLink.setType(smartFence.getType());
        deepLink.addDestination(Integer.valueOf(R.id.action_homePageFragment_to_smart_fence_navGraph));
        return deepLink;
    }

    public DeepLink getDeepLink(Alert alert) {
        DeepLink deepLink = new DeepLink();
        switch (C18351.$SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory[alert.getSubCategory().ordinal()]) {
            case 1:
                deepLink.setRacId(alert.getRacId());
                deepLink.addDestination(Integer.valueOf(R.id.action_homePageFragment_to_energy_consumption_NavGraph));
                deepLink.setType(alert.getType());
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                deepLink.setRacId(alert.getRacId());
                deepLink.addDestination(Integer.valueOf(R.id.homePageFragment));
                deepLink.setType(alert.getType());
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                deepLink.setRacId(alert.getRacId());
                deepLink.addDestination(Integer.valueOf(R.id.action_homePageFragment_to_holiday_mode_fragment));
                deepLink.setType(alert.getType());
                break;
        }
        return deepLink;
    }

    public DeepLink getDeepLink(Error error) {
        int i = C18351.$SwitchMap$com$jch$racWiFi$fcm$util$ErrorSubCategory[error.getErrorSubCategory().ordinal()];
        if (i != 1 && i != 2) {
            return null;
        }
        DeepLink deepLink = new DeepLink();
        deepLink.setRacId(error.getRacId());
        deepLink.setRacName(error.getName());
        deepLink.addDestination(Integer.valueOf(R.id.individualIDUControlFragment));
        deepLink.addDestination(Integer.valueOf(R.id.customerCareFragment));
        deepLink.setType(error.getType());
        return deepLink;
    }

    public DeepLink getDeepLink(Reminder reminder) {
        int i = C18351.$SwitchMap$com$jch$racWiFi$fcm$util$ReminderSubCategory[reminder.getSubCategory().ordinal()];
        if (i != 1 && i != 2) {
            return null;
        }
        DeepLink deepLink = new DeepLink();
        deepLink.addDestination(Integer.valueOf(R.id.homePageFragment));
        deepLink.setType(reminder.getType());
        return deepLink;
    }

    /* renamed from: com.jch.racWiFi.fcm.util.DeepLinkFactory$1 */
    static /* synthetic */ class C18351 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$ErrorSubCategory;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$MaintenanceSubCategory;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$ReminderSubCategory;

        /* JADX WARNING: Can't wrap try/catch for region: R(46:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|(2:15|16)|17|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|54) */
        /* JADX WARNING: Can't wrap try/catch for region: R(48:0|(2:1|2)|3|5|6|7|(2:9|10)|11|13|14|15|16|17|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|54) */
        /* JADX WARNING: Can't wrap try/catch for region: R(49:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|54) */
        /* JADX WARNING: Can't wrap try/catch for region: R(50:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|54) */
        /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x006f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x009b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00a7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00b3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00bf */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00cb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00d7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00e3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00ef */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00fb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x0107 */
        static {
            /*
                com.jch.racWiFi.fcm.util.MaintenanceSubCategory[] r0 = com.jch.racWiFi.fcm.util.MaintenanceSubCategory.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$MaintenanceSubCategory = r0
                r1 = 1
                com.jch.racWiFi.fcm.util.MaintenanceSubCategory r2 = com.jch.racWiFi.fcm.util.MaintenanceSubCategory.PLANNED_MAINTENANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.jch.racWiFi.fcm.util.ReminderSubCategory[] r0 = com.jch.racWiFi.fcm.util.ReminderSubCategory.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$ReminderSubCategory = r0
                com.jch.racWiFi.fcm.util.ReminderSubCategory r2 = com.jch.racWiFi.fcm.util.ReminderSubCategory.CLEANING     // Catch:{ NoSuchFieldError -> 0x0023 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0023 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0023 }
            L_0x0023:
                r0 = 2
                int[] r2 = $SwitchMap$com$jch$racWiFi$fcm$util$ReminderSubCategory     // Catch:{ NoSuchFieldError -> 0x002e }
                com.jch.racWiFi.fcm.util.ReminderSubCategory r3 = com.jch.racWiFi.fcm.util.ReminderSubCategory.USER_ACCEPTED     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                com.jch.racWiFi.fcm.util.ErrorSubCategory[] r2 = com.jch.racWiFi.fcm.util.ErrorSubCategory.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$jch$racWiFi$fcm$util$ErrorSubCategory = r2
                com.jch.racWiFi.fcm.util.ErrorSubCategory r3 = com.jch.racWiFi.fcm.util.ErrorSubCategory.INDOOR     // Catch:{ NoSuchFieldError -> 0x003f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                int[] r2 = $SwitchMap$com$jch$racWiFi$fcm$util$ErrorSubCategory     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.fcm.util.ErrorSubCategory r3 = com.jch.racWiFi.fcm.util.ErrorSubCategory.OUTDOOR     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.jch.racWiFi.fcm.util.AlertSubCategory[] r2 = com.jch.racWiFi.fcm.util.AlertSubCategory.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory = r2
                com.jch.racWiFi.fcm.util.AlertSubCategory r3 = com.jch.racWiFi.fcm.util.AlertSubCategory.ENERGY_CONSUMPTION_BUDGET     // Catch:{ NoSuchFieldError -> 0x005a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r2 = com.jch.racWiFi.fcm.util.AlertSubCategory.RAC_OFFLINE     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x006f }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.RAC_OFFLINE_20_HOURS     // Catch:{ NoSuchFieldError -> 0x006f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006f }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006f }
            L_0x006f:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x007a }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.USER_DETACHES_HIMSELF     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.USER_PERMISSIONS_UPDATED_FOR_SPECIFIC_AC     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.USER_PERMISSIONS_UPDATED_FOR_ALL_ACS     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x009b }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.REMOVED_FROM_HOME     // Catch:{ NoSuchFieldError -> 0x009b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009b }
            L_0x009b:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00a7 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.ROLE_UPDATED     // Catch:{ NoSuchFieldError -> 0x00a7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a7 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a7 }
            L_0x00a7:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00b3 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_ON_ALL     // Catch:{ NoSuchFieldError -> 0x00b3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b3 }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b3 }
            L_0x00b3:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00bf }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_ALL     // Catch:{ NoSuchFieldError -> 0x00bf }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bf }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00bf }
            L_0x00bf:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00cb }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_MULTIPLE_AS_PER_SCHEDULE     // Catch:{ NoSuchFieldError -> 0x00cb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cb }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cb }
            L_0x00cb:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00d7 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC_AS_PER_SCHEDULE     // Catch:{ NoSuchFieldError -> 0x00d7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d7 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d7 }
            L_0x00d7:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00e3 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_DUE_TO_INTERRUPTION     // Catch:{ NoSuchFieldError -> 0x00e3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e3 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e3 }
            L_0x00e3:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00ef }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC     // Catch:{ NoSuchFieldError -> 0x00ef }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ef }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ef }
            L_0x00ef:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00fb }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_ON_SPECIFIC     // Catch:{ NoSuchFieldError -> 0x00fb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fb }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fb }
            L_0x00fb:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0107 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_UPDATED     // Catch:{ NoSuchFieldError -> 0x0107 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0107 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0107 }
            L_0x0107:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0113 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_DUE_TO_REMOTE_ACTIVITY     // Catch:{ NoSuchFieldError -> 0x0113 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0113 }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0113 }
            L_0x0113:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.util.DeepLinkFactory.C18351.<clinit>():void");
        }
    }

    public DeepLink getDeepLink(Maintenance maintenance) {
        DeepLink deepLink = new DeepLink();
        if (C18351.$SwitchMap$com$jch$racWiFi$fcm$util$MaintenanceSubCategory[maintenance.getSubCategory().ordinal()] == 1) {
            deepLink.setRacId(0L);
            deepLink.addDestination(Integer.valueOf(R.id.splashFragment));
            deepLink.setType(maintenance.getType());
        }
        return deepLink;
    }
}
