package com.jch.racWiFi.fcm.view_holder;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.NotificationSectionBinding;
import com.jch.racWiFi.fcm.adapter.AlertAdapter;
import com.jch.racWiFi.fcm.adapter.ErrorAdapter;
import com.jch.racWiFi.fcm.adapter.ReminderAdapter;
import com.jch.racWiFi.fcm.adapter.SectionAdapter;
import com.jch.racWiFi.fcm.adapter.SmartFenceAdapter;
import com.jch.racWiFi.fcm.model.Section;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.userManagement.adapter.InviteeListAdapter;
import com.jch_hitachi.aircloudglobal.R;

public class SectionViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    private final NotificationSectionBinding mBinding;
    private final FcmListener<Section, SectionAdapter> mListener;

    public SectionViewHolder(View view, FcmListener<Section, SectionAdapter> fcmListener, SectionAdapter sectionAdapter, Context context2) {
        super(view);
        this.mListener = fcmListener;
        NotificationSectionBinding notificationSectionBinding = (NotificationSectionBinding) DataBindingUtil.bind(view);
        this.mBinding = notificationSectionBinding;
        this.context = context2;
        if (notificationSectionBinding != null) {
            notificationSectionBinding.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            notificationSectionBinding.notificationSection.setOnClickListener(new SectionViewHolder$$ExternalSyntheticLambda0(this, sectionAdapter));
            notificationSectionBinding.notificationSectionClearAll.setOnClickListener(new SectionViewHolder$$ExternalSyntheticLambda1(this, sectionAdapter));
        }
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-fcm-view_holder-SectionViewHolder  reason: not valid java name */
    public /* synthetic */ void m956lambda$new$0$comjchracWiFifcmview_holderSectionViewHolder(SectionAdapter sectionAdapter, View view) {
        Section section = (Section) view.getTag();
        section.setExpanded(!section.isExpanded());
        this.mListener.onClick(view, section, sectionAdapter);
    }

    /* renamed from: lambda$new$1$com-jch-racWiFi-fcm-view_holder-SectionViewHolder  reason: not valid java name */
    public /* synthetic */ void m957lambda$new$1$comjchracWiFifcmview_holderSectionViewHolder(SectionAdapter sectionAdapter, View view) {
        this.mListener.onClose(view, (Section) view.getTag(), sectionAdapter);
    }

    public void bind(Section section) {
        int i;
        this.mBinding.notificationSectionClearAll.setTag(section);
        this.mBinding.notificationSection.setTag(section);
        this.mBinding.notificationSectionTitle.setText(section.getTitle());
        this.mBinding.recyclerView.setAdapter(section.getAdapter());
        int i2 = 0;
        switch (C18361.$SwitchMap$com$jch$racWiFi$fcm$util$Type[section.getType().ordinal()]) {
            case 1:
                i = ((InviteeListAdapter) section.getAdapter()).getItemCount();
                break;
            case 2:
            case 3:
                i = ((ReminderAdapter) section.getAdapter()).getItemCount();
                break;
            case 4:
                i = ((ErrorAdapter) section.getAdapter()).getItemCount();
                break;
            case 5:
                i = ((SmartFenceAdapter) section.getAdapter()).getItemCount();
                break;
            case 6:
                i = ((HomePageActivity.IDUNotificationRecyclerViewAdapter) section.getAdapter()).getItemCount();
                break;
            case 7:
                i = ((AlertAdapter) section.getAdapter()).getItemCount();
                break;
            default:
                i = 0;
                break;
        }
        this.mBinding.recyclerView.setVisibility((!section.isExpanded() || i <= 0) ? 8 : 0);
        this.mBinding.imageViewRightSwipe.setRotation(section.isExpanded() ? 0.0f : 90.0f);
        this.mBinding.notificationSectionClearAll.setVisibility((!section.isClearAll() || i <= 0) ? 8 : 0);
        TextView textView = this.mBinding.textViewNoNotification;
        if (!section.isExpanded() || i != 0) {
            i2 = 8;
        }
        textView.setVisibility(i2);
        TextView textView2 = this.mBinding.notificationSectionTitle;
        Resources resources = this.context.getResources();
        textView2.setTextColor(i > 0 ? resources.getColor(R.color.colorRed) : resources.getColor(R.color.textview_color_vd_light));
        this.mBinding.imageViewRightSwipe.setColorFilter(i > 0 ? this.context.getResources().getColor(R.color.colorRed) : this.context.getResources().getColor(R.color.textview_color_vd_light));
    }

    /* renamed from: com.jch.racWiFi.fcm.view_holder.SectionViewHolder$1 */
    static /* synthetic */ class C18361 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.fcm.util.Type[] r0 = com.jch.racWiFi.fcm.util.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$Type = r0
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.INVITE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.REMINDERS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.REMINDER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.ERRORS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.SMART_FENCE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.AC_ACTIVITIES     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.jch.racWiFi.fcm.util.Type r1 = com.jch.racWiFi.fcm.util.Type.ALERTS     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.view_holder.SectionViewHolder.C18361.<clinit>():void");
        }
    }
}
