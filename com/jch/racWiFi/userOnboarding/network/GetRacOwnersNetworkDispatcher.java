package com.jch.racWiFi.userOnboarding.network;

import com.accord.common.utils.Logger;
import com.google.gson.Gson;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.userOnboarding.model.RacOwnersDataModel;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class GetRacOwnersNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<RacOwnersDataModel.RacOwnersDataModelResponseSuccess> {
    public static final String METHOD_CHANGE_OWNERSHIP = "/rac/ownership/v2/groups/{family-id}/idu-list/{rac-id}/owners";

    public interface GetRacOwnersApi {
        @GET("/rac/ownership/v2/groups/{family-id}/idu-list/{rac-id}/owners")
        Call<RacOwnersDataModel.RacOwnersDataModelResponseSuccess> getRacOwners(@Path("family-id") int i, @Path("rac-id") long j, @Query("excludeMe") boolean z);
    }

    public GetRacOwnersNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public void getRacOwners(int i, long j) {
        ((GetRacOwnersApi) getRetrofitService().create(GetRacOwnersApi.class)).getRacOwners(i, j, false).enqueue(this);
    }

    public void onResponse(Call<RacOwnersDataModel.RacOwnersDataModelResponseSuccess> call, Response<RacOwnersDataModel.RacOwnersDataModelResponseSuccess> response) {
        if (response.isSuccessful()) {
            response.body().setStatusCodeValue(response);
            EventBus.getDefault().post(response.body());
            return;
        }
        new Gson();
        RacOwnersDataModel.FamilyGroupsModelResponseSuccessFailure familyGroupsModelResponseSuccessFailure = new RacOwnersDataModel.FamilyGroupsModelResponseSuccessFailure();
        familyGroupsModelResponseSuccessFailure.statusCode = response.code();
        EventBus.getDefault().post(familyGroupsModelResponseSuccessFailure);
        Logger.m47e("Family_Group_API", "failure dispatcher" + response.code());
    }

    public void onFailure(Call<RacOwnersDataModel.RacOwnersDataModelResponseSuccess> call, Throwable th) {
        EventBus.getDefault().post(th);
        Logger.m47e("Family_Group_API", "failure onFailure");
    }
}
