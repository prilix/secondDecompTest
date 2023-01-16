package kotlinx.coroutines.channels;

import kotlin.Metadata;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, mo36738d2 = {"Lkotlinx/coroutines/channels/ClosedSendChannelException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "message", "", "(Ljava/lang/String;)V", "kotlinx-coroutines-core"}, mo36739k = 1, mo36740mv = {1, 4, 0})
/* compiled from: Channel.kt */
public final class ClosedSendChannelException extends IllegalStateException {
    public ClosedSendChannelException(String str) {
        super(str);
    }
}
