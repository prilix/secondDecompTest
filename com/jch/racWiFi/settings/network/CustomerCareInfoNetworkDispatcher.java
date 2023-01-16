package com.jch.racWiFi.settings.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CustomerCareInfoNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_GET_CUSTOMER_CARE = "/rac/help/customer-support";
    private SingleLiveEvent<GenericResponse> customerCareInfoModelResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface CustomerCareInfoApi {
        @GET("/rac/help/customer-support")
        Call<ResponseBody> getCustomerCareInfoForRac(@Query("familyId") int i, @Query("racId") int i2);
    }

    public CustomerCareInfoNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<GenericResponse> getCustomerCareInfoForRac(int i, int i2) {
        ((CustomerCareInfoApi) getRetrofitService().create(CustomerCareInfoApi.class)).getCustomerCareInfoForRac(i, i2).enqueue(this);
        return this.customerCareInfoModelResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.customerCareInfoModelResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.customerCareInfoModelResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
