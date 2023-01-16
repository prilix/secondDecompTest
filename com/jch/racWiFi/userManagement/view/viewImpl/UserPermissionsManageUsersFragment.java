package com.jch.racWiFi.userManagement.view.viewImpl;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.google.gson.Gson;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.Utils.BackgroundExecutor;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.userManagement.model.PermissionModel;
import com.jch.racWiFi.userManagement.model.dataProvider.PermissionFactory;
import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import com.jch.racWiFi.userManagement.model.dto.PermissionErrorData;
import com.jch.racWiFi.userManagement.presenter.PermissionPresenter;
import com.jch.racWiFi.userManagement.presenter.presenterImpl.PermissionPresenterImpl;
import com.jch.racWiFi.userManagement.view.IDevicePermissionView;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class UserPermissionsManageUsersFragment extends GenericFragment implements IDevicePermissionView {
    private static final boolean LIST_VIEW = true;
    @BindView(2131362195)
    TriStateCheckbox cbAllChildPermission;
    @BindView(2131362197)
    TriStateCheckbox cbAllMemberPermission;
    private boolean isFromDeviceDetails = false;
    @BindView(2131363341)
    LinearLayout linearChild;
    @BindView(2131363350)
    LinearLayout linearMember;
    /* access modifiers changed from: private */
    public PermissionsListViewAdapter listViewAdapter;
    @BindView(2131363650)
    ListView mListViewUserPermissions;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131363649)
    RecyclerView mRecyclerViewUserPermissions;
    @BindView(2131364818)
    ImageButton mSave;
    @BindView(2131364118)
    TextView mTitle;
    private Unbinder mUnbinder;
    private List<PermissionModel> permissionModels = new ArrayList();
    private PermissionPresenter permissionPresenter = new PermissionPresenterImpl(this);
    private PermissionViewModel permissionViewModel;
    @BindView(2131364817)
    TextView tvAllPermissionsText;
    @BindView(2131364813)
    TextView tvDeviceName;
    @BindView(2131364337)
    TextView tvMember;

    static /* synthetic */ boolean lambda$onClickSave$1(Dialog dialog, View view) {
        return true;
    }

    public static UserPermissionsManageUsersFragment newInstance() {
        UserPermissionsManageUsersFragment userPermissionsManageUsersFragment = new UserPermissionsManageUsersFragment();
        userPermissionsManageUsersFragment.setNewBundleAsArgument();
        return userPermissionsManageUsersFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.permissionViewModel = new PermissionViewModel();
        UserPermissions.getInstance().updatePermissionString(requireActivity());
        View inflate = layoutInflater.inflate(R.layout.user_permissions_listvew_frame, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        int i = (arguments == null || !arguments.containsKey(NavigationTransitionKeyValues.NAVIGATION_FROM)) ? -1 : arguments.getInt(NavigationTransitionKeyValues.NAVIGATION_FROM);
        if (i == 1014) {
            this.isFromDeviceDetails = true;
            this.permissionViewModel.iduName = arguments.getString(Values.IDU_NAME_KEY, "");
            this.permissionViewModel.mode = arguments.getInt(Values.PERMISSION_MODE_KEY, 3);
            this.permissionViewModel.iduId = arguments.getInt(Values.IDU_ID_KEY, -1);
            this.mTitle.setText(R.string.manageUser_lbl_ACSettings);
        } else if (i == 1012) {
            this.mTitle.setText(R.string.manageUser_btn_managePermissions);
            this.permissionViewModel.mode = -1;
            this.permissionViewModel.iduId = -1;
        }
        getPermissionDatas();
        PermissionsListViewAdapter permissionsListViewAdapter = new PermissionsListViewAdapter(requireActivity(), UserInfo.getCurrentUserInfo(getCoreActivity()).role[0].getLevel(), this.permissionPresenter, this.permissionViewModel, new ArrayList());
        this.listViewAdapter = permissionsListViewAdapter;
        this.mListViewUserPermissions.setAdapter(permissionsListViewAdapter);
        this.permissionViewModel.setMode(this.tvDeviceName, getString(R.string.manageUser_lbl_allACs));
        this.linearMember.setVisibility(0);
        this.linearMember.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserPermissionsManageUsersFragment.this.confirmationDialogForAllMemberPermission();
            }
        });
        this.linearChild.setVisibility(0);
        this.linearChild.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserPermissionsManageUsersFragment.this.confirmationDialogForAllChildPermission();
            }
        });
        if (UserInfo.getCurrentUserInfo(getCoreActivity()).role[0].getLevel() == 2) {
            this.tvMember.setVisibility(4);
            this.cbAllMemberPermission.setVisibility(4);
        }
    }

    /* access modifiers changed from: private */
    public void getPermissionDatas() {
        if (NetworkConnectivity.isNetworkAvailable(requireActivity().getApplicationContext())) {
            showPleaseWaitDialog();
            this.permissionPresenter.performInitTask(this, this.isFromDeviceDetails, this.permissionViewModel);
            return;
        }
        showAlert(getString(R.string.common_alert_unableToConnectToNw), true);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mUnbinder.unbind();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131364818})
    public void onClickSave(ImageButton imageButton) {
        List<PermissionModel> permissionModels2 = this.listViewAdapter.getPermissionModels();
        boolean[] memberChildPermissionChanged = this.listViewAdapter.getMemberChildPermissionChanged();
        if (memberChildPermissionChanged[0] || memberChildPermissionChanged[1]) {
            ChangePermissionConfirmationDialog changePermissionConfirmationDialog = new ChangePermissionConfirmationDialog(requireActivity());
            changePermissionConfirmationDialog.setParentView(this.mParent);
            changePermissionConfirmationDialog.setPositiveButton(getString(R.string.common_btn_yes), new UserPermissionsManageUsersFragment$$ExternalSyntheticLambda0(this, permissionModels2));
            changePermissionConfirmationDialog.setNegativeButton(getString(R.string.common_btn_no), UserPermissionsManageUsersFragment$$ExternalSyntheticLambda5.INSTANCE);
            if (!memberChildPermissionChanged[0]) {
                changePermissionConfirmationDialog.hideMemberPermissionUpdated();
            }
            if (!memberChildPermissionChanged[1]) {
                changePermissionConfirmationDialog.hideChildPermissionUpdated();
            }
            changePermissionConfirmationDialog.show();
            return;
        }
        showAlert(getString(R.string.userPermission_alert_noChangeInPermission), false);
    }

    /* renamed from: lambda$onClickSave$0$com-jch-racWiFi-userManagement-view-viewImpl-UserPermissionsManageUsersFragment */
    public /* synthetic */ boolean mo33307xe154e79c(List list, Dialog dialog, View view) {
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            showPleaseWaitDialog();
            this.permissionPresenter.requestSave(getViewLifecycleOwner(), list, this.permissionViewModel);
            return true;
        }
        showAlert(getString(R.string.common_alert_unableToConnectToNw), false);
        return true;
    }

    @OnClick({2131363093})
    public void onClickBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: private */
    public void confirmationDialogForAllMemberPermission() {
        boolean booleanValue = this.cbAllMemberPermission.isChecked().booleanValue();
        String string = getResources().getString(!booleanValue ? R.string.userPermission_alert_enableAccessMember : R.string.userPermission_alert_disableAccessMember);
        String string2 = getResources().getString(!booleanValue ? R.string.userPermission_alert_enableAccessMemberDesc : R.string.userPermission_alert_disableAccessMemberDesc);
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(requireActivity());
        confirmationDialog.setTitleString(string);
        confirmationDialog.setMessageString(string2);
        confirmationDialog.setCancelable(false);
        confirmationDialog.setPositiveButton(requireActivity().getString(R.string.common_btn_yes), new UserPermissionsManageUsersFragment$$ExternalSyntheticLambda2(this, booleanValue));
        confirmationDialog.setNegativeButton(requireActivity().getString(R.string.common_btn_no), UserPermissionsManageUsersFragment$$ExternalSyntheticLambda4.INSTANCE);
        confirmationDialog.setParentView(this.mParent);
        confirmationDialog.show();
    }

    /* renamed from: lambda$confirmationDialogForAllMemberPermission$2$com-jch-racWiFi-userManagement-view-viewImpl-UserPermissionsManageUsersFragment */
    public /* synthetic */ boolean mo33306x80ae7f30(boolean z, Dialog dialog, View view) {
        this.cbAllMemberPermission.setChecked(Boolean.valueOf(!z));
        return true;
    }

    /* access modifiers changed from: private */
    public void confirmationDialogForAllChildPermission() {
        boolean booleanValue = this.cbAllChildPermission.isChecked().booleanValue();
        String string = getResources().getString(!booleanValue ? R.string.userPermission_alert_enableAccessChild : R.string.userPermission_alert_disableAccessChild);
        String string2 = getResources().getString(!booleanValue ? R.string.userPermission_alert_enableAccessChildDesc : R.string.userPermission_alert_disableAccessChildDesc);
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(requireActivity());
        confirmationDialog.setTitleString(string);
        confirmationDialog.setMessageString(string2);
        confirmationDialog.setCancelable(false);
        confirmationDialog.setPositiveButton(requireActivity().getString(R.string.common_btn_yes), new UserPermissionsManageUsersFragment$$ExternalSyntheticLambda1(this, booleanValue));
        confirmationDialog.setNegativeButton(requireActivity().getString(R.string.common_btn_no), UserPermissionsManageUsersFragment$$ExternalSyntheticLambda3.INSTANCE);
        confirmationDialog.setParentView(this.mParent);
        confirmationDialog.show();
    }

    /* renamed from: lambda$confirmationDialogForAllChildPermission$4$com-jch-racWiFi-userManagement-view-viewImpl-UserPermissionsManageUsersFragment */
    public /* synthetic */ boolean mo33305xfdacb410(boolean z, Dialog dialog, View view) {
        this.cbAllChildPermission.setChecked(Boolean.valueOf(!z));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void onAllMembersCheckChanged(CompoundButton compoundButton, boolean z) {
        this.listViewAdapter.isALLFunctionClicked = true;
        this.listViewAdapter.checkAllItemsWithRoleLevel(2, z);
    }

    /* access modifiers changed from: package-private */
    public void onAllChildCheckChanged(CompoundButton compoundButton, boolean z) {
        this.listViewAdapter.isALLFunctionClicked = true;
        this.listViewAdapter.checkAllItemsWithRoleLevel(3, z);
    }

    public void onAllCheckedStatusEvaluated(Boolean[] boolArr) {
        TriStateCheckbox triStateCheckbox = this.cbAllMemberPermission;
        if (triStateCheckbox != null && this.cbAllChildPermission != null) {
            triStateCheckbox.setChecked(boolArr[1]);
            this.cbAllChildPermission.setChecked(boolArr[2]);
        }
    }

    public void fetchPermissionResponseDatas(Response<AllPermissionDataDto> response) {
        if (response == null) {
            dismissPleaseWaitDialog();
            showAlert(getString(R.string.common_alert_couldnotFetchPermsData), true);
        } else if (response.body() != null) {
            if (response.isSuccessful()) {
                populatePermissionDatas(response.body());
            }
        } else if (response.errorBody() != null) {
            dismissPleaseWaitDialog();
            handlePermissionErrorDatas(response.code(), (PermissionErrorData) new Gson().fromJson(response.errorBody().charStream(), PermissionErrorData.class), getString(R.string.common_alert_couldnotFetchPermsData));
        } else {
            dismissPleaseWaitDialog();
            showAlert(getString(R.string.common_alert_couldnotFetchPermsData), true);
        }
    }

    private void populatePermissionDatas(AllPermissionDataDto allPermissionDataDto) {
        final List<PermissionModel> cookUserPermission = PermissionFactory.getInstance().cookUserPermission(allPermissionDataDto, Integer.valueOf(this.permissionViewModel.iduId));
        this.permissionPresenter.evaluateAllCheckedForRoles(cookUserPermission);
        new Handler().postDelayed(new Runnable() {
            /* renamed from: lambda$run$0$com-jch-racWiFi-userManagement-view-viewImpl-UserPermissionsManageUsersFragment$3 */
            public /* synthetic */ void mo33314xce0ab230(List list) {
                UserPermissionsManageUsersFragment.this.listViewAdapter.updateData(list);
            }

            public void run() {
                BackgroundExecutor.postOnMainThread(new UserPermissionsManageUsersFragment$3$$ExternalSyntheticLambda2(this, cookUserPermission));
                UserPermissionsManageUsersFragment.this.cbAllChildPermission.setOnCheckedChangeListener(new UserPermissionsManageUsersFragment$3$$ExternalSyntheticLambda0(UserPermissionsManageUsersFragment.this));
                UserPermissionsManageUsersFragment.this.cbAllMemberPermission.setOnCheckedChangeListener(new UserPermissionsManageUsersFragment$3$$ExternalSyntheticLambda1(UserPermissionsManageUsersFragment.this));
                UserPermissionsManageUsersFragment.this.dismissPleaseWaitDialog();
            }
        }, 1000);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r5.equals("NFE002") == false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handlePermissionErrorDatas(int r5, com.jch.racWiFi.userManagement.model.dto.PermissionErrorData r6, java.lang.String r7) {
        /*
            r4 = this;
            r0 = 0
            r1 = 401(0x191, float:5.62E-43)
            if (r5 == r1) goto L_0x0086
            if (r6 == 0) goto L_0x0095
            java.lang.String r5 = r6.getCode()
            r5.hashCode()
            r1 = -1
            int r2 = r5.hashCode()
            r3 = 1
            switch(r2) {
                case -1995143803: goto L_0x003a;
                case -1995143802: goto L_0x002f;
                case -1995143796: goto L_0x0024;
                case 2066172685: goto L_0x0019;
                default: goto L_0x0017;
            }
        L_0x0017:
            r0 = -1
            goto L_0x0043
        L_0x0019:
            java.lang.String r0 = "FAE007"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0022
            goto L_0x0017
        L_0x0022:
            r0 = 3
            goto L_0x0043
        L_0x0024:
            java.lang.String r0 = "NFE009"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x002d
            goto L_0x0017
        L_0x002d:
            r0 = 2
            goto L_0x0043
        L_0x002f:
            java.lang.String r0 = "NFE003"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0038
            goto L_0x0017
        L_0x0038:
            r0 = 1
            goto L_0x0043
        L_0x003a:
            java.lang.String r2 = "NFE002"
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L_0x0043
            goto L_0x0017
        L_0x0043:
            switch(r0) {
                case 0: goto L_0x007b;
                case 1: goto L_0x0070;
                case 2: goto L_0x0065;
                case 3: goto L_0x005a;
                default: goto L_0x0046;
            }
        L_0x0046:
            java.lang.String r5 = r6.getMessage()
            if (r5 == 0) goto L_0x0056
            boolean r6 = r5.isEmpty()
            if (r6 != 0) goto L_0x0056
            r4.showAlert(r5, r3)
            goto L_0x0095
        L_0x0056:
            r4.showAlert(r7, r3)
            goto L_0x0095
        L_0x005a:
            r5 = 2131952821(0x7f1304b5, float:1.9542096E38)
            java.lang.String r5 = r4.getString(r5)
            r4.showAlert(r5, r3)
            goto L_0x0095
        L_0x0065:
            r5 = 2131952833(0x7f1304c1, float:1.954212E38)
            java.lang.String r5 = r4.getString(r5)
            r4.showAlert(r5, r3)
            goto L_0x0095
        L_0x0070:
            r5 = 2131952827(0x7f1304bb, float:1.9542108E38)
            java.lang.String r5 = r4.getString(r5)
            r4.showAlert(r5, r3)
            goto L_0x0095
        L_0x007b:
            r5 = 2131952826(0x7f1304ba, float:1.9542106E38)
            java.lang.String r5 = r4.getString(r5)
            r4.showAlert(r5, r3)
            goto L_0x0095
        L_0x0086:
            r4.showPleaseWaitDialog()
            com.jch.racWiFi.CoreActivity r5 = r4.getCoreActivity()
            com.jch.racWiFi.userManagement.view.viewImpl.UserPermissionsManageUsersFragment$4 r6 = new com.jch.racWiFi.userManagement.view.viewImpl.UserPermissionsManageUsersFragment$4
            r6.<init>()
            r5.refreshToken(r6, r0)
        L_0x0095:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.userManagement.view.viewImpl.UserPermissionsManageUsersFragment.handlePermissionErrorDatas(int, com.jch.racWiFi.userManagement.model.dto.PermissionErrorData, java.lang.String):void");
    }

    public void savePermissionResponseDatas(Response<ResponseBody> response) {
        dismissPleaseWaitDialog();
        if (response == null) {
            showAlert(getString(R.string.userPermission_alert_unableToSaveChanges), false);
        } else if (response.isSuccessful()) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (response.errorBody() != null) {
            handlePermissionErrorDatas(response.code(), (PermissionErrorData) new Gson().fromJson(response.errorBody().charStream(), PermissionErrorData.class), getString(R.string.userPermission_alert_unableToSaveChanges));
        } else {
            showAlert(getString(R.string.userPermission_alert_unableToSaveChanges), false);
        }
    }

    public static class PermissionViewModel {
        public int iduId;
        public String iduName;
        public int mode;

        /* access modifiers changed from: private */
        public void setMode(TextView textView, String str) {
            int i = this.mode;
            if (i != 3) {
                if (i == -1) {
                    textView.setText(str);
                } else {
                    textView.setText(this.iduName);
                }
            }
        }
    }

    private void showAlert(String str, final boolean z) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                if (!z || !UserPermissionsManageUsersFragment.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) || UserPermissionsManageUsersFragment.this.mFragmentToActivityCallback.getNavController() == null) {
                    return true;
                }
                UserPermissionsManageUsersFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
                return true;
            }
        });
        singleChoiceDialog.show();
    }
}
