package com.thanosfisherman.wifiutils;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import com.thanosfisherman.elvis.Elvis;
import com.thanosfisherman.wifiutils.WifiConnectorBuilder;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionScanResultsListener;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;
import com.thanosfisherman.wifiutils.wifiConnect.WifiConnectionCallback;
import com.thanosfisherman.wifiutils.wifiConnect.WifiConnectionReceiver;
import com.thanosfisherman.wifiutils.wifiScan.ScanResultsListener;
import com.thanosfisherman.wifiutils.wifiScan.WifiScanCallback;
import com.thanosfisherman.wifiutils.wifiScan.WifiScanReceiver;
import com.thanosfisherman.wifiutils.wifiState.WifiStateCallback;
import com.thanosfisherman.wifiutils.wifiState.WifiStateListener;
import com.thanosfisherman.wifiutils.wifiState.WifiStateReceiver;
import com.thanosfisherman.wifiutils.wifiWps.ConnectionWpsListener;
import java.util.List;

public final class WifiUtils implements WifiConnectorBuilder, WifiConnectorBuilder.WifiUtilsBuilder, WifiConnectorBuilder.WifiSuccessListener, WifiConnectorBuilder.WifiWpsSuccessListener {
    private static final String TAG = "WifiUtils";
    private static boolean mEnableLog;
    /* access modifiers changed from: private */
    public String mBssid;
    /* access modifiers changed from: private */
    public ConnectionScanResultsListener mConnectionScanResultsListener;
    /* access modifiers changed from: private */
    public ConnectionSuccessListener mConnectionSuccessListener;
    /* access modifiers changed from: private */
    public ConnectionWpsListener mConnectionWpsListener;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public String mPassword;
    /* access modifiers changed from: private */
    public ScanResultsListener mScanResultsListener;
    /* access modifiers changed from: private */
    public ScanResult mSingleScanResult;
    /* access modifiers changed from: private */
    public String mSsid;
    private long mTimeoutMillis = 30000;
    /* access modifiers changed from: private */
    public final WifiConnectionCallback mWifiConnectionCallback;
    /* access modifiers changed from: private */
    public final WifiConnectionReceiver mWifiConnectionReceiver;
    /* access modifiers changed from: private */
    public final WifiManager mWifiManager;
    /* access modifiers changed from: private */
    public final WifiScanReceiver mWifiScanReceiver;
    private final WifiScanCallback mWifiScanResultsCallback;
    private final WifiStateCallback mWifiStateCallback;
    /* access modifiers changed from: private */
    public WifiStateListener mWifiStateListener;
    /* access modifiers changed from: private */
    public final WifiStateReceiver mWifiStateReceiver;
    /* access modifiers changed from: private */
    public long mWpsTimeoutMillis = 30000;

    private WifiUtils(Context context) {
        C27081 r0 = new WifiStateCallback() {
            public void onWifiEnabled() {
                WifiUtils.wifiLog("WIFI ENABLED...");
                ConnectorUtils.unregisterReceiver(WifiUtils.this.mContext, WifiUtils.this.mWifiStateReceiver);
                Elvis.m668of(WifiUtils.this.mWifiStateListener).ifPresent(WifiUtils$1$$ExternalSyntheticLambda1.INSTANCE);
                if (WifiUtils.this.mScanResultsListener != null || WifiUtils.this.mPassword != null) {
                    WifiUtils.wifiLog("START SCANNING....");
                    if (WifiUtils.this.mWifiManager.startScan()) {
                        ConnectorUtils.registerReceiver(WifiUtils.this.mContext, WifiUtils.this.mWifiScanReceiver, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                        return;
                    }
                    Elvis.m668of(WifiUtils.this.mScanResultsListener).ifPresent(WifiUtils$1$$ExternalSyntheticLambda0.INSTANCE);
                    Elvis.m668of(WifiUtils.this.mConnectionWpsListener).ifPresent(WifiUtils$1$$ExternalSyntheticLambda2.INSTANCE);
                    WifiUtils.this.mWifiConnectionCallback.errorConnect();
                    WifiUtils.wifiLog("ERROR COULDN'T SCAN");
                }
            }
        };
        this.mWifiStateCallback = r0;
        C27092 r1 = new WifiScanCallback() {
            public void onScanResultsReady() {
                WifiUtils.wifiLog("GOT SCAN RESULTS");
                ConnectorUtils.unregisterReceiver(WifiUtils.this.mContext, WifiUtils.this.mWifiScanReceiver);
                List<ScanResult> scanResults = WifiUtils.this.mWifiManager.getScanResults();
                Elvis.m668of(WifiUtils.this.mScanResultsListener).ifPresent(new WifiUtils$2$$ExternalSyntheticLambda1(scanResults));
                Elvis.m668of(WifiUtils.this.mConnectionScanResultsListener).ifPresent(new WifiUtils$2$$ExternalSyntheticLambda0(this, scanResults));
                if (WifiUtils.this.mConnectionWpsListener == null || WifiUtils.this.mBssid == null || WifiUtils.this.mPassword == null) {
                    if (WifiUtils.this.mSsid != null) {
                        if (WifiUtils.this.mBssid != null) {
                            WifiUtils wifiUtils = WifiUtils.this;
                            ScanResult unused = wifiUtils.mSingleScanResult = ConnectorUtils.matchScanResult(wifiUtils.mSsid, WifiUtils.this.mBssid, scanResults);
                        } else {
                            WifiUtils wifiUtils2 = WifiUtils.this;
                            ScanResult unused2 = wifiUtils2.mSingleScanResult = ConnectorUtils.matchScanResultSsid(wifiUtils2.mSsid, scanResults);
                        }
                    }
                    if (WifiUtils.this.mSingleScanResult == null || WifiUtils.this.mPassword == null) {
                        WifiUtils.this.mWifiConnectionCallback.errorConnect();
                    } else if (ConnectorUtils.connectToWifi(WifiUtils.this.mContext, WifiUtils.this.mWifiManager, WifiUtils.this.mSingleScanResult, WifiUtils.this.mPassword)) {
                        ConnectorUtils.registerReceiver(WifiUtils.this.mContext, WifiUtils.this.mWifiConnectionReceiver.activateTimeoutHandler(WifiUtils.this.mSingleScanResult), new IntentFilter("android.net.wifi.supplicant.STATE_CHANGE"));
                        ConnectorUtils.registerReceiver(WifiUtils.this.mContext, WifiUtils.this.mWifiConnectionReceiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
                    } else {
                        WifiUtils.this.mWifiConnectionCallback.errorConnect();
                    }
                } else {
                    WifiUtils wifiUtils3 = WifiUtils.this;
                    ScanResult unused3 = wifiUtils3.mSingleScanResult = ConnectorUtils.matchScanResultBssid(wifiUtils3.mBssid, scanResults);
                    if (WifiUtils.this.mSingleScanResult == null || Build.VERSION.SDK_INT < 21) {
                        if (WifiUtils.this.mSingleScanResult == null) {
                            WifiUtils.wifiLog("Couldn't find network. Possibly out of range");
                        }
                        WifiUtils.this.mConnectionWpsListener.isSuccessful(false);
                        return;
                    }
                    ConnectorUtils.connectWps(WifiUtils.this.mWifiManager, WifiUtils.this.mSingleScanResult, WifiUtils.this.mPassword, WifiUtils.this.mWpsTimeoutMillis, WifiUtils.this.mConnectionWpsListener);
                }
            }

            /* renamed from: lambda$onScanResultsReady$1$com-thanosfisherman-wifiutils-WifiUtils$2 */
            public /* synthetic */ void mo33879x558f0fbf(List list, ConnectionScanResultsListener connectionScanResultsListener) {
                ScanResult unused = WifiUtils.this.mSingleScanResult = connectionScanResultsListener.onConnectWithScanResult(list);
            }
        };
        this.mWifiScanResultsCallback = r1;
        C27103 r2 = new WifiConnectionCallback() {
            public void successfulConnect() {
                WifiUtils.wifiLog("CONNECTED SUCCESSFULLY");
                ConnectorUtils.unregisterReceiver(WifiUtils.this.mContext, WifiUtils.this.mWifiConnectionReceiver);
                Elvis.m668of(WifiUtils.this.mConnectionSuccessListener).ifPresent(WifiUtils$3$$ExternalSyntheticLambda1.INSTANCE);
            }

            public void errorConnect() {
                ConnectorUtils.unregisterReceiver(WifiUtils.this.mContext, WifiUtils.this.mWifiConnectionReceiver);
                ConnectorUtils.reenableAllHotspots(WifiUtils.this.mWifiManager);
                Elvis.m668of(WifiUtils.this.mConnectionSuccessListener).ifPresent(WifiUtils$3$$ExternalSyntheticLambda0.INSTANCE);
            }

            static /* synthetic */ void lambda$errorConnect$1(ConnectionSuccessListener connectionSuccessListener) {
                connectionSuccessListener.isSuccessful(false);
                WifiUtils.wifiLog("DIDN'T CONNECT TO WIFI");
            }
        };
        this.mWifiConnectionCallback = r2;
        this.mContext = context;
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        this.mWifiManager = wifiManager;
        if (wifiManager != null) {
            this.mWifiStateReceiver = new WifiStateReceiver(r0);
            this.mWifiScanReceiver = new WifiScanReceiver(r1);
            this.mWifiConnectionReceiver = new WifiConnectionReceiver(r2, wifiManager, this.mTimeoutMillis);
            return;
        }
        throw new RuntimeException("WifiManager is not supposed to be null");
    }

    public static WifiConnectorBuilder.WifiUtilsBuilder withContext(Context context) {
        return new WifiUtils(context);
    }

    public static void wifiLog(String str) {
        if (mEnableLog) {
            String str2 = TAG;
            Log.d(str2, "WifiUtils: " + str);
        }
    }

    public static void enableLog(boolean z) {
        mEnableLog = z;
    }

    public void enableWifi(WifiStateListener wifiStateListener) {
        this.mWifiStateListener = wifiStateListener;
        if (this.mWifiManager.isWifiEnabled()) {
            this.mWifiStateCallback.onWifiEnabled();
        } else if (this.mWifiManager.setWifiEnabled(true)) {
            ConnectorUtils.registerReceiver(this.mContext, this.mWifiStateReceiver, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
        } else {
            Elvis.m668of(wifiStateListener).ifPresent(WifiUtils$$ExternalSyntheticLambda2.INSTANCE);
            Elvis.m668of(this.mScanResultsListener).ifPresent(WifiUtils$$ExternalSyntheticLambda1.INSTANCE);
            Elvis.m668of(this.mConnectionWpsListener).ifPresent(WifiUtils$$ExternalSyntheticLambda3.INSTANCE);
            this.mWifiConnectionCallback.errorConnect();
            wifiLog("COULDN'T ENABLE WIFI");
        }
    }

    public void enableWifi() {
        enableWifi((WifiStateListener) null);
    }

    public WifiConnectorBuilder scanWifi(ScanResultsListener scanResultsListener) {
        this.mScanResultsListener = scanResultsListener;
        return this;
    }

    public WifiConnectorBuilder.WifiSuccessListener connectWith(String str, String str2) {
        this.mSsid = str;
        this.mPassword = str2;
        return this;
    }

    public WifiConnectorBuilder.WifiSuccessListener connectWith(String str, String str2, String str3) {
        this.mSsid = str;
        this.mBssid = str2;
        this.mPassword = str3;
        return this;
    }

    public WifiConnectorBuilder.WifiSuccessListener connectWithScanResult(String str, ConnectionScanResultsListener connectionScanResultsListener) {
        this.mConnectionScanResultsListener = connectionScanResultsListener;
        this.mPassword = str;
        return this;
    }

    public WifiConnectorBuilder.WifiWpsSuccessListener connectWithWps(String str, String str2) {
        this.mBssid = str;
        this.mPassword = str2;
        return this;
    }

    public void cancelAutoConnect() {
        ConnectorUtils.unregisterReceiver(this.mContext, this.mWifiStateReceiver);
        ConnectorUtils.unregisterReceiver(this.mContext, this.mWifiScanReceiver);
        ConnectorUtils.unregisterReceiver(this.mContext, this.mWifiConnectionReceiver);
        Elvis.m668of(this.mSingleScanResult).ifPresent(new WifiUtils$$ExternalSyntheticLambda0(this));
        ConnectorUtils.reenableAllHotspots(this.mWifiManager);
    }

    /* renamed from: lambda$cancelAutoConnect$3$com-thanosfisherman-wifiutils-WifiUtils */
    public /* synthetic */ void mo33877xe8f2a751(ScanResult scanResult) {
        ConnectorUtils.cleanPreviousConfiguration(this.mWifiManager, scanResult);
    }

    public WifiConnectorBuilder.WifiSuccessListener setTimeout(long j) {
        this.mTimeoutMillis = j;
        this.mWifiConnectionReceiver.setTimeout(j);
        return this;
    }

    public WifiConnectorBuilder.WifiWpsSuccessListener setWpsTimeout(long j) {
        this.mWpsTimeoutMillis = j;
        return this;
    }

    public WifiConnectorBuilder onConnectionWpsResult(ConnectionWpsListener connectionWpsListener) {
        this.mConnectionWpsListener = connectionWpsListener;
        return this;
    }

    public WifiConnectorBuilder onConnectionResult(ConnectionSuccessListener connectionSuccessListener) {
        this.mConnectionSuccessListener = connectionSuccessListener;
        return this;
    }

    public void start() {
        ConnectorUtils.unregisterReceiver(this.mContext, this.mWifiStateReceiver);
        ConnectorUtils.unregisterReceiver(this.mContext, this.mWifiScanReceiver);
        ConnectorUtils.unregisterReceiver(this.mContext, this.mWifiConnectionReceiver);
        enableWifi((WifiStateListener) null);
    }

    public void disableWifi() {
        if (this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.setWifiEnabled(false);
            ConnectorUtils.unregisterReceiver(this.mContext, this.mWifiStateReceiver);
            ConnectorUtils.unregisterReceiver(this.mContext, this.mWifiScanReceiver);
            ConnectorUtils.unregisterReceiver(this.mContext, this.mWifiConnectionReceiver);
        }
        wifiLog("WiFi Disabled");
    }
}
