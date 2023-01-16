package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.firebase.messaging.Constants;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.5.0 */
public final class zzp {
    private final zzgb zza;

    public zzp(zzgb zzgb) {
        this.zza = zzgb;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zza.zzp().zzc();
        if (zzd()) {
            if (zzc()) {
                this.zza.zzb().zzv.zza((String) null);
                Bundle bundle = new Bundle();
                bundle.putString("source", "(not set)");
                bundle.putString("medium", "(not set)");
                bundle.putString("_cis", "intent");
                bundle.putLong("_cc", 1);
                this.zza.zzg().zza("auto", "_cmpx", bundle);
            } else {
                String zza2 = this.zza.zzb().zzv.zza();
                if (TextUtils.isEmpty(zza2)) {
                    this.zza.zzq().zzf().zza("Cache still valid but referrer not found");
                } else {
                    long zza3 = ((this.zza.zzb().zzw.zza() / DateUtils.MILLIS_PER_HOUR) - 1) * DateUtils.MILLIS_PER_HOUR;
                    Uri parse = Uri.parse(zza2);
                    Bundle bundle2 = new Bundle();
                    Pair pair = new Pair(parse.getPath(), bundle2);
                    for (String next : parse.getQueryParameterNames()) {
                        bundle2.putString(next, parse.getQueryParameter(next));
                    }
                    ((Bundle) pair.second).putLong("_cc", zza3);
                    this.zza.zzg().zza((String) pair.first, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, (Bundle) pair.second);
                }
                this.zza.zzb().zzv.zza((String) null);
            }
            this.zza.zzb().zzw.zza(0);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, Bundle bundle) {
        String str2;
        this.zza.zzp().zzc();
        if (!this.zza.zzaa()) {
            if (bundle == null || bundle.isEmpty()) {
                str2 = null;
            } else {
                if (str == null || str.isEmpty()) {
                    str = "auto";
                }
                Uri.Builder builder = new Uri.Builder();
                builder.path(str);
                for (String str3 : bundle.keySet()) {
                    builder.appendQueryParameter(str3, bundle.getString(str3));
                }
                str2 = builder.build().toString();
            }
            if (!TextUtils.isEmpty(str2)) {
                this.zza.zzb().zzv.zza(str2);
                this.zza.zzb().zzw.zza(this.zza.zzl().currentTimeMillis());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb() {
        if (zzd() && zzc()) {
            this.zza.zzb().zzv.zza((String) null);
        }
    }

    private final boolean zzc() {
        if (zzd() && this.zza.zzl().currentTimeMillis() - this.zza.zzb().zzw.zza() > this.zza.zza().zza((String) null, zzat.zzcl)) {
            return true;
        }
        return false;
    }

    private final boolean zzd() {
        return this.zza.zzb().zzw.zza() > 0;
    }
}
