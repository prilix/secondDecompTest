package com.jch.racWiFi.iduOnBoarding.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import androidx.navigation.NavOptions;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch_hitachi.aircloudglobal.R;

public class ConnectBackToHomeRouterFragment extends GenericFragment {
    @BindView(2131362136)
    Button connectToHomeRouter;
    @BindView(2131363063)
    View includedLayout;
    private boolean isFromConfirmWirelessNetScreen;
    private Activity mActivity;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new ConnectBackToHomeRouterFragment$$ExternalSyntheticLambda0(this);
    private Unbinder mUnbinder;
    @BindView(2131364814)
    TextView ssidNameText;
    @BindView(2131364047)
    TextView subTitleText;
    @BindView(2131364589)
    TextView textViewStep_4_of_4;

    static /* synthetic */ boolean lambda$singleSelectionPopup$0(Dialog dialog, View view) {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.enable_ap_adapter_step_4_of_4_frame_new, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        String ssid = WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getSsid();
        if (ssid != null) {
            String replaceAll = ssid.replaceAll("^\"|\"$", "");
            this.ssidNameText.setText(getString(R.string.onboard_lbl_ssidColonJch) + " " + replaceAll);
        }
        if (!NetworkConnectivity.isNetworkAvailable(this.mActivity)) {
            this.subTitleText.setText(getString(R.string.onboard_lbl_notConnectedToWirelessNwDesc));
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.isFromConfirmWirelessNetScreen = arguments.getBoolean("ACTION_OUT_OF_HOME_REMINDER");
        }
        return inflate;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mUnbinder.unbind();
    }

    @OnClick({2131362078})
    public void onClickBack() {
        if (this.isFromConfirmWirelessNetScreen) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else {
            checkWifiNetwork();
        }
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362469})
    public void onClickForward() {
        checkWifiNetwork();
    }

    private void checkWifiNetwork() {
        String currentSsid = WifiUtils.getInstance().getCurrentSsid(this.mActivity);
        String replaceAll = WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getSsid().replaceAll("^\"|\"$", "");
        if (currentSsid == null) {
            singleSelectionPopup(getString(R.string.onboard_alert_pleaseConnectToThisNw, replaceAll));
        } else if (!currentSsid.replaceAll("^\"|\"$", "").equals(replaceAll)) {
            singleSelectionPopup(getString(R.string.onboard_alert_pleaseConnectToThisNw, replaceAll));
        } else if (this.isFromConfirmWirelessNetScreen) {
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(Values.TO_ON_BOARDED_DIRECTLY, new NavArgument.Builder().setDefaultValue(true).build());
            WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setAdapterType("2");
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_connectBackToHomeRouterFragment_to_configuringDeviceCheckMdns);
        } else {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    private void singleSelectionPopup(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), ConnectBackToHomeRouterFragment$$ExternalSyntheticLambda1.INSTANCE);
        singleChoiceDialog.show();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362136})
    public void onClickConnectToHomeRouter() {
        startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 516);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        String currentSsid;
        super.onActivityResult(i, i2, intent);
        if (i == 516 && (currentSsid = WifiUtils.getInstance().getCurrentSsid(getActivity())) != null) {
            String replaceAll = currentSsid.replaceAll("^\"|\"$", "");
            Toaster.makeToast(this.mActivity, replaceAll, 1);
            if (!replaceAll.equals(WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getSsid())) {
                singleSelectionPopup(getString(R.string.onboard_alert_pleaseConnectToThisNw, WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getSsid()));
                return;
            }
            if (this.isFromConfirmWirelessNetScreen) {
                this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(Values.TO_ON_BOARDED_DIRECTLY, new NavArgument.Builder().setDefaultValue(true).build());
                WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setAdapterType("2");
            }
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_connectBackToHomeRouterFragment_to_configuringDeviceCheckMdns, (Bundle) null, new NavOptions.Builder().setPopUpTo(R.id.configuringDeviceCheckMdns, true).build());
        }
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.common.view.ConnectBackToHomeRouterFragment$1 */
    static /* synthetic */ class C22191 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.jch.racWiFi.Utils.SwipeScreenType[] r0 = com.jch.racWiFi.Utils.SwipeScreenType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType = r0
                com.jch.racWiFi.Utils.SwipeScreenType r1 = com.jch.racWiFi.Utils.SwipeScreenType.LEFT_SWIPE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.Utils.SwipeScreenType r1 = com.jch.racWiFi.Utils.SwipeScreenType.RIGHT_SWIPE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.common.view.ConnectBackToHomeRouterFragment.C22191.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$1$com-jch-racWiFi-iduOnBoarding-common-view-ConnectBackToHomeRouterFragment */
    public /* synthetic */ void mo31434xbbb012c4(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        int i = C22191.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            checkWifiNetwork();
        } else if (i == 2) {
            if (this.isFromConfirmWirelessNetScreen) {
                this.mFragmentToActivityCallback.getNavController().navigateUp();
            } else {
                checkWifiNetwork();
            }
        }
    }
}
