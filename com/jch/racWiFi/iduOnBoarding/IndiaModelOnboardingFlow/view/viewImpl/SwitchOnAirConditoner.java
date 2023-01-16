package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.navigation.NavArgument;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.databinding.OnboardingStep2Of4IndiaBinding;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;

public class SwitchOnAirConditoner extends GenericFragment {
    private OnboardingStep2Of4IndiaBinding mBinding;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new SwitchOnAirConditoner$$ExternalSyntheticLambda2(this);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (OnboardingStep2Of4IndiaBinding) DataBindingUtil.inflate(layoutInflater, R.layout.onboarding_step_2_of_4_india, viewGroup, false);
        updateStep();
        return this.mBinding.getRoot();
    }

    private void updateStep() {
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.QR_SCAN_SUCCESS);
        if (navArgument != null && navArgument.getDefaultValue() != null) {
            boolean booleanValue = ((Boolean) navArgument.getDefaultValue()).booleanValue();
            Bundle arguments = getArguments();
            if (arguments == null) {
                return;
            }
            if (booleanValue) {
                handleQRSuccess(arguments);
            } else {
                handleQRFail(arguments);
            }
        }
    }

    private void handleQRFail(Bundle bundle) {
        String string = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string.hashCode();
        if (string.equals("2")) {
            updateProgress(this.mBinding.includeLinearProgressIndicator.linearProgressIndicator, 6, 4);
            this.mBinding.includeLinearProgressIndicator.textViewStep1.setText(getString(R.string.common_lbl_step4));
        }
    }

    private void handleQRSuccess(Bundle bundle) {
        String string = bundle.getString(Constants.Keys.ADAPTER_TYPE);
        string.hashCode();
        if (string.equals("2")) {
            updateProgress(this.mBinding.includeLinearProgressIndicator.linearProgressIndicator, 4, 3);
            this.mBinding.includeLinearProgressIndicator.textViewStep1.setText(getString(R.string.common_lbl_step3));
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.forwardButton.setOnClickListener(new SwitchOnAirConditoner$$ExternalSyntheticLambda0(this));
        this.mBinding.backButton.setOnClickListener(new SwitchOnAirConditoner$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-SwitchOnAirConditoner */
    public /* synthetic */ void mo31364x1f548cd5(View view) {
        goNext();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-SwitchOnAirConditoner */
    public /* synthetic */ void mo31365x1e7b1c34(View view) {
        goBack();
    }

    /* access modifiers changed from: package-private */
    public void goNext() {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_switchOnAirConditionerFrag_to_addRac, getArguments());
    }

    /* access modifiers changed from: package-private */
    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onStart() {
        super.onStart();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                SwitchOnAirConditoner.this.goBack();
            }
        });
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl.SwitchOnAirConditoner$2 */
    static /* synthetic */ class C22042 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl.SwitchOnAirConditoner.C22042.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$2$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-view-viewImpl-SwitchOnAirConditoner */
    public /* synthetic */ void mo31363x4c43bcd7(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        int i = C22042.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            goNext();
        } else if (i == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }
}
