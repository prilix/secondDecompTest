package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo36738d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3", mo37451f = "Merge.kt", mo37452i = {0, 0, 0}, mo37453l = {101}, mo37454m = "invokeSuspend", mo37455n = {"$this$flowScope", "previousFlow", "$this$collect$iv"}, mo37456s = {"L$0", "L$1", "L$2"})
/* compiled from: Merge.kt */
final class ChannelFlowTransformLatest$flowCollect$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $collector;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f696p$;
    final /* synthetic */ ChannelFlowTransformLatest this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelFlowTransformLatest$flowCollect$3(ChannelFlowTransformLatest channelFlowTransformLatest, FlowCollector flowCollector, Continuation continuation) {
        super(2, continuation);
        this.this$0 = channelFlowTransformLatest;
        this.$collector = flowCollector;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelFlowTransformLatest$flowCollect$3 channelFlowTransformLatest$flowCollect$3 = new ChannelFlowTransformLatest$flowCollect$3(this.this$0, this.$collector, continuation);
        channelFlowTransformLatest$flowCollect$3.f696p$ = (CoroutineScope) obj;
        return channelFlowTransformLatest$flowCollect$3;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelFlowTransformLatest$flowCollect$3) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f696p$;
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Job job = null;
            objectRef.element = null;
            Flow flow = this.this$0.flow;
            this.L$0 = coroutineScope;
            this.L$1 = objectRef;
            this.L$2 = flow;
            this.label = 1;
            if (flow.collect(new C2938xfa64eeb5(this, coroutineScope, objectRef), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            Flow flow2 = (Flow) this.L$2;
            Ref.ObjectRef objectRef2 = (Ref.ObjectRef) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
