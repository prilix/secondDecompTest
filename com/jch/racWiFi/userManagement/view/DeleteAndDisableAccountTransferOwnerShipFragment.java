package com.jch.racWiFi.userManagement.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.fcm.util.Persistence;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.ChangeOwnerShipDataModel;
import com.jch.racWiFi.userManagement.model.DisableAccountDataModels;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.network.DisableAccountPresenter;
import com.jch.racWiFi.userManagement.presenter.ChangeOwnerShipPresenter;
import com.jch.racWiFi.userManagement.presenter.DeleteAccountPresenter;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;
import p011de.hdodenhof.circleimageview.CircleImageView;

public class DeleteAndDisableAccountTransferOwnerShipFragment extends GenericFragment implements ChangeOwnerShipPresenter.IChangeOwnerShipInterface, DeleteAccountPresenter.IDeleteAccountPresenter, DisableAccountPresenter.IDisableAccountPresenter {
    private ChangeOwnerShipPresenter changeOwnerShipPresenter;
    private DeleteAccountPresenter deleteAccountPresenter;
    /* access modifiers changed from: private */
    public int detailsUserInfoId = -1;
    private DisableAccountPresenter disableAccountPresenter;
    private List<UserFamilyMemberModels.UserFamilyMemberInfo> familyGroupMenuItemsUsers = new ArrayList();
    @BindView(2131363306)
    LinearLayout transferOwnerShipLL;
    private Unbinder unbinder;
    private UserRecyclerViewAdapter userRecyclerViewAdapter;
    private int whatOptionIsSelectedFromMyAccount = -1;

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder> {
        List<FamilyRecyclerItemModel> FamilyRecyclerItemModelsList;
        /* access modifiers changed from: private */
        public int checkedPosition = -1;
        Context context;

        public class UserViewHolder_ViewBinding implements Unbinder {
            private UserViewHolder target;

            public UserViewHolder_ViewBinding(UserViewHolder userViewHolder, View view) {
                this.target = userViewHolder;
                userViewHolder.mProfileCircleImageView = (CircleImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_user_profile_manage_user, "field 'mProfileCircleImageView'", CircleImageView.class);
                userViewHolder.mHomeNameTV = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_user_name_manage_user, "field 'mHomeNameTV'", TextView.class);
                userViewHolder.mRoleNameTV = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_user_type_manage_user, "field 'mRoleNameTV'", TextView.class);
                userViewHolder.radioButton = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.selected_for_owner, "field 'radioButton'", ImageView.class);
            }

            public void unbind() {
                UserViewHolder userViewHolder = this.target;
                if (userViewHolder != null) {
                    this.target = null;
                    userViewHolder.mProfileCircleImageView = null;
                    userViewHolder.mHomeNameTV = null;
                    userViewHolder.mRoleNameTV = null;
                    userViewHolder.radioButton = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        UserRecyclerViewAdapter(List list, Context context2) {
            this.FamilyRecyclerItemModelsList = list;
            this.context = context2;
        }

        public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new UserViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_select_new_owner, viewGroup, false));
        }

        public void onBindViewHolder(UserViewHolder userViewHolder, int i) {
            userViewHolder.bind(this.FamilyRecyclerItemModelsList.get(i));
        }

        public int getItemCount() {
            return this.FamilyRecyclerItemModelsList.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131364701)
            TextView mHomeNameTV;
            @BindView(2131363035)
            CircleImageView mProfileCircleImageView;
            @BindView(2131364717)
            TextView mRoleNameTV;
            @BindView(2131363708)
            ImageView radioButton;

            public UserViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }

            public void bind(final FamilyRecyclerItemModel familyRecyclerItemModel) {
                if (UserRecyclerViewAdapter.this.checkedPosition == -1) {
                    familyRecyclerItemModel.isChecked = false;
                    this.radioButton.setImageResource(R.drawable.ic_wt_border_new);
                } else if (UserRecyclerViewAdapter.this.checkedPosition == getAdapterPosition()) {
                    familyRecyclerItemModel.isChecked = true;
                    this.radioButton.setImageResource(R.drawable.ic_wt_red_tick_new);
                } else {
                    familyRecyclerItemModel.isChecked = false;
                    this.radioButton.setImageResource(R.drawable.ic_wt_border_new);
                }
                this.mHomeNameTV.setText(familyRecyclerItemModel.getName());
                this.mRoleNameTV.setText(familyRecyclerItemModel.getRoleName());
                ProfilePicture.loadIntoImageView(this.mProfileCircleImageView, familyRecyclerItemModel.profilePicture);
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (familyRecyclerItemModel.isChecked) {
                            UserViewHolder.this.radioButton.setImageResource(R.drawable.ic_wt_border_new);
                            familyRecyclerItemModel.isChecked = false;
                            DeleteAndDisableAccountTransferOwnerShipFragment.this.detailsUserInfoId = -1;
                            return;
                        }
                        UserViewHolder.this.radioButton.setImageResource(R.drawable.ic_wt_red_tick_new);
                        familyRecyclerItemModel.isChecked = true;
                        DeleteAndDisableAccountTransferOwnerShipFragment.this.detailsUserInfoId = familyRecyclerItemModel.detailsUserInfoId;
                        if (UserRecyclerViewAdapter.this.checkedPosition != UserViewHolder.this.getAdapterPosition()) {
                            UserRecyclerViewAdapter.this.notifyItemChanged(UserRecyclerViewAdapter.this.checkedPosition);
                            UserRecyclerViewAdapter.this.checkedPosition = UserViewHolder.this.getAdapterPosition();
                        }
                    }
                });
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.select_new_owner_frame, viewGroup, false);
        this.unbinder = ButterKnife.bind((Object) this, inflate);
        this.familyGroupMenuItemsUsers.clear();
        this.familyGroupMenuItemsUsers.addAll((List) this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Constants.DELETE_ACCOUNT_CHANGE_OWNER_SHIP_KEY).getDefaultValue());
        this.whatOptionIsSelectedFromMyAccount = ((Integer) this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Constants.WHAT_OPTION_IS_SELECTED_FROM_MY_ACCOUNT_PAGE).getDefaultValue()).intValue();
        this.userRecyclerViewAdapter = new UserRecyclerViewAdapter(populateMenu(this.familyGroupMenuItemsUsers), requireActivity());
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view_select_new_owner);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(this.userRecyclerViewAdapter);
        this.changeOwnerShipPresenter = new ChangeOwnerShipPresenter(this);
        this.deleteAccountPresenter = new DeleteAccountPresenter(this);
        this.disableAccountPresenter = new DisableAccountPresenter(this);
        return inflate;
    }

    public List populateMenu(List<UserFamilyMemberModels.UserFamilyMemberInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            FamilyRecyclerItemModel familyRecyclerItemModel = new FamilyRecyclerItemModel();
            familyRecyclerItemModel.setName(list.get(i).firstName + " " + list.get(i).lastName);
            String name = list.get(i).role.getName();
            familyRecyclerItemModel.setRoleName(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
            familyRecyclerItemModel.setDetailsUserInfoId(list.get(i).detailsUserInfoId);
            familyRecyclerItemModel.setProfilePicture(list.get(i).profilePicture);
            arrayList.add(familyRecyclerItemModel);
        }
        return arrayList;
    }

    @OnClick({2131362078})
    public void onClickBackButton() {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_deleteAccountChangeOwnerShipFragment_to_myAccountFragment2);
    }

    @OnClick({2131363306})
    public void onClickTransferOwnerShip() {
        if (this.familyGroupMenuItemsUsers.size() == 0) {
            return;
        }
        if (this.detailsUserInfoId != -1) {
            showPleaseWaitDialog();
            this.changeOwnerShipPresenter.changeOwnerShip(UserInfo.getCurrentUserInfo(getCoreActivity()).familyId, this.detailsUserInfoId);
            return;
        }
        showSingleChiocePopUp(getString(R.string.myAcc_alert_selectUserTransferOwnership));
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.changeOwnerShipPresenter.removeCallbacks();
        this.deleteAccountPresenter.removeCallbacks();
        this.unbinder.unbind();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onChangeOwnerShipSuccess() {
        Toaster.makeToast(requireActivity(), getString(R.string.myAcc_alert_ownershipTransSuccess), 0);
        if (this.whatOptionIsSelectedFromMyAccount == 1) {
            this.disableAccountPresenter.disableAccount();
        }
        if (this.whatOptionIsSelectedFromMyAccount == 0) {
            this.deleteAccountPresenter.deleteAccount();
        }
    }

    public void onChangeOwnerShipFailure(ChangeOwnerShipDataModel.ChangeOwnerShipDataModelFailureResponse changeOwnerShipDataModelFailureResponse) {
        dismissPleaseWaitDialog();
        if (changeOwnerShipDataModelFailureResponse.code != null) {
            String str = changeOwnerShipDataModelFailureResponse.code;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -2053325627:
                    if (str.equals("LEE001")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1995143803:
                    if (str.equals("NFE002")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1995143801:
                    if (str.equals("NFE004")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1995143796:
                    if (str.equals("NFE009")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1995143774:
                    if (str.equals(ChangeOwnerShipDataModel.ChangeOwnerShipDataModelFailureResponse.USER_IS_NOT_CREATOR_OF_THE_FAMILY)) {
                        c = 4;
                        break;
                    }
                    break;
                case 2066172679:
                    if (str.equals(ChangeOwnerShipDataModel.ChangeOwnerShipDataModelFailureResponse.USER_CANNOT_TRANSFER_OWNERSHIP_TO_HIMESELF)) {
                        c = 5;
                        break;
                    }
                    break;
                case 2066172680:
                    if (str.equals(ChangeOwnerShipDataModel.ChangeOwnerShipDataModelFailureResponse.MEMBER_CANNOT_TRANSFER_OWNERSHIP)) {
                        c = 6;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_LEE001));
                    return;
                case 1:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE002));
                    return;
                case 2:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE004));
                    return;
                case 3:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE009));
                    return;
                case 4:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE010));
                    return;
                case 5:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_FAE001));
                    return;
                case 6:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_FAE002));
                    return;
                default:
                    showSingleChiocePopUp(getString(R.string.myAcc_alert_unableToChangeOwnership));
                    return;
            }
        } else {
            callRefreshApiOwnerShip(changeOwnerShipDataModelFailureResponse.statusCodeValue);
        }
    }

    private void callRefreshApiOwnerShip(int i) {
        if (i != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                DeleteAndDisableAccountTransferOwnerShipFragment.this.callRefreshOwnerShip();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callRefreshOwnerShip() {
        dismissPleaseWaitDialog();
        if (this.detailsUserInfoId != -1) {
            this.changeOwnerShipPresenter.changeOwnerShip(UserInfo.getCurrentUserInfo(getCoreActivity()).familyId, this.detailsUserInfoId);
        }
    }

    public void onNetworkCallFailure(Throwable th) {
        GenericAlertUtils.getNoNetworkAlert(requireActivity()).show();
    }

    public void onAccountDeleteSuccess() {
        new Persistence().persist(Constants.Keys.IS_ACCOUNT_DELETED, true);
        dismissPleaseWaitDialog();
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_deleteAccountChangeOwnership_to_AccountDeletedSuccessFragment);
    }

    public void onAccountDeleteFailure(GenericResponse genericResponse) {
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
                DeleteAndDisableAccountTransferOwnerShipFragment.this.callDeleteAccountAPI();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callDeleteAccountAPI() {
        dismissPleaseWaitDialog();
        this.deleteAccountPresenter.deleteAccount();
    }

    public void onAccountDisabledSuccess() {
        dismissPleaseWaitDialog();
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_deleteAccountChangeOwnership_to_AccountDisabledSuccessFragment);
    }

    public void onAccountDisabledFailure(DisableAccountDataModels.DisableAccountFailureResponse disableAccountFailureResponse) {
        Logger.m47e("Disable Account", "onAccountDisabledFailure" + disableAccountFailureResponse.code);
        dismissPleaseWaitDialog();
        if (disableAccountFailureResponse.code != null) {
            String str = disableAccountFailureResponse.code;
            if (str.equals(DisableAccountDataModels.TRANSFER_OWNERSHIP)) {
                showSingleChiocePopUp(getString(R.string.myAcc_btn_transferOwnership));
            } else if (str.equals(DisableAccountDataModels.USER_DETALIS_NOT_FOUND)) {
                showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE002));
            } else if (str.equals(DisableAccountDataModels.USER_NOT_FOUND)) {
                showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE001));
            } else {
                showSingleChiocePopUp(getString(R.string.myAcc_alert_unableToDisableAccount));
            }
        } else {
            showSingleChiocePopUp(getString(R.string.myAcc_alert_unableToDisableAccount));
        }
    }

    public class FamilyRecyclerItemModel {
        /* access modifiers changed from: private */
        public int detailsUserInfoId;
        /* access modifiers changed from: private */
        public boolean isChecked;
        private String name;
        /* access modifiers changed from: private */
        public ProfilePicture profilePicture;
        private String roleName;

        public FamilyRecyclerItemModel() {
        }

        public ProfilePicture getProfilePicture() {
            return this.profilePicture;
        }

        public void setProfilePicture(ProfilePicture profilePicture2) {
            this.profilePicture = profilePicture2;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getRoleName() {
            return this.roleName;
        }

        public int getDetailsUserInfoId() {
            return this.detailsUserInfoId;
        }

        public void setDetailsUserInfoId(int i) {
            this.detailsUserInfoId = i;
        }

        public void setRoleName(String str) {
            this.roleName = str;
        }
    }

    private void showSingleChiocePopUp(String str) {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
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
}
