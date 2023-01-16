package com.jch.racWiFi.fcm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.fcm.model.Section;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.fcm.view_holder.TypeViewHolder;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeViewHolder> {
    private final Context mContext;
    private FcmListener<Section, TypeAdapter> mListener;
    private final List<Section> sectionList = new ArrayList();

    public List<Section> getSectionList() {
        return this.sectionList;
    }

    public TypeAdapter(Context context) {
        this.mContext = context;
    }

    public void setListener(FcmListener<Section, TypeAdapter> fcmListener) {
        this.mListener = fcmListener;
    }

    public TypeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TypeViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.item_fcm_notification_type, viewGroup, false), this.mListener, this);
    }

    public void onBindViewHolder(TypeViewHolder typeViewHolder, int i) {
        typeViewHolder.bind(this.sectionList.get(i));
    }

    public int getItemCount() {
        return this.sectionList.size();
    }

    public void setSection(Section section) {
        for (Section next : getSectionList()) {
            next.setSelectedForFilter(false);
            if (next.getType().equals(section.getType())) {
                next.setSelectedForFilter(true);
            }
        }
        notifyDataSetChanged();
    }
}
