package com.jch.racWiFi.NetworkDispatch;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class PrivacyPolicyNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_PRIVACY_POLICY = "/iam/management/privacy-policy";
    private SingleLiveEvent<GenericResponse> genericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public interface PrivacyPolicyApi {
        @GET("/iam/management/privacy-policy")
        Call<ResponseBody> getPrivacyPolicyState(@Query("language_code") String str);
    }

    public SingleLiveEvent<GenericResponse> checkForPrivacyPolicyUpdate() {
        ((PrivacyPolicyApi) getRetrofitService().create(PrivacyPolicyApi.class)).getPrivacyPolicyState(LocaleConfiguration.getLanguageCodeForCurrentLocale()).enqueue(this);
        return this.genericResponseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.genericResponseSingleLiveEvent.postValue(GenericResponse.getSuccessGenericResponse(response));
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.genericResponseSingleLiveEvent.postValue(GenericResponse.getFailureGenericResponse(th));
    }
}
