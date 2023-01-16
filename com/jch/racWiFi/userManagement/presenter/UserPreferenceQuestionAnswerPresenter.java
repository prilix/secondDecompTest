package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.settings.model.SettingsDataModels;
import com.jch.racWiFi.settings.network.PreferencesSettingsNetworkDispatcher;

public class UserPreferenceQuestionAnswerPresenter extends AbstractPresenter {
    private MutableLiveData<TimerEnabledModel.TimerEnabledResponse> holidayModeEnabledResponseMutableLiveData = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public IUserPreferenceQuestionAnswerPresenter iUserPreferenceQuestionAnswerPresenter;
    private SettingsDataModels.UserPreferenceSettingsData mTempSettingsData = new SettingsDataModels.UserPreferenceSettingsData();

    public interface IUserPreferenceQuestionAnswerPresenter extends INetworkConnectivity {
        void onPreferenceUpdateFailure(GenericResponse genericResponse);

        void onPreferenceUpdateSuccess();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public UserPreferenceQuestionAnswerPresenter(IUserPreferenceQuestionAnswerPresenter iUserPreferenceQuestionAnswerPresenter2) {
        this.iUserPreferenceQuestionAnswerPresenter = iUserPreferenceQuestionAnswerPresenter2;
    }

    public void removeCallbacks() {
        this.iUserPreferenceQuestionAnswerPresenter = null;
    }

    public void saveUserPreferenceQuestionAnswer(LifecycleOwner lifecycleOwner, boolean[] zArr) {
        this.mTempSettingsData.homeOnWeekdays = zArr[0];
        this.mTempSettingsData.homeOnWeekends = zArr[1];
        this.mTempSettingsData.sensitiveToCold = zArr[2];
        new PreferencesSettingsNetworkDispatcher().updateSettings(this.mTempSettingsData).observeSingleEvent(lifecycleOwner, new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                if (UserPreferenceQuestionAnswerPresenter.this.iUserPreferenceQuestionAnswerPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        UserPreferenceQuestionAnswerPresenter.this.iUserPreferenceQuestionAnswerPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (genericResponse.isApiSuccessful()) {
                        UserPreferenceQuestionAnswerPresenter.this.iUserPreferenceQuestionAnswerPresenter.onPreferenceUpdateSuccess();
                    } else {
                        UserPreferenceQuestionAnswerPresenter.this.iUserPreferenceQuestionAnswerPresenter.onPreferenceUpdateFailure(genericResponse);
                    }
                }
            }
        });
    }
}
