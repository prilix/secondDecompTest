package com.jch.racWiFi.fcm.model;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.fcm.util.SmartFenceSubCategory;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.p010di.util.Constants;

public class SmartFence implements Parcelable {
    public static final Parcelable.Creator<SmartFence> CREATOR = new Parcelable.Creator<SmartFence>() {
        public SmartFence createFromParcel(Parcel parcel) {
            return new SmartFence(parcel);
        }

        public SmartFence[] newArray(int i) {
            return new SmartFence[i];
        }
    };
    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy, hh:mm a";
    public static final String PARCEL_KEY = "SmartFence_PARCEL_KEY";
    private Bundle bundle;
    @SerializedName("familyId")
    private String familyId;
    @SerializedName("familyName")
    private String familyName;
    @SerializedName("id")

    /* renamed from: id */
    private String f446id;
    @SerializedName("mode")
    private String mode;
    @SerializedName("racId")
    private Long racId;
    @SerializedName("racIncluded")
    private String racIncluded;
    @SerializedName("racName")
    private String racName;
    private SmartFenceSubCategory subCategory;
    @SerializedName("temperature")
    private String temperature;
    @SerializedName("temperatureUnit")
    private String temperatureUnit;
    @SerializedName("timestamp")
    private long timestamp;
    private Type type = Type.SMART_FENCE;
    @SerializedName("userId")
    private String userId;
    @SerializedName("userName")
    private String userName;

    public int describeContents() {
        return 0;
    }

    public SmartFence() {
    }

    protected SmartFence(Parcel parcel) {
        Type type2;
        this.f446id = parcel.readString();
        this.userId = parcel.readString();
        this.racName = parcel.readString();
        this.userName = parcel.readString();
        SmartFenceSubCategory smartFenceSubCategory = null;
        if (parcel.readByte() == 0) {
            this.racId = null;
        } else {
            this.racId = Long.valueOf(parcel.readLong());
        }
        this.timestamp = parcel.readLong();
        this.racIncluded = parcel.readString();
        this.familyId = parcel.readString();
        this.familyName = parcel.readString();
        this.mode = parcel.readString();
        this.temperature = parcel.readString();
        this.temperatureUnit = parcel.readString();
        this.bundle = parcel.readBundle();
        int readInt = parcel.readInt();
        if (readInt == -1) {
            type2 = null;
        } else {
            type2 = Type.values()[readInt];
        }
        this.type = type2;
        int readInt2 = parcel.readInt();
        this.subCategory = readInt2 != -1 ? SmartFenceSubCategory.values()[readInt2] : smartFenceSubCategory;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f446id);
        parcel.writeString(this.userId);
        parcel.writeString(this.racName);
        parcel.writeString(this.userName);
        if (this.racId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.racId.longValue());
        }
        parcel.writeLong(this.timestamp);
        parcel.writeString(this.racIncluded);
        parcel.writeString(this.familyId);
        parcel.writeString(this.familyName);
        parcel.writeString(this.mode);
        parcel.writeString(this.temperature);
        parcel.writeString(this.temperatureUnit);
        parcel.writeBundle(this.bundle);
        Type type2 = this.type;
        int i2 = -1;
        parcel.writeInt(type2 == null ? -1 : type2.ordinal());
        SmartFenceSubCategory smartFenceSubCategory = this.subCategory;
        if (smartFenceSubCategory != null) {
            i2 = smartFenceSubCategory.ordinal();
        }
        parcel.writeInt(i2);
    }

    public SmartFenceSubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(SmartFenceSubCategory smartFenceSubCategory) {
        this.subCategory = smartFenceSubCategory;
    }

    public String getTitle(Context context) {
        return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[0]);
    }

    public String getId() {
        return this.f446id;
    }

    public void setId(String str) {
        this.f446id = str;
    }

    public String getRacName() {
        return this.racName;
    }

    public void setRacName(String str) {
        this.racName = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public Long getRacId() {
        return this.racId;
    }

    public void setRacId(Long l) {
        this.racId = l;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    /* renamed from: com.jch.racWiFi.fcm.model.SmartFence$2 */
    static /* synthetic */ class C18322 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory;

        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|(3:27|28|30)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory[] r0 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory = r0
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ENABLE_LOCATION_ACCESS_PERMISSION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.USER_REMOVED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LOCATION_CONTROLS_DISABLED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ARRIVING_OFF     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ARRIVING_ON     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LEAVING_ON     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LEAVING_OFF     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ARRIVING     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x006c }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ARRIVING_ENABLED     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ARRIVING_DISABLED     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LEAVING     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LEAVING_ENABLED     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x009c }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LEAVING_DISABLED     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LOCATION_CONTROLS_SETTINGS_UPDATED     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.model.SmartFence.C18322.<clinit>():void");
        }
    }

    public String getDescription(Context context) {
        switch (C18322.$SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory[getSubCategory().ordinal()]) {
            case 1:
            case 2:
                if (getRacIncluded() == null || !getRacIncluded().trim().equals("true")) {
                    return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[2], new Object[]{getFamilyName()});
                }
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getFamilyName(), getRacName()});
            case 3:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getFamilyName()});
            case 4:
                if (getRacIncluded() == null || !getRacIncluded().trim().equals("true")) {
                    return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[2], new Object[]{getFamilyName(), getUserName(), getTemperature(), getMode()});
                }
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getFamilyName(), getUserName(), getRacName()});
            case 5:
            case 6:
                if (getRacIncluded() == null || !getRacIncluded().trim().equals("true")) {
                    return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[2], new Object[]{getFamilyName(), getUserName(), getTemperature(), getMode()});
                }
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getFamilyName(), getUserName(), getRacName(), getTemperature(), getMode()});
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                if (getRacIncluded() == null || !getRacIncluded().trim().equals("true")) {
                    return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[2], new Object[]{getFamilyName(), getUserName()});
                }
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getFamilyName(), getUserName(), getRacName()});
            default:
                return null;
        }
    }

    public Bundle getBundle() {
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(PARCEL_KEY, this);
        return bundle2;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        this.type = type2;
    }

    public String getRacIncluded() {
        return this.racIncluded;
    }

    public void setRacIncluded(String str) {
        this.racIncluded = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getFamilyId() {
        return this.familyId;
    }

    public void setFamilyId(String str) {
        this.familyId = str;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String str) {
        this.familyName = str;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String str) {
        this.temperature = str;
    }

    public String getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setTemperatureUnit(String str) {
        this.temperatureUnit = str;
    }

    public void setBundle(Bundle bundle2) {
        this.bundle = bundle2;
    }

    public Bundle getBundle1() {
        return this.bundle;
    }
}
