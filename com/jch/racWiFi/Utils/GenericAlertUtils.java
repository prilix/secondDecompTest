package com.jch.racWiFi.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch_hitachi.aircloudglobal.R;

public class GenericAlertUtils {
    public static SingleChoiceDialog getNoNetworkAlert(Context context) {
        return generateAlertInstance(context, context.getString(R.string.common_alert), context.getString(R.string.common_alert_unableToConnectToNw));
    }

    public static SingleChoiceDialog getSingleChoiceDialogWithAlertTitle(Context context, String str) {
        return generateAlertInstance(context, context.getString(R.string.common_alert), str);
    }

    private static SingleChoiceDialog generateAlertInstance(Context context, String str, String str2) {
        ExceptionUtil.checkContextBelongsToActivity(context);
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(context);
        singleChoiceDialog.setTitleString(str);
        singleChoiceDialog.setMessageString(str2);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(context.getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                return true;
            }
        });
        return singleChoiceDialog;
    }
}
