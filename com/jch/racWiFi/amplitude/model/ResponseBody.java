package com.jch.racWiFi.amplitude.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.module.base.BaseResponse;
import kotlin.Metadata;

@Metadata(mo36737d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, mo36738d2 = {"Lcom/jch/racWiFi/amplitude/model/ResponseBody;", "Lcom/jch/racWiFi/di/module/base/BaseResponse;", "()V", "body", "Lcom/jch/racWiFi/amplitude/model/ResponseBody$Body;", "getBody", "()Lcom/jch/racWiFi/amplitude/model/ResponseBody$Body;", "setBody", "(Lcom/jch/racWiFi/amplitude/model/ResponseBody$Body;)V", "Body", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: ResponseBody.kt */
public final class ResponseBody extends BaseResponse {
    @SerializedName("body")
    private Body body;

    public final Body getBody() {
        return this.body;
    }

    public final void setBody(Body body2) {
        this.body = body2;
    }

    @Metadata(mo36737d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u0010\n\u0002\u0010\b\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\t"}, mo36738d2 = {"Lcom/jch/racWiFi/amplitude/model/ResponseBody$Body;", "", "()V", "isEnabled", "", "()Ljava/lang/Boolean;", "setEnabled", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: ResponseBody.kt */
    public static final class Body {
        @SerializedName("isEnabled")
        private Boolean isEnabled = false;

        public final Boolean isEnabled() {
            return this.isEnabled;
        }

        public final void setEnabled(Boolean bool) {
            this.isEnabled = bool;
        }
    }
}
