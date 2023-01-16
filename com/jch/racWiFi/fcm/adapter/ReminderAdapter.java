package com.jch.racWiFi.fcm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.databinding.ItemFcmReminderBinding;
import com.jch.racWiFi.fcm.model.Reminder;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.fcm.view_holder.ReminderViewHolder;
import com.jch_hitachi.aircloudglobal.R;
import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {
    private FcmListener<Reminder, ReminderAdapter> mListener;
    private List<Reminder> mReminderList;

    public void setReminderList(List<Reminder> list) {
        this.mReminderList = list;
        notifyDataSetChanged();
    }

    public void setListener(FcmListener<Reminder, ReminderAdapter> fcmListener) {
        this.mListener = fcmListener;
    }

    public ReminderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ReminderViewHolder((ItemFcmReminderBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_fcm_reminder, viewGroup, false), this.mListener, this);
    }

    public void onBindViewHolder(ReminderViewHolder reminderViewHolder, int i) {
        reminderViewHolder.bind(this.mReminderList.get(i));
    }

    public int getItemCount() {
        List<Reminder> list = this.mReminderList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
