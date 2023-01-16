package com.jch.racWiFi.userManagement.network.Api;

import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import com.jch.racWiFi.userManagement.model.dto.InitialAppConfigDto;
import com.jch.racWiFi.userManagement.model.dto.PermissionSaveDto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PermissionApi {
    @GET("/rac/ownership/groups/{memberId}/permissions")
    Call<AllPermissionDataDto> requestAllPermissionDetails(@Header("Authorization") String str, @Path("memberId") Integer num);

    @GET("/rac/config/init")
    Call<InitialAppConfigDto> requestConfigData(@Header("Authorization") String str);

    @GET("/rac/ownership/groups/{memberId}/permissions/{racId}")
    Call<AllPermissionDataDto> requestIduSpecificPermissionDetails(@Header("Authorization") String str, @Path("memberId") Integer num, @Path("racId") Integer num2);

    @PUT("/rac/ownership/groups/{memberId}/permissions")
    Call<ResponseBody> requestPermissionDetailsSave(@Header("Authorization") String str, @Body PermissionSaveDto permissionSaveDto, @Path("memberId") Integer num);
}
