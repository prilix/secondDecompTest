package com.jch.racWiFi.linking.amazon.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.module.base.BaseResponse;

public class Fallback extends BaseResponse {
    @SerializedName("body")
    private Body body;

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body2) {
        this.body = body2;
    }

    public static class Body {
        @SerializedName("lwa_fallback_url")
        private String lwaFallbackUrl;

        public String getLwaFallbackUrl() {
            return this.lwaFallbackUrl;
        }

        public void setLwaFallbackUrl(String str) {
            this.lwaFallbackUrl = str;
        }
    }
}
