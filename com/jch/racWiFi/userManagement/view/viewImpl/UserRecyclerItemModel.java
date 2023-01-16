package com.jch.racWiFi.userManagement.view.viewImpl;

import android.view.View;
import com.jch.racWiFi.userManagement.model.FamilyGroupsModels;

public class UserRecyclerItemModel {
    public static String PARCEL_KEY_OF_USER_ID_TO_DETACH = "PARCEL_KEY_OF_USER_ID_TO_DETACH";
    public static String PARCEL_KEY_OF_USER_LIST = "PARCEL_KEY_OF_USER_LIST";
    private FamilyGroupsModels.FamilyResult familyResult;
    private String name;
    private View.OnClickListener onClickListener;

    public FamilyGroupsModels.FamilyResult getFamily() {
        return this.familyResult;
    }

    public void setFamily(FamilyGroupsModels.FamilyResult familyResult2) {
        this.familyResult = familyResult2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public View.OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener2) {
        this.onClickListener = onClickListener2;
    }
}
