package com.jch.racWiFi.userOnboarding.view.uiComponents.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jch_hitachi.aircloudglobal.R;

public class IduRenameDialogNew extends Dialog {
    @BindView(2131362034)
    public Button addRacNameButton;
    @BindView(2131362109)
    public Button btnCancel;
    @BindView(2131362110)
    public Button btnConfirm;
    @BindView(2131362189)
    ConstraintLayout cancelSaveView;
    @BindView(2131362192)
    public CardView cardView;
    @BindView(2131362278)
    public ConstraintLayout constraintLayout;
    @BindView(2131363356)
    public ConstraintLayout layout;
    @BindView(2131363592)
    public RecyclerView mRecyclerViewDeviceName;
    @BindView(2131362442)
    public EditText newIduNameField;
    @BindView(2131364820)
    public TextView tvIduVendorThingID;

    public IduRenameDialogNew(Context context) {
        super(context);
        initDialog(context);
    }

    private void initDialog(Context context) {
        setContentView(R.layout.enter_device_name_popup_new_updated);
        ButterKnife.bind((Dialog) this);
        getWindow().setLayout(-1, -2);
    }

    public IduRenameDialogNew(Context context, int i) {
        super(context, i);
        initDialog(context);
    }

    protected IduRenameDialogNew(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        initDialog(context);
    }

    @OnClick({2131362109})
    public void onCancelled(View view) {
        dismiss();
    }

    @OnClick({2131363094})
    public void onViewClicked() {
        dismiss();
    }

    public void toShowAddAcButton(boolean z) {
        if (z) {
            this.cancelSaveView.setVisibility(4);
            this.addRacNameButton.setVisibility(0);
            return;
        }
        this.cancelSaveView.setVisibility(0);
        this.addRacNameButton.setVisibility(4);
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(IduRenameDialogNew.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                IduRenameDialogNew.this.getWindow().addFlags(2);
                IduRenameDialogNew.this.getWindow().setAttributes(layoutParams);
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
