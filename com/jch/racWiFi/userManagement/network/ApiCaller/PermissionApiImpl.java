package com.jch.racWiFi.userManagement.network.ApiCaller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.accord.common.utils.Logger;
import com.google.gson.Gson;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.MockProvider;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import com.jch.racWiFi.userManagement.model.dto.InitialAppConfigDto;
import com.jch.racWiFi.userManagement.model.dto.PermissionSaveDto;
import com.jch.racWiFi.userManagement.network.Api.PermissionApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PermissionApiImpl extends AbstractNetworkDispatcher {
    private static final PermissionApiImpl PERMISSION_API = new PermissionApiImpl();
    PermissionApi permissionApi = ((PermissionApi) getRetrofitService().create(PermissionApi.class));

    public static PermissionApiImpl getInstance() {
        return PERMISSION_API;
    }

    private PermissionApiImpl() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public void requestConfiguartionAndRoles() {
        this.permissionApi.requestConfigData(TokenUtil.getInstance().obtain().getBearerToken()).enqueue(new Callback<InitialAppConfigDto>() {
            public void onResponse(Call<InitialAppConfigDto> call, Response<InitialAppConfigDto> response) {
                Logger.m49i("", "init response = " + new Gson().toJson((Object) response.body()));
                PermissionApiImpl.this.getMainThreadHandler().post(new PermissionApiImpl$1$$ExternalSyntheticLambda1(response));
            }

            public void onFailure(Call<InitialAppConfigDto> call, Throwable th) {
                if (DemoModeModel.DEMO_MODE) {
                    PermissionApiImpl.this.getMainThreadHandler().post(new PermissionApiImpl$1$$ExternalSyntheticLambda0(MockProvider.getInstance().getConfigMock()));
                    return;
                }
                PermissionApiImpl.this.getMainThreadHandler().post(PermissionApiImpl$1$$ExternalSyntheticLambda2.INSTANCE);
            }
        });
    }

    public LiveData<Response<AllPermissionDataDto>> requestPermissionsData(Integer num) {
        Call<AllPermissionDataDto> call;
        ConfigurationDataProvider.getInstance();
        final MutableLiveData mutableLiveData = new MutableLiveData();
        if (num == null) {
            call = this.permissionApi.requestAllPermissionDetails(TokenUtil.getInstance().obtain().getBearerToken(), Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId));
        } else {
            call = this.permissionApi.requestIduSpecificPermissionDetails(TokenUtil.getInstance().obtain().getBearerToken(), Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId), num);
        }
        call.enqueue(new Callback<AllPermissionDataDto>() {
            public void onResponse(Call<AllPermissionDataDto> call, Response<AllPermissionDataDto> response) {
                if (response != null) {
                    mutableLiveData.setValue(response);
                } else {
                    mutableLiveData.setValue(null);
                }
            }

            public void onFailure(Call<AllPermissionDataDto> call, Throwable th) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public LiveData<Response<ResponseBody>> requestPermissionSaving(PermissionSaveDto permissionSaveDto, Integer num) {
        final MutableLiveData mutableLiveData = new MutableLiveData();
        this.permissionApi.requestPermissionDetailsSave(TokenUtil.getInstance().obtain().getBearerToken(), permissionSaveDto, Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId)).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Logger.m45d("Saved", "save");
                PermissionApiImpl.this.getMainThreadHandler().post(new PermissionApiImpl$3$$ExternalSyntheticLambda1(mutableLiveData, response));
            }

            public void onFailure(Call<ResponseBody> call, Throwable th) {
                Logger.m45d("Saved", "not save");
                PermissionApiImpl.this.getMainThreadHandler().post(new PermissionApiImpl$3$$ExternalSyntheticLambda0(mutableLiveData));
            }
        });
        return mutableLiveData;
    }
}
