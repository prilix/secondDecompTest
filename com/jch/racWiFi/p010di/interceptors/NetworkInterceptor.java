package com.jch.racWiFi.p010di.interceptors;

import com.accord.common.utils.Logger;
import java.io.IOException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.Connection;
import okhttp3.Handshake;
import okhttp3.Interceptor;
import okhttp3.Response;

/* renamed from: com.jch.racWiFi.di.interceptors.NetworkInterceptor */
public class NetworkInterceptor implements Interceptor {
    private static final String TAG = "NetworkInterceptor";

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Handshake handshake;
        Connection connection = chain.connection();
        if (!(connection == null || (handshake = connection.handshake()) == null)) {
            Logger.m47e(TAG, "TLS version : " + handshake.tlsVersion());
            CipherSuite cipherSuite = handshake.cipherSuite();
            Logger.m47e(TAG, "Cipher Suite : " + cipherSuite.javaName());
            for (Certificate next : handshake.peerCertificates()) {
                PublicKey publicKey = next.getPublicKey();
                Logger.m47e(TAG, "Algorithm : " + publicKey.getAlgorithm());
                Logger.m47e(TAG, "Format : " + publicKey.getFormat());
                Logger.m47e(TAG, "Pin : " + CertificatePinner.pin(next));
            }
            Principal peerPrincipal = handshake.peerPrincipal();
            if (peerPrincipal != null) {
                Logger.m47e(TAG, "Principal Name : " + peerPrincipal.getName());
            }
        }
        return chain.proceed(chain.request());
    }
}
