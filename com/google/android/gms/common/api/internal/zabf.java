package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public interface zabf {
    <A extends C1048Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t);

    <A extends C1048Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t);

    void zad();

    void zae();

    void zag(Bundle bundle);

    void zah(ConnectionResult connectionResult, C1048Api<?> api, boolean z);

    void zai(int i);

    boolean zaj();
}
