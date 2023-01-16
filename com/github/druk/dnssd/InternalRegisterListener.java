package com.github.druk.dnssd;

interface InternalRegisterListener extends BaseListener {
    void serviceRegistered(DNSSDRegistration dNSSDRegistration, int i, byte[] bArr, byte[] bArr2, byte[] bArr3);
}
