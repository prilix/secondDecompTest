package com.jch.racWiFi.settings.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.databinding.FragmentServiceRequestBinding;
import com.jch.racWiFi.databinding.LayoutErrorDisplayBinding;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.standard.BannerListener;
import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class ServiceRequestFragment extends GenericFragment {
    @Inject
    Binder mBinder;
    /* access modifiers changed from: private */
    public FragmentServiceRequestBinding mBinding;
    private Error mError;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mError = Error.getInstance(getArguments());
        }
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            showError((DetailedIduModel.IduErrorStatus) arguments.getParcelable(Constants.Keys.ERROR_STATUS));
        }
    }

    private void showError(DetailedIduModel.IduErrorStatus iduErrorStatus) {
        View root = this.mBinder.getBanner(iduErrorStatus, new BannerListener<DetailedIduModel.IduErrorStatus, LayoutErrorDisplayBinding>() {
            public void onItemClick(View view, DetailedIduModel.IduErrorStatus iduErrorStatus, LayoutErrorDisplayBinding layoutErrorDisplayBinding) {
            }

            public void onClick(View view, DetailedIduModel.IduErrorStatus iduErrorStatus, LayoutErrorDisplayBinding layoutErrorDisplayBinding) {
                ServiceRequestFragment.this.mBinding.layoutRacError.removeAllViews();
            }
        }, false).getViewDataBinding().getRoot();
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
        this.mBinding.layoutRacError.addView(root);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentServiceRequestBinding fragmentServiceRequestBinding = (FragmentServiceRequestBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_service_request, viewGroup, false);
        this.mBinding = fragmentServiceRequestBinding;
        fragmentServiceRequestBinding.imageButtonMenu.setOnClickListener(new ServiceRequestFragment$$ExternalSyntheticLambda2(this));
        this.mBinding.buttonServiceRequestPortal.setOnClickListener(new ServiceRequestFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.buttonCallServiceDesk.setOnClickListener(new ServiceRequestFragment$$ExternalSyntheticLambda0(this));
        return this.mBinding.getRoot();
    }

    /* access modifiers changed from: private */
    public void OnClickMenu(View view) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mBinding.unbind();
    }

    /* access modifiers changed from: package-private */
    public void OnClickViewHitachiServiePortal(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://kadenfan.hitachi.co.jp/support/inquiry/"));
        startActivity(intent);
    }

    /* access modifiers changed from: package-private */
    public void OnClickCallServiceDesk(View view) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:0570-0031-68"));
        startActivity(intent);
    }
}
