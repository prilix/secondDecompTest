package com.jch.racWiFi.fcm.view_holder;

import android.text.Html;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.databinding.ItemFcmSmartFenceBinding;
import com.jch.racWiFi.fcm.adapter.SmartFenceAdapter;
import com.jch.racWiFi.fcm.model.SmartFence;
import com.jch.racWiFi.fcm.standard.FcmListener;

public class SmartFenceViewHolder extends RecyclerView.ViewHolder {
    private final ItemFcmSmartFenceBinding mBinding;
    private final FcmListener<SmartFence, SmartFenceAdapter> mFcmListener;

    public SmartFenceViewHolder(ItemFcmSmartFenceBinding itemFcmSmartFenceBinding, FcmListener<SmartFence, SmartFenceAdapter> fcmListener, SmartFenceAdapter smartFenceAdapter) {
        super(itemFcmSmartFenceBinding.getRoot());
        this.mFcmListener = fcmListener;
        this.mBinding = itemFcmSmartFenceBinding;
        itemFcmSmartFenceBinding.imageButtonClear.setOnClickListener(new SmartFenceViewHolder$$ExternalSyntheticLambda0(this, smartFenceAdapter));
        itemFcmSmartFenceBinding.getRoot().setOnClickListener(new SmartFenceViewHolder$$ExternalSyntheticLambda1(this, smartFenceAdapter));
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-fcm-view_holder-SmartFenceViewHolder */
    public /* synthetic */ void mo29436x72070a80(SmartFenceAdapter smartFenceAdapter, View view) {
        this.mFcmListener.onClose(view, (SmartFence) view.getTag(), smartFenceAdapter);
    }

    /* renamed from: lambda$new$1$com-jch-racWiFi-fcm-view_holder-SmartFenceViewHolder */
    public /* synthetic */ void mo29437x8c22891f(SmartFenceAdapter smartFenceAdapter, View view) {
        this.mFcmListener.onClick(view, (SmartFence) view.getTag(), smartFenceAdapter);
    }

    public void bind(SmartFence smartFence) {
        this.mBinding.imageButtonClear.setTag(smartFence);
        this.mBinding.getRoot().setTag(smartFence);
        this.mBinding.itemFcmSmartFenceTitle.setText(smartFence.getTitle(this.mBinding.getRoot().getContext()));
        this.mBinding.itemFcmSmartFenceDesc.setText(Html.fromHtml(smartFence.getDescription(this.mBinding.getRoot().getContext())));
        this.mBinding.itemFcmSmartFenceImage.setImageResource(smartFence.getSubCategory().getIcon());
        this.mBinding.textViewNotoficationDate.setText(DateAndTimeUtils.getNotificationDateWithTime(Long.valueOf(smartFence.getTimestamp()), SmartFence.DATE_TIME_PATTERN));
    }
}
