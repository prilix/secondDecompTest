package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
final class zab extends C1048Api.AbstractClientBuilder<zah, C1048Api.ApiOptions.NoOptions> {
    zab() {
    }

    public final /* synthetic */ C1048Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        C1048Api.ApiOptions.NoOptions noOptions = (C1048Api.ApiOptions.NoOptions) obj;
        return new zah(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
