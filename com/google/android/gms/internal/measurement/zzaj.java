package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzag;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.5.0 */
final class zzaj extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Context zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ zzag zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaj(zzag zzag, String str, String str2, Context context, Bundle bundle) {
        super(zzag);
        this.zzg = zzag;
        this.zzc = str;
        this.zzd = str2;
        this.zze = context;
        this.zzf = bundle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0052 A[Catch:{ Exception -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005e A[Catch:{ Exception -> 0x009e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza() {
        /*
            r14 = this;
            r0 = 0
            r1 = 1
            com.google.android.gms.internal.measurement.zzag r2 = r14.zzg     // Catch:{ Exception -> 0x009e }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x009e }
            r3.<init>()     // Catch:{ Exception -> 0x009e }
            java.util.List unused = r2.zzf = r3     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.internal.measurement.zzag r2 = r14.zzg     // Catch:{ Exception -> 0x009e }
            java.lang.String r3 = r14.zzc     // Catch:{ Exception -> 0x009e }
            java.lang.String r4 = r14.zzd     // Catch:{ Exception -> 0x009e }
            boolean r2 = com.google.android.gms.internal.measurement.zzag.zzc(r3, r4)     // Catch:{ Exception -> 0x009e }
            r3 = 0
            if (r2 == 0) goto L_0x0027
            java.lang.String r3 = r14.zzd     // Catch:{ Exception -> 0x009e }
            java.lang.String r2 = r14.zzc     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.internal.measurement.zzag r4 = r14.zzg     // Catch:{ Exception -> 0x009e }
            java.lang.String r4 = r4.zzc     // Catch:{ Exception -> 0x009e }
            r10 = r2
            r11 = r3
            r9 = r4
            goto L_0x002a
        L_0x0027:
            r9 = r3
            r10 = r9
            r11 = r10
        L_0x002a:
            android.content.Context r2 = r14.zze     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.internal.measurement.zzag.zzh(r2)     // Catch:{ Exception -> 0x009e }
            java.lang.Boolean r2 = com.google.android.gms.internal.measurement.zzag.zzh     // Catch:{ Exception -> 0x009e }
            boolean r2 = r2.booleanValue()     // Catch:{ Exception -> 0x009e }
            if (r2 != 0) goto L_0x003e
            if (r10 == 0) goto L_0x003c
            goto L_0x003e
        L_0x003c:
            r2 = 0
            goto L_0x003f
        L_0x003e:
            r2 = 1
        L_0x003f:
            com.google.android.gms.internal.measurement.zzag r3 = r14.zzg     // Catch:{ Exception -> 0x009e }
            android.content.Context r4 = r14.zze     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.internal.measurement.zzv r4 = r3.zza((android.content.Context) r4, (boolean) r2)     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.internal.measurement.zzv unused = r3.zzm = r4     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.internal.measurement.zzag r3 = r14.zzg     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.internal.measurement.zzv r3 = r3.zzm     // Catch:{ Exception -> 0x009e }
            if (r3 != 0) goto L_0x005e
            com.google.android.gms.internal.measurement.zzag r2 = r14.zzg     // Catch:{ Exception -> 0x009e }
            java.lang.String r2 = r2.zzc     // Catch:{ Exception -> 0x009e }
            java.lang.String r3 = "Failed to connect to measurement client."
            android.util.Log.w(r2, r3)     // Catch:{ Exception -> 0x009e }
            return
        L_0x005e:
            android.content.Context r3 = r14.zze     // Catch:{ Exception -> 0x009e }
            int r3 = com.google.android.gms.internal.measurement.zzag.zzg(r3)     // Catch:{ Exception -> 0x009e }
            android.content.Context r4 = r14.zze     // Catch:{ Exception -> 0x009e }
            int r4 = com.google.android.gms.internal.measurement.zzag.zzf(r4)     // Catch:{ Exception -> 0x009e }
            if (r2 == 0) goto L_0x0077
            int r2 = java.lang.Math.max(r3, r4)     // Catch:{ Exception -> 0x009e }
            if (r4 >= r3) goto L_0x0074
            r3 = 1
            goto L_0x0075
        L_0x0074:
            r3 = 0
        L_0x0075:
            r8 = r3
            goto L_0x0081
        L_0x0077:
            if (r3 <= 0) goto L_0x007a
            r4 = r3
        L_0x007a:
            if (r3 <= 0) goto L_0x007e
            r2 = 1
            goto L_0x007f
        L_0x007e:
            r2 = 0
        L_0x007f:
            r8 = r2
            r2 = r4
        L_0x0081:
            com.google.android.gms.internal.measurement.zzae r13 = new com.google.android.gms.internal.measurement.zzae     // Catch:{ Exception -> 0x009e }
            r4 = 31049(0x7949, double:1.534E-319)
            long r6 = (long) r2     // Catch:{ Exception -> 0x009e }
            android.os.Bundle r12 = r14.zzf     // Catch:{ Exception -> 0x009e }
            r3 = r13
            r3.<init>(r4, r6, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.internal.measurement.zzag r2 = r14.zzg     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.internal.measurement.zzv r2 = r2.zzm     // Catch:{ Exception -> 0x009e }
            android.content.Context r3 = r14.zze     // Catch:{ Exception -> 0x009e }
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r3)     // Catch:{ Exception -> 0x009e }
            long r4 = r14.zza     // Catch:{ Exception -> 0x009e }
            r2.initialize(r3, r13, r4)     // Catch:{ Exception -> 0x009e }
            return
        L_0x009e:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzag r3 = r14.zzg
            r3.zza((java.lang.Exception) r2, (boolean) r1, (boolean) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaj.zza():void");
    }
}
