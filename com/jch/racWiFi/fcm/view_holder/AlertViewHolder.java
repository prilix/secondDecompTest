package com.jch.racWiFi.fcm.view_holder;

import android.text.Html;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.databinding.ItemFcmAlertBinding;
import com.jch.racWiFi.fcm.adapter.AlertAdapter;
import com.jch.racWiFi.fcm.model.Alert;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.fcm.util.AlertSubCategory;
import com.jch.racWiFi.p010di.util.Constants;

public class AlertViewHolder extends RecyclerView.ViewHolder {
    private final ItemFcmAlertBinding mBinding;
    private final FcmListener<Alert, AlertAdapter> mFcmListener;

    public AlertViewHolder(ItemFcmAlertBinding itemFcmAlertBinding, FcmListener<Alert, AlertAdapter> fcmListener, AlertAdapter alertAdapter) {
        super(itemFcmAlertBinding.getRoot());
        this.mFcmListener = fcmListener;
        this.mBinding = itemFcmAlertBinding;
        itemFcmAlertBinding.itemFcmAlertClearImage.setOnClickListener(new AlertViewHolder$$ExternalSyntheticLambda0(this, alertAdapter));
        itemFcmAlertBinding.getRoot().setOnClickListener(new AlertViewHolder$$ExternalSyntheticLambda1(this, alertAdapter));
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-fcm-view_holder-AlertViewHolder  reason: not valid java name */
    public /* synthetic */ void m953lambda$new$0$comjchracWiFifcmview_holderAlertViewHolder(AlertAdapter alertAdapter, View view) {
        this.mFcmListener.onClose(view, (Alert) view.getTag(), alertAdapter);
    }

    /* renamed from: lambda$new$1$com-jch-racWiFi-fcm-view_holder-AlertViewHolder  reason: not valid java name */
    public /* synthetic */ void m954lambda$new$1$comjchracWiFifcmview_holderAlertViewHolder(AlertAdapter alertAdapter, View view) {
        this.mFcmListener.onClick(view, (Alert) view.getTag(), alertAdapter);
    }

    public void bind(Alert alert) {
        this.mBinding.itemFcmAlertClearImage.setTag(alert);
        this.mBinding.getRoot().setTag(alert);
        this.mBinding.itemFcmAlertTitle.setText(alert.getTitle(this.mBinding.getRoot().getContext()));
        String description = alert.getDescription(this.mBinding.getRoot().getContext());
        if (alert.getSubCategory() == AlertSubCategory.ENERGY_CONSUMPTION_BUDGET) {
            if (alert.getFamilyName() != null) {
                this.mBinding.itemFcmAlertDescription.setText(Html.fromHtml(getMessage(description, alert.getFamilyName())));
            } else {
                this.mBinding.itemFcmAlertDescription.setText(Html.fromHtml(getMessage(description, "null")));
            }
        } else if (alert.getSubCategory() != AlertSubCategory.RAC_OFFLINE) {
            this.mBinding.itemFcmAlertDescription.setText(Html.fromHtml(description));
        } else if (description.contains("-")) {
            this.mBinding.itemFcmAlertDescription.setText(Html.fromHtml(description.split("-")[1]));
        } else {
            this.mBinding.itemFcmAlertDescription.setText(Html.fromHtml(description));
        }
        this.mBinding.itemFcmAlertImage.setImageResource(alert.getSubCategory().getIcon());
        this.mBinding.itemFcmAlertDate.setText(DateAndTimeUtils.getNotificationDateWithTime(Long.valueOf(alert.getTimestamp()), Constants.FCM.DATE_PATTERN));
    }

    private String getMessage(String str, String str2) {
        return str.substring(str.indexOf(str2) + str2.length() + 1);
    }
}
