package com.jch.racWiFi.iduOnBoarding.common.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.databinding.SelectAirConditionerTypeSelectionNewBinding;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;
import com.jch.racWiFi.userOnboarding.model.RacTypeEnum;
import com.jch_hitachi.aircloudglobal.R;

public class SelectDeviceTypeFragment extends GenericFragment {
    private final String TAG = getClass().getSimpleName();
    private SelectAirConditionerTypeSelectionNewBinding mBinding;
    private Bundle mBundle;
    private RacTypeEnum mRacType = RacTypeEnum.BUILTIN_WIRELESS;
    private final Observer<SwipeScreenType> mSwipeScreenTypeObserver = new SelectDeviceTypeFragment$$ExternalSyntheticLambda3(this);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (SelectAirConditionerTypeSelectionNewBinding) DataBindingUtil.inflate(layoutInflater, R.layout.select_air_conditioner_type_selection_new, viewGroup, false);
        this.mBundle = getArguments();
        updateStep();
        return this.mBinding.getRoot();
    }

    private void updateStep() {
        Object defaultValue;
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (navArgument != null && (defaultValue = navArgument.getDefaultValue()) != null && !((Boolean) defaultValue).booleanValue()) {
            updateProgress(this.mBinding.includeLinearProgressIndicator.linearProgressIndicator, 7, 2);
            this.mBinding.includeLinearProgressIndicator.textViewStep1.setText(getString(R.string.common_lbl_step2));
        }
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.layoutWithBuiltInWirelessUnit.setOnClickListener(new SelectDeviceTypeFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.layoutWithWithExternalWirelessAdapter.setOnClickListener(new SelectDeviceTypeFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.backButton.setOnClickListener(new SelectDeviceTypeFragment$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-iduOnBoarding-common-view-SelectDeviceTypeFragment */
    public /* synthetic */ void mo31480x673aeab9(View view) {
        onClickBuiltin();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-iduOnBoarding-common-view-SelectDeviceTypeFragment */
    public /* synthetic */ void mo31481x3a8e718(View view) {
        onClickExternal();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-iduOnBoarding-common-view-SelectDeviceTypeFragment */
    public /* synthetic */ void mo31482xa016e377(View view) {
        goBack();
    }

    /* access modifiers changed from: package-private */
    public void onClickBuiltin() {
        this.mRacType = RacTypeEnum.BUILTIN_WIRELESS;
        QRDetailsModel.CURRENT_RAC_DETAILS.setRacTypeEnum(this.mRacType);
        Bundle bundle = new Bundle();
        bundle.putSerializable(RacTypeEnum.RAC_TYPE_KEY, this.mRacType);
        Bundle bundle2 = this.mBundle;
        if (bundle2 != null) {
            bundle.putLong(Constants.Keys.ENTRY_TIME, bundle2.getLong(Constants.Keys.ENTRY_TIME));
        }
        WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setAdapterType(StatusCode.BUILTIN_WIRELESS);
        bundle.putString(Constants.Keys.ADAPTER_TYPE, StatusCode.BUILTIN_WIRELESS);
        handleGRACUpdate();
    }

    private void handleGRACUpdate() {
        WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setAdapterType("2");
        RacTypeEnum racTypeEnum = RacTypeEnum.INDIAN_MODEL;
        QRDetailsModel.CURRENT_RAC_DETAILS.setRacTypeEnum(racTypeEnum);
        Bundle bundle = new Bundle();
        if (getArguments() != null) {
            bundle.putLong(Constants.Keys.ENTRY_TIME, getArguments().getLong(Constants.Keys.ENTRY_TIME));
        }
        bundle.putSerializable(RacTypeEnum.RAC_TYPE_KEY, racTypeEnum);
        bundle.putString(Constants.Keys.ADAPTER_TYPE, "2");
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_selectDeviceTypeFragment_to_confirmWirelessNetworkIndiaFrag, bundle);
        logEvents(Events.DEVICE_TYPE_SELECTION_BUILT_IN.name(), 0);
    }

    /* access modifiers changed from: package-private */
    public void onClickExternal() {
        this.mRacType = RacTypeEnum.EXTERNAL;
        QRDetailsModel.CURRENT_RAC_DETAILS.setRacTypeEnum(this.mRacType);
        Bundle bundle = new Bundle();
        bundle.putSerializable(RacTypeEnum.RAC_TYPE_KEY, this.mRacType);
        Bundle bundle2 = this.mBundle;
        if (bundle2 != null) {
            bundle.putLong(Constants.Keys.ENTRY_TIME, bundle2.getLong(Constants.Keys.ENTRY_TIME));
        }
        WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.setAdapterType("1");
        bundle.putString(Constants.Keys.ADAPTER_TYPE, "1");
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_selectDeviceTypeFragment_to_addDeviceFragment, bundle);
        logEvents(Events.DEVICE_TYPE_SELECTION_ADAPTER_MODEL.name(), 0);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.common.view.SelectDeviceTypeFragment$1 */
    static /* synthetic */ class C22331 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.common.view.SelectDeviceTypeFragment.C22331.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$3$com-jch-racWiFi-iduOnBoarding-common-view-SelectDeviceTypeFragment */
    public /* synthetic */ void mo31479x2b197d1a(SwipeScreenType swipeScreenType) {
        String str = this.TAG;
        Logger.m45d(str, "type = " + swipeScreenType.name());
        if (C22331.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()] == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }
}
