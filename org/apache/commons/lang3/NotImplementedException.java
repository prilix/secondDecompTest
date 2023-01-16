package org.apache.commons.lang3;

public class NotImplementedException extends UnsupportedOperationException {
    private static final long serialVersionUID = 20131021;
    private final String code;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NotImplementedException(String str) {
        this(str, (String) null);
        String str2 = null;
    }

    public NotImplementedException(Throwable th) {
        this(th, (String) null);
    }

    public NotImplementedException(String str, Throwable th) {
        this(str, th, (String) null);
    }

    public NotImplementedException(String str, String str2) {
        super(str);
        this.code = str2;
    }

    public NotImplementedException(Throwable th, String str) {
        super(th);
        this.code = str;
    }

    public NotImplementedException(String str, Throwable th, String str2) {
        super(str, th);
        this.code = str2;
    }

    public String getCode() {
        return this.code;
    }
}
