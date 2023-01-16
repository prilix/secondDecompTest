package com.jch.racWiFi.userManagement.presenter.presenterImpl;

import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.Utils.BackgroundExecutor;
import com.jch.racWiFi.userManagement.model.PermissionModel;
import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import com.jch.racWiFi.userManagement.model.dto.ChangeDto;
import com.jch.racWiFi.userManagement.model.dto.PermissionSaveDto;
import com.jch.racWiFi.userManagement.network.ApiCaller.PermissionApiImpl;
import com.jch.racWiFi.userManagement.presenter.PermissionPresenter;
import com.jch.racWiFi.userManagement.view.IDevicePermissionView;
import com.jch.racWiFi.userManagement.view.viewImpl.UserPermissionsManageUsersFragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class PermissionPresenterImpl implements PermissionPresenter {
    BackgroundExecutor backgroundExecutor;
    IDevicePermissionView iDevicePermissionView;
    private Handler mainLopper;

    public PermissionPresenterImpl(IDevicePermissionView iDevicePermissionView2) {
        this.iDevicePermissionView = iDevicePermissionView2;
        BackgroundExecutor backgroundExecutor2 = new BackgroundExecutor();
        this.backgroundExecutor = backgroundExecutor2;
        backgroundExecutor2.start();
    }

    private Boolean calucateRolePermission(HashSet<Boolean> hashSet) {
        if (hashSet.size() > 1) {
            return null;
        }
        Iterator<Boolean> it = hashSet.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return false;
    }

    public void evaluateAllCheckedForRoles(List<PermissionModel> list) {
        BackgroundExecutor.post(new PermissionPresenterImpl$$ExternalSyntheticLambda2(this, list));
    }

    /* renamed from: lambda$evaluateAllCheckedForRoles$1$com-jch-racWiFi-userManagement-presenter-presenterImpl-PermissionPresenterImpl */
    public /* synthetic */ void mo32830x1c0365a5(List list) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            PermissionModel permissionModel = (PermissionModel) it.next();
            if (permissionModel.clickableMemberDisable) {
                hashSet.add(permissionModel.levelWisePermission[1]);
            }
            if (permissionModel.clickableChildDisable) {
                hashSet2.add(permissionModel.levelWisePermission[2]);
            }
            Logger.m49i("", "Permission " + permissionModel.permissionName + permissionModel.levelWisePermission[1] + " " + permissionModel.levelWisePermission[2]);
        }
        BackgroundExecutor.postOnMainThread(new PermissionPresenterImpl$$ExternalSyntheticLambda3(this, new Boolean[]{false, calucateRolePermission(hashSet), calucateRolePermission(hashSet2)}));
    }

    /* renamed from: lambda$evaluateAllCheckedForRoles$0$com-jch-racWiFi-userManagement-presenter-presenterImpl-PermissionPresenterImpl */
    public /* synthetic */ void mo32829x3e0fffc6(Boolean[] boolArr) {
        this.iDevicePermissionView.onAllCheckedStatusEvaluated(boolArr);
    }

    public List<ChangeDto> getChangeList(List<PermissionModel> list) {
        ArrayList arrayList = new ArrayList();
        for (PermissionModel next : list) {
            boolean z = next.clickableMemberDisable;
            boolean z2 = next.clickableChildDisable;
            Logger.m49i("", "permission name = " + next.permissionName + " feature id = " + next.featureID + " member = " + z + " child = " + z2);
            for (int i = 0; i < next.levelWisePermission.length; i++) {
                if (i == 1 && z) {
                    ChangeDto changeDto = new ChangeDto();
                    changeDto.setFeatureId(Long.valueOf((long) next.featureID.intValue()));
                    changeDto.setEnabled(next.levelWisePermission[i]);
                    changeDto.setRoleId(Long.valueOf((long) (i + 1)));
                    arrayList.add(changeDto);
                }
                if (i == 2 && z2) {
                    ChangeDto changeDto2 = new ChangeDto();
                    changeDto2.setFeatureId(Long.valueOf((long) next.featureID.intValue()));
                    changeDto2.setEnabled(next.levelWisePermission[i]);
                    changeDto2.setRoleId(Long.valueOf((long) (i + 1)));
                    arrayList.add(changeDto2);
                }
            }
        }
        return arrayList;
    }

    private Pair<List<ChangeDto>, List<ChangeDto>> getChangeListDividedByMenuAndIdu(List<PermissionModel> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList<String> arrayList3 = UserPermissions.IduFeatures.iduPermissionNames;
        for (PermissionModel next : list) {
            boolean contains = arrayList3.contains(next.permissionName);
            boolean z = next.clickableMemberDisable;
            boolean z2 = next.clickableChildDisable;
            for (int i = 1; i < next.levelWisePermission.length; i++) {
                ChangeDto changeDto = null;
                if (i == 1 && z) {
                    changeDto = new ChangeDto();
                    changeDto.setFeatureId(Long.valueOf((long) next.featureID.intValue()));
                    changeDto.setEnabled(next.levelWisePermission[i]);
                    changeDto.setRoleId(Long.valueOf((long) (i + 1)));
                }
                if (i == 2 && z2) {
                    changeDto = new ChangeDto();
                    changeDto.setFeatureId(Long.valueOf((long) next.featureID.intValue()));
                    changeDto.setEnabled(next.levelWisePermission[i]);
                    changeDto.setRoleId(Long.valueOf((long) (i + 1)));
                }
                if (changeDto != null) {
                    if (contains) {
                        arrayList.add(changeDto);
                    } else {
                        arrayList2.add(changeDto);
                    }
                }
            }
        }
        return new Pair<>(arrayList2, arrayList);
    }

    private void requestAllDeviceSave(LifecycleOwner lifecycleOwner, List<PermissionModel> list, UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel) {
        performGenericSave(lifecycleOwner, getChangeList(list), permissionViewModel.iduId);
    }

    private void requestIduWiseSave(LifecycleOwner lifecycleOwner, List<PermissionModel> list, UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel) {
        Pair<List<ChangeDto>, List<ChangeDto>> changeListDividedByMenuAndIdu = getChangeListDividedByMenuAndIdu(list);
        performGenericSave(lifecycleOwner, (List) changeListDividedByMenuAndIdu.second, permissionViewModel.iduId);
        performGenericSave(lifecycleOwner, (List) changeListDividedByMenuAndIdu.first, -1);
    }

    private void performGenericSave(LifecycleOwner lifecycleOwner, Collection<ChangeDto> collection, int i) {
        Integer num;
        PermissionSaveDto permissionSaveDto = new PermissionSaveDto();
        permissionSaveDto.setChanges(collection);
        if (i == -1) {
            num = null;
        } else {
            num = Integer.valueOf(i);
        }
        permissionSaveDto.setRacId(num);
        getMainThreadHandler().post(new PermissionPresenterImpl$$ExternalSyntheticLambda0(this, PermissionApiImpl.getInstance().requestPermissionSaving(permissionSaveDto, Integer.valueOf(i)), lifecycleOwner));
    }

    /* renamed from: lambda$performGenericSave$2$com-jch-racWiFi-userManagement-presenter-presenterImpl-PermissionPresenterImpl */
    public /* synthetic */ void mo32831x473df984(LiveData liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, new Observer<Response<ResponseBody>>() {
            public void onChanged(Response<ResponseBody> response) {
                PermissionPresenterImpl.this.iDevicePermissionView.savePermissionResponseDatas(response);
            }
        });
    }

    public void requestSave(LifecycleOwner lifecycleOwner, List<PermissionModel> list, UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel) {
        BackgroundExecutor.post(new PermissionPresenterImpl$$ExternalSyntheticLambda1(this, permissionViewModel, lifecycleOwner, list));
    }

    /* renamed from: lambda$requestSave$3$com-jch-racWiFi-userManagement-presenter-presenterImpl-PermissionPresenterImpl */
    public /* synthetic */ void mo32832x3a0cefc4(UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel, LifecycleOwner lifecycleOwner, List list) {
        if (permissionViewModel.iduId != -1) {
            requestIduWiseSave(lifecycleOwner, list, permissionViewModel);
        } else {
            requestAllDeviceSave(lifecycleOwner, list, permissionViewModel);
        }
    }

    public void performInitTask(LifecycleOwner lifecycleOwner, boolean z, UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel) {
        LiveData<Response<AllPermissionDataDto>> liveData;
        Integer num = null;
        if (permissionViewModel == null) {
            liveData = PermissionApiImpl.getInstance().requestPermissionsData((Integer) null);
        } else {
            PermissionApiImpl instance = PermissionApiImpl.getInstance();
            if (z) {
                num = Integer.valueOf(permissionViewModel.iduId);
            }
            liveData = instance.requestPermissionsData(num);
        }
        liveData.observe(lifecycleOwner, new Observer<Response<AllPermissionDataDto>>() {
            public void onChanged(Response<AllPermissionDataDto> response) {
                PermissionPresenterImpl.this.iDevicePermissionView.fetchPermissionResponseDatas(response);
            }
        });
    }

    public String getPermissionName(String str) {
        return UserPermissions.getInstance().getPermissionName(str);
    }

    public Handler getMainThreadHandler() {
        Handler handler = new Handler(Looper.getMainLooper());
        this.mainLopper = handler;
        return handler;
    }
}
