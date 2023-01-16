package com.jch.racWiFi;

public class UrlPaths {

    public static class EnergyConsumptionPath {
        public static final String COST_DATA_FETCHING_PATH = "/rac/energy-settings/data";
        public static final String ENERGY_PATH = "/rac/energy-consumptions/consumptions-data";
        public static final String SAVE_COST_DATA_PATH = "/rac/energy-settings/save";
    }

    public static class PermissionPath {
        public static final String FETCH_CONFIG_AND_ROLE_PATH = "/rac/config/init";
        public static final String FETCH_FUNTIONAL_ACCESS = "/rac/ownership/groups/{memberId}/permissions/has-functional-access";
        public static final String FETCH_PERMISSION_DATA = "/rac/ownership/groups/";
        public static final String FETCH_PERMISSION_DATA_ALL = "/rac/ownership/groups/{memberId}/permissions";
        public static final String FETCH_PERMISSION_DATA_RAC_SPECIFIC = "/rac/ownership/groups/{memberId}/permissions/{racId}";
        public static final String MEMBER_ID_PATH_PARAM = "memberId";
        public static final String RAC_ID_PATH_PARAM = "racId";
        public static final String SAVE_PERMISSION_DATA = "/rac/ownership/edit-permission";
    }

    public static class UserPermissionPaths {
        public static final String FAMILY_CHANGE_ROLE_PATH = "/rac/family-account/change-role";
        public static final String FAMILY_FETCH_PATH = "/rac/family-account/members";
    }
}
