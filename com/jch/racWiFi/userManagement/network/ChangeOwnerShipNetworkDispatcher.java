package com.jch.racWiFi.userManagement.network;

import com.accord.common.utils.Logger;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ChangeOwnerShipNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final String METHOD_CHANGE_OWNERSHIP = "/rac/ownership/groups/{family-id}/ownership";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface ChangeOwnerShipApi {
        @PUT("/rac/ownership/groups/{family-id}/ownership")
        Call<ResponseBody> changeOwnerShip(@Path("family-id") int i, @Query("user-details-id") int i2);
    }

    public ChangeOwnerShipNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<GenericResponse> changeOwnerShip(int i, int i2) {
        ((ChangeOwnerShipApi) getRetrofitService().create(ChangeOwnerShipApi.class)).changeOwnerShip(i, i2).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(GenericResponse.getSuccessGenericResponse(response));
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(GenericResponse.getFailureGenericResponse(th));
        Logger.m47e("Family_Group_API", "failure onFailure");
    }
}
