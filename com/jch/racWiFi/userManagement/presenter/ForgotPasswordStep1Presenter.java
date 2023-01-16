package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.ForgotPasswordDataModels;
import com.jch.racWiFi.userManagement.network.ForgotPasswordNetworkDispatcherWrapper;

public class ForgotPasswordStep1Presenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IForgotPasswordStep1Presenter iForgotPasswordStep1Presenter;
    /* access modifiers changed from: private */
    public ForgotPasswordDataModels.ForgotPasswordOTPRequestData mForgotPasswordOTPRequestData;

    public interface IForgotPasswordStep1Presenter extends INetworkConnectivity {
        void fieldEmptyCallback();

        void fieldValidated(ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData);

        void invalidField();

        void onOTPSendFailure();

        void onOTPSendSuccess(ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData);

        void userIDNotRegistered();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public ForgotPasswordStep1Presenter(IForgotPasswordStep1Presenter iForgotPasswordStep1Presenter2) {
        this.iForgotPasswordStep1Presenter = iForgotPasswordStep1Presenter2;
    }

    public void removeCallbacks() {
        this.iForgotPasswordStep1Presenter = null;
    }

    public void validateEmailAndSendOTP(String str) {
        ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData = new ForgotPasswordDataModels.ForgotPasswordOTPRequestData();
        this.mForgotPasswordOTPRequestData = forgotPasswordOTPRequestData;
        forgotPasswordOTPRequestData.requestOtpToEmail = true;
        this.mForgotPasswordOTPRequestData.emailID = str;
        if (str.isEmpty()) {
            this.iForgotPasswordStep1Presenter.fieldEmptyCallback();
        } else if (!ValidationUtils.isEmailAddressValid(str)) {
            this.iForgotPasswordStep1Presenter.invalidField();
        } else {
            this.iForgotPasswordStep1Presenter.fieldValidated(this.mForgotPasswordOTPRequestData);
            requestOtp();
        }
    }

    public void validateMobileNumberAndSendOTP(String str) {
        ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData = new ForgotPasswordDataModels.ForgotPasswordOTPRequestData();
        this.mForgotPasswordOTPRequestData = forgotPasswordOTPRequestData;
        forgotPasswordOTPRequestData.requestOtpToEmail = false;
        ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData2 = this.mForgotPasswordOTPRequestData;
        forgotPasswordOTPRequestData2.mobileNumber = CountryCodeUIConfiguration.CURRENT.getCountryCodeString() + str;
        if (str.isEmpty()) {
            this.iForgotPasswordStep1Presenter.fieldEmptyCallback();
        } else if (!ValidationUtils.isMobileNumberValid(CountryCodeUIConfiguration.CURRENT.getCountryCodeString(), str)) {
            this.iForgotPasswordStep1Presenter.invalidField();
        } else {
            this.mForgotPasswordOTPRequestData.mobileNumber = ValidationUtils.formatMobileNumberFor0Prefix(CountryCodeUIConfiguration.CURRENT.getCountryCodeString(), str);
            this.iForgotPasswordStep1Presenter.fieldValidated(this.mForgotPasswordOTPRequestData);
            requestOtp();
        }
    }

    private void requestOtp() {
        final SingleLiveEvent<GenericResponse> requestOTPForgotPassword = new ForgotPasswordNetworkDispatcherWrapper.ForgotPasswordRequestOTP().requestOTPForgotPassword(this.mForgotPasswordOTPRequestData);
        requestOTPForgotPassword.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                requestOTPForgotPassword.removeObserver(this);
                if (ForgotPasswordStep1Presenter.this.iForgotPasswordStep1Presenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        ForgotPasswordStep1Presenter.this.iForgotPasswordStep1Presenter.onNetworkCallFailure(genericResponse.getThrowable());
                        return;
                    }
                    ForgotPasswordStep1Presenter.this.iForgotPasswordStep1Presenter.onNetworkCallSuccess();
                    int code = genericResponse.getResponse().code();
                    if (genericResponse.isApiSuccessful()) {
                        ForgotPasswordStep1Presenter.this.iForgotPasswordStep1Presenter.onOTPSendSuccess(ForgotPasswordStep1Presenter.this.mForgotPasswordOTPRequestData);
                    } else if (code >= 500) {
                        ForgotPasswordStep1Presenter.this.iForgotPasswordStep1Presenter.serverException();
                    } else if (code == 404) {
                        ForgotPasswordStep1Presenter.this.iForgotPasswordStep1Presenter.userIDNotRegistered();
                    } else if (code == 400) {
                        ForgotPasswordStep1Presenter.this.iForgotPasswordStep1Presenter.onOTPSendFailure();
                    }
                }
            }
        });
    }
}
