package okio;

import com.jch.racWiFi.p010di.util.Constants;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36736bv = {1, 0, 2}, mo36737d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, mo36738d2 = {"okio/Timeout$Companion$NONE$1", "Lokio/Timeout;", "deadlineNanoTime", "", "throwIfReached", "", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "jvm"}, mo36739k = 1, mo36740mv = {1, 1, 11})
/* compiled from: Timeout.kt */
public final class Timeout$Companion$NONE$1 extends Timeout {
    public void throwIfReached() {
    }

    Timeout$Companion$NONE$1() {
    }

    public Timeout timeout(long j, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, Constants.Keys.UNIT);
        return this;
    }

    public Timeout deadlineNanoTime(long j) {
        return this;
    }
}
