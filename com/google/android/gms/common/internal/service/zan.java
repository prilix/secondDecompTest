package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
final class zan extends C1048Api.AbstractClientBuilder<zap, TelemetryLoggingOptions> {
    zan() {
    }

    public final /* synthetic */ C1048Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zap(context, looper, clientSettings, (TelemetryLoggingOptions) obj, connectionCallbacks, onConnectionFailedListener);
    }
}
