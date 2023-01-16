package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.UserAddress;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public class UpdateAddressNetworkDispatcherV2 extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_UPDATE_ADDRESS = "/iam/user/v4/update-address";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface UpdateAddressApi {
        @PUT("/iam/user/v4/update-address")
        Call<ResponseBody> updateAddress(@Body UserAddress userAddress);
    }

    public SingleLiveEvent<GenericResponse> updateAddress(UserAddress userAddress) {
        ((UpdateAddressApi) getRetrofitService().create(UpdateAddressApi.class)).updateAddress(userAddress).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
