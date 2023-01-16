package com.jch.racWiFi.iduOnBoarding.common.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.FragmentUnableToScanQrSsidBinding;
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
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import retrofit2.Response;

public class UnableToScanQrSsidFragment extends GenericFragment implements View.OnClickListener, OnBoardedIduInfoView {
    private FragmentUnableToScanQrSsidBinding mBinding;
    private boolean mIsHasFocusEnterSsid;
    private long mStartTimeEnterSsid;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new UnableToScanQrSsidFragment$$ExternalSyntheticLambda6(this);
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
        FragmentUnableToScanQrSsidBinding fragmentUnableToScanQrSsidBinding = (FragmentUnableToScanQrSsidBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_unable_to_scan_qr_ssid, viewGroup, false);
        this.mBinding = fragmentUnableToScanQrSsidBinding;
        return fragmentUnableToScanQrSsidBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.backButton.setOnClickListener(this);
        this.mBinding.forwardButton.setOnClickListener(this);
        this.mBinding.buttonWhereToFindSsidName.setOnClickListener(this);
        this.mBinding.editTextEnterSsid.setOnFocusChangeListener(new UnableToScanQrSsidFragment$$ExternalSyntheticLambda5(this));
        updateStep();
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-iduOnBoarding-common-view-UnableToScanQrSsidFragment */
    public /* synthetic */ void mo31507x3e58f1b4(View view, boolean z) {
        if (!z) {
            logEvents(Events.SS_ID_MANUAL.name(), 0);
        } else if (!this.mIsHasFocusEnterSsid) {
            this.mIsHasFocusEnterSsid = true;
            this.mStartTimeEnterSsid = System.currentTimeMillis();
        }
    }

    private void updateStep() {
        Object defaultValue;
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (navArgument != null && (defaultValue = navArgument.getDefaultValue()) != null && !((Boolean) defaultValue).booleanValue()) {
            this.mBinding.includeLinearProgressIndicator.textViewStep1.setText(getString(R.string.common_lbl_step1));
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_button) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (id == R.id.button_where_to_find_ssid_name) {
            locateSsid();
        } else if (id == R.id.forward_button) {
            moveForward();
        }
    }

    private void moveForward() {
        Editable text = this.mBinding.editTextEnterSsid.getText();
        Objects.requireNonNull(text);
        Editable editable = text;
        String obj = text.toString();
        if (obj.isEmpty()) {
            showErrorPopUp(getString(R.string.onboard_alert_pleaseEnterRACSSID));
            return;
        }
        WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setSsid(obj);
        if (Constants.IS_DEMO_MODE) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_unableToScanQrSsidFragment_to_selectDeviceTypeFragment);
        } else if (isIduAlreadyOnboarded(obj)) {
            new Bundle().putString("vendorThingId", obj);
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_unableToScanQrSsidFragment_to_acAddedInSameHomeFragment);
        } else {
            getIduInfo(obj);
        }
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
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_unableToScanQrSsidFragment_to_selectDeviceTypeFragment, getArguments());
                return;
            }
            showDebugToast(onboardingInfoResponseBody.getRouterSSID());
            HomeDetails homeDetails = homes.get(0);
            if (homeDetails != null) {
                homeDetails.setVendorThingId(onboardingInfoResponseBody.getVendorThingId());
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.Intents.RAC_HOME_DETAIL, homeDetails);
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_unableToScanQrSsidFragment_to_acAddedInOtherFragment, bundle);
                return;
            }
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_unableToScanQrSsidFragment_to_selectDeviceTypeFragment, getArguments());
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
                UnableToScanQrSsidFragment.this.getIduInfo(WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getSsid());
            }
        }, false);
    }

    private void locateSsid() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setCancelable(true).setView(R.layout.dialog_where_to_find_ssid);
        AlertDialog create = builder.create();
        create.setOnShowListener(new UnableToScanQrSsidFragment$$ExternalSyntheticLambda1(this));
        create.show();
        create.setOnDismissListener(new UnableToScanQrSsidFragment$$ExternalSyntheticLambda0(this));
        WindowManager.LayoutParams attributes = create.getWindow().getAttributes();
        attributes.dimAmount = 0.2f;
        create.getWindow().setAttributes(attributes);
        create.getWindow().addFlags(2);
        View decorView = create.getWindow().getDecorView();
        ConstraintLayout constraintLayout = (ConstraintLayout) decorView.findViewById(R.id.layout_built_in);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) decorView.findViewById(R.id.layout_adapter);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) decorView.findViewById(R.id.layout_adapter_content);
        ConstraintLayout constraintLayout4 = constraintLayout2;
        ImageView imageView = (ImageView) decorView.findViewById(R.id.image_view_arrow_drop_down_red_adapter);
        TextView textView = (TextView) decorView.findViewById(R.id.text_view_adapter);
        ImageView imageView2 = (ImageView) decorView.findViewById(R.id.image_view_adapter);
        ConstraintLayout constraintLayout5 = constraintLayout;
        ConstraintLayout constraintLayout6 = (ConstraintLayout) decorView.findViewById(R.id.layout_built_in_content);
        ImageView imageView3 = (ImageView) decorView.findViewById(R.id.image_view_arrow_drop_down_red_built_in);
        UnableToScanQrSsidFragment$$ExternalSyntheticLambda3 unableToScanQrSsidFragment$$ExternalSyntheticLambda3 = r0;
        TextView textView2 = (TextView) decorView.findViewById(R.id.text_view_built_in);
        ImageButton imageButton = (ImageButton) decorView.findViewById(R.id.image_button_clear);
        ImageView imageView4 = (ImageView) decorView.findViewById(R.id.image_view_built_in);
        UnableToScanQrSsidFragment$$ExternalSyntheticLambda3 unableToScanQrSsidFragment$$ExternalSyntheticLambda32 = new UnableToScanQrSsidFragment$$ExternalSyntheticLambda3(this, constraintLayout3, constraintLayout4, imageView, textView, imageView2, constraintLayout5, constraintLayout6, imageView3, textView2, imageView4);
        constraintLayout2.setOnClickListener(unableToScanQrSsidFragment$$ExternalSyntheticLambda3);
        constraintLayout.setOnClickListener(new UnableToScanQrSsidFragment$$ExternalSyntheticLambda4(this, constraintLayout3, constraintLayout4, imageView, textView, imageView2, constraintLayout5, constraintLayout6, imageView3, textView2, imageView4));
        imageButton.setOnClickListener(new UnableToScanQrSsidFragment$$ExternalSyntheticLambda2(create));
    }

    /* renamed from: lambda$locateSsid$1$com-jch-racWiFi-iduOnBoarding-common-view-UnableToScanQrSsidFragment */
    public /* synthetic */ void mo31502xf1506110(DialogInterface dialogInterface) {
        this.mBinding.parent.setBackgroundResource(R.drawable.white_blur);
    }

    /* renamed from: lambda$locateSsid$2$com-jch-racWiFi-iduOnBoarding-common-view-UnableToScanQrSsidFragment */
    public /* synthetic */ void mo31503x2a30c1af(DialogInterface dialogInterface) {
        this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
    }

    /* renamed from: lambda$locateSsid$3$com-jch-racWiFi-iduOnBoarding-common-view-UnableToScanQrSsidFragment */
    public /* synthetic */ void mo31504x6311224e(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView, ImageView imageView2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ImageView imageView3, TextView textView2, ImageView imageView4, View view) {
        constraintLayout.setVisibility(0);
        constraintLayout2.setBackgroundColor(getResources().getColor(R.color.color_lightest_grey));
        imageView.setVisibility(0);
        textView.setTextColor(getResources().getColor(R.color.colorRed));
        imageView2.setImageResource(R.drawable.ic_locate_qr_adapter_red);
        constraintLayout3.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        constraintLayout4.setVisibility(4);
        imageView3.setVisibility(4);
        textView2.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
        imageView4.setImageResource(R.drawable.ic_built_in_grey);
    }

    /* renamed from: lambda$locateSsid$4$com-jch-racWiFi-iduOnBoarding-common-view-UnableToScanQrSsidFragment */
    public /* synthetic */ void mo31505x9bf182ed(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView, ImageView imageView2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ImageView imageView3, TextView textView2, ImageView imageView4, View view) {
        constraintLayout.setVisibility(4);
        constraintLayout2.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        imageView.setVisibility(4);
        textView.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
        imageView2.setImageResource(R.drawable.ic_locate_qr_adapter_grey);
        constraintLayout3.setBackgroundColor(getResources().getColor(R.color.color_lightest_grey));
        constraintLayout4.setVisibility(0);
        imageView3.setVisibility(0);
        textView2.setTextColor(getResources().getColor(R.color.colorRed));
        imageView4.setImageResource(R.drawable.ic_built_in_red);
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.common.view.UnableToScanQrSsidFragment$2 */
    static /* synthetic */ class C22362 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.jch.racWiFi.Utils.SwipeScreenType[] r0 = com.jch.racWiFi.Utils.SwipeScreenType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType = r0
                com.jch.racWiFi.Utils.SwipeScreenType r1 = com.jch.racWiFi.Utils.SwipeScreenType.LEFT_SWIPE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.Utils.SwipeScreenType r1 = com.jch.racWiFi.Utils.SwipeScreenType.RIGHT_SWIPE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.common.view.UnableToScanQrSsidFragment.C22362.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$6$com-jch-racWiFi-iduOnBoarding-common-view-UnableToScanQrSsidFragment */
    public /* synthetic */ void mo31506x2f7d91b2(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        int i = C22362.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            moveForward();
        } else if (i == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }
}
