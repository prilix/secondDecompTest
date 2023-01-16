package com.jch.racWiFi.userManagement.model.dataProvider;

import com.accord.common.utils.Logger;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.userManagement.model.PermissionModel;
import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import com.jch.racWiFi.userManagement.model.dto.FeatureDto;
import com.jch.racWiFi.userManagement.model.dto.InitialAppConfigDto;
import com.jch.racWiFi.userManagement.model.dto.RoleDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import okhttp3.CertificatePinner;

public class PermissionFactory {
    private static final PermissionFactory ourInstance = new PermissionFactory();
    private final String TAG = getClass().getSimpleName();

    public static PermissionFactory getInstance() {
        return ourInstance;
    }

    public List<PermissionModel> cookUserPermission(AllPermissionDataDto allPermissionDataDto, Integer num) {
        int i;
        Boolean bool;
        Boolean bool2;
        InitialAppConfigDto value = ConfigurationDataProvider.getInstance().getInitialAppConfigDtoLiveData().getValue();
        ArrayList arrayList = new ArrayList();
        if (!(value == null || allPermissionDataDto == null)) {
            Collection<FeatureDto> values = value.getFeatures().values();
            Collection<RoleDto> values2 = value.getRoles().values();
            allPermissionDataDto.getTotalRac();
            HashMap<String, Boolean> map = allPermissionDataDto.getMap();
            HashMap<String, Boolean> editableSettingsMap = allPermissionDataDto.getEditableSettingsMap();
            for (FeatureDto next : values) {
                PermissionModel permissionModel = new PermissionModel();
                Iterator<RoleDto> it = values2.iterator();
                while (true) {
                    i = 0;
                    if (!it.hasNext()) {
                        break;
                    }
                    RoleDto next2 = it.next();
                    if (next2.getLevel() != 0) {
                        String str = num + "." + next2.getId() + "." + next.getId();
                        Boolean.valueOf(false);
                        if (map.containsKey(str)) {
                            bool = map.get(str);
                        } else {
                            str = CertificatePinner.WILDCARD + next2.getId() + "." + next.getId();
                            bool = map.get(str);
                        }
                        permissionModel.levelWisePermission[next2.getLevel() - 1] = bool;
                        boolean z = false;
                        Boolean.valueOf(false);
                        if (editableSettingsMap.containsKey(str)) {
                            bool2 = editableSettingsMap.get(str);
                        } else {
                            bool2 = editableSettingsMap.get(CertificatePinner.WILDCARD + next2.getId() + "." + next.getId());
                        }
                        if (next2.getId().intValue() == 2) {
                            if (bool2 != null) {
                                z = bool2.booleanValue();
                            }
                            permissionModel.clickableMemberDisable = z;
                        } else if (next2.getId().intValue() == 3) {
                            if (bool2 != null) {
                                z = bool2.booleanValue();
                            }
                            permissionModel.clickableChildDisable = z;
                        }
                    }
                }
                Integer num2 = num;
                permissionModel.permissionName = next.getName();
                permissionModel.featureID = next.getId();
                if (num.intValue() == -1) {
                    Boolean[] boolArr = permissionModel.levelWisePermission;
                    int length = boolArr.length;
                    while (true) {
                        if (i >= length) {
                            break;
                        } else if (boolArr[i] != null) {
                            arrayList.add(permissionModel);
                            break;
                        } else {
                            i++;
                        }
                    }
                } else if (UserPermissions.IduFeatures.iduPermissionNames.contains(permissionModel.permissionName)) {
                    Boolean[] boolArr2 = permissionModel.levelWisePermission;
                    int length2 = boolArr2.length;
                    while (true) {
                        if (i >= length2) {
                            break;
                        } else if (boolArr2[i] != null) {
                            arrayList.add(permissionModel);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                Logger.m45d(this.TAG, "permissionKey permissionModels = " + permissionModel.toString());
            }
            if (arrayList.size() > 0) {
                Collections.sort(arrayList);
            }
            Logger.m45d(this.TAG, "permissionKey list = " + Arrays.toString(arrayList.toArray()));
        }
        return arrayList;
    }
}
