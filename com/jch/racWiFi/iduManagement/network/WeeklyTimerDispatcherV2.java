package com.jch.racWiFi.iduManagement.network;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModels;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class WeeklyTimerDispatcherV2 extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final String METHOD_WEEKLY_TIMER_ADD = "/rac/scheduled-operations/weekly-timer/racs/schedules";
    private static final String METHOD_WEEKLY_TIMER_CHANGE = "/rac/scheduled-operations/weekly-timer/racs/schedules";
    private static final String METHOD_WEEKLY_TIMER_GET = "/rac/scheduled-operations/weekly-timer/racs/{rac-id}/schedules";
    private static final String METHOD_WEEKLY_TIMER_REMOVE = "/rac/scheduled-operations/weekly-timer/racs/{rac-id}/schedules/{sched-id}";
    private static final String METHOD_WEEKLY_TIMER_SWITCH = "/rac/idu-advance-control/weekly-timer-schedule/{racId}";
    private boolean add = false;
    private MutableLiveData<WeeklyTimerModelV2.WeeklyTimerAddResponse> addResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<WeeklyTimerModelV2.WeeklyTimerFetchResponse> fetchResponseMutableLiveData = new MutableLiveData<>();
    private boolean get = false;
    private boolean remove = false;
    private MutableLiveData<WeeklyTimerModelV2.WeeklyTimerRemoveResponse> removeResponseMutableLiveData = new MutableLiveData<>();
    private boolean update = false;
    private MutableLiveData<WeeklyTimerModelV2.WeeklyTimerUpdateResponse> updateResponseMutableLiveData = new MutableLiveData<>();

    public interface IWeeklyTimerAPI {
        @POST("/rac/scheduled-operations/weekly-timer/racs/schedules")
        Call<ResponseBody> addWeeklyTimer(@Query("familyId") int i, @Body WeeklyTimerModelV2.AddRequestData addRequestData);

        @PUT("/rac/scheduled-operations/weekly-timer/racs/schedules")
        Call<ResponseBody> changeWeeklyTimer(@Query("familyId") int i, @Body WeeklyTimerModelV2.UpdateRequestData updateRequestData);

        @GET("/rac/scheduled-operations/weekly-timer/racs/{rac-id}/schedules")
        Call<ResponseBody> getWeeklyTimer(@Path("rac-id") long j, @Query("familyId") int i);

        @DELETE("/rac/scheduled-operations/weekly-timer/racs/{rac-id}/schedules/{sched-id}")
        Call<ResponseBody> removeWeeklyTimer(@Path("rac-id") long j, @Path("sched-id") long j2, @Query("familyId") int i);
    }

    public WeeklyTimerDispatcherV2() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public LiveData<WeeklyTimerModelV2.WeeklyTimerAddResponse> addWeeklyTimer(WeeklyTimerModelV2.AddRequestData addRequestData) {
        this.add = true;
        Call<ResponseBody> addWeeklyTimer = ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).addWeeklyTimer(FamilyGroupList.getCurrentFamily().familyId, addRequestData);
        addWeeklyTimer.enqueue(this);
        super.setBodyCall(addWeeklyTimer);
        return this.addResponseMutableLiveData;
    }

    public LiveData<WeeklyTimerModelV2.WeeklyTimerUpdateResponse> changeWeeklyTimer(WeeklyTimerModelV2.UpdateRequestData updateRequestData) {
        this.update = true;
        ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).changeWeeklyTimer(FamilyGroupList.getCurrentFamily().familyId, updateRequestData).enqueue(this);
        return this.updateResponseMutableLiveData;
    }

    public LiveData<WeeklyTimerModelV2.WeeklyTimerFetchResponse> getWeeklyTimerData(long j) {
        this.get = true;
        Call<ResponseBody> weeklyTimer = ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).getWeeklyTimer(j, FamilyGroupList.getCurrentFamily().familyId);
        weeklyTimer.enqueue(this);
        setBodyCall(weeklyTimer);
        return this.fetchResponseMutableLiveData;
    }

    public LiveData<WeeklyTimerModelV2.WeeklyTimerRemoveResponse> removeWeeklyTimer(long j, long j2) {
        this.remove = true;
        ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).removeWeeklyTimer(j, j2, FamilyGroupList.getCurrentFamily().familyId).enqueue(this);
        return this.removeResponseMutableLiveData;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (this.get) {
            WeeklyTimerModelV2.WeeklyTimerFetchResponse weeklyTimerFetchResponse = new WeeklyTimerModelV2.WeeklyTimerFetchResponse();
            if (response.body() != null) {
                weeklyTimerFetchResponse.response = new Gson().fromJson(response.body().charStream(), WeeklyTimerModelV2.WeeklyTimerResponseData[].class);
            }
            weeklyTimerFetchResponse.setStatusCodeValue(response);
            this.fetchResponseMutableLiveData.setValue(weeklyTimerFetchResponse);
        } else if (this.add) {
            WeeklyTimerModelV2.WeeklyTimerAddResponse weeklyTimerAddResponse = new WeeklyTimerModelV2.WeeklyTimerAddResponse();
            response.body();
            weeklyTimerAddResponse.setStatusCodeValue(response);
            this.addResponseMutableLiveData.postValue(weeklyTimerAddResponse);
        } else {
            if (this.update) {
                WeeklyTimerModelV2.WeeklyTimerUpdateResponse weeklyTimerUpdateResponse = new WeeklyTimerModelV2.WeeklyTimerUpdateResponse();
                weeklyTimerUpdateResponse.setStatusCodeValue(response);
                this.updateResponseMutableLiveData.postValue(weeklyTimerUpdateResponse);
            }
            if (this.remove) {
                WeeklyTimerModelV2.WeeklyTimerRemoveResponse weeklyTimerRemoveResponse = new WeeklyTimerModelV2.WeeklyTimerRemoveResponse();
                weeklyTimerRemoveResponse.success = response.isSuccessful();
                weeklyTimerRemoveResponse.setStatusCodeValue(response);
                this.removeResponseMutableLiveData.postValue(weeklyTimerRemoveResponse);
            }
        }
    }

    public WeeklyTimerModelV2.WeeklyTimerResponseData[] dummyResponse() {
        WeeklyTimerModelV2.WeeklyTimerResponseData[] weeklyTimerResponseDataArr = new WeeklyTimerModelV2.WeeklyTimerResponseData[1];
        weeklyTimerResponseDataArr[0].day = "MON";
        weeklyTimerResponseDataArr[0].f466id = 1;
        weeklyTimerResponseDataArr[0].mode = "COOLING";
        weeklyTimerResponseDataArr[0].scheduleId = 100;
        weeklyTimerResponseDataArr[0].power = DetailedIduModel.POWER_ON;
        weeklyTimerResponseDataArr[0].startAt = "2019-11-20T06:00:00.000+0530";
        weeklyTimerResponseDataArr[0].temperature = 27.5d;
        return weeklyTimerResponseDataArr;
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        if (this.get) {
            WeeklyTimerModelV2.WeeklyTimerFetchResponse weeklyTimerFetchResponse = new WeeklyTimerModelV2.WeeklyTimerFetchResponse();
            weeklyTimerFetchResponse.throwable = th;
            this.fetchResponseMutableLiveData.setValue(weeklyTimerFetchResponse);
        } else if (this.remove) {
            WeeklyTimerModelV2.WeeklyTimerRemoveResponse weeklyTimerRemoveResponse = new WeeklyTimerModelV2.WeeklyTimerRemoveResponse();
            weeklyTimerRemoveResponse.throwable = th;
            this.removeResponseMutableLiveData.postValue(weeklyTimerRemoveResponse);
        } else if (this.add) {
            WeeklyTimerModelV2.WeeklyTimerAddResponse weeklyTimerAddResponse = new WeeklyTimerModelV2.WeeklyTimerAddResponse();
            weeklyTimerAddResponse.throwable = th;
            this.addResponseMutableLiveData.postValue(weeklyTimerAddResponse);
        } else if (this.update) {
            WeeklyTimerModelV2.WeeklyTimerUpdateResponse weeklyTimerUpdateResponse = new WeeklyTimerModelV2.WeeklyTimerUpdateResponse();
            weeklyTimerUpdateResponse.success = false;
            this.updateResponseMutableLiveData.postValue(weeklyTimerUpdateResponse);
        }
    }

    public MutableLiveData<WeeklyTimerModelV2.WeeklyTimerAddResponse> demoMode(WeeklyTimerModels.WeeklyTimerRequestData weeklyTimerRequestData) {
        String str;
        WeeklyTimerModels.WeeklyTimerData weeklyTimerData = new WeeklyTimerModels.WeeklyTimerData();
        weeklyTimerData.f467id = 99;
        weeklyTimerData.mode = weeklyTimerRequestData.mode;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.sss");
        String[] split = weeklyTimerRequestData.switchOnAfter.split("\\+")[0].split(ExifInterface.GPS_DIRECTION_TRUE);
        String str2 = null;
        try {
            Date parse = simpleDateFormat.parse(split[1]);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss.sss");
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
            str = simpleDateFormat2.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
            str = null;
        }
        weeklyTimerData.startsAt = (split[0] + ExifInterface.GPS_DIRECTION_TRUE + str) + "+0000";
        String[] split2 = weeklyTimerRequestData.switchOffAfter.split("\\+")[0].split(ExifInterface.GPS_DIRECTION_TRUE);
        try {
            Date parse2 = simpleDateFormat.parse(split2[1]);
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("HH:mm:ss.sss");
            simpleDateFormat3.setTimeZone(TimeZone.getTimeZone("UTC"));
            str2 = simpleDateFormat3.format(parse2);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        weeklyTimerData.endsAt = (split2[0] + ExifInterface.GPS_DIRECTION_TRUE + str2) + "+0000";
        weeklyTimerData.temperature = weeklyTimerRequestData.temperature;
        weeklyTimerData.enabled = weeklyTimerRequestData.enabled;
        weeklyTimerData.humidity = weeklyTimerRequestData.humidity;
        this.addResponseMutableLiveData.postValue(new WeeklyTimerModelV2.WeeklyTimerAddResponse());
        return this.addResponseMutableLiveData;
    }
}
