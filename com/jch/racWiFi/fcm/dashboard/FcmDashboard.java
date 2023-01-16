package com.jch.racWiFi.fcm.dashboard;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.databinding.NotificationsBinding;
import com.jch.racWiFi.fcm.adapter.SectionAdapter;
import com.jch.racWiFi.fcm.adapter.TypeAdapter;
import com.jch.racWiFi.fcm.model.Section;
import com.jch.racWiFi.fcm.repository.AdapterRepository;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.fcm.standard.ObserverListener;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch_hitachi.aircloudglobal.R;

public class FcmDashboard {
    private AdapterRepository mAdapterRepository;
    /* access modifiers changed from: private */
    public NotificationsBinding mBinding;
    /* access modifiers changed from: private */
    public PopupWindow mPopupWindow;
    /* access modifiers changed from: private */
    public SectionAdapter mSectionAdapter;
    /* access modifiers changed from: private */
    public TypeAdapter mTypeAdapter;

    public SectionAdapter getSectionAdapter() {
        return this.mSectionAdapter;
    }

    public void setAdapterRepository(AdapterRepository adapterRepository) {
        this.mAdapterRepository = adapterRepository;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, ObserverListener observerListener) {
        if (viewGroup == null) {
            return null;
        }
        NotificationsBinding notificationsBinding = (NotificationsBinding) DataBindingUtil.inflate(layoutInflater, R.layout.notifications, viewGroup, true);
        this.mBinding = notificationsBinding;
        notificationsBinding.recyclerViewNotifications.setLayoutManager(new LinearLayoutManager(viewGroup.getContext()));
        this.mSectionAdapter = new SectionAdapter(this.mAdapterRepository);
        this.mBinding.recyclerViewNotifications.setAdapter(this.mSectionAdapter);
        View inflate = layoutInflater.inflate(R.layout.popup_notifications, (ViewGroup) null);
        observerListener.onInitialize();
        this.mBinding.getRoot().post(new FcmDashboard$$ExternalSyntheticLambda3(this, viewGroup, inflate));
        return this.mBinding.getRoot();
    }

    /* renamed from: lambda$onCreateView$4$com-jch-racWiFi-fcm-dashboard-FcmDashboard  reason: not valid java name */
    public /* synthetic */ void m945lambda$onCreateView$4$comjchracWiFifcmdashboardFcmDashboard(ViewGroup viewGroup, View view) {
        int width = this.mBinding.getRoot().getWidth() - Math.round(ViewUtils.convertDpToPixel(60.0f, viewGroup.getContext()));
        this.mTypeAdapter = new TypeAdapter(viewGroup.getContext());
        Section section = new Section();
        section.setClearAll(false);
        section.setTitle(R.string.notification_lbl_allNotifications);
        section.setType(Type.ALL_NOTIFICATIONS);
        section.setSelectedForFilter(true);
        this.mTypeAdapter.getSectionList().add(section);
        this.mTypeAdapter.getSectionList().addAll(this.mSectionAdapter.getSectionList());
        this.mTypeAdapter.setListener(new FcmListener<Section, TypeAdapter>() {
            public void onClose(View view, Section section, TypeAdapter typeAdapter) {
            }

            public void onClick(View view, Section section, TypeAdapter typeAdapter) {
                FcmDashboard.this.mPopupWindow.dismiss();
                FcmDashboard.this.mTypeAdapter.setSection(section);
                FcmDashboard.this.mSectionAdapter.filter(section.getType());
                FcmDashboard.this.mBinding.textViewAllNotifications.setText(section.getTitle());
            }
        });
        this.mBinding.imageButtonClearNotificationMain.setOnClickListener(new FcmDashboard$$ExternalSyntheticLambda0(viewGroup));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_users_name);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext()));
        recyclerView.setAdapter(this.mTypeAdapter);
        if (this.mTypeAdapter.getSectionList().size() >= 6) {
            this.mPopupWindow = new PopupWindow(view, width, -2);
        } else {
            this.mPopupWindow = new PopupWindow(view, width, -1);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.mPopupWindow.setElevation(5.0f);
        }
        this.mPopupWindow.setOutsideTouchable(true);
        this.mPopupWindow.setFocusable(true);
        this.mPopupWindow.setOnDismissListener(new FcmDashboard$$ExternalSyntheticLambda2(this));
        this.mBinding.layoutAllNotifications.setOnClickListener(new FcmDashboard$$ExternalSyntheticLambda1(this));
    }

    private /* synthetic */ boolean lambda$onCreateView$1(ViewGroup viewGroup, View view) {
        ((HomePageActivity) viewGroup.getContext()).clearAllNotification(Type.ERRORS.name(), new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                if (FcmDashboard.this.mSectionAdapter != null) {
                    FcmDashboard.this.mSectionAdapter.notifyDataSetChanged();
                }
            }
        });
        return true;
    }

    /* renamed from: lambda$onCreateView$2$com-jch-racWiFi-fcm-dashboard-FcmDashboard  reason: not valid java name */
    public /* synthetic */ void m943lambda$onCreateView$2$comjchracWiFifcmdashboardFcmDashboard() {
        this.mBinding.imageViewArrowDown.setRotation(0.0f);
    }

    /* renamed from: lambda$onCreateView$3$com-jch-racWiFi-fcm-dashboard-FcmDashboard  reason: not valid java name */
    public /* synthetic */ void m944lambda$onCreateView$3$comjchracWiFifcmdashboardFcmDashboard(View view) {
        if (!this.mPopupWindow.isShowing()) {
            int[] iArr = new int[2];
            this.mBinding.layoutAllNotifications.getLocationInWindow(iArr);
            this.mPopupWindow.showAtLocation((View) this.mBinding.getRoot().getParent(), 0, iArr[0], iArr[1] + this.mBinding.layoutAllNotifications.getHeight());
            this.mBinding.imageViewArrowDown.setRotation(180.0f);
            return;
        }
        this.mPopupWindow.dismiss();
    }

    public void onDestroyView() {
        this.mBinding.unbind();
    }

    public void dismissFilterPopup() {
        if (this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        }
    }
}
