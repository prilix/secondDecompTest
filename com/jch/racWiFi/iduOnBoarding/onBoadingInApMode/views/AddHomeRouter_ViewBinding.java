package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views;

import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.jch_hitachi.aircloudglobal.R;

public class AddHomeRouter_ViewBinding implements Unbinder {
    private AddHomeRouter target;
    private View view7f0a011e;
    private View view7f0a02a5;

    public AddHomeRouter_ViewBinding(final AddHomeRouter addHomeRouter, View view) {
        this.target = addHomeRouter;
        addHomeRouter.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        addHomeRouter.mRootView = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.root_layout, "field 'mRootView'", ConstraintLayout.class);
        addHomeRouter.mTextViewSteps = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_1, "field 'mTextViewSteps'", TextView.class);
        addHomeRouter.mLinearProgressIndicator = (LinearProgressIndicator) C0840Utils.findRequiredViewAsType(view, R.id.linearProgressIndicator, "field 'mLinearProgressIndicator'", LinearProgressIndicator.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "method 'goBack'");
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addHomeRouter.goBack();
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.forward_button, "method 'goNext'");
        this.view7f0a02a5 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addHomeRouter.goNext();
            }
        });
    }

    public void unbind() {
        AddHomeRouter addHomeRouter = this.target;
        if (addHomeRouter != null) {
            this.target = null;
            addHomeRouter.mParent = null;
            addHomeRouter.mRootView = null;
            addHomeRouter.mTextViewSteps = null;
            addHomeRouter.mLinearProgressIndicator = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a02a5.setOnClickListener((View.OnClickListener) null);
            this.view7f0a02a5 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
