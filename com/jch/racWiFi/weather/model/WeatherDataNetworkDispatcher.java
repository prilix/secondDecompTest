package com.jch.racWiFi.weather.model;

import com.accord.common.utils.Logger;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class WeatherDataNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String WEATHER_DATA_API = "/iam/weather/info";
    public static final String WEATHER_DATA_API_FOR_RAC = "/iam/weather/info/{rac-id}";
    private SingleLiveEvent<GenericResponse> mSingleLiveEvent = new SingleLiveEvent<>();
    private int racID;

    interface WeatherDataAPI {
        @GET("/iam/weather/info")
        Call<ResponseBody> getWeatherData(@Query("language") String str);

        @GET("/iam/weather/info/{rac-id}")
        Call<ResponseBody> getWeatherDataForRac(@Path("rac-id") int i, @Query("language") String str);
    }

    public SingleLiveEvent<GenericResponse> getWeatherData(String str) {
        ((WeatherDataAPI) getRetrofitService().create(WeatherDataAPI.class)).getWeatherData(str).enqueue(this);
        return this.mSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> getWeatherDataForRac(int i, String str) {
        this.racID = i;
        ((WeatherDataAPI) getRetrofitService().create(WeatherDataAPI.class)).getWeatherDataForRac(i, str).enqueue(this);
        return this.mSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        GenericResponse successGenericResponse = GenericResponse.getSuccessGenericResponse(response);
        if (this.mSingleLiveEvent.hasActiveObservers()) {
            Logger.m45d("Acctive observer", "HAS");
        }
        this.mSingleLiveEvent.postValue(successGenericResponse);
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mSingleLiveEvent.postValue(GenericResponse.getFailureGenericResponse(th));
    }
}
