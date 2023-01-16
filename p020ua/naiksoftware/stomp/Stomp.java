package p020ua.naiksoftware.stomp;

import java.util.Map;
import okhttp3.OkHttpClient;
import p020ua.naiksoftware.stomp.provider.OkHttpConnectionProvider;
import p020ua.naiksoftware.stomp.provider.WebSocketsConnectionProvider;

/* renamed from: ua.naiksoftware.stomp.Stomp */
public class Stomp {

    /* renamed from: ua.naiksoftware.stomp.Stomp$ConnectionProvider */
    public enum ConnectionProvider {
        OKHTTP,
        JWS
    }

    public static StompClient over(ConnectionProvider connectionProvider, String str) {
        return over(connectionProvider, str, (Map<String, String>) null, (OkHttpClient) null);
    }

    public static StompClient over(ConnectionProvider connectionProvider, String str, Map<String, String> map) {
        return over(connectionProvider, str, map, (OkHttpClient) null);
    }

    public static StompClient over(ConnectionProvider connectionProvider, String str, Map<String, String> map, OkHttpClient okHttpClient) {
        if (connectionProvider == ConnectionProvider.JWS) {
            if (okHttpClient == null) {
                return createStompClient(new WebSocketsConnectionProvider(str, map));
            }
            throw new IllegalArgumentException("You cannot pass an OkHttpClient when using JWS. Use null instead.");
        } else if (connectionProvider == ConnectionProvider.OKHTTP) {
            if (okHttpClient == null) {
                okHttpClient = new OkHttpClient();
            }
            return createStompClient(new OkHttpConnectionProvider(str, map, okHttpClient));
        } else {
            throw new IllegalArgumentException("ConnectionProvider type not supported: " + connectionProvider.toString());
        }
    }

    private static StompClient createStompClient(p020ua.naiksoftware.stomp.provider.ConnectionProvider connectionProvider) {
        return new StompClient(connectionProvider);
    }
}
