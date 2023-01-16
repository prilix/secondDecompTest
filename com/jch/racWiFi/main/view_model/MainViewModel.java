package com.jch.racWiFi.main.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import com.jch.racWiFi.AppVersionModels;
import com.jch.racWiFi.amplitude.model.RequestBody;
import com.jch.racWiFi.amplitude.model.ResponseBody;
import com.jch.racWiFi.fcm.model.TokenRequest;
import com.jch.racWiFi.fcm.model.TokenResponse;
import com.jch.racWiFi.main.api.MainApi;
import com.jch.racWiFi.main.model.AppVersion;
import com.jch.racWiFi.main.model.CountryUnit;
import com.jch.racWiFi.main.model.RefreshTokenResponse;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.base.BaseViewModel;
import com.jch.racWiFi.p010di.util.Constants;
import javax.inject.Inject;
import org.json.JSONObject;
import p012io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class MainViewModel extends BaseViewModel {
    private final MainApi mMainApi;

    @Inject
    public MainViewModel(MainApi mainApi) {
        this.mMainApi = mainApi;
    }

    public LiveData<Resource<RefreshTokenResponse>> refreshToken() {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        RefreshTokenResponse refreshTokenResponse = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mMainApi.refreshToken().onErrorReturn(new MainViewModel$$ExternalSyntheticLambda1(this)).map(MainViewModel$$ExternalSyntheticLambda8.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new MainViewModel$$ExternalSyntheticLambda12(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$refreshToken$0$com-jch-racWiFi-main-view_model-MainViewModel */
    public /* synthetic */ RefreshTokenResponse mo31668xf4bb64d6(Throwable th) throws Exception {
        if (th instanceof HttpException) {
            JSONObject jSONObject = new JSONObject(((HttpException) th).response().errorBody().string());
            if (jSONObject.has(Constants.Meta.META)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(Constants.Meta.META);
                int i = jSONObject2.has("code") ? jSONObject2.getInt("code") : 0;
                String string = jSONObject2.has(Constants.Meta.MESSAGE) ? jSONObject2.getString(Constants.Meta.MESSAGE) : null;
                String string2 = jSONObject2.has("token") ? jSONObject2.getString("token") : null;
                String string3 = jSONObject2.has("type") ? jSONObject2.getString("type") : null;
                RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
                if (i != 400 || string == null || !string.trim().equals("EXPIRED_TOKEN")) {
                    refreshTokenResponse.setMeta(getMeta(th.getMessage(), th));
                } else {
                    refreshTokenResponse.setMeta(getMeta(i, string, th, string2, string3));
                }
                return refreshTokenResponse;
            }
        }
        RefreshTokenResponse refreshTokenResponse2 = new RefreshTokenResponse();
        refreshTokenResponse2.setMeta(getMeta(th.getMessage(), th));
        return refreshTokenResponse2;
    }

    static /* synthetic */ Resource lambda$refreshToken$1(RefreshTokenResponse refreshTokenResponse) throws Exception {
        if (refreshTokenResponse.getMeta().getCode() < 200 || refreshTokenResponse.getMeta().getCode() >= 300) {
            return Resource.error(refreshTokenResponse.getMeta().getMessage(), refreshTokenResponse, refreshTokenResponse.getMeta().getCode());
        }
        return Resource.success(refreshTokenResponse);
    }

    static /* synthetic */ void lambda$refreshToken$2(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }

    public LiveData<Resource<TokenResponse>> registerDevice(String str) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        TokenResponse tokenResponse = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mMainApi.registerDevice(new TokenRequest(str, AppVersionModels.Platform.ANDROID.name())).onErrorReturn(new MainViewModel$$ExternalSyntheticLambda2(this)).map(MainViewModel$$ExternalSyntheticLambda5.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new MainViewModel$$ExternalSyntheticLambda13(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$registerDevice$3$com-jch-racWiFi-main-view_model-MainViewModel */
    public /* synthetic */ TokenResponse mo31669x9b758554(Throwable th) throws Exception {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setMeta(getMeta(th.getMessage(), th));
        return tokenResponse;
    }

    static /* synthetic */ Resource lambda$registerDevice$4(TokenResponse tokenResponse) throws Exception {
        if (tokenResponse.getMeta().getCode() < 200 || tokenResponse.getMeta().getCode() >= 300) {
            return Resource.error(tokenResponse.getMeta().getMessage(), tokenResponse, tokenResponse.getMeta().getCode());
        }
        return Resource.success(tokenResponse);
    }

    static /* synthetic */ void lambda$registerDevice$5(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }

    public LiveData<Resource<TokenResponse>> deRegisterDevice(String str, String str2) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        TokenResponse tokenResponse = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mMainApi.deRegisterDevice(str, str2).onErrorReturn(new MainViewModel$$ExternalSyntheticLambda14(this)).map(MainViewModel$$ExternalSyntheticLambda4.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new MainViewModel$$ExternalSyntheticLambda0(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$deRegisterDevice$6$com-jch-racWiFi-main-view_model-MainViewModel */
    public /* synthetic */ TokenResponse mo31664x240289b8(Throwable th) throws Exception {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setMeta(getMeta(th.getMessage(), th));
        return tokenResponse;
    }

    static /* synthetic */ Resource lambda$deRegisterDevice$7(TokenResponse tokenResponse) throws Exception {
        if (tokenResponse.getMeta().getCode() < 200 || tokenResponse.getMeta().getCode() >= 300) {
            return Resource.error(tokenResponse.getMeta().getMessage(), tokenResponse, tokenResponse.getMeta().getCode());
        }
        return Resource.success(tokenResponse);
    }

    static /* synthetic */ void lambda$deRegisterDevice$8(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }

    public LiveData<Resource<CountryUnit>> getCountryUnit() {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        CountryUnit countryUnit = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mMainApi.getCountryUnit(Constants.CC.getCountryCode()).onErrorReturn(new MainViewModel$$ExternalSyntheticLambda17(this)).map(MainViewModel$$ExternalSyntheticLambda7.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new MainViewModel$$ExternalSyntheticLambda11(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$getCountryUnit$9$com-jch-racWiFi-main-view_model-MainViewModel */
    public /* synthetic */ CountryUnit mo31667xe5a60705(Throwable th) throws Exception {
        CountryUnit countryUnit = new CountryUnit();
        countryUnit.setMeta(getMeta(th.getMessage(), th));
        return countryUnit;
    }

    static /* synthetic */ Resource lambda$getCountryUnit$10(CountryUnit countryUnit) throws Exception {
        if (countryUnit.getMeta().getCode() < 200 || countryUnit.getMeta().getCode() >= 300) {
            return Resource.error(countryUnit.getMeta().getMessage(), countryUnit, countryUnit.getMeta().getCode());
        }
        return Resource.success(countryUnit);
    }

    static /* synthetic */ void lambda$getCountryUnit$11(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }

    public LiveData<Resource<AppVersion>> getAppVersion() {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        AppVersion appVersion = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mMainApi.appVersion().onErrorReturn(new MainViewModel$$ExternalSyntheticLambda16(this)).map(MainViewModel$$ExternalSyntheticLambda6.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new MainViewModel$$ExternalSyntheticLambda10(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$getAppVersion$12$com-jch-racWiFi-main-view_model-MainViewModel */
    public /* synthetic */ AppVersion mo31666x2b94e2e4(Throwable th) throws Exception {
        AppVersion appVersion = new AppVersion();
        appVersion.setMeta(getMeta(th.getMessage(), th));
        return appVersion;
    }

    static /* synthetic */ Resource lambda$getAppVersion$13(AppVersion appVersion) throws Exception {
        if (appVersion.getMeta().getCode() < 200 || appVersion.getMeta().getCode() >= 300) {
            return Resource.error(appVersion.getMeta().getMessage(), appVersion, appVersion.getMeta().getCode());
        }
        return Resource.success(appVersion);
    }

    static /* synthetic */ void lambda$getAppVersion$14(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }

    public LiveData<Resource<ResponseBody>> getAmplitudeStatus(RequestBody requestBody) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        ResponseBody responseBody = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mMainApi.getAmplitudeStatus(requestBody).onErrorReturn(new MainViewModel$$ExternalSyntheticLambda15(this)).map(MainViewModel$$ExternalSyntheticLambda3.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new MainViewModel$$ExternalSyntheticLambda9(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$getAmplitudeStatus$15$com-jch-racWiFi-main-view_model-MainViewModel */
    public /* synthetic */ ResponseBody mo31665xd3d2cc5b(Throwable th) throws Exception {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setMeta(getMeta(th.getMessage(), th));
        return responseBody;
    }

    static /* synthetic */ Resource lambda$getAmplitudeStatus$16(ResponseBody responseBody) throws Exception {
        if (responseBody.getMeta().getCode() < 200 || responseBody.getMeta().getCode() >= 300) {
            return Resource.error(responseBody.getMeta().getMessage(), responseBody, responseBody.getMeta().getCode());
        }
        return Resource.success(responseBody);
    }

    static /* synthetic */ void lambda$getAmplitudeStatus$17(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }
}
