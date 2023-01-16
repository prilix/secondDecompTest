package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"kotlinx/coroutines/internal/SystemPropsKt__SystemPropsKt", "kotlinx/coroutines/internal/SystemPropsKt__SystemProps_commonKt"}, mo36739k = 4, mo36740mv = {1, 4, 0})
public final class SystemPropsKt {
    public static final int getAVAILABLE_PROCESSORS() {
        return SystemPropsKt__SystemPropsKt.getAVAILABLE_PROCESSORS();
    }

    public static final int systemProp(String str, int i, int i2, int i3) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(str, i, i2, i3);
    }

    public static final long systemProp(String str, long j, long j2, long j3) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(str, j, j2, j3);
    }

    public static final String systemProp(String str) {
        return SystemPropsKt__SystemPropsKt.systemProp(str);
    }

    public static final boolean systemProp(String str, boolean z) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(str, z);
    }
}
