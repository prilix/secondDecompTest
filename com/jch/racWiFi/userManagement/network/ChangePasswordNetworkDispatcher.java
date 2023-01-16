package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.ChangePasswordModels;
import com.jch.racWiFi.userManagement.view.ChangePasswordFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class ChangePasswordNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_CHANGE_PASSWORD = "/iam/account/change-password/v2";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface UserNameRequestApi {
        @POST("/iam/account/change-password/v2")
        Call<ResponseBody> changePassword(@Body ChangePasswordModels.ChangePasswordData changePasswordData);
    }

    public SingleLiveEvent<GenericResponse> changePassword(ChangePasswordModels.ChangePasswordData changePasswordData) {
        ChangePasswordFragment.isPasswordChanged = true;
        ((UserNameRequestApi) getRetrofitService().create(UserNameRequestApi.class)).changePassword(changePasswordData).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(GenericResponse.getSuccessGenericResponse(response));
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        GenericResponse failureGenericResponse = GenericResponse.getFailureGenericResponse(th);
        ChangePasswordFragment.isPasswordChanged = false;
        this.mGenericResponseSingleLiveEvent.postValue(failureGenericResponse);
    }
}
