package com.jch.racWiFi.iduManagement.smartFence.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.ViewModelProviderUtil;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customToolTip.CreateTooltipContentHolder;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.databinding.SmartFenceSelectUsersBinding;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch.racWiFi.iduManagement.smartFence.viewModels.GetAllUsersViewModel;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.FamilyMembersList;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SmartFenceSelectUserFragment extends GenericFragment implements View.OnClickListener {
    public static boolean isUserSettingChange;
    /* access modifiers changed from: private */
    public CreateTooltipContentHolder createTooltipContentHolder;
    private GetAllUsersViewModel getAllUsersViewModel;
    SmartFenceSelectUsersBinding mBinding;
    private GeoFencePair mGeoFencePair;
    private GeoFencePair mOldGeoFencePair;
    private List<UserListModel> mOldUserListModelList = new ArrayList();
    /* access modifiers changed from: private */
    public List<UserListModel> mUserListModelList = new ArrayList();
    public UserListRecyclerViewAdapter userListRecyclerViewAdapter;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (SmartFenceSelectUsersBinding) DataBindingUtil.inflate(layoutInflater, R.layout.smart_fence_select_users, viewGroup, false);
        initAcListRecyclerView();
        initClickListeners();
        this.getAllUsersViewModel = (GetAllUsersViewModel) ViewModelProviderUtil.getViewModelInstance(requireActivity(), GetAllUsersViewModel.class);
        GeoFencePair geoFencePair = (GeoFencePair) getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().get(Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId));
        this.mGeoFencePair = geoFencePair;
        if (geoFencePair != null) {
            GeoFencePair parcelClone = geoFencePair.parcelClone();
            this.mOldGeoFencePair = parcelClone;
            parcelClone.isDefault = this.mGeoFencePair.isDefault;
        }
        this.mBinding.cbAllDevices.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    for (int i = 0; i < SmartFenceSelectUserFragment.this.mUserListModelList.size(); i++) {
                        ((UserListModel) SmartFenceSelectUserFragment.this.mUserListModelList.get(i)).isSelected = true;
                    }
                } else {
                    for (int i2 = 0; i2 < SmartFenceSelectUserFragment.this.mUserListModelList.size(); i2++) {
                        if (((long) UserInfo.getCurrentUserInfo(SmartFenceSelectUserFragment.this.getCoreActivity()).f424id) == ((UserListModel) SmartFenceSelectUserFragment.this.mUserListModelList.get(i2)).userID.longValue()) {
                            ((UserListModel) SmartFenceSelectUserFragment.this.mUserListModelList.get(i2)).isSelected = true;
                        } else {
                            ((UserListModel) SmartFenceSelectUserFragment.this.mUserListModelList.get(i2)).isSelected = false;
                        }
                    }
                }
                SmartFenceSelectUserFragment.this.userListRecyclerViewAdapter.notifyDataSetChanged();
                SmartFenceSelectUserFragment.this.changeSaveButtonState();
            }
        });
        if (NetworkConnectivity.isNetworkAvailable(getContext())) {
            populateUserListV2(this.getAllUsersViewModel.getUserListSingleLiveEvent().getValue());
        } else {
            this.mBinding.layoutAllAcs.setVisibility(8);
            this.mBinding.view3.setVisibility(8);
            showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.common_alert_unableToConnectToNw));
        }
        this.createTooltipContentHolder = new CreateTooltipContentHolder((Context) requireActivity(), (View) this.mBinding.imageButtonHelp, (int) R.string.smartFence_lbl_selectUsersHelp);
        return this.mBinding.getRoot();
    }

    private void showSingleChiocePopUp(String str, String str2) {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getContext());
        singleChoiceDialog.setTitleString(str);
        singleChoiceDialog.setMessageString(str2);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog.dismiss();
                return false;
            }
        });
        singleChoiceDialog.show();
    }

    private void populateUserList(FamilyMembersList familyMembersList) {
        UserListModel userListModel = new UserListModel();
        userListModel.setUserName(UserInfo.getCurrentUserInfo(getCoreActivity()).firstName);
        userListModel.setUserID(Long.valueOf((long) UserInfo.getCurrentUserInfo(getCoreActivity()).f424id));
        userListModel.setSelected(true);
        this.mUserListModelList.add(userListModel);
        Iterator it = familyMembersList.iterator();
        while (it.hasNext()) {
            UserFamilyMemberModels.UserFamilyMemberInfo userFamilyMemberInfo = (UserFamilyMemberModels.UserFamilyMemberInfo) it.next();
            UserListModel userListModel2 = new UserListModel();
            userListModel2.setUserName(userFamilyMemberInfo.firstName);
            userListModel2.setUserID(Long.valueOf((long) userFamilyMemberInfo.detailsUserInfoId));
            if (this.mGeoFencePair != null) {
                int i = 0;
                while (true) {
                    if (i >= this.mGeoFencePair.getAssociatedUsers().size()) {
                        break;
                    } else if (this.mGeoFencePair.getAssociatedUsers().get(i).equals(userListModel2.userID)) {
                        userListModel2.setSelected(true);
                        break;
                    } else {
                        userListModel2.setSelected(false);
                        i++;
                    }
                }
            }
        }
        this.mOldUserListModelList = parcelClone(this.mUserListModelList);
        changeState(this.mUserListModelList);
    }

    private void populateUserListV2(List<UserFamilyMemberModels.UserFamilyMemberInfo> list) {
        if (list != null) {
            for (UserFamilyMemberModels.UserFamilyMemberInfo next : list) {
                UserListModel userListModel = new UserListModel();
                userListModel.setUserName(next.firstName + " " + next.lastName);
                userListModel.setUserID(Long.valueOf((long) next.detailsUserInfoId));
                if (this.mGeoFencePair != null) {
                    int i = 0;
                    while (true) {
                        if (i >= this.mGeoFencePair.getAssociatedUsers().size()) {
                            break;
                        } else if (this.mGeoFencePair.getAssociatedUsers().get(i).equals(userListModel.userID)) {
                            userListModel.setSelected(true);
                            break;
                        } else {
                            userListModel.setSelected(false);
                            i++;
                        }
                    }
                }
                if (UserInfo.getCurrentUserInfo(getCoreActivity()).f424id == next.detailsUserInfoId) {
                    this.mUserListModelList.add(0, userListModel);
                } else {
                    this.mUserListModelList.add(userListModel);
                }
            }
            if (this.mUserListModelList.size() == 1) {
                this.mBinding.layoutAllAcs.setVisibility(8);
                this.mBinding.view3.setVisibility(8);
            } else {
                this.mBinding.layoutAllAcs.setVisibility(0);
                this.mBinding.view3.setVisibility(0);
            }
            this.userListRecyclerViewAdapter.notifyDataSetChanged();
            this.mOldUserListModelList = parcelClone(this.mUserListModelList);
            changeState(this.mUserListModelList);
        }
    }

    /* access modifiers changed from: private */
    public void changeSaveButtonState() {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= this.mOldUserListModelList.size()) {
                z = false;
                break;
            } else if (this.mOldUserListModelList.get(i).isSelected != this.mUserListModelList.get(i).isSelected) {
                z = true;
                break;
            } else {
                Log.e("Smart", "");
                i++;
            }
        }
        if (z) {
            this.mBinding.textViewSave.setEnabled(true);
            this.mBinding.textViewSave.setColorFilter(getResources().getColor(R.color.dark_red));
            return;
        }
        this.mBinding.textViewSave.setEnabled(false);
        this.mBinding.textViewSave.setColorFilter(getResources().getColor(R.color.color_disabled_views));
    }

    /* access modifiers changed from: package-private */
    public List<UserListModel> parcelClone(List<UserListModel> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            UserListModel userListModel = new UserListModel();
            userListModel.isSelected = list.get(i).isSelected;
            arrayList.add(userListModel);
        }
        return arrayList;
    }

    private void dummyList() {
        UserListModel userListModel = new UserListModel();
        userListModel.setUserName("User1");
        userListModel.setSelected(true);
        UserListModel userListModel2 = new UserListModel();
        userListModel2.setUserName("User2");
        userListModel2.setSelected(false);
        UserListModel userListModel3 = new UserListModel();
        userListModel3.setUserName("User3");
        userListModel3.setSelected(false);
        UserListModel userListModel4 = new UserListModel();
        userListModel4.setUserName("User4");
        userListModel4.setSelected(true);
        UserListModel userListModel5 = new UserListModel();
        userListModel5.setUserName("User5");
        userListModel5.setSelected(false);
        UserListModel userListModel6 = new UserListModel();
        userListModel6.setUserName("User6");
        userListModel6.setSelected(true);
    }

    private void initAcListRecyclerView() {
        this.userListRecyclerViewAdapter = new UserListRecyclerViewAdapter(this.mUserListModelList);
        this.mBinding.recyclerViewDevices.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mBinding.recyclerViewDevices.setAdapter(this.userListRecyclerViewAdapter);
    }

    private void initClickListeners() {
        this.mBinding.backButton.setOnClickListener(this);
        this.mBinding.textViewSave.setOnClickListener(this);
        this.mBinding.imageButtonHelp.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_button) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (id == R.id.image_button_help) {
            if (!this.createTooltipContentHolder.isShowing()) {
                this.createTooltipContentHolder.setHintCasePosition(3);
                this.createTooltipContentHolder.setBorderRes(R.drawable.tool_tip_without_radius);
                this.createTooltipContentHolder.setBordercolor(R.color.colorRed);
                this.createTooltipContentHolder.setHitCaseYOffset(R.dimen.hint_case_y_offset);
                this.createTooltipContentHolder.setContainerOffsetXAxis(R.dimen.container_block_x_offset_center_horizontal);
                this.createTooltipContentHolder.buildWithNoDimensions();
                this.createTooltipContentHolder.show();
            } else {
                this.createTooltipContentHolder.dismiss();
            }
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (SmartFenceSelectUserFragment.this.createTooltipContentHolder != null && SmartFenceSelectUserFragment.this.createTooltipContentHolder.isShowing()) {
                        SmartFenceSelectUserFragment.this.createTooltipContentHolder.dismiss();
                    }
                }
            }, JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
        } else if (id == R.id.text_view_save) {
            showPleaseWaitDialog();
            GeoFencePair geoFencePair = this.mGeoFencePair;
            if (geoFencePair != null) {
                geoFencePair.getAssociatedUsers().clear();
                for (int i = 0; i < this.mUserListModelList.size(); i++) {
                    if (this.mUserListModelList.get(i).isSelected) {
                        this.mGeoFencePair.getAssociatedUsers().add(this.mUserListModelList.get(i).userID);
                    }
                }
            }
            isUserSettingChange = true;
            SmartFenceFragment.isAnySettingsChanged = true;
            dismissPleaseWaitDialog();
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    public void onStart() {
        super.onStart();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (SmartFenceSelectUserFragment.this.createTooltipContentHolder != null && SmartFenceSelectUserFragment.this.createTooltipContentHolder.isShowing()) {
                    SmartFenceSelectUserFragment.this.createTooltipContentHolder.dismiss();
                }
                SmartFenceSelectUserFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
            }
        });
    }

    private class UserListRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
        List<UserListModel> list;

        UserListRecyclerViewAdapter(List<UserListModel> list2) {
            this.list = list2;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(SmartFenceSelectUserFragment.this.getContext()).inflate(R.layout.recycler_view_item_smart_fence_user_selection, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            viewHolder.mUserNameTextView.setText(this.list.get(i).userName);
            if (((long) UserInfo.getCurrentUserInfo(SmartFenceSelectUserFragment.this.getCoreActivity()).f424id) == this.list.get(i).userID.longValue()) {
                viewHolder.triStateCheckbox.setChecked(true);
                viewHolder.triStateCheckbox.setEnabled(false);
                viewHolder.constraintLayout.setAlpha(0.4f);
            } else if (this.list.get(i).isSelected) {
                viewHolder.triStateCheckbox.setChecked(true);
            } else {
                viewHolder.triStateCheckbox.setChecked(false);
            }
            viewHolder.triStateCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    UserListRecyclerViewAdapter.this.list.get(i).setSelected(z);
                    SmartFenceSelectUserFragment.this.changeState(UserListRecyclerViewAdapter.this.list);
                    SmartFenceSelectUserFragment.this.changeSaveButtonState();
                }
            });
        }

        public int getItemCount() {
            return this.list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            /* access modifiers changed from: private */
            public ConstraintLayout constraintLayout;
            private ImageView mUserIcon;
            /* access modifiers changed from: private */
            public TextView mUserNameTextView;
            /* access modifiers changed from: private */
            public TriStateCheckbox triStateCheckbox;

            public ViewHolder(View view) {
                super(view);
                this.mUserNameTextView = (TextView) view.findViewById(R.id.text_view_user_name);
                this.mUserIcon = (ImageView) view.findViewById(R.id.image_view_userlist);
                this.triStateCheckbox = (TriStateCheckbox) view.findViewById(R.id.cb_member_permission);
                this.constraintLayout = (ConstraintLayout) view.findViewById(R.id.layout_manage_devices);
            }
        }
    }

    private class UserListModel {
        /* access modifiers changed from: private */
        public boolean isSelected;
        /* access modifiers changed from: private */
        public Long userID;
        /* access modifiers changed from: private */
        public String userName;

        private UserListModel() {
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public boolean isSelected() {
            return this.isSelected;
        }

        public void setSelected(boolean z) {
            this.isSelected = z;
        }

        public Long getUserID() {
            return this.userID;
        }

        public void setUserID(Long l) {
            this.userID = l;
        }
    }

    /* access modifiers changed from: private */
    public void changeState(List<UserListModel> list) {
        int size = list.size();
        int i = 0;
        for (UserListModel r3 : list) {
            if (r3.isSelected) {
                i++;
            }
        }
        if (size == i) {
            this.mBinding.cbAllDevices.setChecked(true);
        } else if (i == 0) {
            this.mBinding.cbAllDevices.setChecked(false);
        } else {
            this.mBinding.cbAllDevices.setChecked((Boolean) null);
        }
    }
}
