package com.jch.racWiFi.iduOnBoarding.common.view;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.navigation.NavArgument;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch_hitachi.aircloudglobal.R;

public class OnboardingSuccessFragmentV2 extends GenericFragment {
    TextView desc;
    private Handler mHandler;
    TextView note;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        IduDetailsModel iduDetailsModel;
        View inflate = layoutInflater.inflate(R.layout.fragment_onboarding_success, viewGroup, false);
        this.desc = (TextView) inflate.findViewById(R.id.textView46);
        this.note = (TextView) inflate.findViewById(R.id.note);
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(Values.IDU_DETAILS_KEY);
        if (!(navArgument == null || (iduDetailsModel = (IduDetailsModel) navArgument.getDefaultValue()) == null)) {
            String name = iduDetailsModel.getName();
            this.desc.setText(Html.fromHtml(getString(R.string.onboard_lbl_racMaygoOfflineDesc, name)));
            this.desc.setVisibility(0);
            this.note.setVisibility(0);
        }
        getArguments();
        this.mHandler = new Handler();
        Bundle bundle2 = new Bundle();
        bundle2.putInt(NavigationTransitionKeyValues.NAVIGATION_FROM, 1009);
        this.mHandler.postDelayed(new OnboardingSuccessFragmentV2$$ExternalSyntheticLambda0(this, bundle2), 3000);
        return inflate;
    }

    /* renamed from: lambda$onCreateView$0$com-jch-racWiFi-iduOnBoarding-common-view-OnboardingSuccessFragmentV2 */
    public /* synthetic */ void mo31441x9aa658d(Bundle bundle) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_global_homePageFragment, bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
