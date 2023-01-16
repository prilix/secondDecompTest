package com.jch.racWiFi.fcm.model;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.fcm.util.AlertSubCategory;
import com.jch.racWiFi.fcm.util.Roles;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;

public class Alert implements Parcelable {
    public static final Parcelable.Creator<Alert> CREATOR = new Parcelable.Creator<Alert>() {
        public Alert createFromParcel(Parcel parcel) {
            return new Alert(parcel);
        }

        public Alert[] newArray(int i) {
            return new Alert[i];
        }
    };
    public static final String PARCEL_KEY = "Alert_PARCEL_KEY";
    private Bundle bundle;
    @SerializedName("ecPercentage")
    private String ecPercentage;
    @SerializedName("familyId")
    public String familyId;
    @SerializedName("familyName")
    public String familyName;
    @SerializedName("homeName")
    private String homeName;
    @SerializedName("id")

    /* renamed from: id */
    private String f440id;
    @SerializedName("memberName")
    private String memberName;
    @SerializedName("racId")
    private Long racId;
    @SerializedName("racName")
    private String racName;
    @SerializedName("roleName")
    private String roleName;
    private AlertSubCategory subCategory;
    @SerializedName("timestamp")
    private long timestamp;
    private Type type = Type.ALERTS;
    @SerializedName("userName")
    private String userName;

    public int describeContents() {
        return 0;
    }

    public Alert() {
    }

    protected Alert(Parcel parcel) {
        Type type2;
        this.f440id = parcel.readString();
        this.familyId = parcel.readString();
        this.ecPercentage = parcel.readString();
        this.racName = parcel.readString();
        this.homeName = parcel.readString();
        this.memberName = parcel.readString();
        this.roleName = parcel.readString();
        AlertSubCategory alertSubCategory = null;
        if (parcel.readByte() == 0) {
            this.racId = null;
        } else {
            this.racId = Long.valueOf(parcel.readLong());
        }
        this.timestamp = parcel.readLong();
        this.userName = parcel.readString();
        this.bundle = parcel.readBundle();
        int readInt = parcel.readInt();
        if (readInt == -1) {
            type2 = null;
        } else {
            type2 = Type.values()[readInt];
        }
        this.type = type2;
        int readInt2 = parcel.readInt();
        this.subCategory = readInt2 != -1 ? AlertSubCategory.values()[readInt2] : alertSubCategory;
        this.familyName = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f440id);
        parcel.writeString(this.familyId);
        parcel.writeString(this.ecPercentage);
        parcel.writeString(this.racName);
        parcel.writeString(this.homeName);
        parcel.writeString(this.memberName);
        parcel.writeString(this.roleName);
        if (this.racId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.racId.longValue());
        }
        parcel.writeLong(this.timestamp);
        parcel.writeString(this.userName);
        parcel.writeBundle(this.bundle);
        Type type2 = this.type;
        int i2 = -1;
        parcel.writeInt(type2 == null ? -1 : type2.ordinal());
        AlertSubCategory alertSubCategory = this.subCategory;
        if (alertSubCategory != null) {
            i2 = alertSubCategory.ordinal();
        }
        parcel.writeInt(i2);
        parcel.writeString(this.familyName);
    }

    public String getEcPercentageWithSymbol() {
        return getEcPercentage() + "%";
    }

    public String getTitle(Context context) {
        switch (C18252.$SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory[getSubCategory().ordinal()]) {
            case 1:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[0], new Object[]{"80%"});
            case 2:
            case 3:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[0], new Object[]{getRacName()});
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[0]);
            default:
                return null;
        }
    }

    public String getDescription(Context context) {
        switch (C18252.$SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory[getSubCategory().ordinal()]) {
            case 1:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getFamilyName(), getEcPercentageWithSymbol()});
            case 2:
            case 3:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getHomeName(), getRacName()});
            case 4:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getHomeName(), getRoleName(), getRacName()});
            case 5:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getHomeName(), getRoleName()});
            case 6:
            case 14:
            case 15:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getHomeName()});
            case 7:
                if (getRoleName() == null || getRoleName().isEmpty()) {
                    return getRoleDesc(context, Constants.CC.getResource(context).getString(R.string.dash_dash));
                }
                int i = C18252.$SwitchMap$com$jch$racWiFi$fcm$util$Roles[Roles.valueOf(getRoleName()).ordinal()];
                if (i == 1) {
                    return getRoleDesc(context, Constants.CC.getResource(context).getString(R.string.common_lbl_owner));
                }
                if (i == 2) {
                    return getRoleDesc(context, Constants.CC.getResource(context).getString(R.string.common_lbl_member));
                }
                if (i != 3) {
                    return getRoleDesc(context, getRoleName());
                }
                return getRoleDesc(context, Constants.CC.getResource(context).getString(R.string.common_lbl_child));
            case 8:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getUserName(), getHomeName()});
            case 9:
            case 10:
            case 11:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getHomeName(), getUserName()});
            case 12:
            case 13:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getHomeName(), getUserName(), getRacName()});
            case 16:
            case 17:
                return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getHomeName(), getRacName()});
            default:
                return null;
        }
    }

    /* renamed from: com.jch.racWiFi.fcm.model.Alert$2 */
    static /* synthetic */ class C18252 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$Roles;

        /* JADX WARNING: Can't wrap try/catch for region: R(43:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(44:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(45:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0063 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0079 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0091 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x009d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00a9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00b5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00c1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00cd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00d9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00e5 */
        static {
            /*
                com.jch.racWiFi.fcm.util.Roles[] r0 = com.jch.racWiFi.fcm.util.Roles.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$Roles = r0
                r1 = 1
                com.jch.racWiFi.fcm.util.Roles r2 = com.jch.racWiFi.fcm.util.Roles.OWNER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$jch$racWiFi$fcm$util$Roles     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.Roles r3 = com.jch.racWiFi.fcm.util.Roles.MEMBER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$jch$racWiFi$fcm$util$Roles     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.fcm.util.Roles r4 = com.jch.racWiFi.fcm.util.Roles.CHILD     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.jch.racWiFi.fcm.util.AlertSubCategory[] r3 = com.jch.racWiFi.fcm.util.AlertSubCategory.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory = r3
                com.jch.racWiFi.fcm.util.AlertSubCategory r4 = com.jch.racWiFi.fcm.util.AlertSubCategory.ENERGY_CONSUMPTION_BUDGET     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r3 = com.jch.racWiFi.fcm.util.AlertSubCategory.RAC_OFFLINE     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x004d }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.RAC_OFFLINE_20_HOURS     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.USER_PERMISSIONS_UPDATED_FOR_SPECIFIC_AC     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0063 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.USER_PERMISSIONS_UPDATED_FOR_ALL_ACS     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x006e }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.REMOVED_FROM_HOME     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0079 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.ROLE_UPDATED     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.USER_DETACHES_HIMSELF     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x0091 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_UPDATED     // Catch:{ NoSuchFieldError -> 0x0091 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0091 }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0091 }
            L_0x0091:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x009d }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_ON_ALL     // Catch:{ NoSuchFieldError -> 0x009d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009d }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009d }
            L_0x009d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00a9 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_ALL     // Catch:{ NoSuchFieldError -> 0x00a9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a9 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a9 }
            L_0x00a9:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00b5 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_ON_SPECIFIC     // Catch:{ NoSuchFieldError -> 0x00b5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b5 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b5 }
            L_0x00b5:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00c1 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC     // Catch:{ NoSuchFieldError -> 0x00c1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c1 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c1 }
            L_0x00c1:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00cd }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_DUE_TO_INTERRUPTION     // Catch:{ NoSuchFieldError -> 0x00cd }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cd }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cd }
            L_0x00cd:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00d9 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_MULTIPLE_AS_PER_SCHEDULE     // Catch:{ NoSuchFieldError -> 0x00d9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d9 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d9 }
            L_0x00d9:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00e5 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC_AS_PER_SCHEDULE     // Catch:{ NoSuchFieldError -> 0x00e5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e5 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e5 }
            L_0x00e5:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$AlertSubCategory     // Catch:{ NoSuchFieldError -> 0x00f1 }
                com.jch.racWiFi.fcm.util.AlertSubCategory r1 = com.jch.racWiFi.fcm.util.AlertSubCategory.HOLIDAY_MODE_SWITCHED_OFF_DUE_TO_REMOTE_ACTIVITY     // Catch:{ NoSuchFieldError -> 0x00f1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f1 }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f1 }
            L_0x00f1:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.model.Alert.C18252.<clinit>():void");
        }
    }

    private String getRoleDesc(Context context, String str) {
        return Constants.CC.getResource(context).getString(getSubCategory().getAttributes()[1], new Object[]{getHomeName(), str});
    }

    public boolean equals(Object obj) {
        if (obj instanceof Alert) {
            return ((Alert) obj).f440id.equals(this.f440id);
        }
        return false;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        this.type = type2;
    }

    public String getId() {
        return this.f440id;
    }

    public void setId(String str) {
        this.f440id = str;
    }

    public AlertSubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(AlertSubCategory alertSubCategory) {
        this.subCategory = alertSubCategory;
    }

    public String getEcPercentage() {
        return this.ecPercentage;
    }

    public void setEcPercentage(String str) {
        this.ecPercentage = str;
    }

    public String getRacName() {
        return this.racName;
    }

    public void setRacName(String str) {
        this.racName = str;
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

    public String getFamilyId() {
        return this.familyId;
    }

    public void setFamilyId(String str) {
        this.familyId = str;
    }

    public String getHomeName() {
        return this.homeName;
    }

    public void setHomeName(String str) {
        this.homeName = str;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String str) {
        this.roleName = str;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String str) {
        this.memberName = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String str) {
        this.familyName = str;
    }

    public Bundle getBundle() {
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(PARCEL_KEY, this);
        return bundle2;
    }

    public void setBundle(Bundle bundle2) {
        this.bundle = bundle2;
    }

    public Bundle getBundle1() {
        return this.bundle;
    }
}
