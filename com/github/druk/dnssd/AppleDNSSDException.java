package com.github.druk.dnssd;

import p020ua.naiksoftware.stomp.dto.StompCommand;

/* compiled from: InternalDNSSD */
class AppleDNSSDException extends DNSSDException {
    protected int fErrorCode;

    public AppleDNSSDException(int i) {
        this.fErrorCode = i;
    }

    public int getErrorCode() {
        return this.fErrorCode;
    }

    public String getMessage() {
        String[] strArr = {StompCommand.UNKNOWN, "NO_SUCH_NAME", "NO_MEMORY", "BAD_PARAM", "BAD_REFERENCE", "BAD_STATE", "BAD_FLAGS", "UNSUPPORTED", "NOT_INITIALIZED", "NO_CACHE", "ALREADY_REGISTERED", "NAME_CONFLICT", "INVALID", "FIREWALL", "INCOMPATIBLE", "BAD_INTERFACE_INDEX", "REFUSED", "NOSUCHRECORD", "NOAUTH", "NOSUCHKEY", "NATTRAVERSAL", "DOUBLENAT", "BADTIME", "BADSIG", "BADKEY", "TRANSIENT", "SERVICENOTRUNNING", "NATPORTMAPPINGUNSUPPORTED", "NATPORTMAPPINGDISABLED"};
        int i = this.fErrorCode;
        if (i > -65537 || i <= -65566) {
            return super.getMessage() + "(" + String.valueOf(this.fErrorCode) + ")";
        }
        return "DNS-SD Error " + String.valueOf(this.fErrorCode) + ": " + strArr[DNSSDException.UNKNOWN - this.fErrorCode];
    }
}
