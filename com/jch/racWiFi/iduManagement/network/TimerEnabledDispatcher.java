package com.jch.racWiFi.iduManagement.network;

import com.google.gson.Gson;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.TimerEnableDisable;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerMode;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class TimerEnabledDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_TIMER_ENABLED = "/rac/scheduled-operations/racs/schedules/enableDisable/{rac-id}";
    public static final String METHOD_WEEKLY_TIMER_ENABLED = "/rac/scheduled-operations/racs/schedules/enableDisable/{rac-id}";
    private SingleLiveEvent<TimerEnabledModel.TimerEnabledResponse> enabledTimerMutableLiveData = new SingleLiveEvent<>();
    private boolean ifIsWeeklyTimer;

    public interface TimerEnabledAPI {
        @POST("/rac/scheduled-operations/racs/schedules/enableDisable/{rac-id}")
        Call<ResponseBody> timerOperation(@Path("rac-id") long j, @Query("familyId") int i, @Body TimerEnableDisable.ScheduleType scheduleType);
    }

    public interface WeeklyTimerEnabledAPI {
        @POST("/rac/scheduled-operations/racs/schedules/enableDisable/{rac-id}")
        Call<ResponseBody> weeklyTimerOperation(@Path("rac-id") long j, @Query("familyId") int i, @Body TimerEnableDisable.ScheduleType scheduleType);
    }

    public TimerEnabledDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<TimerEnabledModel.TimerEnabledResponse> timerOperations(long j, WeeklyTimerMode weeklyTimerMode) {
        this.ifIsWeeklyTimer = false;
        Call<ResponseBody> timerOperation = ((TimerEnabledAPI) getRetrofitService().create(TimerEnabledAPI.class)).timerOperation(j, FamilyGroupList.getCurrentFamily().familyId, new TimerEnableDisable.ScheduleType(weeklyTimerMode));
        timerOperation.enqueue(this);
        super.setBodyCall(timerOperation);
        return this.enabledTimerMutableLiveData;
    }

    public SingleLiveEvent<TimerEnabledModel.TimerEnabledResponse> weeklyTimerOperations(long j, WeeklyTimerMode weeklyTimerMode) {
        this.ifIsWeeklyTimer = true;
        Call<ResponseBody> weeklyTimerOperation = ((WeeklyTimerEnabledAPI) getRetrofitService().create(WeeklyTimerEnabledAPI.class)).weeklyTimerOperation(j, FamilyGroupList.getCurrentFamily().familyId, new TimerEnableDisable.ScheduleType(weeklyTimerMode));
        weeklyTimerOperation.enqueue(this);
        super.setBodyCall(weeklyTimerOperation);
        return this.enabledTimerMutableLiveData;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (this.ifIsWeeklyTimer) {
            TimerEnabledModel.TimerEnabledResponse timerEnabledResponse = new TimerEnabledModel.TimerEnabledResponse();
            if (response.body() != null) {
                timerEnabledResponse.response = new Gson().fromJson(response.body().charStream(), TimerEnabledModel.class);
            }
            timerEnabledResponse.setStatusCodeValue(response);
            this.enabledTimerMutableLiveData.setValue(timerEnabledResponse);
            return;
        }
        TimerEnabledModel.TimerEnabledResponse timerEnabledResponse2 = new TimerEnabledModel.TimerEnabledResponse();
        if (response.body() != null) {
            timerEnabledResponse2.response = new Gson().fromJson(response.body().charStream(), TimerEnabledModel.TimerEnabledResponse.class);
        }
        timerEnabledResponse2.setStatusCodeValue(response);
        this.enabledTimerMutableLiveData.setValue(timerEnabledResponse2);
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        if (this.ifIsWeeklyTimer) {
            TimerEnabledModel.TimerEnabledResponse timerEnabledResponse = new TimerEnabledModel.TimerEnabledResponse();
            timerEnabledResponse.throwable = th;
            this.enabledTimerMutableLiveData.setValue(timerEnabledResponse);
            return;
        }
        TimerEnabledModel.TimerEnabledResponse timerEnabledResponse2 = new TimerEnabledModel.TimerEnabledResponse();
        timerEnabledResponse2.throwable = th;
        this.enabledTimerMutableLiveData.setValue(timerEnabledResponse2);
    }
}
