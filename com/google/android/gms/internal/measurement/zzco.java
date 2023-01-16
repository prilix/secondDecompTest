package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.5.0 */
public final class zzco implements zzcs {
    private static final Map<Uri, zzco> zza = new ArrayMap();
    private static final String[] zzh = {"key", "value"};
    private final ContentResolver zzb;
    private final Uri zzc;
    private final ContentObserver zzd;
    private final Object zze = new Object();
    private volatile Map<String, String> zzf;
    private final List<zzct> zzg = new ArrayList();

    private zzco(ContentResolver contentResolver, Uri uri) {
        zzcq zzcq = new zzcq(this, (Handler) null);
        this.zzd = zzcq;
        this.zzb = contentResolver;
        this.zzc = uri;
        contentResolver.registerContentObserver(uri, false, zzcq);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(5:5|6|7|8|9)|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.measurement.zzco zza(android.content.ContentResolver r4, android.net.Uri r5) {
        /*
            java.lang.Class<com.google.android.gms.internal.measurement.zzco> r0 = com.google.android.gms.internal.measurement.zzco.class
            monitor-enter(r0)
            java.util.Map<android.net.Uri, com.google.android.gms.internal.measurement.zzco> r1 = zza     // Catch:{ all -> 0x0018 }
            java.lang.Object r2 = r1.get(r5)     // Catch:{ all -> 0x0018 }
            com.google.android.gms.internal.measurement.zzco r2 = (com.google.android.gms.internal.measurement.zzco) r2     // Catch:{ all -> 0x0018 }
            if (r2 != 0) goto L_0x0016
            com.google.android.gms.internal.measurement.zzco r3 = new com.google.android.gms.internal.measurement.zzco     // Catch:{ SecurityException -> 0x0016 }
            r3.<init>(r4, r5)     // Catch:{ SecurityException -> 0x0016 }
            r1.put(r5, r3)     // Catch:{ SecurityException -> 0x0015 }
        L_0x0015:
            r2 = r3
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return r2
        L_0x0018:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzco.zza(android.content.ContentResolver, android.net.Uri):com.google.android.gms.internal.measurement.zzco");
    }

    public final Map<String, String> zza() {
        Map<String, String> map = this.zzf;
        if (map == null) {
            synchronized (this.zze) {
                map = this.zzf;
                if (map == null) {
                    map = zze();
                    this.zzf = map;
                }
            }
        }
        if (map != null) {
            return map;
        }
        return Collections.emptyMap();
    }

    public final void zzb() {
        synchronized (this.zze) {
            this.zzf = null;
            zzdc.zza();
        }
        synchronized (this) {
            for (zzct zza2 : this.zzg) {
                zza2.zza();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private final Map<String, String> zze() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Map<String, String> map = (Map) zzcv.zza(new zzcr(this));
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return map;
        } catch (SQLiteException | IllegalStateException | SecurityException unused) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return null;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
    }

    static synchronized void zzc() {
        synchronized (zzco.class) {
            for (zzco next : zza.values()) {
                next.zzb.unregisterContentObserver(next.zzd);
            }
            zza.clear();
        }
    }

    public final /* synthetic */ Object zza(String str) {
        return zza().get(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map zzd() {
        Map map;
        Cursor query = this.zzb.query(this.zzc, zzh, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            query.close();
            return map;
        } finally {
            query.close();
        }
    }
}
