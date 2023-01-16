package com.jch.algo;

import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Security {
    private static Security instance;

    public interface Constants {
        public static final String AES = "AES";
        public static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";
        public static final String ALGORITHM = "RSA";
        public static final String DECRYPT = "keystore.key";
        public static final String ENCRYPT = "keystore.pub";
        public static final String PADDING = "RSA/ECB/PKCS1Padding";
    }

    private Security() {
    }

    public static Security getInstance() {
        if (instance == null) {
            instance = new Security();
        }
        return instance;
    }

    public String encryptJ(String str, InputStream inputStream) throws Exception {
        return Base64.getEncoder().encodeToString(encryptA(str, inputStream));
    }

    public byte[] encryptA(String str, InputStream inputStream) throws Exception {
        Cipher instance2 = Cipher.getInstance(Constants.PADDING);
        instance2.init(1, readPublicKey(inputStream));
        return instance2.doFinal(str.getBytes());
    }

    public String decryptJ(String str, InputStream inputStream) throws Exception {
        return decryptA(Base64.getDecoder().decode(str.getBytes()), inputStream);
    }

    public String decryptA(byte[] bArr, InputStream inputStream) throws Exception {
        Cipher instance2 = Cipher.getInstance(Constants.PADDING);
        instance2.init(2, readPrivateKey(inputStream));
        return new String(instance2.doFinal(bArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.security.PublicKey readPublicKey(java.io.InputStream r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0031, all -> 0x002c }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0031, all -> 0x002c }
            java.lang.Object r2 = r1.readObject()     // Catch:{ Exception -> 0x002a }
            java.math.BigInteger r2 = (java.math.BigInteger) r2     // Catch:{ Exception -> 0x002a }
            java.lang.Object r3 = r1.readObject()     // Catch:{ Exception -> 0x002a }
            java.math.BigInteger r3 = (java.math.BigInteger) r3     // Catch:{ Exception -> 0x002a }
            java.security.spec.RSAPublicKeySpec r4 = new java.security.spec.RSAPublicKeySpec     // Catch:{ Exception -> 0x002a }
            r4.<init>(r2, r3)     // Catch:{ Exception -> 0x002a }
            java.lang.String r2 = "RSA"
            java.security.KeyFactory r2 = java.security.KeyFactory.getInstance(r2)     // Catch:{ Exception -> 0x002a }
            java.security.PublicKey r0 = r2.generatePublic(r4)     // Catch:{ Exception -> 0x002a }
            r1.close()
            if (r7 == 0) goto L_0x0029
            r7.close()
        L_0x0029:
            return r0
        L_0x002a:
            r2 = move-exception
            goto L_0x0033
        L_0x002c:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0042
        L_0x0031:
            r2 = move-exception
            r1 = r0
        L_0x0033:
            r2.printStackTrace()     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0040
            r1.close()
            if (r7 == 0) goto L_0x0040
            r7.close()
        L_0x0040:
            return r0
        L_0x0041:
            r0 = move-exception
        L_0x0042:
            if (r1 == 0) goto L_0x004c
            r1.close()
            if (r7 == 0) goto L_0x004c
            r7.close()
        L_0x004c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.algo.Security.readPublicKey(java.io.InputStream):java.security.PublicKey");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.security.PrivateKey readPrivateKey(java.io.InputStream r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0031, all -> 0x002c }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0031, all -> 0x002c }
            java.lang.Object r2 = r1.readObject()     // Catch:{ Exception -> 0x002a }
            java.math.BigInteger r2 = (java.math.BigInteger) r2     // Catch:{ Exception -> 0x002a }
            java.lang.Object r3 = r1.readObject()     // Catch:{ Exception -> 0x002a }
            java.math.BigInteger r3 = (java.math.BigInteger) r3     // Catch:{ Exception -> 0x002a }
            java.security.spec.RSAPrivateKeySpec r4 = new java.security.spec.RSAPrivateKeySpec     // Catch:{ Exception -> 0x002a }
            r4.<init>(r2, r3)     // Catch:{ Exception -> 0x002a }
            java.lang.String r2 = "RSA"
            java.security.KeyFactory r2 = java.security.KeyFactory.getInstance(r2)     // Catch:{ Exception -> 0x002a }
            java.security.PrivateKey r0 = r2.generatePrivate(r4)     // Catch:{ Exception -> 0x002a }
            r1.close()
            if (r7 == 0) goto L_0x0029
            r7.close()
        L_0x0029:
            return r0
        L_0x002a:
            r2 = move-exception
            goto L_0x0033
        L_0x002c:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0042
        L_0x0031:
            r2 = move-exception
            r1 = r0
        L_0x0033:
            r2.printStackTrace()     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0040
            r1.close()
            if (r7 == 0) goto L_0x0040
            r7.close()
        L_0x0040:
            return r0
        L_0x0041:
            r0 = move-exception
        L_0x0042:
            if (r1 == 0) goto L_0x004c
            r1.close()
            if (r7 == 0) goto L_0x004c
            r7.close()
        L_0x004c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.algo.Security.readPrivateKey(java.io.InputStream):java.security.PrivateKey");
    }

    public SecretKey createAESKey() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator instance2 = KeyGenerator.getInstance(Constants.AES);
        instance2.init(256, secureRandom);
        return instance2.generateKey();
    }

    public SecretKey getSecretKey(String str, String str2) throws Exception {
        return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(new PBEKeySpec(str.toCharArray(), str2.getBytes(), 65536, 256)).getEncoded(), Constants.AES);
    }

    public byte[] initializationVector() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    public byte[] encrypt(String str, SecretKey secretKey, byte[] bArr) throws Exception {
        Cipher instance2 = Cipher.getInstance(Constants.AES_CIPHER_ALGORITHM);
        instance2.init(1, secretKey, new IvParameterSpec(bArr));
        return instance2.doFinal(str.getBytes());
    }

    public String decrypt(byte[] bArr, SecretKey secretKey, byte[] bArr2) throws Exception {
        Cipher instance2 = Cipher.getInstance(Constants.AES_CIPHER_ALGORITHM);
        instance2.init(2, secretKey, new IvParameterSpec(bArr2));
        return new String(instance2.doFinal(bArr));
    }
}
