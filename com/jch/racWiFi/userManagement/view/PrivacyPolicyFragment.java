package com.jch.racWiFi.userManagement.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;
import com.jch_hitachi.aircloudglobal.R;

public class PrivacyPolicyFragment extends DialogFragment {
    public static final String PRIVACY_POLICY_PREF_KEY = "PRIVACY_POLICY_PREF_KEY";
    @BindView(2131362127)
    Button cancel;
    @BindView(2131362146)
    ImageButton dismiss;
    /* access modifiers changed from: private */
    public boolean hideAcceptance;
    /* access modifiers changed from: private */
    public boolean hideTitle = true;
    @BindView(2131362155)
    Button iAgree;
    @BindView(2131364423)
    TextView mPrivacyPolicy;
    private String mPrivacyPolicyString;
    @BindView(2131364424)
    TextView mPrivacyPolicyTitle;
    private Unbinder mUnbinder;
    private View.OnClickListener onClickListener;
    private boolean takeToLogin = false;

    public void setHideTitle(boolean z) {
        this.hideTitle = true;
    }

    public void setHideAcceptance(boolean z) {
        this.hideAcceptance = z;
    }

    public void setTakeToLogin(boolean z) {
        this.takeToLogin = z;
    }

    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
        privacyPolicyFragment.setArguments(new Bundle());
        return privacyPolicyFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PrivacyPolicyModel.PrivacyPolicyData privacyPolicyData = (PrivacyPolicyModel.PrivacyPolicyData) getArguments().getParcelable(PrivacyPolicyModel.PrivacyPolicyData.PREF_KEY);
        if (privacyPolicyData != null) {
            this.mPrivacyPolicyString = privacyPolicyData.privacyPolicyMessage;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.privacy_policy, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.mPrivacyPolicy.setMovementMethod(LinkMovementMethod.getInstance());
        String str = this.mPrivacyPolicyString;
        if (str != null) {
            this.mPrivacyPolicy.setText(Html.fromHtml(str));
        }
        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                DisplayMetrics displayMetrics = PrivacyPolicyFragment.this.getActivity().getResources().getDisplayMetrics();
                PrivacyPolicyFragment.this.getDialog().getWindow().setLayout((int) (((double) displayMetrics.widthPixels) * 0.99d), (int) (((double) displayMetrics.heightPixels) * 0.99d));
                if (PrivacyPolicyFragment.this.hideAcceptance) {
                    PrivacyPolicyFragment.this.iAgree.setVisibility(8);
                    PrivacyPolicyFragment.this.cancel.setVisibility(8);
                    PrivacyPolicyFragment.this.dismiss.setVisibility(0);
                }
                if (PrivacyPolicyFragment.this.hideTitle) {
                    PrivacyPolicyFragment.this.mPrivacyPolicyTitle.setVisibility(8);
                }
            }
        });
        return inflate;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mUnbinder.unbind();
    }

    public void onDetach() {
        super.onDetach();
    }

    @OnClick({2131362127})
    public void onClickCancel() {
        if (this.takeToLogin) {
            dismiss();
        } else {
            getActivity().finish();
        }
    }

    @OnClick({2131362146})
    public void onClickDismissFragment() {
        dismiss();
    }

    @OnClick({2131362155})
    public void onClickAgree(Button button) {
        this.onClickListener.onClick(button);
        Constants.isTutorialFragmentShowing = false;
    }

    public static boolean privacyPolicyShown() {
        return SharedPref.getInstance().getSharedPreferencesReader().getBoolean(PRIVACY_POLICY_PREF_KEY, false);
    }

    public static void setPrivacyPolicyShown() {
        SharedPref.getInstance().getSharedPreferenceEditor().putBoolean(PRIVACY_POLICY_PREF_KEY, true).commit();
    }

    public void setOnClickAgree(View.OnClickListener onClickListener2) {
        this.onClickListener = onClickListener2;
    }
}
