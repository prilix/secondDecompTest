package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DelayKt;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo36738d2 = {"<anonymous>", "", "T", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.flow.FlowKt__MigrationKt$delayEach$1", mo37451f = "Migration.kt", mo37452i = {0}, mo37453l = {423}, mo37454m = "invokeSuspend", mo37455n = {"it"}, mo37456s = {"L$0"})
/* compiled from: Migration.kt */
final class FlowKt__MigrationKt$delayEach$1 extends SuspendLambda implements Function2<T, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $timeMillis;
    Object L$0;
    int label;
    private Object p$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__MigrationKt$delayEach$1(long j, Continuation continuation) {
        super(2, continuation);
        this.$timeMillis = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowKt__MigrationKt$delayEach$1 flowKt__MigrationKt$delayEach$1 = new FlowKt__MigrationKt$delayEach$1(this.$timeMillis, continuation);
        flowKt__MigrationKt$delayEach$1.p$0 = obj;
        return flowKt__MigrationKt$delayEach$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__MigrationKt$delayEach$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.p$0;
            long j = this.$timeMillis;
            this.L$0 = obj2;
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
