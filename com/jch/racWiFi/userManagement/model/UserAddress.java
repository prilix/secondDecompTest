package com.jch.racWiFi.userManagement.model;

import android.location.Address;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0000J\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u00020\u0004H\u0016R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR \u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR \u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR \u0010\u0018\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\b¨\u0006$"}, mo36738d2 = {"Lcom/jch/racWiFi/userManagement/model/UserAddress;", "", "()V", "addressLine", "", "getAddressLine", "()Ljava/lang/String;", "setAddressLine", "(Ljava/lang/String;)V", "city", "getCity", "setCity", "countryCode", "getCountryCode", "setCountryCode", "state", "getState", "setState", "street", "getStreet", "setStreet", "token", "getToken", "setToken", "zipCode", "getZipCode", "setZipCode", "copy", "", "other", "isCountryCodeAvailable", "", "parseAddress", "address", "Landroid/location/Address;", "toString", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: UserAddress.kt */
public final class UserAddress {
    @SerializedName("addressLine")
    private String addressLine;
    @SerializedName("city")
    private String city;
    @SerializedName("countryCode")
    private String countryCode;
    @SerializedName("state")
    private String state;
    @SerializedName("street")
    private String street;
    @SerializedName("token")
    private String token;
    @SerializedName("zipCode")
    private String zipCode;

    public final String getAddressLine() {
        return this.addressLine;
    }

    public final void setAddressLine(String str) {
        this.addressLine = str;
    }

    public final String getStreet() {
        return this.street;
    }

    public final void setStreet(String str) {
        this.street = str;
    }

    public final String getCity() {
        return this.city;
    }

    public final void setCity(String str) {
        this.city = str;
    }

    public final String getState() {
        return this.state;
    }

    public final void setState(String str) {
        this.state = str;
    }

    public final String getZipCode() {
        return this.zipCode;
    }

    public final void setZipCode(String str) {
        this.zipCode = str;
    }

    public final String getToken() {
        return this.token;
    }

    public final void setToken(String str) {
        this.token = str;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final void setCountryCode(String str) {
        this.countryCode = str;
    }

    public final boolean isCountryCodeAvailable() {
        String str = this.countryCode;
        return str != null && !Intrinsics.areEqual((Object) str, (Object) "");
    }

    public final void parseAddress(Address address) {
        Intrinsics.checkNotNullParameter(address, "address");
        this.addressLine = address.getFeatureName();
        this.street = address.getSubLocality();
        this.city = address.getLocality();
        this.state = address.getAdminArea();
        this.zipCode = address.getPostalCode();
    }

    public final void copy(UserAddress userAddress) {
        Intrinsics.checkNotNullParameter(userAddress, "other");
        this.addressLine = userAddress.addressLine;
        this.street = userAddress.street;
        this.city = userAddress.city;
        this.state = userAddress.state;
        this.zipCode = userAddress.zipCode;
        this.countryCode = userAddress.countryCode;
    }

    public String toString() {
        String str = this.addressLine;
        boolean z = true;
        String str2 = "";
        if (str != null) {
            Intrinsics.checkNotNull(str);
            if (str.length() > 0) {
                str2 = str2 + this.addressLine + ',';
            }
        }
        String str3 = this.street;
        if (str3 != null) {
            Intrinsics.checkNotNull(str3);
            if (str3.length() > 0) {
                str2 = str2 + this.street + ',';
            }
        }
        String str4 = this.city;
        if (str4 != null) {
            Intrinsics.checkNotNull(str4);
            if (str4.length() > 0) {
                str2 = str2 + this.city + ',';
            }
        }
        String str5 = this.state;
        if (str5 != null) {
            Intrinsics.checkNotNull(str5);
            if (str5.length() > 0) {
                str2 = str2 + this.state + ',';
            }
        }
        String str6 = this.zipCode;
        if (str6 == null) {
            return str2;
        }
        Intrinsics.checkNotNull(str6);
        if (str6.length() <= 0) {
            z = false;
        }
        return z ? Intrinsics.stringPlus(str2, this.zipCode) : str2;
    }
}
