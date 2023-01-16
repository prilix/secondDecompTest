package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import com.google.android.gms.location.Geofence;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.Utils.InstanceGenerator;
import com.jch.racWiFi.Utils.Stringifier;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyIdGeoFenceDataMap extends HashMap<Long, GeoFencePair> {
    private static final String EMPTY = new Stringifier(new FamilyIdGeoFenceDataMap()).getJsonString();
    private static final String PREF_KEY = "RacIdToGeoFenceDataMap_PREF_KEY";

    public List<Geofence> getListOfGeofenceForGoogleApi() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry value : entrySet()) {
            GeoFencePair geoFencePair = (GeoFencePair) value.getValue();
            if (geoFencePair.isEnabled) {
                arrayList.addAll(geoFencePair.getGeoFenceListForGoogleGeoFenceApi());
            }
        }
        return arrayList;
    }

    public FamilyIdGeoFenceDataMap parcelClone() {
        FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap = new FamilyIdGeoFenceDataMap();
        for (Map.Entry entry : entrySet()) {
            familyIdGeoFenceDataMap.put((Long) entry.getKey(), ((GeoFencePair) entry.getValue()).parcelClone());
        }
        return familyIdGeoFenceDataMap;
    }

    public void persist() {
        SharedPref.getInstance().getSharedPreferenceEditor().putString(PREF_KEY, new Stringifier(this).getJsonString()).commit();
    }

    public static FamilyIdGeoFenceDataMap getInstanceFromPreference() {
        return (FamilyIdGeoFenceDataMap) new InstanceGenerator(FamilyIdGeoFenceDataMap.class, SharedPref.getInstance().getSharedPreferencesReader().getString(PREF_KEY, EMPTY)).getInstance();
    }
}
