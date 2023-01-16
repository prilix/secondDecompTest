package com.jch.racWiFi.main.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, mo36738d2 = {"Lcom/jch/racWiFi/main/model/Country;", "", "locale", "", "milesOrKm", "(Ljava/lang/String;Ljava/lang/String;)V", "getLocale", "()Ljava/lang/String;", "setLocale", "(Ljava/lang/String;)V", "getMilesOrKm", "setMilesOrKm", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Country.kt */
public final class Country {
    private String locale;
    private String milesOrKm;

    public Country(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "locale");
        Intrinsics.checkNotNullParameter(str2, "milesOrKm");
        this.locale = str;
        this.milesOrKm = str2;
    }

    public final String getLocale() {
        return this.locale;
    }

    public final String getMilesOrKm() {
        return this.milesOrKm;
    }

    public final void setLocale(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.locale = str;
    }

    public final void setMilesOrKm(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.milesOrKm = str;
    }
}
