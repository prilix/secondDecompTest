package com.jch.racWiFi.userOnboarding.view.uiComponents.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch_hitachi.aircloudglobal.R;

public class ConnectedWifiDialog extends Dialog {
    @BindView(2131362101)
    public Button btnChangeRouter;
    @BindView(2131362102)
    public Button btnOk;
    @BindView(2131362442)
    public EditText etPasswordField;
    @BindView(2131364814)
    public TextView tvSSID;

    public ConnectedWifiDialog(Context context) {
        super(context);
        initDialog(context);
    }

    public ConnectedWifiDialog(Context context, int i) {
        super(context, i);
        initDialog(context);
    }

    protected ConnectedWifiDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        initDialog(context);
    }

    private void initDialog(Context context) {
        setContentView(R.layout.dialog_confirm_wifi_network_new_big);
        ButterKnife.bind((Dialog) this);
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(ConnectedWifiDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -1;
                ConnectedWifiDialog.this.getWindow().addFlags(2);
                ConnectedWifiDialog.this.getWindow().setAttributes(layoutParams);
                view.setBackgroundResource(R.drawable.white_blur);
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                view.setBackgroundResource(R.drawable.transparent);
            }
        });
    }
}
