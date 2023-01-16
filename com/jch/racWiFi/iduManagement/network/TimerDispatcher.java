package com.jch.racWiFi.iduManagement.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.model.TimerModels;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class TimerDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_TIMER_ADD = "/rac/scheduled-operations/timer/racs/schedules";
    public static final String METHOD_TIMER_GET = "/rac/scheduled-operations/timer/racs/{rac-id}/schedules";
    public static final String METHOD_TIMER_UPDATE = "/rac/scheduled-operations/timer/racs/schedules";
    MutableLiveData<TimerModels.TimerFetchResponse> fetchTimerMutableLiveData = new MutableLiveData<>();
    public boolean get = false;
    MutableLiveData<TimerModels.TimerUpdateResponse> updateTimerMutableLiveData = new MutableLiveData<>();

    public interface ITimerAPI {
        @PUT("/rac/scheduled-operations/timer/racs/schedules")
        Call<ResponseBody> changeTimer(@Query("familyId") int i, @Body TimerModels.AddRequestData addRequestData);

        @PUT("/rac/scheduled-operations/timer/racs/schedules")
        Call<ResponseBody> changeTimer(@Query("familyId") int i, @Body TimerModels.UpdateRequestData updateRequestData);

        @GET("/rac/scheduled-operations/timer/racs/{rac-id}/schedules")
        Call<ResponseBody> getTimer(@Path("rac-id") int i, @Query("familyId") int i2);
    }

    public TimerDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public LiveData<TimerModels.TimerUpdateResponse> addTimer(TimerModels.AddRequestData addRequestData) {
        ((ITimerAPI) getRetrofitService().create(ITimerAPI.class)).changeTimer(FamilyGroupList.getCurrentFamily().familyId, addRequestData).enqueue(this);
        return this.updateTimerMutableLiveData;
    }

    public LiveData<TimerModels.TimerUpdateResponse> updateTimer(TimerModels.UpdateRequestData updateRequestData) {
        ((ITimerAPI) getRetrofitService().create(ITimerAPI.class)).changeTimer(FamilyGroupList.getCurrentFamily().familyId, updateRequestData).enqueue(this);
        return this.updateTimerMutableLiveData;
    }

    public LiveData<TimerModels.TimerFetchResponse> getTimerSchedule(int i) {
        this.get = true;
        ((ITimerAPI) getRetrofitService().create(ITimerAPI.class)).getTimer(i, FamilyGroupList.getCurrentFamily().familyId).enqueue(this);
        return this.fetchTimerMutableLiveData;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (this.get) {
            TimerModels.TimerFetchResponse timerFetchResponse = new TimerModels.TimerFetchResponse();
            if (response.body() != null) {
                timerFetchResponse.response = new Gson().fromJson(response.body().charStream(), TimerModels.ResponseData.class);
            }
            timerFetchResponse.setStatusCodeValue(response);
            this.fetchTimerMutableLiveData.setValue(timerFetchResponse);
            return;
        }
        TimerModels.TimerUpdateResponse timerUpdateResponse = new TimerModels.TimerUpdateResponse();
        timerUpdateResponse.setStatusCodeValue(response);
        if (timerUpdateResponse.isSuccessful()) {
            this.updateTimerMutableLiveData.setValue(timerUpdateResponse);
        } else if (timerUpdateResponse.statusCode == 412) {
            timerUpdateResponse.response = ((JsonObject) new Gson().fromJson(response.errorBody().charStream(), JsonObject.class)).toString();
            this.updateTimerMutableLiveData.setValue(timerUpdateResponse);
        } else {
            this.updateTimerMutableLiveData.setValue(timerUpdateResponse);
        }
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        if (this.get) {
            TimerModels.TimerFetchResponse timerFetchResponse = new TimerModels.TimerFetchResponse();
            timerFetchResponse.throwable = th;
            this.fetchTimerMutableLiveData.setValue(timerFetchResponse);
            return;
        }
        TimerModels.TimerUpdateResponse timerUpdateResponse = new TimerModels.TimerUpdateResponse();
        timerUpdateResponse.throwable = th;
        this.updateTimerMutableLiveData.setValue(timerUpdateResponse);
    }
}
