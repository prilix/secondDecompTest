package com.jch.racWiFi.fcm.view_holder;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class ErrorViewHolder_ViewBinding implements Unbinder {
    private ErrorViewHolder target;

    public ErrorViewHolder_ViewBinding(ErrorViewHolder errorViewHolder, View view) {
        this.target = errorViewHolder;
        errorViewHolder.title = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.item_fcm_error_title, "field 'title'", TextView.class);
        errorViewHolder.description = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.item_fcm_error_description, "field 'description'", TextView.class);
        errorViewHolder.date = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.item_fcm_error_date, "field 'date'", TextView.class);
    }

    public void unbind() {
        ErrorViewHolder errorViewHolder = this.target;
        if (errorViewHolder != null) {
            this.target = null;
            errorViewHolder.title = null;
            errorViewHolder.description = null;
            errorViewHolder.date = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
