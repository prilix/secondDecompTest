package com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.jch_hitachi.aircloudglobal.R;

public class EnableWpsOnRacFragment_ViewBinding implements Unbinder {
    private EnableWpsOnRacFragment target;
    private View view7f0a011e;
    private View view7f0a02a5;

    public EnableWpsOnRacFragment_ViewBinding(final EnableWpsOnRacFragment enableWpsOnRacFragment, View view) {
        this.target = enableWpsOnRacFragment;
        enableWpsOnRacFragment.mRootView = C0840Utils.findRequiredView(view, R.id.parent, "field 'mRootView'");
        enableWpsOnRacFragment.mTextViewSteps = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_1, "field 'mTextViewSteps'", TextView.class);
        enableWpsOnRacFragment.mLinearProgressIndicator = (LinearProgressIndicator) C0840Utils.findRequiredViewAsType(view, R.id.linearProgressIndicator, "field 'mLinearProgressIndicator'", LinearProgressIndicator.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "method 'goBack'");
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                enableWpsOnRacFragment.goBack();
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.forward_button, "method 'goNext'");
        this.view7f0a02a5 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                enableWpsOnRacFragment.goNext();
            }
        });
    }

    public void unbind() {
        EnableWpsOnRacFragment enableWpsOnRacFragment = this.target;
        if (enableWpsOnRacFragment != null) {
            this.target = null;
            enableWpsOnRacFragment.mRootView = null;
            enableWpsOnRacFragment.mTextViewSteps = null;
            enableWpsOnRacFragment.mLinearProgressIndicator = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a02a5.setOnClickListener((View.OnClickListener) null);
            this.view7f0a02a5 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
