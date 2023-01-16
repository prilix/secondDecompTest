package com.jch.racWiFi.userManagement.view.viewImpl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.jch.racWiFi.Utils.BackgroundExecutor;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.iduManagement.network.IduOperationNetworkHelper;
import com.jch.racWiFi.userManagement.model.PermissionModel;
import com.jch.racWiFi.userManagement.presenter.PermissionPresenter;
import com.jch.racWiFi.userManagement.view.viewImpl.UserPermissionsManageUsersFragment;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;

public class PermissionsListViewAdapter extends ArrayAdapter<PermissionModel> {
    private int accessLevel;
    private Context context;
    private boolean evaluationMode = false;
    public boolean isALLFunctionClicked;
    private List<PermissionModel> permissionModels;
    private List<PermissionModel> permissionModelsPreviousCopy = new ArrayList();
    private List<PermissionModel> permissionModelsToServer = new ArrayList();
    private PermissionPresenter permissionPresenter;
    private UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel;

    public PermissionsListViewAdapter(Context context2, int i, PermissionPresenter permissionPresenter2, UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel2, List<PermissionModel> list) {
        super(context2, R.layout.recycler_view_user_permissions_item, list);
        this.context = context2;
        this.accessLevel = i;
        this.permissionPresenter = permissionPresenter2;
        this.permissionViewModel = permissionViewModel2;
        this.permissionModels = list;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.recycler_view_user_permissions_item, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_permission_type);
        TriStateCheckbox triStateCheckbox = (TriStateCheckbox) inflate.findViewById(R.id.cb_member_permission);
        TriStateCheckbox triStateCheckbox2 = (TriStateCheckbox) inflate.findViewById(R.id.cb_child_permission);
        PermissionModel permissionModel = this.permissionModels.get(i);
        String permissionName = this.permissionPresenter.getPermissionName(permissionModel.permissionName);
        if (permissionName != null) {
            textView.setText(permissionName);
            applyAccessLevelOnCheckBox(triStateCheckbox, 1, 2, permissionModel);
            applyAccessLevelOnCheckBox(triStateCheckbox2, 2, 3, permissionModel);
            boolean z = permissionModel.clickableMemberDisable;
            triStateCheckbox.setEnabled(z);
            if (z) {
                triStateCheckbox.setAlpha(1.0f);
            } else {
                triStateCheckbox.setAlpha(0.3f);
            }
            boolean z2 = permissionModel.clickableChildDisable;
            triStateCheckbox2.setEnabled(z2);
            if (z2) {
                triStateCheckbox2.setAlpha(1.0f);
            } else {
                triStateCheckbox2.setAlpha(0.3f);
            }
        }
        return inflate;
    }

    private void applyAccessLevelOnCheckBox(TriStateCheckbox triStateCheckbox, int i, int i2, PermissionModel permissionModel) {
        boolean z = permissionModel.levelWisePermission[i2 - 1];
        if (this.accessLevel <= i) {
            if (z == null && this.permissionViewModel.iduId != -1) {
                z = true;
            }
            triStateCheckbox.setVisibility(0);
            triStateCheckbox.setChecked(z);
        } else {
            triStateCheckbox.setVisibility(4);
        }
        triStateCheckbox.setOnCheckedChangeListener(new PermissionsListViewAdapter$$ExternalSyntheticLambda0(this, permissionModel, i2));
    }

    /* renamed from: lambda$applyAccessLevelOnCheckBox$0$com-jch-racWiFi-userManagement-view-viewImpl-PermissionsListViewAdapter */
    public /* synthetic */ void mo33270x2f68612(PermissionModel permissionModel, int i, CompoundButton compoundButton, boolean z) {
        permissionModel.levelWisePermission[i - 1] = Boolean.valueOf(z);
        permissionModel.isChanged = true;
        startEvaluation();
    }

    public void checkAllItemsWithRoleLevel(int i, boolean z) {
        BackgroundExecutor.post(new PermissionsListViewAdapter$$ExternalSyntheticLambda3(this, i, z));
    }

    /* renamed from: lambda$checkAllItemsWithRoleLevel$2$com-jch-racWiFi-userManagement-view-viewImpl-PermissionsListViewAdapter */
    public /* synthetic */ void mo33272xc3cfad61(int i, boolean z) {
        for (PermissionModel next : this.permissionModels) {
            if ((i != 2 || next.clickableMemberDisable) && (i != 3 || next.clickableChildDisable)) {
                next.levelWisePermission[i - 1] = Boolean.valueOf(z);
                next.isChanged = true;
            }
        }
        BackgroundExecutor.postOnMainThread(new PermissionsListViewAdapter$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$checkAllItemsWithRoleLevel$1$com-jch-racWiFi-userManagement-view-viewImpl-PermissionsListViewAdapter */
    public /* synthetic */ void mo33271xfcc3c660() {
        IduOperationNetworkHelper.getInstance().getMainThreadHandler().post(new PermissionsListViewAdapter$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    public void updateData(List<PermissionModel> list) {
        this.permissionModels.clear();
        for (PermissionModel permissionModel : list) {
            this.permissionModelsPreviousCopy.add(new PermissionModel(permissionModel));
        }
        this.permissionModels.addAll(list);
        notifyDataSetChanged();
    }

    public List<PermissionModel> getPermissionModels() {
        this.permissionModelsToServer.clear();
        for (PermissionModel next : this.permissionModels) {
            if (next.isChanged) {
                this.permissionModelsToServer.add(new PermissionModel(next));
            }
        }
        if (this.permissionModelsToServer.size() == 0) {
            return this.permissionModels;
        }
        return this.permissionModelsToServer;
    }

    public void startEvaluation() {
        this.permissionPresenter.evaluateAllCheckedForRoles(this.permissionModels);
    }

    public boolean[] getMemberChildPermissionChanged() {
        boolean[] zArr = new boolean[2];
        List<PermissionModel> list = this.permissionModelsPreviousCopy;
        for (PermissionModel next : this.permissionModelsToServer) {
            for (PermissionModel next2 : list) {
                if (next.featureID.equals(next2.featureID)) {
                    Boolean[] boolArr = next2.levelWisePermission;
                    Boolean[] boolArr2 = next.levelWisePermission;
                    if (boolArr2[1] != boolArr[1]) {
                        zArr[0] = true;
                    }
                    if (boolArr2[2] != boolArr[2]) {
                        zArr[1] = true;
                    }
                }
            }
        }
        return zArr;
    }
}
