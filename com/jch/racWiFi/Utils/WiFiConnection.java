package com.jch.racWiFi.Utils;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import com.accord.common.utils.Logger;
import java.lang.ref.WeakReference;
import java.util.List;

public class WiFiConnection extends AsyncTask<Void, Void, Boolean> {
    private WeakReference<Context> contextWeakReference;
    private IWiFiConnectionCallback iWiFiConnectionCallback;
    private int method = 0;
    private String password;
    private String securityCapabilities;
    private String ssid;

    public interface IWiFiConnectionCallback {
        void isSuccessful(boolean z);
    }

    public void setiWiFiConnectionCallback(IWiFiConnectionCallback iWiFiConnectionCallback2) {
        this.iWiFiConnectionCallback = iWiFiConnectionCallback2;
    }

    public WiFiConnection(Context context, String str, String str2, String str3) {
        this.contextWeakReference = new WeakReference<>(context);
        this.ssid = str;
        this.password = str2;
        this.securityCapabilities = str3;
    }

    private boolean checkIfConfigured() {
        List<WifiConfiguration> configuredNetworks = ((WifiManager) ((Context) this.contextWeakReference.get()).getApplicationContext().getSystemService("wifi")).getConfiguredNetworks();
        String str = this.ssid;
        for (WifiConfiguration next : configuredNetworks) {
            if (next.SSID != null) {
                String str2 = next.SSID;
                if (str2.equals("\"" + str + "\"")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean connectToConfiguredWifi() {
        WifiManager wifiManager = (WifiManager) ((Context) this.contextWeakReference.get()).getApplicationContext().getSystemService("wifi");
        String str = this.ssid;
        for (WifiConfiguration next : wifiManager.getConfiguredNetworks()) {
            if (next.SSID != null) {
                String str2 = next.SSID;
                if (str2.equals("\"" + str + "\"")) {
                    Logger.m50v("rht", "WifiConfiguration SSID " + next.SSID);
                    boolean disconnect = wifiManager.disconnect();
                    Logger.m50v("rht", "isDisconnected : " + disconnect);
                    boolean enableNetwork = wifiManager.enableNetwork(next.networkId, true);
                    Logger.m50v("rht", "isEnabled : " + enableNetwork);
                    boolean reconnect = wifiManager.reconnect();
                    Logger.m50v("rht", "isReconnected : " + reconnect);
                    return reconnect;
                }
            }
        }
        return false;
    }

    private boolean connectWiFi() {
        try {
            Logger.m50v("rht", "Item clicked, SSID " + this.ssid + " Security : " + this.securityCapabilities);
            String str = this.ssid;
            String str2 = this.password;
            WifiConfiguration wifiConfiguration = new WifiConfiguration();
            wifiConfiguration.SSID = "\"" + str + "\"";
            wifiConfiguration.status = 2;
            wifiConfiguration.priority = 40;
            if (this.securityCapabilities.toUpperCase().contains("WEP")) {
                Logger.m50v("rht", "Configuring WEP");
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.allowedProtocols.set(1);
                wifiConfiguration.allowedProtocols.set(0);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedAuthAlgorithms.set(1);
                wifiConfiguration.allowedPairwiseCiphers.set(2);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(0);
                wifiConfiguration.allowedGroupCiphers.set(1);
                if (str2.matches("^[0-9a-fA-F]+$")) {
                    wifiConfiguration.wepKeys[0] = str2;
                } else {
                    wifiConfiguration.wepKeys[0] = "\"".concat(str2).concat("\"");
                }
                wifiConfiguration.wepTxKeyIndex = 0;
            } else if (this.securityCapabilities.toUpperCase().contains("WPA")) {
                Logger.m50v("rht", "Configuring WPA");
                wifiConfiguration.allowedProtocols.set(1);
                wifiConfiguration.allowedProtocols.set(0);
                wifiConfiguration.allowedKeyManagement.set(1);
                wifiConfiguration.allowedPairwiseCiphers.set(2);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(0);
                wifiConfiguration.allowedGroupCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            } else {
                Logger.m50v("rht", "Configuring OPEN network");
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.allowedProtocols.set(1);
                wifiConfiguration.allowedProtocols.set(0);
                wifiConfiguration.allowedAuthAlgorithms.clear();
                wifiConfiguration.allowedPairwiseCiphers.set(2);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(0);
                wifiConfiguration.allowedGroupCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedGroupCiphers.set(2);
            }
            WifiManager wifiManager = (WifiManager) ((Context) this.contextWeakReference.get()).getApplicationContext().getSystemService("wifi");
            int addNetwork = wifiManager.addNetwork(wifiConfiguration);
            Logger.m50v("rht", "Add result " + addNetwork);
            for (WifiConfiguration next : wifiManager.getConfiguredNetworks()) {
                if (next.SSID != null) {
                    String str3 = next.SSID;
                    if (str3.equals("\"" + str + "\"")) {
                        Logger.m50v("rht", "WifiConfiguration SSID " + next.SSID);
                        boolean disconnect = wifiManager.disconnect();
                        Logger.m50v("rht", "isDisconnected : " + disconnect);
                        boolean enableNetwork = wifiManager.enableNetwork(next.networkId, true);
                        Logger.m50v("rht", "isEnabled : " + enableNetwork);
                        boolean reconnect = wifiManager.reconnect();
                        Logger.m50v("rht", "isReconnected : " + reconnect);
                        return reconnect;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground(Void... voidArr) {
        boolean z;
        if (checkIfConfigured()) {
            z = connectToConfiguredWifi();
        } else {
            z = connectWiFi();
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        IWiFiConnectionCallback iWiFiConnectionCallback2 = this.iWiFiConnectionCallback;
        if (iWiFiConnectionCallback2 != null) {
            iWiFiConnectionCallback2.isSuccessful(bool.booleanValue());
        }
    }
}
