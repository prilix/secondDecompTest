package com.jch.racWiFi.userManagement.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.customViews.customDialogs.RemoveUserDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.userManagement.model.DetachMeFromFamilyDataModel;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.FamilyGroupsModels;
import com.jch.racWiFi.userManagement.model.FamilyMembersList;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.presenter.DetachMeFromFamilyPresenter;
import com.jch.racWiFi.userManagement.presenter.GetFamilyGroupPresenter;
import com.jch.racWiFi.userManagement.presenter.ManageUserFragmentPresenter;
import com.jch.racWiFi.userManagement.view.viewImpl.UserRecyclerItemModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class MyAccountManageHomesFragment extends GenericFragment implements DetachMeFromFamilyPresenter.IDetachFromFamilyPresenter, ManageUserFragmentPresenter.IManageUserFragmentPresenter, GetFamilyGroupPresenter.IGetFamilyGroupPresenterInterface {
    private List<UserRecyclerItemModel> familyGroupMenuItemsUsers = new ArrayList();
    /* access modifiers changed from: private */
    public int familyIdToBeDetached = -1;
    private Observer<Boolean> forceRefreshObserver = new Observer<Boolean>() {
        public void onChanged(Boolean bool) {
            if (MyAccountManageHomesFragment.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                MyAccountManageHomesFragment.this.dismissPleaseWaitDialog();
            }
        }
    };
    private GetFamilyGroupPresenter getFamilyGroupPresenter;
    /* access modifiers changed from: private */
    public DetachMeFromFamilyPresenter mDetachMeFromFamilyPresenter;
    /* access modifiers changed from: private */
    public ManageUserFragmentPresenter mManageUserFragmentPresenter;
    private NavController navController;
    /* access modifiers changed from: private */
    public RemoveUserDialog removeHomeDialog;
    /* access modifiers changed from: private */
    public RemoveUserDialog removeHomeDialog2;
    /* access modifiers changed from: private */
    public int selectedFamilyId = -1;
    private Unbinder unbinder;
    private UserRecyclerViewAdapter userRecyclerViewAdapter;

    class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder> {
        Context context;
        List<UserRecyclerItemModel> mUserRecyclerItemModelsList;

        public class UserViewHolder_ViewBinding implements Unbinder {
            private UserViewHolder target;

            public UserViewHolder_ViewBinding(UserViewHolder userViewHolder, View view) {
                this.target = userViewHolder;
                userViewHolder.mProfileCircleImageView = (CircleImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_user_profile_manage_user, "field 'mProfileCircleImageView'", CircleImageView.class);
                userViewHolder.mHomeNameTV = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_user_name_manage_user, "field 'mHomeNameTV'", TextView.class);
                userViewHolder.mRoleNameTV = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_user_type_manage_user, "field 'mRoleNameTV'", TextView.class);
                userViewHolder.mCloseIV = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_right_swipe, "field 'mCloseIV'", ImageView.class);
            }

            public void unbind() {
                UserViewHolder userViewHolder = this.target;
                if (userViewHolder != null) {
                    this.target = null;
                    userViewHolder.mProfileCircleImageView = null;
                    userViewHolder.mHomeNameTV = null;
                    userViewHolder.mRoleNameTV = null;
                    userViewHolder.mCloseIV = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        UserRecyclerViewAdapter(List list, Context context2) {
            this.mUserRecyclerItemModelsList = list;
            this.context = context2;
        }

        public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new UserViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_manage_homes, viewGroup, false));
        }

        public void onBindViewHolder(UserViewHolder userViewHolder, int i) {
            userViewHolder.bind(this.mUserRecyclerItemModelsList.get(i));
        }

        public int getItemCount() {
            return this.mUserRecyclerItemModelsList.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131362982)
            ImageView mCloseIV;
            @BindView(2131364701)
            TextView mHomeNameTV;
            @BindView(2131363035)
            CircleImageView mProfileCircleImageView;
            @BindView(2131364717)
            TextView mRoleNameTV;

            public UserViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }

            public void bind(final UserRecyclerItemModel userRecyclerItemModel) {
                this.mCloseIV.setTag(userRecyclerItemModel);
                if (userRecyclerItemModel.getFamily().familyId == UserInfo.getCurrentUserInfo(MyAccountManageHomesFragment.this.getCoreActivity()).familyId) {
                    this.mHomeNameTV.setText(MyAccountManageHomesFragment.this.getString(R.string.manageUser_lbl_myHome));
                } else {
                    this.mHomeNameTV.setText(userRecyclerItemModel.getName());
                }
                if (userRecyclerItemModel.getFamily().role.isChild()) {
                    this.mRoleNameTV.setText(MyAccountManageHomesFragment.this.getString(R.string.common_lbl_child));
                } else if (userRecyclerItemModel.getFamily().role.isMember()) {
                    this.mRoleNameTV.setText(MyAccountManageHomesFragment.this.getString(R.string.common_lbl_member));
                } else {
                    this.mRoleNameTV.setText(MyAccountManageHomesFragment.this.getString(R.string.common_lbl_owner));
                }
                ProfilePicture.loadIntoImageView(this.mProfileCircleImageView, userRecyclerItemModel.getFamily().creatorProfilePic);
                this.mCloseIV.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        MyAccountManageHomesFragment.this.familyIdToBeDetached = userRecyclerItemModel.getFamily().familyId;
                        MyAccountManageHomesFragment.this.mFragmentToActivityCallback.getFamilyGroupList().setRequiredToRefreshList(true);
                        if (UserInfo.getCurrentUserInfo(MyAccountManageHomesFragment.this.getCoreActivity()).familyId == userRecyclerItemModel.getFamily().familyId) {
                            MyAccountManageHomesFragment.this.removeHomeDialog2 = new RemoveUserDialog(UserRecyclerViewAdapter.this.context);
                            if (userRecyclerItemModel.getFamily().role.isChild()) {
                                MyAccountManageHomesFragment.this.removeHomeDialog2.setText(MyAccountManageHomesFragment.this.getString(R.string.manageUser_lbl_myHome), MyAccountManageHomesFragment.this.getString(R.string.common_lbl_child));
                            } else if (userRecyclerItemModel.getFamily().role.isMember()) {
                                MyAccountManageHomesFragment.this.removeHomeDialog2.setText(MyAccountManageHomesFragment.this.getString(R.string.manageUser_lbl_myHome), MyAccountManageHomesFragment.this.getString(R.string.common_lbl_member));
                            } else {
                                MyAccountManageHomesFragment.this.removeHomeDialog2.setText(MyAccountManageHomesFragment.this.getString(R.string.manageUser_lbl_myHome), MyAccountManageHomesFragment.this.getString(R.string.common_lbl_owner));
                            }
                            MyAccountManageHomesFragment.this.removeHomeDialog2.setParentView((View) null);
                            MyAccountManageHomesFragment.this.removeHomeDialog2.yseButton.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    MyAccountManageHomesFragment.this.removeHomeDialog2.dismiss();
                                    MyAccountManageHomesFragment.this.showPleaseWaitDialog();
                                    MyAccountManageHomesFragment.this.selectedFamilyId = userRecyclerItemModel.getFamily().familyId;
                                    MyAccountManageHomesFragment.this.mManageUserFragmentPresenter.getFamilyMemberList(MyAccountManageHomesFragment.this.selectedFamilyId);
                                }
                            });
                            MyAccountManageHomesFragment.this.removeHomeDialog2.show();
                            return;
                        }
                        MyAccountManageHomesFragment.this.removeHomeDialog = new RemoveUserDialog(UserRecyclerViewAdapter.this.context);
                        if (userRecyclerItemModel.getFamily().role.isChild()) {
                            MyAccountManageHomesFragment.this.removeHomeDialog.setText(userRecyclerItemModel.getName(), MyAccountManageHomesFragment.this.getString(R.string.common_lbl_child));
                        } else if (userRecyclerItemModel.getFamily().role.isMember()) {
                            MyAccountManageHomesFragment.this.removeHomeDialog.setText(userRecyclerItemModel.getName(), MyAccountManageHomesFragment.this.getString(R.string.common_lbl_member));
                        } else {
                            MyAccountManageHomesFragment.this.removeHomeDialog.setText(userRecyclerItemModel.getName(), MyAccountManageHomesFragment.this.getString(R.string.common_lbl_owner));
                        }
                        MyAccountManageHomesFragment.this.removeHomeDialog.setParentView((View) null);
                        MyAccountManageHomesFragment.this.removeHomeDialog.yseButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                MyAccountManageHomesFragment.this.showPleaseWaitDialog();
                                MyAccountManageHomesFragment.this.removeHomeDialog.dismiss();
                                MyAccountManageHomesFragment.this.selectedFamilyId = userRecyclerItemModel.getFamily().familyId;
                                MyAccountManageHomesFragment.this.mDetachMeFromFamilyPresenter.detachFromFamily(MyAccountManageHomesFragment.this.selectedFamilyId);
                            }
                        });
                        MyAccountManageHomesFragment.this.removeHomeDialog.show();
                    }
                });
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_account_manage_homes_frame, viewGroup, false);
        this.unbinder = ButterKnife.bind((Object) this, inflate);
        this.navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        this.userRecyclerViewAdapter = new UserRecyclerViewAdapter(this.familyGroupMenuItemsUsers, getActivity());
        this.getFamilyGroupPresenter = new GetFamilyGroupPresenter(this);
        if (this.mFragmentToActivityCallback.getFamilyGroupList().size() != 0 || this.mFragmentToActivityCallback.getFamilyGroupList().isRequiredToRefreshList()) {
            showPleaseWaitDialog();
            this.getFamilyGroupPresenter.getFamilyGroup();
        } else {
            this.familyGroupMenuItemsUsers.clear();
            this.familyGroupMenuItemsUsers.addAll(populateFamilyMembersForRecyclerView(this.mFragmentToActivityCallback.getFamilyGroupList()));
            sortRecyclerViewAlpabatically();
            this.userRecyclerViewAdapter.notifyDataSetChanged();
        }
        DetachMeFromFamilyPresenter detachMeFromFamilyPresenter = new DetachMeFromFamilyPresenter(this);
        this.mDetachMeFromFamilyPresenter = detachMeFromFamilyPresenter;
        detachMeFromFamilyPresenter.registerEventBus();
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view_manage_homes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.userRecyclerViewAdapter);
        this.mManageUserFragmentPresenter = new ManageUserFragmentPresenter(this);
        return inflate;
    }

    private void sortRecyclerViewAlpabatically() {
        Collections.sort(this.familyGroupMenuItemsUsers, new Comparator<UserRecyclerItemModel>() {
            public int compare(UserRecyclerItemModel userRecyclerItemModel, UserRecyclerItemModel userRecyclerItemModel2) {
                if (userRecyclerItemModel.getFamily().familyId == UserInfo.getCurrentUserInfo(MyAccountManageHomesFragment.this.getCoreActivity()).familyId) {
                    return -1;
                }
                if (userRecyclerItemModel2.getFamily().familyId == UserInfo.getCurrentUserInfo(MyAccountManageHomesFragment.this.getCoreActivity()).familyId) {
                    return 1;
                }
                return userRecyclerItemModel.getName().toLowerCase().compareTo(userRecyclerItemModel2.getName().toLowerCase());
            }
        });
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getForceRefreshMutableLiveData().observeSingleEvent(getViewLifecycleOwner(), this.forceRefreshObserver);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getForceRefreshMutableLiveData().removeObserver(this.forceRefreshObserver);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mManageUserFragmentPresenter.removeCallbacks();
        this.getFamilyGroupPresenter.removeCallbacks();
        this.mDetachMeFromFamilyPresenter.removeCallbacks();
        this.unbinder.unbind();
        this.mDetachMeFromFamilyPresenter.unregisterEventBus();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onNetworkCallSuccess() {
        Logger.m47e("NETWORK_FAILURE", "called onNetworkcallSuccess() fragment");
        dismissPleaseWaitDialog();
    }

    public void onNetworkCallFailure(Throwable th) {
        Logger.m47e("NETWORK_FAILURE", "called onNetwork() fragment");
        dismissPleaseWaitDialog();
        GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
    }

    public void serverException() {
        dismissPleaseWaitDialog();
    }

    @OnClick({2131362078})
    public void onClickBackButton() {
        this.navController.navigateUp();
    }

    private List<UserRecyclerItemModel> populateFamilyMembersForRecyclerView(FamilyGroupList familyGroupList) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < familyGroupList.size(); i++) {
            FamilyGroupsModels.FamilyResult familyResult = (FamilyGroupsModels.FamilyResult) familyGroupList.get(i);
            UserRecyclerItemModel userRecyclerItemModel = new UserRecyclerItemModel();
            String str = familyResult.familyName;
            userRecyclerItemModel.setName(getString(R.string.common_lbl_homeColon) + " " + str);
            if (familyResult.familyId == UserInfo.getCurrentUserInfo(getCoreActivity()).familyId) {
                familyResult.creatorProfilePic = UserInfo.getCurrentUserInfo(getCoreActivity()).profilePicture;
                userRecyclerItemModel.setFamily(familyResult);
            }
            userRecyclerItemModel.setFamily(familyResult);
            arrayList.add(userRecyclerItemModel);
        }
        return arrayList;
    }

    public void onDetachFromFamilySuccess(DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelSuccessResponse detachMeFromFamilyDataModelSuccessResponse) {
        dismissPleaseWaitDialog();
        showSingleChiocePopUp(getString(R.string.myAcc_alert_homeRemovedSuccessfully));
        if (this.removeHomeDialog.isShowing()) {
            this.removeHomeDialog.dismiss();
        }
        this.getFamilyGroupPresenter.getFamilyGroup();
    }

    public void onDetachFromFamilyFailure(DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelFailureResponse detachMeFromFamilyDataModelFailureResponse) {
        dismissPleaseWaitDialog();
        if (detachMeFromFamilyDataModelFailureResponse.code != null) {
            String str = detachMeFromFamilyDataModelFailureResponse.code;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1995143803:
                    if (str.equals("NFE002")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1995143797:
                    if (str.equals("NFE008")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1995143796:
                    if (str.equals("NFE009")) {
                        c = 2;
                        break;
                    }
                    break;
                case 2066172684:
                    if (str.equals(DetachMeFromFamilyDataModel.DetachMeFromFamilyDataModelFailureResponse.FAMILY_OWNERSGHIP_NEEDS_TO_BE_TRANSFER)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE002));
                    break;
                case 1:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE008));
                    break;
                case 2:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE009));
                    break;
                case 3:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_FAE006));
                    break;
                default:
                    showSingleChiocePopUp(getString(R.string.myAcc_alert_unableToRemoveHome));
                    break;
            }
        } else {
            callDetachFromFamily(detachMeFromFamilyDataModelFailureResponse.statusCodeValue);
        }
        RemoveUserDialog removeUserDialog = this.removeHomeDialog;
        if (removeUserDialog != null && removeUserDialog.isShowing()) {
            this.removeHomeDialog.dismiss();
        }
    }

    private void callDetachFromFamily(int i) {
        if (i != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                MyAccountManageHomesFragment.this.callDetachFamily();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callDetachFamily() {
        dismissPleaseWaitDialog();
        this.mDetachMeFromFamilyPresenter.detachFromFamily(this.selectedFamilyId);
    }

    public void onUserFamilyListAvailable(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        dismissPleaseWaitDialog();
        if (userFamilyMemberSuccessResponse.userFamilyMemberInfo.size() != 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(userFamilyMemberSuccessResponse.userFamilyMemberInfo);
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(UserRecyclerItemModel.PARCEL_KEY_OF_USER_LIST, new NavArgument.Builder().setDefaultValue(arrayList).build());
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(UserRecyclerItemModel.PARCEL_KEY_OF_USER_ID_TO_DETACH, new NavArgument.Builder().setDefaultValue(Integer.valueOf(this.familyIdToBeDetached)).build());
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_manageHomesFragment_to_myAccountTransferOwnerShipFragment);
            return;
        }
        showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE013));
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
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE002));
                    return;
                case 1:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE009));
                    return;
                case 2:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE013));
                    return;
                case 3:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_FAE005));
                    return;
                default:
                    showSingleChiocePopUp(getString(R.string.myAcc_alert_unableToFetchFamilyMembersList));
                    return;
            }
        } else {
            callRefreshApiFamilyList(userFamilyMemberFailureResponse.statusCode);
        }
    }

    private void callRefreshApiFamilyList(int i) {
        if (i != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                MyAccountManageHomesFragment.this.callRefreshFamilyList();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callRefreshFamilyList() {
        dismissPleaseWaitDialog();
        int i = this.selectedFamilyId;
        if (i != -1) {
            this.mManageUserFragmentPresenter.getFamilyMemberList(i);
        }
    }

    private void callRefreshApiFamilyGroup(int i) {
        if (i != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                MyAccountManageHomesFragment.this.callRefreshFamilyGroup();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callRefreshFamilyGroup() {
        dismissPleaseWaitDialog();
        this.getFamilyGroupPresenter.getFamilyGroup();
    }

    public void onUserFamilyGroupFetchSuccess(FamilyGroupsModels.FamilyGroupsModelResponseSuccess familyGroupsModelResponseSuccess) {
        FamilyGroupList familyGroupList = this.mFragmentToActivityCallback.getFamilyGroupList();
        familyGroupList.updateList(familyGroupsModelResponseSuccess.familyResult);
        familyGroupList.updateCurrentFamily(getCoreActivity());
        refreshRacsAndMembers();
        this.familyGroupMenuItemsUsers.clear();
        this.familyGroupMenuItemsUsers.addAll(populateFamilyMembersForRecyclerView(this.mFragmentToActivityCallback.getFamilyGroupList()));
        sortRecyclerViewAlpabatically();
        this.userRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void onUserFamilyGroupFetchFailure(FamilyGroupsModels.FamilyGroupsModelResponseSuccessFailure familyGroupsModelResponseSuccessFailure) {
        dismissPleaseWaitDialog();
        if (familyGroupsModelResponseSuccessFailure.code != null) {
            String str = familyGroupsModelResponseSuccessFailure.code;
            str.hashCode();
            if (str.equals("NFE002")) {
                showSingleChiocePopUp2(getString(R.string.errorCode_alert_NFE002));
            } else if (!str.equals("NFE013")) {
                showSingleChiocePopUp2(getString(R.string.myAcc_alert_unableToFetchFamily));
            } else {
                showSingleChiocePopUp2(getString(R.string.errorCode_alert_NFE013));
            }
        } else {
            callRefreshApiFamilyGroup(familyGroupsModelResponseSuccessFailure.statusCode);
        }
    }

    /* access modifiers changed from: private */
    public void refreshRacsAndMembers() {
        this.mFragmentToActivityCallback.refreshAllIduStates();
        FamilyMembersList familyMembersList = (FamilyMembersList) this.mFragmentToActivityCallback.getFamilyMembersMap().get(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId));
        if (familyMembersList != null) {
            familyMembersList.setRequiredToRefreshList(true);
        }
    }

    private void showSingleChiocePopUp(String str) {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog.dismiss();
                return false;
            }
        });
        singleChoiceDialog.show();
    }

    private void showSingleChiocePopUp2(String str) {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog.dismiss();
                MyAccountManageHomesFragment.this.showPleaseWaitDialog();
                MyAccountManageHomesFragment.this.refreshRacsAndMembers();
                return false;
            }
        });
        singleChoiceDialog.show();
    }
}
