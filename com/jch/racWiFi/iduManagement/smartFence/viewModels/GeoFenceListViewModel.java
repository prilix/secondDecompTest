package com.jch.racWiFi.iduManagement.smartFence.viewModels;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.gson.reflect.TypeToken;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.genericViewModel.GenericNetworkCallViewModel;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.FamilyIdGeoFenceDataMap;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceServerResponseModel;
import com.jch.racWiFi.iduManagement.smartFence.model.LocationControlStateResponseModel;
import com.jch.racWiFi.iduManagement.smartFence.model.LocationControlsStateModel;
import com.jch.racWiFi.iduManagement.smartFence.networkDispatcher.GeoFencesNetworkDispatcher;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GeoFenceListViewModel extends GenericNetworkCallViewModel {
    /* access modifiers changed from: private */
    public SingleLiveEvent<FamilyIdGeoFenceDataMap> racIdToGeoFenceDataMapMutableLiveData = new SingleLiveEvent<>(new FamilyIdGeoFenceDataMap());
    public SingleLiveEvent<Boolean> racListChangedSingleLiveEvent = new SingleLiveEvent<>();
    SingleLiveEvent<GenericResponse> singleLiveEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Map<Long, LocationControlStateResponseModel>> singleLiveEventForStatus = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getRacListChangedSingleLiveEvent() {
        return this.racListChangedSingleLiveEvent;
    }

    public SingleLiveEvent<FamilyIdGeoFenceDataMap> getRacIdToGeoFenceDataMapMutableLiveData() {
        return this.racIdToGeoFenceDataMapMutableLiveData;
    }

    public MutableLiveData<FamilyIdGeoFenceDataMap> getAllGeoFences(LifecycleOwner lifecycleOwner) {
        C19481 r0 = new Observer<GenericResponse>() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            static {
                Class<GeoFenceListViewModel> cls = GeoFenceListViewModel.class;
            }

            public void onChanged(GenericResponse genericResponse) {
                List<GeoFenceServerResponseModel> list;
                if (genericResponse.isApiSuccessful() && (list = (List) genericResponse.getBodyOfType(new TypeToken<ArrayList<GeoFenceServerResponseModel>>() {
                }.getType())) != null) {
                    if (list.isEmpty()) {
                        ((FamilyIdGeoFenceDataMap) GeoFenceListViewModel.this.racIdToGeoFenceDataMapMutableLiveData.getValue()).clear();
                    }
                    FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap = (FamilyIdGeoFenceDataMap) GeoFenceListViewModel.this.racIdToGeoFenceDataMapMutableLiveData.getValue();
                    for (GeoFenceServerResponseModel geoFenceServerResponseModel : list) {
                        familyIdGeoFenceDataMap.put(geoFenceServerResponseModel.getFamilyId(), geoFenceServerResponseModel.createInstanceFromGeoFenceServerResponseModel());
                        if (!(GeoFenceListViewModel.this.singleLiveEventForStatus.getValue() == null || familyIdGeoFenceDataMap.get(geoFenceServerResponseModel.getFamilyId()) == null || GeoFenceListViewModel.this.singleLiveEventForStatus.getValue().get(geoFenceServerResponseModel.getFamilyId()) == null)) {
                            GeoFencePair geoFencePair = (GeoFencePair) familyIdGeoFenceDataMap.get(geoFenceServerResponseModel.getFamilyId());
                            Objects.requireNonNull(geoFencePair);
                            GeoFencePair geoFencePair2 = geoFencePair;
                            LocationControlStateResponseModel locationControlStateResponseModel = (LocationControlStateResponseModel) GeoFenceListViewModel.this.singleLiveEventForStatus.getValue().get(geoFenceServerResponseModel.getFamilyId());
                            Objects.requireNonNull(locationControlStateResponseModel);
                            LocationControlStateResponseModel locationControlStateResponseModel2 = locationControlStateResponseModel;
                            geoFencePair.isEnabled = locationControlStateResponseModel.enabled;
                        }
                    }
                    GeoFenceListViewModel.this.racIdToGeoFenceDataMapMutableLiveData.setValue(familyIdGeoFenceDataMap);
                }
            }
        };
        GeoFencesNetworkDispatcher geoFencesNetworkDispatcher = new GeoFencesNetworkDispatcher();
        if (lifecycleOwner == null) {
            geoFencesNetworkDispatcher.getAllGeoFences().observeForever(r0);
        } else {
            geoFencesNetworkDispatcher.getAllGeoFences().observeSingleEvent(lifecycleOwner, r0);
        }
        return this.racIdToGeoFenceDataMapMutableLiveData;
    }

    public SingleLiveEvent<GenericResponse> getOnEnableSingleLiveEvent() {
        return this.singleLiveEvent;
    }

    public void enableLocationControl(LifecycleOwner lifecycleOwner) {
        new GeoFencesNetworkDispatcher().setGeoFenceStatusForParticularFamily(new LocationControlsStateModel(true), FamilyGroupList.getCurrentFamily().familyId).observeSingleEvent(lifecycleOwner, new GeoFenceListViewModel$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$enableLocationControl$0$com-jch-racWiFi-iduManagement-smartFence-viewModels-GeoFenceListViewModel */
    public /* synthetic */ void mo30420x89b39f1f(GenericResponse genericResponse) {
        if (genericResponse.isApiSuccessful()) {
            this.singleLiveEvent.postValue(genericResponse);
        } else {
            this.singleLiveEvent.postValue(genericResponse);
        }
    }

    public void disableLocationControl(LifecycleOwner lifecycleOwner) {
        new GeoFencesNetworkDispatcher().setGeoFenceStatusForParticularFamily(new LocationControlsStateModel(false), FamilyGroupList.getCurrentFamily().familyId).observeSingleEvent(lifecycleOwner, new GeoFenceListViewModel$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$disableLocationControl$1$com-jch-racWiFi-iduManagement-smartFence-viewModels-GeoFenceListViewModel */
    public /* synthetic */ void mo30419x2333ccf5(GenericResponse genericResponse) {
        if (genericResponse.isApiSuccessful()) {
            this.singleLiveEvent.postValue(genericResponse);
        } else {
            this.singleLiveEvent.postValue(genericResponse);
        }
    }

    public SingleLiveEvent<Map<Long, LocationControlStateResponseModel>> getSingleLiveEventStatus() {
        return this.singleLiveEventForStatus;
    }

    public void getGeoFenceStatus(LifecycleOwner lifecycleOwner) {
        new GeoFencesNetworkDispatcher().getGeoFenceStatus().observeSingleEvent(lifecycleOwner, new GeoFenceListViewModel$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$getGeoFenceStatus$2$com-jch-racWiFi-iduManagement-smartFence-viewModels-GeoFenceListViewModel */
    public /* synthetic */ void mo30421x39268224(GenericResponse genericResponse) {
        if (genericResponse.isApiSuccessful()) {
            List<LocationControlStateResponseModel> list = (List) genericResponse.getBodyOfType(new TypeToken<ArrayList<LocationControlStateResponseModel>>() {
            }.getType());
            HashMap hashMap = new HashMap();
            if (list != null) {
                for (LocationControlStateResponseModel locationControlStateResponseModel : list) {
                    hashMap.put(Long.valueOf((long) locationControlStateResponseModel.familyId), locationControlStateResponseModel);
                }
            }
            this.singleLiveEventForStatus.setValue(hashMap);
        }
    }

    public SingleLiveEvent<GenericResponse> updateGeoFenceSettings(GeoFencePair geoFencePair) {
        return new GeoFencesNetworkDispatcher().updateGeoFenceSettings(geoFencePair.createInstanceFromGeoFencePair());
    }
}
