package com.jch.racWiFi.main.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.module.base.BaseResponse;

public class RefreshTokenResponse extends BaseResponse {
    @SerializedName("body")
    private Body body;

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body2) {
        this.body = body2;
    }

    public static class Body {
        @SerializedName("errorState")
        private String errorState;
        @SerializedName("newUser")
        private boolean newUser;
        @SerializedName("refreshToken")
        private String refreshToken;
        @SerializedName("token")
        private String token;

        public String getToken() {
            return this.token;
        }

        public void setToken(String str) {
            this.token = str;
        }

        public String getRefreshToken() {
            return this.refreshToken;
        }

        public void setRefreshToken(String str) {
            this.refreshToken = str;
        }

        public boolean isNewUser() {
            return this.newUser;
        }

        public void setNewUser(boolean z) {
            this.newUser = z;
        }

        public String getErrorState() {
            return this.errorState;
        }

        public void setErrorState(String str) {
            this.errorState = str;
        }
    }
}
