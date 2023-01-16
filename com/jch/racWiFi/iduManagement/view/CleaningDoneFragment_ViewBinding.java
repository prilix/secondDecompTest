package com.jch.racWiFi.iduManagement.view;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class CleaningDoneFragment_ViewBinding implements Unbinder {
    private CleaningDoneFragment target;

    public CleaningDoneFragment_ViewBinding(CleaningDoneFragment cleaningDoneFragment, View view) {
        this.target = cleaningDoneFragment;
        cleaningDoneFragment.mTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_clean_filter_title, "field 'mTitle'", TextView.class);
        cleaningDoneFragment.mSubTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_filter_has_been_cleaned, "field 'mSubTitle'", TextView.class);
        cleaningDoneFragment.mSubTitleBottom = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_filter_cleaned, "field 'mSubTitleBottom'", TextView.class);
    }

    public void unbind() {
        CleaningDoneFragment cleaningDoneFragment = this.target;
        if (cleaningDoneFragment != null) {
            this.target = null;
            cleaningDoneFragment.mTitle = null;
            cleaningDoneFragment.mSubTitle = null;
            cleaningDoneFragment.mSubTitleBottom = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
