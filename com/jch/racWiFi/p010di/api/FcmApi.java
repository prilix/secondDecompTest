package com.jch.racWiFi.p010di.api;

import com.jch.racWiFi.fcm.model.FcmResponse;
import p012io.reactivex.Flowable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

/* renamed from: com.jch.racWiFi.di.api.FcmApi */
public interface FcmApi {
    @DELETE("/iam/user/notifications")
    Flowable<FcmResponse> clearAllNotification(@Query("notification-category") String str);

    @DELETE("/iam/user/notifications")
    Flowable<FcmResponse> clearNotification(@Query("notification-id") String str);

    @GET("/iam/user/notifications")
    Flowable<FcmResponse> getNotifications(@Query("familyId") int i);
}
