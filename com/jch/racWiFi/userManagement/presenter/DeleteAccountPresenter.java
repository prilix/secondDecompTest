package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.network.DeleteAccountNetworkDispatcher;

public class DeleteAccountPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IDeleteAccountPresenter iDeleteAccountPresenter;

    public interface IDeleteAccountPresenter extends INetworkConnectivity {
        void onAccountDeleteFailure(GenericResponse genericResponse);

        void onAccountDeleteSuccess();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public DeleteAccountPresenter(IDeleteAccountPresenter iDeleteAccountPresenter2) {
        this.iDeleteAccountPresenter = iDeleteAccountPresenter2;
    }

    public void deleteAccount() {
        final SingleLiveEvent<GenericResponse> deleteAccount = new DeleteAccountNetworkDispatcher().deleteAccount();
        deleteAccount.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                deleteAccount.removeObserver(this);
                if (DeleteAccountPresenter.this.iDeleteAccountPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        DeleteAccountPresenter.this.iDeleteAccountPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        DeleteAccountPresenter.this.iDeleteAccountPresenter.onAccountDeleteSuccess();
                    } else {
                        DeleteAccountPresenter.this.iDeleteAccountPresenter.onAccountDeleteFailure(genericResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iDeleteAccountPresenter = null;
    }
}
