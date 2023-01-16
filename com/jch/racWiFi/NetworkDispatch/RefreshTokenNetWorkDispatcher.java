package com.jch.racWiFi.NetworkDispatch;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class RefreshTokenNetWorkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final String REFRESH_TOKEN_API = "/iam/auth/refresh-token";
    SingleLiveEvent<Response<ResponseBody>> singleLiveEvent = new SingleLiveEvent<>();

    private interface IRefreshToken {
        @POST("/iam/auth/refresh-token")
        Call<ResponseBody> refreshTokenAPi();
    }

    public SingleLiveEvent<Response<ResponseBody>> refreshToke() {
        ((IRefreshToken) getRetrofitService().create(IRefreshToken.class)).refreshTokenAPi().enqueue(this);
        return this.singleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.singleLiveEvent.postValue(response);
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.singleLiveEvent.postValue(null);
    }
}
