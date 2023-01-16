package com.jch.racWiFi.userOnboarding.view.uiComponents.dialog;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch_hitachi.aircloudglobal.R;

public class ConnectedWifiDialog_ViewBinding implements Unbinder {
    private ConnectedWifiDialog target;

    public ConnectedWifiDialog_ViewBinding(ConnectedWifiDialog connectedWifiDialog) {
        this(connectedWifiDialog, connectedWifiDialog.getWindow().getDecorView());
    }

    public ConnectedWifiDialog_ViewBinding(ConnectedWifiDialog connectedWifiDialog, View view) {
        this.target = connectedWifiDialog;
        connectedWifiDialog.btnOk = (Button) C0840Utils.findRequiredViewAsType(view, R.id.bt_confirm, "field 'btnOk'", Button.class);
        connectedWifiDialog.btnChangeRouter = (Button) C0840Utils.findRequiredViewAsType(view, R.id.bt_change_router, "field 'btnChangeRouter'", Button.class);
        connectedWifiDialog.tvSSID = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.tv_home_router_ssid, "field 'tvSSID'", TextView.class);
        connectedWifiDialog.etPasswordField = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.et_password_field, "field 'etPasswordField'", EditText.class);
    }

    public void unbind() {
        ConnectedWifiDialog connectedWifiDialog = this.target;
        if (connectedWifiDialog != null) {
            this.target = null;
            connectedWifiDialog.btnOk = null;
            connectedWifiDialog.btnChangeRouter = null;
            connectedWifiDialog.tvSSID = null;
            connectedWifiDialog.etPasswordField = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
