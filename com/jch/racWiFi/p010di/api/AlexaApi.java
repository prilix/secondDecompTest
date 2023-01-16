package com.jch.racWiFi.p010di.api;

import com.jch.racWiFi.linking.amazon.model.Fallback;
import com.jch.racWiFi.linking.amazon.model.SkillRequest;
import com.jch.racWiFi.linking.amazon.model.SkillResponse;
import com.jch.racWiFi.linking.amazon.model.TokenRequest;
import com.jch.racWiFi.linking.amazon.model.TokenResponse;
import p012io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/* renamed from: com.jch.racWiFi.di.api.AlexaApi */
public interface AlexaApi {
    @POST("https://2mdvfgznxj.execute-api.us-east-1.amazonaws.com/dev/generatetoken")
    Flowable<TokenResponse> getAmazonToken(@Body TokenRequest tokenRequest);

    @GET("https://2mdvfgznxj.execute-api.us-east-1.amazonaws.com/dev/redirecturls?devicetype=Android")
    Flowable<Fallback> getFallback();

    @POST("https://2mdvfgznxj.execute-api.us-east-1.amazonaws.com/dev/skillenable")
    Flowable<SkillResponse> skillEnable(@Body SkillRequest skillRequest);
}
