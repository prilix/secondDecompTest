package com.jch.racWiFi.customViews.customDialogs;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class PrivacyPolicyCustomDialog_ViewBinding implements Unbinder {
    private PrivacyPolicyCustomDialog target;

    public PrivacyPolicyCustomDialog_ViewBinding(PrivacyPolicyCustomDialog privacyPolicyCustomDialog) {
        this(privacyPolicyCustomDialog, privacyPolicyCustomDialog.getWindow().getDecorView());
    }

    public PrivacyPolicyCustomDialog_ViewBinding(PrivacyPolicyCustomDialog privacyPolicyCustomDialog, View view) {
        this.target = privacyPolicyCustomDialog;
        privacyPolicyCustomDialog.mPrivacyPolicy = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_privacy_policy, "field 'mPrivacyPolicy'", TextView.class);
        privacyPolicyCustomDialog.mPrivacyPolicyTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_privacy_policy_title, "field 'mPrivacyPolicyTitle'", TextView.class);
        privacyPolicyCustomDialog.iAgree = (Button) C0840Utils.findRequiredViewAsType(view, R.id.button_i_agree_privacy_policy, "field 'iAgree'", Button.class);
        privacyPolicyCustomDialog.cancel = (Button) C0840Utils.findRequiredViewAsType(view, R.id.button_cancel, "field 'cancel'", Button.class);
        privacyPolicyCustomDialog.dismiss = (ImageButton) C0840Utils.findRequiredViewAsType(view, R.id.button_dismiss_fragment, "field 'dismiss'", ImageButton.class);
    }

    public void unbind() {
        PrivacyPolicyCustomDialog privacyPolicyCustomDialog = this.target;
        if (privacyPolicyCustomDialog != null) {
            this.target = null;
            privacyPolicyCustomDialog.mPrivacyPolicy = null;
            privacyPolicyCustomDialog.mPrivacyPolicyTitle = null;
            privacyPolicyCustomDialog.iAgree = null;
            privacyPolicyCustomDialog.cancel = null;
            privacyPolicyCustomDialog.dismiss = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
