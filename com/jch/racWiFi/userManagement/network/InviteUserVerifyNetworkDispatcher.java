package com.jch.racWiFi.userManagement.network;

import com.accord.common.utils.Logger;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.InviteeModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class InviteUserVerifyNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_INVITE_USER_V2 = "/rac/ownership/accept-invitation";
    private SingleLiveEvent<GenericResponse> mGenericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface InviteUserApi {
        @POST("/rac/ownership/accept-invitation")
        Call<ResponseBody> inviteUserV2(@Body AcceptInvitationModel acceptInvitationModel);
    }

    public InviteUserVerifyNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<GenericResponse> inviteUserV2(String str) {
        Logger.m47e("ÏNVIATE_V2", "dispatcher ivite code " + str);
        AcceptInvitationModel acceptInvitationModel = new AcceptInvitationModel();
        acceptInvitationModel.m1406setnviteCode(str);
        acceptInvitationModel.accepted = true;
        Logger.m49i("", "req body ==" + acceptInvitationModel.toString());
        ((InviteUserApi) getRetrofitService().create(InviteUserApi.class)).inviteUserV2(acceptInvitationModel).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> inviteUser(InviteeModel inviteeModel) {
        Logger.m47e("ÏNVIATE_V2", "dispatcher ivite code " + inviteeModel);
        AcceptInvitationModel acceptInvitationModel = new AcceptInvitationModel();
        acceptInvitationModel.m1406setnviteCode(inviteeModel.inviteCode);
        acceptInvitationModel.accepted = inviteeModel.isAccepted();
        Logger.m49i("", "req body ==" + acceptInvitationModel.toString());
        ((InviteUserApi) getRetrofitService().create(InviteUserApi.class)).inviteUserV2(acceptInvitationModel).enqueue(this);
        return this.mGenericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.mGenericResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
