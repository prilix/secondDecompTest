package com.jch.racWiFi.iduManagement.network;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.iduManagement.dto.OperationSwitchOnOffDto;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.StopAllUnitsModels;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IduOperationNetworkHelper extends AbstractNetworkDispatcher {
    private static final IduOperationNetworkHelper IDU_OPERATION_NETWORK_DISPACHER = new IduOperationNetworkHelper();
    MutableLiveData<Response<List<DetailedIduModel>>> detailedIduListLiveData;
    public IduOperationApi iduOperationApi = ((IduOperationApi) getRetrofitService().create(IduOperationApi.class));
    MutableLiveData<Response<ResponseBody>> operationOnOffResponse = new MutableLiveData<>();
    MutableLiveData<StopAllUnitsModels.StartAllUnitsSuccessResponse> startAllResult = new MutableLiveData<>();
    MutableLiveData<StopAllUnitsModels.StopAllnitsSuccessResponse> stopAllResult = new MutableLiveData<>();

    public static IduOperationNetworkHelper getInstance() {
        return IDU_OPERATION_NETWORK_DISPACHER;
    }

    private IduOperationNetworkHelper() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public LiveData<Response<ResponseBody>> requestPowerOnOff(Long l, OperationSwitchOnOffDto operationSwitchOnOffDto) {
        this.operationOnOffResponse = new MutableLiveData<>();
        this.iduOperationApi.requestPowerOnOff(TokenUtil.getInstance().obtain().getBearerToken(), l, operationSwitchOnOffDto).enqueue(new Callback<ResponseBody>() {
            /* renamed from: lambda$onResponse$0$com-jch-racWiFi-iduManagement-network-IduOperationNetworkHelper$1 */
            public /* synthetic */ void mo29945xd09e3a04(Response response) {
                IduOperationNetworkHelper.this.operationOnOffResponse.setValue(response);
            }

            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                IduOperationNetworkHelper.this.getMainThreadHandler().post(new IduOperationNetworkHelper$1$$ExternalSyntheticLambda1(this, response));
            }

            /* renamed from: lambda$onFailure$1$com-jch-racWiFi-iduManagement-network-IduOperationNetworkHelper$1 */
            public /* synthetic */ void mo29944xb11b4b16() {
                IduOperationNetworkHelper.this.operationOnOffResponse.setValue(null);
            }

            public void onFailure(Call<ResponseBody> call, Throwable th) {
                IduOperationNetworkHelper.this.getMainThreadHandler().post(new IduOperationNetworkHelper$1$$ExternalSyntheticLambda0(this));
            }
        });
        return this.operationOnOffResponse;
    }

    public LiveData<Response<List<DetailedIduModel>>> requestDetailedIduLists(LifecycleOwner lifecycleOwner) {
        this.detailedIduListLiveData = new MutableLiveData<>();
        this.iduOperationApi.requestDetailedIduList(TokenUtil.getInstance().obtain().getBearerToken()).enqueue(new Callback<List<DetailedIduModel>>() {
            public void onResponse(Call<List<DetailedIduModel>> call, Response<List<DetailedIduModel>> response) {
                IduOperationNetworkHelper.this.getMainThreadHandler().post(new IduOperationNetworkHelper$2$$ExternalSyntheticLambda1(this, response));
                ConfigurationDataProvider.getInstance().setOnBoardedIdus(response.body());
            }

            /* renamed from: lambda$onResponse$0$com-jch-racWiFi-iduManagement-network-IduOperationNetworkHelper$2 */
            public /* synthetic */ void mo29947xd09e3a05(Response response) {
                IduOperationNetworkHelper.this.detailedIduListLiveData.setValue(response);
            }

            public void onFailure(Call<List<DetailedIduModel>> call, Throwable th) {
                Logger.m47e("IduDetails", th.toString());
                IduOperationNetworkHelper.this.getMainThreadHandler().post(new IduOperationNetworkHelper$2$$ExternalSyntheticLambda0(this));
            }

            /* renamed from: lambda$onFailure$1$com-jch-racWiFi-iduManagement-network-IduOperationNetworkHelper$2 */
            public /* synthetic */ void mo29946xb11b4b17() {
                IduOperationNetworkHelper.this.detailedIduListLiveData.setValue(null);
            }
        });
        return this.detailedIduListLiveData;
    }

    public LiveData<StopAllUnitsModels.StopAllnitsSuccessResponse> requestAllIduStartStop(List<DetailedIduModel> list) {
        this.stopAllResult = new MutableLiveData<>();
        this.iduOperationApi.stopAllUnits(FamilyGroupList.getCurrentFamily().familyId, TokenUtil.getInstance().obtain().getBearerToken(), list).enqueue(new Callback<StopAllUnitsModels.StopAllnitsSuccessResponse>() {
            public void onResponse(Call<StopAllUnitsModels.StopAllnitsSuccessResponse> call, Response<StopAllUnitsModels.StopAllnitsSuccessResponse> response) {
                IduOperationNetworkHelper.this.getMainThreadHandler().post(new IduOperationNetworkHelper$3$$ExternalSyntheticLambda1(this, response.body()));
            }

            /* renamed from: lambda$onResponse$0$com-jch-racWiFi-iduManagement-network-IduOperationNetworkHelper$3 */
            public /* synthetic */ void mo29949xd09e3a06(StopAllUnitsModels.StopAllnitsSuccessResponse stopAllnitsSuccessResponse) {
                IduOperationNetworkHelper.this.stopAllResult.setValue(stopAllnitsSuccessResponse);
            }

            /* renamed from: lambda$onFailure$1$com-jch-racWiFi-iduManagement-network-IduOperationNetworkHelper$3 */
            public /* synthetic */ void mo29948xb11b4b18() {
                IduOperationNetworkHelper.this.stopAllResult.setValue(null);
            }

            public void onFailure(Call<StopAllUnitsModels.StopAllnitsSuccessResponse> call, Throwable th) {
                IduOperationNetworkHelper.this.getMainThreadHandler().post(new IduOperationNetworkHelper$3$$ExternalSyntheticLambda0(this));
            }
        });
        return this.stopAllResult;
    }

    public LiveData<StopAllUnitsModels.StartAllUnitsSuccessResponse> requestAllIduStart(List<DetailedIduModel> list) {
        this.startAllResult = new MutableLiveData<>();
        this.iduOperationApi.startAllUnits(FamilyGroupList.getCurrentFamily().familyId, TokenUtil.getInstance().obtain().getBearerToken(), list).enqueue(new Callback<StopAllUnitsModels.StartAllUnitsSuccessResponse>() {
            public void onResponse(Call<StopAllUnitsModels.StartAllUnitsSuccessResponse> call, Response<StopAllUnitsModels.StartAllUnitsSuccessResponse> response) {
                IduOperationNetworkHelper.this.getMainThreadHandler().post(new IduOperationNetworkHelper$4$$ExternalSyntheticLambda1(this, response.body()));
            }

            /* renamed from: lambda$onResponse$0$com-jch-racWiFi-iduManagement-network-IduOperationNetworkHelper$4 */
            public /* synthetic */ void mo29951xd09e3a07(StopAllUnitsModels.StartAllUnitsSuccessResponse startAllUnitsSuccessResponse) {
                IduOperationNetworkHelper.this.startAllResult.setValue(startAllUnitsSuccessResponse);
            }

            /* renamed from: lambda$onFailure$1$com-jch-racWiFi-iduManagement-network-IduOperationNetworkHelper$4 */
            public /* synthetic */ void mo29950xb11b4b19() {
                IduOperationNetworkHelper.this.startAllResult.setValue(null);
            }

            public void onFailure(Call<StopAllUnitsModels.StartAllUnitsSuccessResponse> call, Throwable th) {
                IduOperationNetworkHelper.this.getMainThreadHandler().post(new IduOperationNetworkHelper$4$$ExternalSyntheticLambda0(this));
            }
        });
        return this.startAllResult;
    }
}
