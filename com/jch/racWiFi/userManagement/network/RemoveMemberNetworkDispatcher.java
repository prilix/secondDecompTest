package com.jch.racWiFi.userManagement.network;

import com.accord.common.utils.Logger;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public class RemoveMemberNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final String METHOD_REMOVE_MEMBER_URL_BY_FAMILY_GROUP_ID = "/rac/ownership/groups/{family-id}/members/{user-details-id}";

    public interface RemoveMemberApi {
        @DELETE("/rac/ownership/groups/{family-id}/members/{user-details-id}")
        Call<ResponseBody> removeMemberByFamilyGroupID(@Path("family-id") int i, @Path("user-details-id") int i2);
    }

    public RemoveMemberNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public void removeMemberByFamilyGroupId(int i, int i2) {
        ((RemoveMemberApi) getRetrofitService().create(RemoveMemberApi.class)).removeMemberByFamilyGroupID(i, i2).enqueue(this);
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Logger.m49i("", "remove successfull .." + response);
        if (response.isSuccessful()) {
            UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse = new UserFamilyMemberModels.UserFamilyMemberSuccessResponse();
            userFamilyMemberSuccessResponse.deleteUser = true;
            EventBus.getDefault().post(userFamilyMemberSuccessResponse);
            return;
        }
        UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse = new UserFamilyMemberModels.UserFamilyMemberFailureResponse();
        userFamilyMemberFailureResponse.statusCode = response.code();
        userFamilyMemberFailureResponse.deleteUser = true;
        EventBus.getDefault().post(userFamilyMemberFailureResponse);
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        EventBus.getDefault().post(th);
    }
}
