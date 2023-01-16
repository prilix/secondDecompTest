package p020ua.naiksoftware.stomp.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import p020ua.naiksoftware.stomp.dto.LifecycleEvent;

/* renamed from: ua.naiksoftware.stomp.provider.OkHttpConnectionProvider */
public class OkHttpConnectionProvider extends AbstractConnectionProvider {
    public static final String TAG = "OkHttpConnProvider";
    private final Map<String, String> mConnectHttpHeaders;
    private final OkHttpClient mOkHttpClient;
    private final String mUri;
    /* access modifiers changed from: private */
    public WebSocket openSocket;

    public OkHttpConnectionProvider(String str, Map<String, String> map, OkHttpClient okHttpClient) {
        this.mUri = str;
        this.mConnectHttpHeaders = map == null ? new HashMap<>() : map;
        this.mOkHttpClient = okHttpClient;
    }

    public void rawDisconnect() {
        WebSocket webSocket = this.openSocket;
        if (webSocket != null) {
            webSocket.close(1000, "");
        }
    }

    /* access modifiers changed from: protected */
    public void createWebSocketConnection() {
        Request.Builder url = new Request.Builder().url(this.mUri);
        addConnectionHeadersToBuilder(url, this.mConnectHttpHeaders);
        this.openSocket = this.mOkHttpClient.newWebSocket(url.build(), new WebSocketListener() {
            public void onOpen(WebSocket webSocket, Response response) {
                LifecycleEvent lifecycleEvent = new LifecycleEvent(LifecycleEvent.Type.OPENED);
                lifecycleEvent.setHandshakeResponseHeaders(OkHttpConnectionProvider.this.headersAsMap(response));
                OkHttpConnectionProvider.this.emitLifecycleEvent(lifecycleEvent);
            }

            public void onMessage(WebSocket webSocket, String str) {
                OkHttpConnectionProvider.this.emitMessage(str);
            }

            public void onMessage(WebSocket webSocket, ByteString byteString) {
                OkHttpConnectionProvider.this.emitMessage(byteString.utf8());
            }

            public void onClosed(WebSocket webSocket, int i, String str) {
                OkHttpConnectionProvider.this.openSocket = null;
                OkHttpConnectionProvider.this.emitLifecycleEvent(new LifecycleEvent(LifecycleEvent.Type.CLOSED));
            }

            public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                OkHttpConnectionProvider.this.emitLifecycleEvent(new LifecycleEvent(LifecycleEvent.Type.ERROR, new Exception(th)));
                OkHttpConnectionProvider.this.openSocket = null;
                OkHttpConnectionProvider.this.emitLifecycleEvent(new LifecycleEvent(LifecycleEvent.Type.CLOSED));
            }

            public void onClosing(WebSocket webSocket, int i, String str) {
                webSocket.close(i, str);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void rawSend(String str) {
        this.openSocket.send(str);
    }

    /* access modifiers changed from: protected */
    public Object getSocket() {
        return this.openSocket;
    }

    /* access modifiers changed from: private */
    public TreeMap<String, String> headersAsMap(Response response) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        Headers headers = response.headers();
        for (String next : headers.names()) {
            treeMap.put(next, headers.get(next));
        }
        return treeMap;
    }

    private void addConnectionHeadersToBuilder(Request.Builder builder, Map<String, String> map) {
        for (Map.Entry next : map.entrySet()) {
            builder.addHeader((String) next.getKey(), (String) next.getValue());
        }
    }
}
