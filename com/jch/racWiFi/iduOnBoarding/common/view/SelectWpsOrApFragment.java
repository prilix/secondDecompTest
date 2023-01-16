package com.jch.racWiFi.iduOnBoarding.common.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customDialogs.GenericNoteDialog;
import com.jch.racWiFi.databinding.FragmentSelectWpsOrApBinding;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.model.RacTypeEnum;
import com.jch.racWiFi.userOnboarding.view.IAddDeviceView;
import com.jch_hitachi.aircloudglobal.R;

public class SelectWpsOrApFragment extends GenericFragment implements IAddDeviceView {
    private String[] locationPermissionArr;
    private Activity mActivity;
    private FragmentSelectWpsOrApBinding mBinding;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new SelectWpsOrApFragment$$ExternalSyntheticLambda5(this);
    private NavController navController;

    private void selectAdapterTypeDialog() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        this.locationPermissionArr = new String[]{Constants.GrantedPermissions.ACCESS_FINE_LOCATION};
        selectAdapterTypeDialog();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (FragmentSelectWpsOrApBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_select_wps_or_ap, viewGroup, false);
        updateStep();
        showDialog();
        this.navController = this.mFragmentToActivityCallback.getNavController();
        if (!com.jch.racWiFi.Constants.IS_DEMO_MODE) {
            if (checkPermissions(this.locationPermissionArr)) {
                checkForLocationEnabledOrNot();
            } else {
                requestPermissions(this.locationPermissionArr, 177);
            }
        }
        return this.mBinding.getRoot();
    }

    private void showDialog() {
        GenericNoteDialog.Companion.newInstance(getString(R.string.grac_adapter_is_compatible_only)).show(getParentFragmentManager(), GenericNoteDialog.class.getCanonicalName());
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
        String string = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string.hashCode();
        if (string.equals(StatusCode.BUILTIN_WIRELESS) || string.equals("1")) {
            updateProgress(this.mBinding.includeLinearProgressIndicator.linearProgressIndicator, 7, 3);
            this.mBinding.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step3);
        }
    }

    private void handleQRSuccess(Bundle bundle) {
        String string = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string.hashCode();
        if (string.equals(StatusCode.BUILTIN_WIRELESS)) {
            updateProgress(this.mBinding.includeLinearProgressIndicator.linearProgressIndicator, 5, 2);
            this.mBinding.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step2);
        } else if (string.equals("1")) {
            updateProgress(this.mBinding.includeLinearProgressIndicator.linearProgressIndicator, 6, 2);
            this.mBinding.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step2);
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
        if (!getCoreActivity().isLocationEnabled()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
            builder.setMessage((CharSequence) getString(R.string.common_alert_enableLocationService)).setCancelable(false).setPositiveButton((CharSequence) getString(R.string.common_btn_yes), (DialogInterface.OnClickListener) new SelectWpsOrApFragment$$ExternalSyntheticLambda0(this)).setNegativeButton((int) R.string.common_btn_no, (DialogInterface.OnClickListener) new SelectWpsOrApFragment$$ExternalSyntheticLambda1(this));
            AlertDialog create = builder.create();
            create.setCancelable(false);
            create.show();
        }
    }

    /* renamed from: lambda$checkForLocationEnabledOrNot$0$com-jch-racWiFi-iduOnBoarding-common-view-SelectWpsOrApFragment */
    public /* synthetic */ void mo31489xf899627e(DialogInterface dialogInterface, int i) {
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 147);
    }

    /* renamed from: lambda$checkForLocationEnabledOrNot$1$com-jch-racWiFi-iduOnBoarding-common-view-SelectWpsOrApFragment */
    public /* synthetic */ void mo31490x4658da7f(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        Toaster.makeToast(getActivity(), getString(R.string.onboard_alert_locationNotEnabled), 0);
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.layoutConnectUsingWpsMethod.setOnClickListener(new SelectWpsOrApFragment$$ExternalSyntheticLambda2(this));
        this.mBinding.layoutConnectUsingApMethod.setOnClickListener(new SelectWpsOrApFragment$$ExternalSyntheticLambda3(this));
        this.mBinding.backButton.setOnClickListener(new SelectWpsOrApFragment$$ExternalSyntheticLambda4(this));
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-iduOnBoarding-common-view-SelectWpsOrApFragment */
    public /* synthetic */ void mo31492xcd26e8c5(View view) {
        switchToWpsMode();
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-iduOnBoarding-common-view-SelectWpsOrApFragment */
    public /* synthetic */ void mo31493x1ae660c6(View view) {
        switchToNonWpsMode();
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-iduOnBoarding-common-view-SelectWpsOrApFragment */
    public /* synthetic */ void mo31494x68a5d8c7(View view) {
        goBack();
    }

    /* access modifiers changed from: package-private */
    public void switchToWpsMode() {
        this.navController.getGraph().addArgument(NavigationTransitionKeyValues.SMART_OR_SOFT_AP_CHOSEN, new NavArgument.Builder().setDefaultValue(false).build());
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putString(Constants.Keys.METHOD, StatusCode.Method.WPS);
        this.navController.navigate((int) R.id.to_confirmNetworkWpsFragment, arguments);
        logEvents(Events.MODEL_SELECTION_WPS.name(), 0);
    }

    /* access modifiers changed from: package-private */
    public void switchToNonWpsMode() {
        NavArgument navArgument = this.navController.getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        boolean booleanValue = (navArgument == null || getArguments() == null || navArgument.getDefaultValue() == null) ? false : ((Boolean) navArgument.getDefaultValue()).booleanValue();
        Bundle arguments = getArguments();
        if (arguments != null) {
            RacTypeEnum racTypeEnum = (RacTypeEnum) arguments.getSerializable(RacTypeEnum.RAC_TYPE_KEY);
            if (racTypeEnum == null) {
                moveToConfirmWirelessNwApScreen();
            } else if (!racTypeEnum.equals(RacTypeEnum.BUILTIN_WIRELESS) || booleanValue) {
                moveToConfirmWirelessNwApScreen();
            }
        } else {
            moveToConfirmWirelessNwApScreen();
        }
        logEvents(Events.MODEL_SELECTION_AP.name(), 0);
    }

    private void moveToConfirmWirelessNwApScreen() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putString(Constants.Keys.METHOD, StatusCode.Method.f423AP);
        this.navController.getGraph().addArgument(NavigationTransitionKeyValues.CONNECTION_METHOD_CHOSEN, new NavArgument.Builder().setDefaultValue(1010).build());
        this.navController.getGraph().addArgument(NavigationTransitionKeyValues.SMART_OR_SOFT_AP_CHOSEN, new NavArgument.Builder().setDefaultValue(true).build());
        this.navController.navigate((int) R.id.to_confirmNetworkApFragment, arguments);
    }

    public void goBack() {
        this.navController.navigateUp();
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

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 132) {
            if (i == 147) {
                checkForLocationEnabledOrNot();
            }
        } else if (checkPermissions(this.locationPermissionArr)) {
            checkForLocationEnabledOrNot();
        } else {
            Toaster.makeToast(getActivity(), getString(R.string.onboard_alert_locationNotEnabled), 1);
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.common.view.SelectWpsOrApFragment$1 */
    static /* synthetic */ class C22341 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.common.view.SelectWpsOrApFragment.C22341.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$5$com-jch-racWiFi-iduOnBoarding-common-view-SelectWpsOrApFragment */
    public /* synthetic */ void mo31491xb7435a04(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        if (C22341.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()] == 2) {
            this.navController.navigateUp();
        }
    }
}
