package retrofit2;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00060\u0002j\u0002`\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@"}, mo36738d2 = {"suspendAndThrow", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo36739k = 3, mo36740mv = {1, 1, 15})
@DebugMetadata(mo37450c = "retrofit2.KotlinExtensions", mo37451f = "KotlinExtensions.kt", mo37452i = {0}, mo37453l = {113}, mo37454m = "suspendAndThrow", mo37455n = {"$this$suspendAndThrow"}, mo37456s = {"L$0"})
/* compiled from: KotlinExtensions.kt */
final class KotlinExtensions$suspendAndThrow$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    KotlinExtensions$suspendAndThrow$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return KotlinExtensions.suspendAndThrow((Exception) null, this);
    }
}
