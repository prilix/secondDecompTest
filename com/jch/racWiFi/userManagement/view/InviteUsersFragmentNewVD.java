package com.jch.racWiFi.userManagement.view;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.iduManagement.view.CountryCodeDialog;
import com.jch.racWiFi.selectMultipleContacts.ContactResult;
import com.jch.racWiFi.selectMultipleContacts.LimitColumn;
import com.jch.racWiFi.selectMultipleContacts.MultiContactPicker;
import com.jch.racWiFi.userManagement.SelectCountryCodeRecyclerViewAdapter;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.InviteMemberModels;
import com.jch.racWiFi.userManagement.presenter.InviteUserPresenter;
import com.jch.racWiFi.userManagement.view.InvalidEmailAndMobileNumberDialog;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;

public class InviteUsersFragmentNewVD extends GenericFragment implements InviteUserPresenter.InviteUserPresenterInterface, InvalidEmailAndMobileNumberDialog.IReceiveCorrectedEmailOrPhoneNum {
    public static final int PICK_CONTACT = 995;
    public static final int PICK_CONTACT_LIB = 996;
    @BindView(2131363122)
    LinearLayout mAddMembers;
    @BindView(2131362078)
    ImageButton mBackButton;
    private String mContactName = null;
    @BindView(2131363164)
    ConstraintLayout mCountryCodeLayout;
    @BindView(2131364069)
    TextView mCountryCodeTextView;
    @BindView(2131362920)
    ImageView mCountryFlag;
    @BindView(2131362404)
    View mEmailSelectionHighlightView;
    @BindView(2131364143)
    TextView mEmailSwitch;
    @BindView(2131362371)
    EditText mEnterEmail;
    @BindView(2131362384)
    EditText mEnterPhoneNumber;
    /* access modifiers changed from: private */
    public int mExistingMemberCount = -1;
    private InviteUserPresenter mInviteUserPresenter;
    private boolean mIsMobileNumber;
    /* access modifiers changed from: private */
    public int mMaxMemberCount = 19;
    @BindView(2131362160)
    Button mMayBeLater;
    @BindView(2131363406)
    ConstraintLayout mMobileNumberLayout;
    @BindView(2131364339)
    TextView mMobileNumberSwitch;
    @BindView(2131363407)
    View mMobileSelectionHighlightView;
    @BindView(2131364384)
    TextView mNumOfUserTextView;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131363612)
    RecyclerView mRecyclerView;
    @BindView(2131363347)
    LinearLayout mSelectContacts;
    @BindView(2131362172)
    Button mSendInvite;
    private Unbinder mUnbinder;
    private NavController navController;
    private int navigatingFrom;
    List<UsersToBeInvitedData> userToBeInvitedList = new ArrayList();
    UsersToBeInvitedAdapter usersToBeInvitedAdapter;

    public void onNetworkCallSuccess() {
    }

    public static InviteUsersFragmentNewVD newInstance() {
        InviteUsersFragmentNewVD inviteUsersFragmentNewVD = new InviteUsersFragmentNewVD();
        inviteUsersFragmentNewVD.setNewBundleAsArgument();
        return inviteUsersFragmentNewVD;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            int i = arguments.getInt(Values.NAVIGATING_FROM);
            this.navigatingFrom = i;
            Logger.m49i("NavigatingFrom", String.valueOf(i));
        }
        if (arguments != null) {
            this.mExistingMemberCount = arguments.getInt(Values.FAMILY_MEMBER_COUNT);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.invite_users_new_vd, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        this.mInviteUserPresenter = new InviteUserPresenter(this);
        CountryCodeUIConfiguration countryCodeUIConfigurationFromCountryObject = CountryCodeUIConfiguration.getCountryCodeUIConfigurationFromCountryObject(getContext(), CountryUtils.getByCode(getContext(), CountryUtils.getAllCountries(getContext()), "+91"));
        countryCodeUIConfigurationFromCountryObject.setCountryCodeString(getContext());
        updateCountryCodeSelectionOnCountryCodeChange(countryCodeUIConfigurationFromCountryObject);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view_mange_users);
        this.usersToBeInvitedAdapter = new UsersToBeInvitedAdapter(this.userToBeInvitedList);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mRecyclerView.setAdapter(this.usersToBeInvitedAdapter);
        String format = String.format(getResources().getString(R.string.manageUser_lbl_numberOfContancts), new Object[]{Integer.valueOf(this.userToBeInvitedList.size()), Integer.valueOf(this.mMaxMemberCount - this.mExistingMemberCount)});
        if (this.userToBeInvitedList.size() == 0) {
            this.mNumOfUserTextView.setVisibility(8);
        } else {
            this.mNumOfUserTextView.setVisibility(0);
            this.mNumOfUserTextView.setText(format);
        }
        ViewUtils.setOutlineCountryImage(this.mCountryFlag);
        this.mCountryFlag.setElevation(ViewUtils.convertDpToPixel(3.0f, requireActivity()));
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mInviteUserPresenter.removeCallbacks();
        this.mUnbinder.unbind();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Logger.m49i("", "User closed the picker without selecting items.");
        if (i != 995) {
            if (i == 996) {
                if (i2 == -1) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.clear();
                    arrayList.addAll(MultiContactPicker.obtainResult(intent));
                    if (arrayList.size() > 0) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            this.mContactName = ((ContactResult) arrayList.get(i3)).getDisplayName();
                            if (((ContactResult) arrayList.get(i3)).getPhoneNumbers().size() > 0) {
                                Logger.m45d("MyTag", ((ContactResult) arrayList.get(i3)).getDisplayName() + "phone number" + ((ContactResult) arrayList.get(i3)).getPhoneNumbers().get(0).getNumber());
                                String number = ((ContactResult) arrayList.get(i3)).getPhoneNumbers().get(0).getNumber();
                                if (!ValidationUtils.containsCountryCode(number)) {
                                    String countryCodeFromISOCode = (UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode() == null || UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode().isEmpty()) ? null : ValidationUtils.getCountryCodeFromISOCode(UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode().toUpperCase());
                                    if (countryCodeFromISOCode == null) {
                                        countryCodeFromISOCode = this.mCountryCodeTextView.getText().toString();
                                    }
                                    if (ValidationUtils.validatePhoneNumberBasedOnCountryCode(countryCodeFromISOCode, number)) {
                                        validateEmailAndPhoneNumber((String) null, number, countryCodeFromISOCode);
                                    } else {
                                        openInvalidMobileNumberDialogFromContactsWithoutCountryCode(number, (String) null);
                                    }
                                } else if (ValidationUtils.validatePhoneNumberBasedOnCountryCode("+91", number)) {
                                    validateEmailAndPhoneNumber((String) null, number, "+91");
                                } else {
                                    Phonenumber.PhoneNumber phoneNumberObjFromString = ValidationUtils.getPhoneNumberObjFromString(number);
                                    if (phoneNumberObjFromString != null) {
                                        openInvalidMobileNumberDialogFromContactsWithCountryCode(String.valueOf(phoneNumberObjFromString.getNationalNumber()), (String) null, String.valueOf(phoneNumberObjFromString.getCountryCode()));
                                    } else {
                                        openInvalidMobileNumberDialog(number, (String) null);
                                    }
                                }
                            }
                            if (((ContactResult) arrayList.get(i3)).getEmails().size() > 0) {
                                validateEmail(((ContactResult) arrayList.get(i3)).getEmails().get(0));
                            }
                        }
                    }
                } else if (i2 == 0) {
                    Logger.m49i("", "User closed the picker without selecting items.");
                }
            }
        } else if (i2 == -1) {
            Uri data = intent.getData();
            getCoreActivity().getContentResolver();
            Cursor query = getCoreActivity().getContentResolver().query(data, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query.moveToFirst()) {
                String string = query.getString(query.getColumnIndex("display_name"));
                Logger.m49i("Contact Name", string);
                this.mContactName = string;
                String string2 = query.getString(query.getColumnIndex("_id"));
                if (Integer.valueOf(query.getString(query.getColumnIndex("has_phone_number"))).intValue() == 1) {
                    ContentResolver contentResolver = getCoreActivity().getContentResolver();
                    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    Cursor query2 = contentResolver.query(uri, (String[]) null, "contact_id = " + string2, (String[]) null, (String) null);
                    while (query2.moveToNext()) {
                        String string3 = query2.getString(query2.getColumnIndex("data1"));
                        Logger.m49i("Contact Number", String.valueOf(string3));
                        if (string3 != null && !string3.isEmpty()) {
                            this.mEnterPhoneNumber.setText(string3);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131363164})
    public void onClickCountryCodeSelection(ConstraintLayout constraintLayout) {
        showCountryCodeSelectionDialog();
    }

    @OnClick({2131363122})
    public void onClickAddMembers(LinearLayout linearLayout) {
        if (this.userToBeInvitedList.size() < this.mMaxMemberCount - this.mExistingMemberCount) {
            String obj = this.mEnterEmail.getText().toString();
            String obj2 = this.mEnterPhoneNumber.getText().toString();
            String charSequence = this.mCountryCodeTextView.getText().toString();
            if ((obj.isEmpty() && obj2.isEmpty()) || charSequence.isEmpty()) {
                return;
            }
            if (obj2 == null || obj2.isEmpty() || ValidationUtils.validatePhoneNumberBasedOnCountryCode(charSequence, obj2)) {
                validateEmailAndPhoneNumber(this.mEnterEmail.getText().toString(), this.mEnterPhoneNumber.getText().toString(), this.mCountryCodeTextView.getText().toString());
            } else {
                openInvalidMobileNumberDialog(obj2, obj);
            }
        } else {
            showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_LEE001));
        }
    }

    @OnClick({2131363347})
    public void onClickSelectContacts(LinearLayout linearLayout) {
        if (!getCoreActivity().isPermissionGranted("android.permission.READ_CONTACTS")) {
            getCoreActivity().askForPermission("android.permission.READ_CONTACTS");
        } else if (this.mMobileNumberLayout.getVisibility() == 0) {
            new MultiContactPicker.Builder((Fragment) this).setNumberOfContactsToBeSelected((this.mMaxMemberCount - this.mExistingMemberCount) - this.userToBeInvitedList.size()).hideScrollbar(false).showTrack(true).searchIconColor(-1).setChoiceMode(0).handleColor(ContextCompat.getColor(requireActivity(), R.color.colorPrimary)).bubbleColor(ContextCompat.getColor(requireActivity(), R.color.colorPrimary)).bubbleTextColor(-1).setTitleText(getString(R.string.manageUser_lbl_selectContact)).setLoadingType(0).limitToColumn(LimitColumn.PHONE).setActivityAnimations(17432576, 17432577, 17432576, 17432577).showPickerForResult(996);
        } else {
            new MultiContactPicker.Builder((Fragment) this).setNumberOfContactsToBeSelected((this.mMaxMemberCount - this.mExistingMemberCount) - this.userToBeInvitedList.size()).hideScrollbar(false).showTrack(true).searchIconColor(-1).setChoiceMode(0).handleColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary)).bubbleColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary)).bubbleTextColor(-1).setTitleText(getString(R.string.manageUser_lbl_selectContact)).setLoadingType(0).limitToColumn(LimitColumn.EMAIL).setActivityAnimations(17432576, 17432577, 17432576, 17432577).showPickerForResult(996);
        }
    }

    @OnClick({2131362172})
    public void onClickSendInvite(Button button) {
        if (!this.userToBeInvitedList.isEmpty()) {
            showPleaseWaitDialog();
            this.mInviteUserPresenter.inviteUserV2(FamilyGroupList.getCurrentFamily().familyId, this.userToBeInvitedList);
            return;
        }
        showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_PCF002));
    }

    @OnClick({2131364339})
    public void onClickMobileButton(TextView textView) {
        this.mEnterEmail.setText("");
        this.mEnterPhoneNumber.setText("");
        this.mMobileNumberLayout.setVisibility(0);
        this.mEnterEmail.setVisibility(4);
        this.mMobileSelectionHighlightView.setVisibility(0);
        this.mEmailSelectionHighlightView.setVisibility(4);
        this.mIsMobileNumber = true;
    }

    @OnClick({2131364143})
    public void onClickEmailButton(TextView textView) {
        this.mEnterEmail.setText("");
        this.mEnterPhoneNumber.setText("");
        this.mMobileNumberLayout.setVisibility(4);
        this.mEnterEmail.setVisibility(0);
        this.mMobileSelectionHighlightView.setVisibility(4);
        this.mEmailSelectionHighlightView.setVisibility(0);
        this.mIsMobileNumber = false;
    }

    @OnClick({2131362078})
    public void onClickBackButton(ImageButton imageButton) {
        this.navController.navigateUp();
    }

    @OnClick({2131362160})
    public void onClickNoButton(Button button) {
        this.navController.navigateUp();
    }

    public void onNetworkCallFailure(Throwable th) {
        showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.common_alert_unableToConnectToNw));
        dismissPleaseWaitDialog();
    }

    public void serverException() {
        showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.common_alert_somethingWentWrong));
        dismissPleaseWaitDialog();
    }

    public void onInviteSendSuccess(InviteMemberModels.InviteMemberSuccessResponse inviteMemberSuccessResponse) {
        showSingleChiocePopUp2(getString(R.string.common_alert), getString(R.string.manageUser_alert_invitationSentSuccessfully)).setPositiveButton(getString(R.string.common_btn_ok), new InviteUsersFragmentNewVD$$ExternalSyntheticLambda0(this));
        dismissPleaseWaitDialog();
    }

    /* renamed from: lambda$onInviteSendSuccess$0$com-jch-racWiFi-userManagement-view-InviteUsersFragmentNewVD */
    public /* synthetic */ boolean mo32886x7a205c78(Dialog dialog, View view) {
        dialog.dismiss();
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        if (this.navigatingFrom == 0) {
            if (this.mIsMobileNumber) {
                postEvent(Events.ADD_USER_HOME_MOBILE_NO.name());
                return false;
            }
            postEvent(Events.ADD_USER_HOME_EMAIL.name());
            return false;
        } else if (this.mIsMobileNumber) {
            postEvent(Events.ADD_USER_MANAGE_USER_MOBILE_NO.name());
            return false;
        } else {
            postEvent(Events.ADD_USER_MANAGE_USER_EMAIL.name());
            return false;
        }
    }

    private void postEvent(String str) {
        logEvents(str, 0);
    }

    public void onInviteSendFailure(InviteMemberModels.InviteMemberFailureResponse inviteMemberFailureResponse) {
        dismissPleaseWaitDialog();
        if (inviteMemberFailureResponse.code != null) {
            String str = inviteMemberFailureResponse.code;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -2053325627:
                    if (str.equals("LEE001")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1995143804:
                    if (str.equals("NFE001")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1995143803:
                    if (str.equals("NFE002")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1995143802:
                    if (str.equals("NFE003")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1995143801:
                    if (str.equals("NFE004")) {
                        c = 4;
                        break;
                    }
                    break;
                case 2037543528:
                    if (str.equals(InviteMemberModels.THE_USER_CANNNOT_INVITE_HIMSELF)) {
                        c = 5;
                        break;
                    }
                    break;
                case 2037543529:
                    if (str.equals(InviteMemberModels.USER_WITH_EMAIL_ALREADY_EXIST_IN_FAMILY)) {
                        c = 6;
                        break;
                    }
                    break;
                case 2037543530:
                    if (str.equals(InviteMemberModels.USER_WITH_PHONE_NUMBER_ALREADY_EXIST_IN_FAMILY)) {
                        c = 7;
                        break;
                    }
                    break;
                case 2037543531:
                    if (str.equals(InviteMemberModels.INVITING_ALREADY_EXISTING_MEMBERS)) {
                        c = 8;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_LEE001));
                    return;
                case 1:
                    showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_NFE001));
                    return;
                case 2:
                    showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_NFE002));
                    return;
                case 3:
                    showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_NFE003));
                    return;
                case 4:
                    showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_NFE004));
                    return;
                case 5:
                    showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_EAE001));
                    return;
                case 6:
                    showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_EAE002));
                    return;
                case 7:
                    showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.errorCode_alert_EAE003));
                    return;
                case 8:
                    List asList = Arrays.asList(inviteMemberFailureResponse.stackTrace.split("\\s*,\\s*"));
                    String str2 = "";
                    for (int i = 0; i < asList.size(); i++) {
                        str2 = str2 + ((String) asList.get(i)) + StringUtils.f715LF;
                    }
                    final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
                    singleChoiceDialog.setTitleString(getString(R.string.common_alert));
                    if (asList.size() == 1) {
                        singleChoiceDialog.setMessageString(str2 + StringUtils.f715LF + getResources().getString(R.string.manageUser_alert_userAlreadyExistInFamily));
                    } else {
                        singleChoiceDialog.setMessageString(str2 + StringUtils.f715LF + getResources().getString(R.string.manageUser_alert_usersAlreadyExistInFamily));
                    }
                    singleChoiceDialog.setCancelable(false);
                    singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                        public boolean onButtonClickListener(Dialog dialog, View view) {
                            singleChoiceDialog.dismiss();
                            return false;
                        }
                    });
                    singleChoiceDialog.show();
                    return;
                default:
                    showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.common_alert_unableToReachServer));
                    return;
            }
        } else {
            callInviteRefreshApi(inviteMemberFailureResponse.statusCode);
        }
    }

    private void callInviteRefreshApi(int i) {
        if (i != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                InviteUsersFragmentNewVD.this.callRefreshForInvite();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callRefreshForInvite() {
        dismissPleaseWaitDialog();
        if (!this.userToBeInvitedList.isEmpty()) {
            this.mInviteUserPresenter.inviteUserV2(FamilyGroupList.getCurrentFamily().familyId, this.userToBeInvitedList);
        }
    }

    private void showSingleChiocePopUp(String str, String str2) {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
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

    private SingleChoiceDialog showSingleChiocePopUp2(String str, String str2) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(str);
        singleChoiceDialog.setMessageString(str2);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.show();
        return singleChoiceDialog;
    }

    public void onReceivedCorrectedEmailOrPhoneNum(String str, String str2, String str3) {
        if (!IsInvitingMySelf(str2, str)) {
            addDataToList(str, str2, str3);
        }
    }

    public void onClickRemove() {
        this.mEnterPhoneNumber.setText("");
        this.mEnterEmail.setText("");
        this.mContactName = null;
    }

    private void addDataToList(String str, String str2, String str3) {
        if (str != null) {
            if (isContactAlreadyExist(this.userToBeInvitedList, str)) {
                showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.manageUser_alert_phNoAlreadyExists));
                return;
            }
            this.userToBeInvitedList.add(new UsersToBeInvitedData(str3, str, str2));
            this.usersToBeInvitedAdapter.notifyDataSetChanged();
            this.mEnterPhoneNumber.setText("");
            this.mEnterEmail.setText("");
            this.mContactName = null;
            String format = String.format(getResources().getString(R.string.manageUser_lbl_numberOfContancts), new Object[]{Integer.valueOf(this.userToBeInvitedList.size()), Integer.valueOf(this.mMaxMemberCount - this.mExistingMemberCount)});
            if (this.userToBeInvitedList.size() == 0) {
                this.mNumOfUserTextView.setVisibility(8);
            } else {
                this.mNumOfUserTextView.setVisibility(0);
                this.mNumOfUserTextView.setText(format);
            }
        }
        if (str2 == null) {
            return;
        }
        if (isContactAlreadyExist(this.userToBeInvitedList, str2)) {
            showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.manageUser_alert_emailAlreadyExists));
            return;
        }
        this.userToBeInvitedList.add(new UsersToBeInvitedData(str3, str, str2));
        this.usersToBeInvitedAdapter.notifyDataSetChanged();
        this.mEnterPhoneNumber.setText("");
        this.mEnterEmail.setText("");
        this.mContactName = null;
        String format2 = String.format(getResources().getString(R.string.manageUser_lbl_numberOfContancts), new Object[]{Integer.valueOf(this.userToBeInvitedList.size()), Integer.valueOf(this.mMaxMemberCount - this.mExistingMemberCount)});
        if (this.userToBeInvitedList.size() == 0) {
            this.mNumOfUserTextView.setVisibility(8);
            return;
        }
        this.mNumOfUserTextView.setVisibility(0);
        this.mNumOfUserTextView.setText(format2);
    }

    public class UsersToBeInvitedData {
        /* access modifiers changed from: private */
        public String emailID;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public String phoneNumber;

        UsersToBeInvitedData(String str, String str2, String str3) {
            this.name = str;
            this.phoneNumber = str2;
            this.emailID = str3;
        }

        public String getEmailID() {
            return this.emailID;
        }

        public void setEmailID(String str) {
            this.emailID = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getPhoneNumber() {
            return this.phoneNumber;
        }

        public void setPhoneNumber(String str) {
            this.phoneNumber = str;
        }
    }

    public class UsersToBeInvitedAdapter extends RecyclerView.Adapter<UserToBeInvitedViewHolder> {
        List<UsersToBeInvitedData> usersToBeInvitedDataList;

        UsersToBeInvitedAdapter(List<UsersToBeInvitedData> list) {
            this.usersToBeInvitedDataList = list;
        }

        public UserToBeInvitedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new UserToBeInvitedViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_items_invite_users, viewGroup, false));
        }

        public void onBindViewHolder(UserToBeInvitedViewHolder userToBeInvitedViewHolder, final int i) {
            userToBeInvitedViewHolder.userNameTV.setText(this.usersToBeInvitedDataList.get(i).name);
            if (this.usersToBeInvitedDataList.get(i).phoneNumber != null) {
                userToBeInvitedViewHolder.mobileOrEmailTv.setText(this.usersToBeInvitedDataList.get(i).phoneNumber);
            } else {
                userToBeInvitedViewHolder.mobileOrEmailTv.setText(this.usersToBeInvitedDataList.get(i).emailID);
            }
            userToBeInvitedViewHolder.userNameTV.setText(this.usersToBeInvitedDataList.get(i).name);
            userToBeInvitedViewHolder.deleteCrossMarkIV.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UsersToBeInvitedAdapter.this.usersToBeInvitedDataList.remove(i);
                    InviteUsersFragmentNewVD.this.usersToBeInvitedAdapter.notifyDataSetChanged();
                    String format = String.format(InviteUsersFragmentNewVD.this.getResources().getString(R.string.manageUser_lbl_numberOfContancts), new Object[]{Integer.valueOf(InviteUsersFragmentNewVD.this.userToBeInvitedList.size()), Integer.valueOf(InviteUsersFragmentNewVD.this.mMaxMemberCount - InviteUsersFragmentNewVD.this.mExistingMemberCount)});
                    if (InviteUsersFragmentNewVD.this.userToBeInvitedList.size() == 0) {
                        InviteUsersFragmentNewVD.this.mNumOfUserTextView.setVisibility(8);
                        return;
                    }
                    InviteUsersFragmentNewVD.this.mNumOfUserTextView.setVisibility(0);
                    InviteUsersFragmentNewVD.this.mNumOfUserTextView.setText(format);
                }
            });
        }

        public int getItemCount() {
            return this.usersToBeInvitedDataList.size();
        }
    }

    public class UserToBeInvitedViewHolder extends RecyclerView.ViewHolder {
        ImageView deleteCrossMarkIV;
        TextView mobileOrEmailTv;
        TextView userNameTV;

        public UserToBeInvitedViewHolder(View view) {
            super(view);
            this.userNameTV = (TextView) view.findViewById(R.id.text_view_user_name_ivite_users);
            this.mobileOrEmailTv = (TextView) view.findViewById(R.id.text_view_user_name_or_mobile_number);
            this.deleteCrossMarkIV = (ImageView) view.findViewById(R.id.image_button_clear);
        }
    }

    public void validateEmailAndPhoneNumber(String str, String str2, String str3) {
        Logger.m45d("code", str3);
        if (ValidationUtils.getCountryCodeOfPhonenumber(str3, str2) != null && !ValidationUtils.getCountryCodeOfPhonenumber(str3, str2).isEmpty()) {
            str3 = Marker.ANY_NON_NULL_MARKER + ValidationUtils.getCountryCodeOfPhonenumber(str3, str2);
        }
        if (str2 != null && !str2.isEmpty() && str3 != null && !str3.isEmpty()) {
            if (PhoneNumberUtil.getInstance().isNANPACountry(getContext().getString(CountryUtils.getByCode(getContext(), CountryUtils.getAllCountries(getContext()), str3).getIso()).toUpperCase()) && str3.length() == 5 && str2.length() == 10) {
                str2 = str2.substring(3);
            }
        }
        String removeCountryCodeFromPhoneNumber = ValidationUtils.removeCountryCodeFromPhoneNumber(getContext(), str3, str2);
        String str4 = str3 + removeCountryCodeFromPhoneNumber;
        String str5 = null;
        if (!ValidationUtils.isEmailAddressValid(str)) {
            if (ValidationUtils.isMobileNumberValidInviteUser(UserInfo.getCurrentUserInfo(getCoreActivity()).phoneNumber, str4)) {
                str5 = ValidationUtils.formatMobileNumberValidInviteUser(UserInfo.getCurrentUserInfo(getCoreActivity()).phoneNumber, str4);
                str = null;
            } else {
                try {
                    Long.parseLong(str4);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.m47e("EXCEPTION", e.getMessage());
                }
                if (this.mMobileNumberLayout.getVisibility() == 0 && removeCountryCodeFromPhoneNumber != null) {
                    if (!removeCountryCodeFromPhoneNumber.isEmpty()) {
                        openInvalidMobileNumberDialog(removeCountryCodeFromPhoneNumber, str);
                    } else {
                        showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.common_alert_phnNoFieldCannotBeEmpty));
                    }
                }
                if (this.mEnterEmail.getVisibility() == 0) {
                    if (str != null) {
                        if (!str.isEmpty()) {
                            openInvalidMobileNumberDialog(removeCountryCodeFromPhoneNumber, str);
                        } else {
                            showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.common_alert_emailFieldCannotBeEmpty));
                        }
                    } else if (removeCountryCodeFromPhoneNumber == null) {
                        showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.common_alert_phnNoFieldCannotBeEmpty));
                    } else if (!removeCountryCodeFromPhoneNumber.isEmpty()) {
                        openInvalidMobileNumberDialog(removeCountryCodeFromPhoneNumber, str);
                    }
                }
                str = null;
            }
        }
        if (!IsInvitingMySelf(str, str5)) {
            addDataToList(str5, str, this.mContactName);
        }
    }

    private boolean IsInvitingMySelf(String str, String str2) {
        if (str2 != null && UserInfo.getCurrentUserInfo(getCoreActivity()).phoneNumber != null && !str2.isEmpty() && UserInfo.getCurrentUserInfo(getCoreActivity()).phoneNumber.equalsIgnoreCase(str2)) {
            showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.manageUser_alert_inviteError));
            return true;
        } else if (str == null || UserInfo.getCurrentUserInfo(getCoreActivity()).email == null || str.isEmpty() || !UserInfo.getCurrentUserInfo(getCoreActivity()).email.equalsIgnoreCase(str)) {
            return false;
        } else {
            showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.manageUser_alert_inviteError));
            return true;
        }
    }

    public void validateEmail(String str) {
        if (!ValidationUtils.isEmailAddressValid(str)) {
            if (str != null && !str.isEmpty()) {
                openInvalidMobileNumberDialog((String) null, str);
            }
            str = null;
        }
        if (!IsInvitingMySelf(str, (String) null)) {
            addDataToList((String) null, str, this.mContactName);
        }
    }

    private void openInvalidMobileNumberDialog(String str, String str2) {
        InvalidEmailAndMobileNumberDialog invalidEmailAndMobileNumberDialog = new InvalidEmailAndMobileNumberDialog(getContext(), this);
        if (str == null) {
            invalidEmailAndMobileNumberDialog.setText(this.mContactName, getResources().getString(R.string.manageUser_lbl_invalidEmail), getResources().getString(R.string.manageUser_lbl_invalidEmailDesc), str, str2, this.mCountryCodeTextView.getText().toString(), ((BitmapDrawable) this.mCountryFlag.getDrawable()).getBitmap());
        } else if (!str.isEmpty()) {
            invalidEmailAndMobileNumberDialog.setText(this.mContactName, getResources().getString(R.string.common_alert_invalidMobNo), getResources().getString(R.string.manageUser_lbl_validMobileNumberDesc), str, str2, this.mCountryCodeTextView.getText().toString(), ((BitmapDrawable) this.mCountryFlag.getDrawable()).getBitmap());
        }
        invalidEmailAndMobileNumberDialog.setParentView(this.mParent);
        invalidEmailAndMobileNumberDialog.show();
    }

    private void openInvalidMobileNumberDialogFromContactsWithoutCountryCode(String str, String str2) {
        CountryCodeUIConfiguration countryCodeUIConfigurationFromCountryObject = CountryCodeUIConfiguration.getCountryCodeUIConfigurationFromCountryObject(getContext(), CountryUtils.getByCode(getContext(), CountryUtils.getAllCountries(getContext()), ValidationUtils.getCountryCodeFromISOCode(UserInfo.getCurrentUserInfo(getCoreActivity()).userAddress.getCountryCode())));
        countryCodeUIConfigurationFromCountryObject.setCountryCodeString(getContext());
        InvalidEmailAndMobileNumberDialog invalidEmailAndMobileNumberDialog = new InvalidEmailAndMobileNumberDialog(getContext(), this);
        if (str == null) {
            invalidEmailAndMobileNumberDialog.setText(this.mContactName, getResources().getString(R.string.manageUser_lbl_invalidEmail), getResources().getString(R.string.manageUser_lbl_invalidEmailDesc), str, str2, this.mCountryCodeTextView.getText().toString(), ((BitmapDrawable) this.mCountryFlag.getDrawable()).getBitmap());
        } else if (!str.isEmpty()) {
            invalidEmailAndMobileNumberDialog.setText(this.mContactName, getResources().getString(R.string.common_alert_invalidMobNo), getResources().getString(R.string.manageUser_lbl_validMobileNumberDesc), str, str2, this.mCountryCodeTextView.getText().toString(), ((BitmapDrawable) this.mCountryFlag.getDrawable()).getBitmap());
        }
        invalidEmailAndMobileNumberDialog.setParentView(this.mParent);
        invalidEmailAndMobileNumberDialog.updateCountryCodeSelectionOnCountryCodeChange(countryCodeUIConfigurationFromCountryObject);
        invalidEmailAndMobileNumberDialog.show();
    }

    private void openInvalidMobileNumberDialogFromContactsWithCountryCode(String str, String str2, String str3) {
        CountryCodeUIConfiguration countryCodeUIConfigurationFromCountryObject = CountryCodeUIConfiguration.getCountryCodeUIConfigurationFromCountryObject(getContext(), CountryUtils.getByCode(getContext(), CountryUtils.getAllCountries(getContext()), Marker.ANY_NON_NULL_MARKER + str3));
        countryCodeUIConfigurationFromCountryObject.setCountryCodeString(getContext());
        InvalidEmailAndMobileNumberDialog invalidEmailAndMobileNumberDialog = new InvalidEmailAndMobileNumberDialog(getContext(), this);
        if (str == null) {
            invalidEmailAndMobileNumberDialog.setText(this.mContactName, getResources().getString(R.string.manageUser_lbl_invalidEmail), getResources().getString(R.string.manageUser_lbl_invalidEmailDesc), str, str2, this.mCountryCodeTextView.getText().toString(), ((BitmapDrawable) this.mCountryFlag.getDrawable()).getBitmap());
        } else if (!str.isEmpty()) {
            invalidEmailAndMobileNumberDialog.setText(this.mContactName, getResources().getString(R.string.common_alert_invalidMobNo), getResources().getString(R.string.manageUser_lbl_validMobileNumberDesc), str, str2, this.mCountryCodeTextView.getText().toString(), ((BitmapDrawable) this.mCountryFlag.getDrawable()).getBitmap());
        }
        invalidEmailAndMobileNumberDialog.setParentView(this.mParent);
        invalidEmailAndMobileNumberDialog.updateCountryCodeSelectionOnCountryCodeChange(countryCodeUIConfigurationFromCountryObject);
        invalidEmailAndMobileNumberDialog.show();
    }

    public void updateCountryCodeSelectionOnCountryCodeChange(CountryCodeUIConfiguration countryCodeUIConfiguration) {
        this.mCountryCodeTextView.setText(countryCodeUIConfiguration.getCountryCodeString());
        this.mCountryFlag.setImageResource(countryCodeUIConfiguration.getCountryFlag());
        this.mEnterPhoneNumber.setText("");
        if (this.mEnterPhoneNumber.getText() != null) {
            this.mEnterPhoneNumber.getText().clear();
        }
        this.mEnterPhoneNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtils.getMaxLengthOfMobileNumberBasedOnCountryCode(CountryCodeUIConfiguration.CURRENT.getCountryCodeString()))});
    }

    private void showCountryCodeSelectionDialog() {
        final CountryCodeDialog countryCodeDialog = new CountryCodeDialog(getActivity());
        countryCodeDialog.getCountryCodeRecyclerViewAdapter().setOnItemSelectionListener(new SelectCountryCodeRecyclerViewAdapter.OnItemSelectionListener() {
            public void onItemSelected(View view, CountryCodeUIConfiguration countryCodeUIConfiguration) {
                InviteUsersFragmentNewVD.this.updateCountryCodeSelectionOnCountryCodeChange(countryCodeUIConfiguration);
                countryCodeDialog.dismiss();
            }
        });
        countryCodeDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
            }
        });
        countryCodeDialog.show();
        countryCodeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        WindowManager.LayoutParams attributes = countryCodeDialog.getWindow().getAttributes();
        attributes.dimAmount = 0.2f;
        countryCodeDialog.getWindow().setAttributes(attributes);
        countryCodeDialog.getWindow().addFlags(2);
    }

    public boolean isContactAlreadyExist(List<UsersToBeInvitedData> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).emailID != null && !list.get(i).emailID.isEmpty() && list.get(i).emailID.equals(str)) {
                return true;
            }
            if (list.get(i).phoneNumber != null && !list.get(i).phoneNumber.isEmpty() && list.get(i).phoneNumber.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
