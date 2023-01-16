package com.jch.racWiFi;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import androidx.fragment.app.DialogFragment;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.customViews.customDialogs.CustomProgressDialog;
import com.jch_hitachi.aircloudglobal.R;

public class GenericDialogFragment extends DialogFragment {
    Handler autoDismissHandler = new Handler();
    private int deviceHeight;
    private int deviceWidth;
    private GenericFragmentContainer genericFragmentContainer;
    public FragmentToActivityCallback mFragmentToActivityCallback;
    /* access modifiers changed from: private */
    public CustomProgressDialog mProgressDialogNetworkCall;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mFragmentToActivityCallback = (FragmentToActivityCallback) context;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.genericFragmentContainer = new GenericFragmentContainer(requireActivity());
        getDeviceDimension();
        this.mProgressDialogNetworkCall = new CustomProgressDialog(requireActivity());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getDialog().getWindow().setLayout(this.deviceWidth, -2);
    }

    public CoreActivity getCoreActivity() {
        return (CoreActivity) requireActivity();
    }

    private void getDeviceDimension() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.deviceHeight = displayMetrics.heightPixels;
        this.deviceWidth = displayMetrics.widthPixels;
    }

    public void showErrorPopUp(String str) {
        this.genericFragmentContainer.showErrorPopUpContent(str);
    }

    public void showPleaseWaitDialog() {
        this.mProgressDialogNetworkCall.setMessage(getString(R.string.common_alert_pleaseWait));
        CustomProgressDialog customProgressDialog = this.mProgressDialogNetworkCall;
        if (customProgressDialog != null && !customProgressDialog.isShowing()) {
            this.mProgressDialogNetworkCall.show();
            this.autoDismissHandler.postDelayed(new Runnable() {
                public void run() {
                    GenericDialogFragment.this.mProgressDialogNetworkCall.dismiss();
                    Toaster.makeToast(GenericDialogFragment.this.requireActivity(), GenericDialogFragment.this.getString(R.string.common_alert_somethingWentWrong), 0);
                }
            }, 15000);
        }
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public void dismissPleaseWaitDialog() {
        CustomProgressDialog customProgressDialog = this.mProgressDialogNetworkCall;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    GenericDialogFragment.this.mProgressDialogNetworkCall.dismiss();
                    GenericDialogFragment.this.autoDismissHandler.removeCallbacksAndMessages((Object) null);
                }
            }, 200);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.autoDismissHandler.removeCallbacksAndMessages((Object) null);
    }
}
