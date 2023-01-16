package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.InitiateSigUpResponseModel;
import com.jch.racWiFi.userManagement.model.ResendOTPTokenData;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;
import com.jch.racWiFi.userManagement.network.UserRegistrationNetworkDispatcherV2;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class AccountVerificationWithOTPPresenterV2 extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IAccountVerificationWithOTPPresenter iAccountVerificationWithOTPPresenter;

    public interface IAccountVerificationWithOTPPresenter extends INetworkConnectivity {
        void onReSendOtpFail(String str);

        void onReSendOtpSuccess(InitiateSigUpResponseModel initiateSigUpResponseModel);

        void onRegistrationFailed();

        void onRegistrationFailedErrorCode(Response<ResponseBody> response);

        void onRegistrationFailedMessage(String str);

        void onRegistrationSuccessful();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public AccountVerificationWithOTPPresenterV2(IAccountVerificationWithOTPPresenter iAccountVerificationWithOTPPresenter2) {
        this.iAccountVerificationWithOTPPresenter = iAccountVerificationWithOTPPresenter2;
    }

    public void removeCallbacks() {
        this.iAccountVerificationWithOTPPresenter = null;
    }

    public void resendOtp() {
        final SingleLiveEvent<GenericResponse> initiateRegisterUser = new UserRegistrationNetworkDispatcherV2().initiateRegisterUser(UserRegistrationModels.UserRegistration.NEW_USER);
        initiateRegisterUser.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                initiateRegisterUser.removeObserver(this);
                if (AccountVerificationWithOTPPresenterV2.this.iAccountVerificationWithOTPPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        AccountVerificationWithOTPPresenterV2.this.iAccountVerificationWithOTPPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.getResponse().body() == null) {
                    } else {
                        if (genericResponse.getResponse().code() == 202) {
                            InitiateSigUpResponseModel initiateSigUpResponseModel = (InitiateSigUpResponseModel) genericResponse.getBodyOfType(InitiateSigUpResponseModel.class);
                            if (initiateSigUpResponseModel != null) {
                                AccountVerificationWithOTPPresenterV2.this.iAccountVerificationWithOTPPresenter.onReSendOtpSuccess(initiateSigUpResponseModel);
                                return;
                            }
                            return;
                        }
                        InitiateSigUpResponseModel initiateSigUpResponseModel2 = (InitiateSigUpResponseModel) genericResponse.getErrorBodyOfType(InitiateSigUpResponseModel.class);
                        if (initiateSigUpResponseModel2 != null) {
                            AccountVerificationWithOTPPresenterV2.this.iAccountVerificationWithOTPPresenter.onReSendOtpFail(initiateSigUpResponseModel2.getMessage());
                        }
                    }
                }
            }
        });
    }

    public void registerUserWithOtp(String str) {
        UserRegistrationModels.UserRegistration.NEW_USER.mOtp = str;
        final SingleLiveEvent<GenericResponse> registerUser = new UserRegistrationNetworkDispatcherV2().registerUser(UserRegistrationModels.UserRegistration.NEW_USER);
        registerUser.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                registerUser.removeObserver(this);
                if (genericResponse.unableToReachServer()) {
                    AccountVerificationWithOTPPresenterV2.this.iAccountVerificationWithOTPPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                } else if (genericResponse.getResponse().body() == null) {
                    AccountVerificationWithOTPPresenterV2.this.iAccountVerificationWithOTPPresenter.onRegistrationFailedErrorCode(genericResponse.getResponse());
                } else if (genericResponse.getResponse().code() == 201) {
                    ResendOTPTokenData resendOTPTokenData = (ResendOTPTokenData) genericResponse.getBodyOfType(ResendOTPTokenData.class);
                    if (resendOTPTokenData != null) {
                        ResendOTPTokenData.CURRENT.setResendOtpToken(resendOTPTokenData.getResendOtpToken());
                        AccountVerificationWithOTPPresenterV2.this.iAccountVerificationWithOTPPresenter.onRegistrationSuccessful();
                    }
                } else {
                    AccountVerificationWithOTPPresenterV2.this.iAccountVerificationWithOTPPresenter.onRegistrationFailed();
                }
            }
        });
    }
}
