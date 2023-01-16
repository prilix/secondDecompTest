package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007HH\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001"}, mo36738d2 = {"withPermit", "", "T", "Lkotlinx/coroutines/sync/Semaphore;", "action", "Lkotlin/Function0;", "continuation", "Lkotlin/coroutines/Continuation;"}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.sync.SemaphoreKt", mo37451f = "Semaphore.kt", mo37452i = {0, 0}, mo37453l = {85}, mo37454m = "withPermit", mo37455n = {"$this$withPermit", "action"}, mo37456s = {"L$0", "L$1"})
/* compiled from: Semaphore.kt */
public final class SemaphoreKt$withPermit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    public SemaphoreKt$withPermit$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SemaphoreKt.withPermit((Semaphore) null, (Function0) null, this);
    }
}
