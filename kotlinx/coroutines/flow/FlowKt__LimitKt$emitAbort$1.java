package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H@"}, mo36738d2 = {"emitAbort", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "value", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.flow.FlowKt__LimitKt", mo37451f = "Limit.kt", mo37452i = {0, 0}, mo37453l = {73}, mo37454m = "emitAbort$FlowKt__LimitKt", mo37455n = {"$this$emitAbort", "value"}, mo37456s = {"L$0", "L$1"})
/* compiled from: Limit.kt */
final class FlowKt__LimitKt$emitAbort$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    FlowKt__LimitKt$emitAbort$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt__LimitKt.emitAbort$FlowKt__LimitKt((FlowCollector) null, null, this);
    }
}
