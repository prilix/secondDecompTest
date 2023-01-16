package com.google.android.gms.internal.firebase_messaging;

import com.jch.racWiFi.C1662R2;
import kotlin.time.DurationKt;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
public final class zzl {
    private static final byte[] zza = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
    private static final int[] zzb = {1, 10, 100, 1000, 10000, 100000, DurationKt.NANOS_IN_MILLIS, 10000000, 100000000, 1000000000};
    private static final int[] zzc = {3, 31, 316, C1662R2.C1664drawable.flag_slovakia, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    private static final int[] zzd = {1, 1, 2, 6, 24, 120, C1662R2.attr.layout_constraintWidth_percent, C1662R2.C1665id.layout_now, 40320, 362880, 3628800, 39916800, 479001600};
    private static int[] zze = {Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, C1662R2.dimen.m3_btn_stroke_size, C1662R2.attr.errorIconDrawable, C1662R2.attr.buttonCompat, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

    public static int zza(int i, int i2) {
        long j = ((long) i) << 1;
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }
}
