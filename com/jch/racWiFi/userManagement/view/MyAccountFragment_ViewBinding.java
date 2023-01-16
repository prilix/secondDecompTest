package com.jch.racWiFi.userManagement.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountFragment_ViewBinding implements Unbinder {
    private MyAccountFragment target;
    private View view7f0a0152;
    private View view7f0a015e;
    private View view7f0a0161;
    private View view7f0a03fc;
    private View view7f0a0533;
    private View view7f0a0598;
    private View view7f0a059e;
    private View view7f0a05b4;

    public MyAccountFragment_ViewBinding(final MyAccountFragment myAccountFragment, View view) {
        this.target = myAccountFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_change_password, "field 'mChangePassword' and method 'OnClickChangePassword'");
        myAccountFragment.mChangePassword = (Button) C0840Utils.castView(findRequiredView, R.id.button_change_password, "field 'mChangePassword'", Button.class);
        this.view7f0a0152 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountFragment.OnClickChangePassword((Button) C0840Utils.castParam(view, "doClick", 0, "OnClickChangePassword", 0, Button.class));
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.image_button_menu, "field 'mMenu' and method 'OnClickMenu'");
        myAccountFragment.mMenu = (ImageButton) C0840Utils.castView(findRequiredView2, R.id.image_button_menu, "field 'mMenu'", ImageButton.class);
        this.view7f0a03fc = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountFragment.OnClickMenu((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickMenu", 0, ImageButton.class));
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.layout_profile_picture, "field 'mProfilePictureLayout' and method 'OnClickProfilePicture'");
        myAccountFragment.mProfilePictureLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView3, R.id.layout_profile_picture, "field 'mProfilePictureLayout'", ConstraintLayout.class);
        this.view7f0a05b4 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountFragment.OnClickProfilePicture((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "OnClickProfilePicture", 0, ConstraintLayout.class));
            }
        });
        View findRequiredView4 = C0840Utils.findRequiredView(view, R.id.layout_name, "field 'mNameLayout' and method 'OnClickName'");
        myAccountFragment.mNameLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView4, R.id.layout_name, "field 'mNameLayout'", ConstraintLayout.class);
        this.view7f0a059e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountFragment.OnClickName((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "OnClickName", 0, ConstraintLayout.class));
            }
        });
        View findRequiredView5 = C0840Utils.findRequiredView(view, R.id.layout_address, "field 'mAddressLayout' and method 'OnClickAddress'");
        myAccountFragment.mAddressLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView5, R.id.layout_address, "field 'mAddressLayout'", ConstraintLayout.class);
        this.view7f0a0533 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountFragment.OnClickAddress((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "OnClickAddress", 0, ConstraintLayout.class));
            }
        });
        myAccountFragment.mUserProfilePhoto = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_profile_picture_my_account, "field 'mUserProfilePhoto'", ImageView.class);
        myAccountFragment.mUserNameSubText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_name_my_account, "field 'mUserNameSubText'", TextView.class);
        myAccountFragment.mUserAddressSubText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_address_my_account, "field 'mUserAddressSubText'", TextView.class);
        myAccountFragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        View findRequiredView6 = C0840Utils.findRequiredView(view, R.id.button_delete_my_account, "field 'deleteAccountButton' and method 'OnClickDeleteAccount'");
        myAccountFragment.deleteAccountButton = (LinearLayout) C0840Utils.castView(findRequiredView6, R.id.button_delete_my_account, "field 'deleteAccountButton'", LinearLayout.class);
        this.view7f0a015e = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountFragment.OnClickDeleteAccount((LinearLayout) C0840Utils.castParam(view, "doClick", 0, "OnClickDeleteAccount", 0, LinearLayout.class));
            }
        });
        View findRequiredView7 = C0840Utils.findRequiredView(view, R.id.layout_manage_homes, "field 'manageHomesLayout' and method 'OnClickManageHomes'");
        myAccountFragment.manageHomesLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView7, R.id.layout_manage_homes, "field 'manageHomesLayout'", ConstraintLayout.class);
        this.view7f0a0598 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountFragment.OnClickManageHomes((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "OnClickManageHomes", 0, ConstraintLayout.class));
            }
        });
        myAccountFragment.mChangeAccountInfotmation = (Button) C0840Utils.findRequiredViewAsType(view, R.id.button_change_account_information, "field 'mChangeAccountInfotmation'", Button.class);
        myAccountFragment.manageHomeTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_manage_homes, "field 'manageHomeTextView'", TextView.class);
        myAccountFragment.forwardArrowImage = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.manageForwardArrowImage, "field 'forwardArrowImage'", ImageView.class);
        myAccountFragment.viewLine = C0840Utils.findRequiredView(view, R.id.view49, "field 'viewLine'");
        View findRequiredView8 = C0840Utils.findRequiredView(view, R.id.button_disable_account, "method 'OnClickDisableAccount'");
        this.view7f0a0161 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountFragment.OnClickDisableAccount((LinearLayout) C0840Utils.castParam(view, "doClick", 0, "OnClickDisableAccount", 0, LinearLayout.class));
            }
        });
    }

    public void unbind() {
        MyAccountFragment myAccountFragment = this.target;
        if (myAccountFragment != null) {
            this.target = null;
            myAccountFragment.mChangePassword = null;
            myAccountFragment.mMenu = null;
            myAccountFragment.mProfilePictureLayout = null;
            myAccountFragment.mNameLayout = null;
            myAccountFragment.mAddressLayout = null;
            myAccountFragment.mUserProfilePhoto = null;
            myAccountFragment.mUserNameSubText = null;
            myAccountFragment.mUserAddressSubText = null;
            myAccountFragment.mParent = null;
            myAccountFragment.deleteAccountButton = null;
            myAccountFragment.manageHomesLayout = null;
            myAccountFragment.mChangeAccountInfotmation = null;
            myAccountFragment.manageHomeTextView = null;
            myAccountFragment.forwardArrowImage = null;
            myAccountFragment.viewLine = null;
            this.view7f0a0152.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0152 = null;
            this.view7f0a03fc.setOnClickListener((View.OnClickListener) null);
            this.view7f0a03fc = null;
            this.view7f0a05b4.setOnClickListener((View.OnClickListener) null);
            this.view7f0a05b4 = null;
            this.view7f0a059e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a059e = null;
            this.view7f0a0533.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0533 = null;
            this.view7f0a015e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a015e = null;
            this.view7f0a0598.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0598 = null;
            this.view7f0a0161.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0161 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
