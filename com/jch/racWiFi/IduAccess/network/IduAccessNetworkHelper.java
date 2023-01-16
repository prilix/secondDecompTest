package com.jch.racWiFi.IduAccess.network;

import com.accord.common.utils.Logger;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.IduAccess.model.IduAccessModel;
import com.jch.racWiFi.MockProvider;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IduAccessNetworkHelper extends AbstractNetworkDispatcher {
    private static final IduAccessNetworkHelper IDU_ACCESS_NETWORK_HELPER = new IduAccessNetworkHelper();
    IduAccessApi iduAccessApi = ((IduAccessApi) getRetrofitService().create(IduAccessApi.class));

    public static IduAccessNetworkHelper getInstance() {
        return IDU_ACCESS_NETWORK_HELPER;
    }

    private IduAccessNetworkHelper() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public void fetchIduAccess() {
        this.iduAccessApi.fetch(TokenUtil.getInstance().obtain().getBearerToken(), Integer.valueOf(FamilyGroupList.getCurrentFamily().familyId)).enqueue(new Callback<HashMap<String, Boolean>>() {
            public void onResponse(Call<HashMap<String, Boolean>> call, Response<HashMap<String, Boolean>> response) {
                IduAccessModel iduAccessModel = new IduAccessModel();
                iduAccessModel.setMap(response.body());
                ConfigurationDataProvider.getInstance().setIduAccessModelData(iduAccessModel);
            }

            public void onFailure(Call<HashMap<String, Boolean>> call, Throwable th) {
                if (DemoModeModel.DEMO_MODE) {
                    IduAccessModel iduAccessModel = new IduAccessModel();
                    iduAccessModel.setMap(MockProvider.getInstance().getIduAccessModelMap());
                    ConfigurationDataProvider.getInstance().setIduAccessModelData(iduAccessModel);
                }
                Logger.m47e("IduAccess", th.toString());
            }
        });
    }
}
