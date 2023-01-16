package com.jch.racWiFi.iduManagement.model;

public enum IDUNotificationType {
    OUT_OF_HOME_REMINDER,
    CLEAN_FILTER,
    MOLD_FORMATION,
    ERROR_DETAILS,
    TIMER_CONFLICT,
    WEEKLY_TIMER_CONFLICT,
    SPECIAL_OPERATION,
    HOLIDAY_MODE_SETTINGS_UPDATE,
    IDU_FROST_WASH,
    ODU_FROST_WASH,
    IDU_TURN_ON,
    CMD_STATUS,
    IDU_OFFLINE;

    /* renamed from: com.jch.racWiFi.iduManagement.model.IDUNotificationType$1 */
    static /* synthetic */ class C18591 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$iduManagement$model$IDUNotificationType */
        static final /* synthetic */ int[] f456x60b5452a = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.iduManagement.model.IDUNotificationType[] r0 = com.jch.racWiFi.iduManagement.model.IDUNotificationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f456x60b5452a = r0
                com.jch.racWiFi.iduManagement.model.IDUNotificationType r1 = com.jch.racWiFi.iduManagement.model.IDUNotificationType.ERROR_DETAILS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f456x60b5452a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.IDUNotificationType r1 = com.jch.racWiFi.iduManagement.model.IDUNotificationType.TIMER_CONFLICT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f456x60b5452a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.IDUNotificationType r1 = com.jch.racWiFi.iduManagement.model.IDUNotificationType.WEEKLY_TIMER_CONFLICT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f456x60b5452a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.IDUNotificationType r1 = com.jch.racWiFi.iduManagement.model.IDUNotificationType.SPECIAL_OPERATION     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f456x60b5452a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.IDUNotificationType r1 = com.jch.racWiFi.iduManagement.model.IDUNotificationType.IDU_FROST_WASH     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.model.IDUNotificationType.C18591.<clinit>():void");
        }
    }

    public IDUNotificationTypeUIConfigration getUiConfigration() {
        int i = C18591.f456x60b5452a[ordinal()];
        if (i == 1) {
            return IDUNotificationTypeUIConfigration.ERROR;
        }
        if (i == 2) {
            return IDUNotificationTypeUIConfigration.TIMER_UPDATE;
        }
        if (i == 3) {
            return IDUNotificationTypeUIConfigration.WEEKLY_TIMER_UPDATE;
        }
        if (i == 4) {
            return IDUNotificationTypeUIConfigration.SPECIAL_OPERATION;
        }
        if (i != 5) {
            return null;
        }
        return IDUNotificationTypeUIConfigration.IDU_FROST_WASH;
    }
}
