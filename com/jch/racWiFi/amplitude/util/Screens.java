package com.jch.racWiFi.amplitude.util;

import kotlin.Metadata;

@Metadata(mo36737d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\t¨\u0006\n"}, mo36738d2 = {"Lcom/jch/racWiFi/amplitude/util/Screens;", "", "strings", "", "", "(Ljava/lang/String;I[Ljava/lang/String;)V", "getStrings", "()[Ljava/lang/String;", "[Ljava/lang/String;", "SCREENS", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Screens.kt */
public enum Screens {
    SCREENS("Splash", "Login", "Home", "Manage User", "Manage ACs", "AC Settings", "Weekly Timer", "Holiday Mode", "Smart Fence", "Energy Cost Estimator", "Help", "Customer Care", "My Account", "Settings", "Privacy Policy", "Create Account Step 1 of 4", "Create Account Step 2 of 4", "Create Account Step 3 of 4", "Create Account Step 4 of 4");
    
    private final String[] strings;

    private Screens(String... strArr) {
        this.strings = strArr;
    }

    public final String[] getStrings() {
        return this.strings;
    }
}
