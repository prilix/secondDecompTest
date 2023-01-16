package com.jch.racWiFi.iduOnBoarding.wps.withoutQrCode.view;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch_hitachi.aircloudglobal.R;

public class EnableWpsOnRacWithoutQrFrag_ViewBinding implements Unbinder {
    private EnableWpsOnRacWithoutQrFrag target;
    private View view7f0a011e;
    private View view7f0a02a5;

    public EnableWpsOnRacWithoutQrFrag_ViewBinding(final EnableWpsOnRacWithoutQrFrag enableWpsOnRacWithoutQrFrag, View view) {
        this.target = enableWpsOnRacWithoutQrFrag;
        enableWpsOnRacWithoutQrFrag.mParentView = C0840Utils.findRequiredView(view, R.id.parent, "field 'mParentView'");
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "method 'goBack'");
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                enableWpsOnRacWithoutQrFrag.goBack();
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.forward_button, "method 'goNext'");
        this.view7f0a02a5 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                enableWpsOnRacWithoutQrFrag.goNext();
            }
        });
    }

    public void unbind() {
        EnableWpsOnRacWithoutQrFrag enableWpsOnRacWithoutQrFrag = this.target;
        if (enableWpsOnRacWithoutQrFrag != null) {
            this.target = null;
            enableWpsOnRacWithoutQrFrag.mParentView = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a02a5.setOnClickListener((View.OnClickListener) null);
            this.view7f0a02a5 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
