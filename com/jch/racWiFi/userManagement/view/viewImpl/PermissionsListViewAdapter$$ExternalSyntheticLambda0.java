package com.jch.racWiFi.userManagement.view.viewImpl;

import android.widget.CompoundButton;
import com.jch.racWiFi.userManagement.model.PermissionModel;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PermissionsListViewAdapter$$ExternalSyntheticLambda0 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ PermissionsListViewAdapter f$0;
    public final /* synthetic */ PermissionModel f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ PermissionsListViewAdapter$$ExternalSyntheticLambda0(PermissionsListViewAdapter permissionsListViewAdapter, PermissionModel permissionModel, int i) {
        this.f$0 = permissionsListViewAdapter;
        this.f$1 = permissionModel;
        this.f$2 = i;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.f$0.mo33270x2f68612(this.f$1, this.f$2, compoundButton, z);
    }
}
