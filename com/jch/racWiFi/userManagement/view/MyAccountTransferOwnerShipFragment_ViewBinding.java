package com.jch.racWiFi.userManagement.view;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountTransferOwnerShipFragment_ViewBinding implements Unbinder {
    private MyAccountTransferOwnerShipFragment target;
    private View view7f0a011e;
    private View view7f0a05ea;

    public MyAccountTransferOwnerShipFragment_ViewBinding(final MyAccountTransferOwnerShipFragment myAccountTransferOwnerShipFragment, View view) {
        this.target = myAccountTransferOwnerShipFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_transfer_owner_ship, "field 'transferOwnerShipLL' and method 'onClickTransferOwnerShip'");
        myAccountTransferOwnerShipFragment.transferOwnerShipLL = (LinearLayout) C0840Utils.castView(findRequiredView, R.id.layout_transfer_owner_ship, "field 'transferOwnerShipLL'", LinearLayout.class);
        this.view7f0a05ea = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountTransferOwnerShipFragment.onClickTransferOwnerShip();
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onClickBackButton'");
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountTransferOwnerShipFragment.onClickBackButton();
            }
        });
    }

    public void unbind() {
        MyAccountTransferOwnerShipFragment myAccountTransferOwnerShipFragment = this.target;
        if (myAccountTransferOwnerShipFragment != null) {
            this.target = null;
            myAccountTransferOwnerShipFragment.transferOwnerShipLL = null;
            this.view7f0a05ea.setOnClickListener((View.OnClickListener) null);
            this.view7f0a05ea = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
