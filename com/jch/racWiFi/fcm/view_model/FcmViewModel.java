package com.jch.racWiFi.fcm.view_model;

import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.messaging.RemoteMessage;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.fcm.model.Alert;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.model.FcmResponse;
import com.jch.racWiFi.fcm.model.Reminder;
import com.jch.racWiFi.fcm.model.Silent;
import com.jch.racWiFi.fcm.model.SmartFence;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import com.jch.racWiFi.fcm.util.AcActivitiesList;
import com.jch.racWiFi.fcm.util.DeepLinkFactory;
import com.jch.racWiFi.fcm.util.EnumUtil;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.p010di.api.FcmApi;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.base.BaseViewModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.liveData.InviteeLiveDataHolder;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import p012io.reactivex.schedulers.Schedulers;

public class FcmViewModel extends BaseViewModel {
    private final SingleLiveEvent<AcActivitiesList> mAcActivitiesLiveEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<List<Alert>> mAlertLiveEvent = new SingleLiveEvent<>(new ArrayList());
    private final DeepLinkFactory mDeepLinkFactory;
    private final MutableLiveData<List<Error>> mErrorLiveData = new MutableLiveData<>(new ArrayList());
    private final FcmApi mFcmApi;
    private final InviteeLiveDataHolder mInviteeLiveDataHolder = InviteeLiveDataHolder.getInstance();
    private final ModelRepository mModelRepository;
    private final MutableLiveData<Integer> mNotificationCountLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Reminder>> mReminderLiveData = new MutableLiveData<>(new ArrayList());
    private final SingleLiveEvent<Silent> mSilentLiveEvent = new SingleLiveEvent<>();
    private final MutableLiveData<List<SmartFence>> mSmartFenceLiveData = new MutableLiveData<>(new ArrayList());

    public MutableLiveData<List<SmartFence>> getSmartFenceLiveData() {
        return this.mSmartFenceLiveData;
    }

    public MutableLiveData<List<Error>> getErrorLiveData() {
        return this.mErrorLiveData;
    }

    public MutableLiveData<List<Reminder>> getReminderLiveData() {
        return this.mReminderLiveData;
    }

    public SingleLiveEvent<Silent> getSilentLiveEvent() {
        return this.mSilentLiveEvent;
    }

    public SingleLiveEvent<List<Alert>> getAlertLiveEvent() {
        return this.mAlertLiveEvent;
    }

    public SingleLiveEvent<AcActivitiesList> getAcActivitiesLiveEvent() {
        return this.mAcActivitiesLiveEvent;
    }

    @Inject
    public FcmViewModel(FcmApi fcmApi, ModelRepository modelRepository, DeepLinkFactory deepLinkFactory) {
        this.mFcmApi = fcmApi;
        this.mModelRepository = modelRepository;
        this.mDeepLinkFactory = deepLinkFactory;
    }

    public LiveData<Resource<FcmResponse>> getNotifications() {
        clear();
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        FcmResponse fcmResponse = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mFcmApi.getNotifications(FamilyGroupList.getCurrentFamily().familyId).onErrorReturn(new FcmViewModel$$ExternalSyntheticLambda5(this)).map(FcmViewModel$$ExternalSyntheticLambda8.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new FcmViewModel$$ExternalSyntheticLambda2(this, mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$getNotifications$0$com-jch-racWiFi-fcm-view_model-FcmViewModel */
    public /* synthetic */ FcmResponse mo29456x3ab8f99e(Throwable th) throws Exception {
        FcmResponse fcmResponse = new FcmResponse();
        fcmResponse.setMeta(getMeta(th.getMessage(), th));
        return fcmResponse;
    }

    static /* synthetic */ Resource lambda$getNotifications$1(FcmResponse fcmResponse) throws Exception {
        if (fcmResponse.getMeta().getCode() < 200 || fcmResponse.getMeta().getCode() >= 300) {
            return Resource.error(fcmResponse.getMeta().getMessage(), fcmResponse, fcmResponse.getMeta().getCode());
        }
        return Resource.success(fcmResponse);
    }

    /* renamed from: lambda$getNotifications$2$com-jch-racWiFi-fcm-view_model-FcmViewModel */
    public /* synthetic */ void mo29457x6ffa7ea0(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        filter((Resource<FcmResponse>) resource);
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }

    private void clear() {
        if (this.mErrorLiveData.getValue() != null) {
            this.mErrorLiveData.getValue().clear();
        }
        if (this.mReminderLiveData.getValue() != null) {
            this.mReminderLiveData.getValue().clear();
        }
        if (this.mAlertLiveEvent.getValue() != null) {
            this.mAlertLiveEvent.getValue().clear();
        }
        if (this.mSmartFenceLiveData.getValue() != null) {
            this.mSmartFenceLiveData.getValue().clear();
        }
        if (this.mAcActivitiesLiveEvent.getValue() != null) {
            this.mAcActivitiesLiveEvent.getValue().clear();
        }
    }

    private void filter(Resource<FcmResponse> resource) {
        FcmResponse fcmResponse;
        FcmResponse.Body body;
        List<FcmResponse.Body.Result> result;
        if (resource.status == Resource.Status.SUCCESS && (fcmResponse = (FcmResponse) resource.response) != null && (body = fcmResponse.getBody()) != null && (result = body.getResult()) != null && !result.isEmpty()) {
            for (FcmResponse.Body.Result filter : result) {
                filter(filter);
            }
        }
    }

    private void filter(FcmResponse.Body.Result result) {
        String category;
        if (result != null && (category = result.getCategory()) != null) {
            filter(category.replace(Constants.FCM.DASH, Constants.FCM.UNDER_SCORE), result.getSubCategory(), result.getSentTimeInMilliseconds().longValue(), result.getId(), result.getData(), true);
        }
    }

    public void filter(RemoteMessage remoteMessage) {
        String str;
        if (remoteMessage != null && (str = remoteMessage.getData().get(Constants.FCM.CATEGORY)) != null) {
            String replace = str.replace(Constants.FCM.DASH, Constants.FCM.UNDER_SCORE);
            String str2 = remoteMessage.getData().get(Constants.FCM.SUB_CATEGORY);
            if (str2 != null) {
                filter(replace, str2.replace(Constants.FCM.DASH, Constants.FCM.UNDER_SCORE), System.currentTimeMillis(), remoteMessage.getMessageId(), remoteMessage.getData(), false);
            }
        }
    }

    private void filter(String str, String str2, long j, String str3, Map<String, String> map, boolean z) {
        Type type = (Type) EnumUtil.getInstance().getString(Type.class, str);
        if (type != null) {
            switch (C18381.$SwitchMap$com$jch$racWiFi$fcm$util$Type[type.ordinal()]) {
                case 1:
                    if (z && map != null && !map.isEmpty()) {
                        Error error = this.mModelRepository.getError(str2, j, str3, map);
                        if (!(error.getErrorSubCategory() == null || error.getFamilyId() == null || !error.getFamilyId().equals(String.valueOf(FamilyGroupList.getCurrentFamily().familyId)))) {
                            Bundle bundle = new Bundle();
                            bundle.putAll(this.mDeepLinkFactory.getDeepLink(error).getBundle());
                            bundle.putAll(error.getBundle());
                            error.setBundle(bundle);
                            List value = this.mErrorLiveData.getValue();
                            if (value != null && !value.contains(error)) {
                                value.add(error);
                                this.mErrorLiveData.postValue(value);
                                break;
                            }
                        }
                    }
                case 2:
                    if (z && map != null && !map.isEmpty()) {
                        SmartFence smartFence = this.mModelRepository.getSmartFence(str2, j, str3, map);
                        if (!(smartFence.getSubCategory() == null || smartFence.getFamilyId() == null || !smartFence.getFamilyId().equals(String.valueOf(FamilyGroupList.getCurrentFamily().familyId)))) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putAll(this.mDeepLinkFactory.getDeepLink(smartFence).getBundle());
                            bundle2.putAll(smartFence.getBundle());
                            smartFence.setBundle(bundle2);
                            List value2 = this.mSmartFenceLiveData.getValue();
                            if (value2 != null && !value2.contains(smartFence)) {
                                value2.add(smartFence);
                                this.mSmartFenceLiveData.postValue(value2);
                                break;
                            }
                        }
                    }
                case 3:
                    if (z && map != null && !map.isEmpty()) {
                        Alert alert = this.mModelRepository.getAlert(str2, j, str3, map);
                        if (!(alert.getSubCategory() == null || alert.getFamilyId() == null || !alert.getFamilyId().equals(String.valueOf(FamilyGroupList.getCurrentFamily().familyId)))) {
                            Bundle bundle3 = new Bundle();
                            bundle3.putAll(this.mDeepLinkFactory.getDeepLink(alert).getBundle());
                            bundle3.putAll(alert.getBundle());
                            alert.setBundle(bundle3);
                            List value3 = this.mAlertLiveEvent.getValue();
                            if (value3 != null && !value3.contains(alert)) {
                                value3.add(alert);
                                this.mAlertLiveEvent.postValue(value3);
                                break;
                            }
                        }
                    }
                case 4:
                case 5:
                    if (z && map != null && !map.isEmpty()) {
                        Reminder reminder = this.mModelRepository.getReminder(str2, j, str3, map);
                        if (!(reminder.getSubCategory() == null || reminder.getFamilyId() == null || !reminder.getFamilyId().equals(String.valueOf(FamilyGroupList.getCurrentFamily().familyId)))) {
                            Bundle bundle4 = new Bundle();
                            bundle4.putAll(this.mDeepLinkFactory.getDeepLink(reminder).getBundle());
                            bundle4.putAll(reminder.getBundle());
                            reminder.setBundle(bundle4);
                            List value4 = this.mReminderLiveData.getValue();
                            if (value4 != null && !value4.contains(reminder)) {
                                value4.add(reminder);
                                this.mReminderLiveData.postValue(value4);
                                break;
                            }
                        }
                    }
                case 6:
                    if (!z) {
                        Silent silent = this.mModelRepository.getSilent(str2);
                        if (silent.getSubCategory() != null) {
                            this.mSilentLiveEvent.setValue(silent);
                            break;
                        }
                    }
                    break;
            }
        }
        updateCount();
    }

    /* renamed from: com.jch.racWiFi.fcm.view_model.FcmViewModel$1 */
    static /* synthetic */ class C18381 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.fcm.util.Type[] r0 = com.jch.racWiFi.fcm.util.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$Type = r0
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.ERRORS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.SMART_FENCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.ALERTS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.REMINDERS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.REMINDER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.SILENT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.view_model.FcmViewModel.C18381.<clinit>():void");
        }
    }

    public MutableLiveData<Integer> getNotificationCountLiveData() {
        return this.mNotificationCountLiveData;
    }

    public void updateCount() {
        this.mNotificationCountLiveData.postValue(Integer.valueOf(notificationCount()));
    }

    public InviteeLiveDataHolder getInviteeLiveDataHolder() {
        return this.mInviteeLiveDataHolder;
    }

    private int notificationCount() {
        int i = 0;
        if (this.mSmartFenceLiveData.getValue() != null) {
            i = 0 + this.mSmartFenceLiveData.getValue().size();
        }
        if (this.mErrorLiveData.getValue() != null) {
            i += this.mErrorLiveData.getValue().size();
        }
        if (this.mReminderLiveData.getValue() != null) {
            i += this.mReminderLiveData.getValue().size();
        }
        if (this.mAlertLiveEvent.getValue() != null) {
            i += this.mAlertLiveEvent.getValue().size();
        }
        if (this.mAcActivitiesLiveEvent.getValue() != null) {
            i += this.mAcActivitiesLiveEvent.getValue().size();
        }
        return this.mInviteeLiveDataHolder.getInviteeListMutableLiveData().getValue() != null ? i + this.mInviteeLiveDataHolder.getInviteeListMutableLiveData().getValue().size() : i;
    }

    public LiveData<Resource<FcmResponse>> clearNotification(String str) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        FcmResponse fcmResponse = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mFcmApi.clearNotification(str).onErrorReturn(new FcmViewModel$$ExternalSyntheticLambda4(this)).map(FcmViewModel$$ExternalSyntheticLambda7.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new FcmViewModel$$ExternalSyntheticLambda1(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$clearNotification$3$com-jch-racWiFi-fcm-view_model-FcmViewModel */
    public /* synthetic */ FcmResponse mo29455x693203ed(Throwable th) throws Exception {
        FcmResponse fcmResponse = new FcmResponse();
        fcmResponse.setMeta(getMeta(th.getMessage(), th));
        return fcmResponse;
    }

    static /* synthetic */ Resource lambda$clearNotification$4(FcmResponse fcmResponse) throws Exception {
        if (fcmResponse.getMeta().getCode() < 200 || fcmResponse.getMeta().getCode() >= 300) {
            return Resource.error(fcmResponse.getMeta().getMessage(), fcmResponse, fcmResponse.getMeta().getCode());
        }
        return Resource.success(fcmResponse);
    }

    static /* synthetic */ void lambda$clearNotification$5(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }

    public LiveData<Resource<FcmResponse>> clearAllNotification(String str) {
        if (str != null && str.trim().equals(Type.SMART_FENCE.name())) {
            str = "SMART-FENCE";
        }
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        FcmResponse fcmResponse = null;
        mediatorLiveData.setValue(Resource.loading(null));
        LiveData<R> fromPublisher = LiveDataReactiveStreams.fromPublisher(this.mFcmApi.clearAllNotification(str).onErrorReturn(new FcmViewModel$$ExternalSyntheticLambda3(this)).map(FcmViewModel$$ExternalSyntheticLambda6.INSTANCE).subscribeOn(Schedulers.m691io()));
        mediatorLiveData.addSource(fromPublisher, new FcmViewModel$$ExternalSyntheticLambda0(mediatorLiveData, fromPublisher));
        return mediatorLiveData;
    }

    /* renamed from: lambda$clearAllNotification$6$com-jch-racWiFi-fcm-view_model-FcmViewModel */
    public /* synthetic */ FcmResponse mo29454x92914551(Throwable th) throws Exception {
        FcmResponse fcmResponse = new FcmResponse();
        fcmResponse.setMeta(getMeta(th.getMessage(), th));
        return fcmResponse;
    }

    static /* synthetic */ Resource lambda$clearAllNotification$7(FcmResponse fcmResponse) throws Exception {
        if (fcmResponse.getMeta().getCode() < 200 || fcmResponse.getMeta().getCode() >= 300) {
            return Resource.error(fcmResponse.getMeta().getMessage(), fcmResponse, fcmResponse.getMeta().getCode());
        }
        return Resource.success(fcmResponse);
    }

    static /* synthetic */ void lambda$clearAllNotification$8(MediatorLiveData mediatorLiveData, LiveData liveData, Resource resource) {
        mediatorLiveData.setValue(resource);
        mediatorLiveData.removeSource(liveData);
    }
}
