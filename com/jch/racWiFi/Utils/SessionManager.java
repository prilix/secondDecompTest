package com.jch.racWiFi.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public static final String MyPREFERENCES = "UserManagementPref";
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedpreferences;

    interface SessionKeys {
        public static final String CLIENT_ID = "CLIENT_ID";
        public static final String DEMO_MODE = "DEMO_MODE";
        public static final String LOGIN_TYPE = "LOGIN_TYPE";
        public static final String USER_NAME = "USER_NAME";
    }

    public SessionManager(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyPREFERENCES, 0);
        this.sharedpreferences = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        this.editor = edit;
        edit.apply();
    }

    public void clearSession() {
        this.editor.clear();
        this.editor.apply();
    }

    public boolean isSocialLogin() {
        return this.sharedpreferences.getBoolean(SessionKeys.LOGIN_TYPE, false);
    }

    public void setLoginType(boolean z) {
        this.editor.putBoolean(SessionKeys.LOGIN_TYPE, z);
        this.editor.apply();
    }

    public String getGoogleClientId() {
        return this.sharedpreferences.getString(SessionKeys.CLIENT_ID, "1234");
    }

    public void setGoogleClientId(String str) {
        this.editor.putString(SessionKeys.CLIENT_ID, str);
        this.editor.apply();
    }

    public String getUserName() {
        return this.sharedpreferences.getString(SessionKeys.USER_NAME, "");
    }

    public void setUserName(String str) {
        this.editor.putString(SessionKeys.USER_NAME, str);
        this.editor.apply();
    }

    public boolean isDemoMode() {
        return this.sharedpreferences.getBoolean(SessionKeys.DEMO_MODE, false);
    }

    public void setDemoMode(boolean z) {
        this.editor.putBoolean(SessionKeys.DEMO_MODE, z);
        this.editor.apply();
    }
}
