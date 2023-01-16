package com.jch.racWiFi.iduManagement.model;

import com.jch_hitachi.aircloudglobal.R;

public class OperationModeUIConfigration {
    public static final OperationModeUIConfigration AUTO;
    public static final OperationModeUIConfigration COOLING;
    public static final OperationModeUIConfigration DE_HUMIDIFY;
    public static final OperationModeUIConfigration DRY_COOL;
    public static final OperationModeUIConfigration FAN;
    public static final OperationModeUIConfigration HEATING;
    private int mainBackgroundDrawableResource;
    private int modeBackgroundColorResource;
    private int modeListDrawableResource;
    private OperationMode operationMode;
    private int operationModeStringResource;
    private int selectedModeDrawableResource;
    private int statusBarColor;
    private boolean visibile;

    public int getModeBackgroundColorResource() {
        return this.modeBackgroundColorResource;
    }

    public void setModeBackgroundColorResource(int i) {
        this.modeBackgroundColorResource = i;
    }

    public int getMainBackgroundDrawableResource() {
        return this.mainBackgroundDrawableResource;
    }

    public void setMainBackgroundDrawableResource(int i) {
        this.mainBackgroundDrawableResource = i;
    }

    public int getOperationModeStringResource() {
        return this.operationModeStringResource;
    }

    public void setOperationModeStringResource(int i) {
        this.operationModeStringResource = i;
    }

    public int getSelectedModeDrawableResource() {
        return this.selectedModeDrawableResource;
    }

    public void setSelectedModeDrawableResource(int i) {
        this.selectedModeDrawableResource = i;
    }

    public int getModeListDrawableResource() {
        return this.modeListDrawableResource;
    }

    public void setModeListDrawableResource(int i) {
        this.modeListDrawableResource = i;
    }

    public int getStatusBarColor() {
        return this.statusBarColor;
    }

    public void setStatusBarColor(int i) {
        this.statusBarColor = i;
    }

    public boolean isVisibile() {
        return this.visibile;
    }

    public void setVisibile(boolean z) {
        this.visibile = z;
    }

    public OperationMode getOperationMode() {
        return this.operationMode;
    }

    public void setOperationMode(OperationMode operationMode2) {
        this.operationMode = operationMode2;
    }

    static {
        OperationModeUIConfigration operationModeUIConfigration = new OperationModeUIConfigration();
        COOLING = operationModeUIConfigration;
        operationModeUIConfigration.setOperationMode(OperationMode.COOLING);
        operationModeUIConfigration.setMainBackgroundDrawableResource(R.drawable.bg_cooling_banner);
        operationModeUIConfigration.setOperationModeStringResource(R.string.common_lbl_cooling);
        operationModeUIConfigration.setVisibile(false);
        operationModeUIConfigration.setSelectedModeDrawableResource(R.drawable.ic_cooling_svg);
        operationModeUIConfigration.setModeBackgroundColorResource(R.color.color_cooling_global);
        operationModeUIConfigration.setModeListDrawableResource(R.drawable.ic_cooling_mode_svg);
        operationModeUIConfigration.setStatusBarColor(R.color.color_cooling_global);
        OperationModeUIConfigration operationModeUIConfigration2 = new OperationModeUIConfigration();
        HEATING = operationModeUIConfigration2;
        operationModeUIConfigration2.setOperationMode(OperationMode.HEATING);
        operationModeUIConfigration2.setMainBackgroundDrawableResource(R.drawable.bg_heating_banner);
        operationModeUIConfigration2.setOperationModeStringResource(R.string.common_lbl_heating);
        operationModeUIConfigration2.setVisibile(false);
        operationModeUIConfigration2.setSelectedModeDrawableResource(R.drawable.ic_white_heat_mode_svg);
        operationModeUIConfigration2.setModeBackgroundColorResource(R.color.color_heating_global);
        operationModeUIConfigration2.setModeListDrawableResource(R.drawable.ic_heating_mode_svg);
        operationModeUIConfigration2.setStatusBarColor(R.color.color_heating_global);
        OperationModeUIConfigration operationModeUIConfigration3 = new OperationModeUIConfigration();
        DE_HUMIDIFY = operationModeUIConfigration3;
        operationModeUIConfigration3.setOperationMode(OperationMode.DRY);
        operationModeUIConfigration3.setMainBackgroundDrawableResource(R.drawable.bg_dehumidify_banner);
        operationModeUIConfigration3.setOperationModeStringResource(R.string.common_lbl_dry);
        operationModeUIConfigration3.setVisibile(true);
        operationModeUIConfigration3.setSelectedModeDrawableResource(R.drawable.ic_white_dehumidify_mode_svg);
        operationModeUIConfigration3.setModeBackgroundColorResource(R.color.color_dehumidify_global);
        operationModeUIConfigration3.setModeListDrawableResource(R.drawable.ic_color_dehumidify_mode_new);
        operationModeUIConfigration3.setStatusBarColor(R.color.color_dehumidity_top);
        OperationModeUIConfigration operationModeUIConfigration4 = new OperationModeUIConfigration();
        DRY_COOL = operationModeUIConfigration4;
        operationModeUIConfigration4.setOperationMode(OperationMode.DRY_COOL);
        operationModeUIConfigration4.setMainBackgroundDrawableResource(R.drawable.bg_dry_cool_banner);
        operationModeUIConfigration4.setOperationModeStringResource(R.string.common_lbl_dryCool);
        operationModeUIConfigration4.setVisibile(true);
        operationModeUIConfigration4.setSelectedModeDrawableResource(R.drawable.ic_white_dry_cool_svg_new);
        operationModeUIConfigration4.setModeBackgroundColorResource(R.color.color_dry_cool_global);
        operationModeUIConfigration4.setModeListDrawableResource(R.drawable.ic_color_dry_cool_mode_new);
        operationModeUIConfigration4.setStatusBarColor(R.color.color_dry_cool_global);
        OperationModeUIConfigration operationModeUIConfigration5 = new OperationModeUIConfigration();
        FAN = operationModeUIConfigration5;
        operationModeUIConfigration5.setOperationMode(OperationMode.FAN);
        operationModeUIConfigration5.setMainBackgroundDrawableResource(R.drawable.bg_fan_banner);
        operationModeUIConfigration5.setOperationModeStringResource(R.string.common_lbl_fan);
        operationModeUIConfigration5.setVisibile(false);
        operationModeUIConfigration5.setSelectedModeDrawableResource(R.drawable.ic_white_fan_mode_svg);
        operationModeUIConfigration5.setModeBackgroundColorResource(R.color.color_fan_global);
        operationModeUIConfigration5.setModeListDrawableResource(R.drawable.ic_color_fan_mode_svg);
        operationModeUIConfigration5.setStatusBarColor(R.color.color_fan_global);
        OperationModeUIConfigration operationModeUIConfigration6 = new OperationModeUIConfigration();
        AUTO = operationModeUIConfigration6;
        operationModeUIConfigration6.setOperationMode(OperationMode.AUTO);
        operationModeUIConfigration6.setMainBackgroundDrawableResource(R.drawable.bg_auto_banner);
        operationModeUIConfigration6.setOperationModeStringResource(R.string.common_lbl_auto);
        operationModeUIConfigration6.setVisibile(false);
        operationModeUIConfigration6.setSelectedModeDrawableResource(R.drawable.ic_grey_auto_mode_svg);
        operationModeUIConfigration6.setModeBackgroundColorResource(R.color.color_auto_global);
        operationModeUIConfigration6.setModeListDrawableResource(R.drawable.ic_color_auto_mode_svg);
        operationModeUIConfigration6.setStatusBarColor(R.color.color_auto_global);
    }

    /* renamed from: com.jch.racWiFi.iduManagement.model.OperationModeUIConfigration$1 */
    static /* synthetic */ class C18611 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.model.OperationModeUIConfigration.C18611.<clinit>():void");
        }
    }

    public static OperationModeUIConfigration getOperationModeUIConfigrationBasedOnMode(OperationMode operationMode2) {
        switch (C18611.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[operationMode2.ordinal()]) {
            case 1:
                return COOLING;
            case 2:
                return HEATING;
            case 3:
                return DE_HUMIDIFY;
            case 4:
                return DRY_COOL;
            case 5:
                return FAN;
            case 6:
                return AUTO;
            default:
                return null;
        }
    }

    public static OperationModeUIConfigration getValue(OperationMode operationMode2) {
        if (C18611.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[operationMode2.ordinal()] != 1) {
            return null;
        }
        return COOLING;
    }
}
