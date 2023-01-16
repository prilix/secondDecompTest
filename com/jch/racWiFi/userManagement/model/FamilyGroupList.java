package com.jch.racWiFi.userManagement.model;

import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.userManagement.model.FamilyGroupsModels;
import java.util.ArrayList;
import java.util.Arrays;

public class FamilyGroupList extends ArrayList<FamilyGroupsModels.FamilyResult> {
    private static FamilyGroupsModels.FamilyResult CURRENT = new FamilyGroupsModels.FamilyResult();
    private boolean requiredToRefreshList;

    public boolean isRequiredToRefreshList() {
        return this.requiredToRefreshList;
    }

    public void setRequiredToRefreshList(boolean z) {
        this.requiredToRefreshList = z;
    }

    public static void copyToCurrentFamily(FamilyGroupsModels.FamilyResult familyResult) {
        getCurrentFamily().createdBy = familyResult.createdBy;
        getCurrentFamily().familyId = familyResult.familyId;
        getCurrentFamily().familyName = familyResult.familyName;
        getCurrentFamily().role = familyResult.role;
        getCurrentFamily().creatorProfilePic = familyResult.creatorProfilePic;
    }

    public static FamilyGroupsModels.FamilyResult getCurrentFamily() {
        return CURRENT;
    }

    public static void clearCurrentFamily() {
        CURRENT.familyId = -1;
        CURRENT.familyName = "";
        CURRENT.role.f480id = -1;
        CURRENT.role.name = "";
        CURRENT.role.level = "";
        CURRENT.createdBy = "";
    }

    public void updateList(FamilyGroupsModels.FamilyResult[] familyResultArr) {
        clear();
        addAll(Arrays.asList(familyResultArr));
        this.requiredToRefreshList = false;
    }

    public void updateCurrentFamily(CoreActivity coreActivity) {
        if (getCurrentFamily().familyId == -1) {
            for (int i = 0; i < size(); i++) {
                FamilyGroupsModels.FamilyResult familyResult = (FamilyGroupsModels.FamilyResult) get(i);
                if (UserInfo.getCurrentUserInfo(coreActivity).familyId == familyResult.familyId) {
                    copyToCurrentFamily(familyResult);
                }
            }
        } else {
            boolean z = false;
            for (int i2 = 0; i2 < size(); i2++) {
                FamilyGroupsModels.FamilyResult familyResult2 = (FamilyGroupsModels.FamilyResult) get(i2);
                if (getCurrentFamily().familyId == familyResult2.familyId) {
                    copyToCurrentFamily(familyResult2);
                    z = true;
                }
            }
            if (!z) {
                getCurrentFamily().familyId = -1;
                updateCurrentFamily(coreActivity);
            }
        }
        this.requiredToRefreshList = false;
    }
}
