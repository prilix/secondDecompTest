package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.DisableAccountDataModels;
import com.jch.racWiFi.userManagement.network.InitiateAccountActivationDispatcher;

public class InitiateEnableAccountPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IEnableAccountPresenter iEnableAccountPresenter;
    /* access modifiers changed from: private */
    public DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel;

    public interface IEnableAccountPresenter extends INetworkConnectivity {
        void fieldEmptyCallback();

        void fieldValidated(DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel);

        void invalidField();

        void onEnableAccountOtpSendFailureResponse(DisableAccountDataModels.InitiateAccountActivationFailureResponse initiateAccountActivationFailureResponse);

        void onEnableAccountOtpSendSuccessResponse(DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public InitiateEnableAccountPresenter(IEnableAccountPresenter iEnableAccountPresenter2) {
        this.iEnableAccountPresenter = iEnableAccountPresenter2;
    }

    private void activateAccount(DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel2) {
        final SingleLiveEvent<GenericResponse> InitiateEnableAccount = new InitiateAccountActivationDispatcher().InitiateEnableAccount(initiateEnableAccountDataModel2);
        InitiateEnableAccount.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                InitiateEnableAccount.removeObserver(this);
                if (InitiateEnableAccountPresenter.this.iEnableAccountPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        InitiateEnableAccountPresenter.this.iEnableAccountPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        InitiateEnableAccountPresenter.this.iEnableAccountPresenter.onEnableAccountOtpSendSuccessResponse(InitiateEnableAccountPresenter.this.initiateEnableAccountDataModel);
                    } else {
                        InitiateEnableAccountPresenter.this.iEnableAccountPresenter.onEnableAccountOtpSendFailureResponse((DisableAccountDataModels.InitiateAccountActivationFailureResponse) genericResponse.getErrorBodyOfType(DisableAccountDataModels.InitiateAccountActivationFailureResponse.class));
                    }
                }
            }
        });
    }

    public void validateEmailAndSendOTP(String str) {
        DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel2 = new DisableAccountDataModels.InitiateEnableAccountDataModel();
        this.initiateEnableAccountDataModel = initiateEnableAccountDataModel2;
        initiateEnableAccountDataModel2.email = str;
        this.initiateEnableAccountDataModel.requestOtpToEmail = true;
        if (str.isEmpty()) {
            this.iEnableAccountPresenter.fieldEmptyCallback();
        } else if (!ValidationUtils.isEmailAddressValid(str)) {
            this.iEnableAccountPresenter.invalidField();
        } else {
            this.iEnableAccountPresenter.fieldValidated(this.initiateEnableAccountDataModel);
            activateAccount(this.initiateEnableAccountDataModel);
        }
    }

    public void validateMobileNumberAndSendOTP(String str) {
        DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel2 = new DisableAccountDataModels.InitiateEnableAccountDataModel();
        this.initiateEnableAccountDataModel = initiateEnableAccountDataModel2;
        initiateEnableAccountDataModel2.mobileNumber = CountryCodeUIConfiguration.CURRENT.getCountryCodeString() + str;
        this.initiateEnableAccountDataModel.requestOtpToEmail = false;
        if (str.isEmpty()) {
            this.iEnableAccountPresenter.fieldEmptyCallback();
        } else if (!ValidationUtils.isMobileNumberValid(CountryCodeUIConfiguration.CURRENT.getCountryCodeString(), str)) {
            this.iEnableAccountPresenter.invalidField();
        } else {
            this.initiateEnableAccountDataModel.mobileNumber = ValidationUtils.formatMobileNumberFor0Prefix(CountryCodeUIConfiguration.CURRENT.getCountryCodeString(), str);
            this.iEnableAccountPresenter.fieldValidated(this.initiateEnableAccountDataModel);
            activateAccount(this.initiateEnableAccountDataModel);
        }
    }

    public void removeCallbacks() {
        this.iEnableAccountPresenter = null;
    }
}
