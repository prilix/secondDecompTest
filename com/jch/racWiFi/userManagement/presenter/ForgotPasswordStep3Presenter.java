package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.ForgotPasswordDataModels;
import com.jch.racWiFi.userManagement.network.ForgotPasswordNetworkDispatcherWrapper;

public class ForgotPasswordStep3Presenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IForgotPasswordStep3Presenter iForgotPasswordStep3Presenter;
    private boolean isEmailAddress;
    private ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData modelData;

    public interface IForgotPasswordStep3Presenter extends INetworkConnectivity {
        void allFieldValidated();

        void confirmPasswordFieldEmptyCallback();

        void confirmPasswordMismatch();

        void onPasswordResetFailure();

        void onPasswordResetSuccess();

        void onPasswordStrengthChanged(ValidationUtils.PasswordStrength passwordStrength);

        void passwordComplexityNotMet();

        void passwordFieldEmptyCallback();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public ForgotPasswordStep3Presenter(IForgotPasswordStep3Presenter iForgotPasswordStep3Presenter2) {
        this.iForgotPasswordStep3Presenter = iForgotPasswordStep3Presenter2;
    }

    public void removeCallbacks() {
        this.iForgotPasswordStep3Presenter = null;
    }

    public void validateFields(String str, String str2, String str3) {
        if (str.isEmpty()) {
            this.iForgotPasswordStep3Presenter.passwordFieldEmptyCallback();
        } else if (str2.isEmpty()) {
            this.iForgotPasswordStep3Presenter.confirmPasswordFieldEmptyCallback();
        } else if (!str.equals(str2)) {
            this.iForgotPasswordStep3Presenter.confirmPasswordMismatch();
        } else {
            this.iForgotPasswordStep3Presenter.allFieldValidated();
            ForgotPasswordDataModels.ForgotPasswordUpdateRequestData forgotPasswordUpdateRequestData = new ForgotPasswordDataModels.ForgotPasswordUpdateRequestData();
            forgotPasswordUpdateRequestData.email = this.modelData.email;
            forgotPasswordUpdateRequestData.mobileNumber = this.modelData.mobileNumber;
            forgotPasswordUpdateRequestData.newPassword = str;
            forgotPasswordUpdateRequestData.token = str3;
            final SingleLiveEvent<GenericResponse> updatePassword = new ForgotPasswordNetworkDispatcherWrapper.UpdatePasswordNetworkDispatcher().updatePassword(forgotPasswordUpdateRequestData);
            updatePassword.observeSingleEvent(new Observer<GenericResponse>() {
                public void onChanged(GenericResponse genericResponse) {
                    updatePassword.removeObserver(this);
                    if (ForgotPasswordStep3Presenter.this.iForgotPasswordStep3Presenter != null) {
                        if (genericResponse.unableToReachServer()) {
                            ForgotPasswordStep3Presenter.this.iForgotPasswordStep3Presenter.onNetworkCallFailure(genericResponse.getThrowable());
                        } else if (genericResponse.isApiSuccessful()) {
                            ForgotPasswordStep3Presenter.this.iForgotPasswordStep3Presenter.onPasswordResetSuccess();
                        } else {
                            ForgotPasswordStep3Presenter.this.iForgotPasswordStep3Presenter.onPasswordResetFailure();
                        }
                    }
                }
            });
        }
    }

    public void setModel(ForgotPasswordDataModels.ForgotPasswordVerifyOTPRequestData forgotPasswordVerifyOTPRequestData) {
        this.modelData = forgotPasswordVerifyOTPRequestData;
    }
}
