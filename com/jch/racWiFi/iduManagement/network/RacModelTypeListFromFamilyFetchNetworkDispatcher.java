package com.jch.racWiFi.iduManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RacModelTypeListFromFamilyFetchNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<List<String>> {
    public static final String METHOD_GET_IDU_MODEL_INFO = "/rac/ownership/groups/cloudIds/{family-id}";
    private SingleLiveEvent<List<String>> mModelTypeListSingleLiveEvent = new SingleLiveEvent<>();

    public interface IDUStateApi {
        @GET("/rac/ownership/groups/cloudIds/{family-id}")
        Call<List<String>> getRacModelTypesForFamily(@Path("family-id") int i);
    }

    public RacModelTypeListFromFamilyFetchNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<List<String>> getRacModelTypesForFamily(int i) {
        ((IDUStateApi) getRetrofitService().create(IDUStateApi.class)).getRacModelTypesForFamily(i).enqueue(this);
        return this.mModelTypeListSingleLiveEvent;
    }

    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
        if (response.isSuccessful()) {
            this.mModelTypeListSingleLiveEvent.postValue(response.body());
            return;
        }
        this.mModelTypeListSingleLiveEvent.setValue(new ArrayList());
    }

    public void onFailure(Call<List<String>> call, Throwable th) {
        this.mModelTypeListSingleLiveEvent.setValue(new ArrayList());
    }
}
