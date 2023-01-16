package com.jch.racWiFi.userOnboarding.view.viewImpl;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class ManageOwnersByRacIdFragment_ViewBinding implements Unbinder {
    private ManageOwnersByRacIdFragment target;
    private View view7f0a011e;

    public ManageOwnersByRacIdFragment_ViewBinding(final ManageOwnersByRacIdFragment manageOwnersByRacIdFragment, View view) {
        this.target = manageOwnersByRacIdFragment;
        manageOwnersByRacIdFragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        manageOwnersByRacIdFragment.mTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_my_account_title, "field 'mTitle'", TextView.class);
        manageOwnersByRacIdFragment.mSubTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_manage_homes_title, "field 'mSubTitle'", TextView.class);
        manageOwnersByRacIdFragment.msubTitleMessage = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_manage_homes_sub_title, "field 'msubTitleMessage'", TextView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onClickBackButton'");
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                manageOwnersByRacIdFragment.onClickBackButton();
            }
        });
    }

    public void unbind() {
        ManageOwnersByRacIdFragment manageOwnersByRacIdFragment = this.target;
        if (manageOwnersByRacIdFragment != null) {
            this.target = null;
            manageOwnersByRacIdFragment.mParent = null;
            manageOwnersByRacIdFragment.mTitle = null;
            manageOwnersByRacIdFragment.mSubTitle = null;
            manageOwnersByRacIdFragment.msubTitleMessage = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
