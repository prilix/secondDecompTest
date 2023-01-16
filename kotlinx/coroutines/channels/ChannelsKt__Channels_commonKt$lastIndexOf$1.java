package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H@"}, mo36738d2 = {"lastIndexOf", "", "E", "Lkotlinx/coroutines/channels/ReceiveChannel;", "element", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", mo37451f = "Channels.common.kt", mo37452i = {0, 0, 0, 0, 0, 0, 0, 0}, mo37453l = {2366}, mo37454m = "lastIndexOf", mo37455n = {"$this$lastIndexOf", "element", "lastIndex", "index", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv"}, mo37456s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$lastIndexOf$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__Channels_commonKt$lastIndexOf$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.lastIndexOf((ReceiveChannel) null, null, this);
    }
}
