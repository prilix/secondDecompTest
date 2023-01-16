package com.jch.racWiFi.settings.presenter;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.settings.model.SettingsDataModels;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch.racWiFi.settings.network.SettingsNetworkDispatcher;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TemperatureFragmentPresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public ITemperatureFragmentPresenter iTemperatureFragmentPresenter;
    /* access modifiers changed from: private */
    public SettingsDataModels.SettingsData mTempSettingsData = new SettingsDataModels.SettingsData();

    public interface ITemperatureFragmentPresenter extends INetworkConnectivity {
        void onTemperaturePreferenceUpdateFailure(GenericResponse genericResponse);

        void onTemperaturePreferenceUpdateSuccess(SettingsDataModels.SettingsData settingsData);
    }

    public TemperatureFragmentPresenter(ITemperatureFragmentPresenter iTemperatureFragmentPresenter2) {
        this.iTemperatureFragmentPresenter = iTemperatureFragmentPresenter2;
    }

    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    public void removeCallbacks() {
        this.iTemperatureFragmentPresenter = null;
    }

    public void saveTemperaturePreference(CoreActivity coreActivity, TemperatureUnit temperatureUnit) {
        this.mTempSettingsData.copy(UserInfo.getCurrentUserInfo(coreActivity).settingsData);
        this.mTempSettingsData.temperatureUnit = temperatureUnit.equals(TemperatureUnit.CELSIUS) ? TemperatureUnit.SERVER_DATA_CELSIUS : TemperatureUnit.SERVER_DATA_FAHRENHEIT;
        SettingsNetworkDispatcher settingsNetworkDispatcher = new SettingsNetworkDispatcher();
        settingsNetworkDispatcher.updateSettings(this.mTempSettingsData);
        settingsNetworkDispatcher.mGenericResponseSingleLiveEvent.observeSingleEvent(new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                if (genericResponse.getThrowable() != null) {
                    TemperatureFragmentPresenter.this.iTemperatureFragmentPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                } else if (genericResponse.isApiSuccessful()) {
                    String str = TemperatureFragmentPresenter.this.mTempSettingsData.temperatureUnit;
                    if (str != null) {
                        TemperatureUnit.CURRENT = TemperatureUnit.getEnumFromServerConstant(str);
                    }
                    TemperatureFragmentPresenter.this.iTemperatureFragmentPresenter.onTemperaturePreferenceUpdateSuccess(TemperatureFragmentPresenter.this.mTempSettingsData);
                } else {
                    TemperatureFragmentPresenter.this.iTemperatureFragmentPresenter.onTemperaturePreferenceUpdateFailure(genericResponse);
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTemperaturePreferenceUpdateSuccess() {
        String str = this.mTempSettingsData.temperatureUnit;
        if (str != null) {
            TemperatureUnit.CURRENT = TemperatureUnit.getEnumFromServerConstant(str);
        }
        this.iTemperatureFragmentPresenter.onTemperaturePreferenceUpdateSuccess(this.mTempSettingsData);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTemperaturePreferenceUpdateFailure(GenericResponse genericResponse) {
        this.iTemperatureFragmentPresenter.onTemperaturePreferenceUpdateFailure(genericResponse);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkCallFailure(Throwable th) {
        this.iTemperatureFragmentPresenter.onNetworkCallFailure(th);
    }
}
