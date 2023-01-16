package com.jch.racWiFi.iduManagement.smartFence.viewModels;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.genericViewModel.GenericViewModel;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch.racWiFi.iduManagement.smartFence.model.SmartFenceGeoFenceSettingsModel;
import java.util.Objects;

public class SmartFenceSettingsViewModel extends GenericViewModel {
    private SingleLiveEvent<SmartFenceGeoFenceSettingsModel> locationControlsGeoFenceSettingsModelMutableLiveData = new SingleLiveEvent<>(new SmartFenceGeoFenceSettingsModel());

    public SingleLiveEvent<SmartFenceGeoFenceSettingsModel> getLocationControlsGeoFenceSettingsModelMutableLiveData() {
        return this.locationControlsGeoFenceSettingsModelMutableLiveData;
    }

    public SmartFenceSettingsViewModel() {
        SmartFenceGeoFenceSettingsModel value = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel = value;
        value.arrivingSettingsAvailable = true;
        SmartFenceGeoFenceSettingsModel value2 = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value2);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel2 = value2;
        value2.leavingSettingsAvailable = false;
        SmartFenceGeoFenceSettingsModel value3 = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value3);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel3 = value3;
        value3.arrivingEnabled = true;
        SmartFenceGeoFenceSettingsModel value4 = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value4);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel4 = value4;
        value4.leavingEnabled = true;
    }

    public void selectedArrivingTab() {
        SmartFenceGeoFenceSettingsModel value = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel = value;
        value.arrivingSettingsAvailable = true;
        SmartFenceGeoFenceSettingsModel value2 = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value2);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel2 = value2;
        value2.leavingSettingsAvailable = false;
        getLocationControlsGeoFenceSettingsModelMutableLiveData().trigger();
    }

    public void selectedLeavingTab() {
        SmartFenceGeoFenceSettingsModel value = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel = value;
        value.leavingSettingsAvailable = true;
        SmartFenceGeoFenceSettingsModel value2 = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value2);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel2 = value2;
        value2.arrivingSettingsAvailable = false;
        getLocationControlsGeoFenceSettingsModelMutableLiveData().trigger();
    }

    public void updateLocationControlsGeoFenceSettingsModel(GeoFencePair geoFencePair) {
        SmartFenceGeoFenceSettingsModel value = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel = value;
        value.arrivingEnabled = true;
        SmartFenceGeoFenceSettingsModel value2 = getLocationControlsGeoFenceSettingsModelMutableLiveData().getValue();
        Objects.requireNonNull(value2);
        SmartFenceGeoFenceSettingsModel smartFenceGeoFenceSettingsModel2 = value2;
        value2.leavingEnabled = true;
        SingleLiveEvent<SmartFenceGeoFenceSettingsModel> locationControlsGeoFenceSettingsModelMutableLiveData2 = getLocationControlsGeoFenceSettingsModelMutableLiveData();
        Objects.requireNonNull(locationControlsGeoFenceSettingsModelMutableLiveData2);
        SingleLiveEvent singleLiveEvent = locationControlsGeoFenceSettingsModelMutableLiveData2;
        locationControlsGeoFenceSettingsModelMutableLiveData2.trigger();
    }
}
