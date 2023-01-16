package com.jch.racWiFi.userManagement.model;

import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;
import java.util.ArrayList;
import java.util.List;

public class PasswordStrengthModel {

    public static class PasswordStrengthResponseModel {
        public String code;
        public String message;
        public String strength;
        public List<String> suggestions = new ArrayList();
    }

    public static class PasswordStrengthRequestModel {
        public String emailId;
        public String mobileNumber;
        public String password;
        public List<String> sanityInputs = new ArrayList();

        public void updateModelBasedOnUserInfo(UserInfo userInfo) {
            this.sanityInputs.add(userInfo.firstName);
            this.sanityInputs.add(userInfo.lastName);
            if (userInfo.middleName != null && userInfo.middleName.isEmpty()) {
                this.sanityInputs.add(userInfo.middleName);
            }
            if (userInfo.email != null) {
                this.sanityInputs.add(userInfo.email);
            }
            if (userInfo.phoneNumber != null) {
                this.sanityInputs.add(userInfo.phoneNumber);
            }
            if (userInfo.userAddress != null) {
                this.sanityInputs.add(userInfo.userAddress.getZipCode());
            }
        }

        public void updateModelBasedOnUserRegistrationData(UserRegistrationModels.UserRegistration userRegistration) {
            this.sanityInputs.add(userRegistration.firstName);
            if (userRegistration.middleName != null && userRegistration.middleName.isEmpty()) {
                this.sanityInputs.add(userRegistration.middleName);
            }
            this.sanityInputs.add(userRegistration.lastName);
            if (userRegistration.middleName != null && userRegistration.middleName.isEmpty()) {
                this.sanityInputs.add(userRegistration.middleName);
            }
            if (userRegistration.email != null) {
                this.sanityInputs.add(userRegistration.email);
            }
            if (userRegistration.mobileNumber != null) {
                this.sanityInputs.add(userRegistration.mobileNumber);
            }
        }
    }
}
