package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36736bv = {1, 0, 2}, mo36737d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo36738d2 = {"okio/Pipe$sink$1", "Lokio/Sink;", "timeout", "Lokio/Timeout;", "close", "", "flush", "write", "source", "Lokio/Buffer;", "byteCount", "", "jvm"}, mo36739k = 1, mo36740mv = {1, 1, 11})
/* compiled from: Pipe.kt */
public final class Pipe$sink$1 implements Sink {
    final /* synthetic */ Pipe this$0;
    private final Timeout timeout = new Timeout();

    Pipe$sink$1(Pipe pipe) {
        this.this$0 = pipe;
    }

    public void write(Buffer buffer, long j) {
        Intrinsics.checkParameterIsNotNull(buffer, "source");
        Sink sink = null;
        Sink sink2 = null;
        synchronized (this.this$0.getBuffer$jvm()) {
            if (!this.this$0.getSinkClosed$jvm()) {
                while (true) {
                    if (j <= 0) {
                        break;
                    }
                    Sink foldedSink$jvm = this.this$0.getFoldedSink$jvm();
                    if (foldedSink$jvm != null) {
                        sink = foldedSink$jvm;
                        break;
                    } else if (!this.this$0.getSourceClosed$jvm()) {
                        long maxBufferSize$jvm = this.this$0.getMaxBufferSize$jvm() - this.this$0.getBuffer$jvm().size();
                        if (maxBufferSize$jvm == 0) {
                            this.timeout.waitUntilNotified(this.this$0.getBuffer$jvm());
                        } else {
                            long min = Math.min(maxBufferSize$jvm, j);
                            this.this$0.getBuffer$jvm().write(buffer, min);
                            j -= min;
                            Buffer buffer$jvm = this.this$0.getBuffer$jvm();
                            if (buffer$jvm != null) {
                                buffer$jvm.notifyAll();
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                            }
                        }
                    } else {
                        throw new IOException("source is closed");
                    }
                }
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("closed".toString());
            }
        }
        if (sink != null) {
            Pipe pipe = this.this$0;
            Timeout timeout2 = sink.timeout();
            Timeout timeout3 = pipe.sink().timeout();
            long timeoutNanos = timeout2.timeoutNanos();
            timeout2.timeout(Timeout.Companion.minTimeout(timeout3.timeoutNanos(), timeout2.timeoutNanos()), TimeUnit.NANOSECONDS);
            if (timeout2.hasDeadline()) {
                long deadlineNanoTime = timeout2.deadlineNanoTime();
                if (timeout3.hasDeadline()) {
                    timeout2.deadlineNanoTime(Math.min(timeout2.deadlineNanoTime(), timeout3.deadlineNanoTime()));
                }
                try {
                    sink.write(buffer, j);
                } finally {
                    timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                    if (timeout3.hasDeadline()) {
                        timeout2.deadlineNanoTime(deadlineNanoTime);
                    }
                }
            } else {
                if (timeout3.hasDeadline()) {
                    timeout2.deadlineNanoTime(timeout3.deadlineNanoTime());
                }
                try {
                    sink.write(buffer, j);
                } finally {
                    timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                    if (timeout3.hasDeadline()) {
                        timeout2.clearDeadline();
                    }
                }
            }
        }
    }

    public void flush() {
        Sink sink = null;
        Sink sink2 = null;
        synchronized (this.this$0.getBuffer$jvm()) {
            if (!this.this$0.getSinkClosed$jvm()) {
                Sink foldedSink$jvm = this.this$0.getFoldedSink$jvm();
                if (foldedSink$jvm != null) {
                    sink = foldedSink$jvm;
                } else if (this.this$0.getSourceClosed$jvm()) {
                    if (this.this$0.getBuffer$jvm().size() > 0) {
                        throw new IOException("source is closed");
                    }
                }
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("closed".toString());
            }
        }
        if (sink != null) {
            Pipe pipe = this.this$0;
            Timeout timeout2 = sink.timeout();
            Timeout timeout3 = pipe.sink().timeout();
            long timeoutNanos = timeout2.timeoutNanos();
            timeout2.timeout(Timeout.Companion.minTimeout(timeout3.timeoutNanos(), timeout2.timeoutNanos()), TimeUnit.NANOSECONDS);
            if (timeout2.hasDeadline()) {
                long deadlineNanoTime = timeout2.deadlineNanoTime();
                if (timeout3.hasDeadline()) {
                    timeout2.deadlineNanoTime(Math.min(timeout2.deadlineNanoTime(), timeout3.deadlineNanoTime()));
                }
                try {
                    sink.flush();
                } finally {
                    timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                    if (timeout3.hasDeadline()) {
                        timeout2.deadlineNanoTime(deadlineNanoTime);
                    }
                }
            } else {
                if (timeout3.hasDeadline()) {
                    timeout2.deadlineNanoTime(timeout3.deadlineNanoTime());
                }
                try {
                    sink.flush();
                } finally {
                    timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                    if (timeout3.hasDeadline()) {
                        timeout2.clearDeadline();
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0059, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005b, code lost:
        r1 = r11.this$0;
        r2 = r0.timeout();
        r1 = r1.sink().timeout();
        r3 = r2.timeoutNanos();
        r2.timeout(okio.Timeout.Companion.minTimeout(r1.timeoutNanos(), r2.timeoutNanos()), java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0084, code lost:
        if (r2.hasDeadline() == false) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0086, code lost:
        r5 = r2.deadlineNanoTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008e, code lost:
        if (r1.hasDeadline() == false) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0090, code lost:
        r2.deadlineNanoTime(java.lang.Math.min(r2.deadlineNanoTime(), r1.deadlineNanoTime()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b2, code lost:
        r2.timeout(r3, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00bb, code lost:
        if (r1.hasDeadline() != false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00bd, code lost:
        r2.deadlineNanoTime(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c0, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c5, code lost:
        if (r1.hasDeadline() == false) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c7, code lost:
        r2.deadlineNanoTime(r1.deadlineNanoTime());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e1, code lost:
        r2.timeout(r3, java.util.concurrent.TimeUnit.NANOSECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ea, code lost:
        if (r1.hasDeadline() != false) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ec, code lost:
        r2.clearDeadline();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ef, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r11 = this;
            r0 = 0
            r1 = r0
            okio.Sink r1 = (okio.Sink) r1
            okio.Pipe r1 = r11.this$0
            okio.Buffer r1 = r1.getBuffer$jvm()
            monitor-enter(r1)
            okio.Pipe r2 = r11.this$0     // Catch:{ all -> 0x00f9 }
            boolean r2 = r2.getSinkClosed$jvm()     // Catch:{ all -> 0x00f9 }
            if (r2 == 0) goto L_0x0015
            monitor-exit(r1)
            return
        L_0x0015:
            okio.Pipe r2 = r11.this$0     // Catch:{ all -> 0x00f9 }
            okio.Sink r2 = r2.getFoldedSink$jvm()     // Catch:{ all -> 0x00f9 }
            if (r2 == 0) goto L_0x001f
            r0 = r2
            goto L_0x0056
        L_0x001f:
            okio.Pipe r2 = r11.this$0     // Catch:{ all -> 0x00f9 }
            boolean r2 = r2.getSourceClosed$jvm()     // Catch:{ all -> 0x00f9 }
            if (r2 == 0) goto L_0x0043
            okio.Pipe r2 = r11.this$0     // Catch:{ all -> 0x00f9 }
            okio.Buffer r2 = r2.getBuffer$jvm()     // Catch:{ all -> 0x00f9 }
            long r2 = r2.size()     // Catch:{ all -> 0x00f9 }
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x0038
            goto L_0x0043
        L_0x0038:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00f9 }
            java.lang.String r2 = "source is closed"
            r0.<init>(r2)     // Catch:{ all -> 0x00f9 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x00f9 }
            throw r0     // Catch:{ all -> 0x00f9 }
        L_0x0043:
            okio.Pipe r2 = r11.this$0     // Catch:{ all -> 0x00f9 }
            r3 = 1
            r2.setSinkClosed$jvm(r3)     // Catch:{ all -> 0x00f9 }
            okio.Pipe r2 = r11.this$0     // Catch:{ all -> 0x00f9 }
            okio.Buffer r2 = r2.getBuffer$jvm()     // Catch:{ all -> 0x00f9 }
            if (r2 == 0) goto L_0x00f1
            java.lang.Object r2 = (java.lang.Object) r2     // Catch:{ all -> 0x00f9 }
            r2.notifyAll()     // Catch:{ all -> 0x00f9 }
        L_0x0056:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00f9 }
            monitor-exit(r1)
            if (r0 == 0) goto L_0x00f0
            okio.Pipe r1 = r11.this$0
            okio.Timeout r2 = r0.timeout()
            okio.Sink r1 = r1.sink()
            okio.Timeout r1 = r1.timeout()
            long r3 = r2.timeoutNanos()
            okio.Timeout$Companion r5 = okio.Timeout.Companion
            long r6 = r1.timeoutNanos()
            long r8 = r2.timeoutNanos()
            long r5 = r5.minTimeout(r6, r8)
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.NANOSECONDS
            r2.timeout(r5, r7)
            boolean r5 = r2.hasDeadline()
            if (r5 == 0) goto L_0x00c1
            long r5 = r2.deadlineNanoTime()
            boolean r7 = r1.hasDeadline()
            if (r7 == 0) goto L_0x009f
            long r7 = r2.deadlineNanoTime()
            long r9 = r1.deadlineNanoTime()
            long r7 = java.lang.Math.min(r7, r9)
            r2.deadlineNanoTime(r7)
        L_0x009f:
            r0.close()     // Catch:{ all -> 0x00b1 }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r2.timeout(r3, r0)
            boolean r0 = r1.hasDeadline()
            if (r0 == 0) goto L_0x00f0
            r2.deadlineNanoTime(r5)
            goto L_0x00f0
        L_0x00b1:
            r0 = move-exception
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.NANOSECONDS
            r2.timeout(r3, r7)
            boolean r1 = r1.hasDeadline()
            if (r1 == 0) goto L_0x00c0
            r2.deadlineNanoTime(r5)
        L_0x00c0:
            throw r0
        L_0x00c1:
            boolean r5 = r1.hasDeadline()
            if (r5 == 0) goto L_0x00ce
            long r5 = r1.deadlineNanoTime()
            r2.deadlineNanoTime(r5)
        L_0x00ce:
            r0.close()     // Catch:{ all -> 0x00e0 }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r2.timeout(r3, r0)
            boolean r0 = r1.hasDeadline()
            if (r0 == 0) goto L_0x00f0
            r2.clearDeadline()
            goto L_0x00f0
        L_0x00e0:
            r0 = move-exception
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.NANOSECONDS
            r2.timeout(r3, r5)
            boolean r1 = r1.hasDeadline()
            if (r1 == 0) goto L_0x00ef
            r2.clearDeadline()
        L_0x00ef:
            throw r0
        L_0x00f0:
            return
        L_0x00f1:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch:{ all -> 0x00f9 }
            java.lang.String r2 = "null cannot be cast to non-null type java.lang.Object"
            r0.<init>(r2)     // Catch:{ all -> 0x00f9 }
            throw r0     // Catch:{ all -> 0x00f9 }
        L_0x00f9:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Pipe$sink$1.close():void");
    }

    public Timeout timeout() {
        return this.timeout;
    }
}
