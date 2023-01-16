package org.java_websocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWebSocket extends WebSocketAdapter {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) AbstractWebSocket.class);
    /* access modifiers changed from: private */
    public int connectionLostTimeout = 60;
    private Timer connectionLostTimer;
    private TimerTask connectionLostTimerTask;
    private boolean reuseAddr;
    private final Object syncConnectionLost = new Object();
    private boolean tcpNoDelay;
    private boolean websocketRunning = false;

    /* access modifiers changed from: protected */
    public abstract Collection<WebSocket> getConnections();

    public int getConnectionLostTimeout() {
        int i;
        synchronized (this.syncConnectionLost) {
            i = this.connectionLostTimeout;
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setConnectionLostTimeout(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.syncConnectionLost
            monitor-enter(r0)
            r3.connectionLostTimeout = r4     // Catch:{ all -> 0x004e }
            if (r4 > 0) goto L_0x0013
            org.slf4j.Logger r4 = log     // Catch:{ all -> 0x004e }
            java.lang.String r1 = "Connection lost timer stopped"
            r4.trace(r1)     // Catch:{ all -> 0x004e }
            r3.cancelConnectionLostTimer()     // Catch:{ all -> 0x004e }
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return
        L_0x0013:
            boolean r4 = r3.websocketRunning     // Catch:{ all -> 0x004e }
            if (r4 == 0) goto L_0x004c
            org.slf4j.Logger r4 = log     // Catch:{ all -> 0x004e }
            java.lang.String r1 = "Connection lost timer restarted"
            r4.trace(r1)     // Catch:{ all -> 0x004e }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x0041 }
            java.util.Collection r1 = r3.getConnections()     // Catch:{ Exception -> 0x0041 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0041 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x0041 }
        L_0x002b:
            boolean r1 = r4.hasNext()     // Catch:{ Exception -> 0x0041 }
            if (r1 == 0) goto L_0x0049
            java.lang.Object r1 = r4.next()     // Catch:{ Exception -> 0x0041 }
            org.java_websocket.WebSocket r1 = (org.java_websocket.WebSocket) r1     // Catch:{ Exception -> 0x0041 }
            boolean r2 = r1 instanceof org.java_websocket.WebSocketImpl     // Catch:{ Exception -> 0x0041 }
            if (r2 == 0) goto L_0x002b
            org.java_websocket.WebSocketImpl r1 = (org.java_websocket.WebSocketImpl) r1     // Catch:{ Exception -> 0x0041 }
            r1.updateLastPong()     // Catch:{ Exception -> 0x0041 }
            goto L_0x002b
        L_0x0041:
            r4 = move-exception
            org.slf4j.Logger r1 = log     // Catch:{ all -> 0x004e }
            java.lang.String r2 = "Exception during connection lost restart"
            r1.error((java.lang.String) r2, (java.lang.Throwable) r4)     // Catch:{ all -> 0x004e }
        L_0x0049:
            r3.restartConnectionLostTimer()     // Catch:{ all -> 0x004e }
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return
        L_0x004e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.AbstractWebSocket.setConnectionLostTimeout(int):void");
    }

    /* access modifiers changed from: protected */
    public void stopConnectionLostTimer() {
        synchronized (this.syncConnectionLost) {
            if (!(this.connectionLostTimer == null && this.connectionLostTimerTask == null)) {
                this.websocketRunning = false;
                log.trace("Connection lost timer stopped");
                cancelConnectionLostTimer();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void startConnectionLostTimer() {
        synchronized (this.syncConnectionLost) {
            if (this.connectionLostTimeout <= 0) {
                log.trace("Connection lost timer deactivated");
                return;
            }
            log.trace("Connection lost timer started");
            this.websocketRunning = true;
            restartConnectionLostTimer();
        }
    }

    private void restartConnectionLostTimer() {
        cancelConnectionLostTimer();
        this.connectionLostTimer = new Timer("WebSocketTimer");
        C30341 r3 = new TimerTask() {
            private ArrayList<WebSocket> connections = new ArrayList<>();

            public void run() {
                this.connections.clear();
                try {
                    this.connections.addAll(AbstractWebSocket.this.getConnections());
                    long currentTimeMillis = System.currentTimeMillis() - ((long) (AbstractWebSocket.this.connectionLostTimeout * 1500));
                    Iterator<WebSocket> it = this.connections.iterator();
                    while (it.hasNext()) {
                        AbstractWebSocket.this.executeConnectionLostDetection(it.next(), currentTimeMillis);
                    }
                } catch (Exception unused) {
                }
                this.connections.clear();
            }
        };
        this.connectionLostTimerTask = r3;
        Timer timer = this.connectionLostTimer;
        int i = this.connectionLostTimeout;
        timer.scheduleAtFixedRate(r3, ((long) i) * 1000, 1000 * ((long) i));
    }

    /* access modifiers changed from: private */
    public void executeConnectionLostDetection(WebSocket webSocket, long j) {
        if (webSocket instanceof WebSocketImpl) {
            WebSocketImpl webSocketImpl = (WebSocketImpl) webSocket;
            if (webSocketImpl.getLastPong() < j) {
                log.trace("Closing connection due to no pong received: {}", (Object) webSocketImpl);
                webSocketImpl.closeConnection(1006, "The connection was closed because the other endpoint did not respond with a pong in time. For more information check: https://github.com/TooTallNate/Java-WebSocket/wiki/Lost-connection-detection");
            } else if (webSocketImpl.isOpen()) {
                webSocketImpl.sendPing();
            } else {
                log.trace("Trying to ping a non open connection: {}", (Object) webSocketImpl);
            }
        }
    }

    private void cancelConnectionLostTimer() {
        Timer timer = this.connectionLostTimer;
        if (timer != null) {
            timer.cancel();
            this.connectionLostTimer = null;
        }
        TimerTask timerTask = this.connectionLostTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.connectionLostTimerTask = null;
        }
    }

    public boolean isTcpNoDelay() {
        return this.tcpNoDelay;
    }

    public void setTcpNoDelay(boolean z) {
        this.tcpNoDelay = z;
    }

    public boolean isReuseAddr() {
        return this.reuseAddr;
    }

    public void setReuseAddr(boolean z) {
        this.reuseAddr = z;
    }
}
