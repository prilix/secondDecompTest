package com.jch.racWiFi.iduOnBoarding.common.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.Lifecycle;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.networkUtil.PairingViaUDP;
import com.jch_hitachi.aircloudglobal.R;

public class ConfiguringDeviceUdpExchange extends GenericFragment implements PairingViaUDP.OnPairingStatus {
    private OnBackPressedCallback mOnBackPressedCallback;
    private final PairingViaUDP mPairingViaUDP = new PairingViaUDP(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS, WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS, this);
    private final Handler mTimeoutHandler = new Handler();
    private boolean paringDone;

    private void showToastMessage(String str) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mOnBackPressedCallback = new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, this.mOnBackPressedCallback);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.configuring_device_frame, viewGroup, false);
        if (Constants.IS_DEMO_MODE) {
            new Handler().postDelayed(new ConfiguringDeviceUdpExchange$$ExternalSyntheticLambda0(this), 3000);
        } else {
            this.mPairingViaUDP.startParing();
            this.mTimeoutHandler.removeCallbacksAndMessages((Object) null);
            this.mTimeoutHandler.postDelayed(new ConfiguringDeviceUdpExchange$$ExternalSyntheticLambda1(this), 30000);
        }
        return inflate;
    }

    /* renamed from: lambda$onCreateView$0$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceUdpExchange */
    public /* synthetic */ void mo31424xc5c9f6f2() {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceUdpExchange_to_configuringDeviceCheckMdns);
    }

    /* renamed from: lambda$onCreateView$1$com-jch-racWiFi-iduOnBoarding-common-view-ConfiguringDeviceUdpExchange */
    public /* synthetic */ void mo31425x4814abd1() {
        showToastMessage("Pairing Timeout");
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceUdpExchange_to_configuringFailedUdpExchange);
    }

    public void onResume() {
        super.onResume();
        if (this.paringDone) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceUdpExchange_to_configuringDeviceCheckMdns, getArguments());
        }
    }

    public void onSsidAckReceived() {
        showToastMessage("Ssid Ack Received ");
    }

    public void onPasswordAckReceived() {
        showToastMessage("Password Ack Received ");
    }

    public void onWrongAckReceived() {
        showToastMessage("Worng Ack Received ");
        this.mPairingViaUDP.interuptParing();
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                ConfiguringDeviceUdpExchange.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceUdpExchange_to_configuringFailedUdpExchange);
            }
        });
    }

    public void onPairingCommandSequenceComplete() {
        this.mTimeoutHandler.removeCallbacksAndMessages((Object) null);
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ConfiguringDeviceUdpExchange.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringDeviceUdpExchange_to_configuringDeviceCheckMdns);
                }
            });
        } else {
            this.paringDone = true;
        }
    }
}
