package com.jch.racWiFi.p010di.util;

import android.util.Base64;
import com.google.gson.Gson;
import com.jch.algo.Security;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.fcm.util.Persistence;
import com.jch.racWiFi.p010di.util.Constants;
import javax.crypto.SecretKey;

/* renamed from: com.jch.racWiFi.di.util.TokenUtil */
public class TokenUtil {
    private static TokenUtil instance;
    private static TokenInfo mTokenInfo;

    private TokenUtil() {
    }

    public static TokenUtil getInstance() {
        if (instance == null) {
            instance = new TokenUtil();
        }
        return instance;
    }

    public void init() {
        try {
            Security instance2 = Security.getInstance();
            SecretKey secretKey = instance2.getSecretKey(Constants.Keys.f429P, Constants.Keys.f430S);
            byte[] bArr = (byte[]) new Persistence().obtain(Constants.Keys.INITIALIZATION_VECTOR, byte[].class);
            String str = (String) new Persistence().obtain(Constants.Keys.TOKEN_INFO, String.class);
            if (str != null) {
                byte[] decode = Base64.decode(str.getBytes(), 2);
                if (bArr != null) {
                    mTokenInfo = (TokenInfo) new Gson().fromJson(instance2.decrypt(decode, secretKey, bArr), TokenInfo.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TokenInfo obtain() {
        System.out.println("TokenUtil  obtain");
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            mTokenInfo = new TokenInfo();
        } else {
            TokenInfo tokenInfo = mTokenInfo;
            if (tokenInfo != null) {
                return tokenInfo;
            }
            init();
        }
        return mTokenInfo;
    }

    public void persist(TokenInfo tokenInfo) {
        try {
            Security instance2 = Security.getInstance();
            SecretKey secretKey = instance2.getSecretKey(Constants.Keys.f429P, Constants.Keys.f430S);
            byte[] initializationVector = instance2.initializationVector();
            new Persistence().persist(Constants.Keys.INITIALIZATION_VECTOR, initializationVector);
            new Persistence().persist(Constants.Keys.TOKEN_INFO, Base64.encodeToString(instance2.encrypt(new Gson().toJson((Object) tokenInfo), secretKey, initializationVector), 2));
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeTokenInfo() {
        new Persistence().persist(Constants.Keys.TOKEN_INFO, null);
    }

    public void clear() {
        TokenInfo obtain = obtain();
        if (obtain != null) {
            obtain.clear();
            persist(obtain);
        }
    }

    public void copy(TokenInfo tokenInfo) {
        TokenInfo obtain = obtain();
        obtain.setId(tokenInfo.getId());
        obtain.setNew(tokenInfo.isNew());
        obtain.setToken(tokenInfo.getToken());
        obtain.setRefreshToken(tokenInfo.getRefreshToken());
        obtain.setType(tokenInfo.getType());
        persist(obtain);
    }

    public boolean isValid() {
        TokenInfo obtain = obtain();
        if (obtain == null) {
            return false;
        }
        return !obtain.getBearerToken().equals(DemoModeModel.DEMO_TOKEN);
    }
}
