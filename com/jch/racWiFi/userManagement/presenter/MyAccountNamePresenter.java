package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.UpdateNameModels;
import com.jch.racWiFi.userManagement.network.UserAccountInfoNetworkDispatcherWrapper;

public class MyAccountNamePresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IMyAccountNamePresenter iMyAccountNamePresenter;
    /* access modifiers changed from: private */
    public UpdateNameModels.UpdateName mUpdateName;

    public interface IMyAccountNamePresenter extends INetworkConnectivity {
        void allFieldsValidated(UpdateNameModels.UpdateName updateName);

        void firstNameFieldEmptyCallback();

        void lastNameFieldEmptyCallback();

        void onUpdateNameFailure(GenericResponse genericResponse);

        void onUpdateNameSuccess(UpdateNameModels.UpdateName updateName);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public MyAccountNamePresenter(IMyAccountNamePresenter iMyAccountNamePresenter2) {
        this.iMyAccountNamePresenter = iMyAccountNamePresenter2;
    }

    public boolean validateFields(String str, String str2, String str3) {
        if (str.isEmpty()) {
            this.iMyAccountNamePresenter.firstNameFieldEmptyCallback();
            return false;
        } else if (str3.isEmpty()) {
            this.iMyAccountNamePresenter.lastNameFieldEmptyCallback();
            return false;
        } else {
            UpdateNameModels.UpdateName updateName = new UpdateNameModels.UpdateName();
            this.mUpdateName = updateName;
            updateName.firstName = str;
            UpdateNameModels.UpdateName updateName2 = this.mUpdateName;
            if (str2.isEmpty()) {
                str2 = null;
            }
            updateName2.middleName = str2;
            this.mUpdateName.lastName = str3;
            this.iMyAccountNamePresenter.allFieldsValidated(this.mUpdateName);
            return true;
        }
    }

    public void updateNameOnServer(LifecycleOwner lifecycleOwner) {
        final SingleLiveEvent<GenericResponse> updateUserName = new UserAccountInfoNetworkDispatcherWrapper.UpdateUserNameNetworkDispatcher().updateUserName(this.mUpdateName);
        updateUserName.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                updateUserName.removeObserver(this);
                if (MyAccountNamePresenter.this.iMyAccountNamePresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        MyAccountNamePresenter.this.iMyAccountNamePresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        MyAccountNamePresenter.this.iMyAccountNamePresenter.onUpdateNameSuccess(MyAccountNamePresenter.this.mUpdateName);
                    } else {
                        MyAccountNamePresenter.this.iMyAccountNamePresenter.onUpdateNameFailure(genericResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iMyAccountNamePresenter = null;
    }
}
