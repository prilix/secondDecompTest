package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo36738d2 = {"<anonymous>", "", "run"}, mo36739k = 3, mo36740mv = {1, 4, 0})
/* compiled from: Runnable.kt */
public final class RunnableKt$Runnable$1 implements Runnable {
    final /* synthetic */ Function0 $block;

    public RunnableKt$Runnable$1(Function0 function0) {
        this.$block = function0;
    }

    public final void run() {
        this.$block.invoke();
    }
}
