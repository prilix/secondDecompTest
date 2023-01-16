package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;
import com.jch.racWiFi.userOnboarding.model.RacTypeEnum;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch_hitachi.aircloudglobal.R;

public class AddRac extends GenericFragment {
    @BindView(2131362131)
    Button changeRouterButton;
    @BindView(2131362260)
    TextView connectToAcTextview;
    private boolean isQrCodeSuccess;
    /* access modifiers changed from: private */
    public Activity mActivity;
    @BindView(2131362275)
    ConstraintLayout mConstraintLayoutError;
    @BindView(2131363340)
    LinearProgressIndicator mLinearProgressIndicator;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new AddRac$$ExternalSyntheticLambda0(this);
    @BindView(2131362314)
    AppCompatTextView mTextViewBannerError;
    @BindView(2131364575)
    TextView mTextViewStep;
    private Unbinder mUnbinder;
    @BindView(2131363536)
    TextInputLayout passwordLayout;
    private boolean racNetworkSelected = false;
    @BindView(2131362442)
    EditText racPasswordEditText;
    @BindView(2131364820)
    TextView racSsidText;
    @BindView(2131363667)
    View swipableLayout;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        this.isQrCodeSuccess = ((Boolean) this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS).getDefaultValue()).booleanValue();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutType(), viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        updateStep();
        TextView textView = this.racSsidText;
        if (textView != null) {
            textView.setTextColor(getResources().getColor(R.color.rac_name_oofline_color));
        }
        String currentSsid = WifiUtils.getInstance().getCurrentSsid(this.mActivity);
        if (currentSsid != null && !currentSsid.isEmpty() && currentSsid.replaceAll("^\"|\"$", "").equals(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid().replaceAll("^\"|\"$", ""))) {
            this.racNetworkSelected = true;
            this.connectToAcTextview.setText(getText(R.string.onboard_lbl_connectedToAcDesc));
            this.changeRouterButton.setVisibility(8);
            if (!this.isQrCodeSuccess) {
                updateStep();
                this.passwordLayout.setVisibility(0);
                this.changeRouterButton.setVisibility(8);
                this.connectToAcTextview.setText(getText(R.string.onboard_lbl_connectedToAcDesc));
            } else {
                this.connectToAcTextview.setText(getText(R.string.onboard_lbl_connectedToAcDescForQrCodeScan));
            }
        }
        return inflate;
    }

    /* access modifiers changed from: private */
    public void updateStep() {
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
        String string = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string.hashCode();
        char c = 65535;
        switch (string.hashCode()) {
            case 48:
                if (string.equals(StatusCode.BUILTIN_WIRELESS)) {
                    c = 0;
                    break;
                }
                break;
            case 49:
                if (string.equals("1")) {
                    c = 1;
                    break;
                }
                break;
            case 50:
                if (string.equals("2")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                if (this.mTextViewStep.getText().equals(getString(R.string.common_lbl_step6))) {
                    updateProgress(this.mLinearProgressIndicator, 7, 7);
                    this.mTextViewStep.setText(getString(R.string.common_lbl_step7));
                    return;
                }
                updateProgress(this.mLinearProgressIndicator, 7, 6);
                this.mTextViewStep.setText(getString(R.string.common_lbl_step6));
                return;
            case 2:
                if (this.mTextViewStep.getText().equals(getString(R.string.common_lbl_step5))) {
                    updateProgress(this.mLinearProgressIndicator, 6, 6);
                    this.mTextViewStep.setText(getString(R.string.common_lbl_step6));
                    return;
                }
                updateProgress(this.mLinearProgressIndicator, 6, 5);
                this.mTextViewStep.setText(getString(R.string.common_lbl_step5));
                return;
            default:
                return;
        }
    }

    private void handleQRSuccess(Bundle bundle) {
        String string = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string.hashCode();
        char c = 65535;
        switch (string.hashCode()) {
            case 48:
                if (string.equals(StatusCode.BUILTIN_WIRELESS)) {
                    c = 0;
                    break;
                }
                break;
            case 49:
                if (string.equals("1")) {
                    c = 1;
                    break;
                }
                break;
            case 50:
                if (string.equals("2")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                if (this.mTextViewStep.getText().equals(getString(R.string.common_lbl_step5))) {
                    updateProgress(this.mLinearProgressIndicator, 6, 6);
                    this.mTextViewStep.setText(getString(R.string.common_lbl_step6));
                    return;
                }
                updateProgress(this.mLinearProgressIndicator, 6, 5);
                this.mTextViewStep.setText(getString(R.string.common_lbl_step5));
                return;
            case 2:
                updateProgress(this.mLinearProgressIndicator, 4, 4);
                this.mTextViewStep.setText(getString(R.string.common_lbl_step4));
                return;
            default:
                return;
        }
    }

    private int getLayoutType() {
        RacTypeEnum racTypeEnum = QRDetailsModel.CURRENT_RAC_DETAILS.getRacTypeEnum();
        if (racTypeEnum == null) {
            return R.layout.fragment_ap_add_rac_builtin;
        }
        int i = C22474.$SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum[racTypeEnum.ordinal()];
        if (i == 1) {
            return R.layout.fragment_ap_add_rac_india;
        }
        if (i == 2 || i != 3) {
            return R.layout.fragment_ap_add_rac_builtin;
        }
        return R.layout.fragment_ap_add_rac_external;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        String ssid = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid();
        if (ssid != null && !ssid.isEmpty() && this.racSsidText != null) {
            this.racSsidText.setText(getString(R.string.onboard_lbl_ssidColonJch) + " " + ssid);
        }
    }

    @OnClick({2131362078})
    public void goBack() {
        onBackClicked();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362469})
    public void goNext() {
        moveForward();
    }

    private void moveForward() {
        this.mConstraintLayoutError.setVisibility(8);
        if (com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setSsid("JCH-88600053");
            WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setPassword("8632237Gdb");
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_addRac_to_configuringDeviceUdpExchange);
        } else if (this.racNetworkSelected) {
            if (!this.isQrCodeSuccess) {
                String obj = this.racPasswordEditText.getText().toString();
                if (isNullOrEmpty(obj) && this.changeRouterButton.getVisibility() == 8) {
                    showErrorPopUp(getString(R.string.onboard_alert_plsEnterPwd));
                    return;
                } else if (!isNullOrEmpty(obj) || this.changeRouterButton.getVisibility() != 0) {
                    WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setPassword(obj);
                } else if (isSSIDValidated()) {
                    updateStep();
                    this.changeRouterButton.setVisibility(8);
                    this.passwordLayout.setVisibility(0);
                    this.connectToAcTextview.setText(getText(R.string.onboard_lbl_connectedToAcDesc));
                    return;
                } else {
                    this.mTextViewBannerError.setText(HtmlCompat.fromHtml(getString(R.string.err_msg_your_smartphone_is_not, WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid()).trim(), 0));
                    this.mConstraintLayoutError.setVisibility(0);
                    return;
                }
            }
            String ssid = WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getSsid();
            String password = WiFiCredentialModel.CURRENT_HOME_ROUTER_CREDENTIALS.getPassword();
            String ssid2 = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid();
            String password2 = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getPassword();
            if (isNullOrEmpty(ssid) || isNullOrEmpty(password) || isNullOrEmpty(ssid2) || isNullOrEmpty(password2)) {
                validateMandatoryParam(ssid, password, ssid2);
            } else {
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_addRac_to_configuringDeviceUdpExchange, getArguments());
            }
        } else {
            this.mTextViewBannerError.setText(HtmlCompat.fromHtml(getString(R.string.err_msg_your_smartphone_is_not, WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid()).trim(), 0));
            this.mConstraintLayoutError.setVisibility(0);
        }
    }

    private boolean isSSIDValidated() {
        String currentSsid = WifiUtils.getInstance().getCurrentSsid(this.mActivity);
        if (currentSsid == null || currentSsid.isEmpty()) {
            return false;
        }
        String replaceAll = currentSsid.replaceAll("^\"|\"$", "");
        String ssid = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid();
        if (ssid != null && !ssid.isEmpty()) {
            ssid = ssid.replaceAll("^\"|\"$", "");
        }
        return replaceAll.equals(ssid);
    }

    private void validateMandatoryParam(String str, String str2, String str3) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_addRac_to_configuringFailedUdpExchange);
    }

    private void showValidationAlert(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new AddRac$$ExternalSyntheticLambda1(this));
        singleChoiceDialog.show();
    }

    /* renamed from: lambda$showValidationAlert$0$com-jch-racWiFi-iduOnBoarding-onBoadingInApMode-views-AddRac */
    public /* synthetic */ boolean mo31557xf3f086a(Dialog dialog, View view) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_addRac_to_configuringFailedUdpExchange);
        return true;
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362131})
    public void changeRouter() {
        com.jch.racWiFi.Constants.IS_ON_BOARDING = true;
        startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 516);
    }

    public void onDestroyView() {
        super.onDestroyView();
        Unbinder unbinder = this.mUnbinder;
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mConstraintLayoutError.setVisibility(8);
        if (i == 516) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    String currentSsid = WifiUtils.getInstance().getCurrentSsid(AddRac.this.mActivity);
                    if (currentSsid != null && !currentSsid.isEmpty()) {
                        boolean z = true;
                        Toast.makeText(AddRac.this.mActivity, currentSsid, 1).show();
                        if (!currentSsid.replaceAll("^\"|\"$", "").equals(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid().replaceAll("^\"|\"$", ""))) {
                            AddRac.this.mTextViewBannerError.setText(HtmlCompat.fromHtml(AddRac.this.getString(R.string.err_msg_your_smartphone_is_not, WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid()).trim(), 0));
                            AddRac.this.mConstraintLayoutError.setVisibility(0);
                            return;
                        }
                        RacTypeEnum racTypeEnum = QRDetailsModel.CURRENT_RAC_DETAILS.getRacTypeEnum();
                        if (racTypeEnum != null) {
                            AddRac.this.updateStep();
                            AddRac addRac = AddRac.this;
                            if (RacTypeEnum.INDIAN_MODEL != racTypeEnum) {
                                z = false;
                            }
                            addRac.handleCommon(z);
                            return;
                        }
                        AddRac.this.singleSelectionPopup(AddRac.this.getActivity().getString(R.string.onboard_alert_notSelectedRacNw));
                    }
                }
            }, 500);
        }
    }

    /* access modifiers changed from: private */
    public void handleCommon(boolean z) {
        this.racNetworkSelected = true;
        if (!z) {
            this.changeRouterButton.setVisibility(8);
        }
        if (!this.isQrCodeSuccess) {
            this.changeRouterButton.setVisibility(8);
            this.passwordLayout.setVisibility(0);
            this.connectToAcTextview.setText(getText(R.string.onboard_lbl_connectedToAcDesc));
        } else if (!z) {
            this.connectToAcTextview.setText(getText(R.string.onboard_lbl_connectedToAcDescForQrCodeScan));
        } else {
            moveForward();
        }
    }

    /* access modifiers changed from: private */
    public void singleSelectionPopup(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                return true;
            }
        });
        singleChoiceDialog.show();
    }

    public void onStart() {
        super.onStart();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                AddRac.this.onBackClicked();
            }
        });
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    /* access modifiers changed from: private */
    public void onBackClicked() {
        if (this.changeRouterButton.getVisibility() == 8) {
            updateStep();
            this.changeRouterButton.setVisibility(0);
            this.passwordLayout.setVisibility(8);
            this.connectToAcTextview.setText(getText(R.string.onboard_lbl_connectToAdapterDesc));
            return;
        }
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views.AddRac$4 */
    static /* synthetic */ class C22474 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
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
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum r3 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.INDIAN_MODEL     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum r2 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.BUILTIN_WIRELESS     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum r1 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.EXTERNAL     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views.AddRac.C22474.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$1$com-jch-racWiFi-iduOnBoarding-onBoadingInApMode-views-AddRac */
    public /* synthetic */ void mo31556xf3d9a8cf(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        int i = C22474.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            moveForward();
        } else if (i == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }
}
