package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00042<\u0010\u0005\u001a8\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\rHH"}, mo36738d2 = {"reduceIndexed", "", "S", "E", "Lkotlinx/coroutines/channels/ReceiveChannel;", "operation", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "index", "acc", "continuation", "Lkotlin/coroutines/Continuation;"}, mo36739k = 3, mo36740mv = {1, 4, 0})
@DebugMetadata(mo37450c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", mo37451f = "Channels.common.kt", mo37452i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, mo37453l = {2057, 2060}, mo37454m = "reduceIndexed", mo37455n = {"$this$reduceIndexed", "operation", "$this$consume$iv", "cause$iv", "$this$consume", "iterator", "$this$reduceIndexed", "operation", "$this$consume$iv", "cause$iv", "$this$consume", "iterator", "index", "accumulator"}, mo37456s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$6"})
/* compiled from: Channels.common.kt */
public final class ChannelsKt__Channels_commonKt$reduceIndexed$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;

    public ChannelsKt__Channels_commonKt$reduceIndexed$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.reduceIndexed((ReceiveChannel) null, (Function3) null, this);
    }
}
