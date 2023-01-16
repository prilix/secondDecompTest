package com.jch.racWiFi.customViews.customWidgets;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch_hitachi.aircloudglobal.R;

public class TriStateCheckbox_ViewBinding implements Unbinder {
    private TriStateCheckbox target;

    public TriStateCheckbox_ViewBinding(TriStateCheckbox triStateCheckbox) {
        this(triStateCheckbox, triStateCheckbox);
    }

    public TriStateCheckbox_ViewBinding(TriStateCheckbox triStateCheckbox, View view) {
        this.target = triStateCheckbox;
        triStateCheckbox.checkBox = (CheckBox) C0840Utils.findRequiredViewAsType(view, R.id.cb_custom, "field 'checkBox'", CheckBox.class);
        triStateCheckbox.partialImage = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.iv_partial, "field 'partialImage'", ImageView.class);
    }

    public void unbind() {
        TriStateCheckbox triStateCheckbox = this.target;
        if (triStateCheckbox != null) {
            this.target = null;
            triStateCheckbox.checkBox = null;
            triStateCheckbox.partialImage = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
