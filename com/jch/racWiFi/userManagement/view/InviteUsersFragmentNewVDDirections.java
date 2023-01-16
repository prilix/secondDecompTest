package com.jch.racWiFi.userManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class InviteUsersFragmentNewVDDirections {
    private InviteUsersFragmentNewVDDirections() {
    }

    public static NavDirections actionInviteUsersFragmentNewVDToManageUsersFragment() {
        return new ActionOnlyNavDirections(R.id.action_inviteUsersFragmentNewVD_to_manageUsersFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
