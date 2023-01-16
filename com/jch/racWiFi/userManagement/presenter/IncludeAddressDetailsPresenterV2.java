package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.LifecycleOwner;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.ResendOTPTokenData;
import com.jch.racWiFi.userManagement.model.UserAddress;
import com.jch.racWiFi.userManagement.network.UpdateAddressNetworkDispatcherV2;

public class IncludeAddressDetailsPresenterV2 extends AbstractPresenter {
    private IIncludeAddressDetailsPresenter iIncludeAddressDetailsPresenter;
    private UserAddress mUserAddress;

    public interface IIncludeAddressDetailsPresenter extends INetworkConnectivity {
        void addressLine1EmptyCallback();

        void allFieldsValidated(UserAddress userAddress);

        void cityFieldEmptyCallback();

        void onAddressUpdateFailure(GenericResponse genericResponse);

        void onAddressUpdateSuccess(UserAddress userAddress);

        void stateFieldEmptyCallback();

        void streetAreaFieldEmptyCallback();

        void zipCodeFieldEmptyCallback();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public IncludeAddressDetailsPresenterV2(IIncludeAddressDetailsPresenter iIncludeAddressDetailsPresenter2) {
        this.iIncludeAddressDetailsPresenter = iIncludeAddressDetailsPresenter2;
    }

    public boolean validateFields(String str, String str2, String str3, String str4, String str5) {
        if (str.isEmpty() || str4.isEmpty() || str3.isEmpty()) {
            if (str.isEmpty()) {
                this.iIncludeAddressDetailsPresenter.addressLine1EmptyCallback();
            }
            if (str4.isEmpty()) {
                this.iIncludeAddressDetailsPresenter.stateFieldEmptyCallback();
            }
            if (!str3.isEmpty()) {
                return false;
            }
            this.iIncludeAddressDetailsPresenter.cityFieldEmptyCallback();
            return false;
        }
        UserAddress userAddress = new UserAddress();
        this.mUserAddress = userAddress;
        userAddress.setAddressLine(str);
        this.mUserAddress.setStreet(str2);
        this.mUserAddress.setCity(str3);
        this.mUserAddress.setState(str4);
        this.mUserAddress.setZipCode(str5);
        this.mUserAddress.setToken(ResendOTPTokenData.CURRENT.getResendOtpToken());
        this.iIncludeAddressDetailsPresenter.allFieldsValidated(this.mUserAddress);
        return true;
    }

    public void updateAddressOnServer(LifecycleOwner lifecycleOwner, String str, String str2) {
        UserAddress userAddress = this.mUserAddress;
        if (userAddress != null) {
            userAddress.setCountryCode(str);
            this.mUserAddress.setZipCode(str2);
            new UpdateAddressNetworkDispatcherV2().updateAddress(this.mUserAddress).observeSingleEvent(lifecycleOwner, new IncludeAddressDetailsPresenterV2$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: lambda$updateAddressOnServer$0$com-jch-racWiFi-userManagement-presenter-IncludeAddressDetailsPresenterV2 */
    public /* synthetic */ void mo32738x37057a7c(GenericResponse genericResponse) {
        if (this.iIncludeAddressDetailsPresenter != null) {
            if (genericResponse.unableToReachServer()) {
                this.iIncludeAddressDetailsPresenter.onNetworkCallFailure(genericResponse.getThrowable());
            } else if (genericResponse.isApiSuccessful()) {
                this.iIncludeAddressDetailsPresenter.onAddressUpdateSuccess(this.mUserAddress);
            } else {
                this.iIncludeAddressDetailsPresenter.onAddressUpdateFailure(genericResponse);
            }
        }
    }

    public void removeCallbacks() {
        this.iIncludeAddressDetailsPresenter = null;
    }
}
