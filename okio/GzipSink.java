package okio;

import com.jch.racWiFi.C1662R2;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36736bv = {1, 0, 2}, mo36737d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u00020\t8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, mo36738d2 = {"Lokio/GzipSink;", "Lokio/Sink;", "sink", "(Lokio/Sink;)V", "closed", "", "crc", "Ljava/util/zip/CRC32;", "deflater", "Ljava/util/zip/Deflater;", "()Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "Lokio/RealBufferedSink;", "close", "", "-deprecated_deflater", "flush", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "Lokio/Buffer;", "byteCount", "", "write", "source", "writeFooter", "jvm"}, mo36739k = 1, mo36740mv = {1, 1, 11})
/* compiled from: GzipSink.kt */
public final class GzipSink implements Sink {
    private boolean closed;
    private final CRC32 crc = new CRC32();
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final RealBufferedSink sink;

    public GzipSink(Sink sink2) {
        Intrinsics.checkParameterIsNotNull(sink2, "sink");
        RealBufferedSink realBufferedSink = new RealBufferedSink(sink2);
        this.sink = realBufferedSink;
        Deflater deflater2 = new Deflater(-1, true);
        this.deflater = deflater2;
        this.deflaterSink = new DeflaterSink((BufferedSink) realBufferedSink, deflater2);
        Buffer buffer = realBufferedSink.bufferField;
        buffer.writeShort((int) C1662R2.string.country_rwanda_code);
        buffer.writeByte(8);
        buffer.writeByte(0);
        buffer.writeInt(0);
        buffer.writeByte(0);
        buffer.writeByte(0);
    }

    public final Deflater deflater() {
        return this.deflater;
    }

    public void write(Buffer buffer, long j) throws IOException {
        Intrinsics.checkParameterIsNotNull(buffer, "source");
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        } else if (i != 0) {
            updateCrc(buffer, j);
            this.deflaterSink.write(buffer, j);
        }
    }

    public void flush() throws IOException {
        this.deflaterSink.flush();
    }

    public Timeout timeout() {
        return this.sink.timeout();
    }

    public void close() throws IOException {
        if (!this.closed) {
            Throwable th = null;
            Throwable th2 = null;
            try {
                this.deflaterSink.finishDeflate$jvm();
                writeFooter();
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                this.deflater.end();
            } catch (Throwable th4) {
                if (th == null) {
                    th = th4;
                }
            }
            try {
                this.sink.close();
            } catch (Throwable th5) {
                if (th == null) {
                    th = th5;
                }
            }
            this.closed = true;
            if (th != null) {
                throw th;
            }
        }
    }

    private final void writeFooter() {
        this.sink.writeIntLe((int) this.crc.getValue());
        this.sink.writeIntLe((int) this.deflater.getBytesRead());
    }

    private final void updateCrc(Buffer buffer, long j) {
        Segment segment = buffer.head;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        while (j > 0) {
            int min = (int) Math.min(j, (long) (segment.limit - segment.pos));
            this.crc.update(segment.data, segment.pos, min);
            j -= (long) min;
            segment = segment.next;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "deflater", imports = {}))
    /* renamed from: -deprecated_deflater  reason: not valid java name */
    public final Deflater m3169deprecated_deflater() {
        return this.deflater;
    }
}
