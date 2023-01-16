package com.jch.racWiFi.fcm.repository;

import androidx.lifecycle.LifecycleOwner;
import com.jch.racWiFi.fcm.adapter.AlertAdapter;
import com.jch.racWiFi.fcm.adapter.ErrorAdapter;
import com.jch.racWiFi.fcm.adapter.ReminderAdapter;
import com.jch.racWiFi.fcm.adapter.SmartFenceAdapter;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.userManagement.adapter.InviteeListAdapter;

public class AdapterRepository {
    private AlertAdapter mAlertAdapter;
    private ErrorAdapter mErrorAdapter;
    private HomePageActivity.IDUNotificationRecyclerViewAdapter mIduNotificationRecyclerViewAdapter;
    private InviteeListAdapter mInviteeListAdapter;
    private ReminderAdapter mReminderAdapter;
    private SmartFenceAdapter mSmartFenceAdapter;

    public void initAdapters() {
        this.mErrorAdapter = new ErrorAdapter();
        this.mReminderAdapter = new ReminderAdapter();
        this.mSmartFenceAdapter = new SmartFenceAdapter();
        this.mAlertAdapter = new AlertAdapter();
    }

    public void initInviteeListAdapter(LifecycleOwner lifecycleOwner, InviteeListAdapter.InvitationStatusCallBack invitationStatusCallBack) {
        this.mInviteeListAdapter = new InviteeListAdapter(lifecycleOwner, invitationStatusCallBack);
    }

    public SmartFenceAdapter getSmartFenceAdapter() {
        return this.mSmartFenceAdapter;
    }

    public InviteeListAdapter getInviteeListAdapter() {
        return this.mInviteeListAdapter;
    }

    public ErrorAdapter getErrorAdapter() {
        return this.mErrorAdapter;
    }

    public AlertAdapter getAlertAdapter() {
        return this.mAlertAdapter;
    }

    public ReminderAdapter getReminderAdapter() {
        return this.mReminderAdapter;
    }

    public void setIduNotificationRecyclerViewAdapter(HomePageActivity.IDUNotificationRecyclerViewAdapter iDUNotificationRecyclerViewAdapter) {
        this.mIduNotificationRecyclerViewAdapter = iDUNotificationRecyclerViewAdapter;
    }

    public HomePageActivity.IDUNotificationRecyclerViewAdapter getIduNotificationRecyclerViewAdapter() {
        return this.mIduNotificationRecyclerViewAdapter;
    }
}
