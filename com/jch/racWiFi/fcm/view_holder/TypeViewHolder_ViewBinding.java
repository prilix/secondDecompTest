package com.jch.racWiFi.fcm.view_holder;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class TypeViewHolder_ViewBinding implements Unbinder {
    private TypeViewHolder target;
    private View view7f0a0597;

    public TypeViewHolder_ViewBinding(final TypeViewHolder typeViewHolder, View view) {
        this.target = typeViewHolder;
        typeViewHolder.mDeviceName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_device_name, "field 'mDeviceName'", TextView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_manage_devices, "field 'mOuterLayout' and method 'onClickItem'");
        typeViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_manage_devices, "field 'mOuterLayout'", ConstraintLayout.class);
        this.view7f0a0597 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                typeViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
            }
        });
        typeViewHolder.mTickMark = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_selected_langauge, "field 'mTickMark'", ImageView.class);
    }

    public void unbind() {
        TypeViewHolder typeViewHolder = this.target;
        if (typeViewHolder != null) {
            this.target = null;
            typeViewHolder.mDeviceName = null;
            typeViewHolder.mOuterLayout = null;
            typeViewHolder.mTickMark = null;
            this.view7f0a0597.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0597 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
