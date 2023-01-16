package com.jch.racWiFi.settings.presenter;

import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UserPreferencesFragmentPresenter extends AbstractPresenter {
    private IUserPreferencesFragmentPresenter iUserPreferencesFragmentPresenter;

    public interface IUserPreferencesFragmentPresenter extends INetworkConnectivity {
        void setUserPreferences();
    }

    public void requestOutOfHomeRemainderData() {
    }

    public void send(int i) {
    }

    public UserPreferencesFragmentPresenter(IUserPreferencesFragmentPresenter iUserPreferencesFragmentPresenter2) {
        this.iUserPreferencesFragmentPresenter = iUserPreferencesFragmentPresenter2;
    }

    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    public void removeCallbacks() {
        this.iUserPreferencesFragmentPresenter = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkCallFailure(Throwable th) {
        this.iUserPreferencesFragmentPresenter.onNetworkCallFailure(th);
    }
}
