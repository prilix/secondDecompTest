package com.jch.racWiFi.settings.view;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.HelpWebPageModel;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.CustomerCareDevicesBinding;
import com.jch.racWiFi.databinding.HelpFrameGlobalAppBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.settings.model.HelpDataModelResponse;
import com.jch.racWiFi.settings.presenter.HelpInfoPresenter;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import com.jch.racWiFi.util.listeners.AlertListener;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;

public class HelpFragmentGlobal extends GenericFragment implements HelpInfoPresenter.HelpInfoInterface {
    private View customView;
    private DetailedIduModel detailedIduModel;
    private IduList iduList;
    /* access modifiers changed from: private */
    public final ActivityResultLauncher<Intent> mActivityResultMultiplePermissionsShowNotRationale = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new HelpFragmentGlobal$$ExternalSyntheticLambda12(this));
    private HelpFrameGlobalAppBinding mBinding;
    private RacListItemModel mCurrentSelectedRacListItemModel;
    private int mHeightValue;
    private HelpDataModelResponse mHelpDataModelResponse;
    private HelpInfoPresenter mHelpInfoPresenter;
    private HelpWebPageModel mHelpWebPageModel;
    @Inject
    JCIAlertDialog mJciAlertDialog;
    /* access modifiers changed from: private */
    public final ActivityResultLauncher<String[]> mMultiplePermissionResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new HelpFragmentGlobal$$ExternalSyntheticLambda13(this));
    private PopupWindow mPopupWindow;
    private int mWidthValue;
    private NavArgument navArgument;
    private int navigatingFrom = -1;
    private RacListRecyclerViewAdapter racListRecyclerViewAdapter;
    private final String[] storagePermission = {"android.permission.WRITE_EXTERNAL_STORAGE"};

    public interface OnItemClickListener {
        void onClickOfRac(RacListItemModel racListItemModel);
    }

    static /* synthetic */ boolean lambda$showGlobalLinkNotAvailableDialog$11(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$showPleaseSelectAirConditionerDialog$10(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$showPleaseSelectAirConditionerFilesDialog$14(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$showRegionalLinkNotAvailableDialog$12(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$showUserManualNotAvailableDialog$13(Dialog dialog, View view) {
        return true;
    }

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public static HelpFragmentGlobal newInstance() {
        HelpFragmentGlobal helpFragmentGlobal = new HelpFragmentGlobal();
        helpFragmentGlobal.setNewBundleAsArgument();
        return helpFragmentGlobal;
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (HelpFrameGlobalAppBinding) DataBindingUtil.inflate(layoutInflater, R.layout.help_frame_global_app, viewGroup, false);
        onCreateCode();
        this.iduList = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList();
        this.mHelpInfoPresenter = new HelpInfoPresenter(this);
        this.mHelpDataModelResponse = null;
        if (this.iduList.isEmpty()) {
            this.mBinding.include.textViewSelectedDeviceName.setText(R.string.common_lbl_noAc);
            this.mBinding.include.textViewSelectedDeviceName.setTextColor(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.white));
            this.mBinding.include.imageViewArrowDown.setColorFilter(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.white));
            this.mBinding.include.layoutDeviceName.setBackgroundColor(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.button_disabled));
            this.mBinding.include.layoutDeviceName.setEnabled(false);
        } else {
            this.mBinding.include.textViewSelectedDeviceName.setTextColor(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.textview_color_vd_light));
            this.mBinding.include.imageViewArrowDown.setColorFilter(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.colorRed));
            this.mBinding.include.layoutDeviceName.setBackground(ContextCompat.getDrawable(this.mBinding.getRoot().getContext(), R.drawable.black_border_filled));
            this.mBinding.include.layoutDeviceName.setEnabled(true);
        }
        if (this.mHelpWebPageModel.helpType.equals(HelpWebPageModel.HelpType.IDU_SPECIFIC)) {
            this.mBinding.imageButtonMenu.setImageDrawable(R.drawable.ic_back_button_svg);
            this.mBinding.include.textViewDeviceTitleHelp.setVisibility(4);
            this.mBinding.include.textViewDeviceNameHelpIdu.setVisibility(0);
            this.mBinding.include.textViewHelpSubTitle.setVisibility(8);
            this.mBinding.include.layoutDeviceName.setVisibility(8);
            showPleaseWaitDialog();
            this.mHelpInfoPresenter.getHelpInfo(getViewLifecycleOwner(), FamilyGroupList.getCurrentFamily().familyId, this.detailedIduModel.getId().intValue());
            TextView textView = this.mBinding.include.textViewDeviceNameHelpIdu;
            textView.setText(getResources().getString(R.string.manageAc_alert_acDisplatInfo) + "  " + this.detailedIduModel.getName());
        } else {
            this.mBinding.imageButtonMenu.setImageDrawable(R.drawable.ic_hamburger_menu_grey);
            this.mBinding.include.textViewDeviceTitleHelp.setVisibility(8);
            this.mBinding.include.textViewDeviceNameHelpIdu.setVisibility(8);
            this.mBinding.include.textViewHelpSubTitle.setVisibility(0);
            this.mBinding.include.layoutDeviceName.setVisibility(0);
        }
        this.customView = layoutInflater.inflate(R.layout.popup_help_and_cutomercare, (ViewGroup) null);
        this.mCurrentSelectedRacListItemModel = null;
        this.mBinding.include.imageViewDownloadUserManual.setEnabled(!Constants.IS_DEMO_MODE);
        this.mBinding.include.imageViewDownloadUserManual.setAlpha(Constants.IS_DEMO_MODE ? 0.4f : 1.0f);
        logEvent(Screens.SCREENS.name(), 10);
        logEvents(Events.HELP_FREQUENCY.name(), 0);
        return this.mBinding.getRoot();
    }

    private void onCreateCode() {
        if (getArguments() != null) {
            try {
                this.detailedIduModel = HelpFragmentGlobalArgs.fromBundle(getArguments()).getDetailedIduModel();
                this.mHelpWebPageModel = HelpFragmentGlobalArgs.fromBundle(getArguments()).getHelpWebPageModel();
            } catch (IllegalArgumentException unused) {
                commonTask();
            }
        } else {
            commonTask();
        }
    }

    private void commonTask() {
        Object defaultValue;
        NavDestination currentDestination = this.mFragmentToActivityCallback.getNavController().getCurrentDestination();
        if (currentDestination != null) {
            NavArgument navArgument2 = currentDestination.getArguments().get(Values.NAVIGATING_FROM);
            this.navArgument = navArgument2;
            if (navArgument2 != null && (defaultValue = navArgument2.getDefaultValue()) != null) {
                int intValue = ((Integer) defaultValue).intValue();
                this.navigatingFrom = intValue;
                if (intValue == 0) {
                    this.mHelpWebPageModel = (HelpWebPageModel) this.mFragmentToActivityCallback.getNavController().getCurrentDestination().getArguments().get(HelpWebPageModel.PARCEL_KEY).getDefaultValue();
                    return;
                }
                Bundle arguments = getArguments();
                if (arguments != null) {
                    this.mHelpWebPageModel = (HelpWebPageModel) arguments.getParcelable(HelpWebPageModel.PARCEL_KEY);
                    this.detailedIduModel = (DetailedIduModel) arguments.getParcelable(HelpWebPageModel.IDU_PARCEL_KEY);
                }
            }
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.imageButtonMenu.setOnClickListener(new HelpFragmentGlobal$$ExternalSyntheticLambda0(this));
        this.mBinding.include.imageViewDownloadUserManual.setOnClickListener(new HelpFragmentGlobal$$ExternalSyntheticLambda6(this));
        this.mBinding.include.textViewQuickStartGuide.setOnClickListener(new HelpFragmentGlobal$$ExternalSyntheticLambda7(this));
        this.mBinding.include.textViewUserManual.setOnClickListener(new HelpFragmentGlobal$$ExternalSyntheticLambda8(this));
        this.mBinding.include.textViewSpecifications.setOnClickListener(new HelpFragmentGlobal$$ExternalSyntheticLambda9(this));
        this.mBinding.include.layoutDeviceName.setOnClickListener(new HelpFragmentGlobal$$ExternalSyntheticLambda10(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-settings-view-HelpFragmentGlobal */
    public /* synthetic */ void mo31884x16919d71(View view) {
        onClickMenu();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-settings-view-HelpFragmentGlobal */
    public /* synthetic */ void mo31885x83b4390(View view) {
        onLongClick();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-settings-view-HelpFragmentGlobal */
    public /* synthetic */ void mo31886xf9e4e9af(View view) {
        onClickQuickStartGuide();
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-settings-view-HelpFragmentGlobal */
    public /* synthetic */ void mo31887xeb8e8fce(View view) {
        onClickUserManual();
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-settings-view-HelpFragmentGlobal */
    public /* synthetic */ void mo31888xdd3835ed(View view) {
        onClickSpecifications();
    }

    /* renamed from: lambda$onViewCreated$5$com-jch-racWiFi-settings-view-HelpFragmentGlobal */
    public /* synthetic */ void mo31889xcee1dc0c(View view) {
        onClickLayoutName();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHelpInfoPresenter.removeCallbacks();
    }

    /* access modifiers changed from: package-private */
    public void onClickMenu() {
        if (this.mHelpWebPageModel.helpType.equals(HelpWebPageModel.HelpType.GENERAL)) {
            this.iDrawerMenuFunctions.openMenuDrawer();
        } else {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    /* access modifiers changed from: package-private */
    public void onLongClick() {
        HelpDataModelResponse helpDataModelResponse = this.mHelpDataModelResponse;
        if (helpDataModelResponse == null) {
            showPleaseSelectAirConditionerFilesDialog();
        } else if (!helpDataModelResponse.manualLinks.isEmpty()) {
            checkPermissions();
        } else {
            showUserManualNotAvailableDialog();
        }
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT > 28) {
            downloadPdfFromLink();
        } else if (checkReadWriteExternalPermission()) {
            downloadPdfFromLink();
        } else if (shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE") || shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE")) {
            showRationale(getString(R.string.android_permission_alert_storage));
        } else {
            this.mMultiplePermissionResult.launch(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"});
        }
    }

    /* renamed from: lambda$new$6$com-jch-racWiFi-settings-view-HelpFragmentGlobal  reason: not valid java name */
    public /* synthetic */ void m1355lambda$new$6$comjchracWiFisettingsviewHelpFragmentGlobal(Map map) {
        if (checkReadWriteExternalPermission()) {
            downloadPdfFromLink();
        } else {
            showNotRationale(getString(R.string.android_permission_alert_storage));
        }
    }

    private void showRationale(String str) {
        this.mJciAlertDialog.showDialog(this.mBinding.getRoot().getContext(), getString(R.string.android_permission_alert_permissionDenied), getString(R.string.android_permission_alert_permissionDeniedAlertDesc, str), getString(R.string.android_permission_btn_allow), getString(R.string.common_btn_no), new AlertListener() {
            public void onNegative() {
            }

            public void onPositive() {
                HelpFragmentGlobal.this.mMultiplePermissionResult.launch(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"});
            }
        });
    }

    private void showNotRationale(String str) {
        this.mJciAlertDialog.showDialog(this.mBinding.getRoot().getContext(), (String) null, getString(R.string.android_permission_alert_deniedMsg, str), getString(R.string.android_permission_btn_goToSetting), getString(R.string.common_btn_cancel), new AlertListener() {
            public void onPositive() {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", HelpFragmentGlobal.this.getCoreActivity().getPackageName(), (String) null));
                HelpFragmentGlobal.this.mActivityResultMultiplePermissionsShowNotRationale.launch(intent);
            }

            public void onNegative() {
                if (HelpFragmentGlobal.this.getCoreActivity().getNavController() != null) {
                    HelpFragmentGlobal.this.getCoreActivity().getNavController().navigateUp();
                }
            }
        });
    }

    /* renamed from: lambda$new$7$com-jch-racWiFi-settings-view-HelpFragmentGlobal  reason: not valid java name */
    public /* synthetic */ void m1356lambda$new$7$comjchracWiFisettingsviewHelpFragmentGlobal(ActivityResult activityResult) {
        if (checkReadWriteExternalPermission()) {
            downloadPdfFromLink();
        } else {
            showNotRationale(getString(R.string.android_permission_alert_storage));
        }
    }

    private void onScopedStoragePermissionGranted() {
        downloadPdfFromLink();
    }

    private void downloadPdfFromLink() {
        String str;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.mHelpDataModelResponse.manualLinks.get(0)));
        if (this.mHelpWebPageModel.helpType.equals(HelpWebPageModel.HelpType.IDU_SPECIFIC)) {
            str = this.detailedIduModel.name + "_manual.pdf";
        } else {
            str = this.mCurrentSelectedRacListItemModel.name + "_manual.pdf";
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        ((DownloadManager) requireActivity().getSystemService("download")).enqueue(request);
    }

    /* access modifiers changed from: package-private */
    public void onClickQuickStartGuide() {
        if (!NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
        } else if (this.mCurrentSelectedRacListItemModel != null || this.mHelpWebPageModel.helpType.equals(HelpWebPageModel.HelpType.IDU_SPECIFIC)) {
            HelpDataModelResponse helpDataModelResponse = this.mHelpDataModelResponse;
            if (helpDataModelResponse == null) {
                showUserManualNotAvailableDialog();
            } else if (!helpDataModelResponse.manualLinks.isEmpty()) {
                openBrowser();
            } else {
                showUserManualNotAvailableDialog();
            }
        } else {
            showPleaseSelectAirConditionerDialog();
        }
    }

    private void openBrowserV3() {
        String str = this.mHelpDataModelResponse.manualLinks.get(0);
        String.format(Locale.ENGLISH, "https://docs.google.com/viewer?embedded=true&url=%s", new Object[]{str});
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse(str), "text/html");
        startActivity(intent);
    }

    private void openBrowserV2() {
        String str = this.mHelpDataModelResponse.manualLinks.get(0);
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), "application/pdf");
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            openBrowser();
        }
    }

    private void openBrowserV1() {
        String format = String.format(Locale.ENGLISH, "https://docs.google.com/gview?embedded=true&url=%s", new Object[]{"https://documents.aircloudhome.com/user-guide/EN/HO2020341A%20airCloud%20Home%20Adapter%20Operation%20&%20Installation%20Guide%20ver21.03.pdf"});
        Log.e("URI_drive_encode", Uri.encode(format));
        Log.e("URI_drive", format);
        Log.e("URI_backend", "https://documents.aircloudhome.com/user-guide/EN/HO2020341A%20airCloud%20Home%20Adapter%20Operation%20&%20Installation%20Guide%20ver21.03.pdf");
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://documents.aircloudhome.com/user-guide/EN/HO2020341A%20airCloud%20Home%20Adapter%20Operation%20&%20Installation%20Guide%20ver21.03.pdf")));
    }

    private void openBrowser() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.ENGLISH, "https://docs.google.com/gview?embedded=true&url=%s", new Object[]{this.mHelpDataModelResponse.manualLinks.get(0)}))));
    }

    /* access modifiers changed from: package-private */
    public void onClickUserManual() {
        if (!NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
        } else if (this.mCurrentSelectedRacListItemModel != null || this.mHelpWebPageModel.helpType.equals(HelpWebPageModel.HelpType.IDU_SPECIFIC)) {
            HelpDataModelResponse helpDataModelResponse = this.mHelpDataModelResponse;
            if (helpDataModelResponse != null) {
                String str = helpDataModelResponse.globalLink.get(0);
                if (str != null) {
                    if (!str.startsWith("http://") && !str.startsWith("https://")) {
                        ArrayList<String> arrayList = this.mHelpDataModelResponse.globalLink;
                        arrayList.set(0, "https://" + str);
                    }
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.mHelpDataModelResponse.globalLink.get(0))));
                    return;
                }
                showGlobalLinkNotAvailableDialog();
                return;
            }
            showGlobalLinkNotAvailableDialog();
        } else {
            showPleaseSelectAirConditionerDialog();
        }
    }

    /* access modifiers changed from: package-private */
    public void onClickSpecifications() {
        if (!NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
        } else if (this.mCurrentSelectedRacListItemModel != null || this.mHelpWebPageModel.helpType.equals(HelpWebPageModel.HelpType.IDU_SPECIFIC)) {
            HelpDataModelResponse helpDataModelResponse = this.mHelpDataModelResponse;
            if (helpDataModelResponse != null) {
                String str = helpDataModelResponse.regionalLink.get(0);
                if (str != null) {
                    if (!str.startsWith("http://") && !str.startsWith("https://")) {
                        ArrayList<String> arrayList = this.mHelpDataModelResponse.regionalLink;
                        arrayList.set(0, "https://" + str);
                    }
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.mHelpDataModelResponse.regionalLink.get(0))));
                    return;
                }
                showRegionalLinkNotAvailableDialog();
                return;
            }
            showRegionalLinkNotAvailableDialog();
        } else {
            showPleaseSelectAirConditionerDialog();
        }
    }

    public void onClickLayoutName() {
        if (!NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
            return;
        }
        IduList iduList2 = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList();
        this.iduList = iduList2;
        if (!iduList2.isEmpty()) {
            float dimension = getResources().getDimension(R.dimen.popup_height);
            float dimension2 = getResources().getDimension(R.dimen.popup_width);
            this.mHeightValue = Math.round(dimension);
            this.mWidthValue = Math.round(dimension2);
            Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            int round = point.x - Math.round(ViewUtils.convertDpToPixel(90.0f, this.mBinding.getRoot().getContext()));
            RacListRecyclerViewAdapter racListRecyclerViewAdapter2 = new RacListRecyclerViewAdapter(this.mBinding.getRoot().getContext());
            this.racListRecyclerViewAdapter = racListRecyclerViewAdapter2;
            List<RacListItemModel> devices = racListRecyclerViewAdapter2.getDevices();
            Iterator it = this.iduList.iterator();
            while (it.hasNext()) {
                RacListItemModel racListItemModel = new RacListItemModel();
                racListItemModel.copyFromDetailIduModel((DetailedIduModel) it.next());
                racListItemModel.setOnItemClickListener(new HelpFragmentGlobal$$ExternalSyntheticLambda5(this));
                devices.add(racListItemModel);
            }
            this.racListRecyclerViewAdapter.notifyDataSetChanged();
            if (this.racListRecyclerViewAdapter.getDevices().size() >= 6) {
                this.mPopupWindow = new PopupWindow(this.customView, round, this.mHeightValue);
            } else {
                this.mPopupWindow = new PopupWindow(this.customView, round, -2);
            }
            RecyclerView recyclerView = (RecyclerView) this.customView.findViewById(R.id.recycler_view_users_name);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.mBinding.getRoot().getContext()));
            recyclerView.setAdapter(this.racListRecyclerViewAdapter);
            if (Build.VERSION.SDK_INT >= 21) {
                this.mPopupWindow.setElevation(5.0f);
            }
            this.mPopupWindow.setOutsideTouchable(true);
            this.mPopupWindow.setFocusable(true);
            this.mPopupWindow.setOnDismissListener(new HelpFragmentGlobal$$ExternalSyntheticLambda11(this));
            if (!this.mPopupWindow.isShowing()) {
                int[] iArr = new int[2];
                this.mBinding.include.layoutDeviceName.getLocationInWindow(iArr);
                this.mPopupWindow.showAtLocation((View) getView().getParent(), 48, 0, iArr[1] + this.mBinding.include.layoutDeviceName.getHeight());
                this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
                this.mBinding.include.imageViewArrowDown.setRotation(180.0f);
                return;
            }
            this.mPopupWindow.dismiss();
            this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
            return;
        }
        this.mBinding.include.textViewSelectedDeviceName.setText(R.string.common_lbl_noAc);
    }

    /* renamed from: lambda$onClickLayoutName$8$com-jch-racWiFi-settings-view-HelpFragmentGlobal */
    public /* synthetic */ void mo31882x1862be2f(RacListItemModel racListItemModel) {
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        }
        showPleaseWaitDialog();
        this.mBinding.include.textViewSelectedDeviceName.setText(racListItemModel.name);
        this.mHelpInfoPresenter.getHelpInfo(getViewLifecycleOwner(), FamilyGroupList.getCurrentFamily().familyId, racListItemModel.racID);
        this.mCurrentSelectedRacListItemModel = racListItemModel;
    }

    /* renamed from: lambda$onClickLayoutName$9$com-jch-racWiFi-settings-view-HelpFragmentGlobal */
    public /* synthetic */ void mo31883xa0c644e() {
        this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
        this.mBinding.include.imageViewArrowDown.setRotation(0.0f);
    }

    public void onHelpInfoFetchSuccess(HelpDataModelResponse helpDataModelResponse) {
        dismissPleaseWaitDialog();
        this.mHelpDataModelResponse = helpDataModelResponse;
    }

    public void onHelpInfoFetchFailure(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse.getResponse().code() != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                HelpFragmentGlobal.this.callHelpAPI();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callHelpAPI() {
        dismissPleaseWaitDialog();
        this.mHelpInfoPresenter.getHelpInfo(getViewLifecycleOwner(), FamilyGroupList.getCurrentFamily().familyId, this.mCurrentSelectedRacListItemModel.racID);
    }

    public void onNetworkCallFailure(Throwable th) {
        dismissPleaseWaitDialog();
        GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 177 && iArr.length > 0) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] != 0) {
                    if (!shouldShowRequestPermissionRationale(strArr[i2])) {
                        showPermissionSettingDialog(strArr[i2], this, (NavController) null);
                    } else {
                        showPermissionDeniedDialog(this.mBinding.parent, strArr[i2], (NavController) null);
                    }
                }
            }
            if (checkPermissions(this.storagePermission)) {
                downloadPdfFromLink();
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 132 && checkPermissions(this.storagePermission)) {
            downloadPdfFromLink();
        }
    }

    static class RacListRecyclerViewAdapter extends RecyclerView.Adapter<RacListViewHolder> {
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public final List<RacListItemModel> devices = new ArrayList();
        private RacListItemModel mSelectedUser;

        public List<RacListItemModel> getDevices() {
            return this.devices;
        }

        public RacListItemModel getUserName() {
            return this.mSelectedUser;
        }

        public void setSelectedLanguage(RacListItemModel racListItemModel) {
            this.mSelectedUser = racListItemModel;
        }

        public RacListRecyclerViewAdapter(Context context2) {
            this.context = context2;
        }

        public RacListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new RacListViewHolder((CustomerCareDevicesBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.customer_care_devices, viewGroup, false));
        }

        public void onBindViewHolder(RacListViewHolder racListViewHolder, int i) {
            racListViewHolder.bind(this.devices.get(i));
        }

        public int getItemCount() {
            return this.devices.size();
        }

        class RacListViewHolder extends RecyclerView.ViewHolder {
            CustomerCareDevicesBinding binding;

            public RacListViewHolder(CustomerCareDevicesBinding customerCareDevicesBinding) {
                super(customerCareDevicesBinding.getRoot());
                this.binding = customerCareDevicesBinding;
                customerCareDevicesBinding.layoutManageDevices.setOnClickListener(new C2335x42e0e9d7(this, customerCareDevicesBinding));
            }

            /* renamed from: lambda$new$0$com-jch-racWiFi-settings-view-HelpFragmentGlobal$RacListRecyclerViewAdapter$RacListViewHolder */
            public /* synthetic */ void mo31909x504738c7(CustomerCareDevicesBinding customerCareDevicesBinding, View view) {
                onClickItem(customerCareDevicesBinding.layoutManageDevices);
            }

            public void onClickItem(ConstraintLayout constraintLayout) {
                RacListItemModel racListItemModel = (RacListItemModel) constraintLayout.getTag();
                racListItemModel.onItemClickListener.onClickOfRac(racListItemModel);
                for (RacListItemModel racListItemModel2 : RacListRecyclerViewAdapter.this.devices) {
                    if (racListItemModel2.equals(racListItemModel)) {
                        racListItemModel2.setSelected(true);
                    } else {
                        racListItemModel2.setSelected(false);
                    }
                }
                RacListRecyclerViewAdapter.this.notifyDataSetChanged();
            }

            public void bind(RacListItemModel racListItemModel) {
                this.binding.textViewDeviceName.setText(racListItemModel.getName());
                if (racListItemModel.isSelected()) {
                    this.binding.layoutManageDevices.setBackgroundColor(ContextCompat.getColor(RacListRecyclerViewAdapter.this.context, R.color.lyt_grey));
                } else {
                    this.binding.layoutManageDevices.setBackgroundColor(ContextCompat.getColor(RacListRecyclerViewAdapter.this.context, R.color.white));
                }
                this.binding.layoutManageDevices.setTag(racListItemModel);
            }
        }
    }

    public static class RacListItemModel {
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public OnItemClickListener onItemClickListener;
        /* access modifiers changed from: private */
        public int racID;
        private boolean selected;
        private String vendorThingID;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
            this.onItemClickListener = onItemClickListener2;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean z) {
            this.selected = z;
        }

        public boolean equals(Object obj) {
            return (obj instanceof RacListItemModel) && ((RacListItemModel) obj).name.equals(this.name);
        }

        public void copyFromDetailIduModel(DetailedIduModel detailedIduModel) {
            this.name = detailedIduModel.name;
            this.racID = detailedIduModel.f454id.intValue();
            this.vendorThingID = detailedIduModel.vendorThingId;
        }
    }

    private void showPleaseSelectAirConditionerDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.help_alert_selectAC));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), HelpFragmentGlobal$$ExternalSyntheticLambda1.INSTANCE);
        singleChoiceDialog.show();
    }

    private void showGlobalLinkNotAvailableDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.help_alert_globalPortalNotAvailable));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), HelpFragmentGlobal$$ExternalSyntheticLambda14.INSTANCE);
        singleChoiceDialog.show();
    }

    private void showRegionalLinkNotAvailableDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.help_alert_regionalPortalNotAvailable));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), HelpFragmentGlobal$$ExternalSyntheticLambda3.INSTANCE);
        singleChoiceDialog.show();
    }

    private void showUserManualNotAvailableDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.help_alert_userManualNotAvailable));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), HelpFragmentGlobal$$ExternalSyntheticLambda4.INSTANCE);
        singleChoiceDialog.show();
    }

    private void showPleaseSelectAirConditionerFilesDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.help_alert_selectAcToGetFiles));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), HelpFragmentGlobal$$ExternalSyntheticLambda2.INSTANCE);
        singleChoiceDialog.show();
    }
}
