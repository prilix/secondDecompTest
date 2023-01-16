package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.C1048Api;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public final class zaci {
    public final RegisterListenerMethod<C1048Api.AnyClient, ?> zaa;
    public final UnregisterListenerMethod<C1048Api.AnyClient, ?> zab;
    public final Runnable zac;

    public zaci(RegisterListenerMethod<C1048Api.AnyClient, ?> registerListenerMethod, UnregisterListenerMethod<C1048Api.AnyClient, ?> unregisterListenerMethod, Runnable runnable) {
        this.zaa = registerListenerMethod;
        this.zab = unregisterListenerMethod;
        this.zac = runnable;
    }
}
