package com.jch.racWiFi.userManagement.view;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountManageHomesFragment_ViewBinding implements Unbinder {
    private MyAccountManageHomesFragment target;
    private View view7f0a011e;

    public MyAccountManageHomesFragment_ViewBinding(final MyAccountManageHomesFragment myAccountManageHomesFragment, View view) {
        this.target = myAccountManageHomesFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onClickBackButton'");
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountManageHomesFragment.onClickBackButton();
            }
        });
    }

    public void unbind() {
        if (this.target != null) {
            this.target = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
