package com.jch.racWiFi.userManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.accord.common.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.StatusCode;

public class PrivacyPolicyModel {

    public static class PrivacyPolicyDataResponse {
        @SerializedName("message")
        public String message;
        @SerializedName("result")
        public PrivacyPolicyData[] privacyPolicyData;
    }

    public static class PrivacyPolicyData implements Parcelable {
        public static final Parcelable.Creator<PrivacyPolicyData> CREATOR = new Parcelable.Creator<PrivacyPolicyData>() {
            public PrivacyPolicyData createFromParcel(Parcel parcel) {
                return new PrivacyPolicyData(parcel);
            }

            public PrivacyPolicyData[] newArray(int i) {
                return new PrivacyPolicyData[i];
            }
        };
        public static final PrivacyPolicyData CURRENT = new PrivacyPolicyData();
        public static final String PREF_KEY = "PrivacyPolicyDataPrefKey";
        public static boolean PRIVACY_POLICY_UPDATED = false;
        @SerializedName("createdAt")
        public long createdAt;
        @SerializedName("version")
        public String currentVersion = StatusCode.BUILTIN_WIRELESS;
        @SerializedName("language")
        public String language = "ja";
        @SerializedName("content")
        public String privacyPolicyMessage;

        public int describeContents() {
            return 0;
        }

        public PrivacyPolicyData() {
            Logger.m49i("", "PrivacyPolicyData Constructor");
        }

        protected PrivacyPolicyData(Parcel parcel) {
            this.privacyPolicyMessage = parcel.readString();
            this.currentVersion = parcel.readString();
        }

        public void copy(PrivacyPolicyData privacyPolicyData) {
            this.privacyPolicyMessage = privacyPolicyData.privacyPolicyMessage;
            this.currentVersion = privacyPolicyData.currentVersion;
        }

        public void persist() {
            SharedPref.getInstance().getSharedPreferenceEditor().putString(PREF_KEY, new Gson().toJson((Object) this)).commit();
            CURRENT.copy(this);
        }

        public static void importFromPreference() {
            Gson gson = new Gson();
            String string = SharedPref.getInstance().getSharedPreferencesReader().getString(PREF_KEY, (String) null);
            if (string != null) {
                CURRENT.copy((PrivacyPolicyData) gson.fromJson(string, PrivacyPolicyData.class));
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.privacyPolicyMessage);
            parcel.writeString(this.currentVersion);
        }
    }
}
