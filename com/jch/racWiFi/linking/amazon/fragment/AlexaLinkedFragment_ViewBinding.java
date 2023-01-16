package com.jch.racWiFi.linking.amazon.fragment;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch_hitachi.aircloudglobal.R;

public class AlexaLinkedFragment_ViewBinding implements Unbinder {
    private AlexaLinkedFragment target;

    public AlexaLinkedFragment_ViewBinding(AlexaLinkedFragment alexaLinkedFragment, View view) {
        this.target = alexaLinkedFragment;
        alexaLinkedFragment.mErrorCloseButton = (Button) C0840Utils.findRequiredViewAsType(view, R.id.btnAlexaErrorClose, "field 'mErrorCloseButton'", Button.class);
        alexaLinkedFragment.mAlexaCloseButton = (Button) C0840Utils.findRequiredViewAsType(view, R.id.btnLinkedWithAlexaClose, "field 'mAlexaCloseButton'", Button.class);
        alexaLinkedFragment.mLinkedView = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.alexaLinkedView, "field 'mLinkedView'", ConstraintLayout.class);
        alexaLinkedFragment.mErrorView = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.alexaErrorView, "field 'mErrorView'", ConstraintLayout.class);
    }

    public void unbind() {
        AlexaLinkedFragment alexaLinkedFragment = this.target;
        if (alexaLinkedFragment != null) {
            this.target = null;
            alexaLinkedFragment.mErrorCloseButton = null;
            alexaLinkedFragment.mAlexaCloseButton = null;
            alexaLinkedFragment.mLinkedView = null;
            alexaLinkedFragment.mErrorView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
