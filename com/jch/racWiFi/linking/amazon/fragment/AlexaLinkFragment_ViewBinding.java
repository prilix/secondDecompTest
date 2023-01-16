package com.jch.racWiFi.linking.amazon.fragment;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch_hitachi.aircloudglobal.R;

public class AlexaLinkFragment_ViewBinding implements Unbinder {
    private AlexaLinkFragment target;

    public AlexaLinkFragment_ViewBinding(AlexaLinkFragment alexaLinkFragment, View view) {
        this.target = alexaLinkFragment;
        alexaLinkFragment.mAllowButton = (Button) C0840Utils.findRequiredViewAsType(view, R.id.btnLinkWithAlexaAllow, "field 'mAllowButton'", Button.class);
        alexaLinkFragment.mNotNowButton = (Button) C0840Utils.findRequiredViewAsType(view, R.id.btnLinkWithAlexaNotNow, "field 'mNotNowButton'", Button.class);
    }

    public void unbind() {
        AlexaLinkFragment alexaLinkFragment = this.target;
        if (alexaLinkFragment != null) {
            this.target = null;
            alexaLinkFragment.mAllowButton = null;
            alexaLinkFragment.mNotNowButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
