package p020ua.naiksoftware.stomp.pathmatcher;

import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.Marker;
import p020ua.naiksoftware.stomp.dto.StompMessage;

/* renamed from: ua.naiksoftware.stomp.pathmatcher.RabbitPathMatcher */
public class RabbitPathMatcher implements PathMatcher {
    public boolean matches(String str, StompMessage stompMessage) {
        String findHeader = stompMessage.findHeader("destination");
        if (findHeader == null) {
            return false;
        }
        String[] split = str.split("\\.");
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            str2.hashCode();
            if (str2.equals("#")) {
                arrayList.add(".*");
            } else if (!str2.equals(Marker.ANY_MARKER)) {
                arrayList.add(str2.replaceAll("\\*", ".*"));
            } else {
                arrayList.add("[^.]+");
            }
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            if (sb.length() > 0) {
                sb.append("\\.");
            }
            sb.append(str3);
        }
        return findHeader.matches(sb.toString());
    }
}
