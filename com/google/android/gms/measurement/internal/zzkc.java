package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.5.0 */
final class zzkc {
    final /* synthetic */ zzkb zza;
    private zzkf zzb;

    zzkc(zzkb zzkb) {
        this.zza = zzkb;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zza.zzc();
        if (this.zza.zzs().zza(zzat.zzbk) && this.zzb != null) {
            this.zza.zzc.removeCallbacks(this.zzb);
        }
        if (this.zza.zzs().zza(zzat.zzbw)) {
            this.zza.zzr().zzr.zza(false);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j) {
        if (this.zza.zzs().zza(zzat.zzbk)) {
            this.zzb = new zzkf(this, this.zza.zzl().currentTimeMillis(), j);
            this.zza.zzc.postDelayed(this.zzb, 2000);
        }
    }
}
