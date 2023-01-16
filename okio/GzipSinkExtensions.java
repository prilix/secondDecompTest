package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36736bv = {1, 0, 2}, mo36737d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b¨\u0006\u0003"}, mo36738d2 = {"gzip", "Lokio/GzipSink;", "Lokio/Sink;", "jvm"}, mo36739k = 2, mo36740mv = {1, 1, 11})
/* renamed from: okio.-GzipSinkExtensions  reason: invalid class name */
/* compiled from: GzipSink.kt */
public final class GzipSinkExtensions {
    public static final GzipSink gzip(Sink sink) {
        Intrinsics.checkParameterIsNotNull(sink, "$receiver");
        return new GzipSink(sink);
    }
}
