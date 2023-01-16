package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.ChangeOwnerShipDataModel;
import com.jch.racWiFi.userManagement.network.ChangeOwnerShipNetworkDispatcher;

public class ChangeOwnerShipPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IChangeOwnerShipInterface iChangeOwnerShipInterface;

    public interface IChangeOwnerShipInterface extends INetworkConnectivity {
        void onChangeOwnerShipFailure(ChangeOwnerShipDataModel.ChangeOwnerShipDataModelFailureResponse changeOwnerShipDataModelFailureResponse);

        void onChangeOwnerShipSuccess();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public ChangeOwnerShipPresenter(IChangeOwnerShipInterface iChangeOwnerShipInterface2) {
        this.iChangeOwnerShipInterface = iChangeOwnerShipInterface2;
    }

    public void changeOwnerShip(int i, int i2) {
        final SingleLiveEvent<GenericResponse> changeOwnerShip = new ChangeOwnerShipNetworkDispatcher().changeOwnerShip(i, i2);
        changeOwnerShip.observeSingleEvent(new Observer<GenericResponse>() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            static {
                Class<ChangeOwnerShipPresenter> cls = ChangeOwnerShipPresenter.class;
            }

            public void onChanged(GenericResponse genericResponse) {
                changeOwnerShip.removeObserver(this);
                if (ChangeOwnerShipPresenter.this.iChangeOwnerShipInterface != null) {
                    if (genericResponse.unableToReachServer()) {
                        ChangeOwnerShipPresenter.this.iChangeOwnerShipInterface.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        ChangeOwnerShipPresenter.this.iChangeOwnerShipInterface.onChangeOwnerShipSuccess();
                    } else {
                        ChangeOwnerShipDataModel.ChangeOwnerShipDataModelFailureResponse changeOwnerShipDataModelFailureResponse = (ChangeOwnerShipDataModel.ChangeOwnerShipDataModelFailureResponse) genericResponse.getErrorBodyOfType(ChangeOwnerShipDataModel.ChangeOwnerShipDataModelFailureResponse.class);
                        changeOwnerShipDataModelFailureResponse.statusCodeValue = genericResponse.getResponse().code();
                        ChangeOwnerShipPresenter.this.iChangeOwnerShipInterface.onChangeOwnerShipFailure(changeOwnerShipDataModelFailureResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iChangeOwnerShipInterface = null;
    }
}
