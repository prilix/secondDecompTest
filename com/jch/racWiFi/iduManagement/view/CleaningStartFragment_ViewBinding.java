package com.jch.racWiFi.iduManagement.view;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class CleaningStartFragment_ViewBinding implements Unbinder {
    private CleaningStartFragment target;
    private View view7f0a011e;
    private View view7f0a0156;

    public CleaningStartFragment_ViewBinding(final CleaningStartFragment cleaningStartFragment, View view) {
        this.target = cleaningStartFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'OnClickBack'");
        cleaningStartFragment.mBack = (ImageButton) C0840Utils.castView(findRequiredView, R.id.back_button, "field 'mBack'", ImageButton.class);
        this.view7f0a011e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleaningStartFragment.OnClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickBack", 0, ImageButton.class));
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.button_clean_filter, "field 'mCleanFilter' and method 'OnClickCleanFilter'");
        cleaningStartFragment.mCleanFilter = (Button) C0840Utils.castView(findRequiredView2, R.id.button_clean_filter, "field 'mCleanFilter'", Button.class);
        this.view7f0a0156 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cleaningStartFragment.OnClickCleanFilter((Button) C0840Utils.castParam(view, "doClick", 0, "OnClickCleanFilter", 0, Button.class));
            }
        });
        cleaningStartFragment.mCleaningModeHeading = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_clean_filter_title, "field 'mCleaningModeHeading'", TextView.class);
        cleaningStartFragment.mCleaningModeSubHeading = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_idu_frost_wash_sub_title, "field 'mCleaningModeSubHeading'", TextView.class);
    }

    public void unbind() {
        CleaningStartFragment cleaningStartFragment = this.target;
        if (cleaningStartFragment != null) {
            this.target = null;
            cleaningStartFragment.mBack = null;
            cleaningStartFragment.mCleanFilter = null;
            cleaningStartFragment.mCleaningModeHeading = null;
            cleaningStartFragment.mCleaningModeSubHeading = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a0156.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0156 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
