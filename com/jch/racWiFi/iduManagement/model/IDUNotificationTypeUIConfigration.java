package com.jch.racWiFi.iduManagement.model;

import com.jch_hitachi.aircloudglobal.R;

public class IDUNotificationTypeUIConfigration {
    public static final IDUNotificationTypeUIConfigration ERROR;
    public static final IDUNotificationTypeUIConfigration IDU_FROST_WASH;
    public static final IDUNotificationTypeUIConfigration SPECIAL_OPERATION;
    public static final IDUNotificationTypeUIConfigration TIMER_UPDATE;
    public static final IDUNotificationTypeUIConfigration WEEKLY_TIMER_UPDATE;
    private IDUNotificationType iduNotificationType;
    private int notificationIcon;
    private int notificationMessage;
    private int notificationTitle;

    public IDUNotificationType getIduNotificationType() {
        return this.iduNotificationType;
    }

    public void setIduNotificationType(IDUNotificationType iDUNotificationType) {
        this.iduNotificationType = iDUNotificationType;
    }

    public int getNotificationIcon() {
        return this.notificationIcon;
    }

    public int getNotificationTitle() {
        return this.notificationTitle;
    }

    public int getNotificationMessage() {
        return this.notificationMessage;
    }

    public void setNotificationIcon(int i) {
        this.notificationIcon = i;
    }

    public void setNotificationTitle(int i) {
        this.notificationTitle = i;
    }

    public void setNotificationMessage(int i) {
        this.notificationMessage = i;
    }

    static {
        IDUNotificationTypeUIConfigration iDUNotificationTypeUIConfigration = new IDUNotificationTypeUIConfigration();
        ERROR = iDUNotificationTypeUIConfigration;
        iDUNotificationTypeUIConfigration.setNotificationIcon(R.drawable.ic_red_critical_error_notification);
        iDUNotificationTypeUIConfigration.setNotificationTitle(R.string.notification_lbl_criticalError);
        iDUNotificationTypeUIConfigration.setNotificationMessage(R.string.notification_lbl_criticalErrorNotify);
        iDUNotificationTypeUIConfigration.setIduNotificationType(IDUNotificationType.ERROR_DETAILS);
        IDUNotificationTypeUIConfigration iDUNotificationTypeUIConfigration2 = new IDUNotificationTypeUIConfigration();
        TIMER_UPDATE = iDUNotificationTypeUIConfigration2;
        iDUNotificationTypeUIConfigration2.setNotificationIcon(R.drawable.ic_red_critical_error_notification);
        iDUNotificationTypeUIConfigration2.setNotificationTitle(R.string.notification_lbl_timerUpdate);
        iDUNotificationTypeUIConfigration2.setNotificationMessage(R.string.notification_lbl_timerUpdateNotify);
        iDUNotificationTypeUIConfigration2.setIduNotificationType(IDUNotificationType.TIMER_CONFLICT);
        IDUNotificationTypeUIConfigration iDUNotificationTypeUIConfigration3 = new IDUNotificationTypeUIConfigration();
        WEEKLY_TIMER_UPDATE = iDUNotificationTypeUIConfigration3;
        iDUNotificationTypeUIConfigration3.setNotificationIcon(R.drawable.ic_red_critical_error_notification);
        iDUNotificationTypeUIConfigration3.setNotificationTitle(R.string.notification_lbl_weeklyTimerUpdate);
        iDUNotificationTypeUIConfigration3.setNotificationMessage(R.string.notification_lbl_weeklyTimerUpdateNotify);
        iDUNotificationTypeUIConfigration3.setIduNotificationType(IDUNotificationType.WEEKLY_TIMER_CONFLICT);
        IDUNotificationTypeUIConfigration iDUNotificationTypeUIConfigration4 = new IDUNotificationTypeUIConfigration();
        SPECIAL_OPERATION = iDUNotificationTypeUIConfigration4;
        iDUNotificationTypeUIConfigration4.setNotificationIcon(R.drawable.ic_grey_special_state_2_notification);
        iDUNotificationTypeUIConfigration4.setNotificationTitle(R.string.notification_lbl_specialOp);
        iDUNotificationTypeUIConfigration4.setNotificationMessage(R.string.notification_lbl_specialOpNotify);
        iDUNotificationTypeUIConfigration4.setIduNotificationType(IDUNotificationType.SPECIAL_OPERATION);
        IDUNotificationTypeUIConfigration iDUNotificationTypeUIConfigration5 = new IDUNotificationTypeUIConfigration();
        IDU_FROST_WASH = iDUNotificationTypeUIConfigration5;
        iDUNotificationTypeUIConfigration5.setNotificationIcon(R.drawable.ic_idu_frost_wash_svg);
        iDUNotificationTypeUIConfigration5.setNotificationTitle(R.string.notification_lbl_frostWashIndoor);
        iDUNotificationTypeUIConfigration5.setNotificationMessage(R.string.notification_lbl_frostWashIndoorDesc);
        iDUNotificationTypeUIConfigration5.setIduNotificationType(IDUNotificationType.IDU_FROST_WASH);
    }
}
