package com.jch.racWiFi.fcm.model;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.fcm.util.MaintenanceSubCategory;
import com.jch.racWiFi.fcm.util.Persistence;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import org.apache.commons.lang3.time.DateUtils;

public class Maintenance implements Parcelable {
    public static final Parcelable.Creator<Maintenance> CREATOR = new Parcelable.Creator<Maintenance>() {
        public Maintenance createFromParcel(Parcel parcel) {
            return new Maintenance(parcel);
        }

        public Maintenance[] newArray(int i) {
            return new Maintenance[i];
        }
    };
    public static final String PARCEL_KEY = "Maintenance_PARCEL_KEY";
    public static final String SP_KEY = "Maintenance_PrefKey";
    @SerializedName("id")

    /* renamed from: id */
    public String f443id;
    @SerializedName("maintenanceDate")
    public long maintenanceDate;
    @SerializedName("maintenanceDuration")
    public long maintenanceDuration;
    private MaintenanceSubCategory subCategory;
    private Type type = Type.MAINTENANCE;

    public int describeContents() {
        return 0;
    }

    public Maintenance() {
    }

    protected Maintenance(Parcel parcel) {
        Type type2;
        this.f443id = parcel.readString();
        this.maintenanceDate = parcel.readLong();
        this.maintenanceDuration = parcel.readLong();
        int readInt = parcel.readInt();
        MaintenanceSubCategory maintenanceSubCategory = null;
        if (readInt == -1) {
            type2 = null;
        } else {
            type2 = Type.values()[readInt];
        }
        this.type = type2;
        int readInt2 = parcel.readInt();
        this.subCategory = readInt2 != -1 ? MaintenanceSubCategory.values()[readInt2] : maintenanceSubCategory;
    }

    public String getDurationForDisplay(Context context) {
        long j = this.maintenanceDuration;
        long j2 = j % DateUtils.MILLIS_PER_HOUR;
        boolean z = true;
        boolean z2 = j - j2 == DateUtils.MILLIS_PER_HOUR;
        if (j2 <= 60000) {
            long j3 = j - j2;
            this.maintenanceDuration = j3;
            j2 = j3 % DateUtils.MILLIS_PER_HOUR;
        }
        if (j2 != 0) {
            z = false;
        }
        long j4 = this.maintenanceDuration;
        if (j4 == DateUtils.MILLIS_PER_HOUR) {
            context.getString(R.string.duration_format_hr);
        } else if (z) {
            context.getString(R.string.duration_format_hrs);
        } else if (z2) {
            context.getString(R.string.duration_format_hr);
            context.getString(R.string.duration_format_min);
        } else if (j4 < DateUtils.MILLIS_PER_HOUR) {
            context.getString(R.string.duration_format_min);
        } else {
            context.getString(R.string.duration_format_hrs);
            context.getString(R.string.duration_format_min);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(context.getString(R.string.duration_format_hrs) + " " + context.getString(R.string.duration_format_min), Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date(this.maintenanceDuration));
    }

    public String[] getTimeStampForDisplay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateAndTimeUtils.DATE_FORMAT_WITH_SLASH, Locale.getDefault());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return new String[]{simpleDateFormat.format(new Date(this.maintenanceDate)), simpleDateFormat2.format(new Date(this.maintenanceDate))};
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f443id);
        parcel.writeLong(this.maintenanceDate);
        parcel.writeLong(this.maintenanceDuration);
        Type type2 = this.type;
        int i2 = -1;
        parcel.writeInt(type2 == null ? -1 : type2.ordinal());
        MaintenanceSubCategory maintenanceSubCategory = this.subCategory;
        if (maintenanceSubCategory != null) {
            i2 = maintenanceSubCategory.ordinal();
        }
        parcel.writeInt(i2);
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        this.type = type2;
    }

    public String getId() {
        return this.f443id;
    }

    public void setId(String str) {
        this.f443id = str;
    }

    public long getMaintenanceDate() {
        return this.maintenanceDate;
    }

    public void setMaintenanceDate(long j) {
        this.maintenanceDate = j;
    }

    public long getMaintenanceDuration() {
        return this.maintenanceDuration;
    }

    public void setMaintenanceDuration(long j) {
        this.maintenanceDuration = j;
    }

    public MaintenanceSubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(MaintenanceSubCategory maintenanceSubCategory) {
        this.subCategory = maintenanceSubCategory;
    }

    public String getTitle(Context context) {
        return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[0]);
    }

    /* renamed from: com.jch.racWiFi.fcm.model.Maintenance$2 */
    static /* synthetic */ class C18282 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$MaintenanceSubCategory;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.jch.racWiFi.fcm.util.MaintenanceSubCategory[] r0 = com.jch.racWiFi.fcm.util.MaintenanceSubCategory.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$MaintenanceSubCategory = r0
                com.jch.racWiFi.fcm.util.MaintenanceSubCategory r1 = com.jch.racWiFi.fcm.util.MaintenanceSubCategory.PLANNED_MAINTENANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$MaintenanceSubCategory     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.MaintenanceSubCategory r1 = com.jch.racWiFi.fcm.util.MaintenanceSubCategory.SERVICE_UNAVAILABLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.model.Maintenance.C18282.<clinit>():void");
        }
    }

    public String getDescription(Context context) {
        int i = C18282.$SwitchMap$com$jch$racWiFi$fcm$util$MaintenanceSubCategory[getSubCategory().ordinal()];
        if (i == 1) {
            String[] timeStampForDisplay = getTimeStampForDisplay();
            return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getDurationForDisplay(context), timeStampForDisplay[0], timeStampForDisplay[1]});
        } else if (i != 2) {
            return null;
        } else {
            return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1]);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Maintenance) {
            return ((Maintenance) obj).getId().equals(getId());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.type, this.f443id, Long.valueOf(this.maintenanceDate), Long.valueOf(this.maintenanceDuration), this.subCategory});
    }

    public void persist() {
        getPersistence().persist(SP_KEY, this);
    }

    private Persistence<Maintenance> getPersistence() {
        return new Persistence<>();
    }

    public Maintenance obtain() {
        return getPersistence().obtain(SP_KEY, Maintenance.class);
    }

    public void clear() {
        getPersistence().persist(SP_KEY, null);
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCEL_KEY, this);
        return bundle;
    }
}
