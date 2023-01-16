package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.network.UserOnBoardingNetworkDispatcher;

public class UserOnBoardingPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IUserOnBoardingPresenter iUserOnBoardingPresenter;

    public interface IUserOnBoardingPresenter extends INetworkConnectivity {
        void onUserOnBoardFailure(GenericResponse genericResponse);

        void onUserOnBoardSuccess();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public UserOnBoardingPresenter(IUserOnBoardingPresenter iUserOnBoardingPresenter2) {
        this.iUserOnBoardingPresenter = iUserOnBoardingPresenter2;
    }

    public void onBoardUser() {
        if (!Constants.IS_DEMO_MODE) {
            final SingleLiveEvent<GenericResponse> onBoardUser = new UserOnBoardingNetworkDispatcher().onBoardUser(TokenUtil.getInstance().obtain());
            onBoardUser.observeSingleEvent(new Observer<GenericResponse>() {
                public void onChanged(GenericResponse genericResponse) {
                    onBoardUser.removeObserver(this);
                    if (UserOnBoardingPresenter.this.iUserOnBoardingPresenter != null) {
                        if (genericResponse.unableToReachServer()) {
                            UserOnBoardingPresenter.this.iUserOnBoardingPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                        } else if (genericResponse.isApiSuccessful()) {
                            UserOnBoardingPresenter.this.iUserOnBoardingPresenter.onUserOnBoardSuccess();
                        } else {
                            UserOnBoardingPresenter.this.iUserOnBoardingPresenter.onUserOnBoardFailure(genericResponse);
                        }
                    }
                }
            });
        }
    }

    public void removeCallbacks() {
        this.iUserOnBoardingPresenter = null;
    }
}
