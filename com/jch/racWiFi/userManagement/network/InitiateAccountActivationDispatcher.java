package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.DisableAccountDataModels;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class InitiateAccountActivationDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String INITIATE_ACTIVATE_ACCOUNT = "/iam/account/initiate-enable-account";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    interface EnableAccountAPI {
        @POST("/iam/account/initiate-enable-account")
        Call<ResponseBody> enableAccount(@Query("language") String str, @Body DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel);
    }

    public SingleLiveEvent<GenericResponse> InitiateEnableAccount(DisableAccountDataModels.InitiateEnableAccountDataModel initiateEnableAccountDataModel) {
        ((EnableAccountAPI) getRetrofitService().create(EnableAccountAPI.class)).enableAccount(LocaleConfiguration.getLanguageCodeForCurrentLocale(), initiateEnableAccountDataModel).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
