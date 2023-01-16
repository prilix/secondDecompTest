package com.jch.racWiFi.userManagement.network;

import com.google.gson.Gson;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.userManagement.model.DetachMeFromFamilyDataModel;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public class DetachMeFromFamilyNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final String METHOD_DETACH_ME_FROM_FAMILY = "/rac/ownership/groups/{family-id}/me";

    public interface DetachMeFromFamilyApi {
        @DELETE("/rac/ownership/groups/{family-id}/me")
        Call<ResponseBody> detachMeFromFamily(@Path("family-id") int i);
    }

    public DetachMeFromFamilyNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public void detachFromFamily(int i) {
        ((DetachMeFromFamilyApi) getRetrofitService().create(DetachMeFromFamilyApi.class)).detachMeFromFamily(i).enqueue(this);
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            EventBus.getDefault().post(new DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelSuccessResponse());
            return;
        }
        DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelFailureResponse detachMeFromFamilyDataModelFailureResponse = (DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelFailureResponse) new Gson().fromJson(response.errorBody().charStream(), DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelFailureResponse.class);
        detachMeFromFamilyDataModelFailureResponse.statusCodeValue = response.code();
        EventBus.getDefault().post(detachMeFromFamilyDataModelFailureResponse);
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        EventBus.getDefault().post(th);
    }
}
