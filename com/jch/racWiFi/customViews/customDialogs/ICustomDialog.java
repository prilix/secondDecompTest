package com.jch.racWiFi.customViews.customDialogs;

import android.content.DialogInterface;
import android.view.View;
import com.jch.racWiFi.customViews.customDialogs.SavingBehaviourDialog;

public interface ICustomDialog {
    void dismiss();

    boolean isShowing();

    void setCancelable(boolean z);

    void setMessageString(String str);

    void setNegativeButton(String str, SavingBehaviourDialog.CustomOnClickListener customOnClickListener);

    void setOnDismissListenerCustom(DialogInterface.OnDismissListener onDismissListener);

    void setOperationString(String str);

    void setOperationTitleString(String str);

    void setParentView(View view);

    void setPositiveButton(String str, SavingBehaviourDialog.CustomOnClickListener customOnClickListener);

    void setTitleString(String str);

    void show();
}
