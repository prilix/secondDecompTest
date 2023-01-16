package com.jch.racWiFi.iduManagement;

import android.util.Log;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.model.RefreshRequestPayload;
import com.jch.racWiFi.mock.MockInterceptor;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import p012io.reactivex.Flowable;
import p012io.reactivex.disposables.Disposable;
import p020ua.naiksoftware.stomp.Stomp;
import p020ua.naiksoftware.stomp.StompClient;
import p020ua.naiksoftware.stomp.dto.LifecycleEvent;
import p020ua.naiksoftware.stomp.dto.StompCommand;
import p020ua.naiksoftware.stomp.dto.StompHeader;
import p020ua.naiksoftware.stomp.dto.StompMessage;

public class WebSocketWrapper {
    private static final String TAG = "WebSocketWrapper";
    private static final String TAG_STOMP = "STOMP";
    private boolean completelyTerminate = false;
    private boolean isConnected = false;
    private final List<Disposable> mDisposableList = new ArrayList();
    private final OnStompEvents mOnStompEvents;
    private StompClient mStompClient;

    public interface OnStompEvents {
        void onStompClosed();

        void onStompError();

        void onStompMessageReceived(StompMessage stompMessage);

        void onStompOpened();
    }

    static /* synthetic */ void lambda$disconnectWebSocket$10(Throwable th) throws Exception {
    }

    static /* synthetic */ void lambda$disconnectWebSocket$8(Throwable th) throws Exception {
    }

    static /* synthetic */ void lambda$individualIduRefresh$2() throws Exception {
    }

    static /* synthetic */ void lambda$individualIduRefresh$3(Throwable th) throws Exception {
    }

    static /* synthetic */ void lambda$refreshAllIduStates$0() throws Exception {
    }

    static /* synthetic */ void lambda$refreshAllIduStates$1(Throwable th) throws Exception {
    }

    public WebSocketWrapper(OnStompEvents onStompEvents) {
        this.mOnStompEvents = onStompEvents;
    }

    public boolean isConnected() {
        StompClient stompClient = this.mStompClient;
        return (stompClient != null && stompClient.isConnected()) || this.isConnected;
    }

    public void refreshAllIduStates(CoreActivity coreActivity) {
        if (Constants.IS_DEMO_MODE) {
            try {
                this.mOnStompEvents.onStompMessageReceived(MockInterceptor.getStompMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.mStompClient != null && isConnected()) {
            StompHeader stompHeader = new StompHeader("Authorization", TokenUtil.getInstance().obtain().getBearerToken());
            StompHeader stompHeader2 = new StompHeader("destination", "/app/racs/" + UserInfo.getCurrentUserInfo(coreActivity).f424id + "/" + FamilyGroupList.getCurrentFamily().familyId);
            ArrayList arrayList = new ArrayList();
            arrayList.add(stompHeader);
            arrayList.add(stompHeader2);
            RefreshRequestPayload refreshRequestPayload = new RefreshRequestPayload();
            refreshRequestPayload.racId = 0;
            refreshRequestPayload.requestType = WebSocketNotification.RequestType.REFRESH_ALL.name();
            this.mDisposableList.add(this.mStompClient.send(new StompMessage(StompCommand.MESSAGE, arrayList, refreshRequestPayload.toJson())).subscribe(WebSocketWrapper$$ExternalSyntheticLambda5.INSTANCE, WebSocketWrapper$$ExternalSyntheticLambda2.INSTANCE));
        }
    }

    public void individualIduRefresh(CoreActivity coreActivity, int i) {
        if (Constants.IS_DEMO_MODE) {
            try {
                this.mOnStompEvents.onStompMessageReceived(MockInterceptor.getStompMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.mStompClient != null && isConnected()) {
            StompHeader stompHeader = new StompHeader("Authorization", TokenUtil.getInstance().obtain().getBearerToken());
            StompHeader stompHeader2 = new StompHeader("destination", "/app/racs/" + UserInfo.getCurrentUserInfo(coreActivity).f424id + "/" + FamilyGroupList.getCurrentFamily().familyId);
            ArrayList arrayList = new ArrayList();
            arrayList.add(stompHeader);
            arrayList.add(stompHeader2);
            RefreshRequestPayload refreshRequestPayload = new RefreshRequestPayload();
            refreshRequestPayload.racId = i;
            refreshRequestPayload.requestType = WebSocketNotification.RequestType.REFRESH_INDIVIDUAL.name();
            this.mDisposableList.add(this.mStompClient.send(new StompMessage(StompCommand.MESSAGE, arrayList, refreshRequestPayload.toJson())).subscribe(WebSocketWrapper$$ExternalSyntheticLambda4.INSTANCE, WebSocketWrapper$$ExternalSyntheticLambda1.INSTANCE));
        }
    }

    public void connectToWebSocket(CoreActivity coreActivity) {
        if (this.mStompClient == null) {
            this.isConnected = true;
            this.mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://notification-global-prod.aircloudhome.com/rac-notifications/websocket");
            StompHeader stompHeader = new StompHeader("Authorization", TokenUtil.getInstance().obtain().getBearerToken());
            ArrayList arrayList = new ArrayList();
            arrayList.add(stompHeader);
            this.mStompClient.withClientHeartbeat(10000);
            this.mStompClient.withServerHeartbeat(10000);
            this.mStompClient.connect(arrayList);
            this.completelyTerminate = false;
            subscribeToEventLifecycle(coreActivity);
        }
    }

    private void subscribeToEventLifecycle(CoreActivity coreActivity) {
        StompClient stompClient = this.mStompClient;
        if (stompClient != null) {
            Disposable subscribe = stompClient.lifecycle().subscribe(new WebSocketWrapper$$ExternalSyntheticLambda9(this, coreActivity), new WebSocketWrapper$$ExternalSyntheticLambda7(this));
            Logger.m49i("", "Stomp isConnected = " + this.mStompClient.isConnected());
            this.mDisposableList.add(subscribe);
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.WebSocketWrapper$1 */
    static /* synthetic */ class C18411 {
        static final /* synthetic */ int[] $SwitchMap$ua$naiksoftware$stomp$dto$LifecycleEvent$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                ua.naiksoftware.stomp.dto.LifecycleEvent$Type[] r0 = p020ua.naiksoftware.stomp.dto.LifecycleEvent.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$ua$naiksoftware$stomp$dto$LifecycleEvent$Type = r0
                ua.naiksoftware.stomp.dto.LifecycleEvent$Type r1 = p020ua.naiksoftware.stomp.dto.LifecycleEvent.Type.OPENED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$ua$naiksoftware$stomp$dto$LifecycleEvent$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                ua.naiksoftware.stomp.dto.LifecycleEvent$Type r1 = p020ua.naiksoftware.stomp.dto.LifecycleEvent.Type.ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$ua$naiksoftware$stomp$dto$LifecycleEvent$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                ua.naiksoftware.stomp.dto.LifecycleEvent$Type r1 = p020ua.naiksoftware.stomp.dto.LifecycleEvent.Type.CLOSED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.WebSocketWrapper.C18411.<clinit>():void");
        }
    }

    /* renamed from: lambda$subscribeToEventLifecycle$4$com-jch-racWiFi-iduManagement-WebSocketWrapper */
    public /* synthetic */ void mo29530x4cf9b766(CoreActivity coreActivity, LifecycleEvent lifecycleEvent) throws Exception {
        int i = C18411.$SwitchMap$ua$naiksoftware$stomp$dto$LifecycleEvent$Type[lifecycleEvent.getType().ordinal()];
        if (i == 1) {
            subscribeToTopic(coreActivity);
            this.mOnStompEvents.onStompOpened();
        } else if (i == 2) {
            Logger.m48e(TAG_STOMP, "Error", lifecycleEvent.getException());
            if (!this.completelyTerminate) {
                this.mOnStompEvents.onStompError();
            }
            this.isConnected = false;
        } else if (i == 3) {
            Logger.m47e(TAG_STOMP, "Stomp connection closed");
            if (!this.completelyTerminate) {
                this.mOnStompEvents.onStompClosed();
            }
            this.isConnected = false;
        }
    }

    /* renamed from: lambda$subscribeToEventLifecycle$5$com-jch-racWiFi-iduManagement-WebSocketWrapper */
    public /* synthetic */ void mo29531x86c45945(Throwable th) throws Exception {
        this.isConnected = false;
    }

    private void subscribeToTopic(CoreActivity coreActivity) {
        if (this.mStompClient != null) {
            int i = FamilyGroupList.getCurrentFamily().familyId;
            Log.e(TAG, "subscribeToTopic: " + i);
            StompClient stompClient = this.mStompClient;
            Flowable<StompMessage> flowable = stompClient.topic("/notification/" + UserInfo.getCurrentUserInfo(coreActivity).f424id + "/" + i);
            OnStompEvents onStompEvents = this.mOnStompEvents;
            Objects.requireNonNull(onStompEvents);
            this.mDisposableList.add(flowable.subscribe(new WebSocketWrapper$$ExternalSyntheticLambda6(onStompEvents), new WebSocketWrapper$$ExternalSyntheticLambda8(this)));
        }
    }

    /* renamed from: lambda$subscribeToTopic$6$com-jch-racWiFi-iduManagement-WebSocketWrapper */
    public /* synthetic */ void mo29532xba35115(Throwable th) throws Exception {
        Logger.m48e(TAG_STOMP, "Error on subscribe topic", th);
        this.isConnected = false;
    }

    public void disconnectWebSocket() {
        StompClient stompClient = this.mStompClient;
        if (stompClient != null) {
            this.mDisposableList.add(stompClient.disconnectCompletable().subscribe(new WebSocketWrapper$$ExternalSyntheticLambda0(this), WebSocketWrapper$$ExternalSyntheticLambda11.INSTANCE));
        }
    }

    /* renamed from: lambda$disconnectWebSocket$7$com-jch-racWiFi-iduManagement-WebSocketWrapper */
    public /* synthetic */ void mo29528x2f29b98d() throws Exception {
        this.mStompClient = null;
        this.isConnected = false;
        this.completelyTerminate = true;
    }

    public void disconnectWebSocket(boolean z) {
        StompClient stompClient = this.mStompClient;
        if (stompClient != null) {
            this.mDisposableList.add(stompClient.disconnectCompletable().subscribe(new WebSocketWrapper$$ExternalSyntheticLambda3(this, z), WebSocketWrapper$$ExternalSyntheticLambda10.INSTANCE));
        }
    }

    /* renamed from: lambda$disconnectWebSocket$9$com-jch-racWiFi-iduManagement-WebSocketWrapper */
    public /* synthetic */ void mo29529xa2befd4b(boolean z) throws Exception {
        this.mStompClient = null;
        this.isConnected = false;
        this.completelyTerminate = z;
    }

    public void dispose() {
        for (Disposable dispose : this.mDisposableList) {
            dispose.dispose();
        }
    }

    public void reconnectToWebSocket(CoreActivity coreActivity) {
        this.mStompClient = null;
        connectToWebSocket(coreActivity);
    }
}
