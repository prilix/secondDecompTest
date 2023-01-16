package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.ForgotPasswordDataModels;
import com.jch.racWiFi.userManagement.network.ForgotPasswordNetworkDispatcherWrapper;

public class ForgotPasswordStep2Presenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IForgotPasswordStep2Presenter iForgotPasswordStep2Presenter;
    private boolean isEmailAddress;
    /* access modifiers changed from: private */
    public ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData mForgotPasswordVerifyOTPRequestData;
    private ForgotPasswordDataModels.ForgotPasswordOTPRequestData modelData;

    public interface IForgotPasswordStep2Presenter extends INetworkConnectivity {
        void fieldEmptyCallback();

        void fieldValidated();

        void invalidField();

        void onOTPSendFailure();

        void onOTPSendSuccess();

        void otpVerificationFailed();

        void otpVerificationSuccess(ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData forgotPasswordVerifyOTPRequestData, String str);

        void userIDNotRegistered();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public ForgotPasswordStep2Presenter(IForgotPasswordStep2Presenter iForgotPasswordStep2Presenter2) {
        this.iForgotPasswordStep2Presenter = iForgotPasswordStep2Presenter2;
    }

    public void removeCallbacks() {
        this.iForgotPasswordStep2Presenter = null;
    }

    public void sendOtp(ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData) {
        final SingleLiveEvent<GenericResponse> requestOTPForgotPassword = new ForgotPasswordNetworkDispatcherWrapper.ForgotPasswordRequestOTP().requestOTPForgotPassword(forgotPasswordOTPRequestData);
        requestOTPForgotPassword.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                requestOTPForgotPassword.removeObserver(this);
                if (ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter.onNetworkCallFailure(genericResponse.getThrowable());
                        return;
                    }
                    ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter.onNetworkCallSuccess();
                    int code = genericResponse.getResponse().code();
                    if (genericResponse.isApiSuccessful()) {
                        ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter.onOTPSendSuccess();
                    } else if (code >= 500) {
                        ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter.serverException();
                    } else if (code == 404) {
                        ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter.userIDNotRegistered();
                    } else if (code == 400) {
                        ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter.onOTPSendFailure();
                    }
                }
            }
        });
    }

    public void verifyOTPEmail(String str, int i) {
        ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData forgotPasswordVerifyOTPRequestData = new ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData();
        this.mForgotPasswordVerifyOTPRequestData = forgotPasswordVerifyOTPRequestData;
        forgotPasswordVerifyOTPRequestData.email = str;
        this.mForgotPasswordVerifyOTPRequestData.OTP = i;
        verifyOTP();
    }

    public void verifyOTPMobileNumber(String str, int i) {
        ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData forgotPasswordVerifyOTPRequestData = new ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData();
        this.mForgotPasswordVerifyOTPRequestData = forgotPasswordVerifyOTPRequestData;
        forgotPasswordVerifyOTPRequestData.mobileNumber = str;
        this.mForgotPasswordVerifyOTPRequestData.OTP = i;
        verifyOTP();
    }

    private void verifyOTP() {
        final SingleLiveEvent<GenericResponse> verifyOTPForgotPassword = new ForgotPasswordNetworkDispatcherWrapper.ForgotPasswordVerifyOTPNetworkDispatcher().verifyOTPForgotPassword(this.mForgotPasswordVerifyOTPRequestData);
        verifyOTPForgotPassword.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                verifyOTPForgotPassword.removeObserver(this);
                if (ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        try {
                            ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter.otpVerificationSuccess(ForgotPasswordStep2Presenter.this.mForgotPasswordVerifyOTPRequestData, (String) genericResponse.parseJson().getValue("token"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (genericResponse.getResponse().code() == 400) {
                        ForgotPasswordStep2Presenter.this.iForgotPasswordStep2Presenter.otpVerificationFailed();
                    }
                }
            }
        });
    }

    public void setModel(ForgotPasswordDataModels.ForgotPasswordOTPRequestData forgotPasswordOTPRequestData) {
        this.modelData = forgotPasswordOTPRequestData;
    }
}
