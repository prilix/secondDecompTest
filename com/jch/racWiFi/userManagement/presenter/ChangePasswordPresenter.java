package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.ChangePasswordModels;
import com.jch.racWiFi.userManagement.network.ChangePasswordNetworkDispatcher;
import com.jch.racWiFi.userManagement.view.ChangePasswordFragment;

public class ChangePasswordPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IChangePasswordPresenter iChangePasswordPresenter;
    private ChangePasswordModels.ChangePasswordData mChangePasswordData;

    public interface IChangePasswordPresenter extends INetworkConnectivity {
        void allFieldsValidated(ChangePasswordModels.ChangePasswordData changePasswordData);

        void confirmNewPasswordEmptyCallback();

        void currentPasswordFieldEmptyCallback();

        void newPasswordAndOldPasswordCannotBeSame();

        void newPasswordFieldEmptyCallback();

        void onChangePasswordFailure(int i);

        void onChangePasswordSuccess();

        void passwordMismatch();

        void passwordStrengthInadequate();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public ChangePasswordPresenter(IChangePasswordPresenter iChangePasswordPresenter2) {
        this.iChangePasswordPresenter = iChangePasswordPresenter2;
    }

    public void validateFields(String str, String str2, String str3) {
        this.mChangePasswordData = new ChangePasswordModels.ChangePasswordData();
        if (str.isEmpty()) {
            this.iChangePasswordPresenter.currentPasswordFieldEmptyCallback();
        } else if (str2.isEmpty()) {
            this.iChangePasswordPresenter.newPasswordFieldEmptyCallback();
        } else if (str3.isEmpty()) {
            this.iChangePasswordPresenter.confirmNewPasswordEmptyCallback();
        } else if (str.equals(str3) || str.equals(str2)) {
            this.iChangePasswordPresenter.newPasswordAndOldPasswordCannotBeSame();
        } else {
            this.mChangePasswordData.oldPassword = str;
            this.mChangePasswordData.newPassword = str2;
            this.mChangePasswordData.confirmPassword = str3;
            if (!this.mChangePasswordData.newPassword.equals(this.mChangePasswordData.confirmPassword)) {
                this.iChangePasswordPresenter.passwordMismatch();
                return;
            }
            this.iChangePasswordPresenter.allFieldsValidated(this.mChangePasswordData);
            ChangePasswordFragment.isPasswordChanged = true;
            final SingleLiveEvent<GenericResponse> changePassword = new ChangePasswordNetworkDispatcher().changePassword(this.mChangePasswordData);
            changePassword.observeSingleEvent(new Observer<GenericResponse>() {
                public void onChanged(GenericResponse genericResponse) {
                    changePassword.removeObserver(this);
                    if (ChangePasswordPresenter.this.iChangePasswordPresenter == null) {
                        ChangePasswordFragment.isPasswordChanged = false;
                    } else if (genericResponse.unableToReachServer()) {
                        ChangePasswordFragment.isPasswordChanged = false;
                        ChangePasswordPresenter.this.iChangePasswordPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        ChangePasswordPresenter.this.iChangePasswordPresenter.onChangePasswordSuccess();
                    } else {
                        ChangePasswordFragment.isPasswordChanged = false;
                        ChangePasswordPresenter.this.iChangePasswordPresenter.onChangePasswordFailure(genericResponse.getResponse().code());
                    }
                }
            });
        }
    }

    public void removeCallbacks() {
        this.iChangePasswordPresenter = null;
    }
}
