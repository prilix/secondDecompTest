package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo36738d2 = {"<anonymous>", "", "e", "invoke"}, mo36739k = 3, mo36740mv = {1, 4, 0})
/* compiled from: ExceptionsConstuctor.kt */
public final class ExceptionsConstuctorKt$safeCtor$1 extends Lambda implements Function1<Throwable, Throwable> {
    final /* synthetic */ Function1 $block;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExceptionsConstuctorKt$safeCtor$1(Function1 function1) {
        super(1);
        this.$block = function1;
    }

    public final Throwable invoke(Throwable th) {
        Object obj;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m1581constructorimpl((Throwable) this.$block.invoke(th));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m1581constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m1587isFailureimpl(obj)) {
            obj = null;
        }
        return (Throwable) obj;
    }
}
