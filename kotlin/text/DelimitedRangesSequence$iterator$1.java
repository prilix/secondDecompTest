package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(mo36737d1 = {"\u0000%\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\t\u0010\u0019\u001a\u00020\u001aH\u0002J\t\u0010\u001b\u001a\u00020\u0002H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\bR\u001a\u0010\u0014\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\b¨\u0006\u001c"}, mo36738d2 = {"kotlin/text/DelimitedRangesSequence$iterator$1", "", "Lkotlin/ranges/IntRange;", "counter", "", "getCounter", "()I", "setCounter", "(I)V", "currentStartIndex", "getCurrentStartIndex", "setCurrentStartIndex", "nextItem", "getNextItem", "()Lkotlin/ranges/IntRange;", "setNextItem", "(Lkotlin/ranges/IntRange;)V", "nextSearchIndex", "getNextSearchIndex", "setNextSearchIndex", "nextState", "getNextState", "setNextState", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"}, mo36739k = 1, mo36740mv = {1, 5, 1})
/* compiled from: Strings.kt */
public final class DelimitedRangesSequence$iterator$1 implements Iterator<IntRange>, KMappedMarker {
    private int counter;
    private int currentStartIndex;
    private IntRange nextItem;
    private int nextSearchIndex;
    private int nextState = -1;
    final /* synthetic */ DelimitedRangesSequence this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        this.this$0 = delimitedRangesSequence;
        int coerceIn = RangesKt.coerceIn(delimitedRangesSequence.startIndex, 0, delimitedRangesSequence.input.length());
        this.currentStartIndex = coerceIn;
        this.nextSearchIndex = coerceIn;
    }

    public final int getNextState() {
        return this.nextState;
    }

    public final void setNextState(int i) {
        this.nextState = i;
    }

    public final int getCurrentStartIndex() {
        return this.currentStartIndex;
    }

    public final void setCurrentStartIndex(int i) {
        this.currentStartIndex = i;
    }

    public final int getNextSearchIndex() {
        return this.nextSearchIndex;
    }

    public final void setNextSearchIndex(int i) {
        this.nextSearchIndex = i;
    }

    public final IntRange getNextItem() {
        return this.nextItem;
    }

    public final void setNextItem(IntRange intRange) {
        this.nextItem = intRange;
    }

    public final int getCounter() {
        return this.counter;
    }

    public final void setCounter(int i) {
        this.counter = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0024, code lost:
        if (r0 < r6.this$0.limit) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void calcNext() {
        /*
            r6 = this;
            int r0 = r6.nextSearchIndex
            r1 = 0
            if (r0 >= 0) goto L_0x000f
            r6.nextState = r1
            r0 = 0
            r1 = r0
            kotlin.ranges.IntRange r1 = (kotlin.ranges.IntRange) r1
            r6.nextItem = r0
            goto L_0x00a1
        L_0x000f:
            kotlin.text.DelimitedRangesSequence r0 = r6.this$0
            int r0 = r0.limit
            r2 = -1
            r3 = 1
            if (r0 <= 0) goto L_0x0026
            int r0 = r6.counter
            int r0 = r0 + r3
            r6.counter = r0
            kotlin.text.DelimitedRangesSequence r4 = r6.this$0
            int r4 = r4.limit
            if (r0 >= r4) goto L_0x0034
        L_0x0026:
            int r0 = r6.nextSearchIndex
            kotlin.text.DelimitedRangesSequence r4 = r6.this$0
            java.lang.CharSequence r4 = r4.input
            int r4 = r4.length()
            if (r0 <= r4) goto L_0x004a
        L_0x0034:
            int r0 = r6.currentStartIndex
            kotlin.ranges.IntRange r1 = new kotlin.ranges.IntRange
            kotlin.text.DelimitedRangesSequence r4 = r6.this$0
            java.lang.CharSequence r4 = r4.input
            int r4 = kotlin.text.StringsKt.getLastIndex(r4)
            r1.<init>(r0, r4)
            r6.nextItem = r1
            r6.nextSearchIndex = r2
            goto L_0x009f
        L_0x004a:
            kotlin.text.DelimitedRangesSequence r0 = r6.this$0
            kotlin.jvm.functions.Function2 r0 = r0.getNextMatch
            kotlin.text.DelimitedRangesSequence r4 = r6.this$0
            java.lang.CharSequence r4 = r4.input
            int r5 = r6.nextSearchIndex
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r0 = r0.invoke(r4, r5)
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 != 0) goto L_0x007a
            int r0 = r6.currentStartIndex
            kotlin.ranges.IntRange r1 = new kotlin.ranges.IntRange
            kotlin.text.DelimitedRangesSequence r4 = r6.this$0
            java.lang.CharSequence r4 = r4.input
            int r4 = kotlin.text.StringsKt.getLastIndex(r4)
            r1.<init>(r0, r4)
            r6.nextItem = r1
            r6.nextSearchIndex = r2
            goto L_0x009f
        L_0x007a:
            java.lang.Object r2 = r0.component1()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.component2()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r4 = r6.currentStartIndex
            kotlin.ranges.IntRange r4 = kotlin.ranges.RangesKt.until((int) r4, (int) r2)
            r6.nextItem = r4
            int r2 = r2 + r0
            r6.currentStartIndex = r2
            if (r0 != 0) goto L_0x009c
            r1 = 1
        L_0x009c:
            int r2 = r2 + r1
            r6.nextSearchIndex = r2
        L_0x009f:
            r6.nextState = r3
        L_0x00a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.calcNext():void");
    }

    public IntRange next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
            IntRange intRange = this.nextItem;
            Objects.requireNonNull(intRange, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            IntRange intRange2 = null;
            this.nextItem = null;
            this.nextState = -1;
            return intRange;
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        return this.nextState == 1;
    }
}
