package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views;

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
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Utils.IntentUtils;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.OnboardingStep1Of4IndiaBinding;
import com.jch.racWiFi.iduOnBoarding.ViewModel.OnBoardingViewModel;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch.racWiFi.userOnboarding.presenter.presenterImpl.APTutorialPresenterImpl;
import com.jch.racWiFi.userOnboarding.view.IAPTutorialView;
import com.jch_hitachi.aircloudglobal.R;

public class ConfirmNetworkApFragment extends GenericFragment implements View.OnClickListener, IAPTutorialView {
    private APTutorialPresenterImpl apTutorialPresenter;
    private Activity mActivity;
    private OnboardingStep1Of4IndiaBinding mBinding;
    private boolean mIsHasFocusEtPassword;
    private long mStartTimeEtPassword;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new ConfirmNetworkApFragment$$ExternalSyntheticLambda1(this);
    private OnBoardingViewModel onBoardingViewModel;

    static /* synthetic */ boolean lambda$moveForward$1(Dialog dialog, View view) {
        return true;
    }

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
        this.mBinding.backButton.setOnClickListener(this);
        this.mBinding.include.btChangeRouter.setOnClickListener(this);
        updateStep();
        return this.mBinding.getRoot();
    }

    private void updateStep() {
        Object defaultValue;
        String string;
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (navArgument != null && (defaultValue = navArgument.getDefaultValue()) != null) {
            boolean booleanValue = ((Boolean) defaultValue).booleanValue();
            Bundle arguments = getArguments();
            if (arguments != null && (string = arguments.getString(Constants.Keys.ADAPTER_TYPE)) != null) {
                if (booleanValue) {
                    handleQRSuccess(string);
                } else {
                    handleQRFail(string);
                }
            }
        }
    }

    private void handleQRFail(String str) {
        str.hashCode();
        if (str.equals(StatusCode.BUILTIN_WIRELESS) || str.equals("1")) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, 7, 4);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step4);
        }
    }

    private void handleQRSuccess(String str) {
        str.hashCode();
        if (str.equals(StatusCode.BUILTIN_WIRELESS) || str.equals("1")) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, 6, 3);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step3);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.include.etPasswordField.setOnFocusChangeListener(new ConfirmNetworkApFragment$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-iduOnBoarding-onBoadingInApMode-views-ConfirmNetworkApFragment */
    public /* synthetic */ void mo31560x2b141e86(View view, boolean z) {
        if (!z) {
            logEvents(Events.CONNECTION_SUCCESSFUL.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeEtPassword));
        } else if (!this.mIsHasFocusEtPassword) {
            this.mIsHasFocusEtPassword = true;
            this.mStartTimeEtPassword = System.currentTimeMillis();
        }
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            onConnectedWifiNetworkFound(getString(R.string.onboard_lbl_dummyNetwork));
        } else {
            this.apTutorialPresenter.requestCurrentlyConnectedWifiNetwork(this.mActivity);
        }
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
        WifiUtils.getInstance().show5GHzNotSupportedDialog(requireActivity(), ConfirmNetworkApFragment$$ExternalSyntheticLambda2.INSTANCE);
        String replaceAll = string.replaceAll("^\"|\"$", "");
        if (isNullOrEmpty(replaceAll) || !replaceAll.equals(this.onBoardingViewModel.getAddedAcRouterSsid())) {
            String obj = this.mBinding.include.etPasswordField.getText().toString();
            if (obj.isEmpty()) {
                showErrorPopUp(getString(R.string.onboard_alert_plsEnterPwd));
                return;
            }
            WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.setSsid(replaceAll);
            WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.setPassword(obj);
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_apConfirmNetworkFragment_to_addHomeRouter, getArguments());
            return;
        }
        showErrorPopUp(getString(R.string.onboard_alert_pleaseSelectAnotherNw));
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

    /* renamed from: com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views.ConfirmNetworkApFragment$2 */
    static /* synthetic */ class C22522 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views.ConfirmNetworkApFragment.C22522.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$2$com-jch-racWiFi-iduOnBoarding-onBoadingInApMode-views-ConfirmNetworkApFragment */
    public /* synthetic */ void mo31559x4d59c188(SwipeScreenType swipeScreenType) {
        int i = C22522.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (i == 2) {
            moveForward();
        }
    }
}
