package com.jch.racWiFi.userManagement.view.viewImpl;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch_hitachi.aircloudglobal.R;

public class UserPermissionsManageUsersFragment_ViewBinding implements Unbinder {
    private UserPermissionsManageUsersFragment target;
    private View view7f0a0515;
    private View view7f0a0bd2;

    public UserPermissionsManageUsersFragment_ViewBinding(final UserPermissionsManageUsersFragment userPermissionsManageUsersFragment, View view) {
        this.target = userPermissionsManageUsersFragment;
        userPermissionsManageUsersFragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        userPermissionsManageUsersFragment.mRecyclerViewUserPermissions = (RecyclerView) C0840Utils.findOptionalViewAsType(view, R.id.rv_idu_permissions_viewer, "field 'mRecyclerViewUserPermissions'", RecyclerView.class);
        userPermissionsManageUsersFragment.mListViewUserPermissions = (ListView) C0840Utils.findOptionalViewAsType(view, R.id.rv_idu_permissions_viewer_listvew, "field 'mListViewUserPermissions'", ListView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.tv_save, "method 'onClickSave'");
        userPermissionsManageUsersFragment.mSave = (ImageButton) C0840Utils.castView(findRequiredView, R.id.tv_save, "field 'mSave'", ImageButton.class);
        this.view7f0a0bd2 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userPermissionsManageUsersFragment.onClickSave((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickSave", 0, ImageButton.class));
            }
        });
        userPermissionsManageUsersFragment.mTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_device_settings_title, "field 'mTitle'", TextView.class);
        userPermissionsManageUsersFragment.tvAllPermissionsText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.tv_permission_type, "field 'tvAllPermissionsText'", TextView.class);
        userPermissionsManageUsersFragment.cbAllMemberPermission = (TriStateCheckbox) C0840Utils.findRequiredViewAsType(view, R.id.cb_member_permission, "field 'cbAllMemberPermission'", TriStateCheckbox.class);
        userPermissionsManageUsersFragment.cbAllChildPermission = (TriStateCheckbox) C0840Utils.findRequiredViewAsType(view, R.id.cb_child_permission, "field 'cbAllChildPermission'", TriStateCheckbox.class);
        userPermissionsManageUsersFragment.linearMember = (LinearLayout) C0840Utils.findRequiredViewAsType(view, R.id.linear_member_permission, "field 'linearMember'", LinearLayout.class);
        userPermissionsManageUsersFragment.linearChild = (LinearLayout) C0840Utils.findRequiredViewAsType(view, R.id.linear_child_permission, "field 'linearChild'", LinearLayout.class);
        userPermissionsManageUsersFragment.tvDeviceName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.tv_device_name, "field 'tvDeviceName'", TextView.class);
        userPermissionsManageUsersFragment.tvMember = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_member, "field 'tvMember'", TextView.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.iv_back, "method 'onClickBack'");
        this.view7f0a0515 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userPermissionsManageUsersFragment.onClickBack();
            }
        });
    }

    public void unbind() {
        UserPermissionsManageUsersFragment userPermissionsManageUsersFragment = this.target;
        if (userPermissionsManageUsersFragment != null) {
            this.target = null;
            userPermissionsManageUsersFragment.mParent = null;
            userPermissionsManageUsersFragment.mRecyclerViewUserPermissions = null;
            userPermissionsManageUsersFragment.mListViewUserPermissions = null;
            userPermissionsManageUsersFragment.mSave = null;
            userPermissionsManageUsersFragment.mTitle = null;
            userPermissionsManageUsersFragment.tvAllPermissionsText = null;
            userPermissionsManageUsersFragment.cbAllMemberPermission = null;
            userPermissionsManageUsersFragment.cbAllChildPermission = null;
            userPermissionsManageUsersFragment.linearMember = null;
            userPermissionsManageUsersFragment.linearChild = null;
            userPermissionsManageUsersFragment.tvDeviceName = null;
            userPermissionsManageUsersFragment.tvMember = null;
            this.view7f0a0bd2.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0bd2 = null;
            this.view7f0a0515.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0515 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
