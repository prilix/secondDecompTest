package com.jch.racWiFi.iduOnBoarding.common.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.navigation.NavArgument;
import androidx.navigation.NavGraph;
import androidx.navigation.NavOptions;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models.WiFiCredentialModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.Map;

public class OnBoardingFailureFragment extends GenericFragment {
    @BindView(2131362176)
    Button btStartOver;
    @BindView(2131362179)
    Button mButtonTryAgain;
    private OnBackPressedCallback mOnBackPressedCallback;
    Map<String, NavArgument> navArgumentMap;
    private NavGraph navGraph;
    @BindView(2131364447)
    TextView tvRetry;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mOnBackPressedCallback = new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, this.mOnBackPressedCallback);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.device_configuration_failed_wps_frame, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        NavGraph graph = this.mFragmentToActivityCallback.getNavController().getGraph();
        this.navGraph = graph;
        Map<String, NavArgument> arguments = graph.getArguments();
        this.navArgumentMap = arguments;
        NavArgument navArgument = arguments.get(NavigationTransitionKeyValues.CONNECTION_METHOD_CHOSEN);
        int intValue = navArgument != null ? ((Integer) navArgument.getDefaultValue()).intValue() : 1010;
        String adapterType = WiFiCredentialModel.CURRENT_RAC_CREDENTIALS.getAdapterType();
        if (adapterType == null || !adapterType.equals("2")) {
            this.mButtonTryAgain.setVisibility(0);
            if (intValue == 1010) {
                this.mButtonTryAgain.setText(R.string.onboard_btn_tryAnotherMethodWps);
            } else {
                this.mButtonTryAgain.setText(R.string.onboard_btn_tryAnotherMethodAp);
            }
        } else {
            this.mButtonTryAgain.setVisibility(4);
        }
        return inflate;
    }

    @OnClick({2131362176})
    public void startOver(View view) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringFailedUdpExchange_to_qrScanFragment, (Bundle) null, new NavOptions.Builder().setPopUpTo(R.id.qrScanFragment, true).build());
    }

    @OnClick({2131364447})
    public void retry(View view) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringFailedUdpExchange_to_qrScanFragment, (Bundle) null, new NavOptions.Builder().setPopUpTo(R.id.qrScanFragment, true).build());
    }

    @OnClick({2131362179})
    public void onClickTryAntherMethod() {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_configuringFailedUdpExchange_to_qrScanFragment, (Bundle) null, new NavOptions.Builder().setPopUpTo(R.id.qrScanFragment, true).build());
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onBackPressed() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        return super.onBackPressed();
    }
}
