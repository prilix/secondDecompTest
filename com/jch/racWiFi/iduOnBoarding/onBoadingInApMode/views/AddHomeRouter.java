package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.accord.common.utils.Logger;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;
import com.jch.racWiFi.userOnboarding.model.RacTypeEnum;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch.racWiFi.userOnboarding.presenter.APTutorialPresenter;
import com.jch.racWiFi.userOnboarding.presenter.presenterImpl.APTutorialPresenterImpl;
import com.jch.racWiFi.userOnboarding.view.IAPTutorialView;
import com.jch.racWiFi.userOnboarding.view.uiComponents.dialog.ConnectedWifiDialog;
import com.jch_hitachi.aircloudglobal.R;

public class AddHomeRouter extends GenericFragment implements IAPTutorialView {
    private APTutorialPresenter apTutorialPresenter = new APTutorialPresenterImpl(this);
    private ConnectedWifiDialog connectedWifiDialog;
    private final NavArgument destinationDetails = new NavArgument.Builder().setDefaultValue(Values.NAVIGATED_FROM_AP_TUTORIAL).build();
    private Activity mActivity;
    @BindView(2131363340)
    LinearProgressIndicator mLinearProgressIndicator;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131363642)
    ConstraintLayout mRootView;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new AddHomeRouter$$ExternalSyntheticLambda2(this);
    @BindView(2131364575)
    TextView mTextViewSteps;
    private View rootView;
    private String ssid;

    static /* synthetic */ boolean lambda$singleSelectionPopup$2(Dialog dialog, View view) {
        return true;
    }

    public void onConnectedWifiNetworkFound(WifiInfo wifiInfo) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = requireActivity();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ConnectedWifiDialog connectedWifiDialog2 = new ConnectedWifiDialog(this.mActivity);
        this.connectedWifiDialog = connectedWifiDialog2;
        connectedWifiDialog2.setParentView(this.mRootView);
        this.connectedWifiDialog.setCancelable(false);
        this.connectedWifiDialog.btnOk.setOnClickListener(new AddHomeRouter$$ExternalSyntheticLambda0(this));
        this.connectedWifiDialog.btnChangeRouter.setOnClickListener(new AddHomeRouter$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$onActivityCreated$0$com-jch-racWiFi-iduOnBoarding-onBoadingInApMode-views-AddHomeRouter */
    public /* synthetic */ void mo31547x2d2e0306(View view) {
        String currentSsid = WifiUtils.getInstance().getCurrentSsid(requireActivity());
        if (currentSsid == null || currentSsid.isEmpty()) {
            singleSelectionPopup(getString(R.string.onboard_alert_pleaseSelectHomeRouter));
            return;
        }
        String replaceAll = currentSsid.replaceAll("^\"|\"$", "");
        String obj = this.connectedWifiDialog.etPasswordField.getText().toString();
        if (obj.isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.onboard_alert_plsEnterPwd), 0).show();
            return;
        }
        WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.setSsid(replaceAll);
        WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.setPassword(obj);
        this.connectedWifiDialog.dismiss();
    }

    /* renamed from: lambda$onActivityCreated$1$com-jch-racWiFi-iduOnBoarding-onBoadingInApMode-views-AddHomeRouter */
    public /* synthetic */ void mo31548x1e7f9287(View view) {
        startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 516);
        this.connectedWifiDialog.dismiss();
    }

    private void singleSelectionPopup(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), AddHomeRouter$$ExternalSyntheticLambda3.INSTANCE);
        singleChoiceDialog.show();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(getLayoutType(), viewGroup, false);
        this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(Values.NAVIGATED_FROM, this.destinationDetails);
        ButterKnife.bind((Object) this, this.rootView);
        updateStep();
        return this.rootView;
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
            updateProgress(this.mLinearProgressIndicator, 7, 5);
            this.mTextViewSteps.setText(R.string.common_lbl_step5);
        }
    }

    private void handleQRSuccess(String str) {
        str.hashCode();
        if (str.equals(StatusCode.BUILTIN_WIRELESS) || str.equals("1")) {
            updateProgress(this.mLinearProgressIndicator, 6, 4);
            this.mTextViewSteps.setText(R.string.common_lbl_step4);
        }
    }

    private int getLayoutType() {
        int i;
        RacTypeEnum racTypeEnum = QRDetailsModel.CURRENT_RAC_DETAILS.getRacTypeEnum();
        if (racTypeEnum == null || (i = C22411.$SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum[racTypeEnum.ordinal()]) == 1 || i != 2) {
            return R.layout.enable_ap_built_in_step_3_of_5_frame;
        }
        return R.layout.enable_ap_built_in_step_3_of_5_vd_2_new;
    }

    @OnClick({2131362078})
    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    @OnClick({2131362469})
    public void goNext() {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_addHomeRouter_to_addRac, getArguments());
    }

    public void onConnectedWifiNetworkFound(String str) {
        String replaceAll = str.replaceAll("^\"|\"$", "");
        this.connectedWifiDialog.tvSSID.setText(getString(R.string.onboard_lbl_ssidColonJch) + " " + replaceAll);
        this.connectedWifiDialog.show();
    }

    public void onNoConnectedWifiNetworkFound() {
        this.connectedWifiDialog.tvSSID.setText(getString(R.string.onboard_alert_noNwAvailable));
        this.connectedWifiDialog.show();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        new Handler().postDelayed(new AddHomeRouter$$ExternalSyntheticLambda4(this), 500);
    }

    /* renamed from: lambda$onActivityResult$3$com-jch-racWiFi-iduOnBoarding-onBoadingInApMode-views-AddHomeRouter */
    public /* synthetic */ void mo31549x704a2ef4() {
        String currentSsid = WifiUtils.getInstance().getCurrentSsid(this.mActivity);
        this.ssid = currentSsid;
        if (currentSsid != null && !currentSsid.isEmpty()) {
            this.ssid = getString(R.string.onboard_lbl_ssidColonJch) + " " + this.ssid;
            this.connectedWifiDialog.tvSSID.setText(this.ssid);
        }
        this.connectedWifiDialog.show();
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views.AddHomeRouter$1 */
    static /* synthetic */ class C22411 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        static {
            /*
                com.jch.racWiFi.Utils.SwipeScreenType[] r0 = com.jch.racWiFi.Utils.SwipeScreenType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType = r0
                r1 = 1
                com.jch.racWiFi.Utils.SwipeScreenType r2 = com.jch.racWiFi.Utils.SwipeScreenType.LEFT_SWIPE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.Utils.SwipeScreenType r3 = com.jch.racWiFi.Utils.SwipeScreenType.RIGHT_SWIPE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum[] r2 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum = r2
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum r3 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.BUILTIN_WIRELESS     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum r2 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.EXTERNAL     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views.AddHomeRouter.C22411.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$4$com-jch-racWiFi-iduOnBoarding-onBoadingInApMode-views-AddHomeRouter */
    public /* synthetic */ void mo31546x5f429a70(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        int i = C22411.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            goNext();
        } else if (i == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }
}
