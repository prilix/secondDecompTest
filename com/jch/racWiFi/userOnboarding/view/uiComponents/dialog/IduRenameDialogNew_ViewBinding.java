package com.jch.racWiFi.userOnboarding.view.uiComponents.dialog;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch_hitachi.aircloudglobal.R;

public class IduRenameDialogNew_ViewBinding implements Unbinder {
    private IduRenameDialogNew target;
    private View view7f0a013d;
    private View view7f0a0516;

    public IduRenameDialogNew_ViewBinding(IduRenameDialogNew iduRenameDialogNew) {
        this(iduRenameDialogNew, iduRenameDialogNew.getWindow().getDecorView());
    }

    public IduRenameDialogNew_ViewBinding(final IduRenameDialogNew iduRenameDialogNew, View view) {
        this.target = iduRenameDialogNew;
        iduRenameDialogNew.btnConfirm = (Button) C0840Utils.findRequiredViewAsType(view, R.id.btn_continue, "field 'btnConfirm'", Button.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.btn_cancel, "field 'btnCancel' and method 'onCancelled'");
        iduRenameDialogNew.btnCancel = (Button) C0840Utils.castView(findRequiredView, R.id.btn_cancel, "field 'btnCancel'", Button.class);
        this.view7f0a013d = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                iduRenameDialogNew.onCancelled(view);
            }
        });
        iduRenameDialogNew.newIduNameField = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.et_password_field, "field 'newIduNameField'", EditText.class);
        iduRenameDialogNew.tvIduVendorThingID = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.tv_ssid, "field 'tvIduVendorThingID'", TextView.class);
        iduRenameDialogNew.cardView = (CardView) C0840Utils.findRequiredViewAsType(view, R.id.cardView, "field 'cardView'", CardView.class);
        iduRenameDialogNew.mRecyclerViewDeviceName = (RecyclerView) C0840Utils.findRequiredViewAsType(view, R.id.recycle_view_devices_name, "field 'mRecyclerViewDeviceName'", RecyclerView.class);
        iduRenameDialogNew.cancelSaveView = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.cancelSaveView, "field 'cancelSaveView'", ConstraintLayout.class);
        iduRenameDialogNew.constraintLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.constraint_1, "field 'constraintLayout'", ConstraintLayout.class);
        iduRenameDialogNew.layout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.llinearlayout_included, "field 'layout'", ConstraintLayout.class);
        iduRenameDialogNew.addRacNameButton = (Button) C0840Utils.findRequiredViewAsType(view, R.id.addRacNameButton, "field 'addRacNameButton'", Button.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.iv_close2, "method 'onViewClicked'");
        this.view7f0a0516 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                iduRenameDialogNew.onViewClicked();
            }
        });
    }

    public void unbind() {
        IduRenameDialogNew iduRenameDialogNew = this.target;
        if (iduRenameDialogNew != null) {
            this.target = null;
            iduRenameDialogNew.btnConfirm = null;
            iduRenameDialogNew.btnCancel = null;
            iduRenameDialogNew.newIduNameField = null;
            iduRenameDialogNew.tvIduVendorThingID = null;
            iduRenameDialogNew.cardView = null;
            iduRenameDialogNew.mRecyclerViewDeviceName = null;
            iduRenameDialogNew.cancelSaveView = null;
            iduRenameDialogNew.constraintLayout = null;
            iduRenameDialogNew.layout = null;
            iduRenameDialogNew.addRacNameButton = null;
            this.view7f0a013d.setOnClickListener((View.OnClickListener) null);
            this.view7f0a013d = null;
            this.view7f0a0516.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0516 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
