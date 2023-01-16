package com.jch.racWiFi.p010di.api;

import com.jch.racWiFi.linking.google.model.SkillStatus;
import p012io.reactivex.Flowable;
import retrofit2.http.GET;

/* renamed from: com.jch.racWiFi.di.api.AppFlipApi */
public interface AppFlipApi {
    @GET("https://googlesmarthome-dev-global.aircloudhome.com/googlesmarthomedetails")
    Flowable<SkillStatus> getSkillStatus();
}
