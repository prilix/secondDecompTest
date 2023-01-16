package com.jch.racWiFi.userManagement.network;

import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.userManagement.model.CreateFamilyInstanceOnServer;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class CreateFamilyInstanceNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_CREATE_FAMILY_INSTANCE = "/iam/family-account/create-new";

    public interface CreateFamilyInstance {
        @POST("/iam/family-account/create-new")
        Call<ResponseBody> createFamilyInstance();
    }

    public void createFamilyInstanceOnServer() {
        ((CreateFamilyInstance) getRetrofitService().create(CreateFamilyInstance.class)).createFamilyInstance().enqueue(this);
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            CreateFamilyInstanceOnServer createFamilyInstanceOnServer = new CreateFamilyInstanceOnServer();
            createFamilyInstanceOnServer.status = response.code();
            EventBus.getDefault().post(createFamilyInstanceOnServer);
            return;
        }
        CreateFamilyInstanceOnServer createFamilyInstanceOnServer2 = new CreateFamilyInstanceOnServer();
        createFamilyInstanceOnServer2.status = response.code();
        EventBus.getDefault().post(createFamilyInstanceOnServer2);
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        EventBus.getDefault().post(th);
    }
}
