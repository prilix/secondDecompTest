package com.thanosfisherman.wifiutils;

import com.thanosfisherman.wifiutils.wifiConnect.ConnectionScanResultsListener;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;
import com.thanosfisherman.wifiutils.wifiScan.ScanResultsListener;
import com.thanosfisherman.wifiutils.wifiState.WifiStateListener;
import com.thanosfisherman.wifiutils.wifiWps.ConnectionWpsListener;

public interface WifiConnectorBuilder {

    public interface WifiSuccessListener {
        WifiConnectorBuilder onConnectionResult(ConnectionSuccessListener connectionSuccessListener);

        WifiSuccessListener setTimeout(long j);
    }

    public interface WifiUtilsBuilder {
        void cancelAutoConnect();

        WifiSuccessListener connectWith(String str, String str2);

        WifiSuccessListener connectWith(String str, String str2, String str3);

        WifiSuccessListener connectWithScanResult(String str, ConnectionScanResultsListener connectionScanResultsListener);

        WifiWpsSuccessListener connectWithWps(String str, String str2);

        void disableWifi();

        void enableWifi();

        void enableWifi(WifiStateListener wifiStateListener);

        WifiConnectorBuilder scanWifi(ScanResultsListener scanResultsListener);
    }

    public interface WifiWpsSuccessListener {
        WifiConnectorBuilder onConnectionWpsResult(ConnectionWpsListener connectionWpsListener);

        WifiWpsSuccessListener setWpsTimeout(long j);
    }

    void start();
}
