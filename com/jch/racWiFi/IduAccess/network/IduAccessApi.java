package com.jch.racWiFi.IduAccess.network;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IduAccessApi {
    @GET("/rac/ownership/groups/{memberId}/permissions/has-functional-access")
    Call<HashMap<String, Boolean>> fetch(@Header("Authorization") String str, @Path("memberId") Integer num);
}
