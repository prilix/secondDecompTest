package okhttp3.internal.connection;

import kotlin.Metadata;
import okhttp3.internal.p018ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo36738d2 = {"okhttp3/internal/connection/RealConnection$newWebSocketStreams$1", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "close", "", "okhttp"}, mo36739k = 1, mo36740mv = {1, 1, 15})
/* compiled from: RealConnection.kt */
public final class RealConnection$newWebSocketStreams$1 extends RealWebSocket.Streams {
    final /* synthetic */ Exchange $exchange;
    final /* synthetic */ BufferedSink $sink;
    final /* synthetic */ BufferedSource $source;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RealConnection$newWebSocketStreams$1(Exchange exchange, BufferedSource bufferedSource, BufferedSink bufferedSink, boolean z, BufferedSource bufferedSource2, BufferedSink bufferedSink2) {
        super(z, bufferedSource2, bufferedSink2);
        this.$exchange = exchange;
        this.$source = bufferedSource;
        this.$sink = bufferedSink;
    }

    public void close() {
        this.$exchange.bodyComplete(-1, true, true, null);
    }
}
