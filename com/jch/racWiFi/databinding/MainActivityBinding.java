package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import com.google.android.material.navigation.NavigationView;
import com.jch_hitachi.aircloudglobal.R;

public abstract class MainActivityBinding extends ViewDataBinding {
    public final DrawerLayout drawerLayout;
    public final FragmentContainerView navHostFragment;
    public final NavigationView navView;
    public final ConstraintLayout rightDrawerLayout;

    protected MainActivityBinding(Object obj, View view, int i, DrawerLayout drawerLayout2, FragmentContainerView fragmentContainerView, NavigationView navigationView, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.drawerLayout = drawerLayout2;
        this.navHostFragment = fragmentContainerView;
        this.navView = navigationView;
        this.rightDrawerLayout = constraintLayout;
    }

    public static MainActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MainActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_activity, viewGroup, z, obj);
    }

    public static MainActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MainActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_activity, (ViewGroup) null, false, obj);
    }

    public static MainActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainActivityBinding bind(View view, Object obj) {
        return (MainActivityBinding) bind(obj, view, R.layout.main_activity);
    }
}
