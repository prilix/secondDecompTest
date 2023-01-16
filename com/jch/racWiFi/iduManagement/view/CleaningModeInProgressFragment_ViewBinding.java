package com.jch.racWiFi.iduManagement.view;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class CleaningModeInProgressFragment_ViewBinding implements Unbinder {
    private CleaningModeInProgressFragment target;
    private View view7f0a011e;
    private View view7f0a016a;

    public CleaningModeInProgressFragment_ViewBinding(final CleaningModeInProgressFragment cleaningModeInProgressFragment, View view) {
        this.target = cleaningModeInProgressFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'OnClickBack'");
        cleaningModeInProgressFragment.mBack = (ImageButton) C0840Utils.castView(findRequiredView, R.id.back_button, "field 'mBack'", ImageButton.class);
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleaningModeInProgressFragment.OnClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickBack", 0, ImageButton.class));
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.button_hide, "field 'mHide' and method 'OnClickHide'");
        cleaningModeInProgressFragment.mHide = (Button) C0840Utils.castView(findRequiredView2, R.id.button_hide, "field 'mHide'", Button.class);
        this.view7f0a016a = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleaningModeInProgressFragment.OnClickHide((Button) C0840Utils.castParam(view, "doClick", 0, "OnClickHide", 0, Button.class));
            }
        });
        cleaningModeInProgressFragment.mCleaningModeHeading = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_clean_filter_title, "field 'mCleaningModeHeading'", TextView.class);
        cleaningModeInProgressFragment.mCleaningModeProgress = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_cleaning_in_progress, "field 'mCleaningModeProgress'", TextView.class);
        cleaningModeInProgressFragment.mCleaningModeWarning = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_idu_frost_wash_sub_title, "field 'mCleaningModeWarning'", TextView.class);
    }

    public void unbind() {
        CleaningModeInProgressFragment cleaningModeInProgressFragment = this.target;
        if (cleaningModeInProgressFragment != null) {
            this.target = null;
            cleaningModeInProgressFragment.mBack = null;
            cleaningModeInProgressFragment.mHide = null;
            cleaningModeInProgressFragment.mCleaningModeHeading = null;
            cleaningModeInProgressFragment.mCleaningModeProgress = null;
            cleaningModeInProgressFragment.mCleaningModeWarning = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a016a.setOnClickListener((View.OnClickListener) null);
            this.view7f0a016a = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
