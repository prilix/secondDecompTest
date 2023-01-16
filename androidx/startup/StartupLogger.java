package androidx.startup;

import android.util.Log;

public final class StartupLogger {
    static final boolean DEBUG = false;
    private static final String TAG = "StartupLogger";

    private StartupLogger() {
    }

    /* renamed from: i */
    public static void m42i(String str) {
        Log.i(TAG, str);
    }

    /* renamed from: e */
    public static void m41e(String str, Throwable th) {
        Log.e(TAG, str, th);
    }
}
