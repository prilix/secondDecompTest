package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class OnBoardedAircontionerDialog extends Dialog {
    @BindView(2131362159)
    public Button cancelButton;
    @BindView(2131362163)
    public Button continueButton;
    @BindView(2131364814)
    public TextView routerSsidText;

    public OnBoardedAircontionerDialog(Context context) {
        super(context);
        initDialog(context);
    }

    public OnBoardedAircontionerDialog(Context context, int i) {
        super(context, i);
        initDialog(context);
    }

    protected OnBoardedAircontionerDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        initDialog(context);
    }

    private void initDialog(Context context) {
        setContentView(R.layout.dialog_this_ac_is_onboarded_india);
        ButterKnife.bind((Dialog) this);
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(OnBoardedAircontionerDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -1;
                OnBoardedAircontionerDialog.this.getWindow().addFlags(2);
                OnBoardedAircontionerDialog.this.getWindow().setAttributes(layoutParams);
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
