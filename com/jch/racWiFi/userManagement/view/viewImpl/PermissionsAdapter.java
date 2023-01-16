package com.jch.racWiFi.userManagement.view.viewImpl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.Utils.BackgroundExecutor;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.iduManagement.network.IduOperationNetworkHelper;
import com.jch.racWiFi.userManagement.model.PermissionModel;
import com.jch.racWiFi.userManagement.presenter.PermissionPresenter;
import com.jch.racWiFi.userManagement.view.viewImpl.UserPermissionsManageUsersFragment;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PermissionsAdapter extends RecyclerView.Adapter<PermissionsViewHolder> {
    /* access modifiers changed from: private */
    public int accessLevel;
    private Context context;
    private boolean evaluationMode = false;
    private List<PermissionModel> permissionModels = new ArrayList();
    /* access modifiers changed from: private */
    public PermissionPresenter permissionPresenter;
    /* access modifiers changed from: private */
    public UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel;

    public class PermissionsViewHolder_ViewBinding implements Unbinder {
        private PermissionsViewHolder target;

        public PermissionsViewHolder_ViewBinding(PermissionsViewHolder permissionsViewHolder, View view) {
            this.target = permissionsViewHolder;
            permissionsViewHolder.mPermissionName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.tv_permission_type, "field 'mPermissionName'", TextView.class);
            permissionsViewHolder.mPermissionMember = (TriStateCheckbox) C0840Utils.findRequiredViewAsType(view, R.id.cb_member_permission, "field 'mPermissionMember'", TriStateCheckbox.class);
            permissionsViewHolder.mPermissionChild = (TriStateCheckbox) C0840Utils.findRequiredViewAsType(view, R.id.cb_child_permission, "field 'mPermissionChild'", TriStateCheckbox.class);
        }

        public void unbind() {
            PermissionsViewHolder permissionsViewHolder = this.target;
            if (permissionsViewHolder != null) {
                this.target = null;
                permissionsViewHolder.mPermissionName = null;
                permissionsViewHolder.mPermissionMember = null;
                permissionsViewHolder.mPermissionChild = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public PermissionsAdapter(Context context2, int i, PermissionPresenter permissionPresenter2, UserPermissionsManageUsersFragment.PermissionViewModel permissionViewModel2) {
        this.context = context2;
        this.accessLevel = i;
        this.permissionPresenter = permissionPresenter2;
        this.permissionViewModel = permissionViewModel2;
    }

    public PermissionsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PermissionsViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_user_permissions_item, viewGroup, false), i);
    }

    public void onBindViewHolder(PermissionsViewHolder permissionsViewHolder, int i) {
        permissionsViewHolder.bind(this.permissionModels.get(i));
        if (i == getItemCount() - 1) {
            startEvaluation();
        }
    }

    public int getItemCount() {
        return this.permissionModels.size();
    }

    public void checkAllItemsWithRoleLevel(int i, boolean z) {
        BackgroundExecutor.post(new PermissionsAdapter$$ExternalSyntheticLambda2(this, i, z));
    }

    /* renamed from: lambda$checkAllItemsWithRoleLevel$1$com-jch-racWiFi-userManagement-view-viewImpl-PermissionsAdapter */
    public /* synthetic */ void mo33255x3352e843(int i, boolean z) {
        for (PermissionModel next : this.permissionModels) {
            if ((i != 2 || next.clickableMemberDisable) && (i != 3 || next.clickableChildDisable)) {
                next.levelWisePermission[i - 1] = Boolean.valueOf(z);
            }
        }
        BackgroundExecutor.postOnMainThread(new PermissionsAdapter$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$checkAllItemsWithRoleLevel$0$com-jch-racWiFi-userManagement-view-viewImpl-PermissionsAdapter */
    public /* synthetic */ void mo33254xe5937042() {
        IduOperationNetworkHelper.getInstance().getMainThreadHandler().post(new PermissionsAdapter$$ExternalSyntheticLambda0(this));
    }

    public void updateData(List<PermissionModel> list) {
        this.permissionModels = list;
        Iterator<PermissionModel> it = list.iterator();
        while (it.hasNext()) {
            if (this.permissionPresenter.getPermissionName(it.next().permissionName) == null) {
                it.remove();
            }
        }
        notifyDataSetChanged();
    }

    public List<PermissionModel> getPermissionModels() {
        return this.permissionModels;
    }

    public class PermissionsViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131362195)
        TriStateCheckbox mPermissionChild;
        @BindView(2131362197)
        TriStateCheckbox mPermissionMember;
        @BindView(2131364817)
        TextView mPermissionName;

        public PermissionsViewHolder(View view, int i) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }

        public void bind(PermissionModel permissionModel) {
            String permissionName = PermissionsAdapter.this.permissionPresenter.getPermissionName(permissionModel.permissionName);
            if (permissionName != null) {
                this.mPermissionName.setText(permissionName);
                applyAccessLevelOnCheckBox(this.mPermissionMember, 1, 2, permissionModel);
                applyAccessLevelOnCheckBox(this.mPermissionChild, 2, 3, permissionModel);
                this.mPermissionMember.setEnabled(permissionModel.clickableMemberDisable);
                this.mPermissionChild.setEnabled(permissionModel.clickableChildDisable);
            }
        }

        public void applyAccessLevelOnCheckBox(TriStateCheckbox triStateCheckbox, int i, int i2, PermissionModel permissionModel) {
            boolean z = permissionModel.levelWisePermission[i2 - 1];
            triStateCheckbox.setOnCheckedChangeListener(new C2624x45e586b(this, permissionModel, i2));
            if (PermissionsAdapter.this.accessLevel <= i) {
                if (z == null && PermissionsAdapter.this.permissionViewModel.iduId != -1) {
                    z = true;
                }
                triStateCheckbox.setVisibility(0);
                triStateCheckbox.setChecked(z);
                return;
            }
            triStateCheckbox.setVisibility(4);
        }

        /* renamed from: lambda$applyAccessLevelOnCheckBox$0$com-jch-racWiFi-userManagement-view-viewImpl-PermissionsAdapter$PermissionsViewHolder */
        public /* synthetic */ void mo33261x5f0b92c6(PermissionModel permissionModel, int i, CompoundButton compoundButton, boolean z) {
            permissionModel.levelWisePermission[i - 1] = Boolean.valueOf(z);
            permissionModel.isChanged = true;
            PermissionsAdapter.this.startEvaluation();
        }
    }

    public void startEvaluation() {
        this.permissionPresenter.evaluateAllCheckedForRoles(this.permissionModels);
    }
}
