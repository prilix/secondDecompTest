package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch_hitachi.aircloudglobal.R;

public abstract class LayoutRacListBinding extends ViewDataBinding {
    public final LayoutEcHeaderBinding layoutEcHeader;
    public final RecyclerView recyclerViewECSelectACs;
    public final Button textViewSelectAcSave;

    protected LayoutRacListBinding(Object obj, View view, int i, LayoutEcHeaderBinding layoutEcHeaderBinding, RecyclerView recyclerView, Button button) {
        super(obj, view, i);
        this.layoutEcHeader = layoutEcHeaderBinding;
        this.recyclerViewECSelectACs = recyclerView;
        this.textViewSelectAcSave = button;
    }

    public static LayoutRacListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutRacListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutRacListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_rac_list, viewGroup, z, obj);
    }

    public static LayoutRacListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutRacListBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutRacListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_rac_list, (ViewGroup) null, false, obj);
    }

    public static LayoutRacListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutRacListBinding bind(View view, Object obj) {
        return (LayoutRacListBinding) bind(obj, view, R.layout.layout_rac_list);
    }
}
