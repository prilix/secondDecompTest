package com.jch.racWiFi.iduManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RacModelWiseDataFetchNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<List<RacModelWiseData>> {
    public static final String METHOD_GET_IDU_MODEL_INFO = "/rac/model-wise/rac-configuration";
    private SingleLiveEvent<List<RacModelWiseData>> mModelWiseDataSingleLiveEvent = new SingleLiveEvent<>();

    public interface IDUStateApi {
        @POST("/rac/model-wise/rac-configuration")
        Call<List<RacModelWiseData>> getRacModelInfo(@Body List<String> list);
    }

    public RacModelWiseDataFetchNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<List<RacModelWiseData>> getRacModelInfo(String... strArr) {
        ((IDUStateApi) getRetrofitService().create(IDUStateApi.class)).getRacModelInfo(new ArrayList(Arrays.asList(strArr))).enqueue(this);
        return this.mModelWiseDataSingleLiveEvent;
    }

    public void onResponse(Call<List<RacModelWiseData>> call, Response<List<RacModelWiseData>> response) {
        if (response.isSuccessful()) {
            this.mModelWiseDataSingleLiveEvent.postValue(response.body());
            return;
        }
        this.mModelWiseDataSingleLiveEvent.setValue(new ArrayList());
    }

    public void onFailure(Call<List<RacModelWiseData>> call, Throwable th) {
        this.mModelWiseDataSingleLiveEvent.setValue(new ArrayList());
    }
}
