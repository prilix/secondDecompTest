package com.jch.racWiFi.fcm.view_holder;

import android.content.Context;
import android.text.Html;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.fcm.adapter.ErrorAdapter;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.p010di.util.Constants;

public class ErrorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(2131363080)
    TextView date;
    @BindView(2131363081)
    TextView description;
    private final ErrorAdapter mAdapter;
    private final Context mContext;
    private final FcmListener<Error, ErrorAdapter> mListener;
    @BindView(2131363083)
    TextView title;

    public ErrorViewHolder(View view, FcmListener<Error, ErrorAdapter> fcmListener, ErrorAdapter errorAdapter) {
        super(view);
        ButterKnife.bind((Object) this, view);
        this.mContext = view.getContext();
        view.setOnClickListener(this);
        this.mListener = fcmListener;
        this.mAdapter = errorAdapter;
    }

    public void bind(Error error) {
        this.itemView.setTag(error);
        this.title.setTag(error);
        this.description.setTag(error);
        this.date.setTag(error);
        this.title.setText(Html.fromHtml(error.getBellTitle(this.mContext)));
        this.description.setText(Html.fromHtml(error.getBellDescription(this.mContext)));
        this.date.setText(DateAndTimeUtils.getNotificationDateWithTime(Long.valueOf(error.getTimestamp()), Constants.FCM.DATE_PATTERN));
    }

    public void onClick(View view) {
        FcmListener<Error, ErrorAdapter> fcmListener = this.mListener;
        if (fcmListener != null) {
            fcmListener.onClick(view, (Error) view.getTag(), this.mAdapter);
        }
    }
}
