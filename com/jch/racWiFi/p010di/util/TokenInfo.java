package com.jch.racWiFi.p010di.util;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.DemoMode.DemoModeModel;

/* renamed from: com.jch.racWiFi.di.util.TokenInfo */
public class TokenInfo implements Parcelable {
    public static final Parcelable.Creator<TokenInfo> CREATOR = new Parcelable.Creator<TokenInfo>() {
        public TokenInfo createFromParcel(Parcel parcel) {
            return new TokenInfo(parcel);
        }

        public TokenInfo[] newArray(int i) {
            return new TokenInfo[i];
        }
    };
    @SerializedName("id")

    /* renamed from: id */
    private float f431id;
    @SerializedName("new")
    private boolean isNew;
    @SerializedName("refreshToken")
    private String refreshToken;
    @SerializedName("token")
    private String token;
    @SerializedName("type")
    private String type;

    public int describeContents() {
        return 0;
    }

    public TokenInfo() {
        setType(DemoModeModel.TOKEN_TYPE);
        setToken("");
        setNew(false);
        setId(-1.0f);
        setRefreshToken("");
    }

    protected TokenInfo(Parcel parcel) {
        this.isNew = parcel.readByte() != 0;
        this.refreshToken = parcel.readString();
        this.token = parcel.readString();
        this.type = parcel.readString();
        this.f431id = parcel.readFloat();
    }

    public boolean isNew() {
        return this.isNew;
    }

    public String getBearerRefreshToken() {
        return getType() + " " + getRefreshToken();
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getToken() {
        return this.token;
    }

    public String getType() {
        return this.type;
    }

    public float getId() {
        return this.f431id;
    }

    public void setNew(boolean z) {
        this.isNew = z;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setId(float f) {
        this.f431id = f;
    }

    public String getBearerToken() {
        return getType() + " " + getToken();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isNew ? (byte) 1 : 0);
        parcel.writeString(this.refreshToken);
        parcel.writeString(this.token);
        parcel.writeString(this.type);
        parcel.writeFloat(this.f431id);
    }

    public void clear() {
        setType("");
        setToken("");
        setNew(false);
        setId(-1.0f);
        setRefreshToken("");
    }
}
