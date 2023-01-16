package com.jch.racWiFi.userManagement.network;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.DisableAccountDataModels;

public class DisableAccountPresenter {
    /* access modifiers changed from: private */
    public IDisableAccountPresenter iDisableAccountPresenter;

    public interface IDisableAccountPresenter extends INetworkConnectivity {
        void onAccountDisabledFailure(DisableAccountDataModels.DisableAccountFailureResponse disableAccountFailureResponse);

        void onAccountDisabledSuccess();
    }

    public DisableAccountPresenter(IDisableAccountPresenter iDisableAccountPresenter2) {
        this.iDisableAccountPresenter = iDisableAccountPresenter2;
    }

    public void disableAccount() {
        final SingleLiveEvent<GenericResponse> disableAccount = new DisableAccountNetworkDispatcher().disableAccount();
        disableAccount.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                disableAccount.removeObserver(this);
                if (genericResponse.unableToReachServer()) {
                    DisableAccountPresenter.this.iDisableAccountPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                } else if (genericResponse.isApiSuccessful()) {
                    DisableAccountPresenter.this.iDisableAccountPresenter.onAccountDisabledSuccess();
                } else {
                    DisableAccountPresenter.this.iDisableAccountPresenter.onAccountDisabledFailure((DisableAccountDataModels.DisableAccountFailureResponse) genericResponse.getErrorBodyOfType(DisableAccountDataModels.DisableAccountFailureResponse.class));
                }
            }
        });
    }
}
