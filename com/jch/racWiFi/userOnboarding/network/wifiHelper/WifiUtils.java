package com.jch.racWiFi.userOnboarding.network.wifiHelper;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.p010di.util.Constants;
import com.thanosfisherman.wifiutils.WifiConnectorBuilder;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;

public class WifiUtils {
    public static final int WIFI_PERMISSION_REQUEST_CODE = 1101;
    private static final WifiUtils WIFI_UTILS = new WifiUtils();
    final int[] failedCount = {0};
    private SingleChoiceDialog notSupported5GhzDialog;

    public static WifiUtils getInstance() {
        return WIFI_UTILS;
    }

    private WifiUtils() {
    }

    public void requestWifiAndCameraPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION, "android.permission.CAMERA"}, 1101);
    }

    public String getCurrentSsid(Context context) {
        ConnectivityManager connectivityManager;
        NetworkCapabilities networkCapabilities;
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        if (ActivityCompat.checkSelfPermission(context, Constants.GrantedPermissions.ACCESS_FINE_LOCATION) != 0 || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork())) == null || !networkCapabilities.hasTransport(1) || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null || (connectionInfo = wifiManager.getConnectionInfo()) == null || connectionInfo.getSSID() == null) {
            return null;
        }
        return connectionInfo.getSSID();
    }

    @Deprecated
    public String getCurrentSsid1(Context context) {
        WifiInfo connectionInfo;
        if (ActivityCompat.checkSelfPermission(context, Constants.GrantedPermissions.ACCESS_FINE_LOCATION) == 0 && ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected() && (connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo()) != null && connectionInfo.getSSID() != null) {
            return connectionInfo.getSSID().replaceAll("^\"|\"$", "");
        }
        return null;
    }

    public WifiInfo getCurrentWiFiInfo(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Constants.GrantedPermissions.ACCESS_FINE_LOCATION) == 0 && ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected()) {
            return ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        }
        return null;
    }

    public static boolean is5GHzBand(WifiInfo wifiInfo) {
        return wifiInfo.getFrequency() > 2500;
    }

    public boolean show5GHzNotSupportedDialog(Context context, SingleChoiceDialog.CustomOnClickListener customOnClickListener) {
        WifiInfo currentWiFiInfo = getInstance().getCurrentWiFiInfo(context);
        if (currentWiFiInfo == null) {
            return false;
        }
        return is5GHzBand(currentWiFiInfo);
    }

    public void connectWithWpa(Context context, String str, String str2, ConnectionSuccessListener connectionSuccessListener) {
        this.failedCount[0] = 0;
        ((WifiManager) context.getApplicationContext().getSystemService("wifi")).disconnect();
        this.failedCount[0] = 0;
        Logger.m45d("Configure", str + " " + str2);
        WifiConnectorBuilder.WifiUtilsBuilder withContext = com.thanosfisherman.wifiutils.WifiUtils.withContext(context.getApplicationContext());
        withContext.connectWith(str, str2).setTimeout(30000).onConnectionResult(new WifiUtils$$ExternalSyntheticLambda0(this, withContext, str, str2, connectionSuccessListener)).start();
    }

    /* renamed from: lambda$connectWithWpa$2$com-jch-racWiFi-userOnboarding-network-wifiHelper-WifiUtils */
    public /* synthetic */ void mo33429x4be0e1e9(WifiConnectorBuilder.WifiUtilsBuilder wifiUtilsBuilder, String str, String str2, ConnectionSuccessListener connectionSuccessListener, boolean z) {
        Logger.m45d("WifiConnection", z + " " + this.failedCount[0]);
        if (!z) {
            int[] iArr = this.failedCount;
            if (iArr[0] == 0) {
                iArr[0] = iArr[0] + 1;
                wifiUtilsBuilder.disableWifi();
                new Handler().postDelayed(new WifiUtils$$ExternalSyntheticLambda3(this, wifiUtilsBuilder, str, str2, connectionSuccessListener), 5000);
                return;
            }
        }
        connectionSuccessListener.isSuccessful(z);
        Logger.m47e("onboadring", "is Success");
    }

    /* renamed from: lambda$connectWithWpa$1$com-jch-racWiFi-userOnboarding-network-wifiHelper-WifiUtils */
    public /* synthetic */ void mo33428x916b4168(WifiConnectorBuilder.WifiUtilsBuilder wifiUtilsBuilder, String str, String str2, ConnectionSuccessListener connectionSuccessListener) {
        wifiUtilsBuilder.disableWifi();
        new Handler().postDelayed(new WifiUtils$$ExternalSyntheticLambda2(this, wifiUtilsBuilder, str, str2, connectionSuccessListener), 5000);
    }

    /* access modifiers changed from: private */
    /* renamed from: buildSecondaryConnector */
    public void mo33427xd6f5a0e7(WifiConnectorBuilder.WifiUtilsBuilder wifiUtilsBuilder, String str, String str2, ConnectionSuccessListener connectionSuccessListener) {
        wifiUtilsBuilder.connectWith(str, str2).setTimeout(30000).onConnectionResult(new WifiUtils$$ExternalSyntheticLambda1(this, connectionSuccessListener)).start();
    }

    /* renamed from: lambda$buildSecondaryConnector$3$com-jch-racWiFi-userOnboarding-network-wifiHelper-WifiUtils */
    public /* synthetic */ void mo33426x1f3831df(ConnectionSuccessListener connectionSuccessListener, boolean z) {
        Logger.m45d("WifiConnection", z + " " + this.failedCount[0]);
        connectionSuccessListener.isSuccessful(z);
    }

    public void connectWithSsid(Context context, String str) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        if (ActivityCompat.checkSelfPermission(context, Constants.GrantedPermissions.ACCESS_FINE_LOCATION) == 0) {
            for (WifiConfiguration next : wifiManager.getConfiguredNetworks()) {
                if (next.SSID.equals(str)) {
                    int i = next.networkId;
                    wifiManager.disconnect();
                    wifiManager.enableNetwork(i, true);
                }
            }
        }
    }

    private boolean fallbackToPreConfigure(Context context, String str) {
        Logger.m47e("Configure", "Called fallback PreConfigured");
        try {
            connectWithSsid(context, str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void fallBackToNativeConnection(Context context, String str, String str2, ConnectionSuccessListener connectionSuccessListener) {
        Logger.m47e("Configure", "Called fallback");
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = "\"" + str + "\"";
        wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
        wifiConfiguration.status = 2;
        wifiConfiguration.allowedGroupCiphers.set(2);
        wifiConfiguration.allowedGroupCiphers.set(3);
        wifiConfiguration.allowedKeyManagement.set(1);
        wifiConfiguration.allowedPairwiseCiphers.set(1);
        wifiConfiguration.allowedPairwiseCiphers.set(2);
        wifiConfiguration.allowedProtocols.set(1);
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        int addNetwork = wifiManager.addNetwork(wifiConfiguration);
        if (addNetwork != -1) {
            wifiManager.disconnect();
            wifiManager.enableNetwork(addNetwork, true);
            wifiManager.reconnect();
            wifiManager.saveConfiguration();
            connectionSuccessListener.isSuccessful(true);
            return;
        }
        connectionSuccessListener.isSuccessful(false);
    }

    public static void connectWiFi(Context context, ScanResult scanResult) {
        try {
            Logger.m50v("rht", "Item clicked, SSID " + scanResult.SSID + " Security : " + scanResult.capabilities);
            String str = scanResult.SSID;
            WifiConfiguration wifiConfiguration = new WifiConfiguration();
            wifiConfiguration.SSID = "\"" + str + "\"";
            wifiConfiguration.status = 2;
            wifiConfiguration.priority = 40;
            if (scanResult.capabilities.toUpperCase().contains("WEP")) {
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
                if ("12345678".matches("^[0-9a-fA-F]+$")) {
                    wifiConfiguration.wepKeys[0] = "12345678";
                } else {
                    wifiConfiguration.wepKeys[0] = "\"".concat("12345678").concat("\"");
                }
                wifiConfiguration.wepTxKeyIndex = 0;
            } else if (scanResult.capabilities.toUpperCase().contains("WPA")) {
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
                wifiConfiguration.preSharedKey = "\"" + "12345678" + "\"";
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
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            int addNetwork = wifiManager.addNetwork(wifiConfiguration);
            Logger.m50v("rht", "Add result " + addNetwork);
            if (ActivityCompat.checkSelfPermission(context, Constants.GrantedPermissions.ACCESS_FINE_LOCATION) == 0) {
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
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
