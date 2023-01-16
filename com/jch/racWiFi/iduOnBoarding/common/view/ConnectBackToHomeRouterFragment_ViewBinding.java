package com.jch.racWiFi.iduOnBoarding.common.view;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class ConnectBackToHomeRouterFragment_ViewBinding implements Unbinder {
    private ConnectBackToHomeRouterFragment target;
    private View view7f0a011e;
    private View view7f0a0158;
    private View view7f0a02a5;

    public ConnectBackToHomeRouterFragment_ViewBinding(final ConnectBackToHomeRouterFragment connectBackToHomeRouterFragment, View view) {
        this.target = connectBackToHomeRouterFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_connect_to_home_router, "field 'connectToHomeRouter' and method 'onClickConnectToHomeRouter'");
        connectBackToHomeRouterFragment.connectToHomeRouter = (Button) C0840Utils.castView(findRequiredView, R.id.button_connect_to_home_router, "field 'connectToHomeRouter'", Button.class);
        this.view7f0a0158 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                connectBackToHomeRouterFragment.onClickConnectToHomeRouter();
            }
        });
        connectBackToHomeRouterFragment.ssidNameText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.tv_home_router_ssid, "field 'ssidNameText'", TextView.class);
        connectBackToHomeRouterFragment.subTitleText = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_connect_to_wifi_network_sub_title, "field 'subTitleText'", TextView.class);
        connectBackToHomeRouterFragment.textViewStep_4_of_4 = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_4, "field 'textViewStep_4_of_4'", TextView.class);
        connectBackToHomeRouterFragment.includedLayout = C0840Utils.findRequiredView(view, R.id.included_layout, "field 'includedLayout'");
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "method 'onClickBack'");
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                connectBackToHomeRouterFragment.onClickBack();
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.forward_button, "method 'onClickForward'");
        this.view7f0a02a5 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                connectBackToHomeRouterFragment.onClickForward();
            }
        });
    }

    public void unbind() {
        ConnectBackToHomeRouterFragment connectBackToHomeRouterFragment = this.target;
        if (connectBackToHomeRouterFragment != null) {
            this.target = null;
            connectBackToHomeRouterFragment.connectToHomeRouter = null;
            connectBackToHomeRouterFragment.ssidNameText = null;
            connectBackToHomeRouterFragment.subTitleText = null;
            connectBackToHomeRouterFragment.textViewStep_4_of_4 = null;
            connectBackToHomeRouterFragment.includedLayout = null;
            this.view7f0a0158.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0158 = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            this.view7f0a02a5.setOnClickListener((View.OnClickListener) null);
            this.view7f0a02a5 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
