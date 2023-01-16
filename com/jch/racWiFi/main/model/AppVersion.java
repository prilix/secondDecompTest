package com.jch.racWiFi.main.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.module.base.BaseResponse;
import kotlin.Metadata;

@Metadata(mo36737d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, mo36738d2 = {"Lcom/jch/racWiFi/main/model/AppVersion;", "Lcom/jch/racWiFi/di/module/base/BaseResponse;", "()V", "body", "Lcom/jch/racWiFi/main/model/AppVersion$Body;", "getBody", "()Lcom/jch/racWiFi/main/model/AppVersion$Body;", "setBody", "(Lcom/jch/racWiFi/main/model/AppVersion$Body;)V", "Body", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: AppVersion.kt */
public final class AppVersion extends BaseResponse {
    @SerializedName("body")
    private Body body;

    public final Body getBody() {
        return this.body;
    }

    public final void setBody(Body body2) {
        this.body = body2;
    }

    @Metadata(mo36737d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, mo36738d2 = {"Lcom/jch/racWiFi/main/model/AppVersion$Body;", "", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "result", "Lcom/jch/racWiFi/main/model/AppVersion$Body$Result;", "getResult", "()Lcom/jch/racWiFi/main/model/AppVersion$Body$Result;", "setResult", "(Lcom/jch/racWiFi/main/model/AppVersion$Body$Result;)V", "Result", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: AppVersion.kt */
    public static final class Body {
        @SerializedName("message")
        private String message;
        @SerializedName("result")
        private Result result;

        public final String getMessage() {
            return this.message;
        }

        public final void setMessage(String str) {
            this.message = str;
        }

        public final Result getResult() {
            return this.result;
        }

        public final void setResult(Result result2) {
            this.result = result2;
        }

        @Metadata(mo36737d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, mo36738d2 = {"Lcom/jch/racWiFi/main/model/AppVersion$Body$Result;", "", "()V", "latestVersion", "", "getLatestVersion", "()Ljava/lang/String;", "setLatestVersion", "(Ljava/lang/String;)V", "minimumVersion", "getMinimumVersion", "setMinimumVersion", "storeLink", "getStoreLink", "setStoreLink", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
        /* compiled from: AppVersion.kt */
        public static final class Result {
            @SerializedName("latestVersion")
            private String latestVersion;
            @SerializedName("minimumVersion")
            private String minimumVersion;
            @SerializedName("storeLink")
            private String storeLink;

            public final String getLatestVersion() {
                return this.latestVersion;
            }

            public final void setLatestVersion(String str) {
                this.latestVersion = str;
            }

            public final String getStoreLink() {
                return this.storeLink;
            }

            public final void setStoreLink(String str) {
                this.storeLink = str;
            }

            public final String getMinimumVersion() {
                return this.minimumVersion;
            }

            public final void setMinimumVersion(String str) {
                this.minimumVersion = str;
            }
        }
    }
}
