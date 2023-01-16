package com.jch.racWiFi.iduOnBoarding.common.view;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch_hitachi.aircloudglobal.R;

public class ConfiguringDeviceCheckMdns_ViewBinding implements Unbinder {
    private ConfiguringDeviceCheckMdns target;

    public ConfiguringDeviceCheckMdns_ViewBinding(ConfiguringDeviceCheckMdns configuringDeviceCheckMdns, View view) {
        this.target = configuringDeviceCheckMdns;
        configuringDeviceCheckMdns.mParent = C0840Utils.findRequiredView(view, R.id.parent, "field 'mParent'");
    }

    public void unbind() {
        ConfiguringDeviceCheckMdns configuringDeviceCheckMdns = this.target;
        if (configuringDeviceCheckMdns != null) {
            this.target = null;
            configuringDeviceCheckMdns.mParent = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
