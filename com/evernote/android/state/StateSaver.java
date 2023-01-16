package com.evernote.android.state;

import android.app.Application;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import java.util.LinkedHashMap;

public final class StateSaver {
    public static final String ANDROID_PREFIX = "android.";
    private static final StateSaverImpl IMPL = new StateSaverImpl(new LinkedHashMap());
    public static final String JAVA_PREFIX = "java.";
    public static final String SUFFIX = "$$StateSaver";

    public static <T> void saveInstanceState(T t, Bundle bundle) {
        IMPL.saveInstanceState(t, bundle);
    }

    public static <T> void restoreInstanceState(T t, Bundle bundle) {
        IMPL.restoreInstanceState(t, bundle);
    }

    public static <T extends View> Parcelable saveInstanceState(T t, Parcelable parcelable) {
        return IMPL.saveInstanceState(t, parcelable);
    }

    public static <T extends View> Parcelable restoreInstanceState(T t, Parcelable parcelable) {
        return IMPL.restoreInstanceState(t, parcelable);
    }

    public static void setEnabledForAllActivitiesAndSupportFragments(Application application, boolean z) {
        IMPL.setEnabledForAllActivitiesAndSupportFragments(application, z);
    }

    private StateSaver() {
        throw new UnsupportedOperationException();
    }
}
