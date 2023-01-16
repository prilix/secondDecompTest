package org.java_websocket.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.SSLException;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketListener;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;

public abstract class WebSocketClient extends AbstractWebSocket implements Runnable, WebSocket {
    private CountDownLatch closeLatch;
    private CountDownLatch connectLatch;
    private Thread connectReadThread;
    private int connectTimeout;
    private Draft draft;
    /* access modifiers changed from: private */
    public WebSocketImpl engine;
    private Map<String, String> headers;
    /* access modifiers changed from: private */
    public OutputStream ostream;
    private Proxy proxy;
    /* access modifiers changed from: private */
    public Socket socket;
    private SocketFactory socketFactory;
    protected URI uri;
    /* access modifiers changed from: private */
    public Thread writeThread;

    public abstract void onClose(int i, String str, boolean z);

    public void onCloseInitiated(int i, String str) {
    }

    public void onClosing(int i, String str, boolean z) {
    }

    public abstract void onError(Exception exc);

    public abstract void onMessage(String str);

    public void onMessage(ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(ServerHandshake serverHandshake);

    public final void onWriteDemand(WebSocket webSocket) {
    }

    public WebSocketClient(URI uri2) {
        this(uri2, (Draft) new Draft_6455());
    }

    public WebSocketClient(URI uri2, Draft draft2) {
        this(uri2, draft2, (Map<String, String>) null, 0);
    }

    public WebSocketClient(URI uri2, Map<String, String> map) {
        this(uri2, new Draft_6455(), map);
    }

    public WebSocketClient(URI uri2, Draft draft2, Map<String, String> map) {
        this(uri2, draft2, map, 0);
    }

    public WebSocketClient(URI uri2, Draft draft2, Map<String, String> map, int i) {
        this.uri = null;
        this.engine = null;
        this.socket = null;
        this.socketFactory = null;
        this.proxy = Proxy.NO_PROXY;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.connectTimeout = 0;
        if (uri2 == null) {
            throw new IllegalArgumentException();
        } else if (draft2 != null) {
            this.uri = uri2;
            this.draft = draft2;
            this.headers = map;
            this.connectTimeout = i;
            setTcpNoDelay(false);
            setReuseAddr(false);
            this.engine = new WebSocketImpl((WebSocketListener) this, draft2);
        } else {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        }
    }

    public URI getURI() {
        return this.uri;
    }

    public Draft getDraft() {
        return this.draft;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void reconnect() {
        reset();
        connect();
    }

    public boolean reconnectBlocking() throws InterruptedException {
        reset();
        return connectBlocking();
    }

    private void reset() {
        Thread currentThread = Thread.currentThread();
        if (currentThread == this.writeThread || currentThread == this.connectReadThread) {
            throw new IllegalStateException("You cannot initialize a reconnect out of the websocket thread. Use reconnect in another thread to insure a successful cleanup.");
        }
        try {
            closeBlocking();
            Thread thread = this.writeThread;
            if (thread != null) {
                thread.interrupt();
                this.writeThread = null;
            }
            Thread thread2 = this.connectReadThread;
            if (thread2 != null) {
                thread2.interrupt();
                this.connectReadThread = null;
            }
            this.draft.reset();
            Socket socket2 = this.socket;
            if (socket2 != null) {
                socket2.close();
                this.socket = null;
            }
            this.connectLatch = new CountDownLatch(1);
            this.closeLatch = new CountDownLatch(1);
            this.engine = new WebSocketImpl((WebSocketListener) this, this.draft);
        } catch (Exception e) {
            onError(e);
            this.engine.closeConnection(1006, e.getMessage());
        }
    }

    public void connect() {
        if (this.connectReadThread == null) {
            Thread thread = new Thread(this);
            this.connectReadThread = thread;
            thread.setName("WebSocketConnectReadThread-" + this.connectReadThread.getId());
            this.connectReadThread.start();
            return;
        }
        throw new IllegalStateException("WebSocketClient objects are not reuseable");
    }

    public boolean connectBlocking() throws InterruptedException {
        connect();
        this.connectLatch.await();
        return this.engine.isOpen();
    }

    public boolean connectBlocking(long j, TimeUnit timeUnit) throws InterruptedException {
        connect();
        return this.connectLatch.await(j, timeUnit) && this.engine.isOpen();
    }

    public void close() {
        if (this.writeThread != null) {
            this.engine.close(1000);
        }
    }

    public void closeBlocking() throws InterruptedException {
        close();
        this.closeLatch.await();
    }

    public void send(String str) {
        this.engine.send(str);
    }

    public void send(byte[] bArr) {
        this.engine.send(bArr);
    }

    public <T> T getAttachment() {
        return this.engine.getAttachment();
    }

    public <T> void setAttachment(T t) {
        this.engine.setAttachment(t);
    }

    /* access modifiers changed from: protected */
    public Collection<WebSocket> getConnections() {
        return Collections.singletonList(this.engine);
    }

    public void sendPing() {
        this.engine.sendPing();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e A[Catch:{ Exception -> 0x00e7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ba A[Catch:{ IOException -> 0x00da, RuntimeException -> 0x00ca }, LOOP:0: B:21:0x00a8->B:28:0x00ba, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r8 = this;
            r0 = -1
            javax.net.SocketFactory r1 = r8.socketFactory     // Catch:{ Exception -> 0x00e7 }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x000e
            java.net.Socket r1 = r1.createSocket()     // Catch:{ Exception -> 0x00e7 }
            r8.socket = r1     // Catch:{ Exception -> 0x00e7 }
            goto L_0x0023
        L_0x000e:
            java.net.Socket r1 = r8.socket     // Catch:{ Exception -> 0x00e7 }
            if (r1 != 0) goto L_0x001d
            java.net.Socket r1 = new java.net.Socket     // Catch:{ Exception -> 0x00e7 }
            java.net.Proxy r4 = r8.proxy     // Catch:{ Exception -> 0x00e7 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x00e7 }
            r8.socket = r1     // Catch:{ Exception -> 0x00e7 }
            r1 = 1
            goto L_0x0024
        L_0x001d:
            boolean r1 = r1.isClosed()     // Catch:{ Exception -> 0x00e7 }
            if (r1 != 0) goto L_0x00e1
        L_0x0023:
            r1 = 0
        L_0x0024:
            java.net.Socket r4 = r8.socket     // Catch:{ Exception -> 0x00e7 }
            boolean r5 = r8.isTcpNoDelay()     // Catch:{ Exception -> 0x00e7 }
            r4.setTcpNoDelay(r5)     // Catch:{ Exception -> 0x00e7 }
            java.net.Socket r4 = r8.socket     // Catch:{ Exception -> 0x00e7 }
            boolean r5 = r8.isReuseAddr()     // Catch:{ Exception -> 0x00e7 }
            r4.setReuseAddress(r5)     // Catch:{ Exception -> 0x00e7 }
            java.net.Socket r4 = r8.socket     // Catch:{ Exception -> 0x00e7 }
            boolean r4 = r4.isBound()     // Catch:{ Exception -> 0x00e7 }
            if (r4 != 0) goto L_0x0054
            java.net.Socket r4 = r8.socket     // Catch:{ Exception -> 0x00e7 }
            java.net.InetSocketAddress r5 = new java.net.InetSocketAddress     // Catch:{ Exception -> 0x00e7 }
            java.net.URI r6 = r8.uri     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r6 = r6.getHost()     // Catch:{ Exception -> 0x00e7 }
            int r7 = r8.getPort()     // Catch:{ Exception -> 0x00e7 }
            r5.<init>(r6, r7)     // Catch:{ Exception -> 0x00e7 }
            int r6 = r8.connectTimeout     // Catch:{ Exception -> 0x00e7 }
            r4.connect(r5, r6)     // Catch:{ Exception -> 0x00e7 }
        L_0x0054:
            r4 = 0
            if (r1 == 0) goto L_0x0084
            java.lang.String r1 = "wss"
            java.net.URI r5 = r8.uri     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r5 = r5.getScheme()     // Catch:{ Exception -> 0x00e7 }
            boolean r1 = r1.equals(r5)     // Catch:{ Exception -> 0x00e7 }
            if (r1 == 0) goto L_0x0084
            java.lang.String r1 = "TLSv1.2"
            javax.net.ssl.SSLContext r1 = javax.net.ssl.SSLContext.getInstance(r1)     // Catch:{ Exception -> 0x00e7 }
            r1.init(r4, r4, r4)     // Catch:{ Exception -> 0x00e7 }
            javax.net.ssl.SSLSocketFactory r1 = r1.getSocketFactory()     // Catch:{ Exception -> 0x00e7 }
            java.net.Socket r5 = r8.socket     // Catch:{ Exception -> 0x00e7 }
            java.net.URI r6 = r8.uri     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r6 = r6.getHost()     // Catch:{ Exception -> 0x00e7 }
            int r7 = r8.getPort()     // Catch:{ Exception -> 0x00e7 }
            java.net.Socket r1 = r1.createSocket(r5, r6, r7, r2)     // Catch:{ Exception -> 0x00e7 }
            r8.socket = r1     // Catch:{ Exception -> 0x00e7 }
        L_0x0084:
            java.net.Socket r1 = r8.socket     // Catch:{ Exception -> 0x00e7 }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ Exception -> 0x00e7 }
            java.net.Socket r2 = r8.socket     // Catch:{ Exception -> 0x00e7 }
            java.io.OutputStream r2 = r2.getOutputStream()     // Catch:{ Exception -> 0x00e7 }
            r8.ostream = r2     // Catch:{ Exception -> 0x00e7 }
            r8.sendHandshake()     // Catch:{ Exception -> 0x00e7 }
            java.lang.Thread r2 = new java.lang.Thread
            org.java_websocket.client.WebSocketClient$WebsocketWriteThread r5 = new org.java_websocket.client.WebSocketClient$WebsocketWriteThread
            r5.<init>(r8)
            r2.<init>(r5)
            r8.writeThread = r2
            r2.start()
            r2 = 16384(0x4000, float:2.2959E-41)
            byte[] r2 = new byte[r2]
        L_0x00a8:
            boolean r5 = r8.isClosing()     // Catch:{ IOException -> 0x00da, RuntimeException -> 0x00ca }
            if (r5 != 0) goto L_0x00c4
            boolean r5 = r8.isClosed()     // Catch:{ IOException -> 0x00da, RuntimeException -> 0x00ca }
            if (r5 != 0) goto L_0x00c4
            int r5 = r1.read(r2)     // Catch:{ IOException -> 0x00da, RuntimeException -> 0x00ca }
            if (r5 == r0) goto L_0x00c4
            org.java_websocket.WebSocketImpl r6 = r8.engine     // Catch:{ IOException -> 0x00da, RuntimeException -> 0x00ca }
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.wrap(r2, r3, r5)     // Catch:{ IOException -> 0x00da, RuntimeException -> 0x00ca }
            r6.decode(r5)     // Catch:{ IOException -> 0x00da, RuntimeException -> 0x00ca }
            goto L_0x00a8
        L_0x00c4:
            org.java_websocket.WebSocketImpl r0 = r8.engine     // Catch:{ IOException -> 0x00da, RuntimeException -> 0x00ca }
            r0.eot()     // Catch:{ IOException -> 0x00da, RuntimeException -> 0x00ca }
            goto L_0x00de
        L_0x00ca:
            r0 = move-exception
            r8.onError(r0)
            org.java_websocket.WebSocketImpl r1 = r8.engine
            r2 = 1006(0x3ee, float:1.41E-42)
            java.lang.String r0 = r0.getMessage()
            r1.closeConnection((int) r2, (java.lang.String) r0)
            goto L_0x00de
        L_0x00da:
            r0 = move-exception
            r8.handleIOException(r0)
        L_0x00de:
            r8.connectReadThread = r4
            return
        L_0x00e1:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ Exception -> 0x00e7 }
            r1.<init>()     // Catch:{ Exception -> 0x00e7 }
            throw r1     // Catch:{ Exception -> 0x00e7 }
        L_0x00e7:
            r1 = move-exception
            org.java_websocket.WebSocketImpl r2 = r8.engine
            r8.onWebsocketError(r2, r1)
            org.java_websocket.WebSocketImpl r2 = r8.engine
            java.lang.String r1 = r1.getMessage()
            r2.closeConnection((int) r0, (java.lang.String) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.client.WebSocketClient.run():void");
    }

    private int getPort() {
        int port = this.uri.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.uri.getScheme();
        if ("wss".equals(scheme)) {
            return 443;
        }
        if ("ws".equals(scheme)) {
            return 80;
        }
        throw new IllegalArgumentException("unknown scheme: " + scheme);
    }

    private void sendHandshake() throws InvalidHandshakeException {
        String str;
        String rawPath = this.uri.getRawPath();
        String rawQuery = this.uri.getRawQuery();
        if (rawPath == null || rawPath.length() == 0) {
            rawPath = "/";
        }
        if (rawQuery != null) {
            rawPath = rawPath + '?' + rawQuery;
        }
        int port = getPort();
        StringBuilder sb = new StringBuilder();
        sb.append(this.uri.getHost());
        if (port == 80 || port == 443) {
            str = "";
        } else {
            str = ":" + port;
        }
        sb.append(str);
        String sb2 = sb.toString();
        HandshakeImpl1Client handshakeImpl1Client = new HandshakeImpl1Client();
        handshakeImpl1Client.setResourceDescriptor(rawPath);
        handshakeImpl1Client.put("Host", sb2);
        Map<String, String> map = this.headers;
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                handshakeImpl1Client.put((String) next.getKey(), (String) next.getValue());
            }
        }
        this.engine.startHandshake(handshakeImpl1Client);
    }

    public ReadyState getReadyState() {
        return this.engine.getReadyState();
    }

    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(str);
    }

    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(byteBuffer);
    }

    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        startConnectionLostTimer();
        onOpen((ServerHandshake) handshakedata);
        this.connectLatch.countDown();
    }

    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        stopConnectionLostTimer();
        Thread thread = this.writeThread;
        if (thread != null) {
            thread.interrupt();
        }
        onClose(i, str, z);
        this.connectLatch.countDown();
        this.closeLatch.countDown();
    }

    public final void onWebsocketError(WebSocket webSocket, Exception exc) {
        onError(exc);
    }

    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(i, str);
    }

    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(i, str, z);
    }

    public WebSocket getConnection() {
        return this.engine;
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        Socket socket2 = this.socket;
        if (socket2 != null) {
            return (InetSocketAddress) socket2.getLocalSocketAddress();
        }
        return null;
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        Socket socket2 = this.socket;
        if (socket2 != null) {
            return (InetSocketAddress) socket2.getRemoteSocketAddress();
        }
        return null;
    }

    private class WebsocketWriteThread implements Runnable {
        private final WebSocketClient webSocketClient;

        WebsocketWriteThread(WebSocketClient webSocketClient2) {
            this.webSocketClient = webSocketClient2;
        }

        public void run() {
            Thread currentThread = Thread.currentThread();
            currentThread.setName("WebSocketWriteThread-" + Thread.currentThread().getId());
            try {
                runWriteData();
            } catch (IOException e) {
                WebSocketClient.this.handleIOException(e);
            } catch (Throwable th) {
                closeSocket();
                Thread unused = WebSocketClient.this.writeThread = null;
                throw th;
            }
            closeSocket();
            Thread unused2 = WebSocketClient.this.writeThread = null;
        }

        private void runWriteData() throws IOException {
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer take = WebSocketClient.this.engine.outQueue.take();
                    WebSocketClient.this.ostream.write(take.array(), 0, take.limit());
                    WebSocketClient.this.ostream.flush();
                } catch (InterruptedException unused) {
                    for (ByteBuffer byteBuffer : WebSocketClient.this.engine.outQueue) {
                        WebSocketClient.this.ostream.write(byteBuffer.array(), 0, byteBuffer.limit());
                        WebSocketClient.this.ostream.flush();
                    }
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        private void closeSocket() {
            try {
                if (WebSocketClient.this.socket != null) {
                    WebSocketClient.this.socket.close();
                }
            } catch (IOException e) {
                WebSocketClient.this.onWebsocketError(this.webSocketClient, e);
            }
        }
    }

    public void setProxy(Proxy proxy2) {
        if (proxy2 != null) {
            this.proxy = proxy2;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Deprecated
    public void setSocket(Socket socket2) {
        if (this.socket == null) {
            this.socket = socket2;
            return;
        }
        throw new IllegalStateException("socket has already been set");
    }

    public void setSocketFactory(SocketFactory socketFactory2) {
        this.socketFactory = socketFactory2;
    }

    public void sendFragmentedFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        this.engine.sendFragmentedFrame(opcode, byteBuffer, z);
    }

    public boolean isOpen() {
        return this.engine.isOpen();
    }

    public boolean isFlushAndClose() {
        return this.engine.isFlushAndClose();
    }

    public boolean isClosed() {
        return this.engine.isClosed();
    }

    public boolean isClosing() {
        return this.engine.isClosing();
    }

    public boolean hasBufferedData() {
        return this.engine.hasBufferedData();
    }

    public void close(int i) {
        this.engine.close(i);
    }

    public void close(int i, String str) {
        this.engine.close(i, str);
    }

    public void closeConnection(int i, String str) {
        this.engine.closeConnection(i, str);
    }

    public void send(ByteBuffer byteBuffer) {
        this.engine.send(byteBuffer);
    }

    public void sendFrame(Framedata framedata) {
        this.engine.sendFrame(framedata);
    }

    public void sendFrame(Collection<Framedata> collection) {
        this.engine.sendFrame(collection);
    }

    public InetSocketAddress getLocalSocketAddress() {
        return this.engine.getLocalSocketAddress();
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return this.engine.getRemoteSocketAddress();
    }

    public String getResourceDescriptor() {
        return this.uri.getPath();
    }

    /* access modifiers changed from: private */
    public void handleIOException(IOException iOException) {
        if (iOException instanceof SSLException) {
            onError(iOException);
        }
        this.engine.eot();
    }
}
