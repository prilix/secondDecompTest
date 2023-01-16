package com.jch.racWiFi.settings.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.settings.model.SettingsDataModels;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class PreferencesSettingsNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final String METHOD_UPDATE_SETTINGS = "/rac/scheduled-operations/racs/userPreferences";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface UpdateSettingsApi {
        @POST("/rac/scheduled-operations/racs/userPreferences")
        Call<ResponseBody> updateSettings(@Body SettingsDataModels.UserPreferenceSettingsData userPreferenceSettingsData, @Query("familyId") int i);
    }

    public PreferencesSettingsNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<GenericResponse> updateSettings(SettingsDataModels.UserPreferenceSettingsData userPreferenceSettingsData) {
        ((UpdateSettingsApi) getRetrofitService().create(UpdateSettingsApi.class)).updateSettings(userPreferenceSettingsData, FamilyGroupList.getCurrentFamily().familyId).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
