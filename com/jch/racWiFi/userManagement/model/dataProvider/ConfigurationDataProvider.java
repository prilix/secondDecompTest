package com.jch.racWiFi.userManagement.model.dataProvider;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.jch.racWiFi.IduAccess.model.IduAccessModel;
import com.jch.racWiFi.Utils.BackgroundExecutor;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import com.jch.racWiFi.userManagement.model.dto.InitialAppConfigDto;
import java.util.List;

public class ConfigurationDataProvider {
    private static final ConfigurationDataProvider CONFIGURATION_DATA_PROVIDER = new ConfigurationDataProvider();
    private IduAccessModel iduAccessModelData;
    public MutableLiveData<IduAccessModel> iduAccessModelMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<InitialAppConfigDto> initialAppConfigDtoLiveData = new MutableLiveData<>();
    private List<DetailedIduModel> onboardedIdus;
    private MutableLiveData<AllPermissionDataDto> permissionDataDtoMutableLiveData = new MutableLiveData<>();

    public static ConfigurationDataProvider getInstance() {
        return CONFIGURATION_DATA_PROVIDER;
    }

    private ConfigurationDataProvider() {
    }

    public void setOnBoardedIdus(List<DetailedIduModel> list) {
        this.onboardedIdus = list;
    }

    public List<DetailedIduModel> getOnboardedIdus() {
        return this.onboardedIdus;
    }

    public LiveData<InitialAppConfigDto> getInitialAppConfigDtoLiveData() {
        return this.initialAppConfigDtoLiveData;
    }

    public void setInitialAppConfigDtoLiveData(InitialAppConfigDto initialAppConfigDto) {
        this.initialAppConfigDtoLiveData.setValue(initialAppConfigDto);
    }

    public LiveData<AllPermissionDataDto> getPermissionDataDtoLiveData() {
        return this.permissionDataDtoMutableLiveData;
    }

    /* renamed from: lambda$setPermissionDataDtoMutableLiveData$0$com-jch-racWiFi-userManagement-model-dataProvider-ConfigurationDataProvider */
    public /* synthetic */ void mo32514xadfc89f7(AllPermissionDataDto allPermissionDataDto) {
        this.permissionDataDtoMutableLiveData.setValue(allPermissionDataDto);
    }

    public void setPermissionDataDtoMutableLiveData(AllPermissionDataDto allPermissionDataDto) {
        BackgroundExecutor.postOnMainThread(new ConfigurationDataProvider$$ExternalSyntheticLambda0(this, allPermissionDataDto));
    }

    public IduAccessModel getIduAccessModelData() {
        return this.iduAccessModelData;
    }

    public void setIduAccessModelData(IduAccessModel iduAccessModel) {
        this.iduAccessModelData = iduAccessModel;
        this.iduAccessModelMutableLiveData.setValue(iduAccessModel);
    }
}
