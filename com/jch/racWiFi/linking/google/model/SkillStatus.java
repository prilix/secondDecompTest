package com.jch.racWiFi.linking.google.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.module.base.BaseResponse;

public class SkillStatus extends BaseResponse {
    @SerializedName("body")
    private Body body;

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body2) {
        this.body = body2;
    }

    public static class Body {
        @SerializedName("skillenabled")
        private boolean skillEnabled;

        public boolean isSkillEnabled() {
            return this.skillEnabled;
        }

        public void setSkillEnabled(boolean z) {
            this.skillEnabled = z;
        }
    }
}
