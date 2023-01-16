package com.jch.racWiFi.fcm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.fcm.view_holder.ErrorViewHolder;
import com.jch_hitachi.aircloudglobal.R;
import java.util.List;

public class ErrorAdapter extends RecyclerView.Adapter<ErrorViewHolder> {
    private List<Error> mErrorList;
    private FcmListener<Error, ErrorAdapter> mListener;

    public void setErrorList(List<Error> list) {
        this.mErrorList = list;
        notifyDataSetChanged();
    }

    public void setListener(FcmListener<Error, ErrorAdapter> fcmListener) {
        this.mListener = fcmListener;
    }

    public ErrorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ErrorViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fcm_error, viewGroup, false), this.mListener, this);
    }

    public void onBindViewHolder(ErrorViewHolder errorViewHolder, int i) {
        errorViewHolder.bind(this.mErrorList.get(i));
    }

    public int getItemCount() {
        return this.mErrorList.size();
    }
}
