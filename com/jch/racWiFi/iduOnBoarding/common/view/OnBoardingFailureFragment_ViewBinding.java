package com.jch.racWiFi.iduOnBoarding.common.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch_hitachi.aircloudglobal.R;

public class OnBoardingFailureFragment_ViewBinding implements Unbinder {
    private OnBoardingFailureFragment target;
    private View view7f0a0180;
    private View view7f0a0183;
    private View view7f0a0a5f;

    public OnBoardingFailureFragment_ViewBinding(final OnBoardingFailureFragment onBoardingFailureFragment, View view) {
        this.target = onBoardingFailureFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.text_view_retry, "field 'tvRetry' and method 'retry'");
        onBoardingFailureFragment.tvRetry = (TextView) C0840Utils.castView(findRequiredView, R.id.text_view_retry, "field 'tvRetry'", TextView.class);
        this.view7f0a0a5f = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                onBoardingFailureFragment.retry(view);
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.button_start_over, "field 'btStartOver' and method 'startOver'");
        onBoardingFailureFragment.btStartOver = (Button) C0840Utils.castView(findRequiredView2, R.id.button_start_over, "field 'btStartOver'", Button.class);
        this.view7f0a0180 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                onBoardingFailureFragment.startOver(view);
            }
        });
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.button_try_another_method_wps, "field 'mButtonTryAgain' and method 'onClickTryAntherMethod'");
        onBoardingFailureFragment.mButtonTryAgain = (Button) C0840Utils.castView(findRequiredView3, R.id.button_try_another_method_wps, "field 'mButtonTryAgain'", Button.class);
        this.view7f0a0183 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                onBoardingFailureFragment.onClickTryAntherMethod();
            }
        });
    }

    public void unbind() {
        OnBoardingFailureFragment onBoardingFailureFragment = this.target;
        if (onBoardingFailureFragment != null) {
            this.target = null;
            onBoardingFailureFragment.tvRetry = null;
            onBoardingFailureFragment.btStartOver = null;
            onBoardingFailureFragment.mButtonTryAgain = null;
            this.view7f0a0a5f.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0a5f = null;
            this.view7f0a0180.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0180 = null;
            this.view7f0a0183.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0183 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
