package com.jch.racWiFi.iduManagement.viewModel;

import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.genericViewModel.GenericViewModel;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;

public class HumidityChangeViewModel extends GenericViewModel {
    private SingleLiveEvent<String> decreaseHumidityLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<String> increaseHumidityLiveEvent = new SingleLiveEvent<>();
    private DetailedIduModel mDetailedIduModel;
    private RacModelWiseData mRacModelWiseData;

    public SingleLiveEvent<String> getIncreaseHumidityLiveEvent() {
        return this.increaseHumidityLiveEvent;
    }

    public SingleLiveEvent<String> getDecreaseHumidityLiveEvent() {
        return this.decreaseHumidityLiveEvent;
    }

    public HumidityChangeViewModel(DetailedIduModel detailedIduModel, RacModelWiseData racModelWiseData) {
        this.mDetailedIduModel = detailedIduModel;
        this.mRacModelWiseData = racModelWiseData;
    }

    public void increaseHumidity() {
        if (this.mRacModelWiseData != null) {
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(this.mDetailedIduModel.getOperationModeEnum());
            String humidity = this.mDetailedIduModel.getHumidity();
            if ((humidity == null || !humidity.equalsIgnoreCase("--")) && humidity != null) {
                int parseInt = Integer.parseInt(humidity);
                if (racModeDetailBasedOnMode != null) {
                    long j = (long) parseInt;
                    if (j < racModeDetailBasedOnMode.getMaxHumidity()) {
                        this.mDetailedIduModel.setHumidity(String.valueOf((int) (j + racModeDetailBasedOnMode.getHumiditySettingPitchType())));
                        getIncreaseHumidityLiveEvent().postValue(this.mDetailedIduModel.getHumidity());
                    }
                }
            }
        }
    }

    public void decreaseHumidity() {
        if (this.mRacModelWiseData != null) {
            RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mRacModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(this.mDetailedIduModel.getOperationModeEnum());
            if (this.mDetailedIduModel.getHumidity() != null) {
                String humidity = this.mDetailedIduModel.getHumidity();
                if (humidity == null || !humidity.equalsIgnoreCase("--")) {
                    int parseInt = Integer.parseInt(humidity);
                    if (racModeDetailBasedOnMode != null) {
                        long j = (long) parseInt;
                        if (j > racModeDetailBasedOnMode.getMinHumidity()) {
                            this.mDetailedIduModel.setHumidity(String.valueOf((int) (j - racModeDetailBasedOnMode.getHumiditySettingPitchType())));
                            getDecreaseHumidityLiveEvent().postValue(this.mDetailedIduModel.getHumidity());
                        }
                    }
                }
            }
        }
    }

    public static class HumidityChangeViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private DetailedIduModel detailedIduModel;
        private RacModelWiseData mRacModelWiseData;

        public HumidityChangeViewModelFactory(DetailedIduModel detailedIduModel2, RacModelWiseData racModelWiseData) {
            this.detailedIduModel = detailedIduModel2;
            this.mRacModelWiseData = racModelWiseData;
        }

        public <T extends C0534ViewModel> T create(Class<T> cls) {
            return new HumidityChangeViewModel(this.detailedIduModel, this.mRacModelWiseData);
        }
    }
}
