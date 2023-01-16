package com.google.android.gms.internal.measurement;

import java.lang.reflect.Type;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.5.0 */
public enum zzhp {
    DOUBLE(0, zzhr.SCALAR, zzii.DOUBLE),
    FLOAT(1, zzhr.SCALAR, zzii.FLOAT),
    INT64(2, zzhr.SCALAR, zzii.LONG),
    UINT64(3, zzhr.SCALAR, zzii.LONG),
    INT32(4, zzhr.SCALAR, zzii.INT),
    FIXED64(5, zzhr.SCALAR, zzii.LONG),
    FIXED32(6, zzhr.SCALAR, zzii.INT),
    BOOL(7, zzhr.SCALAR, zzii.BOOLEAN),
    STRING(8, zzhr.SCALAR, zzii.STRING),
    MESSAGE(9, zzhr.SCALAR, zzii.MESSAGE),
    BYTES(10, zzhr.SCALAR, zzii.BYTE_STRING),
    UINT32(11, zzhr.SCALAR, zzii.INT),
    ENUM(12, zzhr.SCALAR, zzii.ENUM),
    SFIXED32(13, zzhr.SCALAR, zzii.INT),
    SFIXED64(14, zzhr.SCALAR, zzii.LONG),
    SINT32(15, zzhr.SCALAR, zzii.INT),
    SINT64(16, zzhr.SCALAR, zzii.LONG),
    GROUP(17, zzhr.SCALAR, zzii.MESSAGE),
    DOUBLE_LIST(18, zzhr.VECTOR, zzii.DOUBLE),
    FLOAT_LIST(19, zzhr.VECTOR, zzii.FLOAT),
    INT64_LIST(20, zzhr.VECTOR, zzii.LONG),
    UINT64_LIST(21, zzhr.VECTOR, zzii.LONG),
    INT32_LIST(22, zzhr.VECTOR, zzii.INT),
    FIXED64_LIST(23, zzhr.VECTOR, zzii.LONG),
    FIXED32_LIST(24, zzhr.VECTOR, zzii.INT),
    BOOL_LIST(25, zzhr.VECTOR, zzii.BOOLEAN),
    STRING_LIST(26, zzhr.VECTOR, zzii.STRING),
    MESSAGE_LIST(27, zzhr.VECTOR, zzii.MESSAGE),
    BYTES_LIST(28, zzhr.VECTOR, zzii.BYTE_STRING),
    UINT32_LIST(29, zzhr.VECTOR, zzii.INT),
    ENUM_LIST(30, zzhr.VECTOR, zzii.ENUM),
    SFIXED32_LIST(31, zzhr.VECTOR, zzii.INT),
    SFIXED64_LIST(32, zzhr.VECTOR, zzii.LONG),
    SINT32_LIST(33, zzhr.VECTOR, zzii.INT),
    SINT64_LIST(34, zzhr.VECTOR, zzii.LONG),
    DOUBLE_LIST_PACKED(35, zzhr.PACKED_VECTOR, zzii.DOUBLE),
    FLOAT_LIST_PACKED(36, zzhr.PACKED_VECTOR, zzii.FLOAT),
    INT64_LIST_PACKED(37, zzhr.PACKED_VECTOR, zzii.LONG),
    UINT64_LIST_PACKED(38, zzhr.PACKED_VECTOR, zzii.LONG),
    INT32_LIST_PACKED(39, zzhr.PACKED_VECTOR, zzii.INT),
    FIXED64_LIST_PACKED(40, zzhr.PACKED_VECTOR, zzii.LONG),
    FIXED32_LIST_PACKED(41, zzhr.PACKED_VECTOR, zzii.INT),
    BOOL_LIST_PACKED(42, zzhr.PACKED_VECTOR, zzii.BOOLEAN),
    UINT32_LIST_PACKED(43, zzhr.PACKED_VECTOR, zzii.INT),
    ENUM_LIST_PACKED(44, zzhr.PACKED_VECTOR, zzii.ENUM),
    SFIXED32_LIST_PACKED(45, zzhr.PACKED_VECTOR, zzii.INT),
    SFIXED64_LIST_PACKED(46, zzhr.PACKED_VECTOR, zzii.LONG),
    SINT32_LIST_PACKED(47, zzhr.PACKED_VECTOR, zzii.INT),
    SINT64_LIST_PACKED(48, zzhr.PACKED_VECTOR, zzii.LONG),
    GROUP_LIST(49, zzhr.VECTOR, zzii.MESSAGE),
    MAP(50, zzhr.MAP, zzii.VOID);
    
    private static final zzhp[] zzbe = null;
    private static final Type[] zzbf = null;
    private final zzii zzaz;
    private final int zzba;
    private final zzhr zzbb;
    private final Class<?> zzbc;
    private final boolean zzbd;

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        r5 = com.google.android.gms.internal.measurement.zzhs.zzb[r6.ordinal()];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzhp(int r4, com.google.android.gms.internal.measurement.zzhr r5, com.google.android.gms.internal.measurement.zzii r6) {
        /*
            r1 = this;
            r1.<init>(r2, r3)
            r1.zzba = r4
            r1.zzbb = r5
            r1.zzaz = r6
            int[] r2 = com.google.android.gms.internal.measurement.zzhs.zza
            int r3 = r5.ordinal()
            r2 = r2[r3]
            r3 = 2
            r4 = 1
            if (r2 == r4) goto L_0x0022
            if (r2 == r3) goto L_0x001b
            r2 = 0
            r1.zzbc = r2
            goto L_0x0028
        L_0x001b:
            java.lang.Class r2 = r6.zza()
            r1.zzbc = r2
            goto L_0x0028
        L_0x0022:
            java.lang.Class r2 = r6.zza()
            r1.zzbc = r2
        L_0x0028:
            r2 = 0
            com.google.android.gms.internal.measurement.zzhr r0 = com.google.android.gms.internal.measurement.zzhr.SCALAR
            if (r5 != r0) goto L_0x003d
            int[] r5 = com.google.android.gms.internal.measurement.zzhs.zzb
            int r6 = r6.ordinal()
            r5 = r5[r6]
            if (r5 == r4) goto L_0x003d
            if (r5 == r3) goto L_0x003d
            r3 = 3
            if (r5 == r3) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r4 = 0
        L_0x003e:
            r1.zzbd = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhp.<init>(java.lang.String, int, int, com.google.android.gms.internal.measurement.zzhr, com.google.android.gms.internal.measurement.zzii):void");
    }

    public final int zza() {
        return this.zzba;
    }

    static {
        int i;
        zzbf = new Type[0];
        zzhp[] values = values();
        zzbe = new zzhp[values.length];
        for (zzhp zzhp : values) {
            zzbe[zzhp.zzba] = zzhp;
        }
    }
}
