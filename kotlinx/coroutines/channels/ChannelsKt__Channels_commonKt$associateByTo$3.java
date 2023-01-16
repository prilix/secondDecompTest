package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004\"\u0018\b\u0003\u0010\u0005*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0003\u0012\u0006\b\u0000\u0012\u0002H\u00040\u0006*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00050\rHH"}, mo36738d2 = {"associateByTo", "", "E", "K", "V", "M", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "destination", "keySelector", "Lkotlin/Function1;", "valueTransform", "continuation", "Lkotlin/coroutines/Continuation;"}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", mo37451f = "Channels.common.kt", mo37452i = {0, 0, 0, 0, 0, 0, 0, 0}, mo37453l = {2572}, mo37454m = "associateByTo", mo37455n = {"$this$associateByTo", "destination", "keySelector", "valueTransform", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv"}, mo37456s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
/* compiled from: Channels.common.kt */
public final class ChannelsKt__Channels_commonKt$associateByTo$3 extends ContinuationImpl {
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

    public ChannelsKt__Channels_commonKt$associateByTo$3(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.associateByTo((ReceiveChannel) null, null, (Function1) null, (Function1) null, this);
    }
}
