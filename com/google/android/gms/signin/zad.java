package com.google.android.gms.signin;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public final class zad {
    public static final C1048Api.ClientKey<SignInClientImpl> zaa;
    public static final C1048Api.ClientKey<SignInClientImpl> zab;
    public static final C1048Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> zac;
    static final C1048Api.AbstractClientBuilder<SignInClientImpl, zac> zad;
    public static final Scope zae = new Scope(Scopes.PROFILE);
    public static final Scope zaf = new Scope("email");
    public static final C1048Api<SignInOptions> zag;
    public static final C1048Api<zac> zah;

    static {
        C1048Api.ClientKey<SignInClientImpl> clientKey = new C1048Api.ClientKey<>();
        zaa = clientKey;
        C1048Api.ClientKey<SignInClientImpl> clientKey2 = new C1048Api.ClientKey<>();
        zab = clientKey2;
        zaa zaa2 = new zaa();
        zac = zaa2;
        zab zab2 = new zab();
        zad = zab2;
        zag = new C1048Api<>("SignIn.API", zaa2, clientKey);
        zah = new C1048Api<>("SignIn.INTERNAL_API", zab2, clientKey2);
    }
}
