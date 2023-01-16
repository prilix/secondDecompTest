package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.5.0 */
public final class zze implements zza {
    /* access modifiers changed from: private */
    public AnalyticsConnector.AnalyticsConnectorListener zza;
    private AppMeasurementSdk zzb;
    private zzg zzc;

    public zze(AppMeasurementSdk appMeasurementSdk, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.zza = analyticsConnectorListener;
        this.zzb = appMeasurementSdk;
        zzg zzg = new zzg(this);
        this.zzc = zzg;
        this.zzb.registerOnMeasurementEventListener(zzg);
    }

    public final void zza(Set<String> set) {
    }

    public final void zzb() {
    }

    public final AnalyticsConnector.AnalyticsConnectorListener zza() {
        return this.zza;
    }
}
