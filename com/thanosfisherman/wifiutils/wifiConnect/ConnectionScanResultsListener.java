package com.thanosfisherman.wifiutils.wifiConnect;

import android.net.wifi.ScanResult;
import java.util.List;

public interface ConnectionScanResultsListener {
    ScanResult onConnectWithScanResult(List<ScanResult> list);
}
