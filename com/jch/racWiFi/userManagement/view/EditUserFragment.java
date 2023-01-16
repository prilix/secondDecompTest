package com.jch.racWiFi.userManagement.view;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.FamilyMembersList;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.presenter.EditUserFragmentPresenter;
import com.jch_hitachi.aircloudglobal.R;
import info.hoang8f.android.segmented.SegmentedGroup;

public class EditUserFragment extends GenericFragment implements EditUserFragmentPresenter.IEditUserFragmentPresenter {
    private boolean isFromDeviceDetailsFragment;
    @BindView(2131362078)
    ImageButton mBack;
    @BindView(2131363570)
    RadioButton mChildSelection;
    /* access modifiers changed from: private */
    public EditUserFragmentPresenter mEditUserFragmentPresenter;
    @BindView(2131363575)
    RadioButton mMemberSelection;
    @BindView(2131363580)
    RadioButton mOwnerSelection;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131363346)
    LinearLayout mRemoveUser;
    @BindView(2131364471)
    ImageButton mSave;
    private Unbinder mUnbinder;
    /* access modifiers changed from: private */
    public UserFamilyMemberModels.UserFamilyMemberInfo mUserInformation = new UserFamilyMemberModels.UserFamilyMemberInfo();
    @BindView(2131364698)
    TextView mUserName;
    @BindView(2131363034)
    ImageView mUserProfilePhoto;
    @BindView(2131363693)
    SegmentedGroup mUserRoleSegmentedGroup;
    public boolean navigateUpDone = false;
    private long racId;

    public void onNetworkCallSuccess() {
    }

    public static EditUserFragment newInstance() {
        EditUserFragment editUserFragment = new EditUserFragment();
        editUserFragment.setNewBundleAsArgument();
        return editUserFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mUserInformation.copy((UserFamilyMemberModels.UserFamilyMemberInfo) arguments.getParcelable(UserFamilyMemberModels.UserFamilyMemberInfo.USER_FAMILY_MEMBER));
            this.isFromDeviceDetailsFragment = arguments.getBoolean(Values.FROM_DEVICE_SETTING);
            this.racId = arguments.getLong(Values.RAC_ID);
        }
        Logger.m49i("", "values == " + this.isFromDeviceDetailsFragment + " " + this.racId);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.edit_user_frame, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.mEditUserFragmentPresenter = new EditUserFragmentPresenter(this);
        TextView textView = this.mUserName;
        textView.setText(this.mUserInformation.firstName + " " + this.mUserInformation.lastName);
        ProfilePicture.loadIntoImageView(this.mUserProfilePhoto, this.mUserInformation.profilePicture);
        this.mUserRoleSegmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radio_button_child) {
                    EditUserFragment.this.mChildSelection.setBackground(EditUserFragment.this.getActivity().getResources().getDrawable(R.drawable.red_border_without_radius));
                    EditUserFragment.this.mChildSelection.setTextColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.colorRed));
                    EditUserFragment.this.mChildSelection.setTypeface((Typeface) null, 1);
                    EditUserFragment.this.mMemberSelection.setBackgroundColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.color_light_grey));
                    EditUserFragment.this.mMemberSelection.setTextColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.textview_color_vd_light));
                    EditUserFragment.this.mMemberSelection.setTypeface((Typeface) null, 0);
                    EditUserFragment.this.mOwnerSelection.setBackgroundColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.color_light_grey));
                    EditUserFragment.this.mOwnerSelection.setTextColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.textview_color_vd_light));
                    EditUserFragment.this.mOwnerSelection.setTypeface((Typeface) null, 0);
                    EditUserFragment.this.mUserInformation.role.setName("CHILD");
                    EditUserFragment.this.mUserInformation.role.setLevel(3);
                } else if (i == R.id.radio_button_member) {
                    EditUserFragment.this.mMemberSelection.setBackground(EditUserFragment.this.getActivity().getResources().getDrawable(R.drawable.red_border_without_radius));
                    EditUserFragment.this.mMemberSelection.setTextColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.colorRed));
                    EditUserFragment.this.mMemberSelection.setTypeface((Typeface) null, 1);
                    EditUserFragment.this.mOwnerSelection.setBackgroundColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.color_light_grey));
                    EditUserFragment.this.mOwnerSelection.setTextColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.textview_color_vd_light));
                    EditUserFragment.this.mOwnerSelection.setTypeface((Typeface) null, 0);
                    EditUserFragment.this.mChildSelection.setBackgroundColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.color_light_grey));
                    EditUserFragment.this.mChildSelection.setTextColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.textview_color_vd_light));
                    EditUserFragment.this.mChildSelection.setTypeface((Typeface) null, 0);
                    EditUserFragment.this.mUserInformation.role.setName("MEMBER");
                    EditUserFragment.this.mUserInformation.role.setLevel(2);
                } else if (i == R.id.radio_button_owner) {
                    EditUserFragment.this.mOwnerSelection.setBackground(EditUserFragment.this.getActivity().getResources().getDrawable(R.drawable.red_border_without_radius));
                    EditUserFragment.this.mOwnerSelection.setTextColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.colorRed));
                    EditUserFragment.this.mOwnerSelection.setTypeface((Typeface) null, 1);
                    EditUserFragment.this.mMemberSelection.setBackgroundColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.color_light_grey));
                    EditUserFragment.this.mMemberSelection.setTextColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.textview_color_vd_light));
                    EditUserFragment.this.mMemberSelection.setTypeface((Typeface) null, 0);
                    EditUserFragment.this.mChildSelection.setBackgroundColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.color_light_grey));
                    EditUserFragment.this.mChildSelection.setTextColor(EditUserFragment.this.getActivity().getResources().getColor(R.color.textview_color_vd_light));
                    EditUserFragment.this.mChildSelection.setTypeface((Typeface) null, 0);
                    EditUserFragment.this.mUserInformation.role.setName("OWNER");
                    EditUserFragment.this.mUserInformation.role.setLevel(1);
                }
            }
        });
        String name = this.mUserInformation.role.getName();
        if (name.equals("OWNER")) {
            this.mUserRoleSegmentedGroup.check(R.id.radio_button_owner);
        } else if (name.equals("MEMBER")) {
            this.mUserRoleSegmentedGroup.check(R.id.radio_button_member);
        } else {
            this.mUserRoleSegmentedGroup.check(R.id.radio_button_child);
        }
        this.mEditUserFragmentPresenter.registerEventBus();
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mEditUserFragmentPresenter.unregisterEventBus();
        this.mEditUserFragmentPresenter.removeCallbacks();
        this.mUnbinder.unbind();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({2131364471})
    public void onClickSave(ImageButton imageButton) {
        final ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity());
        confirmationDialog.setTitleString(getString(R.string.common_alert_saveChanges));
        confirmationDialog.setMessageString(getString(R.string.common_alert_saveChangesDesc));
        confirmationDialog.setPositiveButton(getString(R.string.common_btn_yes), new ConfirmationDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                EditUserFragment.this.showPleaseWaitDialog();
                EditUserFragment.this.mEditUserFragmentPresenter.updateRole(EditUserFragment.this.mUserInformation, FamilyGroupList.getCurrentFamily().familyId, EditUserFragment.this.mUserInformation.detailsUserInfoId);
                confirmationDialog.dismiss();
                return false;
            }
        });
        confirmationDialog.setNegativeButton(getString(R.string.common_btn_no), new ConfirmationDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                confirmationDialog.dismiss();
                return false;
            }
        });
        confirmationDialog.setParentView(this.mParent);
        confirmationDialog.show();
    }

    @OnClick({2131362078})
    public void onClickBack(ImageButton imageButton) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131363346})
    public void OnClickRemoveUser(LinearLayout linearLayout) {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity());
        confirmationDialog.setTitleString(getActivity().getString(R.string.manageUser_btn_removeUser));
        confirmationDialog.setMessageString(getActivity().getString(R.string.manageUser_lbl_removeUserDesc));
        confirmationDialog.setCancelable(false);
        confirmationDialog.setPositiveButton(getActivity().getString(R.string.common_btn_yes), new EditUserFragment$$ExternalSyntheticLambda0(this));
        confirmationDialog.setNegativeButton(getActivity().getString(R.string.common_btn_no), new ConfirmationDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                return true;
            }
        });
        confirmationDialog.setParentView(this.mParent);
        confirmationDialog.show();
    }

    /* renamed from: lambda$OnClickRemoveUser$0$com-jch-racWiFi-userManagement-view-EditUserFragment */
    public /* synthetic */ boolean mo32869x3b430639(Dialog dialog, View view) {
        ((FamilyMembersList) this.mFragmentToActivityCallback.getFamilyMembersMap().get(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId))).setRequiredToRefreshList(true);
        this.mEditUserFragmentPresenter.removeUser(FamilyGroupList.getCurrentFamily().familyId, this.mUserInformation.detailsUserInfoId);
        showPleaseWaitDialog();
        return true;
    }

    public void onFamilyMemberRoleUpdateSuccess(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        ((FamilyMembersList) this.mFragmentToActivityCallback.getFamilyMembersMap().get(Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId))).setRequiredToRefreshList(true);
        showSingleChiocePopUp2(getString(R.string.manageUser_alert_roleUpdatedSucces)).setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                dialog.dismiss();
                if (EditUserFragment.this.navigateUpDone) {
                    return false;
                }
                EditUserFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
                EditUserFragment.this.navigateUpDone = true;
                return false;
            }
        });
        dismissPleaseWaitDialog();
    }

    public void onFamilyMemberRemoveSuccess(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        showSingleChiocePopUp2(getString(R.string.manageUser_alert_memRemovedFromFamilyGroup)).setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                dialog.dismiss();
                if (EditUserFragment.this.navigateUpDone) {
                    return false;
                }
                EditUserFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
                EditUserFragment.this.navigateUpDone = true;
                return false;
            }
        });
        dismissPleaseWaitDialog();
    }

    public void onFamilyMemberRemoveFailure(UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse) {
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
                case -1995143802:
                    if (str.equals("NFE003")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1995143797:
                    if (str.equals("NFE008")) {
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
                case 2066172685:
                    if (str.equals("FAE007")) {
                        c = 4;
                        break;
                    }
                    break;
                case 2066172687:
                    if (str.equals(UserFamilyMemberModels.UserFamilyMemberFailureResponse.USER_CANNOT_REMOVE_HIMSELF)) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE002));
                    return;
                case 1:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE003));
                    return;
                case 2:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE008));
                    return;
                case 3:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE009));
                    return;
                case 4:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_FAE007));
                    return;
                case 5:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_FAE009));
                    return;
                default:
                    showSingleChiocePopUp(getString(R.string.common_alert_unableToReachServer));
                    return;
            }
        } else {
            callRemoveApiFailure(userFamilyMemberFailureResponse.statusCode);
        }
    }

    public void onOwnerChangeRoleToOwnerFailure(UserFamilyMemberModels.UserFamilyMemberFailureResponse userFamilyMemberFailureResponse) {
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
                case 2066172681:
                    if (str.equals(UserFamilyMemberModels.UserFamilyMemberFailureResponse.USER_CANNOT_CHANGE_HIS_OWN_ROLE)) {
                        c = 3;
                        break;
                    }
                    break;
                case 2066172682:
                    if (str.equals(UserFamilyMemberModels.UserFamilyMemberFailureResponse.MEMBER_NOT_ALLOWED_TO_CHANGE_THE_ROLE)) {
                        c = 4;
                        break;
                    }
                    break;
                case 2066172683:
                    if (str.equals(UserFamilyMemberModels.UserFamilyMemberFailureResponse.ROLE_OF_CREATOR_CANNOT_BE_CHANGED)) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE002));
                    return;
                case 1:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE008));
                    return;
                case 2:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_NFE009));
                    return;
                case 3:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_FAE003));
                    return;
                case 4:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_FAE004));
                    return;
                case 5:
                    showSingleChiocePopUp(getString(R.string.errorCode_alert_FAE005));
                    return;
                default:
                    showSingleChiocePopUp(getString(R.string.manageUser_alert_roleUpdateFailed));
                    return;
            }
        } else {
            callRefreshApiChangeOwnerRole(userFamilyMemberFailureResponse.statusCode);
        }
    }

    private void callRefreshApiChangeOwnerRole(int i) {
        if (i != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                EditUserFragment.this.callRefreshOwnerRole();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callRefreshOwnerRole() {
        dismissPleaseWaitDialog();
        this.mEditUserFragmentPresenter.updateRole(this.mUserInformation, FamilyGroupList.getCurrentFamily().familyId, this.mUserInformation.detailsUserInfoId);
    }

    private void callRemoveApiFailure(int i) {
        if (i != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                EditUserFragment.this.callRemoveApi();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callRemoveApi() {
        dismissPleaseWaitDialog();
        this.mEditUserFragmentPresenter.removeUser(FamilyGroupList.getCurrentFamily().familyId, this.mUserInformation.detailsUserInfoId);
    }

    public void onNetworkCallFailure(Throwable th) {
        if (!DemoModeModel.DEMO_MODE_ON) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                public boolean onButtonClickListener(Dialog dialog, View view) {
                    return true;
                }
            });
            singleChoiceDialog.show();
        }
        dismissPleaseWaitDialog();
    }

    public void serverException() {
        showSingleChiocePopUp(getString(R.string.common_alert_somethingWentWrong));
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

    private SingleChoiceDialog showSingleChiocePopUp2(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.show();
        return singleChoiceDialog;
    }
}
