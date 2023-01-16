package com.jch.racWiFi.iduOnBoarding.wps.withoutQrCode.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Utils.SwipeScreenType;
import com.jch.racWiFi.userOnboarding.model.RacTypeEnum;
import com.jch_hitachi.aircloudglobal.R;

public class EnableWpsOnRacWithoutQrFrag extends GenericFragment {
    private Activity mActivity;
    @BindView(2131363530)
    View mParentView;
    private Observer<SwipeScreenType> mSwipeScreenTypeObserver = new EnableWpsOnRacWithoutQrFrag$$ExternalSyntheticLambda0(this);
    private Unbinder mUnbinder;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view;
        Bundle arguments = getArguments();
        if (arguments != null) {
            RacTypeEnum racTypeEnum = (RacTypeEnum) arguments.getSerializable(RacTypeEnum.RAC_TYPE_KEY);
            if (racTypeEnum == null) {
                view = layoutInflater.inflate(R.layout.enable_wps_built_in_step_3_of_4_frame, viewGroup, false);
            } else if (racTypeEnum.equals(RacTypeEnum.BUILTIN_WIRELESS)) {
                view = layoutInflater.inflate(R.layout.enable_wps_built_in_step_3_of_4_frame, viewGroup, false);
            } else {
                view = layoutInflater.inflate(R.layout.enable_wps_adapter_step_3_of_4_frame_new, viewGroup, false);
            }
        } else {
            view = layoutInflater.inflate(R.layout.enable_wps_built_in_step_3_of_4_frame, viewGroup, false);
        }
        this.mUnbinder = ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onStart() {
        super.onStart();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.mSwipeScreenTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mFragmentToActivityCallback.getScreenSwipeSingleLiveEvent().removeObserver(this.mSwipeScreenTypeObserver);
    }

    @OnClick({2131362078})
    public void goBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362469})
    public void goNext() {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_enableWpsOnRacWithoutQrFrag_to_enableWpsOnHomeRouterFragment);
    }

    public void onDestroyView() {
        super.onDestroyView();
        Unbinder unbinder = this.mUnbinder;
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /* renamed from: com.jch.racWiFi.iduOnBoarding.wps.withoutQrCode.view.EnableWpsOnRacWithoutQrFrag$1 */
    static /* synthetic */ class C22641 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduOnBoarding.wps.withoutQrCode.view.EnableWpsOnRacWithoutQrFrag.C22641.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-iduOnBoarding-wps-withoutQrCode-view-EnableWpsOnRacWithoutQrFrag */
    public /* synthetic */ void mo31581x8318999a(SwipeScreenType swipeScreenType) {
        Logger.m45d(GenericFragment.TAG, "type = " + swipeScreenType.name());
        int i = C22641.$SwitchMap$com$jch$racWiFi$Utils$SwipeScreenType[swipeScreenType.ordinal()];
        if (i == 1) {
            goNext();
        } else if (i == 2) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }
}
