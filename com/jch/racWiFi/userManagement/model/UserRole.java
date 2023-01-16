package com.jch.racWiFi.userManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.JsonObject;
import com.jch.racWiFi.IGenericModelData;
import com.jch.racWiFi.p010di.util.Constants;
import p020ua.naiksoftware.stomp.dto.StompHeader;

public class UserRole implements Parcelable, IGenericModelData<JsonObject> {
    public static final UserRole CHILD = new UserRole(5, "Role_Child");
    public static final Parcelable.Creator<UserRole> CREATOR = new Parcelable.Creator<UserRole>() {
        public UserRole createFromParcel(Parcel parcel) {
            return new UserRole(parcel);
        }

        public UserRole[] newArray(int i) {
            return new UserRole[i];
        }
    };
    public static final UserRole CURRENT = new UserRole();
    public static final UserRole MEMBER = new UserRole(4, "Role_Member");
    public static final UserRole OWNER = new UserRole(1, "Role_Owner");
    public static final String ROLE_KEY = "roleId";

    /* renamed from: id */
    private int f483id;
    private String roleName;

    public int describeContents() {
        return 0;
    }

    public String getJsonKey() {
        return ROLE_KEY;
    }

    public boolean isOwner() {
        return equals(OWNER);
    }

    public boolean isMember() {
        return equals(MEMBER);
    }

    public boolean isChild() {
        return equals(CHILD);
    }

    public UserRole() {
    }

    public UserRole(int i, String str) {
        this.f483id = i;
        this.roleName = str;
    }

    public UserRole(Parcel parcel) {
        this.f483id = parcel.readInt();
        this.roleName = parcel.readString();
    }

    public int getId() {
        return this.f483id;
    }

    public void setId(int i) {
        this.f483id = i;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String str) {
        this.roleName = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f483id);
        parcel.writeString(this.roleName);
    }

    public void importFields(UserRole userRole) {
        this.f483id = userRole.f483id;
        this.roleName = userRole.roleName;
    }

    public JsonObject getJsonValue() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(StompHeader.f739ID, (Number) Integer.valueOf(this.f483id));
        jsonObject.addProperty(Constants.FCM.ROLE_NAME, this.roleName);
        return jsonObject;
    }

    public void importFromJson(JsonObject jsonObject) {
        this.f483id = jsonObject.get(StompHeader.f739ID).getAsInt();
        this.roleName = jsonObject.get(Constants.FCM.ROLE_NAME).getAsString();
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(getJsonKey(), getJsonValue());
        return jsonObject;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof UserRole)) {
            UserRole userRole = (UserRole) obj;
            return this.f483id == userRole.f483id && this.roleName.equals(userRole.getRoleName());
        }
    }

    public static UserRole getRoleType(int i) {
        if (i == 1) {
            return OWNER;
        }
        if (i == 4) {
            return MEMBER;
        }
        if (i != 5) {
            return null;
        }
        return CHILD;
    }
}
