package com.google.android.gms.internal.measurement;

import com.jch.racWiFi.C1662R2;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
import org.apache.commons.lang3.CharEncoding;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.5.0 */
public final class zzhx {
    static final Charset zza = Charset.forName("UTF-8");
    public static final byte[] zzb;
    private static final Charset zzc = Charset.forName(CharEncoding.ISO_8859_1);
    private static final ByteBuffer zzd;
    private static final zzgy zze;

    public static int zza(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int zza(boolean z) {
        return z ? C1662R2.attr.trackColorInactive : C1662R2.attr.transformPivotTarget;
    }

    static <T> T zza(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    static <T> T zza(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static boolean zza(byte[] bArr) {
        return zzkw.zza(bArr);
    }

    public static String zzb(byte[] bArr) {
        return new String(bArr, zza);
    }

    public static int zzc(byte[] bArr) {
        int length = bArr.length;
        int zza2 = zza(length, bArr, 0, length);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static boolean zza(zzjg zzjg) {
        if (!(zzjg instanceof zzge)) {
            return false;
        }
        zzge zzge = (zzge) zzjg;
        return false;
    }

    static Object zza(Object obj, Object obj2) {
        return ((zzjg) obj).zzbt().zza((zzjg) obj2).zzx();
    }

    static {
        byte[] bArr = new byte[0];
        zzb = bArr;
        zzd = ByteBuffer.wrap(bArr);
        zze = zzgy.zza(bArr, 0, bArr.length, false);
    }
}
