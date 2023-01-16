package com.jch.racWiFi.userManagement.view;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch_hitachi.aircloudglobal.R;

public class DeleteAndDisableAccountTransferOwnerShipFragment_ViewBinding implements Unbinder {
    private DeleteAndDisableAccountTransferOwnerShipFragment target;
    private View view7f0a011e;
    private View view7f0a05ea;

    public DeleteAndDisableAccountTransferOwnerShipFragment_ViewBinding(final DeleteAndDisableAccountTransferOwnerShipFragment deleteAndDisableAccountTransferOwnerShipFragment, View view) {
        this.target = deleteAndDisableAccountTransferOwnerShipFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_transfer_owner_ship, "field 'transferOwnerShipLL' and method 'onClickTransferOwnerShip'");
        deleteAndDisableAccountTransferOwnerShipFragment.transferOwnerShipLL = (LinearLayout) C0840Utils.castView(findRequiredView, R.id.layout_transfer_owner_ship, "field 'transferOwnerShipLL'", LinearLayout.class);
        this.view7f0a05ea = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deleteAndDisableAccountTransferOwnerShipFragment.onClickTransferOwnerShip();
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onClickBackButton'");
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deleteAndDisableAccountTransferOwnerShipFragment.onClickBackButton();
            }
        });
    }

    public void unbind() {
        DeleteAndDisableAccountTransferOwnerShipFragment deleteAndDisableAccountTransferOwnerShipFragment = this.target;
        if (deleteAndDisableAccountTransferOwnerShipFragment != null) {
            this.target = null;
            deleteAndDisableAccountTransferOwnerShipFragment.transferOwnerShipLL = null;
            this.view7f0a05ea.setOnClickListener((View.OnClickListener) null);
            this.view7f0a05ea = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
