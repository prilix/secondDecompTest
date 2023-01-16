package com.jch.racWiFi.fcm.model;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.fcm.util.AcActivitiesSubCategory;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.p010di.util.Constants;

public class AcActivity implements Parcelable {
    public static final Parcelable.Creator<AcActivity> CREATOR = new Parcelable.Creator<AcActivity>() {
        public AcActivity createFromParcel(Parcel parcel) {
            return new AcActivity(parcel);
        }

        public AcActivity[] newArray(int i) {
            return new AcActivity[i];
        }
    };
    private static final String PARCEL_KEY = "AcActivities_PARCEL_KEY";
    private Bundle bundle;
    @SerializedName("id")

    /* renamed from: id */
    public String f439id;
    @SerializedName("racId")
    private Long racId;
    @SerializedName("racName")
    private String racName;
    private AcActivitiesSubCategory subCategory;
    @SerializedName("timestamp")
    public long timestamp;
    private Type type = Type.AC_ACTIVITIES;
    @SerializedName("userName")
    private String userName;

    public int describeContents() {
        return 0;
    }

    public AcActivity() {
    }

    protected AcActivity(Parcel parcel) {
        Type type2;
        this.f439id = parcel.readString();
        this.racName = parcel.readString();
        this.userName = parcel.readString();
        AcActivitiesSubCategory acActivitiesSubCategory = null;
        if (parcel.readByte() == 0) {
            this.racId = null;
        } else {
            this.racId = Long.valueOf(parcel.readLong());
        }
        this.timestamp = parcel.readLong();
        this.bundle = parcel.readBundle();
        int readInt = parcel.readInt();
        if (readInt == -1) {
            type2 = null;
        } else {
            type2 = Type.values()[readInt];
        }
        this.type = type2;
        int readInt2 = parcel.readInt();
        this.subCategory = readInt2 != -1 ? AcActivitiesSubCategory.values()[readInt2] : acActivitiesSubCategory;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        this.type = type2;
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

    public AcActivitiesSubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(AcActivitiesSubCategory acActivitiesSubCategory) {
        this.subCategory = acActivitiesSubCategory;
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

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f439id);
        parcel.writeString(this.racName);
        parcel.writeString(this.userName);
        if (this.racId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.racId.longValue());
        }
        parcel.writeLong(this.timestamp);
        parcel.writeBundle(this.bundle);
        Type type2 = this.type;
        int i2 = -1;
        parcel.writeInt(type2 == null ? -1 : type2.ordinal());
        AcActivitiesSubCategory acActivitiesSubCategory = this.subCategory;
        if (acActivitiesSubCategory != null) {
            i2 = acActivitiesSubCategory.ordinal();
        }
        parcel.writeInt(i2);
    }

    public String getId() {
        return getRacId() + "_" + getSubCategory().name();
    }

    public String getTitle(Context context) {
        return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[0], new Object[]{getRacName()});
    }

    /* renamed from: com.jch.racWiFi.fcm.model.AcActivity$2 */
    static /* synthetic */ class C18232 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$AcActivitiesSubCategory;

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
                com.jch.racWiFi.fcm.util.AcActivitiesSubCategory[] r0 = com.jch.racWiFi.fcm.util.AcActivitiesSubCategory.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$AcActivitiesSubCategory = r0
                com.jch.racWiFi.fcm.util.AcActivitiesSubCategory r1 = com.jch.racWiFi.fcm.util.AcActivitiesSubCategory.ERROR_DETAILS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AcActivitiesSubCategory     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.AcActivitiesSubCategory r1 = com.jch.racWiFi.fcm.util.AcActivitiesSubCategory.SPECIAL_OPERATION     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AcActivitiesSubCategory     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.fcm.util.AcActivitiesSubCategory r1 = com.jch.racWiFi.fcm.util.AcActivitiesSubCategory.IDU_FROST_WASH     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AcActivitiesSubCategory     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.fcm.util.AcActivitiesSubCategory r1 = com.jch.racWiFi.fcm.util.AcActivitiesSubCategory.ODU_FROST_WASH     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AcActivitiesSubCategory     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.fcm.util.AcActivitiesSubCategory r1 = com.jch.racWiFi.fcm.util.AcActivitiesSubCategory.CLEAN_FILTER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.model.AcActivity.C18232.<clinit>():void");
        }
    }

    public String getDescription(Context context) {
        int i = C18232.$SwitchMap$com$jch$racWiFi$fcm$util$AcActivitiesSubCategory[getSubCategory().ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getRacName()});
        } else if (i != 5) {
            return null;
        } else {
            return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getUserName()});
        }
    }

    public Bundle getBundle() {
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(PARCEL_KEY, this);
        return bundle2;
    }

    public static AcActivity getInstance(Bundle bundle2) {
        return (AcActivity) bundle2.getParcelable(PARCEL_KEY);
    }

    public boolean equals(Object obj) {
        if (obj instanceof AcActivity) {
            return ((AcActivity) obj).getId().equals(getId());
        }
        return false;
    }

    public void setBundle(Bundle bundle2) {
        this.bundle = bundle2;
    }

    public Bundle getBundle1() {
        return this.bundle;
    }

    public void setId(String str) {
        this.f439id = str;
    }
}
