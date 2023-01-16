package com.jch.racWiFi.iduManagement.smartFence.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.databinding.SmartFenceFragmentDummyBinding;
import com.jch_hitachi.aircloudglobal.R;

public class SmartFenceFragmentDummy extends GenericFragment {
    SmartFenceFragmentDummyBinding mBinding;

    public void onDestroy() {
        super.onDestroy();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        SmartFenceFragmentDummyBinding smartFenceFragmentDummyBinding = (SmartFenceFragmentDummyBinding) DataBindingUtil.inflate(layoutInflater, R.layout.smart_fence_fragment_dummy, viewGroup, false);
        this.mBinding = smartFenceFragmentDummyBinding;
        return smartFenceFragmentDummyBinding.getRoot();
    }
}
