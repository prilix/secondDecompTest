package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public class BaseImplementation {

    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public interface ResultHolder<R> {
        void setFailedResult(Status status);

        void setResult(R r);
    }

    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public static abstract class ApiMethodImpl<R extends Result, A extends C1048Api.AnyClient> extends BasePendingResult<R> implements ResultHolder<R> {
        private final C1048Api<?> mApi;
        private final C1048Api.AnyClientKey<A> mClientKey;

        @Deprecated
        protected ApiMethodImpl(C1048Api.AnyClientKey<A> anyClientKey, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
            this.mClientKey = (C1048Api.AnyClientKey) Preconditions.checkNotNull(anyClientKey);
            this.mApi = null;
        }

        private void setFailedResult(RemoteException remoteException) {
            setFailedResult(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
        }

        /* access modifiers changed from: protected */
        public abstract void doExecute(A a) throws RemoteException;

        public final C1048Api<?> getApi() {
            return this.mApi;
        }

        public final C1048Api.AnyClientKey<A> getClientKey() {
            return this.mClientKey;
        }

        /* access modifiers changed from: protected */
        public void onSetFailedResult(R r) {
        }

        public final void run(A a) throws DeadObjectException {
            try {
                doExecute(a);
            } catch (DeadObjectException e) {
                setFailedResult((RemoteException) e);
                throw e;
            } catch (RemoteException e2) {
                setFailedResult(e2);
            }
        }

        public /* bridge */ /* synthetic */ void setResult(Object obj) {
            super.setResult((Result) obj);
        }

        protected ApiMethodImpl(C1048Api<?> api, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
            Preconditions.checkNotNull(api, "Api must not be null");
            this.mClientKey = api.zab();
            this.mApi = api;
        }

        public final void setFailedResult(Status status) {
            Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success");
            Result createFailedResult = createFailedResult(status);
            setResult(createFailedResult);
            onSetFailedResult(createFailedResult);
        }

        protected ApiMethodImpl(BasePendingResult.CallbackHandler<R> callbackHandler) {
            super(callbackHandler);
            this.mClientKey = new C1048Api.AnyClientKey<>();
            this.mApi = null;
        }
    }
}
