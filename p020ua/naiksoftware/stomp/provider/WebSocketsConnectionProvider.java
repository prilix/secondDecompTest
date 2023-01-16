package p020ua.naiksoftware.stomp.provider;

import com.accord.common.utils.Logger;
import java.net.URI;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;
import p020ua.naiksoftware.stomp.dto.LifecycleEvent;

/* renamed from: ua.naiksoftware.stomp.provider.WebSocketsConnectionProvider */
public class WebSocketsConnectionProvider extends AbstractConnectionProvider {
    /* access modifiers changed from: private */
    public static final String TAG = "WebSocketsConnectionProvider";
    /* access modifiers changed from: private */
    public boolean haveConnection;
    private final Map<String, String> mConnectHttpHeaders;
    /* access modifiers changed from: private */
    public TreeMap<String, String> mServerHandshakeHeaders;
    private final String mUri;
    private WebSocketClient mWebSocketClient;

    public WebSocketsConnectionProvider(String str, Map<String, String> map) {
        this.mUri = str;
        this.mConnectHttpHeaders = map == null ? new HashMap<>() : map;
    }

    public void rawDisconnect() {
        try {
            this.mWebSocketClient.closeBlocking();
        } catch (InterruptedException e) {
            Logger.m48e(TAG, "Thread interrupted while waiting for Websocket closing: ", e);
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void createWebSocketConnection() {
        if (!this.haveConnection) {
            this.mWebSocketClient = new WebSocketClient(URI.create(this.mUri), new Draft_6455(), this.mConnectHttpHeaders, 0) {
                public void onWebsocketHandshakeReceivedAsClient(WebSocket webSocket, ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidDataException {
                    String r3 = WebSocketsConnectionProvider.TAG;
                    Logger.m45d(r3, "onWebsocketHandshakeReceivedAsClient with response: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
                    WebSocketsConnectionProvider.this.mServerHandshakeHeaders = new TreeMap();
                    Iterator<String> iterateHttpFields = serverHandshake.iterateHttpFields();
                    while (iterateHttpFields.hasNext()) {
                        String next = iterateHttpFields.next();
                        WebSocketsConnectionProvider.this.mServerHandshakeHeaders.put(next, serverHandshake.getFieldValue(next));
                    }
                }

                public void onOpen(ServerHandshake serverHandshake) {
                    String r0 = WebSocketsConnectionProvider.TAG;
                    Logger.m45d(r0, "onOpen with handshakeData: " + serverHandshake.getHttpStatus() + " " + serverHandshake.getHttpStatusMessage());
                    LifecycleEvent lifecycleEvent = new LifecycleEvent(LifecycleEvent.Type.OPENED);
                    lifecycleEvent.setHandshakeResponseHeaders(WebSocketsConnectionProvider.this.mServerHandshakeHeaders);
                    WebSocketsConnectionProvider.this.emitLifecycleEvent(lifecycleEvent);
                }

                public void onMessage(String str) {
                    String r0 = WebSocketsConnectionProvider.TAG;
                    Logger.m45d(r0, "onMessage: " + str);
                    WebSocketsConnectionProvider.this.emitMessage(str);
                }

                public void onClose(int i, String str, boolean z) {
                    String r0 = WebSocketsConnectionProvider.TAG;
                    Logger.m45d(r0, "onClose: code=" + i + " reason=" + str + " remote=" + z);
                    WebSocketsConnectionProvider.this.haveConnection = false;
                    WebSocketsConnectionProvider.this.emitLifecycleEvent(new LifecycleEvent(LifecycleEvent.Type.CLOSED));
                    Logger.m45d(WebSocketsConnectionProvider.TAG, "Disconnect after close.");
                    WebSocketsConnectionProvider.this.disconnect();
                }

                public void onError(Exception exc) {
                    Logger.m48e(WebSocketsConnectionProvider.TAG, "onError", exc);
                    WebSocketsConnectionProvider.this.emitLifecycleEvent(new LifecycleEvent(LifecycleEvent.Type.ERROR, exc));
                }
            };
            if (this.mUri.startsWith("wss")) {
                try {
                    SSLContext instance = SSLContext.getInstance("TLS");
                    instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
                    this.mWebSocketClient.setSocket(instance.getSocketFactory().createSocket());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.mWebSocketClient.connect();
            this.haveConnection = true;
            return;
        }
        throw new IllegalStateException("Already have connection to web socket");
    }

    /* access modifiers changed from: protected */
    public void rawSend(String str) {
        this.mWebSocketClient.send(str);
    }

    /* access modifiers changed from: protected */
    public Object getSocket() {
        return this.mWebSocketClient;
    }
}
