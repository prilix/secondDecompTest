package p020ua.naiksoftware.stomp.pathmatcher;

import p020ua.naiksoftware.stomp.dto.StompMessage;

/* renamed from: ua.naiksoftware.stomp.pathmatcher.PathMatcher */
public interface PathMatcher {
    boolean matches(String str, StompMessage stompMessage);
}
