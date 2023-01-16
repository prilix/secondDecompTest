package com.jch.racWiFi.iduManagement.network;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModels;
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

public class WeeklyTimerDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_WEEKLY_TIMER_ADD = "/rac/idu-advance-control/weekly-timer/{racId}";
    public static final String METHOD_WEEKLY_TIMER_CHANGE = "/rac/idu-advance-control/weekly-timer/{racId}";
    public static final String METHOD_WEEKLY_TIMER_GET = "/rac/idu-advance-control/weekly-timer-schedule/{racId}";
    public static final String METHOD_WEEKLY_TIMER_REMOVE = "/rac/idu-advance-control/racs/{racId}/weekly-timer/{weeklyTimerId}";
    public static final String METHOD_WEEKLY_TIMER_SWITCH = "/rac/idu-advance-control/weekly-timer-schedule/{racId}";
    private boolean add = false;
    MutableLiveData<WeeklyTimerModels.WeeklyTimerAddResponse> addResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<WeeklyTimerModels.WeeklyTimerFetchResponse> fetchResponseMutableLiveData = new MutableLiveData<>();
    private boolean get = false;
    private boolean remove = false;
    MutableLiveData<WeeklyTimerModels.WeeklyTimerRemoveResponse> removeResponseMutableLiveData = new MutableLiveData<>();
    private boolean update = false;
    MutableLiveData<WeeklyTimerModels.WeeklyTimerUpdateResponse> updateResponseMutableLiveData = new MutableLiveData<>();

    public interface IWeeklyTimerAPI {
        @POST("/rac/idu-advance-control/weekly-timer/{racId}")
        Call<ResponseBody> addWeeklyTimer(@Path("racId") int i, @Body WeeklyTimerModels.WeeklyTimerRequestData weeklyTimerRequestData);

        @PUT("/rac/idu-advance-control/weekly-timer/{racId}")
        Call<ResponseBody> changeWeeklyTimer(@Path("racId") int i, @Body WeeklyTimerModels.WeeklyTimerRequestData weeklyTimerRequestData);

        @GET("/rac/idu-advance-control/weekly-timer-schedule/{racId}")
        Call<ResponseBody> getWeeklyTimer(@Path("racId") int i);

        @DELETE("/rac/idu-advance-control/racs/{racId}/weekly-timer/{weeklyTimerId}")
        Call<ResponseBody> removeWeeklyTimer(@Path("racId") int i, @Path("weeklyTimerId") int i2);
    }

    public WeeklyTimerDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public LiveData<WeeklyTimerModels.WeeklyTimerAddResponse> addWeeklyTimer(int i, WeeklyTimerModels.WeeklyTimerRequestData weeklyTimerRequestData) {
        this.add = true;
        ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).addWeeklyTimer(i, weeklyTimerRequestData).enqueue(this);
        return this.addResponseMutableLiveData;
    }

    public LiveData<WeeklyTimerModels.WeeklyTimerUpdateResponse> changeWeeklyTimer(int i, WeeklyTimerModels.WeeklyTimerRequestData weeklyTimerRequestData) {
        this.update = true;
        ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).changeWeeklyTimer(i, weeklyTimerRequestData).enqueue(this);
        return this.updateResponseMutableLiveData;
    }

    public LiveData<WeeklyTimerModels.WeeklyTimerFetchResponse> getWeeklyTimerData(int i) {
        this.get = true;
        ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).getWeeklyTimer(i).enqueue(this);
        return this.fetchResponseMutableLiveData;
    }

    public LiveData<WeeklyTimerModels.WeeklyTimerRemoveResponse> removeWeeklyTimer(int i, int i2) {
        this.remove = true;
        ((IWeeklyTimerAPI) getRetrofitService().create(IWeeklyTimerAPI.class)).removeWeeklyTimer(i, i2).enqueue(this);
        return this.removeResponseMutableLiveData;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (this.get) {
            WeeklyTimerModels.WeeklyTimerFetchResponse weeklyTimerFetchResponse = new WeeklyTimerModels.WeeklyTimerFetchResponse();
            if (response.body() != null) {
                weeklyTimerFetchResponse.response = new Gson().fromJson(response.body().charStream(), WeeklyTimerModels.WeeklyTimerData[].class);
            }
            weeklyTimerFetchResponse.setStatusCodeValue(response);
            this.fetchResponseMutableLiveData.setValue(weeklyTimerFetchResponse);
        } else if (this.remove) {
            WeeklyTimerModels.WeeklyTimerRemoveResponse weeklyTimerRemoveResponse = new WeeklyTimerModels.WeeklyTimerRemoveResponse();
            weeklyTimerRemoveResponse.success = response.isSuccessful();
            weeklyTimerRemoveResponse.setStatusCodeValue(response);
            this.removeResponseMutableLiveData.postValue(weeklyTimerRemoveResponse);
        } else if (this.add) {
            WeeklyTimerModels.WeeklyTimerAddResponse weeklyTimerAddResponse = new WeeklyTimerModels.WeeklyTimerAddResponse();
            weeklyTimerAddResponse.success = response.isSuccessful();
            weeklyTimerAddResponse.setStatusCodeValue(response);
            this.addResponseMutableLiveData.postValue(weeklyTimerAddResponse);
        } else if (this.update) {
            WeeklyTimerModels.WeeklyTimerUpdateResponse weeklyTimerUpdateResponse = new WeeklyTimerModels.WeeklyTimerUpdateResponse();
            weeklyTimerUpdateResponse.success = response.isSuccessful();
            weeklyTimerUpdateResponse.statusCodeValue = response.code();
            this.updateResponseMutableLiveData.postValue(weeklyTimerUpdateResponse);
        }
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        if (this.get) {
            WeeklyTimerModels.WeeklyTimerFetchResponse weeklyTimerFetchResponse = new WeeklyTimerModels.WeeklyTimerFetchResponse();
            weeklyTimerFetchResponse.throwable = th;
            this.fetchResponseMutableLiveData.setValue(weeklyTimerFetchResponse);
        } else if (this.remove) {
            WeeklyTimerModels.WeeklyTimerRemoveResponse weeklyTimerRemoveResponse = new WeeklyTimerModels.WeeklyTimerRemoveResponse();
            weeklyTimerRemoveResponse.success = false;
            this.removeResponseMutableLiveData.postValue(weeklyTimerRemoveResponse);
        } else if (this.add) {
            WeeklyTimerModels.WeeklyTimerAddResponse weeklyTimerAddResponse = new WeeklyTimerModels.WeeklyTimerAddResponse();
            weeklyTimerAddResponse.success = false;
            this.addResponseMutableLiveData.postValue(weeklyTimerAddResponse);
        } else if (this.update) {
            WeeklyTimerModels.WeeklyTimerUpdateResponse weeklyTimerUpdateResponse = new WeeklyTimerModels.WeeklyTimerUpdateResponse();
            weeklyTimerUpdateResponse.success = false;
            this.updateResponseMutableLiveData.postValue(weeklyTimerUpdateResponse);
        }
    }

    public MutableLiveData<WeeklyTimerModels.WeeklyTimerAddResponse> demoMode(WeeklyTimerModels.WeeklyTimerRequestData weeklyTimerRequestData) {
        String str;
        WeeklyTimerModels.WeeklyTimerData weeklyTimerData = new WeeklyTimerModels.WeeklyTimerData();
        weeklyTimerData.f467id = 99;
        weeklyTimerData.selectedDays = weeklyTimerRequestData.selectedDays;
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
        this.addResponseMutableLiveData.postValue(new WeeklyTimerModels.WeeklyTimerAddResponse());
        return this.addResponseMutableLiveData;
    }
}
