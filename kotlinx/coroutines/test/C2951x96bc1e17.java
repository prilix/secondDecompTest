package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, mo36738d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"}, mo36739k = 1, mo36740mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.test.TestCoroutineContext$$special$$inlined$CoroutineExceptionHandler$1 */
/* compiled from: CoroutineExceptionHandler.kt */
public final class C2951x96bc1e17 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    final /* synthetic */ TestCoroutineContext this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2951x96bc1e17(CoroutineContext.Key key, TestCoroutineContext testCoroutineContext) {
        super(key);
        this.this$0 = testCoroutineContext;
    }

    public void handleException(CoroutineContext coroutineContext, Throwable th) {
        this.this$0.uncaughtExceptions.add(th);
    }
}
