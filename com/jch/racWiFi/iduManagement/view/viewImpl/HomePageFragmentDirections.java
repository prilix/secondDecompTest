package com.jch.racWiFi.iduManagement.view.viewImpl;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class HomePageFragmentDirections {
    private HomePageFragmentDirections() {
    }

    public static NavDirections actionHomePageFragmentToManageDevicesFragment() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_manageDevicesFragment);
    }

    public static NavDirections actionHomePageFragmentToQrScanFragment() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_qrScanFragment);
    }

    public static NavDirections actionHomePageFragmentToIndividualIDUControlFragment() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_individualIDUControlFragment);
    }

    public static NavDirections actionHomePageFragmentToMyAccountFragment2() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_myAccountFragment2);
    }

    public static NavDirections actionHomePageFragmentToManageUsersFragment() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_manageUsersFragment);
    }

    public static NavDirections actionHomePageFragmentToCustomerCareFragment() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_customerCareFragment);
    }

    public static NavDirections actionHomePageFragmentToSettingsFragment() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_settingsFragment);
    }

    public static NavDirections actionHomePageFragmentToHelpFragment() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_helpFragment);
    }

    public static NavDirections actionHomePageFragmentSelf() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_self);
    }

    public static NavDirections actionHomePageFragmentToIndividualIDUControlFragmentV3() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_individualIDUControlFragmentV3);
    }

    public static NavDirections actionHomePageFragmentToWeeklyTimerDevicesFragment() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_weeklyTimerDevicesFragment);
    }

    public static NavDirections actionIndividualIDUControlFragmentToPrivacyPolicyFragment() {
        return new ActionOnlyNavDirections(R.id.action_individualIDUControlFragment_to_privacyPolicyFragment);
    }

    public static NavDirections actionHomePageFragmentToInviteUsersFragmentNewVd() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_inviteUsersFragmentNewVd);
    }

    public static NavDirections actionHomePageFragmentToOnBoardNavGraph() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_on_board_nav_graph);
    }

    public static NavDirections actionHomePageFragmentToSmartFenceNavGraph() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_smart_fence_navGraph);
    }

    public static NavDirections actionHomePageFragmentToHolidayModeFragment() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_holiday_mode_fragment);
    }

    public static NavDirections actionHomePageFragmentToEnergyConsumptionNavGraph() {
        return new ActionOnlyNavDirections(R.id.action_homePageFragment_to_energy_consumption_NavGraph);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
