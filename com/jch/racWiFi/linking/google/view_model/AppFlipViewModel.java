package com.jch.racWiFi.linking.google.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import com.jch.racWiFi.linking.google.model.SkillStatus;
import com.jch.racWiFi.p010di.api.AppFlipApi;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.base.BaseViewModel;
import javax.inject.Inject;
import p012io.reactivex.schedulers.Schedulers;

public class AppFlipViewModel extends BaseViewModel {
    private final AppFlipApi mAppFlipApi;

    @Inject
    public AppFlipViewModel(AppFlipApi appFlipApi) {
        this.mAppFlipApi = appFlipApi;
    }

    public LiveData<Resource<SkillStatus>> getSkillStatus() {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        SkillStatus skillStatus = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mAppFlipApi.getSkillStatus().onErrorReturn(new AppFlipViewModel$$ExternalSyntheticLambda1(this)).map(AppFlipViewModel$$ExternalSyntheticLambda2.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new AppFlipViewModel$$ExternalSyntheticLambda0(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$getSkillStatus$0$com-jch-racWiFi-linking-google-view_model-AppFlipViewModel */
    public /* synthetic */ SkillStatus mo31623xfc8d4821(Throwable th) throws Exception {
        SkillStatus skillStatus = new SkillStatus();
        skillStatus.setMeta(getMeta(th.getMessage(), th));
        return skillStatus;
    }

    static /* synthetic */ Resource lambda$getSkillStatus$1(SkillStatus skillStatus) throws Exception {
        if (skillStatus.getMeta().getCode() < 200 || skillStatus.getMeta().getCode() >= 300) {
            return Resource.error(skillStatus.getMeta().getMessage(), skillStatus, skillStatus.getMeta().getCode());
        }
        return Resource.success(skillStatus);
    }

    static /* synthetic */ void lambda$getSkillStatus$2(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.postValue(resource);
        mediatorLiveData.removeSource(liveData);
    }
}
