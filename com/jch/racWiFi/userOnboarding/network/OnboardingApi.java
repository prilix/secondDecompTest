package com.jch.racWiFi.userOnboarding.network;

import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoRequestBody;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoResponseBody;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch.racWiFi.userOnboarding.model.OnBoardingModel;
import com.jch.racWiFi.userOnboarding.model.RenamingDto;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OnboardingApi {
    @POST("/rac/on-board/idu/info ")
    Call<OnboardingInfoResponseBody> onBoardedIduInfo(@Header("Authorization") String str, @Body OnboardingInfoRequestBody onboardingInfoRequestBody);

    @GET("/rac/ownership/groups/{family-id}/idu-list")
    Call<List<IduDetailsModel>> requestIduDetails(@Path("family-id") int i, @Header("Authorization") String str);

    @DELETE("/rac/manage-idu/groups/{family-id}/idu-list/{racId}")
    Call<ResponseBody> requestIduRemoval(@Header("Authorization") String str, @Path("family-id") int i, @Path("racId") Long l);

    @PUT("/rac/manage-idu/update/{racId}")
    Call<ResponseBody> requestIduRenaming(@Header("Authorization") String str, @Path("racId") Long l, @Body RenamingDto renamingDto);

    @POST("/rac/on-board/groups/{family-id}/idu/india  ")
    Call<IduDetailsModel> requestIndiaOnboarding(@Path("family-id") int i, @Header("Authorization") String str, @Body OnBoardingModel onBoardingModel);

    @POST("/rac/on-board/groups/{family-id}/idu")
    Call<IduDetailsModel> requestOnboarding(@Path("family-id") int i, @Header("Authorization") String str, @Body OnBoardingModel onBoardingModel);

    @POST("/rac/manage-idu/reset")
    Call<ResponseBody> resetIdu(@Query("vendorThingId") String str, @Query("familyId") int i);
}
