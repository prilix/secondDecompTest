package com.thanosfisherman.wifiutils;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WpsInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.jch.racWiFi.C1662R2;
import com.thanosfisherman.elvis.Objects;
import com.thanosfisherman.wifiutils.wifiWps.ConnectionWpsListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ConnectorUtils {
    private static final int MAX_PRIORITY = 99999;

    public static int getPowerPercentage(int i) {
        if (i <= -93) {
            return 0;
        }
        if (-25 > i || i > 0) {
            return i + 125;
        }
        return 100;
    }

    public static boolean isAlreadyConnected(WifiManager wifiManager, String str) {
        if (str == null || wifiManager == null || wifiManager.getConnectionInfo() == null || wifiManager.getConnectionInfo().getBSSID() == null || wifiManager.getConnectionInfo().getIpAddress() == 0 || !Objects.equals(str, wifiManager.getConnectionInfo().getBSSID())) {
            return false;
        }
        WifiUtils.wifiLog("Already connected to: " + wifiManager.getConnectionInfo().getSSID() + "  BSSID: " + wifiManager.getConnectionInfo().getBSSID());
        return true;
    }

    private static boolean checkForExcessOpenNetworkAndSave(ContentResolver contentResolver, WifiManager wifiManager) {
        int i;
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        sortByPriority(configuredNetworks);
        if (Build.VERSION.SDK_INT >= 17) {
            i = Settings.Secure.getInt(contentResolver, "wifi_num_open_networks_kept", 10);
        } else {
            i = Settings.Secure.getInt(contentResolver, "wifi_num_open_networks_kept", 10);
        }
        boolean z = false;
        int i2 = 0;
        for (int size = configuredNetworks.size() - 1; size >= 0; size--) {
            WifiConfiguration wifiConfiguration = configuredNetworks.get(size);
            if (Objects.equals("OPEN", ConfigSecurities.getSecurity(wifiConfiguration)) && (i2 = i2 + 1) >= i) {
                wifiManager.removeNetwork(wifiConfiguration.networkId);
                z = true;
            }
        }
        if (!z || wifiManager.saveConfiguration()) {
            return true;
        }
        return false;
    }

    private static int getMaxPriority(WifiManager wifiManager) {
        int i = 0;
        for (WifiConfiguration next : wifiManager.getConfiguredNetworks()) {
            if (next.priority > i) {
                i = next.priority;
            }
        }
        return i;
    }

    private static int shiftPriorityAndSave(WifiManager wifiManager) {
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        sortByPriority(configuredNetworks);
        int size = configuredNetworks.size();
        for (int i = 0; i < size; i++) {
            WifiConfiguration wifiConfiguration = configuredNetworks.get(i);
            wifiConfiguration.priority = i;
            wifiManager.updateNetwork(wifiConfiguration);
        }
        wifiManager.saveConfiguration();
        return size;
    }

    private static String trimQuotes(String str) {
        return (str == null || str.isEmpty()) ? str : str.replaceAll("^\"*", "").replaceAll("\"*$", "");
    }

    static String convertToQuotedString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length() - 1;
        if (length < 0) {
            return str;
        }
        if (str.charAt(0) == '\"' && str.charAt(length) == '\"') {
            return str;
        }
        return "\"" + str + "\"";
    }

    static boolean isHexWepKey(String str) {
        int length = str == null ? 0 : str.length();
        if (length == 0) {
            return false;
        }
        if ((length == 10 || length == 26 || length == 58) && str.matches("[0-9A-Fa-f]*")) {
            return true;
        }
        return false;
    }

    static /* synthetic */ int lambda$sortByPriority$0(WifiConfiguration wifiConfiguration, WifiConfiguration wifiConfiguration2) {
        return wifiConfiguration.priority - wifiConfiguration2.priority;
    }

    private static void sortByPriority(List<WifiConfiguration> list) {
        Collections.sort(list, ConnectorUtils$$ExternalSyntheticLambda0.INSTANCE);
    }

    public static int frequencyToChannel(int i) {
        if (2412 <= i && i <= 2484) {
            return ((i - C1662R2.dimen.m3_sys_shape_large_corner_size) / 5) + 1;
        }
        if (5170 > i || i > 5825) {
            return -1;
        }
        return ((i - C1662R2.C1665id.manageUsersFragment) / 5) + 34;
    }

    static void registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (broadcastReceiver != null) {
            try {
                context.registerReceiver(broadcastReceiver, intentFilter);
            } catch (Exception unused) {
            }
        }
    }

    static void unregisterReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            try {
                context.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    static boolean connectToWifi(Context context, WifiManager wifiManager, ScanResult scanResult, String str) {
        WifiConfiguration wifiConfiguration = ConfigSecurities.getWifiConfiguration(wifiManager, scanResult);
        if (wifiConfiguration != null && str.isEmpty()) {
            WifiUtils.wifiLog("PASSWORD WAS EMPTY. TRYING TO CONNECT TO EXISTING NETWORK CONFIGURATION");
            return connectToConfiguredNetwork(wifiManager, wifiConfiguration, true);
        } else if (!cleanPreviousConfiguration(wifiManager, wifiConfiguration)) {
            WifiUtils.wifiLog("COULDN'T REMOVE PREVIOUS CONFIG, CONNECTING TO EXISTING ONE");
            return connectToConfiguredNetwork(wifiManager, wifiConfiguration, true);
        } else {
            String security = ConfigSecurities.getSecurity(scanResult);
            if (Objects.equals("OPEN", security)) {
                checkForExcessOpenNetworkAndSave(context.getContentResolver(), wifiManager);
            }
            WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
            wifiConfiguration2.SSID = convertToQuotedString(scanResult.SSID);
            wifiConfiguration2.BSSID = scanResult.BSSID;
            ConfigSecurities.setupSecurity(wifiConfiguration2, security, str);
            int addNetwork = wifiManager.addNetwork(wifiConfiguration2);
            WifiUtils.wifiLog("Network ID: " + addNetwork);
            if (addNetwork == -1) {
                return false;
            }
            if (!wifiManager.saveConfiguration()) {
                WifiUtils.wifiLog("Couldn't save wifi config");
                return false;
            }
            WifiConfiguration wifiConfiguration3 = ConfigSecurities.getWifiConfiguration(wifiManager, wifiConfiguration2);
            if (wifiConfiguration3 != null) {
                return connectToConfiguredNetwork(wifiManager, wifiConfiguration3, true);
            }
            WifiUtils.wifiLog("Error getting wifi config after save. (config == null)");
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0020 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0070 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean connectToConfiguredNetwork(android.net.wifi.WifiManager r5, android.net.wifi.WifiConfiguration r6, boolean r7) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            r3 = 1
            if (r1 < r2) goto L_0x0022
            boolean r6 = disableAllButOne((android.net.wifi.WifiManager) r5, (android.net.wifi.WifiConfiguration) r6)
            if (r6 == 0) goto L_0x0021
            if (r7 == 0) goto L_0x001a
            boolean r5 = r5.reassociate()
            if (r5 == 0) goto L_0x0021
            goto L_0x0020
        L_0x001a:
            boolean r5 = r5.reconnect()
            if (r5 == 0) goto L_0x0021
        L_0x0020:
            r0 = 1
        L_0x0021:
            return r0
        L_0x0022:
            int r1 = r6.priority
            int r2 = getMaxPriority(r5)
            int r2 = r2 + r3
            r4 = 99999(0x1869f, float:1.40128E-40)
            if (r2 <= r4) goto L_0x0039
            int r2 = shiftPriorityAndSave(r5)
            android.net.wifi.WifiConfiguration r6 = com.thanosfisherman.wifiutils.ConfigSecurities.getWifiConfiguration((android.net.wifi.WifiManager) r5, (android.net.wifi.WifiConfiguration) r6)
            if (r6 != 0) goto L_0x0039
            return r0
        L_0x0039:
            r6.priority = r2
            int r2 = r5.updateNetwork(r6)
            r4 = -1
            if (r2 != r4) goto L_0x0043
            return r0
        L_0x0043:
            boolean r2 = r5.enableNetwork(r2, r0)
            if (r2 != 0) goto L_0x004c
            r6.priority = r1
            return r0
        L_0x004c:
            boolean r2 = r5.saveConfiguration()
            if (r2 != 0) goto L_0x0055
            r6.priority = r1
            return r0
        L_0x0055:
            android.net.wifi.WifiConfiguration r6 = com.thanosfisherman.wifiutils.ConfigSecurities.getWifiConfiguration((android.net.wifi.WifiManager) r5, (android.net.wifi.WifiConfiguration) r6)
            if (r6 == 0) goto L_0x0071
            boolean r6 = disableAllButOne((android.net.wifi.WifiManager) r5, (android.net.wifi.WifiConfiguration) r6)
            if (r6 == 0) goto L_0x0071
            if (r7 == 0) goto L_0x006a
            boolean r5 = r5.reassociate()
            if (r5 == 0) goto L_0x0071
            goto L_0x0070
        L_0x006a:
            boolean r5 = r5.reconnect()
            if (r5 == 0) goto L_0x0071
        L_0x0070:
            r0 = 1
        L_0x0071:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thanosfisherman.wifiutils.ConnectorUtils.connectToConfiguredNetwork(android.net.wifi.WifiManager, android.net.wifi.WifiConfiguration, boolean):boolean");
    }

    private static boolean disableAllButOne(WifiManager wifiManager, WifiConfiguration wifiConfiguration) {
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        boolean z = false;
        if (!(configuredNetworks == null || wifiConfiguration == null || configuredNetworks.isEmpty())) {
            for (WifiConfiguration next : configuredNetworks) {
                if (next.networkId == wifiConfiguration.networkId) {
                    z = wifiManager.enableNetwork(next.networkId, true);
                } else {
                    wifiManager.disableNetwork(next.networkId);
                }
            }
            WifiUtils.wifiLog("disableAllButOne " + z);
        }
        return z;
    }

    private static boolean disableAllButOne(WifiManager wifiManager, ScanResult scanResult) {
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        boolean z = false;
        if (!(configuredNetworks == null || scanResult == null || configuredNetworks.isEmpty())) {
            for (WifiConfiguration next : configuredNetworks) {
                if (!Objects.equals(scanResult.BSSID, next.BSSID) || !Objects.equals(scanResult.SSID, trimQuotes(next.SSID))) {
                    wifiManager.disableNetwork(next.networkId);
                } else {
                    z = wifiManager.enableNetwork(next.networkId, true);
                }
            }
        }
        return z;
    }

    public static boolean reEnableNetworkIfPossible(WifiManager wifiManager, ScanResult scanResult) {
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        boolean z = false;
        if (!(configuredNetworks == null || scanResult == null || configuredNetworks.isEmpty())) {
            Iterator<WifiConfiguration> it = configuredNetworks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WifiConfiguration next = it.next();
                if (Objects.equals(scanResult.BSSID, next.BSSID) && Objects.equals(scanResult.SSID, trimQuotes(next.SSID))) {
                    z = wifiManager.enableNetwork(next.networkId, true);
                    break;
                }
            }
            WifiUtils.wifiLog("reEnableNetworkIfPossible " + z);
        }
        return z;
    }

    static void connectWps(final WifiManager wifiManager, final ScanResult scanResult, String str, long j, final ConnectionWpsListener connectionWpsListener) {
        final WeakHandler weakHandler = new WeakHandler();
        WpsInfo wpsInfo = new WpsInfo();
        C27051 r8 = new Runnable() {
            public void run() {
                wifiManager.cancelWps((WifiManager.WpsCallback) null);
                WifiUtils.wifiLog("Connection with WPS has timed out");
                ConnectorUtils.cleanPreviousConfiguration(wifiManager, scanResult);
                connectionWpsListener.isSuccessful(false);
                weakHandler.removeCallbacks(this);
            }
        };
        final WeakHandler weakHandler2 = weakHandler;
        final C27051 r2 = r8;
        final ConnectionWpsListener connectionWpsListener2 = connectionWpsListener;
        final WifiManager wifiManager2 = wifiManager;
        final ScanResult scanResult2 = scanResult;
        C27062 r0 = new WifiManager.WpsCallback() {
            public void onStarted(String str) {
            }

            public void onSucceeded() {
                WeakHandler.this.removeCallbacks(r2);
                WifiUtils.wifiLog("CONNECTED With WPS successfully");
                connectionWpsListener2.isSuccessful(true);
            }

            public void onFailed(int i) {
                WeakHandler.this.removeCallbacks(r2);
                String valueOf = i != 3 ? i != 4 ? i != 5 ? i != 6 ? i != 7 ? String.valueOf(i) : "WPS_TIMED_OUT" : "WPS_AUTH_FAILURE" : "WPS_TKIP_ONLY_PROHIBITED" : "WPS_WEP_PROHIBITED" : "WPS_OVERLAP_ERROR";
                WifiUtils.wifiLog("FAILED to connect with WPS. Reason: " + valueOf);
                ConnectorUtils.cleanPreviousConfiguration(wifiManager2, scanResult2);
                ConnectorUtils.reenableAllHotspots(wifiManager2);
                connectionWpsListener2.isSuccessful(false);
            }
        };
        WifiUtils.wifiLog("Connecting with WPS...");
        wpsInfo.setup = 2;
        wpsInfo.BSSID = scanResult.BSSID;
        wpsInfo.pin = str;
        wifiManager.cancelWps((WifiManager.WpsCallback) null);
        if (!cleanPreviousConfiguration(wifiManager, scanResult)) {
            disableAllButOne(wifiManager, scanResult);
        }
        weakHandler.postDelayed(r8, j);
        wifiManager.startWps(wpsInfo, r0);
    }

    static boolean cleanPreviousConfiguration(WifiManager wifiManager, ScanResult scanResult) {
        WifiConfiguration wifiConfiguration = ConfigSecurities.getWifiConfiguration(wifiManager, scanResult);
        WifiUtils.wifiLog("Attempting to remove previous network config...");
        if (wifiConfiguration == null) {
            return true;
        }
        if (!wifiManager.removeNetwork(wifiConfiguration.networkId)) {
            return false;
        }
        wifiManager.saveConfiguration();
        return true;
    }

    static boolean cleanPreviousConfiguration(WifiManager wifiManager, WifiConfiguration wifiConfiguration) {
        WifiUtils.wifiLog("Attempting to remove previous network config...");
        if (wifiConfiguration == null) {
            return true;
        }
        if (!wifiManager.removeNetwork(wifiConfiguration.networkId)) {
            return false;
        }
        wifiManager.saveConfiguration();
        return true;
    }

    static void reenableAllHotspots(WifiManager wifiManager) {
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        if (configuredNetworks != null && !configuredNetworks.isEmpty()) {
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                wifiManager.enableNetwork(wifiConfiguration.networkId, false);
            }
        }
    }

    static ScanResult matchScanResultSsid(String str, Iterable<ScanResult> iterable) {
        for (ScanResult next : iterable) {
            if (Objects.equals(next.SSID, str)) {
                return next;
            }
        }
        return null;
    }

    static ScanResult matchScanResult(String str, String str2, Iterable<ScanResult> iterable) {
        for (ScanResult next : iterable) {
            if (Objects.equals(next.SSID, str) && Objects.equals(next.BSSID, str2)) {
                return next;
            }
        }
        return null;
    }

    static ScanResult matchScanResultBssid(String str, Iterable<ScanResult> iterable) {
        for (ScanResult next : iterable) {
            if (Objects.equals(next.BSSID, str)) {
                return next;
            }
        }
        return null;
    }
}
