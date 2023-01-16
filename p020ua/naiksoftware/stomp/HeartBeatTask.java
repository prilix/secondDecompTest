package p020ua.naiksoftware.stomp;

import com.accord.common.utils.Logger;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import p012io.reactivex.Scheduler;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.schedulers.Schedulers;
import p020ua.naiksoftware.stomp.dto.StompCommand;
import p020ua.naiksoftware.stomp.dto.StompHeader;
import p020ua.naiksoftware.stomp.dto.StompMessage;

/* renamed from: ua.naiksoftware.stomp.HeartBeatTask */
public class HeartBeatTask {
    private static final String TAG = "HeartBeatTask";
    private int clientHeartbeat = 0;
    private int clientHeartbeatNew = 0;
    private transient Disposable clientSendHeartBeatTask;
    private FailedListener failedListener;
    private transient long lastServerHeartBeat = 0;
    private Scheduler scheduler;
    private SendCallback sendCallback;
    private transient Disposable serverCheckHeartBeatTask;
    private int serverHeartbeat = 0;
    private int serverHeartbeatNew = 0;

    /* renamed from: ua.naiksoftware.stomp.HeartBeatTask$FailedListener */
    public interface FailedListener {
        void onServerHeartBeatFailed();
    }

    /* renamed from: ua.naiksoftware.stomp.HeartBeatTask$SendCallback */
    public interface SendCallback {
        void sendClientHeartBeat(String str);
    }

    public HeartBeatTask(SendCallback sendCallback2, FailedListener failedListener2) {
        this.failedListener = failedListener2;
        this.sendCallback = sendCallback2;
    }

    public void setServerHeartbeat(int i) {
        this.serverHeartbeatNew = i;
    }

    public void setClientHeartbeat(int i) {
        this.clientHeartbeatNew = i;
    }

    public int getServerHeartbeat() {
        return this.serverHeartbeatNew;
    }

    public int getClientHeartbeat() {
        return this.clientHeartbeatNew;
    }

    public boolean consumeHeartBeat(StompMessage stompMessage) {
        String stompCommand = stompMessage.getStompCommand();
        stompCommand.hashCode();
        char c = 65535;
        switch (stompCommand.hashCode()) {
            case -2087582999:
                if (stompCommand.equals(StompCommand.CONNECTED)) {
                    c = 0;
                    break;
                }
                break;
            case 2541448:
                if (stompCommand.equals(StompCommand.SEND)) {
                    c = 1;
                    break;
                }
                break;
            case 433141802:
                if (stompCommand.equals(StompCommand.UNKNOWN)) {
                    c = 2;
                    break;
                }
                break;
            case 1672907751:
                if (stompCommand.equals(StompCommand.MESSAGE)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                heartBeatHandshake(stompMessage.findHeader(StompHeader.HEART_BEAT));
                break;
            case 1:
                abortClientHeartBeatSend();
                break;
            case 2:
                if (StringUtils.f715LF.equals(stompMessage.getPayload())) {
                    Logger.m45d(TAG, "<<< PONG");
                    abortServerHeartBeatCheck();
                    return false;
                }
                break;
            case 3:
                abortServerHeartBeatCheck();
                break;
        }
        return true;
    }

    public void shutdown() {
        Disposable disposable = this.clientSendHeartBeatTask;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.serverCheckHeartBeatTask;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        this.lastServerHeartBeat = 0;
    }

    private void heartBeatHandshake(String str) {
        if (str != null) {
            String[] split = str.split(",");
            int i = this.clientHeartbeatNew;
            if (i > 0) {
                this.clientHeartbeat = Math.max(i, Integer.parseInt(split[1]));
            }
            int i2 = this.serverHeartbeatNew;
            if (i2 > 0) {
                this.serverHeartbeat = Math.max(i2, Integer.parseInt(split[0]));
            }
        }
        if (this.clientHeartbeat > 0 || this.serverHeartbeat > 0) {
            this.scheduler = Schedulers.m691io();
            if (this.clientHeartbeat > 0) {
                String str2 = TAG;
                Logger.m45d(str2, "Client will send heart-beat every " + this.clientHeartbeat + " ms");
                scheduleClientHeartBeat();
            }
            if (this.serverHeartbeat > 0) {
                String str3 = TAG;
                Logger.m45d(str3, "Client will listen to server heart-beat every " + this.serverHeartbeat + " ms");
                scheduleServerHeartBeatCheck();
                this.lastServerHeartBeat = System.currentTimeMillis();
            }
        }
    }

    private void scheduleServerHeartBeatCheck() {
        if (this.serverHeartbeat > 0 && this.scheduler != null) {
            long currentTimeMillis = System.currentTimeMillis();
            String str = TAG;
            Logger.m45d(str, "Scheduling server heart-beat to be checked in " + this.serverHeartbeat + " ms and now is '" + currentTimeMillis + "'");
            this.serverCheckHeartBeatTask = this.scheduler.scheduleDirect(new HeartBeatTask$$ExternalSyntheticLambda1(this), (long) this.serverHeartbeat, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: checkServerHeartBeat */
    public void mo42892x824318c0() {
        if (this.serverHeartbeat > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.lastServerHeartBeat < currentTimeMillis - ((long) (this.serverHeartbeat * 3))) {
                String str = TAG;
                Logger.m45d(str, "It's a sad day ;( Server didn't send heart-beat on time. Last received at '" + this.lastServerHeartBeat + "' and now is '" + currentTimeMillis + "'");
                FailedListener failedListener2 = this.failedListener;
                if (failedListener2 != null) {
                    failedListener2.onServerHeartBeatFailed();
                    return;
                }
                return;
            }
            Logger.m45d(TAG, "We were checking and server sent heart-beat on time. So well-behaved :)");
            this.lastServerHeartBeat = System.currentTimeMillis();
        }
    }

    private void abortServerHeartBeatCheck() {
        this.lastServerHeartBeat = System.currentTimeMillis();
        String str = TAG;
        Logger.m45d(str, "Aborted last check because server sent heart-beat on time ('" + this.lastServerHeartBeat + "'). So well-behaved :)");
        Disposable disposable = this.serverCheckHeartBeatTask;
        if (disposable != null) {
            disposable.dispose();
        }
        scheduleServerHeartBeatCheck();
    }

    private void scheduleClientHeartBeat() {
        if (this.clientHeartbeat > 0 && this.scheduler != null) {
            String str = TAG;
            Logger.m45d(str, "Scheduling client heart-beat to be sent in " + this.clientHeartbeat + " ms");
            this.clientSendHeartBeatTask = this.scheduler.scheduleDirect(new HeartBeatTask$$ExternalSyntheticLambda0(this), (long) this.clientHeartbeat, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: sendClientHeartBeat */
    public void mo42891x6104687b() {
        this.sendCallback.sendClientHeartBeat("\r\n");
        Logger.m45d(TAG, "PING >>>");
        scheduleClientHeartBeat();
    }

    private void abortClientHeartBeatSend() {
        Disposable disposable = this.clientSendHeartBeatTask;
        if (disposable != null) {
            disposable.dispose();
        }
        scheduleClientHeartBeat();
    }
}
