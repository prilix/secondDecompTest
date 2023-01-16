package com.jch.racWiFi.linking.amazon.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import com.jch.racWiFi.linking.amazon.model.Fallback;
import com.jch.racWiFi.linking.amazon.model.SkillRequest;
import com.jch.racWiFi.linking.amazon.model.SkillResponse;
import com.jch.racWiFi.linking.amazon.model.TokenRequest;
import com.jch.racWiFi.linking.amazon.model.TokenResponse;
import com.jch.racWiFi.p010di.api.AlexaApi;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.base.BaseViewModel;
import javax.inject.Inject;
import p012io.reactivex.schedulers.Schedulers;

public class AlexaViewModel extends BaseViewModel {
    private final AlexaApi mAlexaApi;

    @Inject
    public AlexaViewModel(AlexaApi alexaApi) {
        this.mAlexaApi = alexaApi;
    }

    public LiveData<Resource<Fallback>> getFallback() {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        Fallback fallback = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mAlexaApi.getFallback().onErrorReturn(new AlexaViewModel$$ExternalSyntheticLambda4(this)).map(AlexaViewModel$$ExternalSyntheticLambda6.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new AlexaViewModel$$ExternalSyntheticLambda1(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$getFallback$0$com-jch-racWiFi-linking-amazon-view_model-AlexaViewModel */
    public /* synthetic */ Fallback mo31615xbe9bf006(Throwable th) throws Exception {
        Fallback fallback = new Fallback();
        fallback.setMeta(getMeta(th.getMessage(), th));
        return fallback;
    }

    static /* synthetic */ Resource lambda$getFallback$1(Fallback fallback) throws Exception {
        if (fallback.getMeta().getCode() < 200 || fallback.getMeta().getCode() >= 300) {
            return Resource.error(fallback.getMeta().getMessage(), fallback, fallback.getMeta().getCode());
        }
        return Resource.success(fallback);
    }

    static /* synthetic */ void lambda$getFallback$2(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }

    public LiveData<Resource<TokenResponse>> getAmazonToken(String str, String str2) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        TokenResponse tokenResponse = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mAlexaApi.getAmazonToken(new TokenRequest(str, str2)).onErrorReturn(new AlexaViewModel$$ExternalSyntheticLambda3(this)).map(AlexaViewModel$$ExternalSyntheticLambda8.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new AlexaViewModel$$ExternalSyntheticLambda0(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$getAmazonToken$3$com-jch-racWiFi-linking-amazon-view_model-AlexaViewModel */
    public /* synthetic */ TokenResponse mo31614xbd6c022(Throwable th) throws Exception {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setMeta(getMeta(th.getMessage(), th));
        return tokenResponse;
    }

    static /* synthetic */ Resource lambda$getAmazonToken$4(TokenResponse tokenResponse) throws Exception {
        if (tokenResponse.getMeta().getCode() < 200 || tokenResponse.getMeta().getCode() >= 300) {
            return Resource.error(tokenResponse.getMeta().getMessage(), tokenResponse, tokenResponse.getMeta().getCode());
        }
        return Resource.success(tokenResponse);
    }

    static /* synthetic */ void lambda$getAmazonToken$5(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }

    public LiveData<Resource<SkillResponse>> skillEnable(String str, String str2, String str3) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        SkillResponse skillResponse = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mAlexaApi.skillEnable(new SkillRequest(str, str2, str3)).onErrorReturn(new AlexaViewModel$$ExternalSyntheticLambda5(this)).map(AlexaViewModel$$ExternalSyntheticLambda7.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new AlexaViewModel$$ExternalSyntheticLambda2(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$skillEnable$6$com-jch-racWiFi-linking-amazon-view_model-AlexaViewModel */
    public /* synthetic */ SkillResponse mo31616x3f34a64(Throwable th) throws Exception {
        SkillResponse skillResponse = new SkillResponse();
        skillResponse.setMeta(getMeta(th.getMessage(), th));
        return skillResponse;
    }

    static /* synthetic */ Resource lambda$skillEnable$7(SkillResponse skillResponse) throws Exception {
        if (skillResponse.getMeta().getCode() < 200 || skillResponse.getMeta().getCode() >= 300) {
            return Resource.error(skillResponse.getMeta().getMessage(), skillResponse, skillResponse.getMeta().getCode());
        }
        return Resource.success(skillResponse);
    }

    static /* synthetic */ void lambda$skillEnable$8(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }
}
