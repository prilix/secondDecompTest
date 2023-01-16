package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.C1048Api.ApiOptions;
import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public final class ApiKey<O extends C1048Api.ApiOptions> {
    private final int zaa;
    private final C1048Api<O> zab;
    private final O zac;
    private final String zad;

    private ApiKey(C1048Api<O> api, O o, String str) {
        this.zab = api;
        this.zac = o;
        this.zad = str;
        this.zaa = Objects.hashCode(api, o, str);
    }

    public static <O extends C1048Api.ApiOptions> ApiKey<O> zaa(C1048Api<O> api, O o, String str) {
        return new ApiKey<>(api, o, str);
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApiKey)) {
            return false;
        }
        ApiKey apiKey = (ApiKey) obj;
        return Objects.equal(this.zab, apiKey.zab) && Objects.equal(this.zac, apiKey.zac) && Objects.equal(this.zad, apiKey.zad);
    }

    public final int hashCode() {
        return this.zaa;
    }

    public final String zab() {
        return this.zab.zad();
    }
}
