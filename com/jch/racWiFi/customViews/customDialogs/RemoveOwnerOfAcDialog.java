package com.jch.racWiFi.customViews.customDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class RemoveOwnerOfAcDialog extends Dialog {
    TextView mHomeNameTV = ((TextView) findViewById(R.id.text_view_device_name_dialog));
    TextView mHomeTv = ((TextView) findViewById(R.id.text_view_device_title));
    TextView mMyRoleTV = ((TextView) findViewById(R.id.text_view_user_title));
    Button mNoButton = ((Button) findViewById(R.id.button_negative));
    TextView mRoleNameTv = ((TextView) findViewById(R.id.text_view_user_name_dialog));
    TextView mSubTitle = ((TextView) findViewById(R.id.text_view_remove_user_sub_title_dialog));
    TextView mTitleTV = ((TextView) findViewById(R.id.text_view_remove_user_title_dialog));
    public Button yseButton = ((Button) findViewById(R.id.button_positive));

    public RemoveOwnerOfAcDialog(Context context) {
        super(context);
        setContentView(LayoutInflater.from(context).inflate(R.layout.remove_owner_from_ac_dialog, (ViewGroup) null, false));
        this.mTitleTV.setText(context.getResources().getString(R.string.manageAc_alert_removeOwnerAcTitle));
        this.mSubTitle.setText(context.getResources().getString(R.string.manageAc_alert_removeOwnerAcDesc));
        this.mHomeTv.setText(context.getResources().getString(R.string.manageAc_acNameDisplayInfo));
        this.mMyRoleTV.setText(context.getResources().getString(R.string.manageAc_alert_ownerDisplayInfo));
        this.mNoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RemoveOwnerOfAcDialog.this.dismiss();
            }
        });
    }

    public void setText(String str, String str2) {
        this.mRoleNameTv.setText(str);
        this.mHomeNameTV.setText(str2);
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(RemoveOwnerOfAcDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                RemoveOwnerOfAcDialog.this.getWindow().addFlags(2);
                RemoveOwnerOfAcDialog.this.getWindow().setAttributes(layoutParams);
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
            }
        });
    }
}
