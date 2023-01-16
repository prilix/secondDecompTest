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

public class PairedAirConditionerDialog extends Dialog {
    @BindView(2131362159)
    public Button changeHomeRouter;
    @BindView(2131362163)
    public Button continueButton;
    @BindView(2131364814)
    public TextView routerSsidText;

    public PairedAirConditionerDialog(Context context) {
        super(context);
        initDialog(context);
    }

    public PairedAirConditionerDialog(Context context, int i) {
        super(context, i);
        initDialog(context);
    }

    protected PairedAirConditionerDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        initDialog(context);
    }

    private void initDialog(Context context) {
        setContentView(R.layout.dialog_this_ac_is_paired_india);
        ButterKnife.bind((Dialog) this);
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(PairedAirConditionerDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                PairedAirConditionerDialog.this.getWindow().addFlags(2);
                PairedAirConditionerDialog.this.getWindow().setAttributes(layoutParams);
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
