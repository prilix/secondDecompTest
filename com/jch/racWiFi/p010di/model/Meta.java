package com.jch.racWiFi.p010di.model;

import com.google.gson.annotations.SerializedName;
import retrofit2.HttpException;

/* renamed from: com.jch.racWiFi.di.model.Meta */
public class Meta {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    private Throwable throwable;
    @SerializedName("token")
    private String token;
    @SerializedName("type")
    private String type;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setThrowable(Throwable th) {
        this.throwable = th;
    }

    public static Meta getMeta(String str, Throwable th) {
        Meta meta = new Meta();
        if (th instanceof HttpException) {
            HttpException httpException = (HttpException) th;
            meta.setCode(httpException.code());
            meta.setMessage(httpException.message());
        } else {
            meta.setCode(424);
            meta.setMessage(str);
        }
        meta.setThrowable(th);
        return meta;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
