package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.accord.common.utils.Logger;
import com.google.gson.Gson;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceOccupancyModel;
import com.jch.racWiFi.iduManagement.smartFence.networkDispatcher.GeoFencesNetworkDispatcher;
import com.jch.racWiFi.main.api.BaseMainApi;
import com.jch.racWiFi.main.model.RefreshTokenResponse;
import com.jch.racWiFi.p010di.model.Meta;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.util.TokenInfo;
import com.jch.racWiFi.p010di.util.TokenUtil;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import p012io.reactivex.schedulers.Schedulers;

public class GeoFenceTransitionNetworkJobService extends JobService {
    private static final String TAG = "GeoFenceTransitionNetwo";
    @Inject
    BaseMainApi mBaseMainApi;

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public void onCreate() {
        AndroidInjection.inject((Service) this);
        super.onCreate();
    }

    public boolean onStartJob(JobParameters jobParameters) {
        String string = jobParameters.getExtras().getString("GeoFenceOccupancyModel");
        new Handler(Looper.getMainLooper()).post(new GeoFenceTransitionNetworkJobService$$ExternalSyntheticLambda3(this, new GeoFencesNetworkDispatcher(), (GeoFenceOccupancyModel) new Gson().fromJson(string, GeoFenceOccupancyModel.class), jobParameters));
        return true;
    }

    /* renamed from: lambda$onStartJob$0$com-jch-racWiFi-iduManagement-smartFence-geoFenceApi-GeoFenceTransitionNetworkJobService */
    public /* synthetic */ void mo30073xa7905758(GeoFencesNetworkDispatcher geoFencesNetworkDispatcher, GeoFenceOccupancyModel geoFenceOccupancyModel, JobParameters jobParameters) {
        SingleLiveEvent<GenericResponse> updateGeoFenceOccupancy = geoFencesNetworkDispatcher.updateGeoFenceOccupancy(geoFenceOccupancyModel);
        final GeoFencesNetworkDispatcher geoFencesNetworkDispatcher2 = geoFencesNetworkDispatcher;
        final GeoFenceOccupancyModel geoFenceOccupancyModel2 = geoFenceOccupancyModel;
        final SingleLiveEvent<GenericResponse> singleLiveEvent = updateGeoFenceOccupancy;
        final JobParameters jobParameters2 = jobParameters;
        updateGeoFenceOccupancy.observeForever(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                if (genericResponse.getResponse().code() == 401) {
                    GeoFenceTransitionNetworkJobService.this.refreshToken().observeForever(new GeoFenceTransitionNetworkJobService$1$$ExternalSyntheticLambda0(this, geoFencesNetworkDispatcher2, geoFenceOccupancyModel2));
                    return;
                }
                singleLiveEvent.removeObserver(this);
                GeoFenceTransitionNetworkJobService.this.jobFinished(jobParameters2, false);
            }

            /* renamed from: lambda$onChanged$0$com-jch-racWiFi-iduManagement-smartFence-geoFenceApi-GeoFenceTransitionNetworkJobService$1 */
            public /* synthetic */ void mo30078x22109a44(GeoFencesNetworkDispatcher geoFencesNetworkDispatcher, GeoFenceOccupancyModel geoFenceOccupancyModel, Resource resource) {
                RefreshTokenResponse.Body body;
                if (resource != null) {
                    int i = C18862.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
                    if (i == 1) {
                        Logger.m47e(GeoFenceTransitionNetworkJobService.TAG, "refreshToken: Error");
                    } else if (i == 2) {
                        Logger.m47e(GeoFenceTransitionNetworkJobService.TAG, "refreshToken: Loading");
                    } else if (i == 3) {
                        Logger.m47e(GeoFenceTransitionNetworkJobService.TAG, "refreshToken: Success");
                        RefreshTokenResponse refreshTokenResponse = (RefreshTokenResponse) resource.response;
                        if (refreshTokenResponse != null && (body = refreshTokenResponse.getBody()) != null) {
                            String refreshToken = body.getRefreshToken();
                            TokenUtil.getInstance().copy(GeoFenceTransitionNetworkJobService.this.getTokenInfo(body.getToken(), refreshToken, TokenUtil.getInstance().obtain()));
                            geoFencesNetworkDispatcher.updateGeoFenceOccupancy(geoFenceOccupancyModel);
                        }
                    }
                }
            }
        });
    }

    /* renamed from: com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GeoFenceTransitionNetworkJobService$2 */
    static /* synthetic */ class C18862 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$di$model$Resource$Status;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.jch.racWiFi.di.model.Resource$Status[] r0 = com.jch.racWiFi.p010di.model.Resource.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$di$model$Resource$Status = r0
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GeoFenceTransitionNetworkJobService.C18862.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public TokenInfo getTokenInfo(String str, String str2, TokenInfo tokenInfo) {
        TokenInfo tokenInfo2 = new TokenInfo();
        tokenInfo2.setNew(tokenInfo.isNew());
        tokenInfo2.setToken(str);
        tokenInfo2.setRefreshToken(str2);
        tokenInfo2.setType(tokenInfo.getType());
        tokenInfo2.setId(tokenInfo.getId());
        tokenInfo.clear();
        return tokenInfo2;
    }

    public LiveData<Resource<RefreshTokenResponse>> refreshToken() {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        RefreshTokenResponse refreshTokenResponse = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mBaseMainApi.refreshToken().onErrorReturn(GeoFenceTransitionNetworkJobService$$ExternalSyntheticLambda2.INSTANCE).map(GeoFenceTransitionNetworkJobService$$ExternalSyntheticLambda1.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new GeoFenceTransitionNetworkJobService$$ExternalSyntheticLambda0(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    static /* synthetic */ RefreshTokenResponse lambda$refreshToken$1(Throwable th) throws Exception {
        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
        refreshTokenResponse.setMeta(Meta.getMeta(th.getMessage(), th));
        return refreshTokenResponse;
    }

    static /* synthetic */ Resource lambda$refreshToken$2(RefreshTokenResponse refreshTokenResponse) throws Exception {
        if (refreshTokenResponse.getMeta().getCode() < 200 || refreshTokenResponse.getMeta().getCode() >= 300) {
            return Resource.error(refreshTokenResponse.getMeta().getMessage(), refreshTokenResponse, refreshTokenResponse.getMeta().getCode());
        }
        return Resource.success(refreshTokenResponse);
    }

    static /* synthetic */ void lambda$refreshToken$3(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }
}
