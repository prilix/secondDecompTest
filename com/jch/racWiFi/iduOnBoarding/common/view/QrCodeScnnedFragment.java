package com.jch.racWiFi.iduOnBoarding.common.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.databinding.QrCodeScannedFrameBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.HomeDetails;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoResponseBody;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.presenter.OnBoardedIduPresenter;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.OnBoardedIduInfoView;
import com.jch.racWiFi.iduOnBoarding.ViewModel.OnBoardingViewModel;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.model.RacTypeEnum;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Iterator;
import retrofit2.Response;

public class QrCodeScnnedFragment extends GenericFragment implements OnBoardedIduInfoView {
    private QrCodeScannedFrameBinding mBinding;
    private Handler mHandler;
    private OnBoardedIduPresenter onBoardedIduPresenter;
    private OnBoardingViewModel onBoardingViewModel;

    private void showDebugToast(String str) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.onBoardedIduPresenter = new OnBoardedIduPresenter(this);
        this.onBoardingViewModel = getCoreActivity().getGlobalViewModelRepository().getOnBoardingViewModel();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        QrCodeScannedFrameBinding qrCodeScannedFrameBinding = (QrCodeScannedFrameBinding) DataBindingUtil.inflate(layoutInflater, R.layout.qr_code_scanned_frame, viewGroup, false);
        this.mBinding = qrCodeScannedFrameBinding;
        qrCodeScannedFrameBinding.include.includeLinearProgressIndicator.textViewStep1.setText(R.string.common_lbl_step1);
        Handler handler = new Handler();
        this.mHandler = handler;
        handler.postDelayed(new QrCodeScnnedFragment$$ExternalSyntheticLambda1(this), 3000);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.backButton.setOnClickListener(new QrCodeScnnedFragment$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-iduOnBoarding-common-view-QrCodeScnnedFragment */
    public /* synthetic */ void mo31444x10709d6a(View view) {
        goBack();
    }

    public void onDestroyView() {
        super.onDestroyView();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: private */
    public void moveForward() {
        if (Constants.IS_DEMO_MODE) {
            screenNavigation();
            return;
        }
        String ssid = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid();
        if (ssid.isEmpty()) {
            showDebugToast("Vendor Thing Id is null");
            this.mFragmentToActivityCallback.getNavController().navigateUp();
            return;
        }
        WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setSsid(ssid);
        if (isIduAlreadyOnboarded(ssid)) {
            Bundle arguments = getArguments();
            if (arguments == null) {
                arguments = new Bundle();
            }
            arguments.putString("vendorThingId", ssid);
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.acAddedInSameHomeFragment, getArguments());
            return;
        }
        getIduInfo(ssid);
    }

    private boolean isIduAlreadyOnboarded(String str) {
        String replaceAll = str.replaceAll("^\"|\"$", "");
        IduList iduList = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList();
        if (iduList == null) {
            return false;
        }
        Iterator it = iduList.iterator();
        while (it.hasNext()) {
            if (((DetailedIduModel) it.next()).getVendorThingId().equalsIgnoreCase(replaceAll)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void getIduInfo(String str) {
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            showPleaseWaitDialog();
            this.onBoardedIduPresenter.getOnBoardedIduInfo(getActivity(), str);
            return;
        }
        showErrorPopUp(getString(R.string.common_alert_unableToConnectToNw));
    }

    public void getOnBoardedIduInfo(Response<OnboardingInfoResponseBody> response) {
        dismissPleaseWaitDialog();
        if (response == null) {
            showErrorPopUp(getString(R.string.onboard_alert_unableToGetIduDetail, WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid()));
        } else if (response.body() != null) {
            if (response.isSuccessful()) {
                handleOnboardedIduInfo(response.body());
                return;
            }
            showErrorPopUp(getString(R.string.onboard_alert_unableToGetIduDetail, WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid()));
        } else if (response.errorBody() != null) {
            handleErrorIduInfoResponse(response.code());
        } else {
            showErrorPopUp(getString(R.string.onboard_alert_unableToGetIduDetail, WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid()));
        }
    }

    private void handleOnboardedIduInfo(OnboardingInfoResponseBody onboardingInfoResponseBody) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            ArrayList<HomeDetails> homes = onboardingInfoResponseBody.getHomes();
            if (homes == null || homes.isEmpty()) {
                screenNavigation();
                return;
            }
            showDebugToast(onboardingInfoResponseBody.getRouterSSID());
            HomeDetails homeDetails = homes.get(0);
            if (homeDetails != null) {
                homeDetails.setVendorThingId(onboardingInfoResponseBody.getVendorThingId());
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.Intents.RAC_HOME_DETAIL, homeDetails);
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_qrCodeScanned_to_acAddedInOtherFragment, bundle);
                return;
            }
            screenNavigation();
        }
    }

    private void handleErrorIduInfoResponse(int i) {
        if (i == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    QrCodeScnnedFragment.this.getIduInfo(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid());
                }
            }, false);
            return;
        }
        showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
    }

    private void screenNavigation() {
        Bundle arguments = getArguments();
        if (this.mFragmentToActivityCallback.getNavController() != null) {
            RacTypeEnum racTypeEnum = null;
            if (arguments != null) {
                racTypeEnum = (RacTypeEnum) arguments.getSerializable(RacTypeEnum.RAC_TYPE_KEY);
            }
            if (racTypeEnum == null || !racTypeEnum.equals(RacTypeEnum.INDIAN_MODEL)) {
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_qrCodeScanned_to_addDeviceFragment, arguments);
            } else {
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.to_confirmWirelessNetworkIndiaFrag, arguments);
            }
        }
    }
}
