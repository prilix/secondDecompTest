package com.jch.racWiFi.iduManagement.view.viewImpl;

import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch_hitachi.aircloudglobal.R;

public class IndividualIDUControlFragmentModelWise_ViewBinding implements Unbinder {
    private IndividualIDUControlFragmentModelWise target;

    public IndividualIDUControlFragmentModelWise_ViewBinding(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise, View view) {
        this.target = individualIDUControlFragmentModelWise;
        individualIDUControlFragmentModelWise.mParentTop = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent_top, "field 'mParentTop'", ConstraintLayout.class);
        individualIDUControlFragmentModelWise.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        individualIDUControlFragmentModelWise.mParentOuter = (FrameLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_rac_error, "field 'mParentOuter'", FrameLayout.class);
        individualIDUControlFragmentModelWise.mViewPopUp = C0840Utils.findRequiredView(view, R.id.view_popup, "field 'mViewPopUp'");
        individualIDUControlFragmentModelWise.pullToRefresh = (SwipeRefreshLayout) C0840Utils.findRequiredViewAsType(view, R.id.pullToRefresh, "field 'pullToRefresh'", SwipeRefreshLayout.class);
    }

    public void unbind() {
        IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise = this.target;
        if (individualIDUControlFragmentModelWise != null) {
            this.target = null;
            individualIDUControlFragmentModelWise.mParentTop = null;
            individualIDUControlFragmentModelWise.mParent = null;
            individualIDUControlFragmentModelWise.mParentOuter = null;
            individualIDUControlFragmentModelWise.mViewPopUp = null;
            individualIDUControlFragmentModelWise.pullToRefresh = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
