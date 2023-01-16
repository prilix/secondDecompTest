package com.jch.racWiFi.iduManagement;

import com.jch_hitachi.aircloudglobal.R;

public enum Weekday {
    SUN,
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT;

    public static int getPosition(String str) {
        return valueOf(str.toUpperCase()).ordinal();
    }

    public static String getDay(int i) {
        String weekday = values()[i].toString();
        return weekday.substring(0, 1).toUpperCase() + weekday.substring(1, 3).toLowerCase();
    }

    public static boolean contains(String str) {
        for (Weekday name : values()) {
            if (name.name().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: com.jch.racWiFi.iduManagement.Weekday$1 */
    static /* synthetic */ class C18421 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$Weekday = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.iduManagement.Weekday[] r0 = com.jch.racWiFi.iduManagement.Weekday.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$iduManagement$Weekday = r0
                com.jch.racWiFi.iduManagement.Weekday r1 = com.jch.racWiFi.iduManagement.Weekday.MON     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$Weekday     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.Weekday r1 = com.jch.racWiFi.iduManagement.Weekday.TUE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$Weekday     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.Weekday r1 = com.jch.racWiFi.iduManagement.Weekday.WED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$Weekday     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.Weekday r1 = com.jch.racWiFi.iduManagement.Weekday.THU     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$Weekday     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.Weekday r1 = com.jch.racWiFi.iduManagement.Weekday.FRI     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$Weekday     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.Weekday r1 = com.jch.racWiFi.iduManagement.Weekday.SAT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$Weekday     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.jch.racWiFi.iduManagement.Weekday r1 = com.jch.racWiFi.iduManagement.Weekday.SUN     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.Weekday.C18421.<clinit>():void");
        }
    }

    public int getStringRes() {
        switch (C18421.$SwitchMap$com$jch$racWiFi$iduManagement$Weekday[ordinal()]) {
            case 1:
                return R.string.weeklyTimer_lbl_mon;
            case 2:
                return R.string.weeklyTimer_lbl_tue;
            case 3:
                return R.string.weeklyTimer_lbl_wed;
            case 4:
                return R.string.weeklyTimer_lbl_thu;
            case 5:
                return R.string.weeklyTimer_lbl_fri;
            case 6:
                return R.string.weeklyTimer_lbl_sat;
            case 7:
                return R.string.weeklyTimer_lbl_sun;
            default:
                return -1;
        }
    }
}
