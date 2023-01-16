package com.jch.racWiFi.iduOnBoarding.common.view;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;
import p015me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrScanFragmentV2_ViewBinding implements Unbinder {
    private QrScanFragmentV2 target;
    private View view7f0a011e;
    private View view7f0a0185;
    private View view7f0a0189;

    public QrScanFragmentV2_ViewBinding(final QrScanFragmentV2 qrScanFragmentV2, View view) {
        this.target = qrScanFragmentV2;
        qrScanFragmentV2.scannerView = (ZXingScannerView) C0840Utils.findRequiredViewAsType(view, R.id.scannerID, "field 'scannerView'", ZXingScannerView.class);
        qrScanFragmentV2.failedToScanWidget = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_sacn_qr_code_title2, "field 'failedToScanWidget'", TextView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_unable_to_scan_qr_code, "field 'unableToScanWidget' and method 'onFailedToScan'");
        qrScanFragmentV2.unableToScanWidget = (Button) C0840Utils.castView(findRequiredView, R.id.button_unable_to_scan_qr_code, "field 'unableToScanWidget'", Button.class);
        this.view7f0a0185 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                qrScanFragmentV2.onFailedToScan();
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.button_where_to_locate_qr_code, "field 'whereToLocateWidget' and method 'onWhereToLocateDialog'");
        qrScanFragmentV2.whereToLocateWidget = (Button) C0840Utils.castView(findRequiredView2, R.id.button_where_to_locate_qr_code, "field 'whereToLocateWidget'", Button.class);
        this.view7f0a0189 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                qrScanFragmentV2.onWhereToLocateDialog();
            }
        });
        qrScanFragmentV2.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        qrScanFragmentV2.mTextViewSteps = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_1, "field 'mTextViewSteps'", TextView.class);
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'goBack'");
        this.view7f0a011e = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                qrScanFragmentV2.goBack();
            }
        });
    }

    public void unbind() {
        QrScanFragmentV2 qrScanFragmentV2 = this.target;
        if (qrScanFragmentV2 != null) {
            this.target = null;
            qrScanFragmentV2.scannerView = null;
            qrScanFragmentV2.failedToScanWidget = null;
            qrScanFragmentV2.unableToScanWidget = null;
            qrScanFragmentV2.whereToLocateWidget = null;
            qrScanFragmentV2.mParent = null;
            qrScanFragmentV2.mTextViewSteps = null;
            this.view7f0a0185.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0185 = null;
            this.view7f0a0189.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0189 = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
