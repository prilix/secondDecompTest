package com.jch.racWiFi.iduManagement.model;

public enum WeeklyTimerMode {
    SCHEDULE_DISABLED,
    OFF_TIMER_ENABLED,
    ON_TIMER_ENABLED,
    ON_OFF_TIMER_ENABLED,
    WEEKLY_TIMER_ENABLED,
    HOLIDAY_MODE_ENABLED;
    
    public static final String TIMER_MODE = "timer_mode";

    /* renamed from: com.jch.racWiFi.iduManagement.model.WeeklyTimerMode$1 */
    static /* synthetic */ class C18641 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode[] r0 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode = r0
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.SCHEDULE_DISABLED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.OFF_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.ON_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.ON_OFF_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.WEEKLY_TIMER_ENABLED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.model.WeeklyTimerMode r1 = com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.HOLIDAY_MODE_ENABLED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.model.WeeklyTimerMode.C18641.<clinit>():void");
        }
    }

    public String getSchedulerTypeCommand(WeeklyTimerMode weeklyTimerMode) {
        switch (C18641.$SwitchMap$com$jch$racWiFi$iduManagement$model$WeeklyTimerMode[weeklyTimerMode.ordinal()]) {
            case 1:
                return "SCHEDULE_DISABLED";
            case 2:
                return "OFF_TIMER_ENABLED";
            case 3:
                return "ON_TIMER_ENABLED";
            case 4:
                return "ON_OFF_TIMER_ENABLED";
            case 5:
                return "WEEKLY_TIMER_ENABLED";
            case 6:
                return "HOLIDAY_MODE_ENABLED";
            default:
                return "";
        }
    }
}
