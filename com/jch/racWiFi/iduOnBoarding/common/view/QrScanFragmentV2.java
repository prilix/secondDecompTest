package com.jch.racWiFi.iduOnBoarding.common.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.AddDeviceStep1Of5VdBinding;
import com.jch.racWiFi.iduOnBoarding.ViewModel.OnBoardingViewModel;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;
import com.jch.racWiFi.userOnboarding.model.RacTypeEnum;
import com.jch.racWiFi.userOnboarding.presenter.QRScanPresenter;
import com.jch.racWiFi.userOnboarding.presenter.presenterImpl.QRScanPresenterImpl;
import com.jch.racWiFi.userOnboarding.view.IQRView;
import com.jch_hitachi.aircloudglobal.R;
import p015me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrScanFragmentV2 extends GenericFragment implements IQRView {
    private final String TAG = getClass().getSimpleName();
    private String[] cameraPermission;
    @BindView(2131364469)
    TextView failedToScanWidget;
    private Activity mActivity;
    private final Handler mCameraHandler = new Handler();
    private long mEntryTime;
    @BindView(2131363530)
    ConstraintLayout mParent;
    private RetryQrScanViewHolder mRetryQrScanViewHolder;
    private final Handler mRetryTimeoutHandler = new Handler();
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new QrScanFragmentV2$$ExternalSyntheticLambda8(this);
    @BindView(2131364575)
    TextView mTextViewSteps;
    private OnBoardingViewModel onBoardingViewModel;
    private final NavArgument.Builder qrOrManualArgBuilder = new NavArgument.Builder();
    private final QRScanPresenter qrScanPresenter = new QRScanPresenterImpl(this);
    @BindView(2131363658)
    ZXingScannerView scannerView;
    private SingleChoiceDialog singleChoiceDialog;
    @BindView(2131362181)
    Button unableToScanWidget;
    @BindView(2131362185)
    Button whereToLocateWidget;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        this.cameraPermission = new String[]{"android.permission.CAMERA"};
        this.singleChoiceDialog = new SingleChoiceDialog(this.mActivity);
        OnBoardingViewModel onBoardingViewModel2 = getCoreActivity().getGlobalViewModelRepository().getOnBoardingViewModel();
        this.onBoardingViewModel = onBoardingViewModel2;
        onBoardingViewModel2.setAddedAcRouterSsid((String) null);
        this.onBoardingViewModel.setIduOnline(false);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.add_device_step_1_of_5_frame, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.mTextViewSteps.setText(R.string.common_lbl_step1);
        Constants.IS_TO_PREVIEW_CAMERA = true;
        getCoreActivity().getWifiManager().setWifiEnabled(true);
        RetryQrScanViewHolder retryQrScanViewHolder = new RetryQrScanViewHolder((ConstraintLayout) inflate.findViewById(R.id.include));
        this.mRetryQrScanViewHolder = retryQrScanViewHolder;
        retryQrScanViewHolder.getBinding().retryImageView.setOnClickListener(new QrScanFragmentV2$$ExternalSyntheticLambda4(this));
        this.mRetryQrScanViewHolder.getBinding().retryTextview.setOnClickListener(new QrScanFragmentV2$$ExternalSyntheticLambda5(this));
        return inflate;
    }

    /* renamed from: lambda$onCreateView$0$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ void mo31453xb525051(View view) {
        retryQrScanning();
    }

    /* renamed from: lambda$onCreateView$1$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ void mo31454x11561bb0(View view) {
        retryQrScanning();
    }

    private void retryQrScanning() {
        this.scannerView.startCamera();
        this.mRetryQrScanViewHolder.scanningState();
        startRetryTimeoutHandler();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.scannerView.setResultHandler(this.qrScanPresenter);
        this.qrScanPresenter.setRunStatus(this.mFragmentToActivityCallback.getNavController());
    }

    private void startCam() {
        if (!Constants.IS_TO_PREVIEW_CAMERA) {
            return;
        }
        if (checkPermissions(this.cameraPermission)) {
            startQrCodeScanner();
            return;
        }
        Constants.IS_TO_PREVIEW_CAMERA = false;
        requestPermissions(this.cameraPermission, 177);
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onResume() {
        startCam();
        super.onResume();
    }

    public void onPause() {
        super.onPause();
        this.scannerView.stopCamera();
        stopRetryTimeoutHandler();
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    public void onDestroyView() {
        super.onDestroyView();
        dismissPleaseWaitDialog();
        this.mCameraHandler.removeCallbacksAndMessages((Object) null);
    }

    public void onScanSuccessful(QRDetailsModel qRDetailsModel) {
        RacTypeEnum racTypeEnum;
        NavArgument.Builder builder = new NavArgument.Builder();
        builder.setDefaultValue(qRDetailsModel);
        String type = qRDetailsModel.getType();
        if (type.trim().equals("2") || type.trim().equals("1")) {
            WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setAdapterType(type);
            if (type.equals("2")) {
                racTypeEnum = RacTypeEnum.INDIAN_MODEL;
            } else {
                racTypeEnum = RacTypeEnum.EXTERNAL;
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(RacTypeEnum.RAC_TYPE_KEY, racTypeEnum);
            QRDetailsModel.CURRENT_RAC_DETAILS.setSSID(qRDetailsModel.getSSID());
            QRDetailsModel.CURRENT_RAC_DETAILS.setPassword(qRDetailsModel.getPassword());
            QRDetailsModel.CURRENT_RAC_DETAILS.setType(qRDetailsModel.getType());
            QRDetailsModel.CURRENT_RAC_DETAILS.setRacTypeEnum(racTypeEnum);
            WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setSsid(qRDetailsModel.getSSID());
            WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setPassword(qRDetailsModel.getPassword());
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(Values.QR_DETAILS_KEY, builder.build());
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(Values.QR_SCAN_SUCCESS, this.qrOrManualArgBuilder.setDefaultValue(true).build());
            bundle.putLong(Constants.Keys.ENTRY_TIME, this.mEntryTime);
            bundle.putString(Constants.Keys.ADAPTER_TYPE, type);
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_qrScanFragment_to_qrCodeScanned, bundle);
            logEvents(Events.QR_CODE_SCAN_SUCCESSFUL.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mEntryTime));
            return;
        }
        showInvalidQrCodeAlert();
    }

    public void runOnFreshMode() {
        Logger.m45d(this.TAG, "runOnFreshMode");
        this.failedToScanWidget.setText(getString(R.string.onboard_lbl_scanQrCode));
        this.failedToScanWidget.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
    }

    public void runOnPreviouslyFailedMode() {
        Logger.m45d(this.TAG, "runOnPreviouslyFailedMode");
        this.failedToScanWidget.setText(getString(R.string.onboard_btn_unableToScanQrCodeWithOutQuestionMark));
        this.failedToScanWidget.setTextColor(getResources().getColor(R.color.colorRed));
    }

    public void onScanFailed() {
        Logger.m45d(this.TAG, "onScanFailed");
    }

    public void onWrongQRScanned() {
        Logger.m45d(this.TAG, "onWrongQRScanned");
        showInvalidQrCodeAlert();
    }

    private void showInvalidQrCodeAlert() {
        this.singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        this.singleChoiceDialog.setMessageString(getString(R.string.onboard_alert_invalidQrCode));
        this.singleChoiceDialog.setCancelable(false);
        this.singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new QrScanFragmentV2$$ExternalSyntheticLambda9(this));
        this.singleChoiceDialog.show();
    }

    /* renamed from: lambda$showInvalidQrCodeAlert$2$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ boolean mo31455x116ebf1b(Dialog dialog, View view) {
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            return true;
        }
        this.scannerView.setResultHandler(this.qrScanPresenter);
        startCam();
        return true;
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362181})
    public void onFailedToScan() {
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.Keys.ENTRY_TIME, this.mEntryTime);
        this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(Values.QR_SCAN_SUCCESS, this.qrOrManualArgBuilder.setDefaultValue(false).build());
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_qrScanFragment_to_unableToScanQrSsidFragment, bundle);
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362185})
    public void onWhereToLocateDialog() {
        locateQRCode();
    }

    private void locateQRCode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setCancelable(true).setView(R.layout.dialog_where_to_find_qr_code_india);
        AlertDialog create = builder.create();
        create.setOnShowListener(new QrScanFragmentV2$$ExternalSyntheticLambda2(this));
        create.show();
        create.setOnDismissListener(new QrScanFragmentV2$$ExternalSyntheticLambda0(this));
        WindowManager.LayoutParams attributes = create.getWindow().getAttributes();
        attributes.dimAmount = 0.2f;
        create.getWindow().setAttributes(attributes);
        create.getWindow().addFlags(2);
        View decorView = create.getWindow().getDecorView();
        ConstraintLayout constraintLayout = (ConstraintLayout) decorView.findViewById(R.id.layout_built_in);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) decorView.findViewById(R.id.layout_adapter);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) decorView.findViewById(R.id.layout_adapter_content);
        ConstraintLayout constraintLayout4 = constraintLayout2;
        ImageView imageView = (ImageView) decorView.findViewById(R.id.image_view_arrow_drop_down_red_adapter);
        TextView textView = (TextView) decorView.findViewById(R.id.text_view_adapter);
        ImageView imageView2 = (ImageView) decorView.findViewById(R.id.image_view_adapter);
        ConstraintLayout constraintLayout5 = constraintLayout;
        ConstraintLayout constraintLayout6 = (ConstraintLayout) decorView.findViewById(R.id.layout_built_in_content);
        ImageView imageView3 = (ImageView) decorView.findViewById(R.id.image_view_arrow_drop_down_red_built_in);
        QrScanFragmentV2$$ExternalSyntheticLambda6 qrScanFragmentV2$$ExternalSyntheticLambda6 = r0;
        TextView textView2 = (TextView) decorView.findViewById(R.id.text_view_built_in);
        ImageButton imageButton = (ImageButton) decorView.findViewById(R.id.image_button_clear);
        ImageView imageView4 = (ImageView) decorView.findViewById(R.id.image_view_built_in);
        QrScanFragmentV2$$ExternalSyntheticLambda6 qrScanFragmentV2$$ExternalSyntheticLambda62 = new QrScanFragmentV2$$ExternalSyntheticLambda6(this, constraintLayout3, constraintLayout4, imageView, textView, imageView2, constraintLayout5, constraintLayout6, imageView3, textView2, imageView4);
        constraintLayout2.setOnClickListener(qrScanFragmentV2$$ExternalSyntheticLambda6);
        constraintLayout.setOnClickListener(new QrScanFragmentV2$$ExternalSyntheticLambda7(this, constraintLayout3, constraintLayout4, imageView, textView, imageView2, constraintLayout5, constraintLayout6, imageView3, textView2, imageView4));
        imageButton.setOnClickListener(new QrScanFragmentV2$$ExternalSyntheticLambda3(create));
    }

    /* renamed from: lambda$locateQRCode$3$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ void mo31448x62b5d5ee(DialogInterface dialogInterface) {
        this.mParent.setBackgroundResource(R.drawable.white_blur);
    }

    /* renamed from: lambda$locateQRCode$4$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ void mo31449x68b9a14d(DialogInterface dialogInterface) {
        this.mParent.setBackgroundResource(R.drawable.transparent);
    }

    /* renamed from: lambda$locateQRCode$5$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ void mo31450x6ebd6cac(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView, ImageView imageView2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ImageView imageView3, TextView textView2, ImageView imageView4, View view) {
        constraintLayout.setVisibility(0);
        constraintLayout2.setBackgroundColor(getResources().getColor(R.color.color_lightest_grey));
        imageView.setVisibility(0);
        textView.setTextColor(getResources().getColor(R.color.colorRed));
        imageView2.setImageResource(R.drawable.ic_locate_qr_adapter_red);
        constraintLayout3.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        constraintLayout4.setVisibility(4);
        imageView3.setVisibility(4);
        textView2.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
        imageView4.setImageResource(R.drawable.ic_built_in_grey);
    }

    /* renamed from: lambda$locateQRCode$6$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ void mo31451x74c1380b(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView, ImageView imageView2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ImageView imageView3, TextView textView2, ImageView imageView4, View view) {
        constraintLayout.setVisibility(4);
        constraintLayout2.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        imageView.setVisibility(4);
        textView.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
        imageView2.setImageResource(R.drawable.ic_locate_qr_adapter_grey);
        constraintLayout3.setBackgroundColor(getResources().getColor(R.color.color_lightest_grey));
        constraintLayout4.setVisibility(0);
        imageView3.setVisibility(0);
        textView2.setTextColor(getResources().getColor(R.color.colorRed));
        imageView4.setImageResource(R.drawable.ic_built_in_red);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({2131362078})
    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 177 && iArr.length > 0) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] != 0) {
                    if (!shouldShowRequestPermissionRationale(strArr[i2])) {
                        showPermissionSettingDialog(strArr[i2], this, (NavController) null);
                    } else {
                        showPermissionDeniedDialog(this.mParent, strArr[i2], (NavController) null);
                    }
                }
            }
            if (checkPermissions(this.cameraPermission)) {
                startQrCodeScanner();
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 132 && checkPermissions(this.cameraPermission)) {
            startQrCodeScanner();
        }
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.common.view.QrScanFragmentV2$1 */
    static /* synthetic */ class C22291 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.common.view.QrScanFragmentV2.C22291.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$8$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ void mo31452x2b337c83(SwipeScreenType swipeScreenType) {
        String str = this.TAG;
        Logger.m45d(str, "type = " + swipeScreenType.name());
        if (C22291.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()] == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    private void startQrCodeScanner() {
        this.mEntryTime = System.currentTimeMillis();
        showPleaseWaitDialog();
        this.mCameraHandler.postDelayed(new QrScanFragmentV2$$ExternalSyntheticLambda10(this), 500);
    }

    /* renamed from: lambda$startQrCodeScanner$9$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ void mo31456xae0aeefa() {
        this.scannerView.startCamera();
        startRetryTimeoutHandler();
        dismissPleaseWaitDialog();
    }

    private static class RetryQrScanViewHolder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Context context;
        AddDeviceStep1Of5VdBinding deviceStep1Of5VdBinding;

        static {
            Class<QrScanFragmentV2> cls = QrScanFragmentV2.class;
        }

        public AddDeviceStep1Of5VdBinding getBinding() {
            return this.deviceStep1Of5VdBinding;
        }

        public RetryQrScanViewHolder(View view) {
            AddDeviceStep1Of5VdBinding addDeviceStep1Of5VdBinding = (AddDeviceStep1Of5VdBinding) DataBindingUtil.bind(view);
            this.deviceStep1Of5VdBinding = addDeviceStep1Of5VdBinding;
            this.context = addDeviceStep1Of5VdBinding.getRoot().getContext();
        }

        public void errorUiState() {
            this.deviceStep1Of5VdBinding.retryImageView.setVisibility(0);
            this.deviceStep1Of5VdBinding.retryTextview.setVisibility(0);
            this.deviceStep1Of5VdBinding.scannerID.setVisibility(4);
            this.deviceStep1Of5VdBinding.textViewSacnQrCodeTitle2.setText(R.string.onboard_btn_unableToScanQrCodeWithOutQuestionMark);
            this.deviceStep1Of5VdBinding.textViewSacnQrCodeTitle2.setTextColor(this.context.getResources().getColor(R.color.colorRed));
            this.deviceStep1Of5VdBinding.view12.setBackgroundColor(this.context.getResources().getColor(R.color.colorRed));
            this.deviceStep1Of5VdBinding.view15.setBackgroundColor(this.context.getResources().getColor(R.color.colorRed));
            this.deviceStep1Of5VdBinding.view16.setBackgroundColor(this.context.getResources().getColor(R.color.colorRed));
            this.deviceStep1Of5VdBinding.view17.setBackgroundColor(this.context.getResources().getColor(R.color.colorRed));
            this.deviceStep1Of5VdBinding.view18.setBackgroundColor(this.context.getResources().getColor(R.color.colorRed));
            this.deviceStep1Of5VdBinding.view19.setBackgroundColor(this.context.getResources().getColor(R.color.colorRed));
            this.deviceStep1Of5VdBinding.view21.setBackgroundColor(this.context.getResources().getColor(R.color.colorRed));
            this.deviceStep1Of5VdBinding.view22.setBackgroundColor(this.context.getResources().getColor(R.color.colorRed));
        }

        public void scanningState() {
            this.deviceStep1Of5VdBinding.retryImageView.setVisibility(4);
            this.deviceStep1Of5VdBinding.retryTextview.setVisibility(4);
            this.deviceStep1Of5VdBinding.scannerID.setVisibility(0);
            this.deviceStep1Of5VdBinding.textViewSacnQrCodeTitle2.setText(R.string.onboard_lbl_scanQrCode);
            this.deviceStep1Of5VdBinding.textViewSacnQrCodeTitle2.setTextColor(this.context.getResources().getColor(R.color.textview_color_vd_dark));
            this.deviceStep1Of5VdBinding.view12.setBackgroundColor(this.context.getResources().getColor(R.color.textview_color_vd_dark));
            this.deviceStep1Of5VdBinding.view15.setBackgroundColor(this.context.getResources().getColor(R.color.textview_color_vd_dark));
            this.deviceStep1Of5VdBinding.view16.setBackgroundColor(this.context.getResources().getColor(R.color.textview_color_vd_dark));
            this.deviceStep1Of5VdBinding.view17.setBackgroundColor(this.context.getResources().getColor(R.color.textview_color_vd_dark));
            this.deviceStep1Of5VdBinding.view18.setBackgroundColor(this.context.getResources().getColor(R.color.textview_color_vd_dark));
            this.deviceStep1Of5VdBinding.view19.setBackgroundColor(this.context.getResources().getColor(R.color.textview_color_vd_dark));
            this.deviceStep1Of5VdBinding.view21.setBackgroundColor(this.context.getResources().getColor(R.color.textview_color_vd_dark));
            this.deviceStep1Of5VdBinding.view22.setBackgroundColor(this.context.getResources().getColor(R.color.textview_color_vd_dark));
        }

        public void unbind() {
            this.deviceStep1Of5VdBinding.unbind();
        }
    }

    private void startRetryTimeoutHandler() {
        this.mRetryTimeoutHandler.removeCallbacksAndMessages((Object) null);
        this.mRetryTimeoutHandler.postDelayed(new QrScanFragmentV2$$ExternalSyntheticLambda1(this), 30000);
    }

    /* renamed from: lambda$startRetryTimeoutHandler$10$com-jch-racWiFi-iduOnBoarding-common-view-QrScanFragmentV2 */
    public /* synthetic */ void mo31457xf519ebc3() {
        this.scannerView.stopCamera();
        this.mRetryQrScanViewHolder.errorUiState();
    }

    private void stopRetryTimeoutHandler() {
        this.mRetryTimeoutHandler.removeCallbacksAndMessages((Object) null);
    }
}
