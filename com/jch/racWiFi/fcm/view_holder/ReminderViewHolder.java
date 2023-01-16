package com.jch.racWiFi.fcm.view_holder;

import android.text.Html;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.databinding.ItemFcmReminderBinding;
import com.jch.racWiFi.fcm.adapter.ReminderAdapter;
import com.jch.racWiFi.fcm.model.Reminder;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;

public class ReminderViewHolder extends RecyclerView.ViewHolder {
    private final ItemFcmReminderBinding mBinding;
    private final FcmListener<Reminder, ReminderAdapter> mFcmListener;

    public ReminderViewHolder(ItemFcmReminderBinding itemFcmReminderBinding, FcmListener<Reminder, ReminderAdapter> fcmListener, ReminderAdapter reminderAdapter) {
        super(itemFcmReminderBinding.getRoot());
        this.mFcmListener = fcmListener;
        this.mBinding = itemFcmReminderBinding;
        itemFcmReminderBinding.imageButtonClear.setOnClickListener(new ReminderViewHolder$$ExternalSyntheticLambda0(this, reminderAdapter));
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-fcm-view_holder-ReminderViewHolder  reason: not valid java name */
    public /* synthetic */ void m955lambda$new$0$comjchracWiFifcmview_holderReminderViewHolder(ReminderAdapter reminderAdapter, View view) {
        this.mFcmListener.onClose(view, (Reminder) view.getTag(), reminderAdapter);
    }

    public void bind(Reminder reminder) {
        this.mBinding.imageButtonClear.setTag(reminder);
        this.mBinding.itemFcmReminderTitle.setText(reminder.getTitle(this.mBinding.getRoot().getContext()));
        this.mBinding.itemFcmReminderImage.setImageResource(reminder.getSubCategory().getIcon());
        this.mBinding.imageButtonClear.setImageDrawable(R.drawable.ic_close_red);
        this.mBinding.itemFcmReminderDescription.setText(Html.fromHtml(reminder.getDescription(this.mBinding.getRoot().getContext())));
        this.mBinding.itemFcmReminderDate.setText(DateAndTimeUtils.getNotificationDateWithTime(Long.valueOf(reminder.getTimestamp()), Constants.FCM.DATE_PATTERN));
    }
}
