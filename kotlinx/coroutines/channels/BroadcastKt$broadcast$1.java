package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, mo36738d2 = {"<anonymous>", "", "E", "it", "", "invoke"}, mo36739k = 3, mo36740mv = {1, 4, 0})
/* compiled from: Broadcast.kt */
final class BroadcastKt$broadcast$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ ReceiveChannel $this_broadcast;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BroadcastKt$broadcast$1(ReceiveChannel receiveChannel) {
        super(1);
        this.$this_broadcast = receiveChannel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        ChannelsKt.cancelConsumed(this.$this_broadcast, th);
    }
}
