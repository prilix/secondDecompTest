package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(mo36737d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\t\n\u0002\b\u0005\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u0002H\u0002¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H&¨\u0006\u0007"}, mo36738d2 = {"Lkotlin/collections/LongIterator;", "", "", "()V", "next", "()Ljava/lang/Long;", "nextLong", "kotlin-stdlib"}, mo36739k = 1, mo36740mv = {1, 5, 1})
/* compiled from: Iterators.kt */
public abstract class LongIterator implements Iterator<Long>, KMappedMarker {
    public abstract long nextLong();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final Long next() {
        return Long.valueOf(nextLong());
    }
}
