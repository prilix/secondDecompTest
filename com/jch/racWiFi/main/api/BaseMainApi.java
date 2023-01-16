package com.jch.racWiFi.main.api;

import com.jch.racWiFi.main.model.RefreshTokenResponse;
import p012io.reactivex.Flowable;
import retrofit2.http.POST;

public interface BaseMainApi {
    @POST("/iam/auth/refresh-token")
    Flowable<RefreshTokenResponse> refreshToken();
}
