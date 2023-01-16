package com.jch.racWiFi.customViews.customDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.jch.racWiFi.customViews.customDialogs.SavingBehaviourDialog;
import com.jch_hitachi.aircloudglobal.R;

public class CopyScheduleConfirmationDialog extends Dialog implements DialogInterface.OnShowListener {
    private String copyFromValueStr;
    private String copyToValueStr;
    private String dialogSubTitle = "------";
    private String dialogTitle = "------";
    private String mButtonNegativeStr = "";
    private String mButtonPositiveStr = "";
    private TextView mCopyScheduleFromValue;
    private TextView mCopyScheduleToValue;
    private TextView mDialogSubTitle;
    private TextView mDialogTitle;
    private Button mNegativeButton;
    private Button mPositiveButton;
    private View mView;
    /* access modifiers changed from: private */
    public DialogInterface.OnDismissListener onDismissListener;

    public void onShow(DialogInterface dialogInterface) {
    }

    public CopyScheduleConfirmationDialog(Context context) {
        super(context);
        init(context);
        setContentView(this.mView);
        getWindow().getDecorView().setBackgroundResource(17170445);
    }

    public void setOnDismissListenerCustom(DialogInterface.OnDismissListener onDismissListener2) {
        this.onDismissListener = onDismissListener2;
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(CopyScheduleConfirmationDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                CopyScheduleConfirmationDialog.this.getWindow().addFlags(2);
                CopyScheduleConfirmationDialog.this.getWindow().setAttributes(layoutParams);
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.white_blur);
                }
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.transparent);
                }
                if (CopyScheduleConfirmationDialog.this.onDismissListener != null) {
                    CopyScheduleConfirmationDialog.this.onDismissListener.onDismiss(dialogInterface);
                }
            }
        });
    }

    private void init(Context context) {
        requestWindowFeature(1);
        this.mView = LayoutInflater.from(context).inflate(R.layout.copy_confirmation_schedule_dialog, (ViewGroup) null, false);
        initViews();
        setCancelable(false);
    }

    private void initViews() {
        this.mPositiveButton = (Button) this.mView.findViewById(R.id.button_positive);
        this.mNegativeButton = (Button) this.mView.findViewById(R.id.button_negative);
        this.mPositiveButton.setVisibility(0);
        this.mNegativeButton.setVisibility(0);
        this.mDialogTitle = (TextView) this.mView.findViewById(R.id.text_view__title_dialog);
        this.mDialogSubTitle = (TextView) this.mView.findViewById(R.id.text_view_sub_title_dialog);
        this.mCopyScheduleFromValue = (TextView) this.mView.findViewById(R.id.text_view_copy_schedule_from_value);
        this.mCopyScheduleToValue = (TextView) this.mView.findViewById(R.id.text_view_copy_schedule_to_value);
    }

    public void show() {
        updateView();
        super.show();
    }

    public void updateView() {
        this.mDialogTitle.setText(this.dialogTitle);
        this.mDialogSubTitle.setText(this.dialogSubTitle);
        this.mCopyScheduleFromValue.setText(this.copyFromValueStr);
        this.mCopyScheduleToValue.setText(this.copyToValueStr);
    }

    public void setPositiveButton(String str, final SavingBehaviourDialog.CustomOnClickListener customOnClickListener) {
        this.mButtonPositiveStr = str;
        this.mPositiveButton.setText(str);
        this.mPositiveButton.setVisibility(0);
        this.mPositiveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (customOnClickListener.onButtonClickListener(CopyScheduleConfirmationDialog.this, view)) {
                    CopyScheduleConfirmationDialog.this.dismiss();
                }
            }
        });
    }

    public void setNegativeButton(String str, final SavingBehaviourDialog.CustomOnClickListener customOnClickListener) {
        this.mButtonNegativeStr = str;
        this.mNegativeButton.setText(str);
        this.mNegativeButton.setVisibility(0);
        this.mNegativeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (customOnClickListener.onButtonClickListener(CopyScheduleConfirmationDialog.this, view)) {
                    CopyScheduleConfirmationDialog.this.dismiss();
                }
            }
        });
    }

    public void setDialogTitle(String str) {
        this.dialogTitle = str;
    }

    public void setDialogSubTitle(String str) {
        this.dialogSubTitle = str;
    }

    public void setCopyFromValueStr(String str) {
        this.copyFromValueStr = str;
    }

    public void setCopyToValueStr(String str) {
        this.copyToValueStr = str;
    }
}
