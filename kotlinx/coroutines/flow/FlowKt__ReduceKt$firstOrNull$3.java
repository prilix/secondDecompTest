package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0006H@"}, mo36738d2 = {"firstOrNull", "", "T", "Lkotlinx/coroutines/flow/Flow;", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "continuation"}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", mo37451f = "Reduce.kt", mo37452i = {0, 0, 0, 0, 0}, mo37453l = {227}, mo37454m = "firstOrNull", mo37455n = {"$this$firstOrNull", "predicate", "result", "$this$collectWhile$iv", "collector$iv"}, mo37456s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: Reduce.kt */
final class FlowKt__ReduceKt$firstOrNull$3 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    FlowKt__ReduceKt$firstOrNull$3(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.firstOrNull((Flow) null, (Function2) null, this);
    }
}
