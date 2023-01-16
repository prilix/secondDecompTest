package p020ua.naiksoftware.stomp.pathmatcher;

import p020ua.naiksoftware.stomp.dto.StompMessage;

/* renamed from: ua.naiksoftware.stomp.pathmatcher.SimplePathMatcher */
public class SimplePathMatcher implements PathMatcher {
    public boolean matches(String str, StompMessage stompMessage) {
        String findHeader = stompMessage.findHeader("destination");
        if (findHeader == null) {
            return false;
        }
        return str.equals(findHeader);
    }
}
