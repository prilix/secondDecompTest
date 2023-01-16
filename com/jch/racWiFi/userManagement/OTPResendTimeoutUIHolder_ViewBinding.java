package com.jch.racWiFi.userManagement;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.CircleProgressBar;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class OTPResendTimeoutUIHolder_ViewBinding implements Unbinder {
    private OTPResendTimeoutUIHolder target;

    public OTPResendTimeoutUIHolder_ViewBinding(OTPResendTimeoutUIHolder oTPResendTimeoutUIHolder, View view) {
        this.target = oTPResendTimeoutUIHolder;
        oTPResendTimeoutUIHolder.mResendOtp = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_resend_code, "field 'mResendOtp'", TextView.class);
        oTPResendTimeoutUIHolder.mTimerProgressBar = (CircleProgressBar) C0840Utils.findRequiredViewAsType(view, R.id.progressBar, "field 'mTimerProgressBar'", CircleProgressBar.class);
        oTPResendTimeoutUIHolder.mTimerUpdateTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_time, "field 'mTimerUpdateTextView'", TextView.class);
        oTPResendTimeoutUIHolder.mTimerUpdateShort = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_sec, "field 'mTimerUpdateShort'", TextView.class);
    }

    public void unbind() {
        OTPResendTimeoutUIHolder oTPResendTimeoutUIHolder = this.target;
        if (oTPResendTimeoutUIHolder != null) {
            this.target = null;
            oTPResendTimeoutUIHolder.mResendOtp = null;
            oTPResendTimeoutUIHolder.mTimerProgressBar = null;
            oTPResendTimeoutUIHolder.mTimerUpdateTextView = null;
            oTPResendTimeoutUIHolder.mTimerUpdateShort = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
