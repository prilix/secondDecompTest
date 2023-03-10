package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzd;
import com.google.android.gms.internal.measurement.zzmt;
import com.google.firebase.messaging.Constants;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.5.0 */
final class zzfq implements Runnable {
    private final /* synthetic */ zzd zza;
    private final /* synthetic */ ServiceConnection zzb;
    private final /* synthetic */ zzfr zzc;

    zzfq(zzfr zzfr, zzd zzd, ServiceConnection serviceConnection) {
        this.zzc = zzfr;
        this.zza = zzd;
        this.zzb = serviceConnection;
    }

    public final void run() {
        zzfo zzfo = this.zzc.zza;
        String zza2 = this.zzc.zzb;
        zzd zzd = this.zza;
        ServiceConnection serviceConnection = this.zzb;
        Bundle zza3 = zzfo.zza(zza2, zzd);
        zzfo.zza.zzp().zzc();
        if (zza3 != null) {
            long j = zza3.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zzfo.zza.zzq().zzh().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = zza3.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzfo.zza.zzq().zze().zza("No referrer defined in Install Referrer response");
                } else {
                    zzfo.zza.zzq().zzw().zza("InstallReferrer API result", string);
                    zzkx zzh = zzfo.zza.zzh();
                    String valueOf = String.valueOf(string);
                    Bundle zza4 = zzh.zza(Uri.parse(valueOf.length() != 0 ? "?".concat(valueOf) : new String("?")));
                    if (zza4 == null) {
                        zzfo.zza.zzq().zze().zza("No campaign params defined in Install Referrer result");
                    } else {
                        String string2 = zza4.getString("medium");
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j2 = zza3.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                            if (j2 == 0) {
                                zzfo.zza.zzq().zze().zza("Install Referrer is missing click timestamp for ad campaign");
                            } else {
                                zza4.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == zzfo.zza.zzb().zzi.zza()) {
                            zzfo.zza.zzq().zzw().zza("Install Referrer campaign has already been logged");
                        } else if (!zzmt.zzb() || !zzfo.zza.zza().zza(zzat.zzbu) || zzfo.zza.zzaa()) {
                            zzfo.zza.zzb().zzi.zza(j);
                            zzfo.zza.zzq().zzw().zza("Logging Install Referrer campaign from sdk with ", "referrer API");
                            zza4.putString("_cis", "referrer API");
                            zzfo.zza.zzg().zza("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, zza4);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzfo.zza.zzm(), serviceConnection);
        }
    }
}
