package com.jch.racWiFi.userManagement.presenter;

import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;

public class EnterNameCreateAccountPresenterV2 extends AbstractPresenter {
    private IEnterNameCreateAccountPresenter iEnterNameCreateAccountPresenter;

    public interface IEnterNameCreateAccountPresenter {
        void allFieldsValidated(UserRegistrationModels.UserRegistration userRegistration);

        void firstNameFieldEmptyCallback();

        void lastNameFieldEmptyCallback();

        void middleNameFieldEmptyCallback();
    }

    public void registerEventBus() {
    }

    public void removeCallbacks() {
    }

    public void unregisterEventBus() {
    }

    public EnterNameCreateAccountPresenterV2(IEnterNameCreateAccountPresenter iEnterNameCreateAccountPresenter2) {
        this.iEnterNameCreateAccountPresenter = iEnterNameCreateAccountPresenter2;
    }

    public void validateFields(String str, String str2, String str3) {
        if (str.isEmpty()) {
            this.iEnterNameCreateAccountPresenter.firstNameFieldEmptyCallback();
        } else if (str3.isEmpty()) {
            this.iEnterNameCreateAccountPresenter.lastNameFieldEmptyCallback();
        } else {
            UserRegistrationModels.UserRegistration.NEW_USER.firstName = str;
            UserRegistrationModels.UserRegistration userRegistration = UserRegistrationModels.UserRegistration.NEW_USER;
            if (str2.isEmpty()) {
                str2 = null;
            }
            userRegistration.middleName = str2;
            UserRegistrationModels.UserRegistration.NEW_USER.lastName = str3;
            this.iEnterNameCreateAccountPresenter.allFieldsValidated(UserRegistrationModels.UserRegistration.NEW_USER);
        }
    }
}
