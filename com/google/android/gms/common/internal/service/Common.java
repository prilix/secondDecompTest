package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.C1048Api;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public final class Common {
    public static final C1048Api<C1048Api.ApiOptions.NoOptions> API;
    public static final C1048Api.ClientKey<zah> CLIENT_KEY;
    public static final zae zaa = new zae();
    private static final C1048Api.AbstractClientBuilder<zah, C1048Api.ApiOptions.NoOptions> zab;

    static {
        C1048Api.ClientKey<zah> clientKey = new C1048Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zab zab2 = new zab();
        zab = zab2;
        API = new C1048Api<>("Common.API", zab2, clientKey);
    }
}
