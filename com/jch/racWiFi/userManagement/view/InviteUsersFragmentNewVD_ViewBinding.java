package com.jch.racWiFi.userManagement.view;

import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class InviteUsersFragmentNewVD_ViewBinding implements Unbinder {
    private InviteUsersFragmentNewVD target;
    private View view7f0a011e;
    private View view7f0a0170;
    private View view7f0a017c;
    private View view7f0a0532;
    private View view7f0a055c;
    private View view7f0a0613;
    private View view7f0a092f;
    private View view7f0a09f3;

    public InviteUsersFragmentNewVD_ViewBinding(final InviteUsersFragmentNewVD inviteUsersFragmentNewVD, View view) {
        this.target = inviteUsersFragmentNewVD;
        inviteUsersFragmentNewVD.mCountryCodeTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_country_number_code_login, "field 'mCountryCodeTextView'", TextView.class);
        inviteUsersFragmentNewVD.mCountryFlag = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_flag_login, "field 'mCountryFlag'", ImageView.class);
        inviteUsersFragmentNewVD.mEnterPhoneNumber = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_mobile_number, "field 'mEnterPhoneNumber'", EditText.class);
        inviteUsersFragmentNewVD.mEnterEmail = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_email, "field 'mEnterEmail'", EditText.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_country_code, "field 'mCountryCodeLayout' and method 'onClickCountryCodeSelection'");
        inviteUsersFragmentNewVD.mCountryCodeLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_country_code, "field 'mCountryCodeLayout'", ConstraintLayout.class);
        this.view7f0a055c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteUsersFragmentNewVD.onClickCountryCodeSelection((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickCountryCodeSelection", 0, ConstraintLayout.class));
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.linear_layout_select_contacts, "field 'mSelectContacts' and method 'onClickSelectContacts'");
        inviteUsersFragmentNewVD.mSelectContacts = (LinearLayout) C0840Utils.castView(findRequiredView2, R.id.linear_layout_select_contacts, "field 'mSelectContacts'", LinearLayout.class);
        this.view7f0a0613 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteUsersFragmentNewVD.onClickSelectContacts((LinearLayout) C0840Utils.castParam(view, "doClick", 0, "onClickSelectContacts", 0, LinearLayout.class));
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.layout_add_members_manage_users, "field 'mAddMembers' and method 'onClickAddMembers'");
        inviteUsersFragmentNewVD.mAddMembers = (LinearLayout) C0840Utils.castView(findRequiredView3, R.id.layout_add_members_manage_users, "field 'mAddMembers'", LinearLayout.class);
        this.view7f0a0532 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteUsersFragmentNewVD.onClickAddMembers((LinearLayout) C0840Utils.castParam(view, "doClick", 0, "onClickAddMembers", 0, LinearLayout.class));
            }
        });
        View findRequiredView4 = C0840Utils.findRequiredView(view, R.id.button_no, "field 'mMayBeLater' and method 'onClickNoButton'");
        inviteUsersFragmentNewVD.mMayBeLater = (Button) C0840Utils.castView(findRequiredView4, R.id.button_no, "field 'mMayBeLater'", Button.class);
        this.view7f0a0170 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteUsersFragmentNewVD.onClickNoButton((Button) C0840Utils.castParam(view, "doClick", 0, "onClickNoButton", 0, Button.class));
            }
        });
        View findRequiredView5 = C0840Utils.findRequiredView(view, R.id.button_send_invite, "field 'mSendInvite' and method 'onClickSendInvite'");
        inviteUsersFragmentNewVD.mSendInvite = (Button) C0840Utils.castView(findRequiredView5, R.id.button_send_invite, "field 'mSendInvite'", Button.class);
        this.view7f0a017c = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteUsersFragmentNewVD.onClickSendInvite((Button) C0840Utils.castParam(view, "doClick", 0, "onClickSendInvite", 0, Button.class));
            }
        });
        View findRequiredView6 = C0840Utils.findRequiredView(view, R.id.text_view_mobile_number, "field 'mMobileNumberSwitch' and method 'onClickMobileButton'");
        inviteUsersFragmentNewVD.mMobileNumberSwitch = (TextView) C0840Utils.castView(findRequiredView6, R.id.text_view_mobile_number, "field 'mMobileNumberSwitch'", TextView.class);
        this.view7f0a09f3 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteUsersFragmentNewVD.onClickMobileButton((TextView) C0840Utils.castParam(view, "doClick", 0, "onClickMobileButton", 0, TextView.class));
            }
        });
        View findRequiredView7 = C0840Utils.findRequiredView(view, R.id.text_view_email, "field 'mEmailSwitch' and method 'onClickEmailButton'");
        inviteUsersFragmentNewVD.mEmailSwitch = (TextView) C0840Utils.castView(findRequiredView7, R.id.text_view_email, "field 'mEmailSwitch'", TextView.class);
        this.view7f0a092f = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteUsersFragmentNewVD.onClickEmailButton((TextView) C0840Utils.castParam(view, "doClick", 0, "onClickEmailButton", 0, TextView.class));
            }
        });
        inviteUsersFragmentNewVD.mMobileNumberLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.mobile_number_layout, "field 'mMobileNumberLayout'", ConstraintLayout.class);
        View findRequiredView8 = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBackButton' and method 'onClickBackButton'");
        inviteUsersFragmentNewVD.mBackButton = (ImageButton) C0840Utils.castView(findRequiredView8, R.id.back_button, "field 'mBackButton'", ImageButton.class);
        this.view7f0a011e = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteUsersFragmentNewVD.onClickBackButton((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickBackButton", 0, ImageButton.class));
            }
        });
        inviteUsersFragmentNewVD.mMobileSelectionHighlightView = C0840Utils.findRequiredView(view, R.id.mobile_number_selection_highlight, "field 'mMobileSelectionHighlightView'");
        inviteUsersFragmentNewVD.mEmailSelectionHighlightView = C0840Utils.findRequiredView(view, R.id.email_selection_highlight, "field 'mEmailSelectionHighlightView'");
        inviteUsersFragmentNewVD.mRecyclerView = (RecyclerView) C0840Utils.findRequiredViewAsType(view, R.id.recycler_view_mange_users, "field 'mRecyclerView'", RecyclerView.class);
        inviteUsersFragmentNewVD.mNumOfUserTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_num_of_users, "field 'mNumOfUserTextView'", TextView.class);
        inviteUsersFragmentNewVD.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
    }

    public void unbind() {
        InviteUsersFragmentNewVD inviteUsersFragmentNewVD = this.target;
        if (inviteUsersFragmentNewVD != null) {
            this.target = null;
            inviteUsersFragmentNewVD.mCountryCodeTextView = null;
            inviteUsersFragmentNewVD.mCountryFlag = null;
            inviteUsersFragmentNewVD.mEnterPhoneNumber = null;
            inviteUsersFragmentNewVD.mEnterEmail = null;
            inviteUsersFragmentNewVD.mCountryCodeLayout = null;
            inviteUsersFragmentNewVD.mSelectContacts = null;
            inviteUsersFragmentNewVD.mAddMembers = null;
            inviteUsersFragmentNewVD.mMayBeLater = null;
            inviteUsersFragmentNewVD.mSendInvite = null;
            inviteUsersFragmentNewVD.mMobileNumberSwitch = null;
            inviteUsersFragmentNewVD.mEmailSwitch = null;
            inviteUsersFragmentNewVD.mMobileNumberLayout = null;
            inviteUsersFragmentNewVD.mBackButton = null;
            inviteUsersFragmentNewVD.mMobileSelectionHighlightView = null;
            inviteUsersFragmentNewVD.mEmailSelectionHighlightView = null;
            inviteUsersFragmentNewVD.mRecyclerView = null;
            inviteUsersFragmentNewVD.mNumOfUserTextView = null;
            inviteUsersFragmentNewVD.mParent = null;
            this.view7f0a055c.setOnClickListener((View.OnClickListener) null);
            this.view7f0a055c = null;
            this.view7f0a0613.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0613 = null;
            this.view7f0a0532.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0532 = null;
            this.view7f0a0170.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0170 = null;
            this.view7f0a017c.setOnClickListener((View.OnClickListener) null);
            this.view7f0a017c = null;
            this.view7f0a09f3.setOnClickListener((View.OnClickListener) null);
            this.view7f0a09f3 = null;
            this.view7f0a092f.setOnClickListener((View.OnClickListener) null);
            this.view7f0a092f = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
