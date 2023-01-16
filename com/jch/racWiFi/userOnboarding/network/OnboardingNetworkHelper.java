package com.jch.racWiFi.userOnboarding.network;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoRequestBody;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoResponseBody;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch.racWiFi.userOnboarding.model.OnBoardingModel;
import com.jch.racWiFi.userOnboarding.model.RenamingDto;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnboardingNetworkHelper extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    private static final OnboardingNetworkHelper ONBOARDING_NETWORK_HELPER = new OnboardingNetworkHelper();
    /* access modifiers changed from: private */
    public MutableLiveData<Response<ResponseBody>> deleteResponse = new MutableLiveData<>();
    private SingleLiveEvent<GenericResponse> iduOnBoardingSingleLiveEvent;
    /* access modifiers changed from: private */
    public Handler mainUiHandler = getMainThreadHandler();
    /* access modifiers changed from: private */
    public SingleLiveEvent<Response<IduDetailsModel>> onBoardResult = new SingleLiveEvent<>();
    /* access modifiers changed from: private */
    public MutableLiveData<Response<List<IduDetailsModel>>> onBoardedIduDetails = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public MutableLiveData<Response<OnboardingInfoResponseBody>> onBoardedIduInfo = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public OnboardingApi onboardingApi = ((OnboardingApi) getRetrofitService().create(OnboardingApi.class));
    /* access modifiers changed from: private */
    public MutableLiveData<Response<ResponseBody>> renamingResponse = new MutableLiveData<>();

    public static OnboardingNetworkHelper getInstance() {
        return ONBOARDING_NETWORK_HELPER;
    }

    private OnboardingNetworkHelper() {
        super("https://api-global-prod.aircloudhome.com");
    }

    public SingleLiveEvent<Response<IduDetailsModel>> requestOnBoardingToServer(OnBoardingModel onBoardingModel) {
        Call<IduDetailsModel> call;
        this.onBoardResult = new SingleLiveEvent<>();
        String bearerToken = TokenUtil.getInstance().obtain().getBearerToken();
        if (onBoardingModel.getAdapterType().equals("2")) {
            call = this.onboardingApi.requestIndiaOnboarding(FamilyGroupList.getCurrentFamily().familyId, bearerToken, onBoardingModel);
        } else {
            call = this.onboardingApi.requestOnboarding(FamilyGroupList.getCurrentFamily().familyId, bearerToken, onBoardingModel);
        }
        call.enqueue(new Callback<IduDetailsModel>() {
            public void onResponse(Call<IduDetailsModel> call, Response<IduDetailsModel> response) {
                Logger.m49i("", "onboarding api url = " + call.request().url());
                Logger.m49i("", "onboarding api responce = " + response.body());
                OnboardingNetworkHelper.this.mainUiHandler.post(new OnboardingNetworkHelper$1$$ExternalSyntheticLambda1(this, response));
            }

            /* renamed from: lambda$onResponse$0$com-jch-racWiFi-userOnboarding-network-OnboardingNetworkHelper$1 */
            public /* synthetic */ void mo33398x182b4cd1(Response response) {
                OnboardingNetworkHelper.this.onBoardResult.setValue(response);
            }

            /* renamed from: lambda$onFailure$1$com-jch-racWiFi-userOnboarding-network-OnboardingNetworkHelper$1 */
            public /* synthetic */ void mo33397xf61ed17f() {
                OnboardingNetworkHelper.this.onBoardResult.setValue(null);
            }

            public void onFailure(Call<IduDetailsModel> call, Throwable th) {
                OnboardingNetworkHelper.this.mainUiHandler.post(new OnboardingNetworkHelper$1$$ExternalSyntheticLambda0(this));
                Logger.m45d("OnboardStatus", th.toString());
            }
        });
        return this.onBoardResult;
    }

    public LiveData<Response<OnboardingInfoResponseBody>> requestOnboardedIduInfoToServer(String str) {
        this.onBoardedIduInfo = new MutableLiveData<>();
        OnboardingInfoRequestBody onboardingInfoRequestBody = new OnboardingInfoRequestBody();
        onboardingInfoRequestBody.setVendorThingId(str);
        this.onboardingApi.onBoardedIduInfo(TokenUtil.getInstance().obtain().getBearerToken(), onboardingInfoRequestBody).enqueue(new Callback<OnboardingInfoResponseBody>() {
            public void onResponse(Call<OnboardingInfoResponseBody> call, Response<OnboardingInfoResponseBody> response) {
                OnboardingNetworkHelper.this.onBoardedIduInfo.setValue(response);
            }

            public void onFailure(Call<OnboardingInfoResponseBody> call, Throwable th) {
                OnboardingNetworkHelper.this.onBoardedIduInfo.setValue(null);
            }
        });
        return this.onBoardedIduInfo;
    }

    public SingleLiveEvent<GenericResponse> resetIdu(int i, String str) {
        this.iduOnBoardingSingleLiveEvent = new SingleLiveEvent<>();
        this.onboardingApi.resetIdu(str, i).enqueue(this);
        return this.iduOnBoardingSingleLiveEvent;
    }

    public LiveData<Response<ResponseBody>> requestIduRemoval(int i, Long l) {
        this.deleteResponse = new MutableLiveData<>();
        Call<ResponseBody> requestIduRemoval = this.onboardingApi.requestIduRemoval(TokenUtil.getInstance().obtain().getBearerToken(), i, l);
        requestIduRemoval.enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                OnboardingNetworkHelper.this.deleteResponse.setValue(response);
            }

            public void onFailure(Call<ResponseBody> call, Throwable th) {
                OnboardingNetworkHelper.this.deleteResponse.setValue(null);
            }
        });
        super.setBodyCall(requestIduRemoval);
        return this.deleteResponse;
    }

    public LiveData<Response<List<IduDetailsModel>>> requestOnBoardedIduDeatils(final LifecycleOwner lifecycleOwner) {
        this.onBoardedIduDetails = new MutableLiveData<>();
        final String bearerToken = TokenUtil.getInstance().obtain().getBearerToken();
        this.onboardingApi.requestIduDetails(FamilyGroupList.getCurrentFamily().familyId, bearerToken).enqueue(new Callback<List<IduDetailsModel>>() {
            public void onResponse(Call<List<IduDetailsModel>> call, Response<List<IduDetailsModel>> response) {
                Logger.m45d("IduDetails", response.code() + "");
                OnboardingNetworkHelper.this.mainUiHandler.post(new OnboardingNetworkHelper$4$$ExternalSyntheticLambda3(this, response));
                if (lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    OnboardingNetworkHelper.this.mainUiHandler.postDelayed(new OnboardingNetworkHelper$4$$ExternalSyntheticLambda2(this, bearerToken), 4000);
                }
            }

            /* renamed from: lambda$onResponse$0$com-jch-racWiFi-userOnboarding-network-OnboardingNetworkHelper$4 */
            public /* synthetic */ void mo33401x182b4cd4(Response response) {
                OnboardingNetworkHelper.this.onBoardedIduDetails.setValue(response);
            }

            /* renamed from: lambda$onResponse$1$com-jch-racWiFi-userOnboarding-network-OnboardingNetworkHelper$4 */
            public /* synthetic */ void mo33402x825ad4f3(String str) {
                OnboardingNetworkHelper.this.onboardingApi.requestIduDetails(FamilyGroupList.getCurrentFamily().familyId, str).enqueue(this);
            }

            /* renamed from: lambda$onFailure$2$com-jch-racWiFi-userOnboarding-network-OnboardingNetworkHelper$4 */
            public /* synthetic */ void mo33399x604e59a1() {
                OnboardingNetworkHelper.this.onBoardedIduDetails.setValue(null);
            }

            public void onFailure(Call<List<IduDetailsModel>> call, Throwable th) {
                OnboardingNetworkHelper.this.mainUiHandler.post(new OnboardingNetworkHelper$4$$ExternalSyntheticLambda0(this));
                Logger.m45d("IduDetails", th.toString());
                if (lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    OnboardingNetworkHelper.this.mainUiHandler.postDelayed(new OnboardingNetworkHelper$4$$ExternalSyntheticLambda1(this, bearerToken), 4000);
                }
            }

            /* renamed from: lambda$onFailure$3$com-jch-racWiFi-userOnboarding-network-OnboardingNetworkHelper$4 */
            public /* synthetic */ void mo33400xca7de1c0(String str) {
                OnboardingNetworkHelper.this.onboardingApi.requestIduDetails(FamilyGroupList.getCurrentFamily().familyId, str).enqueue(this);
            }
        });
        return this.onBoardedIduDetails;
    }

    public LiveData<Response<ResponseBody>> requestIduRenaming(IduDetailsModel iduDetailsModel) {
        this.renamingResponse = new MutableLiveData<>();
        this.onboardingApi.requestIduRenaming(TokenUtil.getInstance().obtain().getBearerToken(), iduDetailsModel.getId(), new RenamingDto(iduDetailsModel.getName())).enqueue(new Callback<ResponseBody>() {
            /* renamed from: lambda$onResponse$0$com-jch-racWiFi-userOnboarding-network-OnboardingNetworkHelper$5 */
            public /* synthetic */ void mo33404x182b4cd5(Response response) {
                OnboardingNetworkHelper.this.renamingResponse.setValue(response);
            }

            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                OnboardingNetworkHelper.this.mainUiHandler.post(new OnboardingNetworkHelper$5$$ExternalSyntheticLambda1(this, response));
            }

            public void onFailure(Call<ResponseBody> call, Throwable th) {
                Logger.m47e("Renaming", toString());
                OnboardingNetworkHelper.this.mainUiHandler.post(new OnboardingNetworkHelper$5$$ExternalSyntheticLambda0(this));
            }

            /* renamed from: lambda$onFailure$1$com-jch-racWiFi-userOnboarding-network-OnboardingNetworkHelper$5 */
            public /* synthetic */ void mo33403xf61ed183() {
                OnboardingNetworkHelper.this.renamingResponse.setValue(null);
            }
        });
        return this.renamingResponse;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.iduOnBoardingSingleLiveEvent.setValue(GenericResponse.getSuccessGenericResponse(response));
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.iduOnBoardingSingleLiveEvent.postValue(GenericResponse.getFailureGenericResponse(th));
    }
}
