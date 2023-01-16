package com.jch.racWiFi.userManagement.view.viewImpl;

import android.widget.CompoundButton;
import com.jch.racWiFi.userManagement.model.PermissionModel;
import com.jch.racWiFi.userManagement.view.viewImpl.PermissionsAdapter;

/* renamed from: com.jch.racWiFi.userManagement.view.viewImpl.PermissionsAdapter$PermissionsViewHolder$$ExternalSyntheticLambda0 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class C2624x45e586b implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ PermissionsAdapter.PermissionsViewHolder f$0;
    public final /* synthetic */ PermissionModel f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ C2624x45e586b(PermissionsAdapter.PermissionsViewHolder permissionsViewHolder, PermissionModel permissionModel, int i) {
        this.f$0 = permissionsViewHolder;
        this.f$1 = permissionModel;
        this.f$2 = i;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.f$0.mo33261x5f0b92c6(this.f$1, this.f$2, compoundButton, z);
    }
}
