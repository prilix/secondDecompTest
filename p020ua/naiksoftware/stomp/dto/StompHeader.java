package p020ua.naiksoftware.stomp.dto;

/* renamed from: ua.naiksoftware.stomp.dto.StompHeader */
public class StompHeader {
    public static final String ACK = "ack";
    public static final String CONTENT_TYPE = "content-type";
    public static final String DESTINATION = "destination";
    public static final String HEART_BEAT = "heart-beat";

    /* renamed from: ID */
    public static final String f739ID = "id";
    public static final String MESSAGE_ID = "message-id";
    public static final String VERSION = "accept-version";
    private final String mKey;
    private final String mValue;

    public StompHeader(String str, String str2) {
        this.mKey = str;
        this.mValue = str2;
    }

    public String getKey() {
        return this.mKey;
    }

    public String getValue() {
        return this.mValue;
    }

    public String toString() {
        return "StompHeader{" + this.mKey + '=' + this.mValue + '}';
    }
}
