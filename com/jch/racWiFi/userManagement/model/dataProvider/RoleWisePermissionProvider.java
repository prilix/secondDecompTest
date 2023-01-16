package com.jch.racWiFi.userManagement.model.dataProvider;

import android.content.Context;
import android.content.SharedPreferences;

public class RoleWisePermissionProvider {
    private static final RoleWisePermissionProvider ROLE_WISE_PERMISSION_PROVIDER = new RoleWisePermissionProvider();
    public final String ON_OFF = "on_off";
    public final String PREF_NAME = "jch-rac-permission";

    public static RoleWisePermissionProvider getInstance() {
        return ROLE_WISE_PERMISSION_PROVIDER;
    }

    private RoleWisePermissionProvider() {
    }

    public Boolean getRoleWiseMemberPermissionOnOff(Context context, int i) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("jch-rac-permission", 0);
        return Boolean.valueOf(sharedPreferences.getBoolean("on_off" + i, true));
    }

    public void setRoleWiseMemberPermissionOnOff(Context context, int i, Boolean bool) {
        SharedPreferences.Editor edit = context.getSharedPreferences("jch-rac-permission", 0).edit();
        edit.putBoolean("on_off" + i, bool.booleanValue());
        edit.commit();
    }
}
