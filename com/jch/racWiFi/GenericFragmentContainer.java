package com.jch.racWiFi;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch_hitachi.aircloudglobal.R;

public class GenericFragmentContainer {
    private Context context;

    public GenericFragmentContainer(Context context2) {
        this.context = context2;
    }

    public void showErrorPopUpContent(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.context);
        singleChoiceDialog.setTitleString(this.context.getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(this.context.getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                return true;
            }
        });
        singleChoiceDialog.show();
    }
}
