package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.LifecycleOwner;
import com.jch.racWiFi.userManagement.model.PermissionModel;
import com.jch.racWiFi.userManagement.model.dto.ChangeDto;
import com.jch.racWiFi.userManagement.view.viewImpl.UserPermissionsManageUsersFragment;
import java.util.List;

public interface PermissionPresenter {
    void evaluateAllCheckedForRoles(List<PermissionModel> list);

    List<ChangeDto> getChangeList(List<PermissionModel> list);

    String getPermissionName(String str);

    void performInitTask(LifecycleOwner lifecycleOwner, boolean z, UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel);

    void requestSave(LifecycleOwner lifecycleOwner, List<PermissionModel> list, UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel);
}
