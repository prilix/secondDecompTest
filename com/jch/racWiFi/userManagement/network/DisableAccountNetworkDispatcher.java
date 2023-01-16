package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;

public class DisableAccountNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String DISABLE_ACCOUNT = "/rac/ownership/account/user/disable";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface DisableAccountAPI {
        @DELETE("/rac/ownership/account/user/disable")
        Call<ResponseBody> disableAccount();
    }

    public DisableAccountNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<GenericResponse> disableAccount() {
        ((DisableAccountAPI) getRetrofitService().create(DisableAccountAPI.class)).disableAccount().enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(GenericResponse.getSuccessGenericResponse(response));
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(GenericResponse.getFailureGenericResponse(th));
    }
}
