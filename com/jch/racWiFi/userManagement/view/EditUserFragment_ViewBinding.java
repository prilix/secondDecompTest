package com.jch.racWiFi.userManagement.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch_hitachi.aircloudglobal.R;
import info.hoang8f.android.segmented.SegmentedGroup;

public class EditUserFragment_ViewBinding implements Unbinder {
    private EditUserFragment target;
    private View view7f0a011e;
    private View view7f0a0612;
    private View view7f0a0a77;

    public EditUserFragment_ViewBinding(final EditUserFragment editUserFragment, View view) {
        this.target = editUserFragment;
        editUserFragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.linear_layout_remove_user, "field 'mRemoveUser' and method 'OnClickRemoveUser'");
        editUserFragment.mRemoveUser = (LinearLayout) C0840Utils.castView(findRequiredView, R.id.linear_layout_remove_user, "field 'mRemoveUser'", LinearLayout.class);
        this.view7f0a0612 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editUserFragment.OnClickRemoveUser((LinearLayout) C0840Utils.castParam(view, "doClick", 0, "OnClickRemoveUser", 0, LinearLayout.class));
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'onClickBack'");
        editUserFragment.mBack = (ImageButton) C0840Utils.castView(findRequiredView2, R.id.back_button, "field 'mBack'", ImageButton.class);
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editUserFragment.onClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickBack", 0, ImageButton.class));
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.text_view_save, "field 'mSave' and method 'onClickSave'");
        editUserFragment.mSave = (ImageButton) C0840Utils.castView(findRequiredView3, R.id.text_view_save, "field 'mSave'", ImageButton.class);
        this.view7f0a0a77 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editUserFragment.onClickSave((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickSave", 0, ImageButton.class));
            }
        });
        editUserFragment.mUserProfilePhoto = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_user_profile_edit_user, "field 'mUserProfilePhoto'", ImageView.class);
        editUserFragment.mUserName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_user_name_edit_user, "field 'mUserName'", TextView.class);
        editUserFragment.mUserRoleSegmentedGroup = (SegmentedGroup) C0840Utils.findRequiredViewAsType(view, R.id.segment_user, "field 'mUserRoleSegmentedGroup'", SegmentedGroup.class);
        editUserFragment.mOwnerSelection = (RadioButton) C0840Utils.findRequiredViewAsType(view, R.id.radio_button_owner, "field 'mOwnerSelection'", RadioButton.class);
        editUserFragment.mMemberSelection = (RadioButton) C0840Utils.findRequiredViewAsType(view, R.id.radio_button_member, "field 'mMemberSelection'", RadioButton.class);
        editUserFragment.mChildSelection = (RadioButton) C0840Utils.findRequiredViewAsType(view, R.id.radio_button_child, "field 'mChildSelection'", RadioButton.class);
    }

    public void unbind() {
        EditUserFragment editUserFragment = this.target;
        if (editUserFragment != null) {
            this.target = null;
            editUserFragment.mParent = null;
            editUserFragment.mRemoveUser = null;
            editUserFragment.mBack = null;
            editUserFragment.mSave = null;
            editUserFragment.mUserProfilePhoto = null;
            editUserFragment.mUserName = null;
            editUserFragment.mUserRoleSegmentedGroup = null;
            editUserFragment.mOwnerSelection = null;
            editUserFragment.mMemberSelection = null;
            editUserFragment.mChildSelection = null;
            this.view7f0a0612.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0612 = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a0a77.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0a77 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
