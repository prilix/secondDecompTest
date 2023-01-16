package com.jch.racWiFi.Utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public final class BackgroundExecutor extends Thread {
    private static Handler handler;

    public void run() {
        boolean z = Looper.myLooper() == null;
        if (z) {
            Looper.prepare();
        }
        handler = new Handler();
        if (z) {
            Looper.loop();
        }
    }

    public static void post(Runnable runnable) {
        handler.post(runnable);
    }

    public static void send(Message message) {
        handler.sendMessage(message);
    }

    public static void postOnMainThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
