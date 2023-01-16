package com.jch.racWiFi.Utils;

import androidx.lifecycle.Lifecycle;

public class LifeCycleUtils {
    public static boolean isAtLeastResumed(Lifecycle lifecycle) {
        return lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
    }
}
