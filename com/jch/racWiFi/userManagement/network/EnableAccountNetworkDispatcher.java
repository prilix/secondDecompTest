package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.DisableAccountDataModels;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class EnableAccountNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String INITIATE_ACTIVATE_ACCOUNT = "/iam/auth/account/enable";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    interface EnableAccountAPI {
        @POST("/iam/auth/account/enable")
        Call<ResponseBody> enableAccount(@Body DisableAccountDataModels.EnableAccountDataModel enableAccountDataModel);
    }

    public SingleLiveEvent<GenericResponse> enableAccount(DisableAccountDataModels.EnableAccountDataModel enableAccountDataModel) {
        ((EnableAccountAPI) getRetrofitService().create(EnableAccountAPI.class)).enableAccount(enableAccountDataModel).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
