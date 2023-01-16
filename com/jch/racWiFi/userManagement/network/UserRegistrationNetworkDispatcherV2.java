package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class UserRegistrationNetworkDispatcherV2 extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_INITIATE_REGISTER_USER = "/iam/account/initiate-sign-up";
    public static final String METHOD_REGISTER_USER = "/iam/account/sign-up/v2";
    private boolean isFromInitiateRegisterUser = false;
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface UserRegistrationApi {
        @POST("/iam/account/initiate-sign-up")
        Call<ResponseBody> initiateRegisterUser(@Query("language") String str, @Body UserRegistrationModels.UserRegistration userRegistration);

        @POST("/iam/account/sign-up/v2")
        Call<ResponseBody> registerUser(@Body UserRegistrationModels.UserRegistration userRegistration);
    }

    public SingleLiveEvent<GenericResponse> initiateRegisterUser(UserRegistrationModels.UserRegistration userRegistration) {
        this.isFromInitiateRegisterUser = true;
        ((UserRegistrationApi) getRetrofitService().create(UserRegistrationApi.class)).initiateRegisterUser(LocaleConfiguration.getLanguageCodeForCurrentLocale(), userRegistration).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> registerUser(UserRegistrationModels.UserRegistration userRegistration) {
        this.isFromInitiateRegisterUser = false;
        ((UserRegistrationApi) getRetrofitService().create(UserRegistrationApi.class)).registerUser(userRegistration).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
