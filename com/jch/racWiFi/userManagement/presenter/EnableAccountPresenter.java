package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.DisableAccountDataModels;
import com.jch.racWiFi.userManagement.network.EnableAccountNetworkDispatcher;
import com.jch.racWiFi.userManagement.network.InitiateAccountActivationDispatcher;

public class EnableAccountPresenter extends AbstractPresenter {
    DisableAccountDataModels.EnableAccountDataModel enableAccountDataModel;
    IEnableAccountPresenter iEnableAccountPresenter;

    public interface IEnableAccountPresenter extends INetworkConnectivity {
        void onEnableAccountOtpSendFailureResponse(DisableAccountDataModels.EnableAccountFailureResponse enableAccountFailureResponse);

        void onEnableAccountOtpSendSuccessResponse(DisableAccountDataModels.EnableAccountSuccessResponse enableAccountSuccessResponse);

        void onOtpResendFailureResponse(DisableAccountDataModels.InitiateAccountActivationFailureResponse initiateAccountActivationFailureResponse);

        void onOtpResendSuccessResponse();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public EnableAccountPresenter(IEnableAccountPresenter iEnableAccountPresenter2) {
        this.iEnableAccountPresenter = iEnableAccountPresenter2;
    }

    public void enableAccount(DisableAccountDataModels.EnableAccountDataModel enableAccountDataModel2) {
        final SingleLiveEvent<GenericResponse> enableAccount = new EnableAccountNetworkDispatcher().enableAccount(enableAccountDataModel2);
        enableAccount.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                enableAccount.removeObserver(this);
                if (EnableAccountPresenter.this.iEnableAccountPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        EnableAccountPresenter.this.iEnableAccountPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        EnableAccountPresenter.this.iEnableAccountPresenter.onEnableAccountOtpSendSuccessResponse((DisableAccountDataModels.EnableAccountSuccessResponse) genericResponse.getBodyOfType(DisableAccountDataModels.EnableAccountSuccessResponse.class));
                    } else {
                        EnableAccountPresenter.this.iEnableAccountPresenter.onEnableAccountOtpSendFailureResponse((DisableAccountDataModels.EnableAccountFailureResponse) genericResponse.getErrorBodyOfType(DisableAccountDataModels.EnableAccountFailureResponse.class));
                    }
                }
            }
        });
    }

    public void sendOTP(DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel) {
        final SingleLiveEvent<GenericResponse> InitiateEnableAccount = new InitiateAccountActivationDispatcher().InitiateEnableAccount(initiateEnableAccountDataModel);
        InitiateEnableAccount.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                InitiateEnableAccount.removeObserver(this);
                if (EnableAccountPresenter.this.iEnableAccountPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        EnableAccountPresenter.this.iEnableAccountPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        EnableAccountPresenter.this.iEnableAccountPresenter.onOtpResendSuccessResponse();
                    } else {
                        EnableAccountPresenter.this.iEnableAccountPresenter.onOtpResendFailureResponse((DisableAccountDataModels.InitiateAccountActivationFailureResponse) genericResponse.getErrorBodyOfType(DisableAccountDataModels.InitiateAccountActivationFailureResponse.class));
                    }
                }
            }
        });
    }

    public void verifyOTPEmail(String str, String str2) {
        DisableAccountDataModels.EnableAccountDataModel enableAccountDataModel2 = new DisableAccountDataModels.EnableAccountDataModel();
        this.enableAccountDataModel = enableAccountDataModel2;
        enableAccountDataModel2.email = str;
        this.enableAccountDataModel.otp = str2;
        enableAccount(this.enableAccountDataModel);
    }

    public void verifyOTPMobileNumber(String str, String str2) {
        DisableAccountDataModels.EnableAccountDataModel enableAccountDataModel2 = new DisableAccountDataModels.EnableAccountDataModel();
        this.enableAccountDataModel = enableAccountDataModel2;
        enableAccountDataModel2.mobileNumber = str;
        this.enableAccountDataModel.otp = str2;
        enableAccount(this.enableAccountDataModel);
    }

    public void removeCallbacks() {
        this.iEnableAccountPresenter = null;
    }
}
