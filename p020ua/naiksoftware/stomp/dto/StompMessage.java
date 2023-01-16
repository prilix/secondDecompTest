package p020ua.naiksoftware.stomp.dto;

import com.accord.common.utils.Logger;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: ua.naiksoftware.stomp.dto.StompMessage */
public class StompMessage {
    private static final Pattern PATTERN_HEADER = Pattern.compile("([^:\\s]+)\\s*:\\s*([^:\\s]+)");
    public static final String TERMINATE_MESSAGE_SYMBOL = "\u0000";
    private final String mPayload;
    private final String mStompCommand;
    private final List<StompHeader> mStompHeaders;

    public StompMessage(String str, List<StompHeader> list, String str2) {
        this.mStompCommand = str;
        this.mStompHeaders = list;
        this.mPayload = str2;
    }

    public List<StompHeader> getStompHeaders() {
        return this.mStompHeaders;
    }

    public String getPayload() {
        return this.mPayload;
    }

    public String getStompCommand() {
        return this.mStompCommand;
    }

    public String findHeader(String str) {
        List<StompHeader> list = this.mStompHeaders;
        if (list == null) {
            return null;
        }
        for (StompHeader next : list) {
            if (next.getKey().equals(str)) {
                return next.getValue();
            }
        }
        return null;
    }

    public String compile() {
        return compile(false);
    }

    public String compile(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mStompCommand);
        sb.append(10);
        for (StompHeader next : this.mStompHeaders) {
            sb.append(next.getKey());
            sb.append(':');
            sb.append(next.getValue());
            sb.append(10);
        }
        sb.append(10);
        String str = this.mPayload;
        if (str != null) {
            sb.append(str);
            if (z) {
                sb.append("\n\n");
            }
        }
        sb.append(TERMINATE_MESSAGE_SYMBOL);
        return sb.toString();
    }

    public static StompMessage from(String str) {
        String str2 = null;
        if (str == null || str.trim().isEmpty()) {
            return new StompMessage(StompCommand.UNKNOWN, (List<StompHeader>) null, str);
        }
        Scanner scanner = new Scanner(new StringReader(str));
        scanner.useDelimiter("\\n");
        String next = scanner.next();
        ArrayList arrayList = new ArrayList();
        while (true) {
            Pattern pattern = PATTERN_HEADER;
            if (!scanner.hasNext(pattern)) {
                break;
            }
            Matcher matcher = pattern.matcher(scanner.next());
            matcher.find();
            arrayList.add(new StompHeader(matcher.group(1), matcher.group(2)));
        }
        try {
            scanner.skip("\n\n");
        } catch (Exception e) {
            Logger.m47e("StompMessage", e.toString());
            Logger.m47e("DATA", str);
            e.printStackTrace();
        }
        scanner.useDelimiter(TERMINATE_MESSAGE_SYMBOL);
        if (scanner.hasNext()) {
            str2 = scanner.next();
        }
        return new StompMessage(next, arrayList, str2);
    }

    public String toString() {
        return "StompMessage{command='" + this.mStompCommand + '\'' + ", headers=" + this.mStompHeaders + ", payload='" + this.mPayload + '\'' + '}';
    }
}
