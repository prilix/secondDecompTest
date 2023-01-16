package androidx.databinding;

import androidx.databinding.ViewDataBindingKtx;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo36738d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo36739k = 3, mo36740mv = {1, 4, 2})
@DebugMetadata(mo37450c = "androidx.databinding.ViewDataBindingKtx$StateFlowListener$startCollection$1", mo37451f = "ViewDataBindingKtx.kt", mo37452i = {}, mo37453l = {116}, mo37454m = "invokeSuspend", mo37455n = {}, mo37456s = {})
/* compiled from: ViewDataBindingKtx.kt */
final class ViewDataBindingKtx$StateFlowListener$startCollection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $flow;
    int label;
    final /* synthetic */ ViewDataBindingKtx.StateFlowListener this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ViewDataBindingKtx$StateFlowListener$startCollection$1(ViewDataBindingKtx.StateFlowListener stateFlowListener, Flow flow, Continuation continuation) {
        super(2, continuation);
        this.this$0 = stateFlowListener;
        this.$flow = flow;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new ViewDataBindingKtx$StateFlowListener$startCollection$1(this.this$0, this.$flow, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ViewDataBindingKtx$StateFlowListener$startCollection$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.$flow.collect(new C0339x9486d1cd(this), this) == coroutine_suspended) {
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
