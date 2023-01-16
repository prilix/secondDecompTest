package com.jch.racWiFi.linking.amazon.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.module.base.BaseResponse;

public class SkillResponse extends BaseResponse {
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

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }
    }
}
