package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class GetFamilyMembersListNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_FAMILY_LIST_BY_FAMILY_ID = "/iam/family-account/v2/groups/{family-id}/members";
    SingleLiveEvent<GenericResponse> singleLiveEvent = new SingleLiveEvent<>();

    public interface GetFamilyListApi {
        @GET("/iam/family-account/v2/groups/{family-id}/members")
        Call<ResponseBody> getFamilyListByFamilyID(@Path("family-id") int i);

        @GET("/iam/family-account/v2/groups/{family-id}/members")
        Call<ResponseBody> getFamilyListByFamilyIDForSmartFence(@Path("family-id") int i, @Query("x") boolean z, @Query("y") boolean z2);
    }

    public SingleLiveEvent<GenericResponse> getFamilyListByFamilyID(int i) {
        ((GetFamilyListApi) getRetrofitService().create(GetFamilyListApi.class)).getFamilyListByFamilyID(i).enqueue(this);
        return this.singleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> getFamilyListByFamilyIDForSmartFence(int i, boolean z, boolean z2) {
        ((GetFamilyListApi) getRetrofitService().create(GetFamilyListApi.class)).getFamilyListByFamilyIDForSmartFence(i, z, z2).enqueue(this);
        return this.singleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.singleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.singleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
