package com.jch.racWiFi.userManagement.view.viewImpl;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch_hitachi.aircloudglobal.R;

public class ChangePermissionConfirmationDialog extends Dialog {
    private View mChildSub;
    private View mChildTitle;
    private View mMemeberSub;
    private View mMemeberTitle;
    private Button mNegativeButton;
    private Button mPositiveButton;
    private View mView;

    public ChangePermissionConfirmationDialog(Context context) {
        super(context);
        init(context);
        setContentView(this.mView);
    }

    public ChangePermissionConfirmationDialog(Context context, int i) {
        super(context, i);
        init(context);
        setContentView(this.mView);
    }

    protected ChangePermissionConfirmationDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        init(context);
        setContentView(this.mView);
    }

    private void init(Context context) {
        requestWindowFeature(1);
        this.mView = LayoutInflater.from(context).inflate(R.layout.dialog_permission_updated, (ViewGroup) null, false);
        initViews();
        setCancelable(false);
    }

    private void initViews() {
        this.mPositiveButton = (Button) this.mView.findViewById(R.id.button_positive);
        this.mNegativeButton = (Button) this.mView.findViewById(R.id.button_negative);
        this.mPositiveButton.setVisibility(0);
        this.mNegativeButton.setVisibility(0);
        this.mMemeberTitle = this.mView.findViewById(R.id.text_view_device_title);
        this.mChildTitle = this.mView.findViewById(R.id.text_view_user_title);
        this.mMemeberSub = this.mView.findViewById(R.id.text_view_permission_updated_member);
        this.mChildSub = this.mView.findViewById(R.id.text_view_permission_updated_child);
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(ChangePermissionConfirmationDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                ChangePermissionConfirmationDialog.this.getWindow().addFlags(2);
                ChangePermissionConfirmationDialog.this.getWindow().setAttributes(layoutParams);
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

    public void setPositiveButton(String str, final ConfirmationDialog.CustomOnClickListener customOnClickListener) {
        this.mPositiveButton.setText(str);
        this.mPositiveButton.setVisibility(0);
        this.mPositiveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (customOnClickListener.onButtonClickListener(ChangePermissionConfirmationDialog.this, view)) {
                    ChangePermissionConfirmationDialog.this.dismiss();
                }
            }
        });
    }

    public void setNegativeButton(String str, final ConfirmationDialog.CustomOnClickListener customOnClickListener) {
        this.mNegativeButton.setText(str);
        this.mNegativeButton.setVisibility(0);
        this.mNegativeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (customOnClickListener.onButtonClickListener(ChangePermissionConfirmationDialog.this, view)) {
                    ChangePermissionConfirmationDialog.this.dismiss();
                }
            }
        });
    }

    public void hideMemberPermissionUpdated() {
        this.mMemeberTitle.setVisibility(8);
        this.mMemeberSub.setVisibility(8);
        showChildPermissionUpdated();
    }

    public void hideChildPermissionUpdated() {
        this.mChildTitle.setVisibility(8);
        this.mChildSub.setVisibility(8);
        showMemberPermissionUpdated();
    }

    private void showMemberPermissionUpdated() {
        this.mMemeberTitle.setVisibility(0);
        this.mMemeberSub.setVisibility(0);
    }

    private void showChildPermissionUpdated() {
        this.mChildTitle.setVisibility(0);
        this.mChildSub.setVisibility(0);
    }
}
