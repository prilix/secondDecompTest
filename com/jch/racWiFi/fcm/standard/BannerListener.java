package com.jch.racWiFi.fcm.standard;

import android.view.View;

public interface BannerListener<T, B> {
    void onClick(View view, T t, B b);

    void onItemClick(View view, T t, B b);
}
