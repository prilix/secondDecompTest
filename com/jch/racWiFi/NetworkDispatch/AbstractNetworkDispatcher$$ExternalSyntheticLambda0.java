package com.jch.racWiFi.NetworkDispatch;

import okhttp3.Interceptor;
import okhttp3.Response;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractNetworkDispatcher$$ExternalSyntheticLambda0 implements Interceptor {
    public static final /* synthetic */ AbstractNetworkDispatcher$$ExternalSyntheticLambda0 INSTANCE = new AbstractNetworkDispatcher$$ExternalSyntheticLambda0();

    private /* synthetic */ AbstractNetworkDispatcher$$ExternalSyntheticLambda0() {
    }

    public final Response intercept(Interceptor.Chain chain) {
        return AbstractNetworkDispatcher.lambda$buildRetrofit$0(chain);
    }
}
