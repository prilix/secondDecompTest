package com.jch.racWiFi.iduOnBoarding.common.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgument;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.jch.racWiFi.GenericDialogFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.databinding.DialogAcAlreadyAddedOtherHomeBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.HomeDetails;
import com.jch.racWiFi.iduOnBoarding.ViewModel.OnBoardingViewModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;
import com.jch.racWiFi.userOnboarding.model.RacTypeEnum;
import com.jch_hitachi.aircloudglobal.R;

public class AcAddedInOtherFragment extends GenericDialogFragment implements View.OnClickListener {
    private HomeDetails homeDetail;
    private DialogAcAlreadyAddedOtherHomeBinding mBinding;
    private OnBoardingViewModel onBoardingViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.onBoardingViewModel = getCoreActivity().getGlobalViewModelRepository().getOnBoardingViewModel();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DialogAcAlreadyAddedOtherHomeBinding dialogAcAlreadyAddedOtherHomeBinding = (DialogAcAlreadyAddedOtherHomeBinding) DataBindingUtil.inflate(layoutInflater, R.layout.dialog_ac_already_added_other_home, viewGroup, false);
        this.mBinding = dialogAcAlreadyAddedOtherHomeBinding;
        return dialogAcAlreadyAddedOtherHomeBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.buttonQuitParingProcess.setOnClickListener(this);
        this.mBinding.buttonResetAc.setOnClickListener(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            HomeDetails homeDetails = (HomeDetails) arguments.get(Constants.Intents.RAC_HOME_DETAIL);
            this.homeDetail = homeDetails;
            if (homeDetails != null) {
                String familyName = homeDetails.getFamilyName();
                String creatorProfilePicUrl = this.homeDetail.getCreatorProfilePicUrl();
                if (!isNullOrEmpty(familyName)) {
                    this.mBinding.textViewUserNameManageUser.setText(familyName);
                    this.mBinding.textViewUserTypeManageUser.setText(getString(R.string.common_lbl_owner));
                }
                if (!isNullOrEmpty(creatorProfilePicUrl)) {
                    ((RequestBuilder) Glide.with((Fragment) this).load(creatorProfilePicUrl).circleCrop()).into((ImageView) this.mBinding.imageViewUserProfileManageUser);
                }
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_quit_paring_process) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_global_homePageFragment);
        } else if (id == R.id.button_reset_ac) {
            resetIduFromAllOtherFamily();
        }
    }

    /* access modifiers changed from: private */
    public void resetIduFromAllOtherFamily() {
        if (this.homeDetail == null) {
            return;
        }
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            showPleaseWaitDialog();
            this.onBoardingViewModel.resetIdu(this.homeDetail.getVendorThingId(), FamilyGroupList.getCurrentFamily().familyId).observeWithCachedTrigger(getViewLifecycleOwner(), new AcAddedInOtherFragment$$ExternalSyntheticLambda0(this));
            return;
        }
        showErrorPopUp(getString(R.string.common_alert_unableToConnectToNw));
    }

    /* renamed from: lambda$resetIduFromAllOtherFamily$0$com-jch-racWiFi-iduOnBoarding-common-dialog-AcAddedInOtherFragment */
    public /* synthetic */ void mo31375xe15125f9(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse == null) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.unableToReachServer()) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.isApiSuccessful()) {
            this.mFragmentToActivityCallback.getNavController().navigate(getScreenNavigationId());
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
                        AcAddedInOtherFragment.this.resetIduFromAllOtherFamily();
                    }
                }, false);
            }
        }
    }

    private int getScreenNavigationId() {
        RacTypeEnum racTypeEnum;
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (((navArgument == null || getArguments() == null) ? false : ((Boolean) navArgument.getDefaultValue()).booleanValue()) && (racTypeEnum = QRDetailsModel.CURRENT_RAC_DETAILS.getRacTypeEnum()) != null) {
            int i = C22062.$SwitchMap$com$jch$racWiFi$userOnboarding$model$RacTypeEnum[racTypeEnum.ordinal()];
            if (i == 1) {
                return R.id.confirmWirelessNetworkIndianFrag;
            }
            if (i == 2 || i == 3) {
                return R.id.selectWpsOrApMethodFragment;
            }
        }
        return R.id.selectDeviceTypeFragment;
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.common.dialog.AcAddedInOtherFragment$2 */
    static /* synthetic */ class C22062 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.common.dialog.AcAddedInOtherFragment.C22062.<clinit>():void");
        }
    }
}
