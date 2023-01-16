package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.p010di.util.TokenInfo;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.UserLoginDataModel;
import com.jch.racWiFi.userManagement.network.CreateFamilyInstanceNetworkDispatcher;
import com.jch.racWiFi.userManagement.network.UserLoginNetworkDispatcherV2;

public class UserLoginPresenterV2 extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IUserLoginPresenter iUserLoginPresenter;

    public interface IUserLoginPresenter extends INetworkConnectivity {
        void allFieldsValidatedLogin();

        void emptyEmailFieldCallback();

        void emptyPasswordFieldCallback();

        void emptyPhoneNumberFieldCallback();

        void invalidEmailFormat();

        void invalidMobileNumberFormat();

        void onHardLockHappenedFromServer();

        void onInActiveUserFailureResponse();

        void onLoginApiInitiate(boolean z);

        void onLoginPasswordIncorrect(UserLoginDataModel.LoginFailureResponse loginFailureResponse);

        void onLoginSuccessful();

        void onLoginUserNotVerified();

        void onNetworkCallFailure(Throwable th);

        void onSoftLockHappenedFromServer();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public UserLoginPresenterV2(IUserLoginPresenter iUserLoginPresenter2) {
        this.iUserLoginPresenter = iUserLoginPresenter2;
    }

    public void authenticateUser(String str, String str2, final boolean z) {
        if (z) {
            if (str.isEmpty()) {
                this.iUserLoginPresenter.emptyEmailFieldCallback();
                return;
            } else if (!ValidationUtils.isEmailAddressValid(str)) {
                this.iUserLoginPresenter.invalidEmailFormat();
                return;
            }
        } else if (str.isEmpty()) {
            this.iUserLoginPresenter.emptyPhoneNumberFieldCallback();
            return;
        } else if (!ValidationUtils.isMobileNumberValid(CountryCodeUIConfiguration.CURRENT.getCountryCodeString(), str)) {
            this.iUserLoginPresenter.invalidMobileNumberFormat();
            return;
        }
        if (str2.isEmpty()) {
            this.iUserLoginPresenter.emptyPasswordFieldCallback();
            return;
        }
        this.iUserLoginPresenter.allFieldsValidatedLogin();
        UserLoginDataModel.LoginData loginData = new UserLoginDataModel.LoginData();
        if (z) {
            loginData.email = str;
        } else {
            loginData.mobileNumber = ValidationUtils.formatMobileNumberFor0Prefix(CountryCodeUIConfiguration.CURRENT.getCountryCodeString(), str);
        }
        loginData.password = str2;
        final SingleLiveEvent<GenericResponse> loginUser = new UserLoginNetworkDispatcherV2().loginUser(loginData);
        loginUser.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                loginUser.removeObserver(this);
                if (UserLoginPresenterV2.this.iUserLoginPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        UserLoginPresenterV2.this.iUserLoginPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        UserLoginPresenterV2.this.iUserLoginPresenter.onLoginApiInitiate(z);
                        TokenInfo tokenInfo = (TokenInfo) genericResponse.getBodyOfType(TokenInfo.class);
                        if (tokenInfo != null) {
                            TokenUtil.getInstance().persist(tokenInfo);
                            if (tokenInfo.isNew()) {
                                new CreateFamilyInstanceNetworkDispatcher().createFamilyInstanceOnServer();
                            }
                            UserLoginPresenterV2.this.iUserLoginPresenter.onLoginSuccessful();
                        }
                    } else {
                        int code = genericResponse.getResponse().code();
                        if (code == 403) {
                            UserLoginPresenterV2.this.iUserLoginPresenter.onInActiveUserFailureResponse();
                            return;
                        }
                        UserLoginDataModel.LoginFailureResponse loginFailureResponse = (UserLoginDataModel.LoginFailureResponse) genericResponse.getErrorBodyOfType(UserLoginDataModel.LoginFailureResponse.class);
                        if (loginFailureResponse == null) {
                            return;
                        }
                        if (code == 401) {
                            UserLoginPresenterV2.this.iUserLoginPresenter.onLoginPasswordIncorrect(loginFailureResponse);
                        } else if (code == 412) {
                            UserLoginPresenterV2.this.iUserLoginPresenter.onLoginUserNotVerified();
                        } else if (code != 423) {
                            if (code == 500) {
                                UserLoginPresenterV2.this.iUserLoginPresenter.serverException();
                            }
                        } else if (loginFailureResponse.errorState.equals(UserLoginDataModel.LoginFailureResponse.SOFT_LOCK)) {
                            UserLoginPresenterV2.this.iUserLoginPresenter.onSoftLockHappenedFromServer();
                        } else if (loginFailureResponse.errorState.equals(UserLoginDataModel.LoginFailureResponse.HARD_LOCK)) {
                            UserLoginPresenterV2.this.iUserLoginPresenter.onHardLockHappenedFromServer();
                        }
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iUserLoginPresenter = null;
    }
}
