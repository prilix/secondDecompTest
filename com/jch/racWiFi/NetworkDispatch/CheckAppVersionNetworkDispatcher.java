package com.jch.racWiFi.NetworkDispatch;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class CheckAppVersionNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_CHECK_APP_VERSION = "/iam/management/app-version/{app-platform}";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface CheckAppVersionApi {
        @GET("/iam/management/app-version/{app-platform}")
        Call<ResponseBody> checkAppVersion(@Path("app-platform") String str);
    }

    public SingleLiveEvent<GenericResponse> checkAppVersion(String str) {
        ((CheckAppVersionApi) getRetrofitService().create(CheckAppVersionApi.class)).checkAppVersion(str).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
