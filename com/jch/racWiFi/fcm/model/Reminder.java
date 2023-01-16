package com.jch.racWiFi.fcm.model;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.fcm.util.ReminderSubCategory;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.p010di.util.Constants;

public class Reminder implements Parcelable {
    public static final Parcelable.Creator<Reminder> CREATOR = new Parcelable.Creator<Reminder>() {
        public Reminder createFromParcel(Parcel parcel) {
            return new Reminder(parcel);
        }

        public Reminder[] newArray(int i) {
            return new Reminder[i];
        }
    };
    public static final String PARCEL_KEY = "Reminder_PARCEL_KEY";
    private Bundle bundle;
    @SerializedName("familyId")
    public String familyId;
    @SerializedName("familyName")
    public String familyName;
    @SerializedName("id")

    /* renamed from: id */
    private String f444id;
    @SerializedName("infoUrl")
    private String infoUrl;
    private ReminderSubCategory subCategory;
    @SerializedName("timestamp")
    private long timestamp;
    private Type type = Type.REMINDER;
    @SerializedName("userName")
    private String userName;

    public int describeContents() {
        return 0;
    }

    public Reminder() {
    }

    protected Reminder(Parcel parcel) {
        Type type2;
        this.f444id = parcel.readString();
        this.infoUrl = parcel.readString();
        this.userName = parcel.readString();
        this.timestamp = parcel.readLong();
        this.bundle = parcel.readBundle();
        int readInt = parcel.readInt();
        ReminderSubCategory reminderSubCategory = null;
        if (readInt == -1) {
            type2 = null;
        } else {
            type2 = Type.values()[readInt];
        }
        this.type = type2;
        int readInt2 = parcel.readInt();
        this.subCategory = readInt2 != -1 ? ReminderSubCategory.values()[readInt2] : reminderSubCategory;
        this.familyId = parcel.readString();
        this.familyName = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f444id);
        parcel.writeString(this.infoUrl);
        parcel.writeString(this.userName);
        parcel.writeLong(this.timestamp);
        parcel.writeBundle(this.bundle);
        Type type2 = this.type;
        int i2 = -1;
        parcel.writeInt(type2 == null ? -1 : type2.ordinal());
        ReminderSubCategory reminderSubCategory = this.subCategory;
        if (reminderSubCategory != null) {
            i2 = reminderSubCategory.ordinal();
        }
        parcel.writeInt(i2);
        parcel.writeString(this.familyId);
        parcel.writeString(this.familyName);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Reminder) {
            return ((Reminder) obj).f444id.equals(this.f444id);
        }
        return false;
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

    public String getId() {
        return this.f444id;
    }

    public void setId(String str) {
        this.f444id = str;
    }

    public String getInfoUrl() {
        return this.infoUrl;
    }

    public void setInfoUrl(String str) {
        this.infoUrl = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public ReminderSubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(ReminderSubCategory reminderSubCategory) {
        this.subCategory = reminderSubCategory;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    /* renamed from: com.jch.racWiFi.fcm.model.Reminder$2 */
    static /* synthetic */ class C18302 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$ReminderSubCategory;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.jch.racWiFi.fcm.util.ReminderSubCategory[] r0 = com.jch.racWiFi.fcm.util.ReminderSubCategory.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$ReminderSubCategory = r0
                com.jch.racWiFi.fcm.util.ReminderSubCategory r1 = com.jch.racWiFi.fcm.util.ReminderSubCategory.CLEANING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$ReminderSubCategory     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.ReminderSubCategory r1 = com.jch.racWiFi.fcm.util.ReminderSubCategory.USER_ACCEPTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.model.Reminder.C18302.<clinit>():void");
        }
    }

    public String getTitle(Context context) {
        int i = C18302.$SwitchMap$com$jch$racWiFi$fcm$util$ReminderSubCategory[getSubCategory().ordinal()];
        if (i == 1 || i == 2) {
            return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[0]);
        }
        return null;
    }

    public String getDescription(Context context) {
        int i = C18302.$SwitchMap$com$jch$racWiFi$fcm$util$ReminderSubCategory[getSubCategory().ordinal()];
        if (i == 1) {
            return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1]);
        }
        if (i != 2) {
            return null;
        }
        return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getUserName()});
    }

    public void setBundle(Bundle bundle2) {
        this.bundle = bundle2;
    }

    public Bundle getBundle1() {
        return this.bundle;
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
}
