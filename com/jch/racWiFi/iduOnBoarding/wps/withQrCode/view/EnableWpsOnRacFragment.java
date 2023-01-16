package com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.CustomDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch_hitachi.aircloudglobal.R;

public class EnableWpsOnRacFragment extends GenericFragment {
    public static final String TAG = "EnableWpsOnRacFragment";
    /* access modifiers changed from: private */
    public CustomDialog dialog;
    /* access modifiers changed from: private */
    public Activity mActivity;
    @BindView(2131363340)
    LinearProgressIndicator mLinearProgressIndicator;
    @BindView(2131363530)
    View mRootView;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new EnableWpsOnRacFragment$$ExternalSyntheticLambda1(this);
    @BindView(2131364575)
    TextView mTextViewSteps;
    private Unbinder mUnbinder;
    private View view;

    static /* synthetic */ boolean lambda$singleSelectionPopup$1(Dialog dialog2, View view2) {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        CustomDialog customDialog = new CustomDialog(this.mActivity);
        this.dialog = customDialog;
        customDialog.requestWindowFeature(1);
        this.view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm_home_network_new, (ViewGroup) null, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r5 = (com.jch.racWiFi.userOnboarding.model.RacTypeEnum) r5.getSerializable(com.jch.racWiFi.userOnboarding.model.RacTypeEnum.RAC_TYPE_KEY);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onCreateView(android.view.LayoutInflater r3, android.view.ViewGroup r4, android.os.Bundle r5) {
        /*
            r2 = this;
            android.os.Bundle r5 = r2.getArguments()
            r0 = 0
            if (r5 == 0) goto L_0x001b
            java.lang.String r1 = "RAC_TYPE"
            java.io.Serializable r5 = r5.getSerializable(r1)
            com.jch.racWiFi.userOnboarding.model.RacTypeEnum r5 = (com.jch.racWiFi.userOnboarding.model.RacTypeEnum) r5
            if (r5 == 0) goto L_0x001b
            com.jch.racWiFi.userOnboarding.model.RacTypeEnum r1 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.BUILTIN_WIRELESS
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x001b
            r5 = 1
            goto L_0x001c
        L_0x001b:
            r5 = 0
        L_0x001c:
            if (r5 == 0) goto L_0x0026
            r5 = 2131558519(0x7f0d0077, float:1.8742356E38)
            android.view.View r3 = r3.inflate(r5, r4, r0)
            goto L_0x002d
        L_0x0026:
            r5 = 2131558518(0x7f0d0076, float:1.8742354E38)
            android.view.View r3 = r3.inflate(r5, r4, r0)
        L_0x002d:
            butterknife.Unbinder r4 = butterknife.ButterKnife.bind((java.lang.Object) r2, (android.view.View) r3)
            r2.mUnbinder = r4
            r2.updateStep()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view.EnableWpsOnRacFragment.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
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
            updateProgress(this.mLinearProgressIndicator, 6, 5);
            this.mTextViewSteps.setText(R.string.common_lbl_step5);
        } else if (string2.equals("1") && (string = bundle.getString(Constants.Keys.METHOD)) != null) {
            LinearProgressIndicator linearProgressIndicator = this.mLinearProgressIndicator;
            if (!string.equals(StatusCode.Method.WPS)) {
                i = 7;
            }
            updateProgress(linearProgressIndicator, i, 5);
            this.mTextViewSteps.setText(R.string.common_lbl_step5);
        }
    }

    private void handleQRSuccess(Bundle bundle) {
        String string;
        String string2 = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string2.hashCode();
        int i = 5;
        if (string2.equals(StatusCode.BUILTIN_WIRELESS)) {
            updateProgress(this.mLinearProgressIndicator, 5, 4);
            this.mTextViewSteps.setText(R.string.common_lbl_step4);
        } else if (string2.equals("1") && (string = bundle.getString(Constants.Keys.METHOD)) != null) {
            LinearProgressIndicator linearProgressIndicator = this.mLinearProgressIndicator;
            if (!string.equals(StatusCode.Method.WPS)) {
                i = 6;
            }
            updateProgress(linearProgressIndicator, i, 4);
            this.mTextViewSteps.setText(R.string.common_lbl_step4);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        showConnectedNetworkDialog();
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    public void onDestroyView() {
        super.onDestroyView();
        Unbinder unbinder = this.mUnbinder;
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /* access modifiers changed from: private */
    public void showConnectedNetworkDialog() {
        String str;
        this.dialog.setView(this.view);
        this.dialog.setParent(getView());
        this.dialog.setCanceledOnTouchOutside(false);
        com.jch.racWiFi.customViews.customWidgets.TextView textView = (com.jch.racWiFi.customViews.customWidgets.TextView) this.view.findViewById(R.id.tv_home_router_ssid);
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            str = "Demo_Home_Router";
        } else {
            str = WifiUtils.getInstance().getCurrentSsid(requireActivity());
        }
        if (str == null || str.isEmpty()) {
            textView.setText(getString(R.string.onboard_alert_noNwAvailable));
        } else {
            String replaceAll = str.replaceAll("^\"|\"$", "");
            textView.setText(getString(R.string.onboard_lbl_ssidColonJch) + " " + replaceAll);
        }
        ((Button) this.view.findViewById(R.id.bt_confirm)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str;
                if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
                    str = "Demo_Home_Router";
                } else {
                    str = WifiUtils.getInstance().getCurrentSsid(EnableWpsOnRacFragment.this.requireActivity());
                }
                if (str == null || str.isEmpty()) {
                    EnableWpsOnRacFragment.this.singleSelectionPopup(EnableWpsOnRacFragment.this.getString(R.string.onboard_alert_pleaseSelectHomeRouter));
                    return;
                }
                WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.setSsid(str);
                EnableWpsOnRacFragment.this.dialog.dismiss();
            }
        });
        ((Button) this.view.findViewById(R.id.bt_change_router)).setOnClickListener(new EnableWpsOnRacFragment$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$showConnectedNetworkDialog$0$com-jch-racWiFi-iduOnBoarding-wps-withQrCode-view-EnableWpsOnRacFragment */
    public /* synthetic */ void mo31575x84a76327(View view2) {
        startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 516);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 516) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    String currentSsid = WifiUtils.getInstance().getCurrentSsid(EnableWpsOnRacFragment.this.mActivity);
                    if (currentSsid == null || currentSsid.isEmpty()) {
                        EnableWpsOnRacFragment.this.singleSelectionPopup(EnableWpsOnRacFragment.this.getString(R.string.onboard_alert_pleaseSelectHomeRouter));
                        return;
                    }
                    EnableWpsOnRacFragment.this.showConnectedNetworkDialog();
                }
            }, 500);
        }
    }

    /* access modifiers changed from: private */
    public void singleSelectionPopup(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), EnableWpsOnRacFragment$$ExternalSyntheticLambda2.INSTANCE);
        singleChoiceDialog.show();
    }

    @OnClick({2131362078})
    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362469})
    public void goNext() {
        moveToNextScreen();
    }

    private void moveToNextScreen() {
        String ssid = WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getSsid();
        if (ssid == null || ssid.isEmpty()) {
            showConnectedNetworkDialog();
        } else {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_enableWpsOnRacFragment_to_enableWpsOnHomeRouterFragment, getArguments());
        }
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view.EnableWpsOnRacFragment$3 */
    static /* synthetic */ class C22613 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view.EnableWpsOnRacFragment.C22613.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$2$com-jch-racWiFi-iduOnBoarding-wps-withQrCode-view-EnableWpsOnRacFragment */
    public /* synthetic */ void mo31574xc101ff89(SwipeScreenType swipeScreenType) {
        String str = TAG;
        Logger.m45d(str, "type = " + swipeScreenType.name());
        int i = C22613.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            moveToNextScreen();
        } else if (i == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }
}
