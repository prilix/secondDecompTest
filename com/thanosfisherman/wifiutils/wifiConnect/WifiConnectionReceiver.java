package com.thanosfisherman.wifiutils.wifiConnect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiManager;
import com.thanosfisherman.elvis.Elvis;
import com.thanosfisherman.elvis.Objects;
import com.thanosfisherman.wifiutils.ConnectorUtils;
import com.thanosfisherman.wifiutils.WeakHandler;
import com.thanosfisherman.wifiutils.WifiUtils;

public final class WifiConnectionReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public final WeakHandler handler;
    private final Runnable handlerCallback = new Runnable() {
        public void run() {
            WifiUtils.wifiLog("Connection Timed out...");
            ConnectorUtils.reEnableNetworkIfPossible(WifiConnectionReceiver.this.mWifiManager, WifiConnectionReceiver.this.mScanResult);
            if (ConnectorUtils.isAlreadyConnected(WifiConnectionReceiver.this.mWifiManager, (String) Elvis.m668of(WifiConnectionReceiver.this.mScanResult).next(WifiConnectionReceiver$1$$ExternalSyntheticLambda0.INSTANCE).get())) {
                WifiConnectionReceiver.this.mWifiConnectionCallback.successfulConnect();
            } else {
                WifiConnectionReceiver.this.mWifiConnectionCallback.errorConnect();
            }
            WifiConnectionReceiver.this.handler.removeCallbacks(this);
        }
    };
    private long mDelay;
    /* access modifiers changed from: private */
    public ScanResult mScanResult;
    /* access modifiers changed from: private */
    public final WifiConnectionCallback mWifiConnectionCallback;
    /* access modifiers changed from: private */
    public final WifiManager mWifiManager;

    public WifiConnectionReceiver(WifiConnectionCallback wifiConnectionCallback, WifiManager wifiManager, long j) {
        this.mWifiConnectionCallback = wifiConnectionCallback;
        this.mWifiManager = wifiManager;
        this.mDelay = j;
        this.handler = new WeakHandler();
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        WifiUtils.wifiLog("Connection Broadcast action: " + action);
        if (Objects.equals("android.net.wifi.STATE_CHANGE", action)) {
            if (ConnectorUtils.isAlreadyConnected(this.mWifiManager, (String) Elvis.m668of(this.mScanResult).next(WifiConnectionReceiver$$ExternalSyntheticLambda0.INSTANCE).get())) {
                this.handler.removeCallbacks(this.handlerCallback);
                this.mWifiConnectionCallback.successfulConnect();
            }
        } else if (Objects.equals("android.net.wifi.supplicant.STATE_CHANGE", action)) {
            SupplicantState supplicantState = (SupplicantState) intent.getParcelableExtra("newState");
            int intExtra = intent.getIntExtra("supplicantError", -1);
            if (supplicantState == null) {
                this.handler.removeCallbacks(this.handlerCallback);
                this.mWifiConnectionCallback.errorConnect();
                return;
            }
            WifiUtils.wifiLog("Connection Broadcast action: " + supplicantState);
            int i = C27122.$SwitchMap$android$net$wifi$SupplicantState[supplicantState.ordinal()];
            if (i == 1 || i == 2) {
                if (ConnectorUtils.isAlreadyConnected(this.mWifiManager, (String) Elvis.m668of(this.mScanResult).next(WifiConnectionReceiver$$ExternalSyntheticLambda1.INSTANCE).get())) {
                    this.handler.removeCallbacks(this.handlerCallback);
                    this.mWifiConnectionCallback.successfulConnect();
                }
            } else if (i == 3) {
                if (intExtra == 1) {
                    WifiUtils.wifiLog("Authentication error...");
                    this.handler.removeCallbacks(this.handlerCallback);
                    this.mWifiConnectionCallback.errorConnect();
                    return;
                }
                WifiUtils.wifiLog("Disconnected. Re-attempting to connect...");
                ConnectorUtils.reEnableNetworkIfPossible(this.mWifiManager, this.mScanResult);
            }
        }
    }

    /* renamed from: com.thanosfisherman.wifiutils.wifiConnect.WifiConnectionReceiver$2 */
    static /* synthetic */ class C27122 {
        static final /* synthetic */ int[] $SwitchMap$android$net$wifi$SupplicantState;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                android.net.wifi.SupplicantState[] r0 = android.net.wifi.SupplicantState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$net$wifi$SupplicantState = r0
                android.net.wifi.SupplicantState r1 = android.net.wifi.SupplicantState.COMPLETED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$android$net$wifi$SupplicantState     // Catch:{ NoSuchFieldError -> 0x001d }
                android.net.wifi.SupplicantState r1 = android.net.wifi.SupplicantState.FOUR_WAY_HANDSHAKE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$android$net$wifi$SupplicantState     // Catch:{ NoSuchFieldError -> 0x0028 }
                android.net.wifi.SupplicantState r1 = android.net.wifi.SupplicantState.DISCONNECTED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.thanosfisherman.wifiutils.wifiConnect.WifiConnectionReceiver.C27122.<clinit>():void");
        }
    }

    public void setTimeout(long j) {
        this.mDelay = j;
    }

    public WifiConnectionReceiver activateTimeoutHandler(ScanResult scanResult) {
        this.mScanResult = scanResult;
        this.handler.postDelayed(this.handlerCallback, this.mDelay);
        return this;
    }
}
