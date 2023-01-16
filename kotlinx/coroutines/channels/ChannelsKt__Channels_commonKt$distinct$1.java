package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u0001H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo36738d2 = {"<anonymous>", "E", "it", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$distinct$1", mo37451f = "Channels.common.kt", mo37452i = {}, mo37453l = {}, mo37454m = "invokeSuspend", mo37455n = {}, mo37456s = {})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$distinct$1 extends SuspendLambda implements Function2<E, Continuation<? super E>, Object> {
    int label;
    private Object p$0;

    ChannelsKt__Channels_commonKt$distinct$1(Continuation continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$distinct$1 channelsKt__Channels_commonKt$distinct$1 = new ChannelsKt__Channels_commonKt$distinct$1(continuation);
        channelsKt__Channels_commonKt$distinct$1.p$0 = obj;
        return channelsKt__Channels_commonKt$distinct$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$distinct$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.p$0;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
