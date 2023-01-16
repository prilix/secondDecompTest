package com.google.android.datatransport.runtime.logging;

import android.util.Log;

public final class Logging {
    private Logging() {
    }

    private static String getTag(String str) {
        return "TransportRuntime." + str;
    }

    /* renamed from: d */
    public static void m69d(String str, String str2) {
        Log.d(getTag(str), str2);
    }

    /* renamed from: d */
    public static void m70d(String str, String str2, Object obj) {
        Log.d(getTag(str), String.format(str2, new Object[]{obj}));
    }

    /* renamed from: d */
    public static void m71d(String str, String str2, Object obj, Object obj2) {
        Log.d(getTag(str), String.format(str2, new Object[]{obj, obj2}));
    }

    /* renamed from: d */
    public static void m72d(String str, String str2, Object... objArr) {
        Log.d(getTag(str), String.format(str2, objArr));
    }

    /* renamed from: i */
    public static void m74i(String str, String str2) {
        Log.i(getTag(str), str2);
    }

    /* renamed from: e */
    public static void m73e(String str, String str2, Throwable th) {
        Log.e(getTag(str), str2, th);
    }

    /* renamed from: w */
    public static void m75w(String str, String str2, Object obj) {
        Log.w(getTag(str), String.format(str2, new Object[]{obj}));
    }
}
