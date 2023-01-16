package com.jch.racWiFi.iduManagement.network;

import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.model.CleaningHistoryModel;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class CleaningHistoryDispatcher extends AbstractNetworkDispatcher implements Callback<CleaningHistoryModel.CleaningHistorySuccessResponse> {
    public static final String METHOD_CLEANING_HISTORY = "";

    public interface ICleaningHistoryApi {
        @GET("")
        Call<CleaningHistoryModel.CleaningHistorySuccessResponse> changeCleaningHistory();
    }

    public void onFailure(Call<CleaningHistoryModel.CleaningHistorySuccessResponse> call, Throwable th) {
    }

    public CleaningHistoryDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public void getCleaningHistory() {
        ((ICleaningHistoryApi) getRetrofitService().create(ICleaningHistoryApi.class)).changeCleaningHistory().enqueue(this);
    }

    public void onResponse(Call<CleaningHistoryModel.CleaningHistorySuccessResponse> call, Response<CleaningHistoryModel.CleaningHistorySuccessResponse> response) {
        if (response.isSuccessful()) {
            CleaningHistoryModel.CleaningHistorySuccessResponse body = response.body();
            body.setStatusCodeValue(response);
            EventBus.getDefault().post(body);
            return;
        }
        CleaningHistoryModel.CleaningHistoryFailureResponse cleaningHistoryFailureResponse = new CleaningHistoryModel.CleaningHistoryFailureResponse();
        cleaningHistoryFailureResponse.setStatusCodeValue(response);
        EventBus.getDefault().post(cleaningHistoryFailureResponse);
    }
}
