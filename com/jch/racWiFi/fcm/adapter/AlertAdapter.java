package com.jch.racWiFi.fcm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.databinding.ItemFcmAlertBinding;
import com.jch.racWiFi.fcm.model.Alert;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.fcm.view_holder.AlertViewHolder;
import com.jch_hitachi.aircloudglobal.R;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertViewHolder> {
    private List<Alert> mAlertList;
    private FcmListener<Alert, AlertAdapter> mFcmListener;

    public void setAlertList(List<Alert> list) {
        this.mAlertList = list;
        notifyDataSetChanged();
    }

    public void setListener(FcmListener<Alert, AlertAdapter> fcmListener) {
        this.mFcmListener = fcmListener;
    }

    public AlertViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlertViewHolder((ItemFcmAlertBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_fcm_alert, viewGroup, false), this.mFcmListener, this);
    }

    public void onBindViewHolder(AlertViewHolder alertViewHolder, int i) {
        alertViewHolder.bind(this.mAlertList.get(i));
    }

    public int getItemCount() {
        List<Alert> list = this.mAlertList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
