package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.UserLoginDataModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class UserLoginNetworkDispatcherV2 extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_CREATE_FAMILY_INSTANCE = "/iam/auth/family-account/create-new";
    public static final String METHOD_LOGIN = "/iam/auth/sign-in";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface UserLoginApi {
        @POST("/iam/auth/sign-in")
        Call<ResponseBody> authenticateUser(@Body UserLoginDataModel.LoginData loginData);
    }

    public SingleLiveEvent<GenericResponse> loginUser(UserLoginDataModel.LoginData loginData) {
        ((UserLoginApi) getRetrofitService().create(UserLoginApi.class)).authenticateUser(loginData).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(GenericResponse.getSuccessGenericResponse(response));
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(GenericResponse.getFailureGenericResponse(th));
    }
}
