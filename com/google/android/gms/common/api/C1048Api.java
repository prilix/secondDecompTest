package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.C1048Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.gms.common.api.Api */
/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public final class C1048Api<O extends ApiOptions> {
    private final AbstractClientBuilder<?, O> zaa;
    private final ClientKey<?> zab;
    private final String zac;

    /* renamed from: com.google.android.gms.common.api.Api$AbstractClientBuilder */
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public static abstract class AbstractClientBuilder<T extends Client, O> extends BaseClientBuilder<T, O> {
        @Deprecated
        public T buildClient(Context context, Looper looper, ClientSettings clientSettings, O o, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return buildClient(context, looper, clientSettings, o, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
        }

        public T buildClient(Context context, Looper looper, ClientSettings clientSettings, O o, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    /* renamed from: com.google.android.gms.common.api.Api$AnyClient */
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public interface AnyClient {
    }

    /* renamed from: com.google.android.gms.common.api.Api$AnyClientKey */
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public static class AnyClientKey<C extends AnyClient> {
    }

    /* renamed from: com.google.android.gms.common.api.Api$ApiOptions */
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public interface ApiOptions {
        public static final NoOptions NO_OPTIONS = new NoOptions((zaa) null);

        /* renamed from: com.google.android.gms.common.api.Api$ApiOptions$HasAccountOptions */
        /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
        public interface HasAccountOptions extends HasOptions, NotRequiredOptions {
            Account getAccount();
        }

        /* renamed from: com.google.android.gms.common.api.Api$ApiOptions$HasGoogleSignInAccountOptions */
        /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
        public interface HasGoogleSignInAccountOptions extends HasOptions {
            GoogleSignInAccount getGoogleSignInAccount();
        }

        /* renamed from: com.google.android.gms.common.api.Api$ApiOptions$HasOptions */
        /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
        public interface HasOptions extends ApiOptions {
        }

        /* renamed from: com.google.android.gms.common.api.Api$ApiOptions$NoOptions */
        /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }

            /* synthetic */ NoOptions(zaa zaa) {
            }
        }

        /* renamed from: com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions */
        /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
        public interface NotRequiredOptions extends ApiOptions {
        }

        /* renamed from: com.google.android.gms.common.api.Api$ApiOptions$Optional */
        /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    /* renamed from: com.google.android.gms.common.api.Api$BaseClientBuilder */
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public static abstract class BaseClientBuilder<T extends AnyClient, O> {
        public static final int API_PRIORITY_GAMES = 1;
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;
        public static final int API_PRIORITY_PLUS = 2;

        public List<Scope> getImpliedScopes(O o) {
            return Collections.emptyList();
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    }

    /* renamed from: com.google.android.gms.common.api.Api$Client */
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public interface Client extends AnyClient {
        void connect(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks);

        void disconnect();

        void disconnect(String str);

        void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        Feature[] getAvailableFeatures();

        String getEndpointPackageName();

        String getLastDisconnectMessage();

        int getMinApkVersion();

        void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set);

        Feature[] getRequiredFeatures();

        Set<Scope> getScopesForConnectionlessNonSignIn();

        IBinder getServiceBrokerBinder();

        Intent getSignInIntent();

        boolean isConnected();

        boolean isConnecting();

        void onUserSignOut(BaseGmsClient.SignOutCallbacks signOutCallbacks);

        boolean providesSignIn();

        boolean requiresAccount();

        boolean requiresGooglePlayServices();

        boolean requiresSignIn();
    }

    /* renamed from: com.google.android.gms.common.api.Api$ClientKey */
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public static final class ClientKey<C extends Client> extends AnyClientKey<C> {
    }

    public <C extends Client> C1048Api(String str, AbstractClientBuilder<C, O> abstractClientBuilder, ClientKey<C> clientKey) {
        Preconditions.checkNotNull(abstractClientBuilder, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull(clientKey, "Cannot construct an Api with a null ClientKey");
        this.zac = str;
        this.zaa = abstractClientBuilder;
        this.zab = clientKey;
    }

    public final AbstractClientBuilder<?, O> zaa() {
        return this.zaa;
    }

    public final AnyClientKey<?> zab() {
        return this.zab;
    }

    public final BaseClientBuilder<?, O> zac() {
        return this.zaa;
    }

    public final String zad() {
        return this.zac;
    }
}
