package p020ua.naiksoftware.stomp.dto;

import java.util.TreeMap;

/* renamed from: ua.naiksoftware.stomp.dto.LifecycleEvent */
public class LifecycleEvent {
    private TreeMap<String, String> handshakeResponseHeaders = new TreeMap<>();
    private Exception mException;
    private String mMessage;
    private final Type mType;

    /* renamed from: ua.naiksoftware.stomp.dto.LifecycleEvent$Type */
    public enum Type {
        OPENED,
        CLOSED,
        ERROR,
        FAILED_SERVER_HEARTBEAT
    }

    public LifecycleEvent(Type type) {
        this.mType = type;
    }

    public LifecycleEvent(Type type, Exception exc) {
        this.mType = type;
        this.mException = exc;
    }

    public LifecycleEvent(Type type, String str) {
        this.mType = type;
        this.mMessage = str;
    }

    public Type getType() {
        return this.mType;
    }

    public Exception getException() {
        return this.mException;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setHandshakeResponseHeaders(TreeMap<String, String> treeMap) {
        this.handshakeResponseHeaders = treeMap;
    }

    public TreeMap<String, String> getHandshakeResponseHeaders() {
        return this.handshakeResponseHeaders;
    }
}
