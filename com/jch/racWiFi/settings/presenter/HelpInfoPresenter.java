package com.jch.racWiFi.settings.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.settings.model.HelpDataModelResponse;
import com.jch.racWiFi.settings.network.HelpNetworkDispatcher;

public class HelpInfoPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public HelpInfoInterface iHelpInfoInterface;

    public interface HelpInfoInterface extends INetworkConnectivity {
        void onHelpInfoFetchFailure(GenericResponse genericResponse);

        void onHelpInfoFetchSuccess(HelpDataModelResponse helpDataModelResponse);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public HelpInfoPresenter(HelpInfoInterface helpInfoInterface) {
        this.iHelpInfoInterface = helpInfoInterface;
    }

    public void getHelpInfo(LifecycleOwner lifecycleOwner, int i, int i2) {
        final SingleLiveEvent<GenericResponse> helpInfoForRac = new HelpNetworkDispatcher().getHelpInfoForRac(i, i2);
        helpInfoForRac.observeSingleEvent(lifecycleOwner, new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                helpInfoForRac.removeObserver(this);
                if (HelpInfoPresenter.this.iHelpInfoInterface != null) {
                    if (genericResponse.unableToReachServer()) {
                        HelpInfoPresenter.this.iHelpInfoInterface.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        HelpInfoPresenter.this.iHelpInfoInterface.onHelpInfoFetchSuccess((HelpDataModelResponse) genericResponse.getBodyOfType(HelpDataModelResponse.class));
                    } else {
                        HelpInfoPresenter.this.iHelpInfoInterface.onHelpInfoFetchFailure(genericResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iHelpInfoInterface = null;
    }
}
