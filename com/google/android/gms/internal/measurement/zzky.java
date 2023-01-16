package com.google.android.gms.internal.measurement;

import kotlin.UByte;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.5.0 */
final class zzky {
    /* access modifiers changed from: private */
    public static boolean zzd(byte b) {
        return b >= 0;
    }

    /* access modifiers changed from: private */
    public static boolean zze(byte b) {
        return b < -32;
    }

    /* access modifiers changed from: private */
    public static boolean zzf(byte b) {
        return b < -16;
    }

    private static boolean zzg(byte b) {
        return b > -65;
    }

    /* access modifiers changed from: private */
    public static void zzb(byte b, char[] cArr, int i) {
        cArr[i] = (char) b;
    }

    /* access modifiers changed from: private */
    public static void zzb(byte b, byte b2, char[] cArr, int i) throws zzig {
        if (b < -62 || zzg(b2)) {
            throw zzig.zzh();
        }
        cArr[i] = (char) (((b & 31) << 6) | (b2 & Utf8.REPLACEMENT_BYTE));
    }

    /* access modifiers changed from: private */
    public static void zzb(byte b, byte b2, byte b3, char[] cArr, int i) throws zzig {
        if (zzg(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || zzg(b3)))) {
            throw zzig.zzh();
        }
        cArr[i] = (char) (((b & 15) << 12) | ((b2 & Utf8.REPLACEMENT_BYTE) << 6) | (b3 & Utf8.REPLACEMENT_BYTE));
    }

    /* access modifiers changed from: private */
    public static void zzb(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws zzig {
        if (zzg(b2) || (((b << 28) + (b2 + 112)) >> 30) != 0 || zzg(b3) || zzg(b4)) {
            throw zzig.zzh();
        }
        byte b5 = ((b & 7) << 18) | ((b2 & Utf8.REPLACEMENT_BYTE) << 12) | ((b3 & Utf8.REPLACEMENT_BYTE) << 6) | (b4 & Utf8.REPLACEMENT_BYTE);
        cArr[i] = (char) ((b5 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
        cArr[i + 1] = (char) ((b5 & UByte.MAX_VALUE) + Utf8.LOG_SURROGATE_HEADER);
    }
}
