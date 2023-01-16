package com.jch.racWiFi;

import androidx.drawerlayout.widget.DrawerLayout;

public interface IDrawerMenuFunctions {
    void closeMenuDrawer();

    DrawerLayout getMenuDrawer();

    void onFragmentBackPressed();

    void onLockMenuDrawerDrawer();

    void onUnLockMenuDrawerDrawer();

    void openMenuDrawer();

    void openNotificationDrawer();
}
