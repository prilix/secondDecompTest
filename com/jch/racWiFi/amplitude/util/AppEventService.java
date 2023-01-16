package com.jch.racWiFi.amplitude.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.jch.racWiFi.amplitude.factory.ClientFactory;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

@Metadata(mo36737d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\"\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0016J\u0012\u0010\u001e\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010\u0018H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006 "}, mo36738d2 = {"Lcom/jch/racWiFi/amplitude/util/AppEventService;", "Landroid/app/Service;", "()V", "mAmplitudeUtil", "Lcom/jch/racWiFi/amplitude/util/AmplitudeUtil;", "getMAmplitudeUtil", "()Lcom/jch/racWiFi/amplitude/util/AmplitudeUtil;", "setMAmplitudeUtil", "(Lcom/jch/racWiFi/amplitude/util/AmplitudeUtil;)V", "mClientFactory", "Lcom/jch/racWiFi/amplitude/factory/ClientFactory;", "getMClientFactory", "()Lcom/jch/racWiFi/amplitude/factory/ClientFactory;", "setMClientFactory", "(Lcom/jch/racWiFi/amplitude/factory/ClientFactory;)V", "logEvents", "", "eventType", "", "time", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onStartCommand", "", "flags", "startId", "onTaskRemoved", "rootIntent", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: AppEventService.kt */
public final class AppEventService extends Service {
    @Inject
    public AmplitudeUtil mAmplitudeUtil;
    @Inject
    public ClientFactory mClientFactory;

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public final AmplitudeUtil getMAmplitudeUtil() {
        AmplitudeUtil amplitudeUtil = this.mAmplitudeUtil;
        if (amplitudeUtil != null) {
            return amplitudeUtil;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAmplitudeUtil");
        return null;
    }

    public final void setMAmplitudeUtil(AmplitudeUtil amplitudeUtil) {
        Intrinsics.checkNotNullParameter(amplitudeUtil, "<set-?>");
        this.mAmplitudeUtil = amplitudeUtil;
    }

    public final ClientFactory getMClientFactory() {
        ClientFactory clientFactory = this.mClientFactory;
        if (clientFactory != null) {
            return clientFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mClientFactory");
        return null;
    }

    public final void setMClientFactory(ClientFactory clientFactory) {
        Intrinsics.checkNotNullParameter(clientFactory, "<set-?>");
        this.mClientFactory = clientFactory;
    }

    public IBinder onBind(Intent intent) {
        throw new NotImplementedError("An operation is not implemented: " + "Not yet implemented");
    }

    public void onCreate() {
        AndroidInjection.inject((Service) this);
        super.onCreate();
        logEvents(Events.UNIQUE_VISIT.name(), 0);
    }

    public void onTaskRemoved(Intent intent) {
        logEvents(Events.KILL_APP.name(), 0);
        Object unused = BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new AppEventService$onTaskRemoved$1(this, intent, (Continuation<? super AppEventService$onTaskRemoved$1>) null), 1, (Object) null);
    }

    public final void logEvents(String str, long j) {
        try {
            getMAmplitudeUtil().logEvents(str, j, getMClientFactory());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
