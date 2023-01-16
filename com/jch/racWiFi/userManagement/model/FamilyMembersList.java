package com.jch.racWiFi.userManagement.model;

import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FamilyMembersList extends ArrayList<UserFamilyMemberModels.UserFamilyMemberInfo> {
    private boolean requiredToRefreshList;

    public boolean isRequiredToRefreshList() {
        return this.requiredToRefreshList;
    }

    public void setRequiredToRefreshList(boolean z) {
        this.requiredToRefreshList = z;
    }

    public boolean add(UserFamilyMemberModels.UserFamilyMemberInfo userFamilyMemberInfo) {
        this.requiredToRefreshList = false;
        return super.add(userFamilyMemberInfo);
    }

    public boolean addAll(Collection<? extends UserFamilyMemberModels.UserFamilyMemberInfo> collection) {
        this.requiredToRefreshList = false;
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends UserFamilyMemberModels.UserFamilyMemberInfo> collection) {
        this.requiredToRefreshList = false;
        return super.addAll(i, collection);
    }

    public void updateList(List<UserFamilyMemberModels.UserFamilyMemberInfo> list) {
        clear();
        addAll(list);
    }
}
