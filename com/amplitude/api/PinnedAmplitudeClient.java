package com.amplitude.api;

import android.content.Context;
import com.amplitude.util.DoubleCheck;
import com.amplitude.util.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okio.Buffer;
import okio.ByteString;

public class PinnedAmplitudeClient extends AmplitudeClient {
    private static final String CERTIFICATE_1 = "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";
    protected static final SSLContextBuilder SSL_CONTEXT_API_AMPLITUDE_COM = new SSLContextBuilder().addCertificate(CERTIFICATE_1);
    /* access modifiers changed from: private */
    public static final String TAG = "com.amplitude.api.PinnedAmplitudeClient";
    static Map<String, PinnedAmplitudeClient> instances = new HashMap();
    /* access modifiers changed from: private */
    public static final AmplitudeLog logger = AmplitudeLog.getLogger();
    protected boolean initializedSSLSocketFactory = false;
    protected SSLSocketFactory sslSocketFactory;

    protected static class SSLContextBuilder {
        private final List<String> certificateBase64s = new ArrayList();

        protected SSLContextBuilder() {
        }

        public SSLContextBuilder addCertificate(String str) {
            this.certificateBase64s.add(str);
            return this;
        }

        public SSLContext build() {
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                KeyStore instance3 = KeyStore.getInstance(KeyStore.getDefaultType());
                instance3.load((InputStream) null, (char[]) null);
                int i = 1;
                for (String decodeBase64 : this.certificateBase64s) {
                    int i2 = i + 1;
                    instance3.setCertificateEntry(Integer.toString(i), (X509Certificate) instance.generateCertificate(new Buffer().write(ByteString.decodeBase64(decodeBase64)).inputStream()));
                    i = i2;
                }
                instance2.init(instance3);
                SSLContext instance4 = SSLContext.getInstance("TLS");
                instance4.init((KeyManager[]) null, instance2.getTrustManagers(), (SecureRandom) null);
                return instance4;
            } catch (GeneralSecurityException e) {
                PinnedAmplitudeClient.logger.mo13083e(PinnedAmplitudeClient.TAG, e.getMessage(), e);
                return null;
            } catch (IOException e2) {
                PinnedAmplitudeClient.logger.mo13083e(PinnedAmplitudeClient.TAG, e2.getMessage(), e2);
                return null;
            }
        }
    }

    public static PinnedAmplitudeClient getInstance() {
        return getInstance((String) null);
    }

    public static synchronized PinnedAmplitudeClient getInstance(String str) {
        PinnedAmplitudeClient pinnedAmplitudeClient;
        synchronized (PinnedAmplitudeClient.class) {
            String normalizeInstanceName = C0895Utils.normalizeInstanceName(str);
            pinnedAmplitudeClient = instances.get(normalizeInstanceName);
            if (pinnedAmplitudeClient == null) {
                pinnedAmplitudeClient = new PinnedAmplitudeClient(normalizeInstanceName);
                instances.put(normalizeInstanceName, pinnedAmplitudeClient);
            }
        }
        return pinnedAmplitudeClient;
    }

    public PinnedAmplitudeClient(String str) {
        super(str);
    }

    public synchronized AmplitudeClient initializeInternal(Context context, String str, String str2, final Provider<OkHttpClient> provider) {
        super.initialize(context, str, str2);
        runOnLogThread(new Runnable() {
            public void run() {
                if (!this.initializedSSLSocketFactory) {
                    SSLSocketFactory pinnedCertSslSocketFactory = PinnedAmplitudeClient.this.getPinnedCertSslSocketFactory();
                    if (pinnedCertSslSocketFactory != null) {
                        try {
                            CertificateFactory instance = CertificateFactory.getInstance("X.509");
                            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                            KeyStore instance3 = KeyStore.getInstance(KeyStore.getDefaultType());
                            instance3.load((InputStream) null, (char[]) null);
                            ArrayList<String> arrayList = new ArrayList<>();
                            arrayList.add(PinnedAmplitudeClient.CERTIFICATE_1);
                            int i = 1;
                            for (String decodeBase64 : arrayList) {
                                int i2 = i + 1;
                                instance3.setCertificateEntry(Integer.toString(i), (X509Certificate) instance.generateCertificate(new Buffer().write(ByteString.decodeBase64(decodeBase64)).inputStream()));
                                i = i2;
                            }
                            instance2.init(instance3);
                            TrustManager[] trustManagers = instance2.getTrustManagers();
                            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
                            }
                            Provider provider = DoubleCheck.provider(new PinnedAmplitudeClient$1$$ExternalSyntheticLambda0(provider, pinnedCertSslSocketFactory, (X509TrustManager) trustManagers[0]));
                            this.callFactory = new PinnedAmplitudeClient$1$$ExternalSyntheticLambda1(provider);
                        } catch (GeneralSecurityException e) {
                            PinnedAmplitudeClient.logger.mo13083e(PinnedAmplitudeClient.TAG, e.getMessage(), e);
                        } catch (IOException e2) {
                            PinnedAmplitudeClient.logger.mo13083e(PinnedAmplitudeClient.TAG, e2.getMessage(), e2);
                        }
                    } else {
                        PinnedAmplitudeClient.logger.mo13082e(PinnedAmplitudeClient.TAG, "Unable to pin SSL as requested. Will send data without SSL pinning.");
                    }
                    this.initializedSSLSocketFactory = true;
                }
            }

            static /* synthetic */ OkHttpClient lambda$run$0(Provider provider, SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
                OkHttpClient.Builder builder;
                if (provider != null) {
                    builder = ((OkHttpClient) provider.get()).newBuilder();
                } else {
                    builder = new OkHttpClient.Builder();
                }
                return builder.sslSocketFactory(sSLSocketFactory, x509TrustManager).build();
            }
        });
        return this;
    }

    public synchronized AmplitudeClient initialize(Context context, String str, String str2) {
        return initializeInternal(context, str, str2, (Provider<OkHttpClient>) null);
    }

    public synchronized AmplitudeClient initialize(Context context, String str, String str2, Provider<OkHttpClient> provider) {
        return initializeInternal(context, str, str2, provider);
    }

    /* access modifiers changed from: protected */
    public SSLSocketFactory getPinnedCertSslSocketFactory() {
        return getPinnedCertSslSocketFactory(SSL_CONTEXT_API_AMPLITUDE_COM);
    }

    /* access modifiers changed from: protected */
    public SSLSocketFactory getPinnedCertSslSocketFactory(SSLContextBuilder sSLContextBuilder) {
        if (sSLContextBuilder == null) {
            return null;
        }
        if (this.sslSocketFactory == null) {
            try {
                this.sslSocketFactory = sSLContextBuilder.build().getSocketFactory();
                logger.mo13085i(TAG, "Pinning SSL session using Comodo CA Cert");
            } catch (Exception e) {
                logger.mo13083e(TAG, e.getMessage(), e);
            }
        }
        return this.sslSocketFactory;
    }
}
