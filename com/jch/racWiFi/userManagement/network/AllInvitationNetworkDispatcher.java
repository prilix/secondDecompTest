package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class AllInvitationNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_GET_ALL_INVITATION = "/iam/family-account/family-invitations";
    private SingleLiveEvent<GenericResponse> singleLiveEvent = new SingleLiveEvent<>();

    public interface GetAllInvitationApi {
        @GET("/iam/family-account/family-invitations")
        Call<ResponseBody> getAllInvitation();
    }

    public SingleLiveEvent<GenericResponse> getAllInvitation() {
        ((GetAllInvitationApi) getRetrofitService().create(GetAllInvitationApi.class)).getAllInvitation().enqueue(this);
        return this.singleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.singleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.singleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
