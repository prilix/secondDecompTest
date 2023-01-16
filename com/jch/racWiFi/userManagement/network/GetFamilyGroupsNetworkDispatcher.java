package com.jch.racWiFi.userManagement.network;

import com.accord.common.utils.Logger;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class GetFamilyGroupsNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final String GET_FAMILY_GROUP = "/iam/family-account/v2/groups";
    private SingleLiveEvent<GenericResponse> singleLiveEvent = new SingleLiveEvent<>();

    public interface GetFamilyGroupAPI {
        @GET("/iam/family-account/v2/groups")
        Call<ResponseBody> getFamilyGroup();
    }

    public SingleLiveEvent<GenericResponse> getFamilyGroups() {
        ((GetFamilyGroupAPI) getRetrofitService().create(GetFamilyGroupAPI.class)).getFamilyGroup().enqueue(this);
        return this.singleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.singleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        EventBus.getDefault().post(th);
        Logger.m47e("Family_Group_API", "failure onFailure");
        this.singleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
