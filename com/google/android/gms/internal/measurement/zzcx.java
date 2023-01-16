package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.5.0 */
final class zzcx implements zzcs {
    private static zzcx zza;
    @Nullable
    private final Context zzb;
    @Nullable
    private final ContentObserver zzc;

    static zzcx zza(Context context) {
        zzcx zzcx;
        synchronized (zzcx.class) {
            if (zza == null) {
                zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzcx(context) : new zzcx();
            }
            zzcx = zza;
        }
        return zzcx;
    }

    private zzcx(Context context) {
        this.zzb = context;
        zzcz zzcz = new zzcz(this, (Handler) null);
        this.zzc = zzcz;
        context.getContentResolver().registerContentObserver(zzck.zza, true, zzcz);
    }

    private zzcx() {
        this.zzb = null;
        this.zzc = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final String zza(String str) {
        if (this.zzb == null) {
            return null;
        }
        try {
            return (String) zzcv.zza(new zzcw(this, str));
        } catch (IllegalStateException | SecurityException e) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? "Unable to read GServices for: ".concat(valueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    static synchronized void zza() {
        Context context;
        synchronized (zzcx.class) {
            zzcx zzcx = zza;
            if (!(zzcx == null || (context = zzcx.zzb) == null || zzcx.zzc == null)) {
                context.getContentResolver().unregisterContentObserver(zza.zzc);
            }
            zza = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzb(String str) {
        return zzck.zza(this.zzb.getContentResolver(), str, (String) null);
    }
}
