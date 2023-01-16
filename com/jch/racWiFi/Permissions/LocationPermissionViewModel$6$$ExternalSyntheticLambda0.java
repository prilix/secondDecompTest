package com.jch.racWiFi.Permissions;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import com.jch.racWiFi.Permissions.LocationPermissionViewModel;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocationPermissionViewModel$6$$ExternalSyntheticLambda0 implements ActivityResultCallback {
    public final /* synthetic */ LocationPermissionViewModel.ResolutionCallBack f$0;

    public /* synthetic */ LocationPermissionViewModel$6$$ExternalSyntheticLambda0(LocationPermissionViewModel.ResolutionCallBack resolutionCallBack) {
        this.f$0 = resolutionCallBack;
    }

    public final void onActivityResult(Object obj) {
        LocationPermissionViewModel.C16526.lambda$onFailure$0(this.f$0, (ActivityResult) obj);
    }
}
