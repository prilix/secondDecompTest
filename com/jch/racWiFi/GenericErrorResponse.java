package com.jch.racWiFi;

import retrofit2.Response;

public class GenericErrorResponse<T> {
    public static final int SERVER_EXCEPTION = 500;
    public transient int statusCode;

    public void setStatusCodeValue(Response response) {
        this.statusCode = response.code();
    }
}
