package com.jch.racWiFi.userManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.IGenericModelData;
import com.jch.racWiFi.SharedPreference.SharedPref;

@Deprecated
public class TokenData implements Parcelable, IGenericModelData<String> {
    public static final Parcelable.Creator<TokenData> CREATOR = new Parcelable.Creator<TokenData>() {
        public TokenData createFromParcel(Parcel parcel) {
            return new TokenData(parcel);
        }

        public TokenData[] newArray(int i) {
            return new TokenData[i];
        }
    };
    private static TokenData CURRENT = new TokenData();
    public static final String TOKEN_KEY = "token";
    @SerializedName("new")
    private boolean newLogin;
    private String refreshToken = "";
    @SerializedName("token")
    private String token = "";
    @SerializedName("type")
    private String type = DemoModeModel.TOKEN_TYPE;
    @SerializedName("id")
    private int userId;

    public int describeContents() {
        return 0;
    }

    public String getJsonKey() {
        return "token";
    }

    public JsonObject toJson() {
        return null;
    }

    protected TokenData(Parcel parcel) {
        this.token = parcel.readString();
        this.refreshToken = parcel.readString();
        this.userId = parcel.readInt();
        this.type = parcel.readString();
        this.newLogin = parcel.readByte() != 0;
    }

    public static TokenData getCurrentTokenDataObject() {
        return CURRENT;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean isNewLogin() {
        return this.newLogin;
    }

    public void setNewLogin(boolean z) {
        this.newLogin = z;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public TokenData() {
    }

    public static String getToken() {
        return CURRENT.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public String getJsonValue() {
        return this.token;
    }

    public void importFromJson(JsonObject jsonObject) {
        this.token = jsonObject.get("token").getAsString();
    }

    @Deprecated
    public void persist() {
        SharedPref.getInstance().getSharedPreferenceEditor().putString("token", new Gson().toJson((Object) this)).commit();
        CURRENT.copy(this);
    }

    @Deprecated
    public static void loadTokenFromPreference() {
        String string = SharedPref.getInstance().getSharedPreferencesReader().getString("token", getEmptyObjGsonString());
        CURRENT.copy((TokenData) new Gson().fromJson(string, TokenData.class));
    }

    public static void setTokenDataForDemoMode(TokenData tokenData) {
        CURRENT.copy(tokenData);
    }

    public static void clearDemoModeTokenData() {
        CURRENT.clear();
    }

    public void copy(TokenData tokenData) {
        this.token = tokenData.token;
        this.userId = tokenData.userId;
        this.type = tokenData.type;
        this.refreshToken = tokenData.refreshToken;
    }

    public static String getBearerTokenFromPrefs() {
        return CURRENT.getBearerToken();
    }

    public static String getRefreshTokenFromPrefs() {
        return CURRENT.refreshToken;
    }

    public static void clearTokenData() {
        CURRENT.clear();
        SharedPref.getInstance().getSharedPreferenceEditor().putString("token", getEmptyObjGsonString()).commit();
    }

    private static TokenData getObjFromPrefs() {
        Gson gson = new Gson();
        String string = SharedPref.getInstance().getSharedPreferencesReader().getString("token", (String) null);
        if (string != null) {
            return (TokenData) gson.fromJson(string, TokenData.class);
        }
        return new TokenData();
    }

    public String getBearerToken() {
        return this.type + " " + this.token;
    }

    public String getRefreshToken() {
        return this.type + " " + this.refreshToken;
    }

    private boolean isValid() {
        return !getBearerToken().equals(DemoModeModel.DEMO_TOKEN);
    }

    public static boolean isCurrentTokenDataValid() {
        return CURRENT.isValid();
    }

    private static String getEmptyObjGsonString() {
        return new Gson().toJson((Object) new TokenData());
    }

    public void clear() {
        setType(DemoModeModel.TOKEN_TYPE);
        setToken("");
        setNewLogin(false);
        setUserId(-1);
        setRefreshToken("");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.token);
        parcel.writeString(this.refreshToken);
        parcel.writeInt(this.userId);
        parcel.writeString(this.type);
        parcel.writeByte(this.newLogin ? (byte) 1 : 0);
    }
}
