package com.jch.racWiFi.NetworkDispatch;

import com.jch.racWiFi.userManagement.InviteVerificationModels;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class UserExistanceCheckForInviteCodeNetworkDispatcher extends AbstractNetworkDispatcher {
    public static final String METHOD_VERIFY_INVITE_CODE = "/iam/account/check-existence";

    public interface PrivacyPolicyApi {
        @POST("/iam/account/check-existence")
        Call<ResponseBody> getPrivacyPolicyState(@Body InviteVerificationModels.InviteVerificationRequestData inviteVerificationRequestData);
    }

    public void userExists(InviteVerificationModels.InviteVerificationRequestData inviteVerificationRequestData, Callback<ResponseBody> callback) {
        ((PrivacyPolicyApi) getRetrofitService().create(PrivacyPolicyApi.class)).getPrivacyPolicyState(inviteVerificationRequestData).enqueue(callback);
    }
}
