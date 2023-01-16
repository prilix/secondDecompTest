package com.jch.racWiFi.settings.network;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class HelpNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_GET_HELP = "/rac/help/rac-user-manual/v2";
    private SingleLiveEvent<GenericResponse> helpDataModelResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface HelpInfoApi {
        @GET("/rac/help/rac-user-manual/v2")
        Call<ResponseBody> getCustomerCareInfoForRac(@Query("familyId") int i, @Query("racId") int i2, @Query("language") String str);
    }

    public HelpNetworkDispatcher() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<GenericResponse> getHelpInfoForRac(int i, int i2) {
        ((HelpInfoApi) getRetrofitService().create(HelpInfoApi.class)).getCustomerCareInfoForRac(i, i2, LocaleConfiguration.getLanguageCodeForCurrentLocale()).enqueue(this);
        return this.helpDataModelResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.helpDataModelResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.helpDataModelResponseSingleLiveEvent.postValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }
}
