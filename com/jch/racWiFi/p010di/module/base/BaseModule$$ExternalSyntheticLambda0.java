package com.jch.racWiFi.p010di.module.base;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* renamed from: com.jch.racWiFi.di.module.base.BaseModule$$ExternalSyntheticLambda0 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BaseModule$$ExternalSyntheticLambda0 implements HostnameVerifier {
    public static final /* synthetic */ BaseModule$$ExternalSyntheticLambda0 INSTANCE = new BaseModule$$ExternalSyntheticLambda0();

    private /* synthetic */ BaseModule$$ExternalSyntheticLambda0() {
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        return BaseModule.lambda$provideClient$0(str, sSLSession);
    }
}
