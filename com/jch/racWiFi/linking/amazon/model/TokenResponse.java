package com.jch.racWiFi.linking.amazon.model;

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
        @SerializedName("amazon_token")
        private String amazonToken;

        public String getAmazonToken() {
            return this.amazonToken;
        }

        public void setAmazonToken(String str) {
            this.amazonToken = str;
        }
    }
}
