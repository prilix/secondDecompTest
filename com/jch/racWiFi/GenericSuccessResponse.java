package com.jch.racWiFi;

import com.google.gson.annotations.SerializedName;
import retrofit2.Response;

public class GenericSuccessResponse<T> {
    @SerializedName("body")
    public T body;
    @SerializedName("statusCode")
    public String statusCode;
    public int statusCodeValue;

    public void setStatusCodeValue(Response response) {
        this.statusCodeValue = response.code();
    }
}
