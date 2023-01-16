package com.jch.racWiFi.userManagement.view;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class PrivacyPolicyFragment_ViewBinding implements Unbinder {
    private PrivacyPolicyFragment target;
    private View view7f0a014f;
    private View view7f0a0162;
    private View view7f0a016b;

    public PrivacyPolicyFragment_ViewBinding(final PrivacyPolicyFragment privacyPolicyFragment, View view) {
        this.target = privacyPolicyFragment;
        privacyPolicyFragment.mPrivacyPolicy = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_privacy_policy, "field 'mPrivacyPolicy'", TextView.class);
        privacyPolicyFragment.mPrivacyPolicyTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_privacy_policy_title, "field 'mPrivacyPolicyTitle'", TextView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_i_agree_privacy_policy, "field 'iAgree' and method 'onClickAgree'");
        privacyPolicyFragment.iAgree = (Button) C0840Utils.castView(findRequiredView, R.id.button_i_agree_privacy_policy, "field 'iAgree'", Button.class);
        this.view7f0a016b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                privacyPolicyFragment.onClickAgree((Button) C0840Utils.castParam(view, "doClick", 0, "onClickAgree", 0, Button.class));
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.button_cancel, "field 'cancel' and method 'onClickCancel'");
        privacyPolicyFragment.cancel = (Button) C0840Utils.castView(findRequiredView2, R.id.button_cancel, "field 'cancel'", Button.class);
        this.view7f0a014f = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                privacyPolicyFragment.onClickCancel();
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.button_dismiss_fragment, "field 'dismiss' and method 'onClickDismissFragment'");
        privacyPolicyFragment.dismiss = (ImageButton) C0840Utils.castView(findRequiredView3, R.id.button_dismiss_fragment, "field 'dismiss'", ImageButton.class);
        this.view7f0a0162 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                privacyPolicyFragment.onClickDismissFragment();
            }
        });
    }

    public void unbind() {
        PrivacyPolicyFragment privacyPolicyFragment = this.target;
        if (privacyPolicyFragment != null) {
            this.target = null;
            privacyPolicyFragment.mPrivacyPolicy = null;
            privacyPolicyFragment.mPrivacyPolicyTitle = null;
            privacyPolicyFragment.iAgree = null;
            privacyPolicyFragment.cancel = null;
            privacyPolicyFragment.dismiss = null;
            this.view7f0a016b.setOnClickListener((View.OnClickListener) null);
            this.view7f0a016b = null;
            this.view7f0a014f.setOnClickListener((View.OnClickListener) null);
            this.view7f0a014f = null;
            this.view7f0a0162.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0162 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
