package com.jch.racWiFi.fcm.util;

import android.app.Application;
import android.graphics.PorterDuff;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.BannerPlannedMaintenanceBinding;
import com.jch.racWiFi.databinding.BannerRacOfflineBinding;
import com.jch.racWiFi.databinding.LayoutCleaningAndTrialBinding;
import com.jch.racWiFi.databinding.LayoutErrorDisplayBinding;
import com.jch.racWiFi.fcm.model.Banner;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.model.Maintenance;
import com.jch.racWiFi.fcm.model.Reminder;
import com.jch.racWiFi.fcm.standard.BannerListener;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch_hitachi.aircloudglobal.R;

public class Binder {
    private final Application mApplication;
    private BannerPlannedMaintenanceBinding mBannerPlannedMaintenanceBinding;
    private BannerRacOfflineBinding mBannerRacOfflineBinding;
    /* access modifiers changed from: private */
    public LayoutCleaningAndTrialBinding mLayoutCleaningAndTrialBinding;
    private LayoutErrorDisplayBinding mLayoutErrorDisplayBinding;

    static /* synthetic */ void lambda$inflate$2(View view) {
    }

    public Binder(Application application) {
        this.mApplication = application;
    }

    public Banner getBanner(Error error, BannerListener<Error, LayoutErrorDisplayBinding> bannerListener) {
        Banner banner = new Banner();
        banner.setViewDataBinding(inflate(error, bannerListener));
        banner.setFreezeUi(true);
        banner.setType(Type.ERRORS);
        return banner;
    }

    public Banner getBanner(DetailedIduModel detailedIduModel, BannerListener<DetailedIduModel, BannerRacOfflineBinding> bannerListener) {
        Banner banner = new Banner();
        banner.setViewDataBinding(inflate(detailedIduModel, bannerListener));
        banner.setFreezeUi(true);
        banner.setType(Type.ALERTS);
        return banner;
    }

    public Banner getBanner(Maintenance maintenance, BannerListener<Maintenance, BannerPlannedMaintenanceBinding> bannerListener) {
        Banner banner = new Banner();
        banner.setViewDataBinding(inflate(maintenance, bannerListener));
        banner.setFreezeUi(true);
        banner.setType(Type.MAINTENANCE);
        return banner;
    }

    public Banner getBanner(Reminder reminder, BannerListener<Reminder, LayoutCleaningAndTrialBinding> bannerListener) {
        Banner banner = new Banner();
        banner.setViewDataBinding(inflate(reminder, bannerListener));
        banner.setFreezeUi(true);
        banner.setType(Type.REMINDER);
        return banner;
    }

    public Banner getBanner(DetailedIduModel.IduErrorStatus iduErrorStatus, BannerListener<DetailedIduModel.IduErrorStatus, LayoutErrorDisplayBinding> bannerListener, boolean z) {
        Banner banner = new Banner();
        banner.setViewDataBinding(inflate(iduErrorStatus, bannerListener, z));
        banner.setFreezeUi(false);
        banner.setType(Type.AC_ACTIVITIES);
        return banner;
    }

    private ViewDataBinding inflate(DetailedIduModel.IduErrorStatus iduErrorStatus, BannerListener<DetailedIduModel.IduErrorStatus, LayoutErrorDisplayBinding> bannerListener, boolean z) {
        String str = null;
        if (this.mLayoutErrorDisplayBinding == null) {
            this.mLayoutErrorDisplayBinding = (LayoutErrorDisplayBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mApplication), R.layout.layout_error_display, (ViewGroup) null, false);
        }
        if (iduErrorStatus.subCategory.trim().equals("INDOOR")) {
            str = this.mApplication.getString(R.string.notification_lbl_indoor);
        }
        if (iduErrorStatus.subCategory.trim().equals("OUTDOOR")) {
            str = this.mApplication.getString(R.string.notification_lbl_outdoor);
        }
        commonTask(this.mApplication.getString(R.string.notification_lbl_errorTitleServiceReq, new Object[]{iduErrorStatus.errorCode, str}), this.mApplication.getString(R.string.notification_lbl_errorTitleServiceReqDesc, new Object[]{str}), z);
        this.mLayoutErrorDisplayBinding.errorCrossImage.setOnClickListener(new Binder$$ExternalSyntheticLambda2(this, bannerListener, iduErrorStatus));
        this.mLayoutErrorDisplayBinding.getRoot().setOnClickListener(new Binder$$ExternalSyntheticLambda3(this, bannerListener, iduErrorStatus));
        return this.mLayoutErrorDisplayBinding;
    }

    /* renamed from: lambda$inflate$0$com-jch-racWiFi-fcm-util-Binder  reason: not valid java name */
    public /* synthetic */ void m947lambda$inflate$0$comjchracWiFifcmutilBinder(BannerListener bannerListener, DetailedIduModel.IduErrorStatus iduErrorStatus, View view) {
        bannerListener.onClick(view, iduErrorStatus, this.mLayoutErrorDisplayBinding);
    }

    /* renamed from: lambda$inflate$1$com-jch-racWiFi-fcm-util-Binder  reason: not valid java name */
    public /* synthetic */ void m948lambda$inflate$1$comjchracWiFifcmutilBinder(BannerListener bannerListener, DetailedIduModel.IduErrorStatus iduErrorStatus, View view) {
        bannerListener.onItemClick(view, iduErrorStatus, this.mLayoutErrorDisplayBinding);
    }

    private ViewDataBinding inflate(Maintenance maintenance, BannerListener<Maintenance, BannerPlannedMaintenanceBinding> bannerListener) {
        if (this.mBannerPlannedMaintenanceBinding == null) {
            this.mBannerPlannedMaintenanceBinding = (BannerPlannedMaintenanceBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mApplication), R.layout.banner_planned_maintenance, (ViewGroup) null, false);
        }
        this.mBannerPlannedMaintenanceBinding.progressBarCloseButton.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this.mApplication, R.color.stroke_color), PorterDuff.Mode.SRC_IN);
        this.mBannerPlannedMaintenanceBinding.textViewServicesNotAvailable.setText(maintenance.getTitle(this.mApplication));
        this.mBannerPlannedMaintenanceBinding.textViewServicesNotAvailabledesc.setText(maintenance.getDescription(this.mApplication));
        this.mBannerPlannedMaintenanceBinding.imageButtonClear.setVisibility(4);
        this.mBannerPlannedMaintenanceBinding.imageButtonClear.setOnClickListener(Binder$$ExternalSyntheticLambda6.INSTANCE);
        return this.mBannerPlannedMaintenanceBinding;
    }

    private ViewDataBinding inflate(DetailedIduModel detailedIduModel, BannerListener<DetailedIduModel, BannerRacOfflineBinding> bannerListener) {
        if (this.mBannerRacOfflineBinding == null) {
            this.mBannerRacOfflineBinding = (BannerRacOfflineBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mApplication), R.layout.banner_rac_offline, (ViewGroup) null, false);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.mBannerRacOfflineBinding.textViewGenericNotificationTitle.setText(Html.fromHtml(this.mApplication.getString(R.string.notification_lbl_racOfflineTitle), 63));
            TextView textView = this.mBannerRacOfflineBinding.textViewGenericNotificationDesc;
            Application application = this.mApplication;
            textView.setText(Html.fromHtml(application.getString(R.string.notification_lbl_racOfflineTryAgain, new Object[]{UserInfo.getCurrentUserInfo((CoreActivity) application.getApplicationContext()), detailedIduModel.name}), 63));
            this.mBannerRacOfflineBinding.textViewRacOfflineDescTwo.setText(Html.fromHtml(this.mApplication.getString(R.string.notification_lbl_encourageUserToTurnOnLocationservices), 63));
        } else {
            this.mBannerRacOfflineBinding.textViewGenericNotificationTitle.setText(Html.fromHtml(this.mApplication.getString(R.string.notification_lbl_racOfflineTitle)));
            TextView textView2 = this.mBannerRacOfflineBinding.textViewGenericNotificationDesc;
            Application application2 = this.mApplication;
            textView2.setText(Html.fromHtml(application2.getString(R.string.notification_lbl_racOfflineTryAgain, new Object[]{UserInfo.getCurrentUserInfo((CoreActivity) application2.getApplicationContext()), detailedIduModel.name})));
            this.mBannerRacOfflineBinding.textViewRacOfflineDescTwo.setText(Html.fromHtml(this.mApplication.getString(R.string.notification_lbl_encourageUserToTurnOnLocationservices)));
        }
        this.mBannerRacOfflineBinding.imageButtonClear.setOnClickListener(new Binder$$ExternalSyntheticLambda4(this, bannerListener, detailedIduModel));
        this.mBannerRacOfflineBinding.textViewViewHideDetails.setOnClickListener(new Binder$$ExternalSyntheticLambda5(this, bannerListener, detailedIduModel));
        return this.mBannerRacOfflineBinding;
    }

    /* renamed from: lambda$inflate$3$com-jch-racWiFi-fcm-util-Binder  reason: not valid java name */
    public /* synthetic */ void m949lambda$inflate$3$comjchracWiFifcmutilBinder(BannerListener bannerListener, DetailedIduModel detailedIduModel, View view) {
        bannerListener.onClick(view, detailedIduModel, this.mBannerRacOfflineBinding);
    }

    /* renamed from: lambda$inflate$4$com-jch-racWiFi-fcm-util-Binder  reason: not valid java name */
    public /* synthetic */ void m950lambda$inflate$4$comjchracWiFifcmutilBinder(BannerListener bannerListener, DetailedIduModel detailedIduModel, View view) {
        bannerListener.onItemClick(view, detailedIduModel, this.mBannerRacOfflineBinding);
    }

    private ViewDataBinding inflate(Error error, BannerListener<Error, LayoutErrorDisplayBinding> bannerListener) {
        if (this.mLayoutErrorDisplayBinding == null) {
            this.mLayoutErrorDisplayBinding = (LayoutErrorDisplayBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mApplication), R.layout.layout_error_display, (ViewGroup) null, false);
        }
        String string = this.mApplication.getString(error.getErrorSubCategory().getAttributes()[3]);
        String string2 = this.mApplication.getString(R.string.notification_lbl_errorTitleServiceReq, new Object[]{error.getErrorCode(), string});
        commonTask(string2, this.mApplication.getResources().getString(R.string.notification_lbl_errorTitleServiceReqDesc, new Object[]{string}) + "<br><br>", true);
        this.mLayoutErrorDisplayBinding.errorCrossImage.setOnClickListener(new Binder$$ExternalSyntheticLambda0(this, bannerListener, error));
        this.mLayoutErrorDisplayBinding.getRoot().setOnClickListener(new Binder$$ExternalSyntheticLambda1(this, bannerListener, error));
        return this.mLayoutErrorDisplayBinding;
    }

    /* renamed from: lambda$inflate$5$com-jch-racWiFi-fcm-util-Binder  reason: not valid java name */
    public /* synthetic */ void m951lambda$inflate$5$comjchracWiFifcmutilBinder(BannerListener bannerListener, Error error, View view) {
        bannerListener.onClick(view, error, this.mLayoutErrorDisplayBinding);
    }

    /* renamed from: lambda$inflate$6$com-jch-racWiFi-fcm-util-Binder  reason: not valid java name */
    public /* synthetic */ void m952lambda$inflate$6$comjchracWiFifcmutilBinder(BannerListener bannerListener, Error error, View view) {
        bannerListener.onItemClick(view, error, this.mLayoutErrorDisplayBinding);
    }

    private void commonTask(String str, String str2, boolean z) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.mLayoutErrorDisplayBinding.errorTitle.setText(Html.fromHtml(str, 63));
            this.mLayoutErrorDisplayBinding.errorDescription.setText(Html.fromHtml(str2, 63));
        } else {
            this.mLayoutErrorDisplayBinding.errorTitle.setText(Html.fromHtml(str));
            this.mLayoutErrorDisplayBinding.errorDescription.setText(Html.fromHtml(str2));
        }
        if (z) {
            TextView textView = this.mLayoutErrorDisplayBinding.errorDescription;
            textView.append("\n\n" + this.mApplication.getResources().getString(R.string.notification_idu_errorDesc));
        }
        this.mLayoutErrorDisplayBinding.errorCrossImage.setVisibility(4);
    }

    private ViewDataBinding inflate(final Reminder reminder, final BannerListener<Reminder, LayoutCleaningAndTrialBinding> bannerListener) {
        if (this.mLayoutCleaningAndTrialBinding == null) {
            this.mLayoutCleaningAndTrialBinding = (LayoutCleaningAndTrialBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mApplication), R.layout.layout_cleaning_and_trial, (ViewGroup) null, false);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.mLayoutCleaningAndTrialBinding.textViewCleaningAndTrialHeading.setText(Html.fromHtml(reminder.getTitle(this.mApplication), 63));
            this.mLayoutCleaningAndTrialBinding.textViewCleaningAndTrialDesc.setText(Html.fromHtml(reminder.getDescription(this.mApplication), 63));
        } else {
            this.mLayoutCleaningAndTrialBinding.textViewCleaningAndTrialHeading.setText(Html.fromHtml(reminder.getTitle(this.mApplication)));
            this.mLayoutCleaningAndTrialBinding.textViewCleaningAndTrialDesc.setText(Html.fromHtml(reminder.getDescription(this.mApplication)));
        }
        this.mLayoutCleaningAndTrialBinding.imageButtonClearCleanAndTrial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                bannerListener.onClick(view, reminder, Binder.this.mLayoutCleaningAndTrialBinding);
            }
        });
        return this.mLayoutCleaningAndTrialBinding;
    }

    public void unBind() {
        LayoutErrorDisplayBinding layoutErrorDisplayBinding = this.mLayoutErrorDisplayBinding;
        if (layoutErrorDisplayBinding != null) {
            layoutErrorDisplayBinding.unbind();
        }
        BannerRacOfflineBinding bannerRacOfflineBinding = this.mBannerRacOfflineBinding;
        if (bannerRacOfflineBinding != null) {
            bannerRacOfflineBinding.unbind();
        }
        BannerPlannedMaintenanceBinding bannerPlannedMaintenanceBinding = this.mBannerPlannedMaintenanceBinding;
        if (bannerPlannedMaintenanceBinding != null) {
            bannerPlannedMaintenanceBinding.unbind();
        }
        LayoutCleaningAndTrialBinding layoutCleaningAndTrialBinding = this.mLayoutCleaningAndTrialBinding;
        if (layoutCleaningAndTrialBinding != null) {
            layoutCleaningAndTrialBinding.unbind();
        }
    }
}
