package com.jch.racWiFi.iduManagement.smartFence.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.FamilyIdGeoFenceDataMap;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceData;
import java.util.ArrayList;
import java.util.List;

public class GeoFencePair implements Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<GeoFencePair> CREATOR = new Parcelable.Creator<GeoFencePair>() {
        public GeoFencePair createFromParcel(Parcel parcel) {
            return new GeoFencePair(parcel);
        }

        public GeoFencePair[] newArray(int i) {
            return new GeoFencePair[i];
        }
    };
    public static final GeoFencePair DEFAULT;
    public static final String PARCEL_KEY = "GeoFencePair_PARCEL_KEY";
    private final GeoFenceData arrivingGeoFence;
    private List<Long> associatedRac;
    private List<String> associatedRacCloudId;
    private List<Long> associatedUsers;
    public Long familyId;
    public boolean isDefault;
    public boolean isEnabled;
    private LatLng latLng;
    private final GeoFenceData leavingGeoFence;
    public boolean racListChanged;
    public boolean userListChanged;
    public boolean valueChanged;

    public int describeContents() {
        return 0;
    }

    protected GeoFencePair(Parcel parcel) {
        boolean z = false;
        this.racListChanged = false;
        this.associatedUsers = new ArrayList();
        this.associatedRac = new ArrayList();
        this.associatedRacCloudId = new ArrayList();
        this.arrivingGeoFence = (GeoFenceData) parcel.readParcelable(GeoFenceData.class.getClassLoader());
        this.leavingGeoFence = (GeoFenceData) parcel.readParcelable(GeoFenceData.class.getClassLoader());
        LatLng latLng2 = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.latLng = new LatLng(latLng2.latitude, latLng2.longitude);
        this.isDefault = parcel.readByte() != 0;
        this.valueChanged = parcel.readByte() != 0;
        this.userListChanged = parcel.readByte() != 0;
        this.racListChanged = parcel.readByte() != 0;
        this.isEnabled = parcel.readByte() != 0 ? true : z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.arrivingGeoFence, i);
        parcel.writeParcelable(this.leavingGeoFence, i);
        parcel.writeParcelable(this.latLng, i);
        parcel.writeByte(this.isDefault ? (byte) 1 : 0);
        parcel.writeByte(this.valueChanged ? (byte) 1 : 0);
        parcel.writeByte(this.userListChanged ? (byte) 1 : 0);
        parcel.writeByte(this.racListChanged ? (byte) 1 : 0);
        parcel.writeByte(this.isEnabled ? (byte) 1 : 0);
    }

    static {
        GeoFencePair geoFencePair = new GeoFencePair();
        DEFAULT = geoFencePair;
        geoFencePair.setLatLng((LatLng) null);
        geoFencePair.arrivingGeoFence.setDraggable(true);
        geoFencePair.arrivingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.ENTER);
        geoFencePair.arrivingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        geoFencePair.arrivingGeoFence.setGeoFenceDynamics(new GeoFenceData.GeoFenceDynamics(1000.0f, true));
        geoFencePair.arrivingGeoFence.setModeTempSettings(new GeoFenceData.ModeTempSettings());
        geoFencePair.leavingGeoFence.setDraggable(true);
        geoFencePair.leavingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.EXIT);
        geoFencePair.leavingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        geoFencePair.leavingGeoFence.setGeoFenceDynamics(new GeoFenceData.GeoFenceDynamics(500.0f, true));
        geoFencePair.leavingGeoFence.setModeTempSettings(new GeoFenceData.ModeTempSettings());
    }

    public List<String> getAssociatedRacCloudId() {
        return this.associatedRacCloudId;
    }

    public void setAssociatedRacCloudId(List<String> list) {
        this.associatedRacCloudId = list;
    }

    public GeoFenceData getArrivingGeoFence() {
        return this.arrivingGeoFence;
    }

    public GeoFenceData getLeavingGeoFence() {
        return this.leavingGeoFence;
    }

    public List<Long> getAssociatedUsers() {
        return this.associatedUsers;
    }

    public List<Long> getAssociatedRacs() {
        return this.associatedRac;
    }

    public GeoFencePair() {
        this.racListChanged = false;
        this.associatedUsers = new ArrayList();
        this.associatedRac = new ArrayList();
        this.associatedRacCloudId = new ArrayList();
        this.arrivingGeoFence = new GeoFenceData();
        this.leavingGeoFence = new GeoFenceData();
    }

    public GeoFencePair(GeoFenceData geoFenceData, GeoFenceData geoFenceData2) {
        this.racListChanged = false;
        this.associatedUsers = new ArrayList();
        this.associatedRac = new ArrayList();
        this.associatedRacCloudId = new ArrayList();
        this.arrivingGeoFence = geoFenceData;
        this.leavingGeoFence = geoFenceData2;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public void setLatLng(LatLng latLng2) {
        this.latLng = latLng2;
    }

    public boolean isDraggable() {
        return this.arrivingGeoFence.isDraggable();
    }

    public List<Geofence> getGeoFenceListForGoogleGeoFenceApi() {
        ArrayList arrayList = new ArrayList();
        float floatValue = this.arrivingGeoFence.getGeoFenceDynamics().getGeoFenceRadiusInMeters().floatValue();
        float floatValue2 = this.leavingGeoFence.getGeoFenceDynamics().getGeoFenceRadiusInMeters().floatValue();
        if (!this.arrivingGeoFence.getGeoFenceDynamics().isGeoFenceEnabled() || !this.leavingGeoFence.getGeoFenceDynamics().isGeoFenceEnabled()) {
            Log.e("geofence_equals", "onlu on selected   " + floatValue + "  " + floatValue2);
        } else if (floatValue == floatValue2) {
            floatValue2 -= 50.0f;
            floatValue += 50.0f;
            Log.e("geofence_equals", "equal  " + floatValue + "  " + floatValue2);
        } else {
            Log.e("geofence_equals", "not equal   " + floatValue + "  " + floatValue2);
        }
        float f = floatValue;
        float f2 = floatValue2;
        if (this.arrivingGeoFence.getGeoFenceDynamics().isGeoFenceEnabled()) {
            arrayList.add(new Geofence.Builder().setRequestId(this.arrivingGeoFence.getGeoFenceId()).setCircularRegion(getLatLng().latitude, getLatLng().longitude, f).setExpirationDuration(-1).setTransitionTypes(this.arrivingGeoFence.getFenceTransitionType().getTransitionTypeForGoogleAPI()).setLoiteringDelay(10000).build());
        }
        if (this.leavingGeoFence.getGeoFenceDynamics().isGeoFenceEnabled()) {
            arrayList.add(new Geofence.Builder().setRequestId(this.leavingGeoFence.getGeoFenceId()).setCircularRegion(getLatLng().latitude, getLatLng().longitude, f2).setExpirationDuration(-1).setTransitionTypes(this.leavingGeoFence.getFenceTransitionType().getTransitionTypeForGoogleAPI()).setLoiteringDelay(10000).build());
        }
        return arrayList;
    }

    public static FamilyIdGeoFenceDataMap getDummyList() {
        FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap = new FamilyIdGeoFenceDataMap();
        GeoFencePair geoFencePair = new GeoFencePair();
        geoFencePair.setLatLng(new LatLng(13.0185713d, 77.6685828d));
        geoFencePair.arrivingGeoFence.setId(1);
        geoFencePair.arrivingGeoFence.setDraggable(true);
        geoFencePair.arrivingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.ENTER);
        geoFencePair.arrivingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        geoFencePair.arrivingGeoFence.setGeoFenceDynamics(new GeoFenceData.GeoFenceDynamics(1000.0f, true));
        geoFencePair.leavingGeoFence.setId(2);
        geoFencePair.leavingGeoFence.setDraggable(true);
        geoFencePair.leavingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.EXIT);
        geoFencePair.leavingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        geoFencePair.leavingGeoFence.setGeoFenceDynamics(new GeoFenceData.GeoFenceDynamics(900.0f, true));
        familyIdGeoFenceDataMap.put(62L, geoFencePair);
        GeoFencePair geoFencePair2 = new GeoFencePair();
        geoFencePair2.setLatLng(new LatLng(14.0d, 17.0d));
        geoFencePair2.arrivingGeoFence.setId(1);
        geoFencePair2.arrivingGeoFence.setDraggable(true);
        geoFencePair2.arrivingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.ENTER);
        geoFencePair2.arrivingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        geoFencePair2.arrivingGeoFence.setGeoFenceDynamics(new GeoFenceData.GeoFenceDynamics(900.0f, true));
        geoFencePair2.leavingGeoFence.setId(2);
        geoFencePair2.leavingGeoFence.setDraggable(true);
        geoFencePair2.leavingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.EXIT);
        geoFencePair2.leavingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        geoFencePair2.leavingGeoFence.setGeoFenceDynamics(new GeoFenceData.GeoFenceDynamics(5000.0f, true));
        familyIdGeoFenceDataMap.put(60L, geoFencePair2);
        return familyIdGeoFenceDataMap;
    }

    public GeoFenceServerRequestModel createInstanceFromGeoFencePair() {
        GeoFenceServerRequestModel geoFenceServerRequestModel = new GeoFenceServerRequestModel();
        geoFenceServerRequestModel.getGeoFenceSettings().setArrivingRadius(Long.valueOf(getArrivingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().longValue()));
        geoFenceServerRequestModel.getGeoFenceSettings().setLeavingRadius(Long.valueOf(getLeavingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().longValue()));
        if (getLatLng() != null) {
            geoFenceServerRequestModel.getGeoFenceSettings().setLatitude(Double.valueOf(getLatLng().latitude));
            geoFenceServerRequestModel.getGeoFenceSettings().setLongitude(Double.valueOf(getLatLng().longitude));
        }
        geoFenceServerRequestModel.getAssociatedUsers().addAll(getAssociatedUsers());
        geoFenceServerRequestModel.getAssociatedRac().addAll(getAssociatedRacs());
        geoFenceServerRequestModel.setFamilyId(this.familyId);
        geoFenceServerRequestModel.getRacSettings().setArrivingToggleOn(Boolean.valueOf(getArrivingGeoFence().getModeTempSettings().isSettingsEnabled()));
        if (getArrivingGeoFence().getModeTempSettings().sendNullObjectToServer && !getArrivingGeoFence().getModeTempSettings().isSettingsEnabled) {
            geoFenceServerRequestModel.getRacSettings().setRacParametersForArriving((RacParameterForArriving) null);
        } else if (getArrivingGeoFence().getModeTempSettings().mode != null) {
            RacParameterForArriving racParameterForArriving = new RacParameterForArriving();
            racParameterForArriving.setFanSpeed((String) null);
            racParameterForArriving.setFanSwing((String) null);
            racParameterForArriving.setHumidity((int) getArrivingGeoFence().getModeTempSettings().humidity);
            racParameterForArriving.setMode(getArrivingGeoFence().getModeTempSettings().mode);
            racParameterForArriving.setPowerMode(getArrivingGeoFence().getModeTempSettings().powerMode);
            racParameterForArriving.setTemperature(Double.valueOf(getArrivingGeoFence().getModeTempSettings().temperature));
            racParameterForArriving.setRelativeTemperature(Double.valueOf(getArrivingGeoFence().getModeTempSettings().relativeTemperature));
            geoFenceServerRequestModel.getRacSettings().setRacParametersForArriving(racParameterForArriving);
        } else {
            geoFenceServerRequestModel.getRacSettings().setRacParametersForArriving((RacParameterForArriving) null);
        }
        geoFenceServerRequestModel.getRacSettings().setLeavingToggleOn(Boolean.valueOf(getLeavingGeoFence().getModeTempSettings().isSettingsEnabled()));
        if (getLeavingGeoFence().getModeTempSettings().sendNullObjectToServer && !getLeavingGeoFence().getModeTempSettings().isSettingsEnabled) {
            geoFenceServerRequestModel.getRacSettings().setRacParametersForLeaving((RacParameterForLeaving) null);
        } else if (getLeavingGeoFence().getModeTempSettings().mode != null) {
            RacParameterForLeaving racParameterForLeaving = new RacParameterForLeaving();
            racParameterForLeaving.setFanSpeed((String) null);
            racParameterForLeaving.setFanSwing((String) null);
            racParameterForLeaving.setHumidity((int) getLeavingGeoFence().getModeTempSettings().humidity);
            racParameterForLeaving.setMode(getLeavingGeoFence().getModeTempSettings().mode);
            racParameterForLeaving.setPowerMode(getLeavingGeoFence().getModeTempSettings().powerMode);
            racParameterForLeaving.setTemperature(Double.valueOf(getLeavingGeoFence().getModeTempSettings().temperature));
            racParameterForLeaving.setRelativeTemperature(Double.valueOf(getLeavingGeoFence().getModeTempSettings().relativeTemperature));
            geoFenceServerRequestModel.getRacSettings().setRacParametersForLeaving(racParameterForLeaving);
        } else {
            geoFenceServerRequestModel.getRacSettings().setRacParametersForLeaving((RacParameterForLeaving) null);
        }
        return geoFenceServerRequestModel;
    }

    public GeoFenceServerResponseModel createInstanceOfGeoFenceServerResponseModelFromGeoFencePair() {
        GeoFenceServerResponseModel geoFenceServerResponseModel = new GeoFenceServerResponseModel();
        geoFenceServerResponseModel.getRacSettings().setArrivingToggleOn(Boolean.valueOf(getArrivingGeoFence().getGeoFenceDynamics().isGeoFenceEnabled()));
        geoFenceServerResponseModel.getRacSettings().setLeavingToggleOn(Boolean.valueOf(getLeavingGeoFence().getGeoFenceDynamics().isGeoFenceEnabled()));
        geoFenceServerResponseModel.getGeoFenceSettings().setArrivingRadius(Long.valueOf(getArrivingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().longValue()));
        geoFenceServerResponseModel.getGeoFenceSettings().setLeavingRadius(Long.valueOf(getLeavingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().longValue()));
        if (getLatLng() != null) {
            geoFenceServerResponseModel.getGeoFenceSettings().setLatitude(Double.valueOf(getLatLng().latitude));
            geoFenceServerResponseModel.getGeoFenceSettings().setLongitude(Double.valueOf(getLatLng().longitude));
        }
        geoFenceServerResponseModel.getAssociatedUsers().addAll(getAssociatedUsers());
        return geoFenceServerResponseModel;
    }

    public GeoFencePair parcelClone() {
        GeoFencePair geoFencePair = new GeoFencePair(getArrivingGeoFence().parcelClone(), getLeavingGeoFence().parcelClone());
        geoFencePair.getAssociatedUsers().addAll(getAssociatedUsers());
        geoFencePair.getAssociatedRacs().addAll(getAssociatedRacs());
        geoFencePair.familyId = this.familyId;
        geoFencePair.isEnabled = this.isEnabled;
        geoFencePair.setLatLng(getLatLng());
        geoFencePair.getArrivingGeoFence().setFenceTransitionType(getArrivingGeoFence().getFenceTransitionType());
        geoFencePair.getLeavingGeoFence().setFenceTransitionType(getLeavingGeoFence().getFenceTransitionType());
        geoFencePair.getArrivingGeoFence().setModeTempSettings(getArrivingGeoFence().getModeTempSettings().parcelClone());
        geoFencePair.getLeavingGeoFence().setModeTempSettings(getLeavingGeoFence().getModeTempSettings().parcelClone());
        return geoFencePair;
    }

    public boolean removeCurrentUserIdIfExists(UserInfo userInfo) {
        return this.associatedUsers.remove(Long.valueOf((long) userInfo.f424id));
    }

    public boolean hasChanged(GeoFencePair geoFencePair) {
        boolean z = true;
        if (geoFencePair.isDefault) {
            return true;
        }
        boolean z2 = getArrivingGeoFence().getGeoFenceDynamics().isGeoFenceEnabled() != geoFencePair.getArrivingGeoFence().getGeoFenceDynamics().isGeoFenceEnabled();
        if (getLeavingGeoFence().getGeoFenceDynamics().isGeoFenceEnabled() != geoFencePair.getLeavingGeoFence().getGeoFenceDynamics().isGeoFenceEnabled()) {
            z2 = true;
        }
        if (!getLatLng().equals(geoFencePair.getLatLng())) {
            z2 = true;
        }
        if (!getArrivingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().equals(geoFencePair.getArrivingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters())) {
            z2 = true;
        }
        if (getLeavingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().equals(geoFencePair.getLeavingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters())) {
            z = z2;
        }
        this.valueChanged = z;
        return z;
    }

    public String toString() {
        return "GeoFencePair{arrivingGeoFence=" + this.arrivingGeoFence + ", leavingGeoFence=" + this.leavingGeoFence + ", latLng=" + this.latLng + ", isDefault=" + this.isDefault + ", valueChanged=" + this.valueChanged + ", userListChanged=" + this.userListChanged + ", racListChanged=" + this.racListChanged + ", familyId=" + this.familyId + ", associatedUsers=" + this.associatedUsers + ", associatedRac=" + this.associatedRac + ", associatedRacCloudId=" + this.associatedRacCloudId + '}';
    }
}
