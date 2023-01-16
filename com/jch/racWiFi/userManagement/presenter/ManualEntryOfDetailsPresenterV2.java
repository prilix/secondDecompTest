package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.InitiateSigUpResponseModel;
import com.jch.racWiFi.userManagement.model.ResendOTPTokenData;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;
import com.jch.racWiFi.userManagement.network.UserRegistrationNetworkDispatcherV2;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ManualEntryOfDetailsPresenterV2 extends AbstractPresenter {
    /* access modifiers changed from: private */
    public ICreateAccountManualEntryOfDetailsPresenter iCreateAccountManualEntryOfDetailsPresenter;
    public boolean isFromManualEntrySignUp = false;

    public interface ICreateAccountManualEntryOfDetailsPresenter extends INetworkConnectivity {
        void accountExists();

        void allFieldsValidated(UserRegistrationModels.UserRegistration userRegistration);

        void emptyConfirmPasswordFieldCallback();

        void emptyEmailFieldCallback();

        void emptyPasswordFieldCallback();

        void emptyPhoneNumberFieldCallback();

        void inadequatePasswordStrength();

        void invalidEmailFormat();

        void invalidMobileNumberFormat();

        void onInitiateUserRegistrationFail(String str);

        void onInitiateUserRegistrationFailedErrorCode(GenericResponse genericResponse);

        void onInitiateUserRegistrationSuccess();

        void onPasswordStrengthChanged(ValidationUtils.PasswordStrength passwordStrength);

        void onRegistrationFailure(UserRegistrationModels.UserRegistrationFailureResponse userRegistrationFailureResponse);

        void onRegistrationSuccessful(UserRegistrationModels.UserRegistrationSuccessResponse userRegistrationSuccessResponse);

        void passwordAndConfirmPasswordMismatch();

        void termsAndConditionsNotAccepted();
    }

    public ManualEntryOfDetailsPresenterV2(ICreateAccountManualEntryOfDetailsPresenter iCreateAccountManualEntryOfDetailsPresenter2) {
        this.iCreateAccountManualEntryOfDetailsPresenter = iCreateAccountManualEntryOfDetailsPresenter2;
    }

    private boolean validateFields(String str, String str2, String str3, String str4, boolean z) {
        if (z) {
            if (str.isEmpty()) {
                this.iCreateAccountManualEntryOfDetailsPresenter.emptyEmailFieldCallback();
                return false;
            } else if (!ValidationUtils.isEmailAddressValid(str)) {
                this.iCreateAccountManualEntryOfDetailsPresenter.invalidEmailFormat();
                return false;
            }
        } else if (str2.isEmpty()) {
            this.iCreateAccountManualEntryOfDetailsPresenter.emptyPhoneNumberFieldCallback();
            return false;
        } else if (!ValidationUtils.isMobileNumberValid(CountryCodeUIConfiguration.CURRENT.getCountryCodeString(), str2)) {
            this.iCreateAccountManualEntryOfDetailsPresenter.invalidMobileNumberFormat();
            return false;
        }
        if (str3.isEmpty()) {
            this.iCreateAccountManualEntryOfDetailsPresenter.emptyPasswordFieldCallback();
            return false;
        } else if (str4.isEmpty()) {
            this.iCreateAccountManualEntryOfDetailsPresenter.emptyConfirmPasswordFieldCallback();
            return false;
        } else if (!str4.equals(str3)) {
            this.iCreateAccountManualEntryOfDetailsPresenter.passwordAndConfirmPasswordMismatch();
            return false;
        } else {
            if (z) {
                UserRegistrationModels.UserRegistration.NEW_USER.email = str;
                UserRegistrationModels.UserRegistration.NEW_USER.mobileNumber = null;
            } else {
                UserRegistrationModels.UserRegistration.NEW_USER.email = null;
                UserRegistrationModels.UserRegistration.NEW_USER.mobileNumber = ValidationUtils.formatMobileNumberFor0Prefix(CountryCodeUIConfiguration.CURRENT.getCountryCodeString(), str2);
            }
            UserRegistrationModels.UserRegistration.NEW_USER.password = str3;
            this.iCreateAccountManualEntryOfDetailsPresenter.allFieldsValidated(UserRegistrationModels.UserRegistration.NEW_USER);
            return true;
        }
    }

    public void validateAndInitiateRegisterUser(String str, String str2, String str3, String str4, boolean z) {
        if (validateFields(str, str2, str3, str4, z)) {
            this.isFromManualEntrySignUp = true;
            final SingleLiveEvent<GenericResponse> initiateRegisterUser = new UserRegistrationNetworkDispatcherV2().initiateRegisterUser(UserRegistrationModels.UserRegistration.NEW_USER);
            initiateRegisterUser.observeSingleEvent(new Observer<GenericResponse>() {
                public void onChanged(GenericResponse genericResponse) {
                    initiateRegisterUser.removeObserver(this);
                    if (ManualEntryOfDetailsPresenterV2.this.iCreateAccountManualEntryOfDetailsPresenter != null) {
                        if (genericResponse.unableToReachServer()) {
                            ManualEntryOfDetailsPresenterV2.this.iCreateAccountManualEntryOfDetailsPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                        } else if (genericResponse.getResponse().body() == null) {
                            ManualEntryOfDetailsPresenterV2.this.iCreateAccountManualEntryOfDetailsPresenter.onInitiateUserRegistrationFailedErrorCode(genericResponse);
                        } else if (genericResponse.getResponse().code() == 202) {
                            ManualEntryOfDetailsPresenterV2.this.iCreateAccountManualEntryOfDetailsPresenter.onInitiateUserRegistrationSuccess();
                        } else {
                            InitiateSigUpResponseModel initiateSigUpResponseModel = (InitiateSigUpResponseModel) genericResponse.getErrorBodyOfType(InitiateSigUpResponseModel.class);
                            if (initiateSigUpResponseModel != null) {
                                ManualEntryOfDetailsPresenterV2.this.iCreateAccountManualEntryOfDetailsPresenter.onInitiateUserRegistrationFail(initiateSigUpResponseModel.getMessage());
                            }
                        }
                    }
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserRegistrationSuccessResponse(UserRegistrationModels.UserRegistrationSuccessResponse userRegistrationSuccessResponse) {
        ResendOTPTokenData.CURRENT.copy(userRegistrationSuccessResponse.resendOTPTokenData);
        this.iCreateAccountManualEntryOfDetailsPresenter.onRegistrationSuccessful(userRegistrationSuccessResponse);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserRegistrationFailureResponse(UserRegistrationModels.UserRegistrationFailureResponse userRegistrationFailureResponse) {
        this.iCreateAccountManualEntryOfDetailsPresenter.onRegistrationFailure(userRegistrationFailureResponse);
    }

    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    public void removeCallbacks() {
        this.iCreateAccountManualEntryOfDetailsPresenter = null;
    }
}
