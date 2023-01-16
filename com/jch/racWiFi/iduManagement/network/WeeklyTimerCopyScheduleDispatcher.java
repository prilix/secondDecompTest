package com.jch.racWiFi.iduManagement.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.model.CopySchedule;
import com.jch.racWiFi.iduManagement.model.CopyTimerScheduleModel;
import com.jch.racWiFi.iduManagement.model.ScheduleCount;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import java.util.Set;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class WeeklyTimerCopyScheduleDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_WEEKLY_TIMER_COPY_DAY_WISE = "/rac/scheduled-operations/weekly-timer/racs/schedules/copy-day-wise";
    public static final String METHOD_WEEKLY_TIMER_COPY_RAC_WISE = "/rac/scheduled-operations/weekly-timer/racs/schedules/copy-rac-wise";
    public static final String METHOD_WEEKLY_TIMER_SCHEDULE_COUNT = "/rac/scheduled-operations/weekly-timer/racs/schedules/count";
    private boolean copyDayWise = false;
    private boolean copyRacWise = false;
    public MutableLiveData<CopyTimerScheduleModel.CopyTimerScheduleResponse> copyTimerScheduleModelMutableLiveData = new MutableLiveData<>();
    private boolean scheduleCount = false;
    public MutableLiveData<ScheduleCount.ScheduleCountResponse> scheduleCountModelMutableLiveData = new MutableLiveData<>();

    public interface IWeeklyTimerAPI {
        @POST("/rac/scheduled-operations/weekly-timer/racs/schedules/copy-day-wise")
        Call<ResponseBody> copyDayWiseTimerSchedule(@Query("familyId") int i, @Body CopySchedule.DayWise dayWise);

        @POST("/rac/scheduled-operations/weekly-timer/racs/schedules/copy-rac-wise")
        Call<ResponseBody> copyRacWiseTimerSchedule(@Query("familyId") int i, @Body CopySchedule.RacWise racWise);

        @GET("/rac/scheduled-operations/weekly-timer/racs/schedules/count")
        Call<ResponseBody> weeklyTimerScheduleCount(@Query("familyId") int i);
    }

    public WeeklyTimerCopyScheduleDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public LiveData<CopyTimerScheduleModel.CopyTimerScheduleResponse> copyRacWiseWeeklyTimerSchedule(CopySchedule.RacWise racWise) {
        this.copyRacWise = true;
        Call<ResponseBody> copyRacWiseTimerSchedule = ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).copyRacWiseTimerSchedule(FamilyGroupList.getCurrentFamily().familyId, racWise);
        copyRacWiseTimerSchedule.enqueue(this);
        super.setBodyCall(copyRacWiseTimerSchedule);
        return this.copyTimerScheduleModelMutableLiveData;
    }

    public LiveData<CopyTimerScheduleModel.CopyTimerScheduleResponse> copyDayWiseWeeklyTimerSchedule(CopySchedule.DayWise dayWise) {
        this.copyDayWise = true;
        Call<ResponseBody> copyDayWiseTimerSchedule = ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).copyDayWiseTimerSchedule(FamilyGroupList.getCurrentFamily().familyId, dayWise);
        copyDayWiseTimerSchedule.enqueue(this);
        super.setBodyCall(copyDayWiseTimerSchedule);
        return this.copyTimerScheduleModelMutableLiveData;
    }

    public LiveData<ScheduleCount.ScheduleCountResponse> weeklyTimerScheduleCount() {
        this.scheduleCount = true;
        ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).weeklyTimerScheduleCount(FamilyGroupList.getCurrentFamily().familyId).enqueue(this);
        return this.scheduleCountModelMutableLiveData;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (this.copyRacWise) {
            CopyTimerScheduleModel.CopyTimerScheduleResponse copyTimerScheduleResponse = new CopyTimerScheduleModel.CopyTimerScheduleResponse();
            if (response.body() != null) {
                if (response.code() == 207) {
                    copyTimerScheduleResponse.response = ((JsonObject) new Gson().fromJson(response.body().charStream(), JsonObject.class)).toString();
                } else {
                    copyTimerScheduleResponse.response = new Gson().fromJson(response.body().charStream(), CopyTimerScheduleModel.CopyTimerScheduleResponse.class);
                }
            }
            copyTimerScheduleResponse.setStatusCodeValue(response);
            this.copyTimerScheduleModelMutableLiveData.setValue(copyTimerScheduleResponse);
        }
        if (this.copyDayWise) {
            try {
                CopyTimerScheduleModel.CopyTimerScheduleResponse copyTimerScheduleResponse2 = new CopyTimerScheduleModel.CopyTimerScheduleResponse();
                if (response.body() != null) {
                    copyTimerScheduleResponse2.response = new Gson().fromJson(response.body().charStream(), CopyTimerScheduleModel.CopyTimerScheduleResponse.class);
                }
                copyTimerScheduleResponse2.setStatusCodeValue(response);
                this.copyTimerScheduleModelMutableLiveData.setValue(copyTimerScheduleResponse2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.scheduleCount) {
            ScheduleCount.ScheduleCountResponse scheduleCountResponse = new ScheduleCount.ScheduleCountResponse();
            if (response.body() != null) {
                JsonObject jsonObject = (JsonObject) new Gson().fromJson(response.body().charStream(), JsonObject.class);
                Set<String> keySet = jsonObject.keySet();
                ScheduleCount[] scheduleCountArr = new ScheduleCount[keySet.size()];
                int i = 0;
                for (String next : keySet) {
                    scheduleCountArr[i] = new ScheduleCount(next, String.valueOf(jsonObject.get(next)));
                    i++;
                }
                scheduleCountResponse.response = scheduleCountArr;
            }
            scheduleCountResponse.setStatusCodeValue(response);
            this.scheduleCountModelMutableLiveData.setValue(scheduleCountResponse);
        }
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        if (this.scheduleCount) {
            ScheduleCount.ScheduleCountResponse scheduleCountResponse = new ScheduleCount.ScheduleCountResponse();
            scheduleCountResponse.throwable = th;
            this.scheduleCountModelMutableLiveData.setValue(scheduleCountResponse);
            return;
        }
        CopyTimerScheduleModel.CopyTimerScheduleResponse copyTimerScheduleResponse = new CopyTimerScheduleModel.CopyTimerScheduleResponse();
        copyTimerScheduleResponse.throwable = th;
        this.copyTimerScheduleModelMutableLiveData.setValue(copyTimerScheduleResponse);
    }
}
