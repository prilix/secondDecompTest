package com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.OnSwipeTouchListener;
import com.jch.racWiFi.databinding.EnableWpsBuiltInStep4Of4FrameBinding;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch_hitachi.aircloudglobal.R;

public class EnableWpsOnHomeRouterFragment extends GenericFragment {
    private Activity mActivity;
    private EnableWpsBuiltInStep4Of4FrameBinding mBinding;
    private OnSwipeTouchListener mOnSwipeTouchListener = new OnSwipeTouchListener(this.mActivity) {
        public void onSwipeLeft() {
            Logger.m45d(GenericFragment.TAG, "onSwipeLeft");
            EnableWpsOnHomeRouterFragment.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_enableWpsOnHomeRouterFragment_to_configuringDeviceCheckMdns);
        }

        public void onSwipeRight() {
            Logger.m45d(GenericFragment.TAG, "onSwipeRight");
            EnableWpsOnHomeRouterFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    };
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new EnableWpsOnHomeRouterFragment$$ExternalSyntheticLambda2(this);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (EnableWpsBuiltInStep4Of4FrameBinding) DataBindingUtil.inflate(layoutInflater, R.layout.enable_wps_built_in_step_4_of_4_frame, viewGroup, false);
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
        if (string2.equals(StatusCode.BUILTIN_WIRELESS)) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, 6, 6);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step6);
        } else if (string2.equals("1") && (string = bundle.getString(Constants.Keys.METHOD)) != null) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, string.equals(StatusCode.Method.WPS) ? 6 : 7, 6);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step6);
        }
    }

    private void handleQRSuccess(Bundle bundle) {
        String string;
        String string2 = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string2.hashCode();
        if (string2.equals(StatusCode.BUILTIN_WIRELESS)) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, 5, 5);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step5);
        } else if (string2.equals("1") && (string = bundle.getString(Constants.Keys.METHOD)) != null) {
            updateProgress(this.mBinding.include.includeLinearProgressIndicator.linearProgressIndicator, string.equals(StatusCode.Method.WPS) ? 5 : 6, 5);
            this.mBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step5);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.backButton.setOnClickListener(new EnableWpsOnHomeRouterFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.forwardButton.setOnClickListener(new EnableWpsOnHomeRouterFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.includedLayout.setOnTouchListener(this.mOnSwipeTouchListener);
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-iduOnBoarding-wps-withQrCode-view-EnableWpsOnHomeRouterFragment */
    public /* synthetic */ void mo31568xa36f0195(View view) {
        goBack();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-iduOnBoarding-wps-withQrCode-view-EnableWpsOnHomeRouterFragment */
    public /* synthetic */ void mo31569x83e85796(View view) {
        goNext();
    }

    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: package-private */
    public void goNext() {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_enableWpsOnHomeRouterFragment_to_configuringDeviceCheckMdns, getArguments());
    }

    private void singleSelectionPopup(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.onboard_btn_changeWirelessNw), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                EnableWpsOnHomeRouterFragment.this.startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 516);
                return true;
            }
        });
        singleChoiceDialog.show();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 516) {
            String currentSsid = WifiUtils.getInstance().getCurrentSsid(this.mActivity);
            String replaceAll = WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getSsid().replaceAll("^\"|\"$", "");
            if (currentSsid == null) {
                singleSelectionPopup(getString(R.string.onboard_alert_notSelectedHomeRouterNw));
            } else if (currentSsid.replaceAll("^\"|\"$", "").equals(replaceAll)) {
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_enableWpsOnHomeRouterFragment_to_configuringDeviceCheckMdns);
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view.EnableWpsOnHomeRouterFragment$3 */
    static /* synthetic */ class C22583 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view.EnableWpsOnHomeRouterFragment.C22583.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$2$com-jch-racWiFi-iduOnBoarding-wps-withQrCode-view-EnableWpsOnHomeRouterFragment */
    public /* synthetic */ void mo31567xc9ddbed3(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        int i = C22583.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_enableWpsOnHomeRouterFragment_to_configuringDeviceCheckMdns);
        } else if (i == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }
}
