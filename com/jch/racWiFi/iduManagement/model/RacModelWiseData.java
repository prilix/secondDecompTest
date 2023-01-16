package com.jch.racWiFi.iduManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import java.util.ArrayList;
import java.util.Iterator;

public class RacModelWiseData {
    @SerializedName("cloudId")
    private String cloudId;
    @SerializedName("enabledOptions")
    private EnableOptions enableOptions;

    /* renamed from: id */
    private long f458id;
    @SerializedName("racOperationModes")
    private RacModeDetailList racModelDetails;
    private String racType;
    private long racTypeId;
    private Swing swing;

    public enum TemperatureSettingType {
        ABSOLUTE,
        RELATIVE
    }

    public String getCloudId() {
        return this.cloudId;
    }

    public EnableOptions getEnableOptions() {
        return this.enableOptions;
    }

    public long getID() {
        return this.f458id;
    }

    public void setID(long j) {
        this.f458id = j;
    }

    public String getRacType() {
        return this.racType;
    }

    public void setRacType(String str) {
        this.racType = str;
    }

    public long getRacTypeId() {
        return this.racTypeId;
    }

    public void setRacTypeId(long j) {
        this.racTypeId = j;
    }

    public Swing getSwing() {
        return this.swing;
    }

    public void setSwing(Swing swing2) {
        this.swing = swing2;
    }

    public RacModeDetailList getRacModeDetails() {
        return this.racModelDetails;
    }

    public void setRacModeDetails(RacModeDetailList racModeDetailList) {
        this.racModelDetails = racModeDetailList;
    }

    public static class RacModeDetailList extends ArrayList<RacModeDetail> {
        public RacModeDetail getRacModeDetailBasedOnMode(OperationMode operationMode) {
            Iterator it = iterator();
            while (it.hasNext()) {
                RacModeDetail racModeDetail = (RacModeDetail) it.next();
                if (racModeDetail.getMode() != null && racModeDetail.getMode().equals(operationMode)) {
                    return racModeDetail;
                }
            }
            return null;
        }
    }

    public static class EnableOptions {
        @SerializedName("option0")
        private EnableOption0 enableOption0;
        @SerializedName("option1")
        private EnableOption1 enableOption1 = new EnableOption1();
        @SerializedName("option2")
        private EnableOption2 enableOption2 = new EnableOption2();

        public EnableOption1 getEnableOption1() {
            return this.enableOption1;
        }

        public EnableOption2 getEnableOption2() {
            return this.enableOption2;
        }

        public EnableOption0 getEnableOption0() {
            return this.enableOption0;
        }
    }

    public static class EnableOption0 {
        private boolean childLock;
        private boolean cleanHistory;
        private boolean outOfHomeReminder;
        private boolean powerConsumption;
        private boolean temperatureSettings;
        private boolean weeklyTimer;

        public boolean isWeeklyTimer() {
            return this.weeklyTimer;
        }

        public void setWeeklyTimer(boolean z) {
            this.weeklyTimer = z;
        }

        public boolean isPowerConsumption() {
            return this.powerConsumption;
        }

        public void setPowerConsumption(boolean z) {
            this.powerConsumption = z;
        }

        public boolean isChildLock() {
            return this.childLock;
        }

        public void setChildLock(boolean z) {
            this.childLock = z;
        }

        public boolean isTemperatureSettings() {
            return this.temperatureSettings;
        }

        public void setTemperatureSettings(boolean z) {
            this.temperatureSettings = z;
        }

        public boolean isCleanHistory() {
            return this.cleanHistory;
        }

        public void setCleanHistory(boolean z) {
            this.cleanHistory = z;
        }

        public boolean isOutOfHomeReminder() {
            return this.outOfHomeReminder;
        }

        public void setOutOfHomeReminder(boolean z) {
            this.outOfHomeReminder = z;
        }
    }

    public static class EnableOption1 {
        private boolean airSleep;
        private boolean airSleepTimer;
        private boolean holidayModeLeaveHome;
        private boolean refresh;
        private boolean welcomeBreeze;

        public boolean getHolidayModeLeaveHome() {
            return this.holidayModeLeaveHome;
        }

        public void setHolidayModeLeaveHome(boolean z) {
            this.holidayModeLeaveHome = z;
        }

        public boolean getAirSleep() {
            return this.airSleep;
        }

        public void setAirSleep(boolean z) {
            this.airSleep = z;
        }

        public boolean getAirSleepTimer() {
            return this.airSleepTimer;
        }

        public void setAirSleepTimer(boolean z) {
            this.airSleepTimer = z;
        }

        public boolean getWelcomeBreeze() {
            return this.welcomeBreeze;
        }

        public void setWelcomeBreeze(boolean z) {
            this.welcomeBreeze = z;
        }

        public boolean getRefresh() {
            return this.refresh;
        }

        public void setRefresh(boolean z) {
            this.refresh = z;
        }
    }

    public static class EnableOption2 {
        private boolean cleanFilter;
        private boolean eco;
        private boolean iduFrostWash;
        private boolean oduFrostWash;

        public boolean getEco() {
            return this.eco;
        }

        public void setEco(boolean z) {
            this.eco = z;
        }

        public boolean getIduFrostWash() {
            return this.iduFrostWash;
        }

        public void setIduFrostWash(boolean z) {
            this.iduFrostWash = z;
        }

        public boolean getOduFrostWash() {
            return this.oduFrostWash;
        }

        public void setOduFrostWash(boolean z) {
            this.oduFrostWash = z;
        }

        public boolean getCleanFilter() {
            return this.cleanFilter;
        }

        public void setCleanFilter(boolean z) {
            this.cleanFilter = z;
        }
    }

    public static class RacModeDetail {
        @SerializedName("defaultFanSpeed")
        private FanSpeedDefaults defaultFanSpeedDefaults;
        private long defaultHumidity;
        private float defaultTemperature;
        @SerializedName("enableFanSpeed")
        private FanSpeedDefaults enableFanSpeedDefaults;
        @SerializedName("enableSettings")
        private BleSettings enableSettings;
        private long featureID;
        private long humiditySettingPitchType;

        /* renamed from: id */
        private long f460id;
        private long maxHumidity;
        private long maxTemperature;
        private long minHumidity;
        private long minTemperature;
        private OperationMode mode;
        private String receivingValue;
        private float referenceTemperature = -1.0f;
        private String sendingValue;
        private String temperatureSetting;
        private float temperatureSettingPitchType;
        @SerializedName("visibleSettings")
        private BleSettings visibleSettings;
        @SerializedName("visibleTemperatureSetting")
        private String visibleTemperatureSetting;

        public boolean isPitchFloating() {
            return ((float) Math.round(this.temperatureSettingPitchType)) != this.temperatureSettingPitchType;
        }

        public String getVisibleTemperatureSetting() {
            return this.visibleTemperatureSetting;
        }

        public void setVisibleTemperatureSetting(String str) {
            this.visibleTemperatureSetting = str;
        }

        public TemperatureSettingType getVisibleTemperatureSettingType() {
            String str = this.visibleTemperatureSetting;
            if (str != null) {
                return TemperatureSettingType.valueOf(str);
            }
            return null;
        }

        public float getReferenceTemperature() {
            return this.referenceTemperature;
        }

        public void setReferenceTemperature(float f) {
            this.referenceTemperature = f;
        }

        public void copy(RacModeDetail racModeDetail) {
            this.f460id = racModeDetail.f460id;
            this.featureID = racModeDetail.featureID;
            this.mode = racModeDetail.mode;
            this.sendingValue = racModeDetail.sendingValue;
            this.receivingValue = racModeDetail.receivingValue;
            this.temperatureSetting = racModeDetail.temperatureSetting;
            this.defaultTemperature = racModeDetail.defaultTemperature;
            this.temperatureSettingPitchType = racModeDetail.temperatureSettingPitchType;
            this.defaultHumidity = racModeDetail.defaultHumidity;
            this.enableSettings = racModeDetail.enableSettings;
            this.visibleSettings = racModeDetail.visibleSettings;
            this.enableFanSpeedDefaults = racModeDetail.enableFanSpeedDefaults;
            this.defaultFanSpeedDefaults = racModeDetail.defaultFanSpeedDefaults;
            this.maxHumidity = racModeDetail.maxHumidity;
            this.minHumidity = racModeDetail.minHumidity;
            this.humiditySettingPitchType = racModeDetail.humiditySettingPitchType;
            this.maxTemperature = racModeDetail.maxTemperature;
            this.minTemperature = racModeDetail.minTemperature;
        }

        public TemperatureSettingType getTemperatureSettingType() {
            String str = this.temperatureSetting;
            if (str != null) {
                return TemperatureSettingType.valueOf(str);
            }
            return null;
        }

        public long getID() {
            return this.f460id;
        }

        public void setID(long j) {
            this.f460id = j;
        }

        public long getFeatureID() {
            return this.featureID;
        }

        public void setFeatureID(long j) {
            this.featureID = j;
        }

        public OperationMode getMode() {
            return this.mode;
        }

        public void setMode(OperationMode operationMode) {
            this.mode = operationMode;
        }

        public String getSendingValue() {
            return this.sendingValue;
        }

        public void setSendingValue(String str) {
            this.sendingValue = str;
        }

        public String getReceivingValue() {
            return this.receivingValue;
        }

        public void setReceivingValue(String str) {
            this.receivingValue = str;
        }

        public String getTemperatureSetting() {
            return this.temperatureSetting;
        }

        public void setTemperatureSetting(String str) {
            this.temperatureSetting = str;
        }

        public float getDefaultTemperature() {
            return this.defaultTemperature;
        }

        public void setDefaultTemperature(float f) {
            this.defaultTemperature = f;
        }

        public float getTemperatureSettingPitchType() {
            return this.temperatureSettingPitchType;
        }

        public void setTemperatureSettingPitchType(float f) {
            this.temperatureSettingPitchType = f;
        }

        public long getDefaultHumidity() {
            return this.defaultHumidity;
        }

        public void setDefaultHumidity(long j) {
            this.defaultHumidity = j;
        }

        public BleSettings getEnableSettings() {
            return this.enableSettings;
        }

        public void setEnableSettings(BleSettings bleSettings) {
            this.enableSettings = bleSettings;
        }

        public BleSettings getVisibleSettings() {
            return this.visibleSettings;
        }

        public void setVisibleSettings(BleSettings bleSettings) {
            this.visibleSettings = bleSettings;
        }

        public FanSpeedDefaults getEnableFanSpeedDefaults() {
            return this.enableFanSpeedDefaults;
        }

        public void setEnableFanSpeedDefaults(FanSpeedDefaults fanSpeedDefaults) {
            this.enableFanSpeedDefaults = fanSpeedDefaults;
        }

        public FanSpeedDefaults getDefaultFanSpeedDefaults() {
            return this.defaultFanSpeedDefaults;
        }

        public void setDefaultFanSpeedDefaults(FanSpeedDefaults fanSpeedDefaults) {
            this.defaultFanSpeedDefaults = fanSpeedDefaults;
        }

        public long getMaxHumidity() {
            return this.maxHumidity;
        }

        public void setMaxHumidity(long j) {
            this.maxHumidity = j;
        }

        public long getMinHumidity() {
            return this.minHumidity;
        }

        public void setMinHumidity(long j) {
            this.minHumidity = j;
        }

        public long getHumiditySettingPitchType() {
            return this.humiditySettingPitchType;
        }

        public void setHumiditySettingPitchType(long j) {
            this.humiditySettingPitchType = j;
        }

        public long getMaxTemperature() {
            return this.maxTemperature;
        }

        public void setMaxTemperature(long j) {
            this.maxTemperature = j;
        }

        public long getMinTemperature() {
            return this.minTemperature;
        }

        public void setMinTemperature(long j) {
            this.minTemperature = j;
        }

        public boolean isHumidityValueInRange(float f) {
            return f >= ((float) this.minHumidity) && f <= ((float) this.maxHumidity);
        }

        public boolean isTemperatureValueInRange(float f) {
            return f >= ((float) this.minTemperature) && f <= ((float) this.maxTemperature);
        }

        public boolean isFanSpeedValueInRange(DetailedIduModel.FanSpeed fanSpeed) {
            switch (C18621.f459x94a2e204[fanSpeed.ordinal()]) {
                case 1:
                    return getEnableFanSpeedDefaults().lv1;
                case 2:
                    return getEnableFanSpeedDefaults().lv2;
                case 3:
                    return getEnableFanSpeedDefaults().lv3;
                case 4:
                    return getEnableFanSpeedDefaults().lv4;
                case 5:
                    return getEnableFanSpeedDefaults().lv5;
                case 6:
                    return getEnableFanSpeedDefaults().auto;
                default:
                    return false;
            }
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.model.RacModelWiseData$1 */
    static /* synthetic */ class C18621 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$iduManagement$model$DetailedIduModel$FanSpeed */
        static final /* synthetic */ int[] f459x94a2e204;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$FanSpeed[] r0 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.FanSpeed.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f459x94a2e204 = r0
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$FanSpeed r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.FanSpeed.LV1     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f459x94a2e204     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$FanSpeed r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.FanSpeed.LV2     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f459x94a2e204     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$FanSpeed r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.FanSpeed.LV3     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f459x94a2e204     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$FanSpeed r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.FanSpeed.LV4     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f459x94a2e204     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$FanSpeed r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.FanSpeed.LV5     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f459x94a2e204     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$FanSpeed r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.FanSpeed.AUTO     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.model.RacModelWiseData.C18621.<clinit>():void");
        }
    }

    public static class FanSpeedDefaults {
        /* access modifiers changed from: private */
        @SerializedName("AUTO")
        public boolean auto;
        /* access modifiers changed from: private */
        @SerializedName("LV1")
        public boolean lv1;
        /* access modifiers changed from: private */
        @SerializedName("LV2")
        public boolean lv2;
        /* access modifiers changed from: private */
        @SerializedName("LV3")
        public boolean lv3;
        /* access modifiers changed from: private */
        @SerializedName("LV4")
        public boolean lv4;
        /* access modifiers changed from: private */
        @SerializedName("LV5")
        public boolean lv5;

        public boolean[] getBooleanArray() {
            return new boolean[]{this.lv1, this.lv2, this.lv3, this.lv4, this.lv5, this.auto};
        }

        public boolean getLv3() {
            return this.lv3;
        }

        public void setLv3(boolean z) {
            this.lv3 = z;
        }

        public boolean getAuto() {
            return this.auto;
        }

        public void setAuto(boolean z) {
            this.auto = z;
        }

        public boolean getLv5() {
            return this.lv5;
        }

        public void setLv5(boolean z) {
            this.lv5 = z;
        }

        public boolean getLv2() {
            return this.lv2;
        }

        public void setLv2(boolean z) {
            this.lv2 = z;
        }

        public boolean getLv4() {
            return this.lv4;
        }

        public void setLv4(boolean z) {
            this.lv4 = z;
        }

        public boolean getLv1() {
            return this.lv1;
        }

        public void setLv1(boolean z) {
            this.lv1 = z;
        }

        public DetailedIduModel.FanSpeed getDefaultFanSpeed() {
            return DetailedIduModel.FanSpeed.getDefaultFanSpeedEnum(this);
        }
    }

    public static class BleSettings {
        private boolean eco;
        private boolean fan;
        private boolean humidity;
        private boolean swing;
        private boolean temperature;
        private boolean timer;

        public boolean getTemperature() {
            return this.temperature;
        }

        public void setTemperature(boolean z) {
            this.temperature = z;
        }

        public boolean getHumidity() {
            return this.humidity;
        }

        public void setHumidity(boolean z) {
            this.humidity = z;
        }

        public boolean getFan() {
            return this.fan;
        }

        public void setFan(boolean z) {
            this.fan = z;
        }

        public boolean getSwing() {
            return this.swing;
        }

        public void setSwing(boolean z) {
            this.swing = z;
        }

        public boolean getTimer() {
            return this.timer;
        }

        public void setTimer(boolean z) {
            this.timer = z;
        }

        public boolean getEco() {
            return this.eco;
        }

        public void setEco(boolean z) {
            this.eco = z;
        }
    }

    public static class Swing {
        @SerializedName("HORIZONTAL")
        private boolean horizontal;
        @SerializedName("VERTICAL")
        private boolean vertical;

        public boolean getVertical() {
            return this.vertical;
        }

        public void setVertical(boolean z) {
            this.vertical = z;
        }

        public boolean getHorizontal() {
            return this.horizontal;
        }

        public void setHorizontal(boolean z) {
            this.horizontal = z;
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof RacModelWiseData)) {
            return this.cloudId.equals(((RacModelWiseData) obj).cloudId);
        }
        return false;
    }
}
