package com.jch.racWiFi.fcm.standard;

import android.view.View;

public interface FcmListener<T, A> {
    void onClick(View view, T t, A a);

    void onClose(View view, T t, A a);
}
