package com.jch.racWiFi.fcm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.fcm.model.Section;
import com.jch.racWiFi.fcm.repository.AdapterRepository;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.fcm.view_holder.SectionViewHolder;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionViewHolder> {
    private FcmListener<Section, SectionAdapter> mListener;
    private final List<Section> mSectionList;
    private final List<Section> mSectionSubList;

    public List<Section> getSectionList() {
        return this.mSectionList;
    }

    public void setListener(FcmListener<Section, SectionAdapter> fcmListener) {
        this.mListener = fcmListener;
    }

    public SectionAdapter(AdapterRepository adapterRepository) {
        ArrayList arrayList = new ArrayList();
        this.mSectionList = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.mSectionSubList = arrayList2;
        createSectionList(adapterRepository);
        arrayList.addAll(arrayList2);
    }

    private void createSectionList(AdapterRepository adapterRepository) {
        if (this.mSectionSubList.isEmpty()) {
            Section section = new Section();
            section.setClearAll(false);
            section.setTitle(R.string.notification_lbl_invites);
            section.setAdapter(adapterRepository.getInviteeListAdapter());
            section.setType(Type.INVITE);
            this.mSectionSubList.add(section);
            Section section2 = new Section();
            section2.setClearAll(true);
            section2.setTitle(R.string.notification_lbl_errors);
            section2.setAdapter(adapterRepository.getErrorAdapter());
            section2.setType(Type.ERRORS);
            this.mSectionSubList.add(section2);
            Section section3 = new Section();
            section3.setClearAll(true);
            section3.setTitle(R.string.smartFence_lbl_smartFence);
            section3.setAdapter(adapterRepository.getSmartFenceAdapter());
            section3.setType(Type.SMART_FENCE);
            this.mSectionSubList.add(section3);
            Section section4 = new Section();
            section4.setClearAll(true);
            section4.setTitle(R.string.notification_lbl_alerts);
            section4.setAdapter(adapterRepository.getAlertAdapter());
            section4.setType(Type.ALERTS);
            this.mSectionSubList.add(section4);
            Section section5 = new Section();
            section5.setClearAll(true);
            section5.setTitle(R.string.notification_lbl_reminders);
            section5.setAdapter(adapterRepository.getReminderAdapter());
            section5.setType(Type.REMINDERS);
            this.mSectionSubList.add(section5);
            Section section6 = new Section();
            section6.setClearAll(false);
            section6.setTitle(R.string.notification_lbl_acActivities);
            section6.setAdapter(adapterRepository.getIduNotificationRecyclerViewAdapter());
            section6.setType(Type.AC_ACTIVITIES);
            this.mSectionSubList.add(section6);
        }
    }

    public SectionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_section, viewGroup, false), this.mListener, this, viewGroup.getContext());
    }

    public void onBindViewHolder(SectionViewHolder sectionViewHolder, int i) {
        sectionViewHolder.bind(this.mSectionSubList.get(i));
    }

    public int getItemCount() {
        return this.mSectionSubList.size();
    }

    public void filter(Type type) {
        this.mSectionSubList.clear();
        if (type.equals(Type.ALL_NOTIFICATIONS)) {
            this.mSectionSubList.addAll(this.mSectionList);
        } else {
            for (Section next : this.mSectionList) {
                if (next.getType().equals(type)) {
                    this.mSectionSubList.add(next);
                }
            }
        }
        notifyDataSetChanged();
    }
}
