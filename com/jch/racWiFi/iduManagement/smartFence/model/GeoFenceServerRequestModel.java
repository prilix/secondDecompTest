package com.jch.racWiFi.iduManagement.smartFence.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceData;
import java.util.ArrayList;
import java.util.List;

public class GeoFenceServerRequestModel {
    @SerializedName("familyId")
    private Long familyId;
    @SerializedName("associatedUsers")
    private List<Long> mAssociatedUsers = new ArrayList();
    @SerializedName("geofenceSettings")
    private GeoFenceSettings mGeoFenceSettings = new GeoFenceSettings();
    @SerializedName("racSettings")
    private RacSettings mRacSettings = new RacSettings();
    @SerializedName("racIds")
    private List<Long> racIds = new ArrayList();

    public List<Long> getAssociatedUsers() {
        return this.mAssociatedUsers;
    }

    public void setAssociatedUsers(List<Long> list) {
        this.mAssociatedUsers = list;
    }

    public List<Long> getAssociatedRac() {
        return this.racIds;
    }

    public void setRacIds(List<Long> list) {
        this.racIds = list;
    }

    public Long getFamilyId() {
        return this.familyId;
    }

    public void setFamilyId(Long l) {
        this.familyId = l;
    }

    public RacSettings getRacSettings() {
        return this.mRacSettings;
    }

    public void setRacSettings(RacSettings racSettings) {
        this.mRacSettings = racSettings;
    }

    public GeoFenceSettings getGeoFenceSettings() {
        return this.mGeoFenceSettings;
    }

    public void setGeoFenceSettings(GeoFenceSettings geoFenceSettings) {
        this.mGeoFenceSettings = geoFenceSettings;
    }

    public GeoFenceServerResponseModel getGeoFenceServerResponseModelInstance() {
        GeoFencePair geoFencePair = new GeoFencePair();
        geoFencePair.setLatLng(new LatLng(getGeoFenceSettings().getLatitude().doubleValue(), getGeoFenceSettings().getLongitude().doubleValue()));
        GeoFenceData arrivingGeoFence = geoFencePair.getArrivingGeoFence();
        GeoFenceData leavingGeoFence = geoFencePair.getLeavingGeoFence();
        arrivingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.ENTER);
        getGeoFenceSettings().getArrivingRadius().longValue();
        arrivingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        leavingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.EXIT);
        getGeoFenceSettings().getLeavingRadius().longValue();
        leavingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        geoFencePair.getAssociatedUsers().addAll(getAssociatedUsers());
        return geoFencePair.createInstanceOfGeoFenceServerResponseModelFromGeoFencePair();
    }
}
