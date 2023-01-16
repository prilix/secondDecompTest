package kotlinx.coroutines.internal;

import androidx.concurrent.futures.C0174xc40028dd;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00028\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\r\u0010\fJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0004\b\u000e\u0010\fR\u001a\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00008V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u00058F@\u0006¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0016\u001a\u00020\u00158F@\u0006¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u001b\u001a\u00020\u00188V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, mo36738d2 = {"Lkotlinx/coroutines/internal/AtomicOp;", "T", "<init>", "()V", "affected", "", "failure", "", "complete", "(Ljava/lang/Object;Ljava/lang/Object;)V", "decision", "decide", "(Ljava/lang/Object;)Ljava/lang/Object;", "perform", "prepare", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "atomicOp", "getConsensus", "()Ljava/lang/Object;", "consensus", "", "isDecided", "()Z", "", "getOpSequence", "()J", "opSequence", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/OpDescriptor;"}, mo36739k = 1, mo36740mv = {1, 4, 0})
/* compiled from: Atomic.kt */
public abstract class AtomicOp<T> extends OpDescriptor {
    private static final AtomicReferenceFieldUpdater _consensus$FU = AtomicReferenceFieldUpdater.newUpdater(AtomicOp.class, Object.class, "_consensus");
    private volatile Object _consensus = AtomicKt.NO_DECISION;

    public abstract void complete(T t, Object obj);

    public AtomicOp<?> getAtomicOp() {
        return this;
    }

    public long getOpSequence() {
        return 0;
    }

    public abstract Object prepare(T t);

    public final Object getConsensus() {
        return this._consensus;
    }

    public final boolean isDecided() {
        return this._consensus != AtomicKt.NO_DECISION;
    }

    public final Object decide(Object obj) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(obj != AtomicKt.NO_DECISION)) {
                throw new AssertionError();
            }
        }
        Object obj2 = this._consensus;
        if (obj2 != AtomicKt.NO_DECISION) {
            return obj2;
        }
        if (C0174xc40028dd.m4m(_consensus$FU, this, AtomicKt.NO_DECISION, obj)) {
            return obj;
        }
        return this._consensus;
    }

    public final Object perform(Object obj) {
        Object obj2 = this._consensus;
        if (obj2 == AtomicKt.NO_DECISION) {
            obj2 = decide(prepare(obj));
        }
        complete(obj, obj2);
        return obj2;
    }
}