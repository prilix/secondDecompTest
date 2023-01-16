package com.jch.racWiFi.p010di.module.base;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.model.Meta;

/* renamed from: com.jch.racWiFi.di.module.base.BaseResponse */
public class BaseResponse {
    @SerializedName("meta")
    private Meta meta;

    public Meta getMeta() {
        return this.meta;
    }

    public void setMeta(Meta meta2) {
        this.meta = meta2;
    }
}
