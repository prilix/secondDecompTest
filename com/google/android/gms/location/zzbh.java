package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.location.zzaz;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final class zzbh extends C1048Api.AbstractClientBuilder<zzaz, C1048Api.ApiOptions.NoOptions> {
    zzbh() {
    }

    public final /* bridge */ /* synthetic */ C1048Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        C1048Api.ApiOptions.NoOptions noOptions = (C1048Api.ApiOptions.NoOptions) obj;
        return new zzaz(context, looper, connectionCallbacks, onConnectionFailedListener, "locationServices", clientSettings);
    }
}
