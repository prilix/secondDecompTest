package com.jch.racWiFi.settings.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.settings.model.SettingsDataModels;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public class SettingsNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_UPDATE_SETTINGS = "/iam/user/settings";
    public SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface UpdateSettingsApi {
        @PUT("/iam/user/settings")
        Call<ResponseBody> updateSettings(@Body SettingsDataModels.SettingsData settingsData);
    }

    public SingleLiveEvent<GenericResponse> updateSettings(SettingsDataModels.SettingsData settingsData) {
        ((UpdateSettingsApi) getRetrofitService().create(UpdateSettingsApi.class)).updateSettings(settingsData).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
