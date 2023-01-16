package com.jch.racWiFi.amplitude.util;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(mo36737d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo36738d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo36739k = 3, mo36740mv = {1, 5, 1}, mo36742xi = 48)
@DebugMetadata(mo37450c = "com.jch.racWiFi.amplitude.util.AppEventService$onTaskRemoved$1", mo37451f = "AppEventService.kt", mo37452i = {}, mo37453l = {40}, mo37454m = "invokeSuspend", mo37455n = {}, mo37456s = {})
/* compiled from: AppEventService.kt */
final class AppEventService$onTaskRemoved$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Intent $rootIntent;
    int label;
    final /* synthetic */ AppEventService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AppEventService$onTaskRemoved$1(AppEventService appEventService, Intent intent, Continuation<? super AppEventService$onTaskRemoved$1> continuation) {
        super(2, continuation);
        this.this$0 = appEventService;
        this.$rootIntent = intent;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppEventService$onTaskRemoved$1(this.this$0, this.$rootIntent, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppEventService$onTaskRemoved$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(2000, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.stopSelf();
        AppEventService$onTaskRemoved$1.super.onTaskRemoved(this.$rootIntent);
        return Unit.INSTANCE;
    }
}
