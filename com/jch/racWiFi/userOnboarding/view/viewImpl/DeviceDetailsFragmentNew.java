package com.jch.racWiFi.userOnboarding.view.viewImpl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.Gson;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.SearchLayout;
import com.jch.racWiFi.databinding.DeviceSettingFrameNewBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch.racWiFi.userOnboarding.presenter.DeviceDetailsPresenter;
import com.jch.racWiFi.userOnboarding.presenter.presenterImpl.DeviceDetailsPresenterImpl;
import com.jch.racWiFi.userOnboarding.view.DeviceDetailsView;
import com.jch.racWiFi.userOnboarding.view.DeviceName;
import com.jch.racWiFi.userOnboarding.view.uiComponents.dialog.IduRenameDialogNew;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class DeviceDetailsFragmentNew extends GenericFragment implements DeviceDetailsView, LifecycleOwner, DeviceName {
    private ConfirmationDialog confirmationDialog;
    private final List<IduDetailsModel> detailedIduModelsList = new ArrayList();
    /* access modifiers changed from: private */
    public final DeviceDetailsPresenter deviceDetailsPresenter = new DeviceDetailsPresenterImpl(this);
    private final Observer<Boolean> forceRefreshObserver = new Observer<Boolean>() {
        public void onChanged(Boolean bool) {
            if (DeviceDetailsFragmentNew.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                DeviceDetailsFragmentNew.this.dismissPleaseWaitDialog();
                if (DeviceDetailsFragmentNew.this.toRemoveRac) {
                    DeviceDetailsFragmentNew.this.navController.navigateUp();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public IduDetailsModel iduDetailsModel = null;
    /* access modifiers changed from: private */
    public IduRenameDialogNew iduRenameDialog;
    /* access modifiers changed from: private */
    public DevicesAdapter mAdapter;
    private DeviceSettingFrameNewBinding mBinding;
    private SearchLayout mSearchClass;
    NavController navController;
    private final TextWatcher searchTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            DeviceDetailsFragmentNew.this.mAdapter.getFilter().filter(charSequence);
            new Handler().postDelayed(new DeviceDetailsFragmentNew$1$$ExternalSyntheticLambda0(this, charSequence), 200);
        }

        /* renamed from: lambda$onTextChanged$0$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew$1 */
        public /* synthetic */ void mo33526x8340bc5d(CharSequence charSequence) {
            if (DeviceDetailsFragmentNew.this.mAdapter.deviceListFiltered.size() == 0) {
                DeviceDetailsFragmentNew.this.iduRenameDialog.cardView.setVisibility(8);
            } else {
                DeviceDetailsFragmentNew.this.iduRenameDialog.cardView.setVisibility(0);
            }
            if (charSequence.toString().length() == 0) {
                DeviceDetailsFragmentNew.this.iduRenameDialog.cardView.setVisibility(0);
            }
        }
    };
    private SingleChoiceDialog singleChoiceDialog;
    boolean toRemoveRac = false;

    static /* synthetic */ boolean lambda$onActivityCreated$2(Dialog dialog, View view) {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.singleChoiceDialog = new SingleChoiceDialog(getActivity());
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getForceRefreshMutableLiveData().observeSingleEvent(getViewLifecycleOwner(), this.forceRefreshObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getForceRefreshMutableLiveData().removeObserver(this.forceRefreshObserver);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z;
        this.mBinding = (DeviceSettingFrameNewBinding) DataBindingUtil.inflate(layoutInflater, R.layout.device_setting_frame_new, viewGroup, false);
        this.navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        this.mBinding.includedLayout.guideline260.setVisibility(8);
        Bundle arguments = getArguments();
        int i = arguments != null ? arguments.getInt(NavigationTransitionKeyValues.NAVIGATION_FROM) : 1008;
        for (DetailedIduModel copyFromDetailIduModel : getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList()) {
            IduDetailsModel iduDetailsModel2 = new IduDetailsModel();
            iduDetailsModel2.copyFromDetailIduModel(copyFromDetailIduModel);
            this.detailedIduModelsList.add(iduDetailsModel2);
        }
        if (i == 1008) {
            this.mBinding.tvSkip.setVisibility(4);
            this.mBinding.includedLayout.layoutRemoveDevice.setVisibility(0);
            this.mBinding.backButton.setVisibility(0);
            z = true;
        } else {
            this.mBinding.tvSkip.setVisibility(0);
            this.mBinding.includedLayout.layoutRemoveDevice.setVisibility(4);
            this.mBinding.backButton.setVisibility(4);
            z = false;
        }
        if (!z) {
            requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                public void handleOnBackPressed() {
                    DeviceDetailsFragmentNew.this.skip();
                }
            });
        }
        if (Constants.IS_DEMO_MODE) {
            this.mBinding.includedLayout.manageUser.setVisibility(4);
            this.mBinding.includedLayout.view49.setVisibility(4);
        } else {
            this.mBinding.includedLayout.manageUser.setVisibility(0);
            this.mBinding.includedLayout.view49.setVisibility(0);
        }
        logEvent(Screens.SCREENS.name(), 5);
        return this.mBinding.getRoot();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        NavArgument navArgument = this.navController.getGraph().getArguments().get(Values.IDU_DETAILS_KEY);
        this.iduRenameDialog = new IduRenameDialogNew(getActivity());
        if (navArgument != null) {
            this.iduDetailsModel = (IduDetailsModel) navArgument.getDefaultValue();
            this.mBinding.includedLayout.tvIduDeviceId.setText(this.iduDetailsModel.getVendorThingId());
            this.mBinding.includedLayout.tvDeviceName.setText(this.iduDetailsModel.getName());
            TextView textView = this.iduRenameDialog.tvIduVendorThingID;
            textView.setText(getString(R.string.manageAc_lbl_acIdShort) + " " + this.iduDetailsModel.getVendorThingId());
            String[] split = getActivity().getResources().getString(R.string.manageAc_acNames).split(",");
            ArrayList arrayList = new ArrayList();
            this.mAdapter = new DevicesAdapter(arrayList, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            this.mSearchClass = new SearchLayout(this.iduRenameDialog.layout);
            Collections.addAll(arrayList, split);
            this.iduRenameDialog.mRecyclerViewDeviceName.setLayoutManager(linearLayoutManager);
            this.iduRenameDialog.mRecyclerViewDeviceName.setItemAnimator(new DefaultItemAnimator());
            this.iduRenameDialog.mRecyclerViewDeviceName.setAdapter(this.mAdapter);
            this.iduRenameDialog.newIduNameField.setOnFocusChangeListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda2(this));
            this.iduRenameDialog.newIduNameField.setOnEditorActionListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda3(this));
            this.iduRenameDialog.btnConfirm.setOnClickListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda10(this));
        }
    }

    /* renamed from: lambda$onActivityCreated$0$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33503xa70033e2(View view, boolean z) {
        if (!z) {
            this.iduRenameDialog.cardView.setVisibility(8);
        } else if (this.mAdapter.deviceListFiltered.size() == 0) {
            this.iduRenameDialog.cardView.setVisibility(8);
        } else {
            this.iduRenameDialog.cardView.setVisibility(0);
        }
    }

    /* renamed from: lambda$onActivityCreated$1$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ boolean mo33504x8a2be723(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        this.iduRenameDialog.cardView.setVisibility(8);
        this.iduRenameDialog.newIduNameField.clearFocus();
        return false;
    }

    /* renamed from: lambda$onActivityCreated$3$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33505x50834da5(View view) {
        String trim = this.iduRenameDialog.newIduNameField.getText().toString().trim();
        if (trim.isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.manageAc_alert_nameCannotBeEmpty), 0).show();
        } else if (!isContain(trim)) {
            SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(getActivity());
            singleChoiceDialog2.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog2.setMessageString(getString(R.string.manageAc_alert_acNameAlreadyExists));
            singleChoiceDialog2.setCancelable(false);
            singleChoiceDialog2.setPositiveButton(getString(R.string.common_btn_ok), DeviceDetailsFragmentNew$$ExternalSyntheticLambda7.INSTANCE);
            if (!singleChoiceDialog2.isShowing()) {
                singleChoiceDialog2.show();
            }
        } else {
            this.iduRenameDialog.dismiss();
            if (NetworkConnectivity.isNetworkAvailable(getActivity())) {
                showPleaseWaitDialog();
                this.deviceDetailsPresenter.renameThing(this, this.iduDetailsModel, trim);
                return;
            }
            showAlert(getString(R.string.common_alert_unableToConnectToNw), false);
        }
    }

    public void removeIdu() {
        if (getActivity() == null || !NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            showAlert(getString(R.string.common_alert_unableToConnectToNw), false);
        } else {
            saveSelectedChanges();
        }
    }

    public void onDeviceRemoved() {
        Toast.makeText(getContext(), getString(R.string.manageAc_alert_deletedSuccessfully), 0).show();
        this.toRemoveRac = true;
        this.mFragmentToActivityCallback.refreshAllIduStates();
        getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getGeoFenceStatus(getViewLifecycleOwner());
    }

    public void onDeviceNotRemoved(int i, GenericResponse.GenericErrorBody genericErrorBody) {
        if (i == 403) {
            dismissPleaseWaitDialog();
            showAlert(getString(R.string.forbidden_action), false);
        } else if (i != 404) {
            if (i == 406) {
                dismissPleaseWaitDialog();
                showAlert(getString(R.string.forbidden_action), false);
            } else if (i != 412) {
                dismissPleaseWaitDialog();
                showAlert(getString(R.string.forbidden_action), false);
            } else {
                dismissPleaseWaitDialog();
                showAlert(getString(R.string.errorCode_alert_PCF009), false);
            }
        } else if (genericErrorBody.code != null) {
            String str = genericErrorBody.code;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1995143803:
                    if (str.equals("NFE002")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1995143801:
                    if (str.equals("NFE004")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1995143773:
                    if (str.equals(StatusCode.NO_RAC_FOUND)) {
                        c = 2;
                        break;
                    }
                    break;
                case -1995143772:
                    if (str.equals(StatusCode.RAC_DOES_NOT_BELONG_TO_THIS_FAMILY)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    showAlert(getString(R.string.errorCode_alert_NFE002), true);
                    return;
                case 1:
                    showAlert(getString(R.string.errorCode_alert_NFE004), true);
                    return;
                case 2:
                    showAlert(getString(R.string.errorCode_alert_NFE011), true);
                    return;
                case 3:
                    showAlert(getString(R.string.errorCode_alert_NFE012), true);
                    return;
                default:
                    Toast.makeText(getContext(), getString(R.string.manageAc_alert_acDeletedOrNotExists), 0).show();
                    return;
            }
        } else {
            showAlert(getString(R.string.manageAc_alert_acDeletedOrNotExists), true);
        }
    }

    public void onDeviceRenamed(String str) {
        this.mFragmentToActivityCallback.refreshAllIduStates();
        this.iduRenameDialog.dismiss();
        this.mBinding.includedLayout.tvDeviceName.setText(str);
    }

    public void onRenamingFailed(Response<ResponseBody> response) {
        this.iduRenameDialog.dismiss();
        int code = response.code();
        if (code == 400) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
        } else if (code == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    DeviceDetailsFragmentNew.this.showPleaseWaitDialog();
                    String trim = DeviceDetailsFragmentNew.this.iduRenameDialog.newIduNameField.getText().toString().trim();
                    DeviceDetailsPresenter r1 = DeviceDetailsFragmentNew.this.deviceDetailsPresenter;
                    DeviceDetailsFragmentNew deviceDetailsFragmentNew = DeviceDetailsFragmentNew.this;
                    r1.renameThing(deviceDetailsFragmentNew, deviceDetailsFragmentNew.iduDetailsModel, trim);
                }
            }, false);
        } else if (code != 404) {
            Toast.makeText(getContext(), getString(R.string.manageAc_alert_unableToRenameAc), 0).show();
        } else {
            try {
                new com.jch.racWiFi.GenericResponse();
                com.jch.racWiFi.GenericResponse genericResponse = (com.jch.racWiFi.GenericResponse) new Gson().fromJson(response.errorBody().charStream(), com.jch.racWiFi.GenericResponse.class);
                showErrorPopUp(getString(genericResponse.getErrorMessageStringId(String.valueOf(genericResponse.statusCode))));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void onViewClicked() {
        this.iduRenameDialog.newIduNameField.setText(this.iduDetailsModel.getName());
        this.mSearchClass.addTextWatcher(this.searchTextWatcher);
        this.iduRenameDialog.setParentView(this.mBinding.parent);
        this.iduRenameDialog.show();
    }

    public void onSkipClicked() {
        skip();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.includedLayout.layoutRemoveDevice.setOnClickListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda11(this));
        this.mBinding.includedLayout.manageUser.setOnClickListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda12(this));
        this.mBinding.backButton.setOnClickListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda13(this));
        this.mBinding.includedLayout.clUserPermissions.setOnClickListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda14(this));
        this.mBinding.tvSkip.setOnClickListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda15(this));
        this.mBinding.includedLayout.layoutDeviceNameEditDeviceConfiguration.setOnClickListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda16(this));
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33506xc5e36c90(View view) {
        removeIdu();
    }

    /* renamed from: lambda$onViewCreated$5$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33507xa90f1fd1(View view) {
        moveToManageUserFragment();
    }

    /* renamed from: lambda$onViewCreated$6$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33508x8c3ad312(View view) {
        onClickBackPressed();
    }

    /* renamed from: lambda$onViewCreated$7$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33509x6f668653(View view) {
        onDevicePermissionCalled();
    }

    /* renamed from: lambda$onViewCreated$8$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33510x52923994(View view) {
        onSkipClicked();
    }

    /* renamed from: lambda$onViewCreated$9$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33511x35bdecd5(View view) {
        onViewClicked();
    }

    /* access modifiers changed from: private */
    public void skip() {
        this.navController.navigate((int) R.id.action_deviceDetailsFragment_to_homePageFragment);
    }

    public void onDevicePermissionCalled() {
        Bundle bundle = new Bundle();
        bundle.putInt(NavigationTransitionKeyValues.NAVIGATION_FROM, 1014);
        bundle.putInt(Values.IDU_ID_KEY, (int) this.iduDetailsModel.getId().longValue());
        bundle.putString(Values.IDU_NAME_KEY, this.iduDetailsModel.getName());
        bundle.putInt(Values.PERMISSION_MODE_KEY, 2);
        this.navController.navigate((int) R.id.action_deviceDetailsFragment_to_userPermisissionsDeviceSettingsFragment, bundle);
    }

    public void onClickBackPressed() {
        this.navController.navigateUp();
    }

    private void saveSelectedChanges() {
        if (getActivity() != null) {
            ConfirmationDialog confirmationDialog2 = new ConfirmationDialog(getActivity());
            this.confirmationDialog = confirmationDialog2;
            confirmationDialog2.setTitleString(getString(R.string.manageAc_btn_removeAc));
            this.confirmationDialog.setMessageString(getString(R.string.manageAc_lbl_removeAcDesc));
            this.confirmationDialog.setPositiveButton(getString(R.string.common_btn_yes), new DeviceDetailsFragmentNew$$ExternalSyntheticLambda4(this));
            this.confirmationDialog.setNegativeButton(getString(R.string.common_btn_no), new DeviceDetailsFragmentNew$$ExternalSyntheticLambda5(this));
            this.confirmationDialog.setParentView(this.mBinding.parent);
            this.confirmationDialog.show();
        }
    }

    /* renamed from: lambda$saveSelectedChanges$10$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ boolean mo33515xcd92ae(Dialog dialog, View view) {
        saveChangesDialog2();
        this.confirmationDialog.dismiss();
        return false;
    }

    /* renamed from: lambda$saveSelectedChanges$11$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ boolean mo33516xe3f945ef(Dialog dialog, View view) {
        this.confirmationDialog.dismiss();
        return false;
    }

    private void saveChangesDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true).setView(R.layout.remove_air_conditioner_dialog_two);
        AlertDialog create = builder.create();
        create.setOnShowListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda8(this));
        create.show();
        create.setOnDismissListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda0(this));
        if (create.getWindow() != null) {
            WindowManager.LayoutParams attributes = create.getWindow().getAttributes();
            attributes.dimAmount = 0.2f;
            create.getWindow().setAttributes(attributes);
            create.getWindow().addFlags(2);
            View decorView = create.getWindow().getDecorView();
            ((com.jch.racWiFi.customViews.customWidgets.TextView) decorView.findViewById(R.id.text_view_confirm_dialog_sub_title_two)).setText("! " + getString(R.string.manageAc_lbl_ownershipInformationErased));
            ((com.jch.racWiFi.customViews.customWidgets.TextView) decorView.findViewById(R.id.text_view_confirm_dialog_sub_title_three)).setText("! " + getString(R.string.manageAc_lbl_ownershipInformationErasedDesc));
            ((Button) decorView.findViewById(R.id.button_positive)).setOnClickListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda1(this, create));
            ((Button) decorView.findViewById(R.id.button_negative)).setOnClickListener(new DeviceDetailsFragmentNew$$ExternalSyntheticLambda9(create));
        }
    }

    /* renamed from: lambda$saveChangesDialog2$12$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33512x5eeee41d(DialogInterface dialogInterface) {
        this.mBinding.parent.setBackgroundResource(R.drawable.white_blur);
    }

    /* renamed from: lambda$saveChangesDialog2$13$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33513x421a975e(DialogInterface dialogInterface) {
        this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
    }

    /* renamed from: lambda$saveChangesDialog2$14$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ void mo33514x25464a9f(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
        if (NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            if (this.iduDetailsModel != null) {
                showPleaseWaitDialog();
                this.deviceDetailsPresenter.unBoardIdu(this, FamilyGroupList.getCurrentFamily().familyId, this.iduDetailsModel.getId().longValue());
            } else {
                onDeviceNotRemoved(10009, (GenericResponse.GenericErrorBody) null);
            }
            alertDialog.dismiss();
            return;
        }
        showAlert(getString(R.string.common_alert_unableToConnectToNw), false);
    }

    /* access modifiers changed from: package-private */
    public void moveToManageUserFragment() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Values.FROM_DEVICE_SETTING, true);
        bundle.putLong(Values.RAC_ID, this.iduDetailsModel.getId().longValue());
        bundle.putString(Values.RAC_NAME, this.iduDetailsModel.getName());
        this.navController.navigate((int) R.id.action_deviceDetailsFragment_to_manageOwnersByRacIdFragment, bundle);
    }

    public void getDeviceName(String str) {
        this.iduRenameDialog.newIduNameField.removeTextChangedListener(this.searchTextWatcher);
        this.iduRenameDialog.newIduNameField.setText(str);
        this.iduRenameDialog.newIduNameField.addTextChangedListener(this.searchTextWatcher);
        this.iduRenameDialog.cardView.setVisibility(8);
    }

    private boolean isContain(String str) {
        for (IduDetailsModel next : this.detailedIduModelsList) {
            if (!this.iduDetailsModel.getVendorThingId().equals(next.getVendorThingId()) && str.equalsIgnoreCase(next.getName())) {
                return false;
            }
        }
        return true;
    }

    private void showAlert(String str, boolean z) {
        this.singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        this.singleChoiceDialog.setMessageString(str);
        this.singleChoiceDialog.setCancelable(false);
        this.singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new DeviceDetailsFragmentNew$$ExternalSyntheticLambda6(this, z));
        if (!this.singleChoiceDialog.isShowing()) {
            this.singleChoiceDialog.show();
        }
    }

    /* renamed from: lambda$showAlert$16$com-jch-racWiFi-userOnboarding-view-viewImpl-DeviceDetailsFragmentNew */
    public /* synthetic */ boolean mo33517xf77bbd00(boolean z, Dialog dialog, View view) {
        if (z) {
            showPleaseWaitDialog();
            this.toRemoveRac = true;
            this.mFragmentToActivityCallback.refreshAllIduStates();
        }
        return true;
    }
}
