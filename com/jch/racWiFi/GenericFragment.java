package com.jch.racWiFi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import com.accord.common.utils.Logger;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.CustomProgressDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customToolTip.CreateTooltipContentHolder;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import java.util.Iterator;

public class GenericFragment extends Fragment implements IOnBackPressedFragment {
    public static final String TAG = "GenericFragment";
    Handler autoDismissHandler = new Handler();
    private GenericFragmentContainer genericFragmentContainer;
    public IDrawerMenuFunctions iDrawerMenuFunctions;
    public FragmentToActivityCallback mFragmentToActivityCallback;
    /* access modifiers changed from: private */
    public CustomProgressDialog mProgressDialogNetworkCall;

    public void logEvent(String str, int i) {
    }

    public boolean onBackPressed() {
        return false;
    }

    public CustomProgressDialog getProgressDialogNetworkCall() {
        return this.mProgressDialogNetworkCall;
    }

    public void onAttach(Context context) {
        try {
            this.mFragmentToActivityCallback = (FragmentToActivityCallback) context;
            try {
                if (context instanceof HomePageActivity) {
                    this.iDrawerMenuFunctions = (IDrawerMenuFunctions) context;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.onAttach(context);
    }

    public void onDetach() {
        this.mFragmentToActivityCallback = null;
        this.iDrawerMenuFunctions = null;
        super.onDetach();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mProgressDialogNetworkCall = new CustomProgressDialog(getActivity());
        this.genericFragmentContainer = new GenericFragmentContainer(requireActivity());
        CustomProgressDialog.AttributeSet attributeSet = new CustomProgressDialog.AttributeSet();
        attributeSet.setCancelable(false);
        attributeSet.setMessage(getString(R.string.common_alert_pleaseWait));
        this.mProgressDialogNetworkCall.importFromAttributeSet(attributeSet);
        Logger.m49i(TAG, "onCreate");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onStart() {
        super.onStart();
        FragmentToActivityCallback fragmentToActivityCallback = this.mFragmentToActivityCallback;
        if (fragmentToActivityCallback != null) {
            fragmentToActivityCallback.changeStatusBarColor(R.color.white);
        }
        Logger.m49i(TAG, "onStart");
    }

    public void onResume() {
        super.onResume();
        Logger.m49i(TAG, "onResume");
    }

    public void onPause() {
        super.onPause();
        Logger.m49i(TAG, "onPause");
    }

    public void onStop() {
        super.onStop();
        ViewUtils.hideKeyboard(getActivity());
        Logger.m49i(TAG, "onStop");
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.autoDismissHandler.removeCallbacksAndMessages((Object) null);
        Logger.m49i(TAG, "onDestroyView");
    }

    public void onDestroy() {
        super.onDestroy();
        Logger.m49i(TAG, "onDestroy");
        CustomProgressDialog customProgressDialog = this.mProgressDialogNetworkCall;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            this.mProgressDialogNetworkCall.dismiss();
        }
    }

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        beginTransaction.add((int) R.id.fragment_attach_layout, fragment, fragment.getClass().toString());
        beginTransaction.commit();
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        beginTransaction.replace((int) R.id.fragment_attach_layout, fragment, fragment.getClass().toString());
        beginTransaction.commit();
    }

    public static void addFragmentBackStack(FragmentManager fragmentManager, Fragment fragment) {
        String name = fragment.getClass().getName();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        beginTransaction.add((int) R.id.fragment_attach_layout, fragment, name);
        beginTransaction.addToBackStack(name);
        beginTransaction.commit();
    }

    public static void replaceFragmentBackStack(FragmentManager fragmentManager, Fragment fragment) {
        String name = fragment.getClass().getName();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        beginTransaction.replace((int) R.id.fragment_attach_layout, fragment, name);
        beginTransaction.addToBackStack(name);
        beginTransaction.commit();
    }

    public void setNewBundleAsArgument() {
        setArguments(new Bundle());
    }

    public CoreActivity getCoreActivity() {
        return (CoreActivity) getActivity();
    }

    public void lockMenuDrawer() {
        IDrawerMenuFunctions iDrawerMenuFunctions2 = this.iDrawerMenuFunctions;
        if (iDrawerMenuFunctions2 != null) {
            iDrawerMenuFunctions2.onLockMenuDrawerDrawer();
        }
    }

    public void unlockMenuDrawer() {
        IDrawerMenuFunctions iDrawerMenuFunctions2 = this.iDrawerMenuFunctions;
        if (iDrawerMenuFunctions2 != null) {
            iDrawerMenuFunctions2.onUnLockMenuDrawerDrawer();
        }
    }

    public void showPleaseWaitDialog() {
        if (isAdded()) {
            this.mProgressDialogNetworkCall.setMessage(getString(R.string.common_alert_pleaseWait));
            CustomProgressDialog customProgressDialog = this.mProgressDialogNetworkCall;
            if (customProgressDialog != null && !customProgressDialog.isShowing()) {
                this.mProgressDialogNetworkCall.show();
                Logger.m45d("IncludeAddressDetailsFragmentV2", "progress");
                this.autoDismissHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (AbstractNetworkDispatcher.getBodyCall() != null) {
                            AbstractNetworkDispatcher.getBodyCall().cancel();
                        }
                        GenericFragment.this.mProgressDialogNetworkCall.dismiss();
                        Toaster.makeToast(GenericFragment.this.getActivity(), GenericFragment.this.getString(R.string.common_alert_somethingWentWrong), 0);
                    }
                }, 15000);
            }
        }
    }

    public void showPleaseWaitDialog(final boolean z) {
        if (isAdded()) {
            this.mProgressDialogNetworkCall.setMessage(getString(R.string.common_alert_pleaseWait));
            CustomProgressDialog customProgressDialog = this.mProgressDialogNetworkCall;
            if (customProgressDialog != null && !customProgressDialog.isShowing()) {
                this.mProgressDialogNetworkCall.show();
                Logger.m45d("IncludeAddressDetailsFragmentV2", "progress");
                this.autoDismissHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (AbstractNetworkDispatcher.getBodyCall() != null) {
                            AbstractNetworkDispatcher.getBodyCall().cancel();
                        }
                        GenericFragment.this.mProgressDialogNetworkCall.dismiss();
                        if (z) {
                            Toaster.makeToast(GenericFragment.this.getActivity(), GenericFragment.this.getString(R.string.common_alert_somethingWentWrong), 0);
                        }
                    }
                }, 15000);
            }
        }
    }

    public void showPleaseWaitDialog(long j) {
        if (isAdded()) {
            this.mProgressDialogNetworkCall.setMessage(getString(R.string.common_alert_pleaseWait));
            CustomProgressDialog customProgressDialog = this.mProgressDialogNetworkCall;
            if (customProgressDialog != null && !customProgressDialog.isShowing()) {
                this.mProgressDialogNetworkCall.show();
                this.autoDismissHandler.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            AbstractNetworkDispatcher.getBodyCall().cancel();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        GenericFragment.this.mProgressDialogNetworkCall.dismiss();
                        Toaster.makeToast(GenericFragment.this.getActivity(), GenericFragment.this.getString(R.string.common_alert_somethingWentWrong), 0);
                    }
                }, j);
            }
        }
    }

    public void showPleaseWaitDialog(String str) {
        if (isAdded()) {
            if (str == null) {
                str = getString(R.string.common_alert_pleaseWait);
            }
            this.mProgressDialogNetworkCall.setMessage(str);
            CustomProgressDialog customProgressDialog = this.mProgressDialogNetworkCall;
            if (customProgressDialog != null && !customProgressDialog.isShowing()) {
                this.mProgressDialogNetworkCall.show();
                this.autoDismissHandler.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            AbstractNetworkDispatcher.getBodyCall().cancel();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        GenericFragment.this.mProgressDialogNetworkCall.dismiss();
                        Toaster.makeToast(GenericFragment.this.getActivity(), GenericFragment.this.getString(R.string.common_alert_somethingWentWrong), 0);
                    }
                }, 15000);
            }
        }
    }

    public void dismissPleaseWaitDialog() {
        CustomProgressDialog customProgressDialog;
        if (isAdded() && (customProgressDialog = this.mProgressDialogNetworkCall) != null && customProgressDialog.isShowing()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    GenericFragment.this.mProgressDialogNetworkCall.dismiss();
                    GenericFragment.this.autoDismissHandler.removeCallbacksAndMessages((Object) null);
                }
            }, 200);
        }
    }

    /* access modifiers changed from: protected */
    public void alertDialog(String str, boolean z) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getActivity().getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new GenericFragment$$ExternalSyntheticLambda4(this, singleChoiceDialog, z));
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }

    /* renamed from: lambda$alertDialog$0$com-jch-racWiFi-GenericFragment  reason: not valid java name */
    public /* synthetic */ boolean m794lambda$alertDialog$0$comjchracWiFiGenericFragment(SingleChoiceDialog singleChoiceDialog, boolean z, Dialog dialog, View view) {
        singleChoiceDialog.dismiss();
        if (!z) {
            return false;
        }
        goBackFragment();
        return false;
    }

    /* access modifiers changed from: protected */
    public void alertDialog(String str, String str2, boolean z) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(str2);
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new GenericFragment$$ExternalSyntheticLambda5(this, singleChoiceDialog, z));
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }

    /* renamed from: lambda$alertDialog$1$com-jch-racWiFi-GenericFragment  reason: not valid java name */
    public /* synthetic */ boolean m795lambda$alertDialog$1$comjchracWiFiGenericFragment(SingleChoiceDialog singleChoiceDialog, boolean z, Dialog dialog, View view) {
        singleChoiceDialog.dismiss();
        if (!z) {
            return false;
        }
        goBackFragment();
        return false;
    }

    /* access modifiers changed from: protected */
    public void goBackFragment() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: protected */
    public void showPermissionSettingDialog(String str, final Fragment fragment, final NavController navController) {
        new AlertDialog.Builder(getCoreActivity()).setCancelable(false).setMessage(getString(R.string.android_permission_alert_deniedMsg, getReadablePermissionName(str))).setPositiveButton(getString(R.string.android_permission_btn_goToSetting), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", GenericFragment.this.getActivity().getPackageName(), (String) null));
                fragment.startActivityForResult(intent, 132);
            }
        }).setNegativeButton(getString(R.string.common_btn_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                NavController navController = navController;
                if (navController != null) {
                    navController.navigateUp();
                }
            }
        }).show();
    }

    /* access modifiers changed from: protected */
    public boolean checkReadWriteExternalPermission() {
        return ContextCompat.checkSelfPermission(requireActivity(), "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(requireActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    /* access modifiers changed from: protected */
    public boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(requireActivity(), "android.permission.CAMERA") == 0;
    }

    /* access modifiers changed from: protected */
    public boolean checkPermissions(String[] strArr) {
        if (strArr == null) {
            return false;
        }
        for (String checkSelfPermission : strArr) {
            if (ContextCompat.checkSelfPermission(requireActivity(), checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void showPermissionDeniedDialog(View view, String str, NavController navController) {
        String readablePermissionName = getReadablePermissionName(str);
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(requireActivity());
        confirmationDialog.setTitleString(getActivity().getString(R.string.android_permission_alert_permissionDenied));
        confirmationDialog.setMessageString(getString(R.string.android_permission_alert_permissionDeniedAlertDesc, readablePermissionName));
        confirmationDialog.setCancelable(false);
        confirmationDialog.setPositiveButton(getActivity().getString(R.string.android_permission_btn_allow), new GenericFragment$$ExternalSyntheticLambda3(this, str));
        confirmationDialog.setNegativeButton(getActivity().getString(R.string.common_btn_no), new GenericFragment$$ExternalSyntheticLambda2(navController));
        confirmationDialog.setParentView(view);
        confirmationDialog.show();
    }

    /* renamed from: lambda$showPermissionDeniedDialog$2$com-jch-racWiFi-GenericFragment */
    public /* synthetic */ boolean mo27522x7ee9cc6f(String str, Dialog dialog, View view) {
        requestPermissions(new String[]{str}, 177);
        return true;
    }

    static /* synthetic */ boolean lambda$showPermissionDeniedDialog$3(NavController navController, Dialog dialog, View view) {
        if (navController == null) {
            return true;
        }
        navController.navigateUp();
        return true;
    }

    private String getReadablePermissionName(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1888586689:
                if (str.equals(Constants.GrantedPermissions.ACCESS_FINE_LOCATION)) {
                    c = 0;
                    break;
                }
                break;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    c = 1;
                    break;
                }
                break;
            case 1365911975:
                if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return getString(R.string.android_permission_alert_location);
            case 1:
                return getString(R.string.android_permission_alert_camera);
            case 2:
                return getString(R.string.android_permission_alert_storage);
            default:
                return str;
        }
    }

    /* access modifiers changed from: protected */
    public void enableLocationDialog(Fragment fragment, NavController navController) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage((CharSequence) getString(R.string.common_alert_enableLocationService)).setCancelable(false).setPositiveButton((CharSequence) getString(R.string.common_btn_yes), (DialogInterface.OnClickListener) new GenericFragment$$ExternalSyntheticLambda0(fragment)).setNegativeButton((int) R.string.common_btn_no, (DialogInterface.OnClickListener) new GenericFragment$$ExternalSyntheticLambda1(this, navController));
        androidx.appcompat.app.AlertDialog create = builder.create();
        create.setCancelable(false);
        create.show();
    }

    /* renamed from: lambda$enableLocationDialog$5$com-jch-racWiFi-GenericFragment  reason: not valid java name */
    public /* synthetic */ void m796lambda$enableLocationDialog$5$comjchracWiFiGenericFragment(NavController navController, DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        if (navController != null) {
            goBackFragment();
        }
    }

    public void showErrorPopUp(String str) {
        this.genericFragmentContainer.showErrorPopUpContent(str);
    }

    public void showToolTip(View view, String str) {
        final CreateTooltipContentHolder createTooltipContentHolder = new CreateTooltipContentHolder((Context) requireActivity(), view, str);
        if (!createTooltipContentHolder.isShowing()) {
            createTooltipContentHolder.setHintCasePosition(3);
            createTooltipContentHolder.setBorderRes(R.drawable.tool_tip_without_radius);
            createTooltipContentHolder.setBordercolor(R.color.colorRed);
            createTooltipContentHolder.setHitCaseYOffset(R.dimen.hint_case_y_offset);
            createTooltipContentHolder.setContainerOffsetXAxis(R.dimen.container_block_x_offset_center_horizontal);
            createTooltipContentHolder.build();
            createTooltipContentHolder.show();
        } else {
            createTooltipContentHolder.dismiss();
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                createTooltipContentHolder.dismiss();
            }
        }, JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public boolean isIduAlreadyPresent(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        Iterator it = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList().iterator();
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
            if (!detailedIduModel.getVendorThingId().equals(str) && str.equalsIgnoreCase(detailedIduModel.getName())) {
                return false;
            }
        }
        return true;
    }

    public void logEvents(String str, long j) {
        getCoreActivity().logEvents(str, j);
    }

    public void updateProgress(LinearProgressIndicator linearProgressIndicator, int i, int i2) {
        linearProgressIndicator.setMax(i);
        linearProgressIndicator.setProgressCompat(i2, true);
    }
}
