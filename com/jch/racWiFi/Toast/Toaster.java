package com.jch.racWiFi.Toast;

import android.content.Context;
import android.widget.Toast;

public class Toaster {
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;
    private Context context;

    public Toaster(Context context2) {
        this.context = context2;
    }

    public static void makeToast(Context context2, String str, int i) {
        Toast.makeText(context2, str, i).show();
    }

    public void makeToast(String str, int i) {
        Toast.makeText(this.context, str, i).show();
    }
}
