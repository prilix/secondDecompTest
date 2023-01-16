package com.jch.racWiFi.Presenter;

import androidx.lifecycle.LifecycleOwner;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.NetworkDispatch.PrivacyPolicyNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;

public class PrivacyPolicyPresenter extends AbstractPresenter {
    private IPrivacyPolicyPresenter iPrivacyPolicyPresenter;

    public interface IPrivacyPolicyPresenter extends INetworkConnectivity {
        void onPrivacyPolicyReceived(PrivacyPolicyModel.PrivacyPolicyDataResponse privacyPolicyDataResponse, int i);

        void onPrivacyPolicyReceivedFailure();
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public PrivacyPolicyPresenter(IPrivacyPolicyPresenter iPrivacyPolicyPresenter2) {
        this.iPrivacyPolicyPresenter = iPrivacyPolicyPresenter2;
    }

    public void checkForPrivacyPolicyUpdate(LifecycleOwner lifecycleOwner) {
        new PrivacyPolicyNetworkDispatcher().checkForPrivacyPolicyUpdate().observeSingleEvent(lifecycleOwner, new PrivacyPolicyPresenter$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$checkForPrivacyPolicyUpdate$0$com-jch-racWiFi-Presenter-PrivacyPolicyPresenter */
    public /* synthetic */ void mo27704x70f0a4d4(GenericResponse genericResponse) {
        if (this.iPrivacyPolicyPresenter != null) {
            if (genericResponse.unableToReachServer()) {
                this.iPrivacyPolicyPresenter.onNetworkCallFailure(genericResponse.getThrowable());
            } else if (!genericResponse.isApiSuccessful()) {
                this.iPrivacyPolicyPresenter.onPrivacyPolicyReceivedFailure();
            } else if (genericResponse.getResponse().body() != null) {
                PrivacyPolicyModel.PrivacyPolicyDataResponse privacyPolicyDataResponse = (PrivacyPolicyModel.PrivacyPolicyDataResponse) genericResponse.getBodyOfType(PrivacyPolicyModel.PrivacyPolicyDataResponse.class);
                if (privacyPolicyDataResponse != null) {
                    int i = 0;
                    while (true) {
                        if (i >= privacyPolicyDataResponse.privacyPolicyData.length) {
                            break;
                        }
                        PrivacyPolicyModel.PrivacyPolicyData privacyPolicyData = privacyPolicyDataResponse.privacyPolicyData[i];
                        String languageCodeForCurrentLocale = LocaleConfiguration.getLanguageCodeForCurrentLocale();
                        if (privacyPolicyData.language.equalsIgnoreCase(languageCodeForCurrentLocale.toLowerCase() + "_tc") || privacyPolicyData.language.equalsIgnoreCase("en_tc")) {
                            privacyPolicyDataResponse.privacyPolicyData[i].persist();
                        } else {
                            i++;
                        }
                    }
                    privacyPolicyDataResponse.privacyPolicyData[i].persist();
                }
                this.iPrivacyPolicyPresenter.onPrivacyPolicyReceived(privacyPolicyDataResponse, genericResponse.getResponse().code());
            } else {
                this.iPrivacyPolicyPresenter.onPrivacyPolicyReceived((PrivacyPolicyModel.PrivacyPolicyDataResponse) null, genericResponse.getResponse().code());
            }
        }
    }

    public void removeCallbacks() {
        this.iPrivacyPolicyPresenter = null;
    }
}
