package com.jch.racWiFi.iduOnBoarding.common.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavArgument;
import com.jch.racWiFi.GenericDialogFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.databinding.DialogAcAlreadyAddedSameHomeBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.HomeDetails;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoResponseBody;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.presenter.OnBoardedIduPresenter;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.OnBoardedIduInfoView;
import com.jch.racWiFi.iduOnBoarding.ViewModel.OnBoardingViewModel;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;
import com.jch.racWiFi.userOnboarding.model.RacTypeEnum;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Objects;
import retrofit2.Response;

public class AcAddedInSameHomeFragment extends GenericDialogFragment implements View.OnClickListener, OnBoardedIduInfoView {
    private DialogAcAlreadyAddedSameHomeBinding mBinding;
    private OnBoardedIduPresenter onBoardedIduPresenter;
    private OnBoardingViewModel onBoardingViewModel;
    private String vendorThingId;

    private void showDebugToast(String str) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.onBoardedIduPresenter = new OnBoardedIduPresenter(this);
        this.onBoardingViewModel = getCoreActivity().getGlobalViewModelRepository().getOnBoardingViewModel();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogAcAlreadyAddedSameHomeBinding) DataBindingUtil.inflate(layoutInflater, R.layout.dialog_ac_already_added_same_home, viewGroup, false);
        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                AcAddedInSameHomeFragment.this.getDialog().getWindow().setLayout(-1, -1);
                DisplayMetrics displayMetrics = AcAddedInSameHomeFragment.this.requireActivity().getResources().getDisplayMetrics();
                int i = (int) (((double) displayMetrics.heightPixels) * 0.95d);
                AcAddedInSameHomeFragment.this.getDialog().getWindow().setLayout((int) (((double) displayMetrics.widthPixels) * 0.98d), i);
            }
        });
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.buttonQuitParingProcess.setOnClickListener(this);
        this.mBinding.buttonPairWithAnotherWirelessNw.setOnClickListener(this);
        Dialog dialog = getDialog();
        Objects.requireNonNull(dialog);
        Dialog dialog2 = dialog;
        dialog.setCancelable(false);
        String str = UserInfo.getCurrentUserInfo(getCoreActivity()).familyName;
        ProfilePicture.loadIntoImageView(this.mBinding.imageViewUserProfileManageUser, UserInfo.getCurrentUserInfo(getCoreActivity()).profilePicture);
        if (!isNullOrEmpty(str)) {
            this.mBinding.textViewUserNameManageUser.setText(str);
            this.mBinding.textViewUserTypeManageUser.setText(getString(R.string.common_lbl_owner));
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.vendorThingId = arguments.getString("vendorThingId");
        } else {
            this.vendorThingId = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_pair_with_another_wirelessNw) {
            getIduInfo();
        } else if (id == R.id.button_quit_paring_process) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_global_homePageFragment);
        }
    }

    /* access modifiers changed from: private */
    public void getIduInfo() {
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            showPleaseWaitDialog();
            this.onBoardedIduPresenter.getOnBoardedIduInfo(getActivity(), this.vendorThingId);
            return;
        }
        showErrorPopUp(getString(R.string.common_alert_unableToConnectToNw));
    }

    public void getOnBoardedIduInfo(Response<OnboardingInfoResponseBody> response) {
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

    private void handleErrorIduInfoResponse(int i) {
        if (i != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                AcAddedInSameHomeFragment.this.getIduInfo();
            }
        }, false);
    }

    private void handleOnboardedIduInfo(OnboardingInfoResponseBody onboardingInfoResponseBody) {
        ArrayList<HomeDetails> homes;
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && (homes = onboardingInfoResponseBody.getHomes()) != null && !homes.isEmpty()) {
            String routerSSID = onboardingInfoResponseBody.getRouterSSID();
            showDebugToast(routerSSID);
            this.onBoardingViewModel.setAddedAcRouterSsid(routerSSID);
            this.onBoardingViewModel.setIduOnline(onboardingInfoResponseBody.isOnline());
            if (homes.size() > 1) {
                resetIduFromAllOtherFamily();
            } else if (FamilyGroupList.getCurrentFamily().familyId == homes.get(0).getFamilyId()) {
                dismissPleaseWaitDialog();
                this.mFragmentToActivityCallback.getNavController().navigate(getScreenNavigationId(), getArguments());
            } else {
                resetIduFromAllOtherFamily();
            }
        }
    }

    /* access modifiers changed from: private */
    public void resetIduFromAllOtherFamily() {
        if (isNullOrEmpty(this.vendorThingId)) {
            return;
        }
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            showPleaseWaitDialog();
            this.onBoardingViewModel.resetIdu(this.vendorThingId, FamilyGroupList.getCurrentFamily().familyId).observeWithCachedTrigger(getViewLifecycleOwner(), new AcAddedInSameHomeFragment$$ExternalSyntheticLambda0(this));
            return;
        }
        showErrorPopUp(getString(R.string.common_alert_unableToConnectToNw));
    }

    /* renamed from: lambda$resetIduFromAllOtherFamily$0$com-jch-racWiFi-iduOnBoarding-common-dialog-AcAddedInSameHomeFragment */
    public /* synthetic */ void mo31377x650428bc(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse == null) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.unableToReachServer()) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.isApiSuccessful()) {
            this.mFragmentToActivityCallback.getNavController().navigate(getScreenNavigationId(), getArguments());
        } else {
            GenericResponse.GenericErrorBody genericErrorBody = (GenericResponse.GenericErrorBody) genericResponse.getErrorBodyOfType(GenericResponse.GenericErrorBody.class);
            int code = genericResponse.getResponse().code();
            if (genericErrorBody == null) {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            } else if (code != 401) {
                showErrorPopUp(getString(this.onBoardingViewModel.getErrorMessageStringId(genericErrorBody.code)));
            } else {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        AcAddedInSameHomeFragment.this.resetIduFromAllOtherFamily();
                    }
                }, false);
            }
        }
    }

    private int getScreenNavigationId() {
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (!(navArgument != null ? ((Boolean) navArgument.getDefaultValue()).booleanValue() : false)) {
            return R.id.action_acAddedInSameHomeFragment_to_selectDeviceTypeFragment;
        }
        RacTypeEnum racTypeEnum = QRDetailsModel.CURRENT_RAC_DETAILS.getRacTypeEnum();
        if (racTypeEnum == null) {
            return R.id.action_acAddedInSameHomeFragment_to_addDeviceFragment;
        }
        if (C22104.$SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum[racTypeEnum.ordinal()] != 1) {
            return R.id.action_acAddedInSameHomeFragment_to_addDeviceFragment;
        }
        return R.id.action_acAddedInSameHomeFragment_to_confirmWirelessNetworkFrag;
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.common.dialog.AcAddedInSameHomeFragment$4 */
    static /* synthetic */ class C22104 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum[] r0 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum = r0
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum r1 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.INDIAN_MODEL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum r1 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.EXTERNAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.userOnboarding.model.RacTypeEnum r1 = com.jch.racWiFi.userOnboarding.model.RacTypeEnum.BUILTIN_WIRELESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.common.dialog.AcAddedInSameHomeFragment.C22104.<clinit>():void");
        }
    }
}
