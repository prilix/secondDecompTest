package com.jch.racWiFi.dataClasses;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, mo36738d2 = {"Lcom/jch/racWiFi/dataClasses/Country;", "", "code", "", "name", "(Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "getName", "setName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Country.kt */
public final class Country {
    private String code;
    private String name;

    public static /* synthetic */ Country copy$default(Country country, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = country.code;
        }
        if ((i & 2) != 0) {
            str2 = country.name;
        }
        return country.copy(str, str2);
    }

    public final String component1() {
        return this.code;
    }

    public final String component2() {
        return this.name;
    }

    public final Country copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "code");
        Intrinsics.checkNotNullParameter(str2, "name");
        return new Country(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Country)) {
            return false;
        }
        Country country = (Country) obj;
        return Intrinsics.areEqual((Object) this.code, (Object) country.code) && Intrinsics.areEqual((Object) this.name, (Object) country.name);
    }

    public int hashCode() {
        return (this.code.hashCode() * 31) + this.name.hashCode();
    }

    public String toString() {
        return "Country(code=" + this.code + ", name=" + this.name + ')';
    }

    public Country(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "code");
        Intrinsics.checkNotNullParameter(str2, "name");
        this.code = str;
        this.name = str2;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getName() {
        return this.name;
    }

    public final void setCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.code = str;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }
}
