package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customDialogs.GenericNoteDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.OnboardingStep1Of4IndiaBinding;
import com.jch.racWiFi.iduOnBoarding.ViewModel.OnBoardingViewModel;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch.racWiFi.userOnboarding.presenter.APTutorialPresenter;
import com.jch.racWiFi.userOnboarding.presenter.presenterImpl.APTutorialPresenterImpl;
import com.jch.racWiFi.userOnboarding.view.IAPTutorialView;
import com.jch_hitachi.aircloudglobal.R;
import java.util.Objects;

public class ConfirmWirelessNetworkIndianRac extends GenericFragment implements IAPTutorialView {
    private final APTutorialPresenter apTutorialPresenter = new APTutorialPresenterImpl(this);
    private String[] locationPermissionArr;
    private Activity mActivity;
    private OnboardingStep1Of4IndiaBinding mBinding;
    private boolean mIsHasFocusEtPassword;
    private long mStartTime;
    private long mStartTimeEtPassword;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda6(this);
    private OnBoardingViewModel onBoardingViewModel;

    static /* synthetic */ boolean lambda$singleSelectionPopup$7(Dialog dialog, View view) {
        return true;
    }

    public void onConnectedWifiNetworkFound(WifiInfo wifiInfo) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        this.locationPermissionArr = new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION};
        this.onBoardingViewModel = getCoreActivity().getGlobalViewModelRepository().getOnBoardingViewModel();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (OnboardingStep1Of4IndiaBinding) DataBindingUtil.inflate(layoutInflater, R.layout.onboarding_step_1_of_4_india, viewGroup, false);
        updateStep();
        showDialog();
        return this.mBinding.getRoot();
    }

    private void showDialog() {
        GenericNoteDialog.Companion.newInstance(getString(R.string.grac_adapter_is_compatible_only)).show(getParentFragmentManager(), GenericNoteDialog.class.getCanonicalName());
    }

    private void updateStep() {
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (navArgument != null && navArgument.getDefaultValue() != null) {
            boolean booleanValue = ((Boolean) navArgument.getDefaultValue()).booleanValue();
            String adapterType = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getAdapterType();
            if (booleanValue) {
                handleQRSuccess(adapterType);
            } else {
                handleQRFail(adapterType);
            }
        }
    }

    private void handleQRFail(String str) {
        str.hashCode();
        if (str.equals("2")) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, 6, 3);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(getString(R.string.common_lbl_step3));
        }
    }

    private void handleQRSuccess(String str) {
        str.hashCode();
        if (str.equals("2")) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, 4, 2);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(getString(R.string.common_lbl_step2));
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.backButton.setOnClickListener(new ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda2(this));
        this.mBinding.include.btChangeRouter.setOnClickListener(new ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda3(this));
        this.mBinding.forwardButton.setOnClickListener(new ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda4(this));
        this.mBinding.include.etPasswordField.setOnFocusChangeListener(new ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda5(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConfirmWirelessNetworkIndianRac */
    public /* synthetic */ void mo31334x4f76a192(View view) {
        goBack();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConfirmWirelessNetworkIndianRac */
    public /* synthetic */ void mo31335x17d4c631(View view) {
        OnClickChangeWirelessNetwork();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConfirmWirelessNetworkIndianRac */
    public /* synthetic */ void mo31336xe032ead0(View view) {
        goNext();
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConfirmWirelessNetworkIndianRac */
    public /* synthetic */ void mo31337xa8910f6f(View view, boolean z) {
        if (!z) {
            logEvents(Events.CONNECTION_SUCCESSFUL.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeEtPassword));
        } else if (!this.mIsHasFocusEtPassword) {
            this.mIsHasFocusEtPassword = true;
            this.mStartTimeEtPassword = System.currentTimeMillis();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        checkForLocationEnabledOrNot();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    /* access modifiers changed from: package-private */
    public void OnClickChangeWirelessNetwork() {
        this.mStartTime = System.currentTimeMillis();
        startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 516);
    }

    /* access modifiers changed from: package-private */
    public void goNext() {
        moveForward();
    }

    private void moveForward() {
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.to_switchOnAirConditionerFrag, getArguments());
            return;
        }
        String currentSsid = WifiUtils.getInstance().getCurrentSsid(this.mActivity);
        if (currentSsid == null || currentSsid.isEmpty()) {
            singleSelectionPopup(getString(R.string.onboard_alert_pleaseSelectHomeRouter));
            return;
        }
        String replaceAll = currentSsid.replaceAll("^\"|\"$", "");
        Editable text = this.mBinding.include.etPasswordField.getText();
        Objects.requireNonNull(text);
        Editable editable = text;
        String obj = text.toString();
        String addedAcRouterSsid = this.onBoardingViewModel.getAddedAcRouterSsid();
        boolean isIduOnline = this.onBoardingViewModel.isIduOnline();
        if (addedAcRouterSsid != null && addedAcRouterSsid.equals(replaceAll) && isIduOnline) {
            singleSelectionPopup(getString(R.string.onboard_alert_pleaseSelectAnotherNw));
        } else if (obj.isEmpty()) {
            singleSelectionPopup(getString(R.string.onboard_alert_plsEnterPwd));
        } else {
            WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.setSsid(replaceAll);
            WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.setPassword(obj);
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.to_switchOnAirConditionerFrag, getArguments());
        }
    }

    public void onConnectedWifiNetworkFound(String str) {
        String replaceAll = str.replaceAll("^\"|\"$", "");
        this.mBinding.include.tvHomeRouterSsid.setText(getString(R.string.onboard_lbl_ssidColonJch) + " " + replaceAll);
        WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.setSsid(replaceAll);
    }

    public void onNoConnectedWifiNetworkFound() {
        this.mBinding.include.tvHomeRouterSsid.setText(getString(R.string.onboard_alert_noNwAvailable));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 132) {
            if (i == 147) {
                checkForLocationEnabledOrNot();
            } else if (i == 516) {
                new Handler().postDelayed(new ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda9(this), 500);
            }
        } else if (checkPermissions(this.locationPermissionArr)) {
            checkForLocationEnabledOrNot();
        } else {
            showAlert(getString(R.string.onboard_alert_locationNotEnabled));
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    /* renamed from: lambda$onActivityResult$4$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConfirmWirelessNetworkIndianRac */
    public /* synthetic */ void mo31333xfa9b0ed() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            checkForLocationEnabledOrNot();
            logEvents(Events.CHANGE_WIRELESS_NETWORK.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTime));
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

    private void checkForLocationEnabledOrNot() {
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            onConnectedWifiNetworkFound(getString(R.string.onboard_lbl_dummyNetwork));
        } else if (!checkPermissions(this.locationPermissionArr)) {
            requestPermissions(this.locationPermissionArr, 177);
        } else if (getCoreActivity().isLocationEnabled()) {
            this.apTutorialPresenter.requestCurrentlyConnectedWifiNetwork(getContext());
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
            builder.setMessage((CharSequence) getString(R.string.common_alert_enableLocationService)).setCancelable(false).setPositiveButton((CharSequence) getString(R.string.common_btn_yes), (DialogInterface.OnClickListener) new ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda0(this)).setNegativeButton((int) R.string.common_btn_no, (DialogInterface.OnClickListener) new ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda1(this));
            AlertDialog create = builder.create();
            create.setCancelable(false);
            create.show();
        }
    }

    /* renamed from: lambda$checkForLocationEnabledOrNot$5$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConfirmWirelessNetworkIndianRac */
    public /* synthetic */ void mo31330x9e91f7d2(DialogInterface dialogInterface, int i) {
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 147);
    }

    /* renamed from: lambda$checkForLocationEnabledOrNot$6$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConfirmWirelessNetworkIndianRac */
    public /* synthetic */ void mo31331x66f01c71(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        Toaster.makeToast(getActivity(), getString(R.string.onboard_alert_locationNotEnabled), 0);
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    private void singleSelectionPopup(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda8.INSTANCE);
        singleChoiceDialog.show();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 177) {
            return;
        }
        if (checkPermissions(this.locationPermissionArr)) {
            checkForLocationEnabledOrNot();
            return;
        }
        for (String str : strArr) {
            if (!shouldShowRequestPermissionRationale(str)) {
                showPermissionSettingDialog(str, this, this.mFragmentToActivityCallback.getNavController());
            } else {
                showPermissionDeniedDialog(this.mBinding.rootView, str, this.mFragmentToActivityCallback.getNavController());
            }
        }
    }

    private void showAlert(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new ConfirmWirelessNetworkIndianRac$$ExternalSyntheticLambda7(this));
        singleChoiceDialog.show();
    }

    /* renamed from: lambda$showAlert$8$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConfirmWirelessNetworkIndianRac */
    public /* synthetic */ boolean mo31338xc987256f(Dialog dialog, View view) {
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) || this.mFragmentToActivityCallback.getNavController() == null) {
            return true;
        }
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        return true;
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl.ConfirmWirelessNetworkIndianRac$1 */
    static /* synthetic */ class C22011 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl.ConfirmWirelessNetworkIndianRac.C22011.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$9$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-ConfirmWirelessNetworkIndianRac */
    public /* synthetic */ void mo31332xe386376d(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        int i = C22011.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            moveForward();
        } else if (i == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }
}
