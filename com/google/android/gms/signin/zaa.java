package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
final class zaa extends C1048Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> {
    zaa() {
    }

    public final /* bridge */ /* synthetic */ C1048Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        SignInOptions signInOptions = (SignInOptions) obj;
        return new SignInClientImpl(context, looper, true, clientSettings, SignInClientImpl.createBundleFromClientSettings(clientSettings), connectionCallbacks, onConnectionFailedListener);
    }
}
