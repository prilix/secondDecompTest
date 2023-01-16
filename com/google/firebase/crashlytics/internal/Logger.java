package com.google.firebase.crashlytics.internal;

import android.util.Log;

public class Logger {
    static final Logger DEFAULT_LOGGER = new Logger(TAG);
    public static final String TAG = "FirebaseCrashlytics";
    private int logLevel = 4;
    private final String tag;

    public Logger(String str) {
        this.tag = str;
    }

    public static Logger getLogger() {
        return DEFAULT_LOGGER;
    }

    private boolean canLog(int i) {
        return this.logLevel <= i || Log.isLoggable(this.tag, i);
    }

    /* renamed from: d */
    public void mo24919d(String str, Throwable th) {
        if (canLog(3)) {
            Log.d(this.tag, str, th);
        }
    }

    /* renamed from: v */
    public void mo24927v(String str, Throwable th) {
        if (canLog(2)) {
            Log.v(this.tag, str, th);
        }
    }

    /* renamed from: i */
    public void mo24923i(String str, Throwable th) {
        if (canLog(4)) {
            Log.i(this.tag, str, th);
        }
    }

    /* renamed from: w */
    public void mo24929w(String str, Throwable th) {
        if (canLog(5)) {
            Log.w(this.tag, str, th);
        }
    }

    /* renamed from: e */
    public void mo24921e(String str, Throwable th) {
        if (canLog(6)) {
            Log.e(this.tag, str, th);
        }
    }

    /* renamed from: d */
    public void mo24918d(String str) {
        mo24919d(str, (Throwable) null);
    }

    /* renamed from: v */
    public void mo24926v(String str) {
        mo24927v(str, (Throwable) null);
    }

    /* renamed from: i */
    public void mo24922i(String str) {
        mo24923i(str, (Throwable) null);
    }

    /* renamed from: w */
    public void mo24928w(String str) {
        mo24929w(str, (Throwable) null);
    }

    /* renamed from: e */
    public void mo24920e(String str) {
        mo24921e(str, (Throwable) null);
    }

    public void log(int i, String str) {
        log(i, str, false);
    }

    public void log(int i, String str, boolean z) {
        if (z || canLog(i)) {
            Log.println(i, this.tag, str);
        }
    }
}
