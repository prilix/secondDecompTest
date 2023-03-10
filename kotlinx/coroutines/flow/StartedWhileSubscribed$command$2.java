package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo36738d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/flow/SharingCommand;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.flow.StartedWhileSubscribed$command$2", mo37451f = "SharingStarted.kt", mo37452i = {}, mo37453l = {}, mo37454m = "invokeSuspend", mo37455n = {}, mo37456s = {})
/* compiled from: SharingStarted.kt */
final class StartedWhileSubscribed$command$2 extends SuspendLambda implements Function2<SharingCommand, Continuation<? super Boolean>, Object> {
    int label;
    private SharingCommand p$0;

    StartedWhileSubscribed$command$2(Continuation continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StartedWhileSubscribed$command$2 startedWhileSubscribed$command$2 = new StartedWhileSubscribed$command$2(continuation);
        startedWhileSubscribed$command$2.p$0 = (SharingCommand) obj;
        return startedWhileSubscribed$command$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((StartedWhileSubscribed$command$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(this.p$0 != SharingCommand.START);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
