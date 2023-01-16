package com.jch.racWiFi.iduOnBoarding.directOnboarding;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch_hitachi.aircloudglobal.R;

public class DirectOnboardFragment_ViewBinding implements Unbinder {
    private DirectOnboardFragment target;

    public DirectOnboardFragment_ViewBinding(DirectOnboardFragment directOnboardFragment, View view) {
        this.target = directOnboardFragment;
        directOnboardFragment.mRecyclerView = (RecyclerView) C0840Utils.findRequiredViewAsType(view, R.id.direct_onboarding_recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    }

    public void unbind() {
        DirectOnboardFragment directOnboardFragment = this.target;
        if (directOnboardFragment != null) {
            this.target = null;
            directOnboardFragment.mRecyclerView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
