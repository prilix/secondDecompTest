package p020ua.naiksoftware.stomp;

import com.accord.common.utils.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import p012io.reactivex.BackpressureStrategy;
import p012io.reactivex.Completable;
import p012io.reactivex.CompletableSource;
import p012io.reactivex.Flowable;
import p012io.reactivex.Observable;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.functions.Consumer;
import p012io.reactivex.subjects.BehaviorSubject;
import p012io.reactivex.subjects.PublishSubject;
import p020ua.naiksoftware.stomp.dto.LifecycleEvent;
import p020ua.naiksoftware.stomp.dto.StompCommand;
import p020ua.naiksoftware.stomp.dto.StompHeader;
import p020ua.naiksoftware.stomp.dto.StompMessage;
import p020ua.naiksoftware.stomp.pathmatcher.PathMatcher;
import p020ua.naiksoftware.stomp.pathmatcher.SimplePathMatcher;
import p020ua.naiksoftware.stomp.provider.ConnectionProvider;

/* renamed from: ua.naiksoftware.stomp.StompClient */
public class StompClient {
    public static final String DEFAULT_ACK = "auto";
    public static final String SUPPORTED_VERSIONS = "1.1,1.2";
    private static final String TAG = "StompClient";
    private final ConnectionProvider connectionProvider;
    private BehaviorSubject<Boolean> connectionStream;
    private List<StompHeader> headers;
    private HeartBeatTask heartBeatTask = new HeartBeatTask(new StompClient$$ExternalSyntheticLambda8(this), new StompClient$$ExternalSyntheticLambda7(this));
    private boolean legacyWhitespace;
    private Disposable lifecycleDisposable;
    private PublishSubject<LifecycleEvent> lifecyclePublishSubject = PublishSubject.create();
    private PublishSubject<StompMessage> messageStream;
    private Disposable messagesDisposable;
    private PathMatcher pathMatcher = new SimplePathMatcher();
    private ConcurrentHashMap<String, Flowable<StompMessage>> streamMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> topics;

    static /* synthetic */ void lambda$disconnect$9() throws Exception {
    }

    public StompClient(ConnectionProvider connectionProvider2) {
        this.connectionProvider = connectionProvider2;
    }

    /* renamed from: lambda$new$0$ua-naiksoftware-stomp-StompClient  reason: not valid java name */
    public /* synthetic */ void m3177lambda$new$0$uanaiksoftwarestompStompClient() {
        this.lifecyclePublishSubject.onNext(new LifecycleEvent(LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT));
    }

    public StompClient withServerHeartbeat(int i) {
        this.heartBeatTask.setServerHeartbeat(i);
        return this;
    }

    public StompClient withClientHeartbeat(int i) {
        this.heartBeatTask.setClientHeartbeat(i);
        return this;
    }

    public void connect() {
        connect((List<StompHeader>) null);
    }

    public void connect(List<StompHeader> list) {
        String str = TAG;
        Logger.m45d(str, "Connect");
        this.headers = list;
        if (isConnected()) {
            Logger.m45d(str, "Already connected, ignore");
            return;
        }
        this.lifecycleDisposable = this.connectionProvider.lifecycle().subscribe(new StompClient$$ExternalSyntheticLambda15(this, list));
        Observable<R> map = this.connectionProvider.messages().map(StompClient$$ExternalSyntheticLambda1.INSTANCE);
        HeartBeatTask heartBeatTask2 = this.heartBeatTask;
        Objects.requireNonNull(heartBeatTask2);
        Observable<R> filter = map.filter(new StompClient$$ExternalSyntheticLambda2(heartBeatTask2));
        PublishSubject<StompMessage> messageStream2 = getMessageStream();
        Objects.requireNonNull(messageStream2);
        this.messagesDisposable = filter.doOnNext(new StompClient$$ExternalSyntheticLambda13(messageStream2)).filter(StompClient$$ExternalSyntheticLambda6.INSTANCE).subscribe(new StompClient$$ExternalSyntheticLambda14(this));
    }

    /* renamed from: ua.naiksoftware.stomp.StompClient$2 */
    static /* synthetic */ class C30752 {
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
                ua.naiksoftware.stomp.dto.LifecycleEvent$Type r1 = p020ua.naiksoftware.stomp.dto.LifecycleEvent.Type.CLOSED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$ua$naiksoftware$stomp$dto$LifecycleEvent$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                ua.naiksoftware.stomp.dto.LifecycleEvent$Type r1 = p020ua.naiksoftware.stomp.dto.LifecycleEvent.Type.ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p020ua.naiksoftware.stomp.StompClient.C30752.<clinit>():void");
        }
    }

    /* renamed from: lambda$connect$2$ua-naiksoftware-stomp-StompClient  reason: not valid java name */
    public /* synthetic */ void m3175lambda$connect$2$uanaiksoftwarestompStompClient(List list, LifecycleEvent lifecycleEvent) throws Exception {
        int i = C30752.$SwitchMap$ua$naiksoftware$stomp$dto$LifecycleEvent$Type[lifecycleEvent.getType().ordinal()];
        if (i == 1) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StompHeader(StompHeader.VERSION, SUPPORTED_VERSIONS));
            arrayList.add(new StompHeader(StompHeader.HEART_BEAT, this.heartBeatTask.getClientHeartbeat() + "," + this.heartBeatTask.getServerHeartbeat()));
            if (list != null) {
                arrayList.addAll(list);
            }
            this.connectionProvider.send(new StompMessage(StompCommand.CONNECT, arrayList, (String) null).compile(this.legacyWhitespace)).subscribe(new StompClient$$ExternalSyntheticLambda11(this, lifecycleEvent), new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                }
            });
        } else if (i == 2) {
            Logger.m45d(TAG, "Socket closed");
            disconnect();
        } else if (i == 3) {
            Logger.m45d(TAG, "Socket closed with error");
            this.lifecyclePublishSubject.onNext(lifecycleEvent);
        }
    }

    /* renamed from: lambda$connect$1$ua-naiksoftware-stomp-StompClient  reason: not valid java name */
    public /* synthetic */ void m3174lambda$connect$1$uanaiksoftwarestompStompClient(LifecycleEvent lifecycleEvent) throws Exception {
        Logger.m45d(TAG, "Publish open");
        this.lifecyclePublishSubject.onNext(lifecycleEvent);
    }

    /* renamed from: lambda$connect$4$ua-naiksoftware-stomp-StompClient  reason: not valid java name */
    public /* synthetic */ void m3176lambda$connect$4$uanaiksoftwarestompStompClient(StompMessage stompMessage) throws Exception {
        getConnectionStream().onNext(true);
    }

    private synchronized BehaviorSubject<Boolean> getConnectionStream() {
        BehaviorSubject<Boolean> behaviorSubject = this.connectionStream;
        if (behaviorSubject == null || behaviorSubject.hasComplete()) {
            this.connectionStream = BehaviorSubject.createDefault(false);
        }
        return this.connectionStream;
    }

    private synchronized PublishSubject<StompMessage> getMessageStream() {
        PublishSubject<StompMessage> publishSubject = this.messageStream;
        if (publishSubject == null || publishSubject.hasComplete()) {
            this.messageStream = PublishSubject.create();
        }
        return this.messageStream;
    }

    public Completable send(String str) {
        return send(str, (String) null);
    }

    public Completable send(String str, String str2) {
        return send(new StompMessage(StompCommand.SEND, Collections.singletonList(new StompHeader("destination", str)), str2));
    }

    public Completable send(StompMessage stompMessage) {
        return this.connectionProvider.send(stompMessage.compile(this.legacyWhitespace)).startWith((CompletableSource) getConnectionStream().filter(StompClient$$ExternalSyntheticLambda4.INSTANCE).firstElement().ignoreElement());
    }

    /* access modifiers changed from: private */
    public void sendHeartBeat(String str) {
        this.connectionProvider.send(str).startWith((CompletableSource) getConnectionStream().filter(StompClient$$ExternalSyntheticLambda5.INSTANCE).firstElement().ignoreElement()).onErrorComplete().subscribe();
    }

    public Flowable<LifecycleEvent> lifecycle() {
        return this.lifecyclePublishSubject.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void reconnect() {
        disconnectCompletable().subscribe(new StompClient$$ExternalSyntheticLambda9(this), StompClient$$ExternalSyntheticLambda17.INSTANCE);
    }

    /* renamed from: lambda$reconnect$7$ua-naiksoftware-stomp-StompClient  reason: not valid java name */
    public /* synthetic */ void m3178lambda$reconnect$7$uanaiksoftwarestompStompClient() throws Exception {
        connect(this.headers);
    }

    public void disconnect() {
        disconnectCompletable().subscribe(StompClient$$ExternalSyntheticLambda12.INSTANCE, StompClient$$ExternalSyntheticLambda16.INSTANCE);
    }

    public Completable disconnectCompletable() {
        this.heartBeatTask.shutdown();
        Disposable disposable = this.lifecycleDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.messagesDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        return this.connectionProvider.disconnect().doFinally(new StompClient$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$disconnectCompletable$11$ua-naiksoftware-stomp-StompClient */
    public /* synthetic */ void mo42908x31a38683() throws Exception {
        Logger.m45d(TAG, "Stomp disconnected");
        getConnectionStream().onComplete();
        getMessageStream().onComplete();
        this.lifecyclePublishSubject.onNext(new LifecycleEvent(LifecycleEvent.Type.CLOSED));
    }

    public Flowable<StompMessage> topic(String str) {
        return topic(str, (List<StompHeader>) null);
    }

    public Flowable<StompMessage> topic(String str, List<StompHeader> list) {
        if (str == null) {
            return Flowable.error((Throwable) new IllegalArgumentException("Topic path cannot be null"));
        }
        if (!this.streamMap.containsKey(str)) {
            this.streamMap.put(str, subscribePath(str, list).andThen(getMessageStream().filter(new StompClient$$ExternalSyntheticLambda3(this, str)).toFlowable(BackpressureStrategy.BUFFER).share()).doFinally(new StompClient$$ExternalSyntheticLambda10(this, str)));
        }
        return this.streamMap.get(str);
    }

    /* renamed from: lambda$topic$12$ua-naiksoftware-stomp-StompClient  reason: not valid java name */
    public /* synthetic */ boolean m3179lambda$topic$12$uanaiksoftwarestompStompClient(String str, StompMessage stompMessage) throws Exception {
        return this.pathMatcher.matches(str, stompMessage);
    }

    /* renamed from: lambda$topic$13$ua-naiksoftware-stomp-StompClient  reason: not valid java name */
    public /* synthetic */ void m3180lambda$topic$13$uanaiksoftwarestompStompClient(String str) throws Exception {
        unsubscribePath(str).subscribe();
    }

    private Completable subscribePath(String str, List<StompHeader> list) {
        String uuid = UUID.randomUUID().toString();
        if (this.topics == null) {
            this.topics = new ConcurrentHashMap<>();
        }
        if (this.topics.containsKey(str)) {
            Logger.m45d(TAG, "Attempted to subscribe to already-subscribed path!");
            return Completable.complete();
        }
        this.topics.put(str, uuid);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StompHeader(StompHeader.f739ID, uuid));
        arrayList.add(new StompHeader("destination", str));
        arrayList.add(new StompHeader("ack", "auto"));
        if (list != null) {
            arrayList.addAll(list);
        }
        return send(new StompMessage(StompCommand.SUBSCRIBE, arrayList, (String) null));
    }

    private Completable unsubscribePath(String str) {
        this.streamMap.remove(str);
        String str2 = this.topics.get(str);
        this.topics.remove(str);
        String str3 = TAG;
        Logger.m45d(str3, "Unsubscribe path: " + str + " id: " + str2);
        return send(new StompMessage(StompCommand.UNSUBSCRIBE, Collections.singletonList(new StompHeader(StompHeader.f739ID, str2)), (String) null)).onErrorComplete();
    }

    public void setPathMatcher(PathMatcher pathMatcher2) {
        this.pathMatcher = pathMatcher2;
    }

    public boolean isConnected() {
        return getConnectionStream().getValue().booleanValue();
    }

    public void setLegacyWhitespace(boolean z) {
        this.legacyWhitespace = z;
    }
}
