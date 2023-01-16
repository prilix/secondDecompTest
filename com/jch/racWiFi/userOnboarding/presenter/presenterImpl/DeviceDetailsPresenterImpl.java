package com.jch.racWiFi.userOnboarding.presenter.presenterImpl;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.google.gson.Gson;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch.racWiFi.userOnboarding.network.OnboardingNetworkHelper;
import com.jch.racWiFi.userOnboarding.presenter.DeviceDetailsPresenter;
import com.jch.racWiFi.userOnboarding.view.DeviceDetailsView;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class DeviceDetailsPresenterImpl implements DeviceDetailsPresenter {
    /* access modifiers changed from: private */
    public DeviceDetailsView deviceDetailsView;

    public DeviceDetailsPresenterImpl(DeviceDetailsView deviceDetailsView2) {
        this.deviceDetailsView = deviceDetailsView2;
    }

    public void unBoardIdu(Fragment fragment, int i, long j) {
        OnboardingNetworkHelper.getInstance().requestIduRemoval(i, Long.valueOf(j)).observe(fragment, new Observer<Response<ResponseBody>>() {
            public void onChanged(Response<ResponseBody> response) {
                if (response == null) {
                    DeviceDetailsPresenterImpl.this.deviceDetailsView.onDeviceNotRemoved(10009, (GenericResponse.GenericErrorBody) null);
                } else if (response.code() == 200) {
                    DeviceDetailsPresenterImpl.this.deviceDetailsView.onDeviceRemoved();
                } else {
                    DeviceDetailsPresenterImpl.this.deviceDetailsView.onDeviceNotRemoved(response.code(), (GenericResponse.GenericErrorBody) new Gson().fromJson(response.errorBody().charStream(), GenericResponse.GenericErrorBody.class));
                }
            }
        });
    }

    public void renameThing(Fragment fragment, IduDetailsModel iduDetailsModel, String str) {
        iduDetailsModel.setName(str);
        OnboardingNetworkHelper.getInstance().requestIduRenaming(iduDetailsModel).observe(fragment, new DeviceDetailsPresenterImpl$$ExternalSyntheticLambda0(this, iduDetailsModel));
    }

    /* renamed from: lambda$renameThing$0$com-jch-racWiFi-userOnboarding-presenter-presenterImpl-DeviceDetailsPresenterImpl */
    public /* synthetic */ void mo33465x6f2fc7a(IduDetailsModel iduDetailsModel, Response response) {
        if (response == null || !response.isSuccessful()) {
            this.deviceDetailsView.onRenamingFailed(response);
        } else {
            this.deviceDetailsView.onDeviceRenamed(iduDetailsModel.getName());
        }
    }
}
