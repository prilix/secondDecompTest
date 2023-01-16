package com.jch.racWiFi.userManagement.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavArgument;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.Utils.GenericAlertUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.fcm.util.Persistence;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.DisableAccountDataModels;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.model.UserAddress;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import com.jch.racWiFi.userManagement.network.DisableAccountPresenter;
import com.jch.racWiFi.userManagement.presenter.DeleteAccountPresenter;
import com.jch.racWiFi.userManagement.presenter.ManageUserFragmentPresenter;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class MyAccountFragment extends GenericFragment implements DeleteAccountPresenter.IDeleteAccountPresenter, ManageUserFragmentPresenter.IManageUserFragmentPresenter, DisableAccountPresenter.IDisableAccountPresenter {
    static int memberCount;
    @BindView(2131362142)
    LinearLayout deleteAccountButton;
    private DeleteAccountPresenter deleteAccountPresenter;
    private DisableAccountPresenter disableAccountPresenter;
    @BindView(2131363365)
    ImageView forwardArrowImage;
    @BindView(2131363123)
    ConstraintLayout mAddressLayout;
    @BindView(2131362129)
    Button mChangeAccountInfotmation;
    @BindView(2131362130)
    Button mChangePassword;
    /* access modifiers changed from: private */
    public ManageUserFragmentPresenter mManageUserFragmentPresenter;
    @BindView(2131362812)
    ImageButton mMenu;
    @BindView(2131363230)
    ConstraintLayout mNameLayout;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131363252)
    ConstraintLayout mProfilePictureLayout;
    private Unbinder mUnbinder;
    @BindView(2131363934)
    TextView mUserAddressSubText;
    @BindView(2131364365)
    TextView mUserNameSubText;
    private UserPermissions mUserPermissions = UserPermissions.getInstance();
    @BindView(2131362973)
    ImageView mUserProfilePhoto;
    @BindView(2131364329)
    TextView manageHomeTextView;
    @BindView(2131363224)
    ConstraintLayout manageHomesLayout;
    @BindView(2131364869)
    View viewLine;
    int whatOptionSelected = -1;

    private void setManageHomesForwardImgColor() {
    }

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public static MyAccountFragment newInstance() {
        MyAccountFragment myAccountFragment = new MyAccountFragment();
        myAccountFragment.setNewBundleAsArgument();
        return myAccountFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_account_new_vd, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        UserAddress userAddress = UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress;
        if (userAddress.toString().isEmpty()) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.myAcc_lbl_address));
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(18, true), 0, spannableStringBuilder.length(), 33);
            this.mUserAddressSubText.setText(spannableStringBuilder);
            this.mUserAddressSubText.append("\n------");
        } else {
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(getResources().getString(R.string.myAcc_lbl_address));
            spannableStringBuilder2.setSpan(new AbsoluteSizeSpan(18, true), 0, spannableStringBuilder2.length(), 33);
            this.mUserAddressSubText.setText(spannableStringBuilder2);
            if (Constants.IS_DEMO_MODE) {
                TextView textView = this.mUserAddressSubText;
                textView.append(StringUtils.f715LF + userAddress.toString() + "," + getContext().getResources().getString(R.string.common_lbl_japanCountryName));
            } else {
                TextView textView2 = this.mUserAddressSubText;
                textView2.append(StringUtils.f715LF + userAddress.toString());
            }
        }
        if (UserInfo.getCurrentUserInfo(getCoreActivity()).firstName == null || UserInfo.getCurrentUserInfo(getCoreActivity()).lastName == null) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else {
            SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(getResources().getString(R.string.myAcc_lbl_name));
            spannableStringBuilder3.setSpan(new AbsoluteSizeSpan(18, true), 0, spannableStringBuilder3.length(), 33);
            this.mUserNameSubText.setText(spannableStringBuilder3);
            TextView textView3 = this.mUserNameSubText;
            textView3.append(StringUtils.f715LF + UserInfo.getCurrentUserInfo(getCoreActivity()).firstName);
            this.mUserNameSubText.append(" ");
            if (UserInfo.getCurrentUserInfo(getCoreActivity()).middleName != null && !UserInfo.getCurrentUserInfo(getCoreActivity()).middleName.isEmpty()) {
                this.mUserNameSubText.append(UserInfo.getCurrentUserInfo(getCoreActivity()).middleName);
                this.mUserNameSubText.append(" ");
            }
            this.mUserNameSubText.append(UserInfo.getCurrentUserInfo(getCoreActivity()).lastName);
        }
        this.deleteAccountPresenter = new DeleteAccountPresenter(this);
        this.mManageUserFragmentPresenter = new ManageUserFragmentPresenter(this);
        this.disableAccountPresenter = new DisableAccountPresenter(this);
        this.mChangeAccountInfotmation.setEnabledHollowButton(false);
        boolean permission = this.mUserPermissions.getPermission(UserPermissions.UserFeatures.MANAGE_HOMES);
        int color = ContextCompat.getColor(requireActivity(), R.color.textview_color_vd_light);
        int color2 = ContextCompat.getColor(requireActivity(), R.color.color_disabled_views);
        this.manageHomesLayout.setEnabled(permission);
        this.manageHomeTextView.setTextColor(permission ? color : color2);
        ImageView imageView = this.forwardArrowImage;
        if (!permission) {
            color = color2;
        }
        imageView.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        if (Constants.IS_DEMO_MODE) {
            this.manageHomesLayout.setVisibility(4);
            this.deleteAccountButton.setVisibility(4);
            this.mChangePassword.setVisibility(4);
            this.viewLine.setVisibility(4);
        } else {
            this.manageHomesLayout.setVisibility(0);
            this.deleteAccountButton.setVisibility(0);
            this.mChangePassword.setVisibility(0);
            this.viewLine.setVisibility(0);
        }
        logEvent(Screens.SCREENS.name(), 12);
        logEvents(Events.MY_ACCOUNT_FREQUENCY.name(), 0);
        return inflate;
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        ProfilePicture.loadIntoImageView(this.mUserProfilePhoto, UserInfo.getCurrentUserInfo(getCoreActivity()).profilePicture);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mManageUserFragmentPresenter.removeCallbacks();
        this.deleteAccountPresenter.removeCallbacks();
        this.mUnbinder.unbind();
    }

    @OnClick({2131363252})
    public void OnClickProfilePicture(ConstraintLayout constraintLayout) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_myAccountFragment2_to_myAccountProfilePictureFragment);
    }

    @OnClick({2131363230})
    public void OnClickName(ConstraintLayout constraintLayout) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_myAccountFragment2_to_myAccountNameFragment);
    }

    @OnClick({2131363123})
    public void OnClickAddress(ConstraintLayout constraintLayout) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_myAccountFragment2_to_myAccountAddressFragment);
    }

    @OnClick({2131362130})
    public void OnClickChangePassword(Button button) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_myAccountFragment2_to_changePasswordFragment);
    }

    @OnClick({2131362812})
    public void OnClickMenu(ImageButton imageButton) {
        this.iDrawerMenuFunctions.openMenuDrawer();
    }

    @OnClick({2131363224})
    public void OnClickManageHomes(ConstraintLayout constraintLayout) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_myAccountFragment2_to_myAccountManageHomesFragment);
    }

    @OnClick({2131362145})
    public void OnClickDisableAccount(LinearLayout linearLayout) {
        showDisableConfirmationOne();
    }

    @OnClick({2131362142})
    public void OnClickDeleteAccount(LinearLayout linearLayout) {
        showDeleteConfirmationOne();
    }

    private void showDisableConfirmationOne() {
        final ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity());
        confirmationDialog.setTitleString(getActivity().getString(R.string.myAcc_alert_disableAcc));
        confirmationDialog.setMessageString(getActivity().getString(R.string.myAcc_alert_sureToDisableAccountDesc));
        confirmationDialog.setCancelable(false);
        confirmationDialog.setParentView(this.mParent);
        confirmationDialog.setPositiveButton(getActivity().getString(R.string.common_btn_yes), new ConfirmationDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                confirmationDialog.dismiss();
                MyAccountFragment.this.showDisableAccountConfirmationTwo();
                return true;
            }
        });
        confirmationDialog.setNegativeButton(getActivity().getString(R.string.common_btn_no), new ConfirmationDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                confirmationDialog.dismiss();
                return true;
            }
        });
        confirmationDialog.show();
    }

    /* access modifiers changed from: private */
    public void showDisableAccountConfirmationTwo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true).setView(R.layout.dialog_diable_account);
        final AlertDialog create = builder.create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                MyAccountFragment.this.mParent.setBackgroundResource(R.drawable.white_blur);
            }
        });
        create.show();
        create.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                MyAccountFragment.this.mParent.setBackgroundResource(R.drawable.transparent);
            }
        });
        WindowManager.LayoutParams attributes = create.getWindow().getAttributes();
        attributes.dimAmount = 0.2f;
        create.getWindow().setAttributes(attributes);
        create.getWindow().addFlags(2);
        View decorView = create.getWindow().getDecorView();
        ((Button) decorView.findViewById(R.id.button_positive)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyAccountFragment.this.whatOptionSelected = 1;
                MyAccountFragment.this.showPleaseWaitDialog();
                MyAccountFragment.this.mManageUserFragmentPresenter.getFamilyMemberList(FamilyGroupList.getCurrentFamily().familyId);
                create.dismiss();
            }
        });
        ((Button) decorView.findViewById(R.id.button_negative)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
            }
        });
    }

    private void showDeleteConfirmationOne() {
        final ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity());
        confirmationDialog.setTitleString(getActivity().getString(R.string.myAcc_alert_deleteAcc));
        confirmationDialog.setMessageString((int) R.string.myAcc_alert_sureToDeleteAccountDesc);
        confirmationDialog.setCancelable(false);
        confirmationDialog.setParentView(this.mParent);
        confirmationDialog.setPositiveButton(getActivity().getString(R.string.common_btn_yes), new ConfirmationDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                confirmationDialog.dismiss();
                MyAccountFragment.this.showDeleteConfirmationTwo();
                return true;
            }
        });
        confirmationDialog.setNegativeButton(getActivity().getString(R.string.common_btn_no), new ConfirmationDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                confirmationDialog.dismiss();
                return true;
            }
        });
        confirmationDialog.show();
    }

    /* access modifiers changed from: private */
    public void showDeleteConfirmationTwo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true).setView(R.layout.dialog_delete_account);
        final AlertDialog create = builder.create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                MyAccountFragment.this.mParent.setBackgroundResource(R.drawable.white_blur);
            }
        });
        create.show();
        create.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                MyAccountFragment.this.mParent.setBackgroundResource(R.drawable.transparent);
            }
        });
        WindowManager.LayoutParams attributes = create.getWindow().getAttributes();
        attributes.dimAmount = 0.2f;
        create.getWindow().setAttributes(attributes);
        create.getWindow().addFlags(2);
        View decorView = create.getWindow().getDecorView();
        ((Button) decorView.findViewById(R.id.button_positive)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
                MyAccountFragment.this.whatOptionSelected = 0;
                MyAccountFragment.this.showPleaseWaitDialog();
                MyAccountFragment.this.mManageUserFragmentPresenter.getFamilyMemberList(FamilyGroupList.getCurrentFamily().familyId);
            }
        });
        ((Button) decorView.findViewById(R.id.button_negative)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
            }
        });
    }

    public void onAccountDeleteSuccess() {
        new Persistence().persist(Constants.Keys.IS_ACCOUNT_DELETED, true);
        dismissPleaseWaitDialog();
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_myAccountFragment2_to_AccountDeletedSuccessFragment);
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
                MyAccountFragment.this.callDeleteAccountAPI();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callDeleteAccountAPI() {
        dismissPleaseWaitDialog();
        this.deleteAccountPresenter.deleteAccount();
    }

    public void onUserFamilyListAvailable(UserFamilyMemberModels.UserFamilyMemberSuccessResponse userFamilyMemberSuccessResponse) {
        memberCount = userFamilyMemberSuccessResponse.userFamilyMemberInfo.size();
        if (userFamilyMemberSuccessResponse.userFamilyMemberInfo.size() > 0) {
            dismissPleaseWaitDialog();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(userFamilyMemberSuccessResponse.userFamilyMemberInfo);
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(com.jch.racWiFi.Constants.DELETE_ACCOUNT_CHANGE_OWNER_SHIP_KEY, new NavArgument.Builder().setDefaultValue(arrayList).build());
            this.mFragmentToActivityCallback.getNavController().getGraph().addArgument(com.jch.racWiFi.Constants.WHAT_OPTION_IS_SELECTED_FROM_MY_ACCOUNT_PAGE, new NavArgument.Builder().setDefaultValue(Integer.valueOf(this.whatOptionSelected)).build());
            this.whatOptionSelected = -1;
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_myAccountFragment2_to_deleteAccountTransferOwnerShipFragment);
            return;
        }
        if (this.whatOptionSelected == 0) {
            this.deleteAccountPresenter.deleteAccount();
            this.whatOptionSelected = -1;
        }
        if (this.whatOptionSelected == 1) {
            this.disableAccountPresenter.disableAccount();
            this.whatOptionSelected = -1;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        if (r4.equals("NFE002") == false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUserFamilyListFetchFailure(com.jch.racWiFi.userManagement.model.UserFamilyMemberModels.UserFamilyMemberFailureResponse r4) {
        /*
            r3 = this;
            r3.dismissPleaseWaitDialog()
            java.lang.String r0 = r4.code
            r1 = 0
            if (r0 == 0) goto L_0x007b
            java.lang.String r4 = r4.code
            r4.hashCode()
            r0 = -1
            int r2 = r4.hashCode()
            switch(r2) {
                case -1995143803: goto L_0x0038;
                case -1995143796: goto L_0x002d;
                case -1995143771: goto L_0x0022;
                case 2066172683: goto L_0x0017;
                default: goto L_0x0015;
            }
        L_0x0015:
            r1 = -1
            goto L_0x0041
        L_0x0017:
            java.lang.String r1 = "FAE005"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0020
            goto L_0x0015
        L_0x0020:
            r1 = 3
            goto L_0x0041
        L_0x0022:
            java.lang.String r1 = "NFE013"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x002b
            goto L_0x0015
        L_0x002b:
            r1 = 2
            goto L_0x0041
        L_0x002d:
            java.lang.String r1 = "NFE009"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0036
            goto L_0x0015
        L_0x0036:
            r1 = 1
            goto L_0x0041
        L_0x0038:
            java.lang.String r2 = "NFE002"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L_0x0041
            goto L_0x0015
        L_0x0041:
            switch(r1) {
                case 0: goto L_0x0070;
                case 1: goto L_0x0065;
                case 2: goto L_0x005a;
                case 3: goto L_0x004f;
                default: goto L_0x0044;
            }
        L_0x0044:
            r4 = 2131953153(0x7f130601, float:1.9542769E38)
            java.lang.String r4 = r3.getString(r4)
            r3.showSingleChiocePopUp(r4)
            goto L_0x009b
        L_0x004f:
            r4 = 2131952819(0x7f1304b3, float:1.9542092E38)
            java.lang.String r4 = r3.getString(r4)
            r3.showSingleChiocePopUp(r4)
            goto L_0x009b
        L_0x005a:
            r4 = 2131952837(0x7f1304c5, float:1.9542128E38)
            java.lang.String r4 = r3.getString(r4)
            r3.showSingleChiocePopUp(r4)
            goto L_0x009b
        L_0x0065:
            r4 = 2131952833(0x7f1304c1, float:1.954212E38)
            java.lang.String r4 = r3.getString(r4)
            r3.showSingleChiocePopUp(r4)
            goto L_0x009b
        L_0x0070:
            r4 = 2131952826(0x7f1304ba, float:1.9542106E38)
            java.lang.String r4 = r3.getString(r4)
            r3.showSingleChiocePopUp(r4)
            goto L_0x009b
        L_0x007b:
            int r4 = r4.statusCode
            r0 = 401(0x191, float:5.62E-43)
            if (r4 == r0) goto L_0x008c
            r4 = 2131952851(0x7f1304d3, float:1.9542156E38)
            java.lang.String r4 = r3.getString(r4)
            r3.showErrorPopUp(r4)
            goto L_0x009b
        L_0x008c:
            r3.showPleaseWaitDialog()
            com.jch.racWiFi.CoreActivity r4 = r3.getCoreActivity()
            com.jch.racWiFi.userManagement.view.MyAccountFragment$14 r0 = new com.jch.racWiFi.userManagement.view.MyAccountFragment$14
            r0.<init>()
            r4.refreshToken(r0, r1)
        L_0x009b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.userManagement.view.MyAccountFragment.onUserFamilyListFetchFailure(com.jch.racWiFi.userManagement.model.UserFamilyMemberModels$UserFamilyMemberFailureResponse):void");
    }

    /* access modifiers changed from: private */
    public void callDeleteAccountFamilyAPI() {
        dismissPleaseWaitDialog();
        this.mManageUserFragmentPresenter.getFamilyMemberList(FamilyGroupList.getCurrentFamily().familyId);
    }

    public void onNetworkCallFailure(Throwable th) {
        GenericAlertUtils.getNoNetworkAlert(getActivity()).show();
    }

    public void onAccountDisabledSuccess() {
        dismissPleaseWaitDialog();
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_myAccountFragment2_to_AccountDisabledSuccessFragment);
    }

    public void onAccountDisabledFailure(DisableAccountDataModels.DisableAccountFailureResponse disableAccountFailureResponse) {
        dismissPleaseWaitDialog();
        Logger.m47e("Disable Account", "onAccountDisabledFailure" + disableAccountFailureResponse.code);
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
}
