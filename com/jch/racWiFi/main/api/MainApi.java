package com.jch.racWiFi.main.api;

import com.jch.racWiFi.amplitude.model.RequestBody;
import com.jch.racWiFi.amplitude.model.ResponseBody;
import com.jch.racWiFi.fcm.model.TokenRequest;
import com.jch.racWiFi.fcm.model.TokenResponse;
import com.jch.racWiFi.main.model.AppVersion;
import com.jch.racWiFi.main.model.CountryUnit;
import p012io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface MainApi extends BaseMainApi {
    @GET("/iam/management/app-version/v1/ANDROID")
    Flowable<AppVersion> appVersion();

    @PUT("/iam/user/device/de-register")
    Flowable<TokenResponse> deRegisterDevice(@Query("device-id") String str, @Header("Deregister-Token") String str2);

    @POST("/iam/app-settings/amplitude/status")
    Flowable<ResponseBody> getAmplitudeStatus(@Body RequestBody requestBody);

    @GET("/iam/app-settings/country-distance-measurement-unit")
    Flowable<CountryUnit> getCountryUnit(@Query("country-code") String str);

    @POST("/iam/user/device")
    Flowable<TokenResponse> registerDevice(@Body TokenRequest tokenRequest);
}
