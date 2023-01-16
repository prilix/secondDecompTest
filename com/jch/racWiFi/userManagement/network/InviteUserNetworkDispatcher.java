package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.InviteMemberModels;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class InviteUserNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_INVITE_USER_FAMILY_GROUP = "/iam/family-account/groups/{family-id}/invite";
    private SingleLiveEvent<GenericResponse> singleLiveEvent = new SingleLiveEvent<>();

    public interface InviteUserApi {
        @POST("/iam/family-account/groups/{family-id}/invite")
        Call<ResponseBody> inviteUserFamilyGroup(@Path("family-id") int i, @Query("language") String str, @Body List<InviteMemberModels.InviteMemberData> list);
    }

    public SingleLiveEvent<GenericResponse> inviteUserFamilyGroup(int i, List<InviteMemberModels.InviteMemberData> list) {
        ((InviteUserApi) getRetrofitService().create(InviteUserApi.class)).inviteUserFamilyGroup(i, LocaleConfiguration.getLanguageCodeForCurrentLocale(), list).enqueue(this);
        return this.singleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.singleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.singleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
