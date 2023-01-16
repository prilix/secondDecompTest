package com.jch.racWiFi.genericViewModel;

import androidx.lifecycle.ViewModelStoreOwner;
import com.jch.racWiFi.Utils.ViewModelProviderUtil;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch.racWiFi.iduManagement.smartFence.viewModels.GeoFenceListViewModel;
import com.jch.racWiFi.iduManagement.viewModel.IDUsUpdateViewModel;
import com.jch.racWiFi.iduOnBoarding.ViewModel.OnBoardingViewModel;
import com.jch.racWiFi.userManagement.viewModel.UserViewModel;

public class GlobalViewModelRepository {
    private final EnergyConsumptionViewModel energyConsumptionViewModel;
    private final GeoFenceListViewModel geoFenceListViewModel;
    private final IDUsUpdateViewModel mIDUsUpdateViewModel;
    private final UserViewModel mUserViewModel;
    private final OnBoardingViewModel onBoardingViewModel;

    public IDUsUpdateViewModel getIDUsUpdateViewModel() {
        return this.mIDUsUpdateViewModel;
    }

    public UserViewModel getUserInfoViewModel() {
        return this.mUserViewModel;
    }

    public EnergyConsumptionViewModel getEnergyConsumptionViewModel() {
        return this.energyConsumptionViewModel;
    }

    public OnBoardingViewModel getOnBoardingViewModel() {
        return this.onBoardingViewModel;
    }

    public GeoFenceListViewModel getGeoFenceListViewModel() {
        return this.geoFenceListViewModel;
    }

    public GlobalViewModelRepository(ViewModelStoreOwner viewModelStoreOwner) {
        this.mIDUsUpdateViewModel = (IDUsUpdateViewModel) ViewModelProviderUtil.getViewModelInstance(viewModelStoreOwner, IDUsUpdateViewModel.class);
        this.mUserViewModel = (UserViewModel) ViewModelProviderUtil.getViewModelInstance(viewModelStoreOwner, UserViewModel.class);
        this.energyConsumptionViewModel = (EnergyConsumptionViewModel) ViewModelProviderUtil.getViewModelInstance(viewModelStoreOwner, EnergyConsumptionViewModel.class);
        this.onBoardingViewModel = (OnBoardingViewModel) ViewModelProviderUtil.getViewModelInstance(viewModelStoreOwner, OnBoardingViewModel.class);
        this.geoFenceListViewModel = (GeoFenceListViewModel) ViewModelProviderUtil.getViewModelInstance(viewModelStoreOwner, GeoFenceListViewModel.class);
    }
}
