package com.jch.racWiFi.customViews.customDialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import com.jch_hitachi.aircloudglobal.R;

public class CustomDialog extends AlertDialog {
    public CustomDialog(Context context) {
        super(context);
    }

    protected CustomDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    protected CustomDialog(Context context, int i) {
        super(context, i);
    }

    public void setParent(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                view.setBackgroundResource(R.drawable.white_blur);
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                view.setBackgroundResource(R.drawable.transparent);
            }
        });
    }

    private void inflate() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.dimAmount = 0.2f;
        layoutParams.width = -1;
        layoutParams.height = -2;
        getWindow().addFlags(2);
        getWindow().setAttributes(layoutParams);
    }

    public void show() {
        super.show();
        inflate();
    }
}
