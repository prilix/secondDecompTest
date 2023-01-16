package com.jch.racWiFi.userManagement.view.viewImpl;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class UserPermissionsManageUsersFragmentDirections {
    private UserPermissionsManageUsersFragmentDirections() {
    }

    public static NavDirections actionUserPermissionsManageUsersFragmentToManageUsersFragment() {
        return new ActionOnlyNavDirections(R.id.action_userPermissionsManageUsersFragment_to_manageUsersFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
