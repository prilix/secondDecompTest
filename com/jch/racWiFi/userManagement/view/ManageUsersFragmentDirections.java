package com.jch.racWiFi.userManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ManageUsersFragmentDirections {
    private ManageUsersFragmentDirections() {
    }

    public static NavDirections actionManageUsersFragmentToUserPermissionsManageUsersFragment() {
        return new ActionOnlyNavDirections(R.id.action_manageUsersFragment_to_userPermissionsManageUsersFragment);
    }

    public static NavDirections actionManageUsersFragmentToHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_manageUsersFragment_to_homePageFragment);
    }

    public static NavDirections actionManageUsersFragmentToEditUserFragment() {
        return new ActionOnlyNavDirections(R.id.action_manageUsersFragment_to_editUserFragment);
    }

    public static NavDirections actionManageUserfragmentToInviteUserFragmentNewVD() {
        return new ActionOnlyNavDirections(R.id.action_manageUserfragment_to_inviteUserFragmentNewVD);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
