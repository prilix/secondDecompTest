package com.jch.racWiFi.settings.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.jch.racWiFi.AppVersionModels;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class AppVersionDialog extends Dialog {
    private Context context;
    /* access modifiers changed from: private */
    public Button mCheckForUpdates;
    private ImageButton mClose;
    private TextView mCurrentVersion;
    private TextView mLastUpdatedTime;
    private View mLastUpdatedTimeTitle;
    private TextView mLatestVersion;
    private View mNewUpdateAvailableTitle;
    private TextView maApplicationVersionTitle;
    /* access modifiers changed from: private */
    public View.OnClickListener onClickListener;
    private View view;

    public AppVersionDialog(Context context2) {
        super(context2);
        init(context2);
    }

    public AppVersionDialog(Context context2, int i) {
        super(context2, i);
        init(context2);
    }

    protected AppVersionDialog(Context context2, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context2, z, onCancelListener);
        init(context2);
    }

    private void init(Context context2) {
        this.context = context2;
        setCancelable(false);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.application_version_dialog_new, (ViewGroup) null);
        this.view = inflate;
        setContentView(inflate);
        this.mCurrentVersion = (TextView) this.view.findViewById(R.id.text_view_installed_version);
        this.mLatestVersion = (TextView) this.view.findViewById(R.id.text_view_latest_version);
        this.mClose = (ImageButton) this.view.findViewById(R.id.image_button_clear);
        this.mCheckForUpdates = (Button) this.view.findViewById(R.id.button_check_for_update);
        this.mLastUpdatedTime = (TextView) this.view.findViewById(R.id.text_view_last_updated_date_time);
        this.mLastUpdatedTimeTitle = this.view.findViewById(R.id.text_view_last_updated_on_dialog);
        this.mNewUpdateAvailableTitle = this.view.findViewById(R.id.text_view_application_version_title_2);
        this.maApplicationVersionTitle = (TextView) this.view.findViewById(R.id.text_view_application_version_title);
        this.mCurrentVersion.setText("3.0.9");
        this.mClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppVersionDialog.this.mCheckForUpdates.setOnClickListener(AppVersionDialog.this.onClickListener);
                AppVersionDialog.this.mCheckForUpdates.setText(R.string.settings_lbl_checkForUpdate);
                AppVersionDialog.this.dismiss();
            }
        });
        this.mLastUpdatedTime.setText(AppVersionModels.Platform.ANDROID.getAppInstallTime(context2));
    }

    public void setOnClickCheckUpdates(View.OnClickListener onClickListener2) {
        this.onClickListener = onClickListener2;
        this.mCheckForUpdates.setOnClickListener(onClickListener2);
    }

    public void updateLatestVersionTextView(String str) {
        this.mLatestVersion.setText(str);
    }

    public void setMandatory(View.OnClickListener onClickListener2, String str) {
        this.mClose.setVisibility(4);
        this.mLastUpdatedTime.setVisibility(8);
        this.mLastUpdatedTimeTitle.setVisibility(8);
        this.mCheckForUpdates.setVisibility(0);
        this.mCheckForUpdates.setText(R.string.settings_lbl_installUpdate);
        this.mCheckForUpdates.setOnClickListener(onClickListener2);
        this.mNewUpdateAvailableTitle.setVisibility(0);
        this.maApplicationVersionTitle.setText(R.string.settings_lbl_appicationUpdate);
        this.mLatestVersion.setText(str);
    }

    public void show() {
        super.show();
    }

    public void setParentView(final View view2) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(AppVersionDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                AppVersionDialog.this.getWindow().addFlags(2);
                AppVersionDialog.this.getWindow().setAttributes(layoutParams);
                View view = view2;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.white_blur);
                }
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                View view = view2;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.transparent);
                }
            }
        });
    }

    public void setInstallUpdateButton(View.OnClickListener onClickListener2) {
        this.mCheckForUpdates.setText(R.string.settings_lbl_installUpdate);
        this.mCheckForUpdates.setOnClickListener(onClickListener2);
    }

    public void setInstallUpdateInvisible() {
        this.mCheckForUpdates.setVisibility(8);
    }
}
