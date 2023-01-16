package com.jch.racWiFi.settings.view;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class PrivacyPolicyFragment_ViewBinding implements Unbinder {
    private PrivacyPolicyFragment target;
    private View view7f0a03fc;

    public PrivacyPolicyFragment_ViewBinding(final PrivacyPolicyFragment privacyPolicyFragment, View view) {
        this.target = privacyPolicyFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.image_button_menu, "field 'mMenu' and method 'OnClickMenu'");
        privacyPolicyFragment.mMenu = (ImageButton) C0840Utils.castView(findRequiredView, R.id.image_button_menu, "field 'mMenu'", ImageButton.class);
        this.view7f0a03fc = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                privacyPolicyFragment.OnClickMenu((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickMenu", 0, ImageButton.class));
            }
        });
        privacyPolicyFragment.mPrivacyPolicyTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_privacy_policy, "field 'mPrivacyPolicyTextView'", TextView.class);
    }

    public void unbind() {
        PrivacyPolicyFragment privacyPolicyFragment = this.target;
        if (privacyPolicyFragment != null) {
            this.target = null;
            privacyPolicyFragment.mMenu = null;
            privacyPolicyFragment.mPrivacyPolicyTextView = null;
            this.view7f0a03fc.setOnClickListener((View.OnClickListener) null);
            this.view7f0a03fc = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
