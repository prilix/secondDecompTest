package com.jch.racWiFi.iduManagement.model;

import com.jch_hitachi.aircloudglobal.R;

public enum OperationMode {
    COOLING(27.0f, 0, 0, 1),
    HEATING(23.0f, 0, 0, 2),
    DRY(24.0f, 50, 0, 3),
    DRY_COOL(27.0f, 50, 0, 7),
    FAN(0.0f, 0, 3, 10),
    AUTO(0.0f, 0, 0, 11),
    UNKNOWN(0.0f, 0, 0, 12);
    
    private int defaultFanSpeed;
    private int defaultHumidity;
    private float defaultTemp;
    private int displayOrder;

    public int getDisplayOrder() {
        return this.displayOrder;
    }

    public float getDefaultTemp() {
        return this.defaultTemp;
    }

    public int getDefaultHumidity() {
        return this.defaultHumidity;
    }

    public int getDefaultFanSpeed() {
        return this.defaultFanSpeed;
    }

    private OperationMode(float f, int i, int i2, int i3) {
        this.defaultTemp = f;
        this.defaultHumidity = i;
        this.defaultFanSpeed = i2;
        this.displayOrder = i3;
    }

    public void setCurrentTemp(float f) {
        this.defaultTemp = this.defaultTemp;
    }

    /* renamed from: com.jch.racWiFi.iduManagement.model.OperationMode$1 */
    static /* synthetic */ class C18601 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode = null;

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
                com.jch.racWiFi.iduManagement.model.OperationMode[] r0 = com.jch.racWiFi.iduManagement.model.OperationMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode = r0
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.COOLING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.HEATING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY_COOL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.FAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.AUTO     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.model.OperationMode.C18601.<clinit>():void");
        }
    }

    public int value() {
        int i = C18601.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[ordinal()];
        if (i == 1) {
            return 64;
        }
        if (i == 2) {
            return 16;
        }
        if (i == 3) {
            return 38;
        }
        if (i != 5) {
            return i != 6 ? 0 : 32768;
        }
        return 96;
    }

    public int getStringRes() {
        switch (C18601.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[ordinal()]) {
            case 1:
                return R.string.common_lbl_cooling;
            case 2:
                return R.string.common_lbl_heating;
            case 3:
                return R.string.common_lbl_dry;
            case 4:
                return R.string.common_lbl_dryCool;
            case 5:
                return R.string.common_lbl_fan;
            case 6:
                return R.string.common_lbl_auto;
            case 7:
                return R.string.dots;
            default:
                return -1;
        }
    }

    public int getDrawableRes() {
        switch (C18601.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[ordinal()]) {
            case 1:
                return R.drawable.ic_color_cooling_mode_svg;
            case 2:
                return R.drawable.ic_color_heating_mode_svg;
            case 3:
                return R.drawable.ic_color_dehumidify_mode_new;
            case 4:
                return R.drawable.ic_color_dry_cool_mode_new;
            case 5:
                return R.drawable.ic_color_fan_mode_svg;
            case 6:
            case 7:
                return R.drawable.ic_color_auto_mode_svg;
            default:
                return -1;
        }
    }

    public int getDrawableResSmall() {
        switch (C18601.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[ordinal()]) {
            case 1:
                return R.drawable.ic_cooling_svg;
            case 2:
                return R.drawable.ic_white_heat_mode_svg;
            case 3:
                return R.drawable.ic_white_dehumidify_mode_svg;
            case 4:
                return R.drawable.ic_white_dry_cool_svg_new;
            case 5:
                return R.drawable.ic_white_fan_mode_svg;
            case 6:
            case 7:
                return R.drawable.ic_white_auto_mode_svg;
            default:
                return -1;
        }
    }

    public int getOperationModeDrawableForHomePage() {
        switch (C18601.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[ordinal()]) {
            case 1:
                return R.drawable.ic_color_cooling_mode_svg;
            case 2:
                return R.drawable.ic_color_heating_mode_svg;
            case 3:
                return R.drawable.ic_color_dehumidify_mode_svg;
            case 4:
                return R.drawable.ic_color_dry_cool_mode_svg;
            case 5:
                return R.drawable.ic_color_fan_mode_svg;
            case 6:
                return R.drawable.ic_color_auto_mode_svg;
            case 7:
                return R.drawable.circle_off;
            default:
                return -1;
        }
    }

    public int getDrawableResTimer() {
        switch (C18601.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[ordinal()]) {
            case 1:
                return R.drawable.ic_color_cooling_mode_svg;
            case 2:
                return R.drawable.ic_color_heating_mode_svg;
            case 3:
                return R.drawable.ic_color_timer_dehumidify_mode_svg;
            case 4:
                return R.drawable.ic_color_dry_cool_mode_svg;
            case 5:
                return R.drawable.ic_color_timer_fan_mode_svg;
            case 6:
                return R.drawable.ic_color_timer_auto_mode_svg;
            case 7:
                return R.drawable.circle_off;
            default:
                return -1;
        }
    }

    public int getModeColor() {
        switch (C18601.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[ordinal()]) {
            case 1:
                return R.color.color_cooling_global;
            case 2:
                return R.color.color_fan_global;
            case 3:
                return R.color.color_dehumidify_global;
            case 4:
                return R.color.color_dry_cool_global;
            case 5:
                return R.color.color_heating_global;
            case 6:
                return R.color.textview_color_vd_light;
            case 7:
                return R.color.black;
            default:
                return -1;
        }
    }

    public int getDrawableResSmartFence() {
        switch (C18601.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[ordinal()]) {
            case 1:
                return R.drawable.ic_cooling_svg_smart_fence;
            case 2:
                return R.drawable.ic_color_heating_mode_svg;
            case 3:
                return R.drawable.ic_color_timer_dehumidify_mode_svg;
            case 4:
                return R.drawable.ic_color_dry_cool_mode_svg;
            case 5:
                return R.drawable.ic_color_timer_fan_mode_svg;
            case 6:
                return R.drawable.ic_grey_auto_mode_svg;
            case 7:
                return R.drawable.circle_off;
            default:
                return -1;
        }
    }
}
