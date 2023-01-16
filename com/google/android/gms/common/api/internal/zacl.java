package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
final class zacl extends UnregisterListenerMethod {
    final /* synthetic */ RegistrationMethods.Builder zaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zacl(RegistrationMethods.Builder builder, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        this.zaa = builder;
    }

    /* access modifiers changed from: protected */
    public final void unregisterListener(C1048Api.AnyClient anyClient, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        this.zaa.zab.accept(anyClient, taskCompletionSource);
    }
}
