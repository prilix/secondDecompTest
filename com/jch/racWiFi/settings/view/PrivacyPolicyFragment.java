package com.jch.racWiFi.settings.view;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Presenter.PrivacyPolicyPresenter;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;
import com.jch_hitachi.aircloudglobal.R;

public class PrivacyPolicyFragment extends GenericFragment implements PrivacyPolicyPresenter.IPrivacyPolicyPresenter {
    private SingleChoiceDialog internetCheckDialog;
    private String langCode;
    @BindView(2131362812)
    ImageButton mMenu;
    private PrivacyPolicyPresenter mPrivacyPolicyPresenter;
    private String mPrivacyPolicyString;
    @BindView(2131364423)
    TextView mPrivacyPolicyTextView;
    private Unbinder mUnbinder;

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
        privacyPolicyFragment.setNewBundleAsArgument();
        return privacyPolicyFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.internetCheckDialog = new SingleChoiceDialog(getActivity());
        this.langCode = getResources().getConfiguration().locale.getLanguage().toLowerCase();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.privacy_policy_fragment, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        PrivacyPolicyPresenter privacyPolicyPresenter = new PrivacyPolicyPresenter(this);
        this.mPrivacyPolicyPresenter = privacyPolicyPresenter;
        privacyPolicyPresenter.registerEventBus();
        this.mPrivacyPolicyTextView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mPrivacyPolicyTextView.setText("");
        if (NetworkConnectivity.isNetworkAvailable(getActivity().getApplicationContext())) {
            this.mPrivacyPolicyPresenter.checkForPrivacyPolicyUpdate(getViewLifecycleOwner());
        } else {
            showInternetAlert();
        }
        logEvent(Screens.SCREENS.name(), 14);
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mPrivacyPolicyPresenter.removeCallbacks();
        this.mUnbinder.unbind();
        this.mUnbinder = null;
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131362812})
    public void OnClickMenu(ImageButton imageButton) {
        this.iDrawerMenuFunctions.openMenuDrawer();
    }

    public void onPrivacyPolicyReceived(PrivacyPolicyModel.PrivacyPolicyDataResponse privacyPolicyDataResponse, int i) {
        dismissPleaseWaitDialog();
        if (i == 200) {
            for (PrivacyPolicyModel.PrivacyPolicyData privacyPolicyData : privacyPolicyDataResponse.privacyPolicyData) {
                if (privacyPolicyData.language.equalsIgnoreCase(LocaleConfiguration.getLanguageCodeForCurrentLocale()) || privacyPolicyData.language.equalsIgnoreCase("en")) {
                    this.mPrivacyPolicyTextView.setText(Html.fromHtml(privacyPolicyData.privacyPolicyMessage));
                }
            }
        }
    }

    public void onPrivacyPolicyReceivedFailure() {
        Logger.m47e("P_P_Failure", "Privacy Policy Fragment");
    }

    public void onNetworkCallFailure(Throwable th) {
        Toaster.makeToast(getContext(), getString(R.string.common_alert_unableToReachServer), 1);
    }

    private void showInternetAlert() {
        getCoreActivity().dismissPleaseWaitDialog();
        dismissPleaseWaitDialog();
        SingleChoiceDialog singleChoiceDialog = this.internetCheckDialog;
        if (singleChoiceDialog != null) {
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            this.internetCheckDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
            this.internetCheckDialog.setCancelable(false);
            this.internetCheckDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                public boolean onButtonClickListener(Dialog dialog, View view) {
                    return true;
                }
            });
            if (!this.internetCheckDialog.isShowing()) {
                this.internetCheckDialog.show();
            }
        }
    }
}
