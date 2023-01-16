package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.PasswordStrengthModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class PasswordStrengthCheckNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final String METHOD_CHECK_PASSWORD_STRENGTH = "/iam/account/password-strength";
    private SingleLiveEvent<GenericResponse> genericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface UpdateRoleApi {
        @POST("/iam/account/password-strength")
        Call<ResponseBody> checkPasswordStrength(@Body PasswordStrengthModel.PasswordStrengthRequestModel passwordStrengthRequestModel);
    }

    public SingleLiveEvent<GenericResponse> checkPasswordStrength(PasswordStrengthModel.PasswordStrengthRequestModel passwordStrengthRequestModel) {
        ((UpdateRoleApi) getRetrofitService().create(UpdateRoleApi.class)).checkPasswordStrength(passwordStrengthRequestModel).enqueue(this);
        return this.genericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.genericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.genericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
