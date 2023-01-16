package com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Utils.IntentUtils;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.OnboardingStep1Of4IndiaBinding;
import com.jch.racWiFi.iduOnBoarding.ViewModel.OnBoardingViewModel;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch.racWiFi.userOnboarding.presenter.presenterImpl.APTutorialPresenterImpl;
import com.jch.racWiFi.userOnboarding.view.IAPTutorialView;
import com.jch_hitachi.aircloudglobal.R;

public class ConfirmNetworkWpsFragment extends GenericFragment implements View.OnClickListener, IAPTutorialView {
    private APTutorialPresenterImpl apTutorialPresenter;
    private Activity mActivity;
    private OnboardingStep1Of4IndiaBinding mBinding;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new ConfirmNetworkWpsFragment$$ExternalSyntheticLambda0(this);
    private OnBoardingViewModel onBoardingViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        this.apTutorialPresenter = new APTutorialPresenterImpl(this);
        this.onBoardingViewModel = getCoreActivity().getGlobalViewModelRepository().getOnBoardingViewModel();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        OnboardingStep1Of4IndiaBinding onboardingStep1Of4IndiaBinding = (OnboardingStep1Of4IndiaBinding) DataBindingUtil.inflate(layoutInflater, R.layout.onboarding_step_1_of_4_india, viewGroup, false);
        this.mBinding = onboardingStep1Of4IndiaBinding;
        onboardingStep1Of4IndiaBinding.forwardButton.setOnClickListener(this);
        this.mBinding.include.btChangeRouter.setOnClickListener(this);
        this.mBinding.backButton.setOnClickListener(this);
        this.mBinding.include.textViewConfirmWifiNetworkSubTitle.setText(R.string.onboard_lbl_confirmWirelessNwSmallDesc);
        this.mBinding.include.textInputLayout.setVisibility(8);
        updateStep();
        return this.mBinding.getRoot();
    }

    private void updateStep() {
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (navArgument != null && navArgument.getDefaultValue() != null) {
            boolean booleanValue = ((Boolean) navArgument.getDefaultValue()).booleanValue();
            Bundle arguments = getArguments();
            if (arguments == null) {
                return;
            }
            if (booleanValue) {
                handleQRSuccess(arguments);
            } else {
                handleQRFail(arguments);
            }
        }
    }

    private void handleQRFail(Bundle bundle) {
        String string;
        String string2 = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string2.hashCode();
        int i = 6;
        if (string2.equals(StatusCode.BUILTIN_WIRELESS)) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, 6, 4);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step4);
        } else if (string2.equals("1") && (string = bundle.getString(Constants.Keys.METHOD)) != null) {
            LinearProgressIndicator linearProgressIndicator = this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator;
            if (!string.equals(StatusCode.Method.WPS)) {
                i = 7;
            }
            updateProgress(linearProgressIndicator, i, 4);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step4);
        }
    }

    private void handleQRSuccess(Bundle bundle) {
        String string;
        String string2 = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string2.hashCode();
        int i = 5;
        if (string2.equals(StatusCode.BUILTIN_WIRELESS)) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, 5, 3);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step3);
        } else if (string2.equals("1") && (string = bundle.getString(Constants.Keys.METHOD)) != null) {
            LinearProgressIndicator linearProgressIndicator = this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator;
            if (!string.equals(StatusCode.Method.WPS)) {
                i = 6;
            }
            updateProgress(linearProgressIndicator, i, 3);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step3);
        }
    }

    public void onStart() {
        super.onStart();
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            onConnectedWifiNetworkFound(getString(R.string.onboard_lbl_dummyNetwork));
        } else {
            this.apTutorialPresenter.requestCurrentlyConnectedWifiNetwork(this.mActivity);
        }
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_button) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (id == R.id.bt_change_router) {
            new Intent("android.settings.WIFI_SETTINGS").addFlags(268435456);
            startActivityForResult(IntentUtils.getIntentForWiFiSettings(), 516);
        } else if (id == R.id.forward_button) {
            moveForward();
        }
    }

    private void moveForward() {
        String string = com.jch.racWiFi.Constants.IS_DEMO_MODE ? getString(R.string.onboard_lbl_dummyNetwork) : WifiUtils.getInstance().getCurrentSsid(requireActivity());
        if (string == null || string.isEmpty()) {
            showErrorPopUp(getString(R.string.onboard_lbl_selectWirelessNwDesc));
            return;
        }
        WifiUtils.getInstance().show5GHzNotSupportedDialog(requireActivity(), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                return true;
            }
        });
        if (isNullOrEmpty(string) || !string.equals(this.onBoardingViewModel.getAddedAcRouterSsid())) {
            WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.setSsid(string);
            destinationScreenNavigation();
            return;
        }
        showErrorPopUp(getString(R.string.onboard_alert_pleaseSelectAnotherNw));
    }

    private void destinationScreenNavigation() {
        Bundle arguments = getArguments();
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (navArgument == null || !((Boolean) navArgument.getDefaultValue()).booleanValue()) {
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(NavigationTransitionKeyValues.CONNECTION_METHOD_CHOSEN, new NavArgument.Builder().setDefaultValue(1011).build());
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(NavigationTransitionKeyValues.WPS_WITHOUT_QR_CODE, new NavArgument.Builder().setDefaultValue(true).build());
        } else {
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(NavigationTransitionKeyValues.CONNECTION_METHOD_CHOSEN, new NavArgument.Builder().setDefaultValue(1011).build());
        }
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_wpsConfirmNetworkFragment_to_enableWpsOnRacFragment, arguments);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 516) {
            return;
        }
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            onConnectedWifiNetworkFound(getString(R.string.onboard_lbl_dummyNetwork));
        } else {
            this.apTutorialPresenter.requestCurrentlyConnectedWifiNetwork(this.mActivity);
        }
    }

    public void onConnectedWifiNetworkFound(String str) {
        if (str != null && !str.isEmpty()) {
            String replaceAll = str.replaceAll("^\"|\"$", "");
            this.mBinding.include.tvHomeRouterSsid.setText("SSID: " + replaceAll);
        } else if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            this.mBinding.include.tvHomeRouterSsid.setText("SSID: " + getString(R.string.onboard_lbl_dummyNetwork));
        } else {
            this.mBinding.include.tvHomeRouterSsid.setText(getString(R.string.onboard_alert_noNwAvailable));
        }
    }

    public void onConnectedWifiNetworkFound(WifiInfo wifiInfo) {
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            this.mBinding.include.tvHomeRouterSsid.setText("SSID: " + getString(R.string.onboard_lbl_dummyNetwork));
            return;
        }
        WifiUtils.getInstance().show5GHzNotSupportedDialog(requireActivity(), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                return true;
            }
        });
    }

    public void onNoConnectedWifiNetworkFound() {
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            this.mBinding.include.tvHomeRouterSsid.setText("SSID: " + getString(R.string.onboard_lbl_dummyNetwork));
            return;
        }
        this.mBinding.include.tvHomeRouterSsid.setText(getString(R.string.onboard_alert_noNwAvailable));
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view.ConfirmNetworkWpsFragment$3 */
    static /* synthetic */ class C22553 {
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
                com.jch.racWiFi.Utils.SwipeScreenType r1 = com.jch.racWiFi.Utils.SwipeScreenType.RIGHT_SWIPE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.Utils.SwipeScreenType r1 = com.jch.racWiFi.Utils.SwipeScreenType.LEFT_SWIPE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view.ConfirmNetworkWpsFragment.C22553.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-iduOnBoarding-wps-withQrCode-view-ConfirmNetworkWpsFragment */
    public /* synthetic */ void mo31563x8f688abf(SwipeScreenType swipeScreenType) {
        int i = C22553.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (i == 2) {
            moveForward();
        }
    }
}
