package com.google.android.gms.internal.measurement;

import com.github.mikephil.charting.utils.C1030Utils;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.5.0 */
public enum zzlh {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(C1030Utils.DOUBLE_EPSILON)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzgm.zza),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object zzj;

    private zzlh(Object obj) {
        this.zzj = obj;
    }
}
