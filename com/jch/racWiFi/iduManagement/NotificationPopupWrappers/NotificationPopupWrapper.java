package com.jch.racWiFi.iduManagement.NotificationPopupWrappers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.jch.racWiFi.FragmentToActivityCallback;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customDialogs.NotificationBannerPopUp;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.IDUNotificationType;
import com.jch.racWiFi.iduManagement.model.IDUNotificationTypeUIConfigration;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import java.util.Iterator;
import java.util.List;

public class NotificationPopupWrapper {
    private List<HomePageActivity.IduNotificationItem> iduNotificationItemList;
    /* access modifiers changed from: private */
    public FragmentToActivityCallback mFragmentToActivityCallback;
    private NotificationBannerPopUp mIduErrorPopUp;
    private NotificationBannerPopUp mIduFrostWashPopUp;
    private NotificationBannerPopUp mSpecialModePopUp;
    private NotificationBannerPopUp mTimerPopUp;
    private NotificationBannerPopUp mWeeklyTimerPopUp;
    private OnNotificationShowCallBack onNotificationShowCallBack;

    public interface OnNotificationShowCallBack {
        void onNotificationDismissCallBack(IDUNotificationType iDUNotificationType);

        void onNotificationShowCallBack(IDUNotificationType iDUNotificationType);
    }

    public void setOnNotificationShowCallBack(OnNotificationShowCallBack onNotificationShowCallBack2) {
        this.onNotificationShowCallBack = onNotificationShowCallBack2;
    }

    public void dismissAll() {
        dismissSpecialModePopUp();
        dismissIduFrostWashPopup();
        dismissIduErrorPopUp();
        dismissWeeklyTimerPopup();
        dismissTimerPopup();
    }

    public void setIduNotificationList(FragmentToActivityCallback fragmentToActivityCallback, List<HomePageActivity.IduNotificationItem> list) {
        this.mFragmentToActivityCallback = fragmentToActivityCallback;
        this.iduNotificationItemList = list;
    }

    private void triggerOnNotificationDismissCallBack() {
        OnNotificationShowCallBack onNotificationShowCallBack2 = this.onNotificationShowCallBack;
        if (onNotificationShowCallBack2 != null) {
            onNotificationShowCallBack2.onNotificationDismissCallBack((IDUNotificationType) null);
        }
    }

    public void dismissWeeklyTimerPopup() {
        NotificationBannerPopUp notificationBannerPopUp = this.mWeeklyTimerPopUp;
        if (notificationBannerPopUp != null && notificationBannerPopUp.isShowing()) {
            this.mWeeklyTimerPopUp.dismiss();
            this.mWeeklyTimerPopUp = null;
        }
        triggerOnNotificationDismissCallBack();
    }

    public void dismissTimerPopup() {
        NotificationBannerPopUp notificationBannerPopUp = this.mTimerPopUp;
        if (notificationBannerPopUp != null && notificationBannerPopUp.isShowing()) {
            this.mTimerPopUp.dismiss();
            this.mTimerPopUp = null;
        }
        triggerOnNotificationDismissCallBack();
    }

    public void dismissIduErrorPopUp() {
        NotificationBannerPopUp notificationBannerPopUp = this.mIduErrorPopUp;
        if (notificationBannerPopUp != null && notificationBannerPopUp.isShowing()) {
            this.mIduErrorPopUp.dismiss();
            this.mIduErrorPopUp = null;
        }
        triggerOnNotificationDismissCallBack();
    }

    public void dismissSpecialModePopUp() {
        NotificationBannerPopUp notificationBannerPopUp = this.mSpecialModePopUp;
        if (notificationBannerPopUp != null && notificationBannerPopUp.isShowing()) {
            this.mSpecialModePopUp.dismiss();
            this.mSpecialModePopUp = null;
        }
        triggerOnNotificationDismissCallBack();
    }

    public void dismissIduFrostWashPopup() {
        NotificationBannerPopUp notificationBannerPopUp = this.mIduFrostWashPopUp;
        if (notificationBannerPopUp != null && notificationBannerPopUp.isShowing()) {
            this.mIduFrostWashPopUp.dismiss();
            this.mIduFrostWashPopUp = null;
        }
        triggerOnNotificationDismissCallBack();
    }

    /* access modifiers changed from: private */
    public void removeTimerOrWeeklyTimerNotificationFromList() {
        Iterator<HomePageActivity.IduNotificationItem> it = this.iduNotificationItemList.iterator();
        while (it.hasNext()) {
            HomePageActivity.IduNotificationItem next = it.next();
            if (next.getIduNotificationType().equals(IDUNotificationType.WEEKLY_TIMER_CONFLICT) || next.getIduNotificationType().equals(IDUNotificationType.TIMER_CONFLICT)) {
                it.remove();
            }
        }
    }

    public void showWeeklyTimerPopup(Context context, String str) {
        dismissTimerPopup();
        if (this.mWeeklyTimerPopUp == null) {
            NotificationBannerPopUp notificationBannerPopUp = new NotificationBannerPopUp(context);
            this.mWeeklyTimerPopUp = notificationBannerPopUp;
            notificationBannerPopUp.setNotificationType(IDUNotificationType.WEEKLY_TIMER_CONFLICT);
            IDUNotificationTypeUIConfigration uiConfigration = IDUNotificationType.WEEKLY_TIMER_CONFLICT.getUiConfigration();
            this.mWeeklyTimerPopUp.setTitleString(context.getString(uiConfigration.getNotificationTitle()));
            this.mWeeklyTimerPopUp.setMessageString(context.getString(uiConfigration.getNotificationMessage(), new Object[]{str}));
            this.mWeeklyTimerPopUp.setImage(uiConfigration.getNotificationIcon());
            this.mWeeklyTimerPopUp.setElevation(ViewUtils.convertDpToPixel(19.0f, context));
            this.onNotificationShowCallBack.onNotificationShowCallBack(IDUNotificationType.WEEKLY_TIMER_CONFLICT);
            this.mWeeklyTimerPopUp.getClearButton().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    NotificationPopupWrapper.this.removeTimerOrWeeklyTimerNotificationFromList();
                    NotificationPopupWrapper.this.mFragmentToActivityCallback.getIduNotificationAdapter().notifyDataSetChanged();
                    NotificationPopupWrapper.this.dismissWeeklyTimerPopup();
                }
            });
            this.mWeeklyTimerPopUp.showPopup((Activity) context);
        }
    }

    public void showTimerPopup(Context context, String str) {
        dismissWeeklyTimerPopup();
        if (this.mTimerPopUp == null) {
            NotificationBannerPopUp notificationBannerPopUp = new NotificationBannerPopUp(context);
            this.mTimerPopUp = notificationBannerPopUp;
            notificationBannerPopUp.setNotificationType(IDUNotificationType.TIMER_CONFLICT);
            IDUNotificationTypeUIConfigration uiConfigration = IDUNotificationType.TIMER_CONFLICT.getUiConfigration();
            this.mTimerPopUp.setTitleString(context.getString(uiConfigration.getNotificationTitle()));
            this.mTimerPopUp.setMessageString(context.getString(uiConfigration.getNotificationMessage(), new Object[]{str}));
            this.mTimerPopUp.setImage(uiConfigration.getNotificationIcon());
            this.mTimerPopUp.setElevation(ViewUtils.convertDpToPixel(19.0f, context));
            this.onNotificationShowCallBack.onNotificationShowCallBack(IDUNotificationType.TIMER_CONFLICT);
            this.mTimerPopUp.getClearButton().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    NotificationPopupWrapper.this.removeTimerOrWeeklyTimerNotificationFromList();
                    NotificationPopupWrapper.this.mFragmentToActivityCallback.getIduNotificationAdapter().notifyDataSetChanged();
                    NotificationPopupWrapper.this.dismissTimerPopup();
                }
            });
            this.mTimerPopUp.showPopup((Activity) context);
        }
    }

    public void showIduFrostWashPopup(Context context, String str) {
        if (this.mIduFrostWashPopUp == null) {
            NotificationBannerPopUp notificationBannerPopUp = new NotificationBannerPopUp(context);
            this.mIduFrostWashPopUp = notificationBannerPopUp;
            notificationBannerPopUp.hideCloseButton();
            this.mIduFrostWashPopUp.setNotificationType(IDUNotificationType.IDU_FROST_WASH);
            IDUNotificationTypeUIConfigration uiConfigration = IDUNotificationType.IDU_FROST_WASH.getUiConfigration();
            this.mIduFrostWashPopUp.setTitleString(context.getString(uiConfigration.getNotificationTitle()));
            this.mIduFrostWashPopUp.setMessageString(context.getString(uiConfigration.getNotificationMessage(), new Object[]{str}));
            this.mIduFrostWashPopUp.setImage(uiConfigration.getNotificationIcon());
            this.mIduFrostWashPopUp.setElevation(ViewUtils.convertDpToPixel(19.0f, context));
            OnNotificationShowCallBack onNotificationShowCallBack2 = this.onNotificationShowCallBack;
            if (onNotificationShowCallBack2 != null) {
                onNotificationShowCallBack2.onNotificationShowCallBack(IDUNotificationType.IDU_FROST_WASH);
            }
            this.mIduFrostWashPopUp.showPopup((Activity) context);
        }
    }

    public void showSpecialModePopUp(Context context, String str) {
        if (this.mSpecialModePopUp == null) {
            NotificationBannerPopUp notificationBannerPopUp = new NotificationBannerPopUp(context);
            this.mSpecialModePopUp = notificationBannerPopUp;
            notificationBannerPopUp.hideCloseButton();
            this.mSpecialModePopUp.setNotificationType(IDUNotificationType.SPECIAL_OPERATION);
            IDUNotificationTypeUIConfigration uiConfigration = IDUNotificationType.SPECIAL_OPERATION.getUiConfigration();
            this.mSpecialModePopUp.setTitleString(context.getString(uiConfigration.getNotificationTitle()));
            this.mSpecialModePopUp.setMessageString(context.getString(uiConfigration.getNotificationMessage(), new Object[]{""}));
            this.mSpecialModePopUp.setImage(uiConfigration.getNotificationIcon());
            this.mSpecialModePopUp.setElevation(ViewUtils.convertDpToPixel(19.0f, context));
            OnNotificationShowCallBack onNotificationShowCallBack2 = this.onNotificationShowCallBack;
            if (onNotificationShowCallBack2 != null) {
                onNotificationShowCallBack2.onNotificationShowCallBack(IDUNotificationType.SPECIAL_OPERATION);
            }
            this.mSpecialModePopUp.showPopup((Activity) context);
        }
    }

    public void showIduErrorPopUp(Context context, String str) {
        if (this.mIduErrorPopUp == null) {
            NotificationBannerPopUp notificationBannerPopUp = new NotificationBannerPopUp(context);
            this.mIduErrorPopUp = notificationBannerPopUp;
            notificationBannerPopUp.hideCloseButton();
            this.mIduErrorPopUp.setNotificationType(IDUNotificationType.ERROR_DETAILS);
            IDUNotificationTypeUIConfigration uiConfigration = IDUNotificationType.ERROR_DETAILS.getUiConfigration();
            this.mIduErrorPopUp.setTitleString(context.getString(uiConfigration.getNotificationTitle()));
            this.mIduErrorPopUp.setMessageString(context.getString(uiConfigration.getNotificationMessage(), new Object[]{""}));
            this.mIduErrorPopUp.setImage(uiConfigration.getNotificationIcon());
            this.mIduErrorPopUp.setElevation(ViewUtils.convertDpToPixel(19.0f, context));
            OnNotificationShowCallBack onNotificationShowCallBack2 = this.onNotificationShowCallBack;
            if (onNotificationShowCallBack2 != null) {
                onNotificationShowCallBack2.onNotificationShowCallBack(IDUNotificationType.ERROR_DETAILS);
            }
            this.mIduErrorPopUp.showPopup((Activity) context);
        }
    }

    public void showPopupsBasedOnIduState(Context context, DetailedIduModel detailedIduModel) {
        if (detailedIduModel.isInSpecialMode()) {
            showSpecialModePopUp(context, detailedIduModel.name);
        } else {
            dismissSpecialModePopUp();
        }
        if (detailedIduModel.isFrostWashRunning()) {
            showIduFrostWashPopup(context, detailedIduModel.name);
        } else {
            dismissIduFrostWashPopup();
        }
        if (detailedIduModel.isInErrorState()) {
            showIduErrorPopUp(context, detailedIduModel.name);
            dismissSpecialModePopUp();
            dismissIduFrostWashPopup();
            dismissWeeklyTimerPopup();
            dismissTimerPopup();
            return;
        }
        dismissIduErrorPopUp();
        for (HomePageActivity.IduNotificationItem next : this.iduNotificationItemList) {
            if (next.racId == detailedIduModel.f454id.intValue() && next.iduNotificationType.equals(IDUNotificationType.TIMER_CONFLICT)) {
                showTimerPopup(context, "");
                return;
            } else if (next.racId == detailedIduModel.f454id.intValue() && next.iduNotificationType.equals(IDUNotificationType.WEEKLY_TIMER_CONFLICT)) {
                showWeeklyTimerPopup(context, "");
                return;
            }
        }
    }

    public boolean isAnyBannerShowing() {
        return isWeeklyTimerShowing() || isTimerShowing() || isErrorStateShowing() || isSpecialOperationShowing() || isIduFrostWashShowing();
    }

    private boolean isWeeklyTimerShowing() {
        NotificationBannerPopUp notificationBannerPopUp = this.mWeeklyTimerPopUp;
        return notificationBannerPopUp != null && notificationBannerPopUp.isShowing();
    }

    private boolean isTimerShowing() {
        NotificationBannerPopUp notificationBannerPopUp = this.mTimerPopUp;
        return notificationBannerPopUp != null && notificationBannerPopUp.isShowing();
    }

    private boolean isIduFrostWashShowing() {
        NotificationBannerPopUp notificationBannerPopUp = this.mIduFrostWashPopUp;
        return notificationBannerPopUp != null && notificationBannerPopUp.isShowing();
    }

    private boolean isSpecialOperationShowing() {
        NotificationBannerPopUp notificationBannerPopUp = this.mSpecialModePopUp;
        return notificationBannerPopUp != null && notificationBannerPopUp.isShowing();
    }

    private boolean isErrorStateShowing() {
        NotificationBannerPopUp notificationBannerPopUp = this.mIduErrorPopUp;
        return notificationBannerPopUp != null && notificationBannerPopUp.isShowing();
    }
}
