package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.FragmentApAddRacIndiaBinding;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.userOnboarding.presenter.APTutorialPresenter;
import com.jch.racWiFi.userOnboarding.presenter.presenterImpl.APTutorialPresenterImpl;
import com.jch.racWiFi.userOnboarding.view.IAPTutorialView;
import com.jch_hitachi.aircloudglobal.R;

public class ConnectToAirConditionerSuccess extends GenericFragment implements IAPTutorialView {
    private final APTutorialPresenter apTutorialPresenter = new APTutorialPresenterImpl(this);
    private Activity mActivity;
    private FragmentApAddRacIndiaBinding mBinding;
    private final Observer<SwipeScreenType> mSwipeScreenTypeObserver = new ConnectToAirConditionerSuccess$$ExternalSyntheticLambda3(this);
    private boolean racNetworkSelected = false;

    static /* synthetic */ boolean lambda$moveForward$3(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$singleSelectionPopup$5(Dialog dialog, View view) {
        return true;
    }

    public void onConnectedWifiNetworkFound(WifiInfo wifiInfo) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (FragmentApAddRacIndiaBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_ap_add_rac_india, viewGroup, false);
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (navArgument != null) {
            boolean booleanValue = ((Boolean) navArgument.getDefaultValue()).booleanValue();
            String adapterType = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getAdapterType();
            if (!booleanValue && adapterType.equals("2")) {
                this.mBinding.includeLinearProgressIndicator.textViewStep1.setText(getString(R.string.common_lbl_step4));
            }
        }
        return this.mBinding.getRoot();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        String ssid = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid();
        String password = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getPassword();
        if (ssid != null && !ssid.isEmpty()) {
            this.mBinding.tvSsid.setText(getString(R.string.onboard_lbl_ssidColonJch) + " " + ssid);
        }
        if (password != null && !password.isEmpty()) {
            this.mBinding.textViewPasswordRacWifi.setText(password);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.buttonChangeRouter.setOnClickListener(new ConnectToAirConditionerSuccess$$ExternalSyntheticLambda0(this));
        this.mBinding.forwardButton.setOnClickListener(new ConnectToAirConditionerSuccess$$ExternalSyntheticLambda1(this));
        this.mBinding.backButton.setOnClickListener(new ConnectToAirConditionerSuccess$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConnectToAirConditionerSuccess */
    public /* synthetic */ void mo31354x8d3f6f1b(View view) {
        OnClickChangeWirelessNetwork();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConnectToAirConditionerSuccess */
    public /* synthetic */ void mo31355xe64aba9c(View view) {
        goNext();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConnectToAirConditionerSuccess */
    public /* synthetic */ void mo31356x3f56061d(View view) {
        goBack();
    }

    /* access modifiers changed from: package-private */
    public void OnClickChangeWirelessNetwork() {
        startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 516);
    }

    /* access modifiers changed from: package-private */
    public void goNext() {
        moveForward();
    }

    private void moveForward() {
        if (this.racNetworkSelected) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_addRac_to_configuringDeviceUdpExchange);
            return;
        }
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.onboard_alert_pleaseSelectRacNw));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), ConnectToAirConditionerSuccess$$ExternalSyntheticLambda4.INSTANCE);
        singleChoiceDialog.show();
    }

    /* access modifiers changed from: package-private */
    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 516) {
            new Handler().postDelayed(new ConnectToAirConditionerSuccess$$ExternalSyntheticLambda6(this), 500);
        }
    }

    /* renamed from: lambda$onActivityResult$4$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConnectToAirConditionerSuccess */
    public /* synthetic */ void mo31353x70f8860() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            this.apTutorialPresenter.requestCurrentlyConnectedWifiNetwork(this.mActivity);
        }
    }

    public void onConnectedWifiNetworkFound(String str) {
        if (!str.equals(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid().replaceAll("^\"|\"$", ""))) {
            singleSelectionPopup(getString(R.string.onboard_alert_plsSelectRacNameNw, WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid()));
            return;
        }
        this.racNetworkSelected = true;
    }

    public void onNoConnectedWifiNetworkFound() {
        singleSelectionPopup(this.mActivity.getString(R.string.onboard_alert_notSelectedRacNw));
    }

    private void singleSelectionPopup(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), ConnectToAirConditionerSuccess$$ExternalSyntheticLambda5.INSTANCE);
        singleChoiceDialog.show();
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl.ConnectToAirConditionerSuccess$1 */
    static /* synthetic */ class C22021 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl.ConnectToAirConditionerSuccess.C22021.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$6$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConnectToAirConditionerSuccess */
    public /* synthetic */ void mo31352x8f264f5d(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        int i = C22021.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            moveForward();
        } else if (i == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }
}
