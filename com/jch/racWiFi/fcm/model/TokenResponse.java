package com.jch.racWiFi.fcm.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.module.base.BaseResponse;

public class TokenResponse extends BaseResponse {
    @SerializedName("body")
    private Body body;

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body2) {
        this.body = body2;
    }

    public static class Body {
        @SerializedName("message")
        private String message;
        @SerializedName("result")
        private String result;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public String getResult() {
            return this.result;
        }

        public void setResult(String str) {
            this.result = str;
        }
    }
}
