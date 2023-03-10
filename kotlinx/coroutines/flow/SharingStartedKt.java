package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.time.Duration;
import kotlinx.coroutines.flow.SharingStarted;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, mo36738d2 = {"WhileSubscribed", "Lkotlinx/coroutines/flow/SharingStarted;", "Lkotlinx/coroutines/flow/SharingStarted$Companion;", "stopTimeout", "Lkotlin/time/Duration;", "replayExpiration", "WhileSubscribed-9tZugJw", "(Lkotlinx/coroutines/flow/SharingStarted$Companion;DD)Lkotlinx/coroutines/flow/SharingStarted;", "kotlinx-coroutines-core"}, mo36739k = 2, mo36740mv = {1, 4, 0})
/* compiled from: SharingStarted.kt */
public final class SharingStartedKt {
    /* renamed from: WhileSubscribed-9tZugJw$default  reason: not valid java name */
    public static /* synthetic */ SharingStarted m3000WhileSubscribed9tZugJw$default(SharingStarted.Companion companion, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = Duration.Companion.m2936getZEROUwyO8pc();
        }
        if ((i & 2) != 0) {
            d2 = Duration.Companion.m2934getINFINITEUwyO8pc();
        }
        return m2999WhileSubscribed9tZugJw(companion, d, d2);
    }

    /* renamed from: WhileSubscribed-9tZugJw  reason: not valid java name */
    public static final SharingStarted m2999WhileSubscribed9tZugJw(SharingStarted.Companion companion, double d, double d2) {
        return new StartedWhileSubscribed(Duration.toLongMilliseconds-impl(d), Duration.toLongMilliseconds-impl(d2));
    }
}
