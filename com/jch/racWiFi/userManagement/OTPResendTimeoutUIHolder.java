package com.jch.racWiFi.userManagement;

import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customWidgets.CircleProgressBar;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class OTPResendTimeoutUIHolder implements LifecycleObserver {
    public static final String TAG = "OTPResendTimeoutUIHolder";
    public static final long countDownFinish = -1;
    public static final long countDownInterval = 1000;
    public static final long millisInFuture = 60000;
    private CountDownTimer countDownTimer;
    private LifecycleOwner lifecycleOwner;
    @BindView(2131364445)
    TextView mResendOtp;
    @BindView(2131363550)
    CircleProgressBar mTimerProgressBar;
    @BindView(2131364478)
    TextView mTimerUpdateShort;
    @BindView(2131364656)
    TextView mTimerUpdateTextView;
    private Observer<Long> observer = new OTPResendTimeoutUIHolder$$ExternalSyntheticLambda0(this);
    private View rootView;
    /* access modifiers changed from: private */
    public MutableLiveData<Long> timerCountMutableLiveData = new MutableLiveData<>();
    private Unbinder unbinder;

    public OTPResendTimeoutUIHolder(View view, LifecycleOwner lifecycleOwner2) {
        this.rootView = view;
        this.lifecycleOwner = lifecycleOwner2;
        this.unbinder = ButterKnife.bind((Object) this, view);
        this.countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long j) {
                OTPResendTimeoutUIHolder.this.timerCountMutableLiveData.setValue(Long.valueOf(j));
            }

            public void onFinish() {
                OTPResendTimeoutUIHolder.this.timerCountMutableLiveData.setValue(-1L);
            }
        };
    }

    /* access modifiers changed from: private */
    public void updateUi(long j) {
        String str;
        if (j == -1) {
            unfreezeResendOTP();
            return;
        }
        int i = (int) (j / 1000);
        this.mTimerProgressBar.setProgress(1.0f - (((float) (60000 - j)) / 60000.0f));
        this.mTimerUpdateShort.setText(i + "s");
        if (i < 10) {
            str = StatusCode.BUILTIN_WIRELESS + i;
        } else {
            str = String.valueOf(i);
        }
        this.mTimerUpdateTextView.setText(Html.fromHtml(this.mTimerUpdateTextView.getContext().getResources().getString(R.string.common_lbl_resendOtpDesc, new Object[]{"00:" + str})));
    }

    public void startResendCountDown() {
        freezeResendOTP();
        this.countDownTimer.start();
        this.lifecycleOwner.getLifecycle().addObserver(this);
    }

    public void stopResendCountDown() {
        this.countDownTimer.cancel();
        this.lifecycleOwner.getLifecycle().removeObserver(this);
    }

    public void unbind() {
        Unbinder unbinder2 = this.unbinder;
        if (unbinder2 != null) {
            unbinder2.unbind();
        }
    }

    public void freezeResendOTP() {
        this.mResendOtp.setClickable(false);
        this.mResendOtp.setEnabled(false);
        TextView textView = this.mResendOtp;
        textView.setTextColor(textView.getContext().getResources().getColor(R.color.button_disabled));
        ViewUtils.setTextViewDrawableColor(this.mResendOtp, (int) R.color.button_disabled);
        this.mTimerUpdateTextView.setVisibility(0);
        this.mTimerProgressBar.setVisibility(0);
        this.mTimerUpdateShort.setVisibility(0);
    }

    public void unfreezeResendOTP() {
        this.mResendOtp.setClickable(true);
        this.mResendOtp.setEnabled(true);
        TextView textView = this.mResendOtp;
        textView.setTextColor(textView.getContext().getResources().getColor(R.color.dark_red));
        ViewUtils.setTextViewDrawableColor(this.mResendOtp, (int) R.color.dark_red);
        this.mTimerUpdateTextView.setVisibility(8);
        this.mTimerProgressBar.setVisibility(8);
        this.mTimerUpdateShort.setVisibility(8);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart() {
        this.timerCountMutableLiveData.observe(this.lifecycleOwner, this.observer);
        Logger.m49i(TAG, "Lifecycle.Event.ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop() {
        this.timerCountMutableLiveData.removeObserver(this.observer);
        Logger.m49i(TAG, "Lifecycle.Event.ON_STOP");
    }
}
