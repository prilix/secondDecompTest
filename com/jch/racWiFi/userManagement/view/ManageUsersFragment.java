package com.jch.racWiFi.userManagement.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.ManageUsersFrameBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.FamilyMembersList;
import com.jch.racWiFi.userManagement.model.FamilyMembersMap;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.presenter.ManageUserFragmentPresenter;
import com.jch_hitachi.aircloudglobal.R;
import java.util.List;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class ManageUsersFragment extends GenericFragment implements ManageUserFragmentPresenter.IManageUserFragmentPresenter {
    private boolean isFromDeviceDetailsFragment;
    private ManageUsersFrameBinding mBinding;
    private ManageUserFragmentPresenter mManageUserFragmentPresenter;
    private UsersListRecyclerViewAdapter mUsersListRecyclerViewAdapter;
    private long racId;

    public void onNetworkCallSuccess() {
    }

    public static ManageUsersFragment newInstance() {
        ManageUsersFragment manageUsersFragment = new ManageUsersFragment();
        manageUsersFragment.setNewBundleAsArgument();
        return manageUsersFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (ManageUsersFrameBinding) DataBindingUtil.inflate(layoutInflater, R.layout.manage_users_frame, viewGroup, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.isFromDeviceDetailsFragment = arguments.getBoolean(Values.FROM_DEVICE_SETTING);
            this.racId = arguments.getLong(Values.RAC_ID);
        }
        this.mManageUserFragmentPresenter = new ManageUserFragmentPresenter(this);
        logEvent(Screens.SCREENS.name(), 3);
        logEvents(Events.MANAGE_USER_FREQUENCY.name(), 0);
        return this.mBinding.getRoot();
    }

    private void populateMembersList(List<UserFamilyMemberModels.UserFamilyMemberInfo> list) {
        this.mUsersListRecyclerViewAdapter = new UsersListRecyclerViewAdapter(getActivity(), list, this.mFragmentToActivityCallback.getNavController(), this.isFromDeviceDetailsFragment, this.racId);
        this.mBinding.includedLayout.recyclerViewMangeUsers.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mBinding.includedLayout.recyclerViewMangeUsers.setAdapter(this.mUsersListRecyclerViewAdapter);
    }

    public void onResume() {
        super.onResume();
        if (FamilyGroupList.getCurrentFamily().familyId != -1) {
            FamilyMembersList familyMembersList = (FamilyMembersList) this.mFragmentToActivityCallback.getFamilyMembersMap().get(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId));
            if (familyMembersList == null || familyMembersList.isRequiredToRefreshList()) {
                showPleaseWaitDialog();
                this.mManageUserFragmentPresenter.getFamilyMemberList(FamilyGroupList.getCurrentFamily().familyId);
                return;
            }
            populateMembersList(familyMembersList);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mManageUserFragmentPresenter.removeCallbacks();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.includedLayout.layoutAddMembersManageUsers.setOnClickListener(new ManageUsersFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.includedLayout.layoutManagePermissions.setOnClickListener(new ManageUsersFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.imageButtonMenu.setOnClickListener(new ManageUsersFragment$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-userManagement-view-ManageUsersFragment */
    public /* synthetic */ void mo32908x3188944b(View view) {
        onClickAddUsers();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-userManagement-view-ManageUsersFragment */
    public /* synthetic */ void mo32909x571c9d4c(View view) {
        onClickManagePermissions();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-userManagement-view-ManageUsersFragment */
    public /* synthetic */ void mo32910x7cb0a64d(View view) {
        onClickMenu();
    }

    /* access modifiers changed from: package-private */
    public void onClickAddUsers() {
        Bundle bundle = new Bundle();
        bundle.putInt(Values.NAVIGATING_FROM, 1);
        UsersListRecyclerViewAdapter usersListRecyclerViewAdapter = this.mUsersListRecyclerViewAdapter;
        bundle.putInt(Values.FAMILY_MEMBER_COUNT, usersListRecyclerViewAdapter != null ? usersListRecyclerViewAdapter.getUserInformationList().size() : 0);
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_manageUserfragment_to_inviteUserFragmentNewVD, bundle);
    }

    /* access modifiers changed from: package-private */
    public void onClickManagePermissions() {
        Bundle bundle = new Bundle();
        bundle.putInt(Values.PERMISSION_MODE_KEY, -1);
        bundle.putInt(Values.FROM_MANAGE_USERS, 0);
        bundle.putInt(NavigationTransitionKeyValues.NAVIGATION_FROM, 1012);
        bundle.putInt(Values.PERMISSION_MODE_KEY, -1);
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_manageUsersFragment_to_userPermissionsManageUsersFragment, bundle);
    }

    public void onClickMenu() {
        this.iDrawerMenuFunctions.openMenuDrawer();
    }

    public void onUserFamilyListAvailable(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        FamilyMembersMap familyMembersMap = this.mFragmentToActivityCallback.getFamilyMembersMap();
        FamilyMembersList familyMembersList = (FamilyMembersList) familyMembersMap.get(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId));
        if (familyMembersList != null) {
            familyMembersList.updateList(userFamilyMemberSuccessResponse.userFamilyMemberInfo);
        } else {
            FamilyMembersList familyMembersList2 = new FamilyMembersList();
            familyMembersList2.addAll(userFamilyMemberSuccessResponse.userFamilyMemberInfo);
            familyMembersMap.put(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId), familyMembersList2);
        }
        populateMembersList((List) familyMembersMap.get(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId)));
        dismissPleaseWaitDialog();
    }

    public void onUserFamilyListFetchFailure(UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse) {
        dismissPleaseWaitDialog();
        if (userFamilyMemberFailureResponse.code != null) {
            String str = userFamilyMemberFailureResponse.code;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1995143803:
                    if (str.equals("NFE002")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1995143796:
                    if (str.equals("NFE009")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1995143771:
                    if (str.equals("NFE013")) {
                        c = 2;
                        break;
                    }
                    break;
                case 2066172683:
                    if (str.equals(UserFamilyMemberModels.UserFamilyMemberFailureResponse.ROLE_OF_CREATOR_CANNOT_BE_CHANGED)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    showSingleChoicePopUp(getString(R.string.errorCode_alert_NFE002));
                    return;
                case 1:
                    showSingleChoicePopUp(getString(R.string.errorCode_alert_NFE009));
                    return;
                case 2:
                    showSingleChoicePopUp(getString(R.string.errorCode_alert_NFE013));
                    return;
                case 3:
                    showSingleChoicePopUp(getString(R.string.errorCode_alert_FAE005));
                    return;
                default:
                    showSingleChoicePopUp(getString(R.string.myAcc_alert_unableToFetchFamilyMembersList));
                    return;
            }
        } else {
            callRefreshApi(userFamilyMemberFailureResponse.statusCode);
        }
    }

    private void callRefreshApi(int i) {
        if (i == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    ManageUsersFragment.this.callRefresh();
                }
            }, false);
            return;
        }
        showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
    }

    /* access modifiers changed from: private */
    public void callRefresh() {
        dismissPleaseWaitDialog();
        this.mManageUserFragmentPresenter.getFamilyMemberList(FamilyGroupList.getCurrentFamily().familyId);
    }

    public void onNetworkCallFailure(Throwable th) {
        if (!DemoModeModel.DEMO_MODE_ON) {
            showSingleChoicePopUp(getString(R.string.common_alert_unableToConnectToNw));
        }
        dismissPleaseWaitDialog();
    }

    public void serverException() {
        showSingleChoicePopUp(getString(R.string.common_alert_somethingWentWrong));
        dismissPleaseWaitDialog();
    }

    private static class UsersListRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
        /* access modifiers changed from: private */
        public final boolean isFromDeviceDetailsFrag;
        private final Context mContext;
        /* access modifiers changed from: private */
        public final NavController mNavController;
        private final List<UserFamilyMemberModels.UserFamilyMemberInfo> mUserInformationList;
        /* access modifiers changed from: private */
        public final long racId;

        public List<UserFamilyMemberModels.UserFamilyMemberInfo> getUserInformationList() {
            return this.mUserInformationList;
        }

        public UsersListRecyclerViewAdapter(Context context, List<UserFamilyMemberModels.UserFamilyMemberInfo> list, NavController navController, boolean z, long j) {
            this.mContext = context;
            this.mUserInformationList = list;
            this.mNavController = navController;
            this.isFromDeviceDetailsFrag = z;
            this.racId = j;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recycler_view_items_manage_users, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.bind(this.mUserInformationList.get(i));
        }

        public int getItemCount() {
            return this.mUserInformationList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView name;
            private final ConstraintLayout outerLayout;
            private final CircleImageView profilePic;
            private final TextView roleType;

            public ViewHolder(View view) {
                super(view);
                ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.manage_user_recycler_view_item);
                this.outerLayout = constraintLayout;
                constraintLayout.setOnClickListener(new C2481x1265538e(this));
                this.name = (TextView) view.findViewById(R.id.text_view_user_name_manage_user);
                this.roleType = (TextView) view.findViewById(R.id.text_view_user_type_manage_user);
                this.profilePic = (CircleImageView) view.findViewById(R.id.image_view_user_profile_manage_user);
            }

            /* renamed from: lambda$new$0$com-jch-racWiFi-userManagement-view-ManageUsersFragment$UsersListRecyclerViewAdapter$ViewHolder */
            public /* synthetic */ void mo32917xc9817e13(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(UserFamilyMemberModels.UserFamilyMemberInfo.USER_FAMILY_MEMBER, (UserFamilyMemberModels.UserFamilyMemberInfo) view.getTag());
                bundle.putBoolean(Values.FROM_DEVICE_SETTING, UsersListRecyclerViewAdapter.this.isFromDeviceDetailsFrag);
                bundle.putLong(Values.RAC_ID, UsersListRecyclerViewAdapter.this.racId);
                UsersListRecyclerViewAdapter.this.mNavController.navigate((int) R.id.action_manageUsersFragment_to_editUserFragment, bundle);
            }

            public void bind(UserFamilyMemberModels.UserFamilyMemberInfo userFamilyMemberInfo) {
                this.outerLayout.setTag(userFamilyMemberInfo);
                TextView textView = this.name;
                textView.setText(userFamilyMemberInfo.firstName + " " + userFamilyMemberInfo.lastName);
                String name2 = userFamilyMemberInfo.role.getName();
                name2.hashCode();
                char c = 65535;
                switch (name2.hashCode()) {
                    case -2024440166:
                        if (name2.equals("MEMBER")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 64093436:
                        if (name2.equals("CHILD")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 75627155:
                        if (name2.equals("OWNER")) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        this.roleType.setText(R.string.common_lbl_member);
                        break;
                    case 1:
                        this.roleType.setText(R.string.common_lbl_child);
                        break;
                    case 2:
                        this.roleType.setText(R.string.common_lbl_owner);
                        break;
                }
                ProfilePicture.loadIntoImageView(this.profilePic, userFamilyMemberInfo.profilePicture);
            }
        }
    }

    private void showSingleChoicePopUp(String str) {
        if (getActivity() != null) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(str);
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new ManageUsersFragment$$ExternalSyntheticLambda3(singleChoiceDialog));
            singleChoiceDialog.show();
        }
    }
}
