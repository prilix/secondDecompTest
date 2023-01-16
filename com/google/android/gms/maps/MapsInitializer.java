package com.google.android.gms.maps;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static boolean zzb = false;
    private static Renderer zzc = Renderer.LEGACY;

    /* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        int initialize;
        synchronized (MapsInitializer.class) {
            initialize = initialize(context, (Renderer) null, (OnMapsSdkInitializedCallback) null);
        }
        return initialize;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0092, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        return 0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a A[Catch:{ RemoteException -> 0x0093, RemoteException -> 0x0066 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008c A[Catch:{ RemoteException -> 0x0093, RemoteException -> 0x0066 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized int initialize(android.content.Context r5, com.google.android.gms.maps.MapsInitializer.Renderer r6, com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        /*
            java.lang.Class<com.google.android.gms.maps.MapsInitializer> r0 = com.google.android.gms.maps.MapsInitializer.class
            monitor-enter(r0)
            java.lang.String r1 = "Context is null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r1)     // Catch:{ all -> 0x009f }
            java.lang.String r1 = zza     // Catch:{ all -> 0x009f }
            java.lang.String r2 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x009f }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x009f }
            r3.length()     // Catch:{ all -> 0x009f }
            java.lang.String r3 = "preferredRenderer: "
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x009f }
            java.lang.String r2 = r3.concat(r2)     // Catch:{ all -> 0x009f }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x009f }
            boolean r1 = zzb     // Catch:{ all -> 0x009f }
            r2 = 0
            if (r1 == 0) goto L_0x0030
            if (r7 == 0) goto L_0x002e
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = zzc     // Catch:{ all -> 0x009f }
            r7.onMapsSdkInitialized(r5)     // Catch:{ all -> 0x009f }
        L_0x002e:
            monitor-exit(r0)
            return r2
        L_0x0030:
            com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzca.zza(r5, r6)     // Catch:{ GooglePlayServicesNotAvailableException -> 0x009a }
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r3 = r1.zze()     // Catch:{ RemoteException -> 0x0093 }
            com.google.android.gms.maps.CameraUpdateFactory.zza(r3)     // Catch:{ RemoteException -> 0x0093 }
            com.google.android.gms.internal.maps.zzi r3 = r1.zzj()     // Catch:{ RemoteException -> 0x0093 }
            com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r3)     // Catch:{ RemoteException -> 0x0093 }
            r3 = 1
            zzb = r3     // Catch:{ all -> 0x009f }
            r4 = 2
            if (r6 == 0) goto L_0x0053
            int r6 = r6.ordinal()     // Catch:{ all -> 0x009f }
            if (r6 == 0) goto L_0x0054
            if (r6 == r3) goto L_0x0051
            goto L_0x0053
        L_0x0051:
            r3 = 2
            goto L_0x0054
        L_0x0053:
            r3 = 0
        L_0x0054:
            int r6 = r1.zzd()     // Catch:{ RemoteException -> 0x0066 }
            if (r6 != r4) goto L_0x005e
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST     // Catch:{ RemoteException -> 0x0066 }
            zzc = r6     // Catch:{ RemoteException -> 0x0066 }
        L_0x005e:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch:{ RemoteException -> 0x0066 }
            r1.zzl(r5, r3)     // Catch:{ RemoteException -> 0x0066 }
            goto L_0x006e
        L_0x0066:
            r5 = move-exception
            java.lang.String r6 = zza     // Catch:{ all -> 0x009f }
            java.lang.String r1 = "Failed to retrieve renderer type or log initialization."
            android.util.Log.e(r6, r1, r5)     // Catch:{ all -> 0x009f }
        L_0x006e:
            java.lang.String r5 = zza     // Catch:{ all -> 0x009f }
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = zzc     // Catch:{ all -> 0x009f }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x009f }
            java.lang.String r1 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x009f }
            r1.length()     // Catch:{ all -> 0x009f }
            java.lang.String r1 = "loadedRenderer: "
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x009f }
            java.lang.String r6 = r1.concat(r6)     // Catch:{ all -> 0x009f }
            android.util.Log.d(r5, r6)     // Catch:{ all -> 0x009f }
            if (r7 == 0) goto L_0x0091
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = zzc     // Catch:{ all -> 0x009f }
            r7.onMapsSdkInitialized(r5)     // Catch:{ all -> 0x009f }
        L_0x0091:
            monitor-exit(r0)
            return r2
        L_0x0093:
            r5 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r6 = new com.google.android.gms.maps.model.RuntimeRemoteException     // Catch:{ all -> 0x009f }
            r6.<init>(r5)     // Catch:{ all -> 0x009f }
            throw r6     // Catch:{ all -> 0x009f }
        L_0x009a:
            r5 = move-exception
            int r5 = r5.errorCode     // Catch:{ all -> 0x009f }
            monitor-exit(r0)
            return r5
        L_0x009f:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
