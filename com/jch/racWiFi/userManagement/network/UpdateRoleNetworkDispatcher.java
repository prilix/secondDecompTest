package com.jch.racWiFi.userManagement.network;

import com.google.gson.Gson;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class UpdateRoleNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<UserFamilyMemberModels.UserFamilyMemberSuccessResponse> {
    private static final String METHOD_UPDATE_ROLE_BY_FAMILY_GROUP_ID = "/iam/family-account/groups/{family-id}/members/{user-details-id}";

    public interface UpdateRoleApi {
        @PUT("/iam/family-account/groups/{family-id}/members/{user-details-id}")
        Call<UserFamilyMemberModels.UserFamilyMemberSuccessResponse> updateRoleByFamilyGroupId(@Path("family-id") int i, @Path("user-details-id") int i2, @Query("role-name") String str);
    }

    public void updateRoleByFamilyGroupId(UserFamilyMemberModels.UserFamilyMemberInfo userFamilyMemberInfo, int i, int i2) {
        ((UpdateRoleApi) getRetrofitService().create(UpdateRoleApi.class)).updateRoleByFamilyGroupId(i, i2, userFamilyMemberInfo.role.getName()).enqueue(this);
    }

    public void onResponse(Call<UserFamilyMemberModels.UserFamilyMemberSuccessResponse> call, Response<UserFamilyMemberModels.UserFamilyMemberSuccessResponse> response) {
        if (response.isSuccessful()) {
            EventBus.getDefault().post(response.body());
            return;
        }
        UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse = (UserFamilyMemberModels.UserFamilyMemberFailureResponse) new Gson().fromJson(response.errorBody().charStream(), UserFamilyMemberModels.UserFamilyMemberFailureResponse.class);
        userFamilyMemberFailureResponse.statusCode = response.code();
        EventBus.getDefault().post(userFamilyMemberFailureResponse);
    }

    public void onFailure(Call<UserFamilyMemberModels.UserFamilyMemberSuccessResponse> call, Throwable th) {
        EventBus.getDefault().post(th);
    }
}
